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
public class Hotel {
    private int HotelID;
    private String HotelName;
    private String Location;
    private String Description;
    private int RoomAvailable;
    private int CreatedBy;
    private Date CreatedAt;
    
    public Hotel(){
    }

    public int getHotelID() {
        return HotelID;
    }

    public String getHotelName() {
        return HotelName;
    }

    public String getLocation() {
        return Location;
    }

    public String getDescription() {
        return Description;
    }

    public int getRoomAvailable() {
        return RoomAvailable;
    }

    public int getCreatedBy() {
        return CreatedBy;
    }

    public Date getCreatedAt() {
        return CreatedAt;
    }

    public void setHotelID(int HotelID) {
        this.HotelID = HotelID;
    }

    public void setHotelName(String HotelName) {
        this.HotelName = HotelName;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public void setRoomAvailable(int RoomAvailable) {
        this.RoomAvailable = RoomAvailable;
    }

    public void setCreatedBy(int CreatedBy) {
        this.CreatedBy = CreatedBy;
    }

    public void setCreatedAt(Date CreatedAt) {
        this.CreatedAt = CreatedAt;
    }

    
}
