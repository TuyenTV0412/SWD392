/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Booking;
import Model.Rating;
import Model.Tour;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class BookingDAOlmpl extends DBContext implements BookingDAO {

    @Override
    public List<Booking> getTourBookingByUserId(int id) {
        String sql = "SELECT b.*, t.TourName, t.Description, t.StartDate, t.EndDate, t.Price, "
                + "ti.ImageURL, s.StatusName, "
                + "r.RatingID, r.Rating "
                + // Thêm thông tin từ bảng Ratings
                "FROM Bookings b "
                + "JOIN Tours t ON b.TourID = t.TourID "
                + "JOIN TourImages ti ON t.TourID = ti.TourID "
                + "JOIN Status s ON b.StatusID = s.StatusID "
                + "LEFT JOIN Ratings r ON b.UserID = r.UserID AND b.TourID = r.TourID "
                + // JOIN để lấy Rating (có thể null)
                "WHERE b.UserID = ?";

        List<Booking> bookingList = new ArrayList<>();

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Booking booking = new Booking();
                booking.setBookingId(rs.getInt("BookingID"));
                booking.setUserId(rs.getInt("UserID"));
                booking.setTourId(rs.getInt("TourID"));
                booking.setBookingDate(rs.getDate("BookingDate"));
                booking.setStatusId(rs.getInt("StatusID"));
                booking.setStatusName(rs.getString("StatusName"));
                booking.setImage(rs.getString("ImageURL"));
                booking.setTourName(rs.getString("TourName"));
                booking.setDescription(rs.getString("Description"));
                booking.setStartDate(rs.getDate("StartDate"));
                booking.setEndDate(rs.getDate("EndDate"));
                booking.setPrice(rs.getDouble("Price"));

                // Xử lý rating (có thể NULL)
                if (rs.getObject("RatingID") != null) {
                    Rating rating = new Rating();
                    rating.setRatingID(rs.getInt("RatingID"));
                    rating.setRating(rs.getInt("Rating"));
                    booking.setRating(rating);
                } else {
                    booking.setRating(null); // Chưa có đánh giá
                }

                // Thêm vào danh sách
                bookingList.add(booking);
            }

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookingList;
    }

   @Override
public boolean updateStatusTour(int id) {
    String sql = "UPDATE Bookings SET StatusID = 4 WHERE BookingID = ?";
    boolean isUpdated = false;

    try {
        PreparedStatement st = connection.prepareStatement(sql);
        st.setInt(1, id);
        int rowsUpdated = st.executeUpdate();
        if (rowsUpdated > 0) {
            isUpdated = true;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return isUpdated;
}


    @Override
    public Booking getTourBookingById(int id) {
        String sql = "select * from Bookings b\n"
                + "join Tours t\n"
                + "on b.TourID = t.TourID\n"
                + "join TourImages ti\n"
                + "on t.TourID =ti.TourID\n"
                + "join Status s\n"
                + "on b.StatusID =s.StatusID\n"
                + "where b.BookingID =?";

        Booking booking = null;

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                booking = new Booking();
                booking.setBookingId(rs.getInt("BookingID"));
                booking.setUserId(rs.getInt("UserID"));
                booking.setTourId(rs.getInt("TourID"));
                booking.setBookingDate(rs.getDate("BookingDate"));
                booking.setStatusId(rs.getInt("StatusID"));
                booking.setStatusName(rs.getString("StatusName"));
                booking.setImage(rs.getString("ImageURL"));
                booking.setTourName(rs.getString("TourName"));
                booking.setDescription(rs.getString("Description"));
                booking.setStartDate(rs.getDate("StartDate"));
                booking.setEndDate(rs.getDate("EndDate"));
                booking.setPrice(rs.getDouble("Price"));
            }

            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return booking;

    }

    public static void main(String[] args) {
        BookingDAOlmpl b = new BookingDAOlmpl();
//        List<Booking> a = b.getTourBookingByUserId(2);
//
//        for (Booking c : a) {
//            System.out.println(c);
//        }

//        Booking c = b.getTourBookingById(2);
//        System.out.println(c);

        boolean c = b.updateStatusTour(1);
        System.out.println(c);
    }

}
