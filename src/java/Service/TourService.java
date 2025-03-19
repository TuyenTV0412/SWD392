/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.Tour;
import java.util.List;

/**
 *
 * @author admin
 */
public interface TourService {
    public List<Tour> getAllTour();
    
     public Tour getTourDetail(int id);
     
     public boolean saveTour(int userId, int tourId, int statusId);
}
