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
public class Booking {

    private int bookingId;
    private int userId;
    private int tourId;
    private Date bookingDate;
    private int statusId;

    private String tourName;
    private String description;
    private double price;
    private Date startDate;
    private Date endDate;
    private String statusName;
    private String image;

    public Booking() {
    }

    public Booking(int bookingId, int userId, int tourId, Date bookingDate, int statusId, String tourName, String description, double price, Date startDate, Date endDate,String statusName, String image) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.tourId = tourId;
        this.bookingDate = bookingDate;
        this.statusId = statusId;
        this.tourName = tourName;
        this.description = description;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.statusName = statusName;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    
    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTourId() {
        return tourId;
    }

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
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

    @Override
    public String toString() {
        return "Booking{" + "bookingId=" + bookingId + ", userId=" + userId + ", tourId=" + tourId + ", bookingDate=" + bookingDate + ", statusId=" + statusId + ", tourName=" + tourName + ", description=" + description + ", price=" + price + ", statusName=" + statusName + ", image=" + image + '}';
    }

    
}
