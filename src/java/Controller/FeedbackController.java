package Controller;

import Service.FeedbackService;
import Service.FeedbackServiceImpl;
import Service.RatingService;
import Service.RatingServiceImpl;
import Model.Feedback;
import Model.Rating;
import java.util.Date;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/feedback")
public class FeedbackController extends HttpServlet {

    private final FeedbackService feedbackService = new FeedbackServiceImpl();
    private final RatingService ratingService = new RatingServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        String tourID = request.getParameter("tourID");
        if (tourID == null || tourID.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Thiếu tourID");
            return;
        }

        request.setAttribute("tourID", tourID);
        request.getRequestDispatcher("Feedback.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
           
            Integer userID = (Integer) request.getSession().getAttribute("userID");
            if (userID == null) {
                response.sendRedirect("login");
                return;
            }

            String tourIDStr = request.getParameter("tourID");
            String ratingStr = request.getParameter("rating");
            String comment = request.getParameter("message");

            if (tourIDStr == null || ratingStr == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Thiếu dữ liệu");
                return;
            }

            int tourID = Integer.parseInt(tourIDStr);
            int rating = Integer.parseInt(ratingStr);

            if (rating < 1 || rating > 5) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Giá trị rating không hợp lệ");
                return;
            }

            Date createdAt = new Date();

           
            Rating ratingObj = new Rating(userID, tourID, rating, createdAt);
            ratingService.addRating(ratingObj);

            
            if (comment != null && !comment.trim().isEmpty()) {
                Feedback feedback = new Feedback(userID, tourID, comment, createdAt);
                feedbackService.addFeedback(feedback);
            }

            response.sendRedirect(request.getContextPath() + "/bookingTour");

        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Dữ liệu không hợp lệ");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi xử lý dữ liệu");
        }
    }
}
