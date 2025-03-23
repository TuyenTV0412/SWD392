/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Hotel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author admin
 */
public class HotelDAOImpl extends DBContext implements HotelDAO {

    @Override
    public Hotel getHotelById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    

    @Override
    public List<Hotel> getAllHotel(String search) {
         List<Hotel> HotelList = new ArrayList<>();
         String sql;
         if(search != null){
             sql = "select * from Hotels where HotelName LIKE '%"+ search+ "%' \n" +
                                    "  or Location LIKE '%"+ search+ "%' \n" +
                                    "  or Description LIKE '%"+ search+ "%'\n" +
                                    "  or RoomAvailable LIKE '%"+ search+ "%'";
         }
         else{
              sql = "select * from Hotels";
         }
        try {
            
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Hotel h = new Hotel();
                h.setHotelID(rs.getInt("HotelID"));
                h.setHotelName(rs.getString("HotelName"));
                h.setLocation(rs.getString("Location"));
                h.setDescription(rs.getString("Description"));
                h.setRoomAvailable(rs.getInt("RoomAvailable"));
                h.setCreatedBy(rs.getInt("CreatedBy"));
                h.setCreatedAt(rs.getDate("CreatedAt"));

                HotelList.add(h);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return HotelList;
    }
    
    @Override
    public boolean createHotel(String name, String location, String description, int roomAvailable) {
         
        try {
            String sql = "INSERT INTO Hotels (HotelName, Location, Description, roomAvailable) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, location);
            ps.setString(3, description);
            ps.setInt(4, roomAvailable);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateHotel(String name, String location, String description, int roomAvailable, int hotelID) {
        try {
            String sql = "UPDATE Hotels SET HotelName = ?, Location = ?, Description = ?, RoomAvailable = ? WHERE HotelID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, location);
            ps.setString(3, description);
            ps.setInt(4, roomAvailable);
            ps.setInt(5, hotelID);
            return (ps.executeUpdate() > 0);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteHotel(int hotelID) {
         try {
            String sql = "Delete Hotels WHERE HotelID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, hotelID);
            return (ps.executeUpdate() > 0);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
    

