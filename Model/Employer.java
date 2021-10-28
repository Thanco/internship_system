package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import Model.SalaryType;

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

    public void createInternship() {
        return;
    }

    public boolean editInternship() {
        return true;
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
     * Get current ratings for employer.
     * @return the ratings.
     */
    public ArrayList<Integer> getRatings() {
        return this.ratings;
    }

    /**
     * Get all internship offers of this employer.
     * @return the internships.
     */
    public ArrayList<UUID> getInternshipList() {
        return this.internshipList;
    }

    /**
     * Returns all uuids of employees.
     * @return uudis in array list.
     */
    public ArrayList<UUID> getEmployees(){
        return this.employees;
    }
}
