/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Tour;
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
public class TourDAOlmpl extends DBContext implements TourDAO {

    @Override
    public List<Tour> getAllTour() {
        List<Tour> listTour = new ArrayList<>();
        try {
            String sql = "select * from Tours join TourImages on Tours.TourID = TourImages.TourID";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Tour t = new Tour();
                t.setTourID(rs.getInt("TourID"));
                t.setTourName(rs.getString("TourName"));
                t.setDescription(rs.getString("Description"));
                t.setPrice(rs.getDouble("Price"));
                t.setStartDate(rs.getDate("StartDate"));
                t.setEndDate(rs.getDate("EndDate"));
                t.setAvailableSeats(rs.getInt("AvailableSeats"));
                t.setCreatedAt(rs.getDate("CreatedAt"));
                t.setHotelID(rs.getInt("HotelID"));
                t.setImage(rs.getString("ImageURL"));

                listTour.add(t);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listTour;
    }


    @Override
    public Tour getTourDetail(int id) {
        try {
            String sql = "select * from Tours join TourImages \n"
                    + "on Tours.TourID = TourImages.TourID\n"
                    + "where Tours.TourID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Tour t = new Tour();
                t.setTourID(rs.getInt("TourID"));
                t.setTourName(rs.getString("TourName"));
                t.setDescription(rs.getString("Description"));
                t.setPrice(rs.getDouble("Price"));
                t.setStartDate(rs.getDate("StartDate"));
                t.setEndDate(rs.getDate("EndDate"));
                t.setAvailableSeats(rs.getInt("AvailableSeats"));
                t.setCreatedAt(rs.getDate("CreatedAt"));
                t.setHotelID(rs.getInt("HotelID"));
                t.setImage(rs.getString("ImageURL"));
                return t;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
//     public Book getBookDetail(int bookID) {
//
//        String sql = """
//                     select b.*,p.PublisherName from Book b
//                     JOIN Publisher p ON b.PublisherID=p.PublisherID
//                     where BookID =?""";
//        try {
//            PreparedStatement st = connection.prepareStatement(sql);
//            st.setInt(1, bookID);
//            ResultSet rs = st.executeQuery();
//            if (rs.next()) {
//                return new Book(rs.getInt("BookID"),
//                        rs.getString("BookName"),
//                        rs.getString("Images"),
//                        rs.getString("Author"),
//                        rs.getInt("PublisherID"),
//                        rs.getString("PublisherName"),
//                        rs.getInt("CategoryID"),
//                        rs.getInt("PublishingYear"),
//                        rs.getString("Description"));
//            }
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        return null;
//    }

    @Override
        public boolean saveTour(int userId, int tourId, int statusId) {
        String sql = "INSERT INTO Bookings (UserID, TourID, BookingDate, StatusID) VALUES (?, ?, GETDATE(), ?)";
        try (PreparedStatement st = connection.prepareStatement(sql);) {
            st.setInt(1, userId);
            st.setInt(2, tourId);
            st.setInt(3, statusId);
            int rowsInserted = st.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
        
            public static void main(String[] args) {
        TourDAOlmpl t = new TourDAOlmpl();

//        List<Tour> a = t.getAllTour();
//        
//         for (Tour tour : a) {
//            System.out.println(tour.toString());  // This will use the toString method of Tour class to print the details
//        }

//        Tour a = t.getTourDetail(3);
//        System.out.println(a);
            boolean a = t.saveTour(2, 4, 1);
                System.out.println(a);
    }

}
