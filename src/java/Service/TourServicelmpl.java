/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DAO.TourDAO;
import DAO.TourDAOlmpl;
import Model.Tour;
import java.util.List;

/**
 *
 * @author admin
 */
public class TourServicelmpl implements TourService{

    TourDAO tourDao = new TourDAOlmpl();
    @Override
    public List<Tour> getAllTour() {
        List<Tour> tour = tourDao.getAllTour();
        return tour;
    }

    @Override
    public Tour getTourDetail(int id) {
      Tour tourDetail = tourDao.getTourDetail(id);
      return tourDetail;
    }

    @Override
    public boolean saveTour(int userId, int tourId, int statusId) {
        boolean isSave;
        isSave = tourDao.saveTour(userId, tourId, statusId);
        return isSave;
    }
    
}
