package Model;

import java.util.Date;

public class Rating {

    private int ratingID;
    private int userID;
    private int tourID;
    private int rating;
    private Date createdAt;
    private String fullName;
    
    public Rating() {
    }

    public Rating(int ratingID, int userID, int tourID, int rating, Date createdAt, String fullName) {
        this.ratingID = ratingID;
        this.userID = userID;
        this.tourID = tourID;
        this.rating = rating;
        this.createdAt = createdAt;
        this.fullName = fullName;
    }

    public Rating(int userID, int tourID, int rating, Date createdAt) {

        this.userID = userID;
        this.tourID = tourID;
        this.rating = rating;
        this.createdAt = createdAt;
    }

    public int getRatingID() {
        return ratingID;
    }

    public void setRatingID(int ratingID) {
        this.ratingID = ratingID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getTourID() {
        return tourID;
    }

    public void setTourID(int tourID) {
        this.tourID = tourID;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "Rating{" + "ratingID=" + ratingID + ", userID=" + userID + ", tourID=" + tourID + ", rating=" + rating + ", createdAt=" + createdAt + '}';
    }

}
