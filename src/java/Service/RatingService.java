/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service;

import Model.Rating;
import java.util.List;

/**
 *
 * @author admin
 */
public interface RatingService {
    void addRating(Rating rating); 
    List<Rating> getRatingsByTour(int tourID); 
}
