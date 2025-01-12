package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Class that contains all user information of type employer.
 * 
 * @author Bjorn Sauter
 */
public class Employer extends User {
    private boolean verificationStatus;
    private ArrayList<UUID> internshipList;
    private ArrayList<Integer> ratings;
    private ArrayList<UUID> employees;

    /**
     * Instatiates employer object with given id. To be used when loading an object
     * from JSON.
     * 
     * @param id                 of the employer.
     * @param firstName          of the employer.
     * @param lastName           of the employer.
     * @param email              of the employer.
     * @param password           to use when logging in.
     * @param verificationStatus boolean that indicates verification status.
     * @param internshipList     list with uuids of internships.
     * @param ratings            list with ratings between 1 and 10.
     * @param employees          list with uuids of employees.
     */
    public Employer(UUID id, String firstName, String lastName, String email, String password,
            boolean verificationStatus, ArrayList<UUID> internshipList, ArrayList<Integer> ratings,
            ArrayList<UUID> employees) {
        super(id, firstName, lastName, email, password);
        this.verificationStatus = verificationStatus;
        this.internshipList = internshipList;
        this.ratings = ratings;
        this.employees = employees;
    }

    /**
     * Instatiates employer object from scratch. To be used when creating a new
     * employer.
     * 
     * 
     * @param id                 of the employer.
     * @param firstName          of the employer.
     * @param lastName           of the employer.
     * @param email              of the employer.
     * @param password           to use when logging in.
     * @param verificationStatus boolean that indicates verification status.
     * @param internshipList     list with uuids of internships.
     * @param ratings            list with ratings between 1 and 10.
     * @param employees          list with uuids of employees.
     */
    public Employer(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password);
        this.verificationStatus = false;
        this.internshipList = new ArrayList<>();
        this.ratings = new ArrayList<>();
        this.employees = new ArrayList<>();

    }

    /**
     * Adds a rating to the rating list.
     * 
     * @param rating is the rating number
     */
    public void addRating(int rating){
        ratings.add(rating);
    }

    /**
     * Function to add a rating to a student. 
     * @param rating to give the student.
     * @param studentId the id of the student.
     */
    public void addStudentRating(int rating, UUID studentId) {
        Student student = (Student) UserList.getInstance().getUserById(studentId);
        student.addRating(rating);
    }

    /**
     * Sets the verification status of an Employee to the provided boolean.
     * 
     * @param verificationStatus to set this boolean to.
     */
    public void setVerificationStatus(boolean verificationStatus) {
        this.verificationStatus = verificationStatus;
    }

    /**
     * Completely deltes an intership from the program.
     * @param internshipId the id of the internship to delete.
     */
    public void deleteInternship(UUID internshipId){
        internshipList.removeIf(internship -> internship.equals(internshipId));
        InternshipList.getInstance().removeInternshipById(internshipId);
    }

    /**
     * Add an entirely new Internship. 
     * @param title          the title as string.
     * @param employer       the employer name as a string.
     * @param description    the description of the internship offer.
     * @param requiredSkills the skills required for the internship
     * @param startDate      the date when the internship is supposed to start.
     * @param endDate        the date when the internship is supposed to end.
     * @param hoursPerDay    the hours the student is supposed to work.
     * @param expirationDate the date when the offer expires.
     * @param salaryType     the type of the salary.
     */
    public void createNewInternship(String title, String employer, String description, ArrayList<String> requiredSkills, LocalDate startDate, LocalDate endDate, int hoursPerDay, int endHour, LocalDate expirationDate, SalaryType salaryType){
        Internship internship = new Internship(title, employer, description, requiredSkills, startDate, endDate, hoursPerDay, endHour, expirationDate, salaryType);
        this.internshipList.add(internship.getId());
        InternshipList.getInstance().addInternship(internship);
    }

    /**
     * Adds a student to the employee list by the student's uuid
     * @param studentUUID the student's uuid
     */
    public void addEmployee(UUID studentUUID) {
        employees.add(studentUUID);
    }

    /**
	 * Returns rating list. 
	 * @return rating list.
	 */
    public ArrayList<Integer> getRatings() {
        return this.ratings;
    }

    /**
	 * Returns internship list. 
	 * @return internship list.
	 */
    public ArrayList<UUID> getInternshipList() {
        return this.internshipList;
    }

    /**
	 * Returns employee list. 
	 * @return employee list.
	 */
    public ArrayList<UUID> getEmployees() {
        return this.employees;
    }

    /**
	 * Returns verification status. 
	 * @return verification status.
	 */
    public boolean getVerificationStatus() {
        return this.verificationStatus;
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
        if (this.ratings.size() == 0) return 0.0;
        return sum/this.ratings.size();

    }
}
