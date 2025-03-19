package Service;

import DAO.UserDAO;
import DAO.UserDAOlmpl; // Import UserDAOlmpl để khởi tạo đúng đối tượng
import Model.User;

public class UserServicelmpl implements UserService {

    UserDAO userDAO = new UserDAOlmpl(); // Khởi tạo đối tượng UserDAOlmpl

    public User Login(String username, String pass) {
        User user = new User();
        user = userDAO.Login(username, pass); // Sử dụng userDAO để gọi phương thức Login
        return user;
    }

    @Override
    public User getUserById(int id) {
        User user1 = new User();
        user1 = userDAO.getUserById(id);
        return user1;
    }

    @Override
    public boolean updateProfile(User user) {
       boolean isUpdate;
       isUpdate = userDAO.updateProfile(user);
       return  isUpdate;
    }
}
