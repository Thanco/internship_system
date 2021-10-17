package Model;
import java.util.ArrayList;
import java.util.UUID;

public class Employer extends User {
    private boolean verificationStatus;
    private ArrayList<Internship> internshipList;
    private ArrayList<Rating> ratings;
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

    public void addRating(UUID userId, int rating, String feedback) {
        return;
    }

    @Override
    protected boolean validateEmail(String email) {
        return false;
    }
    public boolean isVerificationStatus() {
        return this.verificationStatus;
    }

    public void setVerificationStatus(boolean verificationStatus) {
        this.verificationStatus = verificationStatus;
    }

    public ArrayList<Internship> getInternshipList() {
        return this.internshipList;
    }

    public void setInternshipList(ArrayList<Internship> internshipList) {
        this.internshipList = internshipList;
    }

    public ArrayList<Rating> getRatings() {
        return this.ratings;
    }

    public void setRatings(ArrayList<Rating> ratings) {
        this.ratings = ratings;
    }

    public ArrayList<Student> getEmployees() {
        return this.employees;
    }

    public void setEmployees(ArrayList<Student> employees) {
        this.employees = employees;
    }

}
