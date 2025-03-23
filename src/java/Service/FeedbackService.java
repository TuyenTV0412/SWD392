/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import Model.Feedback;
import java.util.List;

/**
 *
 * @author admin
 */
public interface FeedbackService {
    void addFeedback(Feedback feedback); 
    List<Feedback> getFeedbacksByTour(int tourID); 
}
