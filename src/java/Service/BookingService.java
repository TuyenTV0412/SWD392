/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.Booking;
import java.util.List;

/**
 *
 * @author admin
 */
public interface BookingService {

    public List<Booking> getTourBookingByUserId(int id);
    
    public Booking getTourBookingById(int id);

    public boolean updateStatusTour(int id);

}
