package Controller;

import Model.User;
import Service.UserServicelmpl;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        } else {
            int userId = user.getUserID();
            UserServicelmpl useS = new UserServicelmpl();
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

        // Kiểm tra xem người dùng có đăng nhập chưa
        if (user == null) {
            response.sendRedirect("Login.jsp");  // Nếu chưa đăng nhập, chuyển đến trang đăng nhập
        } else {
            // Lấy dữ liệu người dùng từ form gửi lên
            String fullName = request.getParameter("fullName");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");

          

            // Tạo đối tượng User với thông tin mới
            user.setFullName(fullName);
            user.setEmail(email);
            user.setPhone(phone);

            // Gọi phương thức updateProfile để cập nhật thông tin người dùng
            UserServicelmpl userService = new UserServicelmpl();
            boolean isUpdated = userService.updateProfile(user);

            if (isUpdated) {
                // Nếu cập nhật thành công, lưu lại đối tượng người dùng mới vào session
                session.setAttribute("user", user);

                request.setAttribute("errorMessage", "Cập nhật thành công.");
                request.getRequestDispatcher("profile").forward(request, response);
            } else {
                // Nếu có lỗi trong quá trình cập nhật, bạn có thể hiển thị thông báo lỗi
                request.setAttribute("errorMessage", "Cập nhật không thành công. Vui lòng thử lại.");
                request.getRequestDispatcher("Profile.jsp").forward(request, response);
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet for managing user profile.";
    }
}
