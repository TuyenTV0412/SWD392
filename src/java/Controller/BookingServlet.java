/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import Model.Tour;
import Model.User;
import Service.TourService;
import Service.TourServicelmpl;
import Service.UserServicelmpl;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author admin
 */
public class BookingServlet extends HttpServlet {
   
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
            out.println("<title>Servlet BookingServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BookingServlet at " + request.getContextPath () + "</h1>");
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
        int id = Integer.parseInt(request.getParameter("id"));

        
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        } else {
            //get tour
            TourService tourDAO = new TourServicelmpl();
            Tour a = tourDAO.getTourDetail(id);
            
            int userId = user.getUserID();
            UserServicelmpl useS = new UserServicelmpl();
            User u = useS.getUserById(userId);
            request.setAttribute("user", u);
            request.setAttribute("tour", a);
            request.getRequestDispatcher("BookingTour.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp"); // Chuyển hướng nếu chưa đăng nhập
            return;
        }

        int tourId = Integer.parseInt(request.getParameter("tourID"));
        int statusId = 1; // Mặc định trạng thái đặt chờ duyệt (hoặc lấy từ request)
         TourService tourDAO = new TourServicelmpl();
        boolean success = tourDAO.saveTour(user.getUserID(), tourId, statusId);

        if (success) {
            
            response.sendRedirect("home"); // Chuyển đến trang thành công
        } else {
            response.sendRedirect("bookingFailed.jsp"); // Chuyển đến trang thất bại
        }
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
