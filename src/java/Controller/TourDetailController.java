/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import Model.Feedback;
import Model.Rating;
import Model.Tour;
import Service.FeedbackService;
import Service.FeedbackServiceImpl;
import Service.RatingService;
import Service.RatingServiceImpl;
import Service.TourService;
import Service.TourServicelmpl;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author admin
 */
public class TourDetailController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TourDetailServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TourDetailServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    try {
        int id = Integer.parseInt(request.getParameter("id"));

        TourService tourService = new TourServicelmpl();
        RatingService ratingService = new RatingServiceImpl();
        FeedbackService feedbackService = new FeedbackServiceImpl();

        Tour a = tourService.getTourDetail(id);

        List<Rating> ratings = ratingService.getRatingsByTour(id); 
        List<Feedback> feedbacks = feedbackService.getFeedbacksByTour(id);

        Map<Integer, Map<String, String>> userReviews = new HashMap<>();

        for (Rating r : ratings) {
            Map<String, String> review = new HashMap<>();
            review.put("userID", String.valueOf(r.getUserID()));
            review.put("fullName", r.getFullName()); // Lấy FullName từ Rating
            review.put("rating", "⭐".repeat(r.getRating()));
            review.put("comment", ""); 
            review.put("createdAt", ""); 
            userReviews.put(r.getUserID(), review);
        }

        for (Feedback f : feedbacks) {
            int userID = f.getUserID();
            userReviews.putIfAbsent(userID, new HashMap<>());
            Map<String, String> review = userReviews.get(userID);
            review.put("userID", String.valueOf(userID));
            review.put("comment", f.getComment());
            review.put("createdAt", f.getCreatedAt() != null ? f.getCreatedAt().toString() : "");
            review.putIfAbsent("rating", "Chưa đánh giá");
        }

        // Gửi dữ liệu sang JSP
        request.setAttribute("tour", a);
        request.setAttribute("id", id);
        request.setAttribute("userReviews", new ArrayList<>(userReviews.values())); 

        // Chuyển hướng sang trang TourDetail.jsp
        request.getRequestDispatcher("TourDetail.jsp").forward(request, response);
    } catch (NumberFormatException e) {
        e.printStackTrace();
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID tour không hợp lệ");
    } catch (Exception e) {
        e.printStackTrace();
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi xử lý dữ liệu");
    }
}
 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
