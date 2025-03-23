/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Hotel;
import java.util.List;

/**
 *
 * @author admin
 */
public interface HotelDAO {
    public Hotel getHotelById(int id);
    
    public List getAllHotel(String search);
    public boolean createHotel(String name, String location, String description, int roomAvailable);
    public boolean updateHotel(String name, String location, String description, int roomAvailable, int HotelID);
    public boolean deleteHotel(int hotelID);
    
}
