package DAO;

import Model.Rating;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class RatingDAOImpl extends DBContext implements RatingDAO {
  

    @Override
    public void addRating(Rating rating) {
        String sql = "INSERT INTO Ratings (UserID, TourID, Rating, CreatedAt) VALUES (?, ?, ?, GETDATE())";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, rating.getUserID());
            stmt.setInt(2, rating.getTourID());
            stmt.setInt(3, rating.getRating());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   @Override
public List<Rating> getRatingsByTour(int tourID) {
    List<Rating> ratings = new ArrayList<>();
    String sql = "SELECT r.*, u.FullName " +
                 "FROM Ratings r " +
                 "JOIN Users u ON r.UserID = u.UserID " +
                 "WHERE r.TourID = ?";
    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setInt(1, tourID);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Rating rating = new Rating(
                rs.getInt("RatingID"),
                rs.getInt("UserID"),
                rs.getInt("TourID"),
                rs.getInt("Rating"),
                rs.getTimestamp("CreatedAt"),
                rs.getString("FullName") // Lấy FullName từ bảng Users
            );
            ratings.add(rating);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return ratings;
}

    
    public static void main(String[] args) {
        RatingDAOImpl ratingDAO = new RatingDAOImpl();
            List<Rating> a = ratingDAO.getRatingsByTour(3);

        for (Rating c : a) {
            System.out.println(c);
        }

     
        
        //Rating newRating = new Rating(1, 2, 5, new Date());
        
        //ratingDAO.addRating(newRating);
      
    }
}
