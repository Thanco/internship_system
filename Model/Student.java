package Model;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Class that represents a Student user.
 * 
 * @author Bjorn Sauter
 */
public class Student extends User {
    private Resume resume;
    private ArrayList<Integer> ratings;

    /**
     * Instantiates Student object with given UUID, resume and ratings. Used when a
     * User from the database is loaded into the program.
     * 
     * @param id        is the uuid of the object.
     * @param firstName the first name of the user.
     * @param lastName  the last name of the user.
     * @param email     the email of the user.
     * @param password  the password of the user.
     * @param resume the resume of the student.
     * @param ratings the rating array of the student.
     */
    public Student(UUID id, String firstName, String lastName, String email, String password, Resume resume,
            ArrayList<Integer> ratings) {
        super(id, firstName, lastName, email, password);
        this.ratings = ratings;
        this.resume = resume;
    }

    /**
     * Instantiates Student object with empty ratings list and no resume.
     * Used when new Student is created.
     * 
     * @param firstName the first name of the user.
     * @param lastName  the last name of the user.
     * @param email     the email of the user.
     * @param password  the password of the user.
     */
    public Student(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password);
        this.ratings = new ArrayList<>();
    }

    /**
     * Adds a rating to the rating list.
     * @param rating is the rating number 
     */
    public void addRating(int rating){
        ratings.add(rating);
    }

    public Resume getResume() {
        return resume;
    }

    public void setResume(Resume resume) {
        this.resume = resume;
    }
}