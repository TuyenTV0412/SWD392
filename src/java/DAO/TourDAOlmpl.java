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
import java.sql.Connection;

/**
 *
 * @author admin
 */
public class TourDAOlmpl extends DBContext implements TourDAO {

    @Override
    public List<Tour> getAllTour() {
        List<Tour> listTour = new ArrayList<>();
        java.sql.Connection conn = null;
        java.sql.PreparedStatement ps = null;
        java.sql.ResultSet rs = null;

        try {
            conn = new DBContext().getConnection();

            // Truy vấn lấy tour và hình ảnh
            String sql = "SELECT t.*, ti.ImageURL FROM Tours t "
                    + "LEFT JOIN TourImages ti ON t.TourID = ti.TourID";

            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

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
                t.setImage(rs.getString("ImageURL")); // Lấy đường dẫn hình ảnh

                listTour.add(t);
            }
        } catch (SQLException e) {
            System.out.println("Error getting all tours: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
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

    @Override
    public boolean addTour(Tour tour) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet generatedKeys = null;

        try {
            // Tạo kết nối mới
            conn = new DBContext().getConnection();

            if (conn == null) {
                System.out.println("Không thể kết nối đến database");
                return false;
            }

            String sql = "INSERT INTO Tours (TourName, Description, Price, StartDate, EndDate, AvailableSeats, CreatedAt) VALUES (?, ?, ?, ?, ?, ?, GETDATE())";
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // Thiết lập các tham số
            ps.setString(1, tour.getTourName());
            ps.setString(2, tour.getDescription());
            ps.setDouble(3, tour.getPrice());
            ps.setDate(4, new java.sql.Date(tour.getStartDate().getTime()));
            ps.setDate(5, new java.sql.Date(tour.getEndDate().getTime()));
            ps.setInt(6, tour.getAvailableSeats());

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int tourId = generatedKeys.getInt(1);

                    // Fix lỗi thứ ba - kiểm tra null trước khi kiểm tra isEmpty
                    String imageUrl = tour.getImage();
                    if (imageUrl != null && !imageUrl.isEmpty()) {
                        // Đóng PreparedStatement trước khi tái sử dụng
                        if (ps != null) {
                            ps.close();
                        }

                        sql = "INSERT INTO TourImages (TourID, ImageURL) VALUES (?, ?)";
                        ps = conn.prepareStatement(sql);
                        ps.setInt(1, tourId);
                        ps.setString(2, imageUrl);
                        ps.executeUpdate();
                    }

                    return true;
                }
            }

            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            // Đóng các tài nguyên
            try {
                if (generatedKeys != null) {
                    generatedKeys.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean editTour(Tour tour) {
        try {
            // Bắt đầu transaction
            connection.setAutoCommit(false);

            // Cập nhật thông tin tour
            String sqlUpdateTour = "UPDATE Tours SET TourName = ?, Description = ?, Price = ?, "
                    + "StartDate = ?, EndDate = ?, AvailableSeats = ?, HotelID = ? "
                    + "WHERE TourID = ?";

            PreparedStatement pstTour = connection.prepareStatement(sqlUpdateTour);
            pstTour.setString(1, tour.getTourName());
            pstTour.setString(2, tour.getDescription());
            pstTour.setDouble(3, tour.getPrice());
            pstTour.setDate(4, new java.sql.Date(tour.getStartDate().getTime()));
            pstTour.setDate(5, new java.sql.Date(tour.getEndDate().getTime()));
            pstTour.setInt(6, tour.getAvailableSeats());
            pstTour.setInt(7, tour.getHotelID());
            pstTour.setInt(8, tour.getTourID());

            int tourUpdated = pstTour.executeUpdate();

            // Cập nhật ảnh tour
            String sqlUpdateImage = "UPDATE TourImages SET ImageURL = ? WHERE TourID = ?";
            PreparedStatement pstImage = connection.prepareStatement(sqlUpdateImage);
            pstImage.setString(1, tour.getImage());
            pstImage.setInt(2, tour.getTourID());

            int imageUpdated = pstImage.executeUpdate();

            // Nếu không có ảnh hiện tại, thêm mới
            if (imageUpdated == 0) {
                String sqlInsertImage = "INSERT INTO TourImages (TourID, ImageURL) VALUES (?, ?)";
                PreparedStatement pstInsertImage = connection.prepareStatement(sqlInsertImage);
                pstInsertImage.setInt(1, tour.getTourID());
                pstInsertImage.setString(2, tour.getImage());
                imageUpdated = pstInsertImage.executeUpdate();
            }

            // Commit nếu cả hai thao tác thành công
            if (tourUpdated > 0 && imageUpdated > 0) {
                connection.commit();
                return true;
            } else {
                connection.rollback();
                return false;
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                System.out.println("Error rolling back: " + ex.getMessage());
            }
            System.out.println("Error updating tour: " + e.getMessage());
            return false;
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("Error resetting auto-commit: " + e.getMessage());
            }
        }
    }

    @Override
    public boolean deleteTour(int tourID) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            // Tạo kết nối mới
            conn = new DBContext().getConnection();

            // Bắt đầu transaction
            conn.setAutoCommit(false);

            // Xóa ảnh trước (ràng buộc khóa ngoại)
            String sqlDeleteImages = "DELETE FROM TourImages WHERE TourID = ?";
            ps = conn.prepareStatement(sqlDeleteImages);
            ps.setInt(1, tourID);
            ps.executeUpdate();

            // Đóng PreparedStatement trước khi tái sử dụng
            if (ps != null) {
                ps.close();
            }

            // Xóa tour
            String sqlDeleteTour = "DELETE FROM Tours WHERE TourID = ?";
            ps = conn.prepareStatement(sqlDeleteTour);
            ps.setInt(1, tourID);
            int tourDeleted = ps.executeUpdate();

            // Commit nếu xóa tour thành công
            if (tourDeleted > 0) {
                conn.commit();
                return true;
            } else {
                conn.rollback();
                return false;
            }
        } catch (SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                System.out.println("Error rolling back: " + ex.getMessage());
            }
            System.out.println("Error deleting tour: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("Error resetting auto-commit: " + e.getMessage());
            }
        }
    }

    @Override
    public List<Tour> searchTours(String keyword) {
        List<Tour> results = new ArrayList<>();
        try {
            String sql = "SELECT t.*, ti.ImageURL FROM Tours t "
                    + "JOIN TourImages ti ON t.TourID = ti.TourID "
                    + "WHERE t.TourName LIKE ? OR t.Description LIKE ?";

            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, "%" + keyword + "%");
            pst.setString(2, "%" + keyword + "%");

            ResultSet rs = pst.executeQuery();
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

                results.add(t);
            }
        } catch (SQLException e) {
            System.out.println("Error searching tours: " + e.getMessage());
        }
        return results;
    }

    @Override
    public List<Tour> getToursByPage(int pageNumber, int pageSize) {
        List<Tour> tours = new ArrayList<>();
        int offset = (pageNumber - 1) * pageSize;

        try {
            String sql = "SELECT t.*, ti.ImageURL FROM Tours t "
                    + "JOIN TourImages ti ON t.TourID = ti.TourID "
                    + "ORDER BY t.TourID "
                    + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1, offset);
            pst.setInt(2, pageSize);

            ResultSet rs = pst.executeQuery();
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

                tours.add(t);
            }
        } catch (SQLException e) {
            System.out.println("Error getting tours by page: " + e.getMessage());
        }
        return tours;
    }

    @Override
    public int getTotalTours() {
        try {
            String sql = "SELECT COUNT(*) FROM Tours";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Error counting tours: " + e.getMessage());
        }
        return 0;
    }

    @Override
    public Tour getTourByID(int id) {
        return getTourDetail(id);
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
