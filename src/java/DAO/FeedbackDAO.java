package DAO;

import Model.Feedback;
import java.util.List;

public interface FeedbackDAO {
    void addFeedback(Feedback feedback); 
    List<Feedback> getFeedbacksByTour(int tourID); 
}
