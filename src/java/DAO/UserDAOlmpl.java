/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author admin
 */
public class UserDAOlmpl extends DBContext implements UserDAO {

    @Override
    public User Login(String Email, String PasswordHash) {
        String sql = "SELECT * FROM Users WHERE FullName = ? AND PasswordHash = ?";
        String xFullName, xEmail, xPass, xPhone;
        int xId, xRole;
        Date xCreateAt;
        User x = null;
        try {
            PreparedStatement st = connection.prepareStatement (sql);
            st.setString(1, Email);
            st.setString(2, PasswordHash);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                xFullName = rs.getString("FullName");
                xPass = rs.getString("PasswordHash");
                xEmail = rs.getString("Email");
                xPhone = rs.getString("Phone");
                xId = rs.getInt("UserID");
                xRole = rs.getInt("Role");
                xCreateAt = rs.getDate("CreatedAt");
                x = new User(xId, xFullName, xEmail, xPass, xPhone, xCreateAt, xRole);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return x;
    }



    @Override
    public User getUserById(int id) {
        String sql = "SELECT * FROM Users WHERE UserID = ?";
        String xFullName, xEmail, xPass, xPhone;
        int xId, xRole;
        Date xCreateAt;
        User x = null;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                xFullName = rs.getString("FullName");
                xPass = rs.getString("PasswordHash");
                xEmail = rs.getString("Email");
                xPhone = rs.getString("Phone");
                xId = rs.getInt("UserID");
                xRole = rs.getInt("Role");
                xCreateAt = rs.getDate("CreatedAt");
                x = new User(xId, xFullName, xEmail, xPass, xPhone, xCreateAt, xRole);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return x;
    }

    @Override
    public boolean updateProfile(User user) {
        String sql = "UPDATE Users SET FullName = ?, Email = ?, Phone = ?, WHERE UserID = ?";
        boolean isUpdated = false;

        try {
            PreparedStatement st = connection.prepareStatement(sql);

            // Gán giá trị cho các tham số trong câu lệnh UPDATE
            st.setString(1, user.getFullName());
            st.setString(2, user.getEmail());
            st.setString(3, user.getPhone());
            st.setInt(4, user.getUserID());

            // Kiểm tra số bản ghi bị ảnh hưởng
            int rowsUpdated = st.executeUpdate();
            if (rowsUpdated > 0) {
                isUpdated = true; // Cập nhật thành công
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isUpdated;
    }

    @Override
    public boolean updatePassword(int id, String pass) {
         String sql = "UPDATE Users SET PasswordHash = ? WHERE UserID = ?";
         boolean isUpdated = false;
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, pass);
            st.setInt(2, id);
            int rowsUpdated = st.executeUpdate(); 
            if(rowsUpdated > 0){
                isUpdated = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Use printStackTrace() instead of System.out.println()
        }
        return isUpdated;
    }
    
    
        public static void main(String[] args) {
        User a = new User();
        UserDAOlmpl u = new UserDAOlmpl();
//            a = u.Login("user@gamil.com", "hashedpassword123");
//        a = u.getUserById(1);
         boolean b = u.updatePassword(2, "1234");
        System.out.println(b);

    }
        
}
