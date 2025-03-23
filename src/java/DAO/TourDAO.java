/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Tour;
import java.util.List;

/**
 *
 * @author admin
 */
public interface TourDAO {
    public List<Tour> getAllTour();
    
    public Tour getTourDetail(int id);
    
     public boolean saveTour(int userId, int tourId, int statusId);
    Tour getTourByID(int tourID);
    public boolean addTour(Tour tour);
    public boolean editTour(Tour tour);
    public boolean deleteTour(int tourID);
    List<Tour> searchTours(String keyword);
    List<Tour> getToursByPage(int pageNumber, int pageSize);
    int getTotalTours();
}
