package Controller;

import Model.User;
import Service.UserService;
import Service.UserServicelmpl;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ProfileController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        } else {
            int userId = user.getUserID();
            UserService useS = new UserServicelmpl();
            User u = useS.getUserById(userId);
            request.setAttribute("user", u);
            request.getRequestDispatcher("Profile.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("Login.jsp");
        } else {
            
            String fullName = request.getParameter("fullName");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");

          
            user.setFullName(fullName);
            user.setEmail(email);
            user.setPhone(phone);

            UserService userService = new UserServicelmpl();
            boolean isUpdated = userService.updateProfile(user);

            if (isUpdated) {
                session.setAttribute("user", user);

                request.setAttribute("mess", "Cập nhật thành công.");
                request.getRequestDispatcher("profile").forward(request, response);
            } else {
                request.setAttribute("mess", "Cập nhật không thành công. Vui lòng thử lại.");
                request.getRequestDispatcher("Profile.jsp").forward(request, response);
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet for managing user profile.";
    }
}
