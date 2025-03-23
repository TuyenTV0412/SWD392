/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DAO.BookingDAOlmpl;
import Model.Booking;
import java.util.List;
import DAO.BookingDAO;

/**
 *
 * @author admin
 */
public class BookingServicelmpl implements BookingService {

    BookingDAO b = new BookingDAOlmpl();

    @Override
    public List<Booking> getTourBookingByUserId(int id) {
        List<Booking> listBooking = b.getTourBookingByUserId(id);
        return listBooking;
         }

    @Override
    public boolean updateStatusTour(int id) {
        boolean isUpdate ;
        isUpdate = b.updateStatusTour(id);
        return  isUpdate;
         }

    @Override
    public Booking getTourBookingById(int id) {
        Booking booking = b.getTourBookingById(id);
        return booking;
        }

}
