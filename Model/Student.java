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
    private ArrayList<UUID> formerEmployers;

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
            ArrayList<Integer> ratings, ArrayList<UUID> formerEmployers) {
        super(id, firstName, lastName, email, password);
        this.ratings = ratings;
        this.resume = resume;
        this.formerEmployers = formerEmployers;
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
        this.formerEmployers = new ArrayList<>();
    }

    /**
     * Adds a rating to the rating list.
     * @param rating is the rating number 
     */
    public void addRating(int rating){
        ratings.add(rating);
    }

    /**
     * Creates a resume and adds it to the student
     * @param school of the student
     * @param schoolClass of the student
     * @param major of the student
     * @param company of the student
     * @param length 
     * @param start
     * @param end
     * @param studentSkills
     * @param extraCirricular
     */
    public void createResume(String firstName, String lastName, Education education){
        this.resume = new Resume(this.getId(), this.getFirstName(), this.getLastName(), education);
    }

    public Resume getResume() {
        return this.resume;
    }

    public void setResume(Resume resume) {
        this.resume = resume;
    }

    public ArrayList<Integer> getRatings() {
        return this.ratings;
    }

    public ArrayList<UUID> getFormerEmployers(){
        return this.formerEmployers;
    }

    /**
     * Sets the ratings of the user.
     * @param ratings to set
     */
    public void setRatings(ArrayList<Integer> ratings){
        this.ratings = ratings;
    }

    /**
     * Calculates the average of all ratings
     * @return the average rating
     */
    public double getAverageRating(){
        int sum = 0;
        for(int num : this.ratings){
            sum += num;
        }
        return sum/this.ratings.size();

    }
}