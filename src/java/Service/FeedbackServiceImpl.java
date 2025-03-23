package Service;

import DAO.FeedbackDAO;
import DAO.FeedbackDAOImpl;
import Model.Feedback;
import java.util.List;

public class FeedbackServiceImpl implements FeedbackService {

   FeedbackDAO feedbackDAO = new FeedbackDAOImpl();

    @Override
    public void addFeedback(Feedback feedback) {
        feedbackDAO.addFeedback(feedback);
    }

    @Override
    public List<Feedback> getFeedbacksByTour(int tourID) {
        return feedbackDAO.getFeedbacksByTour(tourID);
    }
}
