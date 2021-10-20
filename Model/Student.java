package User
import java.util.ArrayList;
import java.util.UUID;

public class Student extends User {
    private Resume resume;
    private ArrayList<Rating> ratings;

    public Student(UUID id, String firstName, String lastName, String email, String password) {
        super(id, firstName, lastName, email, password);
    }

    public void editResume(){
        return;
    }

    public void addRating(UUID userId, int rating, String feedback) {
        return;
    }

    @Override
    protected boolean validateEmail(String email) {
        return false;
    }

    public Resume getResume() {
        return this.resume;
    }

    public void setResume(Resume resume) {
        this.resume = resume;
    }

    public ArrayList<Rating> getRatings() {
        return this.ratings;
    }

    public void setRatings(ArrayList<Rating> ratings) {
        this.ratings = ratings;
    }
    
}
