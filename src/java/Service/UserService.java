/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.User;

/**
 *
 * @author admin
 */
public interface UserService {
    public User Login(String username, String pass);
    
    public User getUserById(int id);
    
    public boolean updateUserById(User user);
    
    public boolean updatePassword(int id , String pass);
}
