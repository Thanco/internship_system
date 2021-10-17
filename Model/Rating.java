package Model;
import java.util.UUID;

public class Rating {
    private int rating;
    private UUID authorID;
    private String feedback;

    public Rating(int rating, UUID author, String feedback) {
        return;
    }

    public String toString() {
        return "";
    }

    public int getRating() {
        return this.rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public UUID getAuthorID() {
        return this.authorID;
    }

    public void setAuthorID(UUID authorID) {
        this.authorID = authorID;
    }

    public String getFeedback() {
        return this.feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

}
