/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.util.Date;

/**
 *
 * @author admin
 */
public class User {
    
    private int UserID;
    private String FullName;
    private String Email;
    private String pass;
    private String Phone;
    private Date creatAt;
    private int Role;

    public User() {
    }

    public User(int UserID, String FullName, String Email, String pass, String Phone, Date creatAt, int Role) {
        this.UserID = UserID;
        this.FullName = FullName;
        this.Email = Email;
        this.pass = pass;
        this.Phone = Phone;
        this.creatAt = creatAt;
        this.Role = Role;
    }

    public int getRole() {
        return Role;
    }

    public void setRole(int Role) {
        this.Role = Role;
    }

   

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public Date getCreatAt() {
        return creatAt;
    }

    public void setCreatAt(Date creatAt) {
        this.creatAt = creatAt;
    }

    @Override
    public String toString() {
        return "User{" + "UserID=" + UserID + ", FullName=" + FullName + ", Email=" + Email + ", pass=" + pass + ", Phone=" + Phone + ", creatAt=" + creatAt + '}';
    }
    
    
    
    
    
}
