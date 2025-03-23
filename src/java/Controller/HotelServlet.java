/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.HotelDAOImpl;
import Model.Hotel;
import Service.UserService;
import Service.UserServicelmpl;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class HotelServlet extends HttpServlet {

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
//        HotelDAOImpl h = new HotelDAOImpl();
//        String searchResult = request.getParameter("search");
//        
//        
//        List<Hotel> hotelList = h.getAllHotel(searchResult);
//        
//        ArrayList<Object[]> dataList = new ArrayList<>();
//
//         for (Hotel hotel : hotelList) {
//            String hotelName = hotel.getHotelName();
//            String hotelLocation = hotel.getLocation();
//            String hotelDescription = hotel.getDescription();
//            int hotelRoomAvailable = hotel.getRoomAvailable();
//            int hotelID = hotel.getHotelID();
//
//            dataList.add(new Object[]{hotelName, hotelLocation, hotelDescription, hotelRoomAvailable, hotelID});
//        }
//         
//         
//        request.setAttribute("hotelList", dataList);  
//        request.getRequestDispatcher("Hotel.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HotelDAOImpl h = new HotelDAOImpl();
         String action = request.getParameter("action");

         if (action != null) { 
        int id = 0;
        if(request.getParameter("hotelID") != null && !request.getParameter("hotelID").isEmpty()){
            id = Integer.parseInt(request.getParameter("hotelID"));
        }

        String name = request.getParameter("hotelName");
        String location = request.getParameter("hotelLocation");
        String description = request.getParameter("hotelDescription");

        int roomAvailable = 0;
        if(request.getParameter("roomAvailable") != null && !request.getParameter("roomAvailable").isEmpty()){
            roomAvailable = Integer.parseInt(request.getParameter("roomAvailable"));
        }

        // Thực hiện hành động tương ứng với action
        switch (action) {
            case "create":
                if (name != null && location != null && description != null) {
                    h.createHotel(name, location, description, roomAvailable);
                    request.setAttribute("message", "Hotel created successfully!");
                } else {
                    request.setAttribute("message", "Invalid input for hotel creation!");
                }
                break;
            case "update":
                if (id > 0 && name != null && location != null && description != null) {
                    h.updateHotel(name, location, description, roomAvailable, id);
                    request.setAttribute("message", "Hotel updated successfully!");
                } else {
                    request.setAttribute("message", "Invalid input for hotel update!");
                }
                break;
            case "delete":
                if (id > 0) {
                    h.deleteHotel(id);
                    request.setAttribute("message", "Hotel deleted successfully!");
                } else {
                    request.setAttribute("message", "Invalid hotel ID for deletion!");
                }
                break;
            default:
                request.setAttribute("message", "Invalid action!");
                break;
        }
    }
        
        
        
        String searchResult = request.getParameter("search");
        
        
        List<Hotel> hotelList = h.getAllHotel(searchResult);
        
        ArrayList<Object[]> dataList = new ArrayList<>();

         for (Hotel hotel : hotelList) {
            String hotelName = hotel.getHotelName();
            String hotelLocation = hotel.getLocation();
            String hotelDescription = hotel.getDescription();
            int hotelRoomAvailable = hotel.getRoomAvailable();
            int hotelID = hotel.getHotelID();

            dataList.add(new Object[]{hotelName, hotelLocation, hotelDescription, hotelRoomAvailable, hotelID});
        }
         
         
        request.setAttribute("hotelList", dataList);  
        
        request.getRequestDispatcher("Hotel.jsp").forward(request, response);
    }
    
    
    
    
    
    
    
    public static void main(String[] args) {
        HotelDAOImpl h = new HotelDAOImpl();
        if(h.updateHotel("2hotel", "lao caiii", "hoteltest", 45, 4)){
            System.out.println("success");
        };
    }
    
}
