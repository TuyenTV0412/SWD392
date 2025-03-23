package DAO;

import Model.Feedback;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FeedbackDAOImpl extends DBContext implements FeedbackDAO {


    @Override
    public void addFeedback(Feedback feedback) {
        String sql = "INSERT INTO Feedbacks (UserID, TourID, Comment, CreatedAt) VALUES (?, ?, ?, GETDATE())";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, feedback.getUserID());
            stmt.setInt(2, feedback.getTourID());
            stmt.setString(3, feedback.getComment());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Feedback> getFeedbacksByTour(int tourID) {
        List<Feedback> feedbacks = new ArrayList<>();
        String sql = "SELECT * FROM Feedbacks WHERE TourID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, tourID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Feedback feedback = new Feedback(
                    rs.getInt("FeedbackID"),
                    rs.getInt("UserID"),
                    rs.getInt("TourID"),
                    rs.getString("Comment"),
                    rs.getTimestamp("CreatedAt")
                );
                feedbacks.add(feedback);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return feedbacks;
    }
}
