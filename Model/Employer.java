
import java.util.ArrayList;
import java.util.UUID;

public class Employer extends User {
    private boolean verificationStatus;
    private ArrayList<Internship> internshipList;
    private ArrayList<Integer> ratings;
    private ArrayList<Student> employees;

    public Employer(UUID id, String firstName, String lastName, String email, String password) {
        super(id, firstName, lastName, email, password);
    }

    public void addInternship() {
        return;
    }

    public boolean editInternship() {
        return true;
    }

    /**
     * Adds a rating to the rating list.
     * @param rating is the rating number 
     */
    public void addRating(int rating){
        ratings.add(rating);
    }

    /**
     * Sets the verification status of an Employee to the provided boolean.
     * @param verificationStatus to set this boolean to.
     */
    public void setVerificationStatus(boolean verificationStatus) {
        this.verificationStatus = verificationStatus;
    }

}
