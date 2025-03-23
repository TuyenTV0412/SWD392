/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DAO.RatingDAO;
import DAO.RatingDAOImpl;
import Model.Rating;
import java.util.List;

/**
 *
 * @author admin
 */
public class RatingServiceImpl implements RatingService{
    RatingDAO ratingDAO = new RatingDAOImpl();

    @Override
    public void addRating(Rating rating) {
        ratingDAO.addRating(rating);
    }

    @Override
    public List<Rating> getRatingsByTour(int tourID) {
        return ratingDAO.getRatingsByTour(tourID);
    }
}
