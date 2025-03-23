package DAO;

import Model.Rating;
import java.util.List;

public interface RatingDAO {
    void addRating(Rating rating); 
    List<Rating> getRatingsByTour(int tourID); 
}
