/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author admin
 */
import java.util.Date;

public class Tour {
    private int tourID;
    private String tourName;
    private String description;
    private double price;
    private Date startDate;
    private Date endDate;
    private int availableSeats;
    private Date createdAt;
    private int hotelID;

    private String image;
    public Tour() {
    }


    public Tour(int tourID, String tourName, String description, double price, Date startDate, Date endDate, int availableSeats, Date createdAt, int hotelID, String image) {
        this.tourID = tourID;
        this.tourName = tourName;
        this.description = description;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.availableSeats = availableSeats;
        this.createdAt = createdAt;
        this.hotelID = hotelID;
        this.image = image;
    }

   
    

    // Getters and Setters
    public int getTourID() {
        return tourID;
    }

    public void setTourID(int tourID) {
        this.tourID = tourID;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public int getHotelID() {
        return hotelID;
    }

    public void setHotelID(int hotelID) {
        this.hotelID = hotelID;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

   

    
    @Override
    public String toString() {
        return "Tour{" +
                "tourID=" + tourID +
                ", tourName='" + tourName + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", availableSeats=" + availableSeats +
                ", createdAt=" + createdAt +
                ", hotelID=" + hotelID +
                ", Image=" + image +
                '}';
    }
}
