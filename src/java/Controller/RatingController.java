package Controller;

import DAO.RatingDAO;
import DAO.RatingDAOImpl;
import Model.Rating;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/rating")
public class RatingController extends HttpServlet {
    private RatingDAO ratingDAO;

    @Override
    public void init() {
        ratingDAO = new RatingDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        int userID = Integer.parseInt(request.getParameter("userID"));
//        int tourID = Integer.parseInt(request.getParameter("tourID"));
//        int ratingValue = Integer.parseInt(request.getParameter("rating"));
//
//        Rating rating = new Rating(0, userID, tourID, ratingValue, new java.sql.Timestamp(System.currentTimeMillis()));
//        ratingDAO.addRating(rating);
//
//        response.sendRedirect("tourDetail?tourID=" + tourID);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int tourID = Integer.parseInt(request.getParameter("tourID"));

        List<Rating> ratings = ratingDAO.getRatingsByTour(tourID);
        request.setAttribute("ratings", ratings);
        request.getRequestDispatcher("TourDetail.jsp").forward(request, response);
    }
}
