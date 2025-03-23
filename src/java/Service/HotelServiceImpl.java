/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DAO.HotelDAO;
import DAO.HotelDAOImpl;
import Model.Hotel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class HotelServiceImpl implements HotelService {
    HotelDAO hotelDAO = new HotelDAOImpl();
    @Override
    public List getAllHotel(String search) {
        List<Hotel> HotelList = new ArrayList<>();
        HotelList = hotelDAO.getAllHotel(search);
        return HotelList;
    }

    @Override
    public boolean createHotel(String name, String location, String description, int roomAvailable) {
        boolean hotelCreated;
        hotelCreated = hotelDAO.createHotel(name, location, description, roomAvailable);
        return hotelCreated;
        
    }

    @Override
    public boolean updateHotel(String name, String location, String description, int roomAvailable, int HotelID) {
        boolean hotelUpdated;    
        hotelUpdated = hotelDAO.updateHotel(name, location, description, roomAvailable, HotelID);
        return hotelUpdated;
    }

    @Override
    public boolean deleteHotel(int hotelID) {
        boolean hotelDeleted;
        hotelDeleted =  hotelDAO.deleteHotel(hotelID);
        return hotelDeleted;
    }
    
}
