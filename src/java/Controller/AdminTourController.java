/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.TourDAO;
import DAO.TourDAOlmpl;
import Model.Tour;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "AdminTourController", urlPatterns = {"/admin/tour", "/admin/tour/*"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class AdminTourController extends HttpServlet {

    private TourDAO tourDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        tourDAO = new TourDAOlmpl();
    }

    @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String pathInfo = request.getPathInfo();
    // Đường dẫn chính: /admin/tour
    if (pathInfo == null || pathInfo.equals("/")) {
        try {
            // Hiển thị tất cả tours
            List<Tour> tours = tourDAO.getAllTour();
            // Kiểm tra xem danh sách có dữ liệu không
            System.out.println("Số lượng tour: " + tours.size());
            for (Tour t : tours) {
                System.out.println("Tour ID: " + t.getTourID() + ", Tên: " + t.getTourName());
            }
            request.setAttribute("tours", tours);
            // Forward to JSP
            request.getRequestDispatcher("/adminHome.jsp").forward(request, response);
            return;
        } catch (Exception e) {
            System.out.println("Lỗi khi lấy danh sách tour: " + e.getMessage());
            e.printStackTrace();
            // Hiển thị danh sách trống và thông báo lỗi
            request.setAttribute("tours", new ArrayList<>());
            request.setAttribute("error", "Không thể tải danh sách tour: " + e.getMessage());
            request.getRequestDispatcher("/adminHome.jsp").forward(request, response);
            return;
        }
    }
    
    // Xử lý các đường dẫn khác
    if (pathInfo.equals("/getDetails")) {
        getTourDetails(request, response);
    } else {
        response.sendRedirect(request.getContextPath() + "/admin/tour");
    }
}

@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
    String pathInfo = request.getPathInfo();
    if (pathInfo == null) {
        pathInfo = "/";
    }
    
    if (pathInfo.equals("/add")) {
        addTour(request, response);
    } else if (pathInfo.equals("/edit")) {
        updateTour(request, response);
    } else if (pathInfo.equals("/delete")) {
        deleteTour(request, response);
    } else {
        response.sendRedirect(request.getContextPath() + "/admin/tour");
    }
}

        
        // Lấy chi tiết tour (cho modal edit)
    private void getTourDetails(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    
    try (PrintWriter out = response.getWriter()) {
        int tourId = Integer.parseInt(request.getParameter("id"));
        Tour tour = tourDAO.getTourDetail(tourId);
        
        if (tour != null) {
            // Log thông tin để debug
            System.out.println("Đang gửi thông tin tour: " + tour.getTourID() + ", " + tour.getTourName());
            
            // Tạo JSON thủ công
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            
            StringBuilder json = new StringBuilder();
            json.append("{");
            json.append("\"tourID\":" + tour.getTourID() + ",");
            json.append("\"tourName\":\"" + escapeJsonString(tour.getTourName()) + "\",");
            json.append("\"description\":\"" + escapeJsonString(tour.getDescription()) + "\",");
            json.append("\"price\":" + tour.getPrice() + ",");
            
            // Format dates properly
            if (tour.getStartDate() != null) {
                json.append("\"startDate\":\"" + sdf.format(tour.getStartDate()) + "\",");
            } else {
                json.append("\"startDate\":null,");
            }
            
            if (tour.getEndDate() != null) {
                json.append("\"endDate\":\"" + sdf.format(tour.getEndDate()) + "\",");
            } else {
                json.append("\"endDate\":null,");
            }
            
            json.append("\"availableSeats\":" + tour.getAvailableSeats() + ",");
            
            if (tour.getImage() != null) {
                json.append("\"image\":\"" + escapeJsonString(tour.getImage()) + "\"");
            } else {
                json.append("\"image\":null");
            }
            
            json.append("}");
            
            out.print(json.toString());
        } else {
            out.print("{\"success\":false,\"message\":\"Tour không tồn tại\"}");
        }
    } catch (NumberFormatException e) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        try (PrintWriter out = response.getWriter()) {
            out.print("{\"success\":false,\"message\":\"ID tour không hợp lệ\"}");
        }
    } catch (Exception e) {
        e.printStackTrace();
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        try (PrintWriter out = response.getWriter()) {
            out.print("{\"success\":false,\"message\":\"Lỗi: " + escapeJsonString(e.getMessage()) + "\"}");
        }
    }
}

    // Phương thức hỗ trợ escape ký tự đặc biệt trong JSON string
    private String escapeJsonString(String input) {
        if (input == null) {
            return "";
        }
        return input.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\b", "\\b")
                .replace("\f", "\\f")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }

    // Thêm tour mới
    private void addTour(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Lấy thông tin từ form
            String tourName = request.getParameter("tourName");
            String description = request.getParameter("description");
            double price = Double.parseDouble(request.getParameter("price"));

            // Xử lý ngày
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = sdf.parse(request.getParameter("startDate"));
            Date endDate = sdf.parse(request.getParameter("endDate"));

            int availableSeats = Integer.parseInt(request.getParameter("availableSeats"));

            // Xử lý file ảnh
            Part filePart = request.getPart("image");
            String fileName = getFileName(filePart);
            String uploadPath = getServletContext().getRealPath("/uploads/tours/");

            // Đảm bảo thư mục upload tồn tại
            java.io.File uploadDir = new java.io.File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // Lưu file
            String uniqueFileName = System.currentTimeMillis() + "_" + fileName;
            String filePath = uploadPath + uniqueFileName;
            filePart.write(filePath);

            // Đường dẫn lưu vào DB
            String imageURL = "uploads/tours/" + uniqueFileName;

            // Tạo đối tượng Tour
            Tour tour = new Tour();
            tour.setTourName(tourName);
            tour.setDescription(description);
            tour.setPrice(price);
            tour.setStartDate(startDate);
            tour.setEndDate(endDate);
            tour.setAvailableSeats(availableSeats);
            tour.setImage(imageURL);

            // Lưu tour vào DB
            boolean success = tourDAO.addTour(tour);

            if (success) {
                request.getSession().setAttribute("message", "Tour đã được thêm thành công!");
            } else {
                request.getSession().setAttribute("error", "Thêm tour thất bại!");
            }

            // Chuyển hướng về trang quản lý tour
            response.sendRedirect(request.getContextPath() + "/admin/tour");

        } catch (ParseException | NumberFormatException e) {
            request.getSession().setAttribute("error", "Dữ liệu không hợp lệ: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/admin/tour");
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("error", "Lỗi: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/admin/tour");
        }
    }

    // Cập nhật tour
    private void updateTour(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    try {
        // Lấy thông tin từ form
        String tourIDStr = request.getParameter("tourID");
        if (tourIDStr == null || tourIDStr.trim().isEmpty()) {
            throw new NumberFormatException("ID tour không được để trống");
        }
        int tourID = Integer.parseInt(tourIDStr);
        
        String tourName = request.getParameter("tourName");
        if (tourName == null || tourName.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên tour không được để trống");
        }
        
        String description = request.getParameter("description");
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Mô tả không được để trống");
        }
        
        String priceStr = request.getParameter("price");
        if (priceStr == null || priceStr.trim().isEmpty()) {
            throw new NumberFormatException("Giá không được để trống");
        }
        double price = Double.parseDouble(priceStr);
        
        String startDateStr = request.getParameter("startDate");
        if (startDateStr == null || startDateStr.trim().isEmpty()) {
            throw new ParseException("Ngày bắt đầu không được để trống", 0);
        }
        
        String endDateStr = request.getParameter("endDate");
        if (endDateStr == null || endDateStr.trim().isEmpty()) {
            throw new ParseException("Ngày kết thúc không được để trống", 0);
        }
        
        String availableSeatsStr = request.getParameter("availableSeats");
        if (availableSeatsStr == null || availableSeatsStr.trim().isEmpty()) {
            throw new NumberFormatException("Số chỗ trống không được để trống");
        }
        
        // Chuyển đổi các giá trị
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = sdf.parse(startDateStr);
        Date endDate = sdf.parse(endDateStr);
        int availableSeats = Integer.parseInt(availableSeatsStr);
        
        // Lấy tour hiện tại để biết ảnh cũ
        Tour existingTour = tourDAO.getTourDetail(tourID);
        String imageURL = existingTour.getImage();
        
        // Kiểm tra xem có file ảnh mới không
        Part filePart = request.getPart("image");
        if (filePart != null && filePart.getSize() > 0) {
            String fileName = getFileName(filePart);
            if (fileName != null && !fileName.isEmpty()) {
                String uploadPath = getServletContext().getRealPath("/uploads/tours/");
                
                // Đảm bảo thư mục upload tồn tại
                java.io.File uploadDir = new java.io.File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }
                
                // Lưu file mới
                String uniqueFileName = System.currentTimeMillis() + "_" + fileName;
                String filePath = uploadPath + uniqueFileName;
                filePart.write(filePath);
                
                // Cập nhật đường dẫn ảnh
                imageURL = "uploads/tours/" + uniqueFileName;
            }
        }
        
        // Cập nhật đối tượng Tour
        Tour tour = new Tour();
        tour.setTourID(tourID);
        tour.setTourName(tourName);
        tour.setDescription(description);
        tour.setPrice(price);
        tour.setStartDate(startDate);
        tour.setEndDate(endDate);
        tour.setAvailableSeats(availableSeats);
        tour.setImage(imageURL);
        tour.setCreatedAt(existingTour.getCreatedAt()); // Giữ nguyên ngày tạo
        
        // Cập nhật vào CSDL - lưu ý gọi editTour từ TourDAO
        boolean success = tourDAO.editTour(tour);
        
        if (success) {
            request.getSession().setAttribute("message", "Tour đã được cập nhật thành công!");
        } else {
            request.getSession().setAttribute("error", "Cập nhật tour thất bại!");
        }
        
        // Chuyển hướng về trang quản lý tour
        response.sendRedirect(request.getContextPath() + "/admin/tour");
        
    } catch (NumberFormatException e) {
        request.getSession().setAttribute("error", "Dữ liệu số không hợp lệ: " + e.getMessage());
        response.sendRedirect(request.getContextPath() + "/admin/tour");
    } catch (ParseException e) {
        request.getSession().setAttribute("error", "Dữ liệu ngày tháng không hợp lệ: " + e.getMessage());
        response.sendRedirect(request.getContextPath() + "/admin/tour");
    } catch (IllegalArgumentException e) {
        request.getSession().setAttribute("error", "Dữ liệu không hợp lệ: " + e.getMessage());
        response.sendRedirect(request.getContextPath() + "/admin/tour");
    } catch (Exception e) {
        e.printStackTrace();
        request.getSession().setAttribute("error", "Lỗi: " + e.getMessage());
        response.sendRedirect(request.getContextPath() + "/admin/tour");
    }
}

    // Xóa tour
    private void deleteTour(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int tourID = Integer.parseInt(request.getParameter("tourID"));
            boolean success = tourDAO.deleteTour(tourID);

            if (success) {
                request.getSession().setAttribute("message", "Tour đã được xóa thành công!");
            } else {
                request.getSession().setAttribute("error", "Xóa tour thất bại!");
            }

            // Chuyển hướng về trang quản lý tour
            response.sendRedirect(request.getContextPath() + "/admin/tour");

        } catch (NumberFormatException e) {
            request.getSession().setAttribute("error", "ID tour không hợp lệ");
            response.sendRedirect(request.getContextPath() + "/admin/tour");
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("error", "Lỗi: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/admin/tour");
        }
    }

    // Phương thức hỗ trợ để lấy tên file từ Part
    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length() - 1);
            }
        }
        return "unknown_" + System.currentTimeMillis() + ".jpg";
    }
}
