package Model;

import java.util.Date;

public class Feedback {
    private int feedbackID;
    private int userID;
    private int tourID;
    private String comment;
    private Date createdAt;

    public Feedback() {
    }

    public Feedback(int feedbackID, int userID, int tourID, String comment, Date createdAt) {
        this.feedbackID = feedbackID;
        this.userID = userID;
        this.tourID = tourID;
        this.comment = comment;
        this.createdAt = createdAt;
    }
    public Feedback( int userID, int tourID, String comment, Date createdAt) {
        
        this.userID = userID;
        this.tourID = tourID;
        this.comment = comment;
        this.createdAt = createdAt;
    }
    public int getFeedbackID() {
        return feedbackID;
    }

    public void setFeedbackID(int feedbackID) {
        this.feedbackID = feedbackID;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
