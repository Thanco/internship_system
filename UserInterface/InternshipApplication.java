package UserInterface;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;
import Model.*;

/**
 * Used by UI to acces other classes
 */
public class InternshipApplication {
    private User currentUser;

    /**
     * Constructor
     */
    public InternshipApplication() {

    }

    /**
     * Checks user list for matching email/password and saves that user to the instance variable
     * @param email Entered email
     * @param password Entered Password
     * @return True/False if login is succesful
     */
    public boolean login(String email, String password) {
        return false;
    }

    /** 
     * Saves saves all data to jsons
    */
    public void end() {

    }
    
    /**
     * Adds a new user to the arraylist of users
     * Checks for valid entries (No white space, valid email, etc..)
     * @param first Users first name
     * @param last Users last name
     * @param email Users email
     * @param password Users password
     * @param type Type of user: 0 = Student, 1 = Employer
     * @return Returns true/false if account was/wasnot created
     */
    public boolean createUser(String first, String last, String email, String password, int type) {
        return false;
    }

    /**
     * @return 0 for Student
     * @return 1 for Employer
     * @return 2 for Admin
     * @return else -1
     */
    public int userType() {
        return 0;
    }
    
    /**
     * @param user UUID of user
     * @return 0 for Student
     * @return 1 for Employer
     * @return 2 for Admin
     * @return else -1
     */
    public int userType(UUID user) {
        return -1;
    }

    /**
     * Creates resume for current user
     * @params add as needed an i will update the UI
     */
    public void createResume(ArrayList<String> skills) {

    }

    /**
     * gets a list of UUIDs that the current user can add a rating to
     * @return for student return an array list of past employer UUIDs
     * @return for employer return an array list of past employee UUIDs
     */
    public ArrayList<UUID> getRatables() {
        return null;
    }

    /**
     * Adds rating to a users array list of ratings
     * @param user The UUID of a user
     * @param rating the entered rating
     */
    public void addRating(UUID user, int rating) {

    }

    /**
     * gets a full list of internships
     * @return The full list of Internships
     */
    public ArrayList<Internship> getInternships() {
        return null;
    }

    /**
     * Searches the internship list using a specified keyword
     * @param entry A search criteria ented by the user
     * @return An array list that contains any internships with info that matches the entry (Company, Position, or Skill)
     */
    public ArrayList<Internship> getInternships(String keyword) {
        return null;
    }

    /**
     * Gets an all of the internships created by a specific user
     * @param user The users UUID
     * @return An array list of internships
     */
    public ArrayList<Internship> getInternships(UUID user) {
        return null;
    }

    /**
     * Gets the full user list
     * @return The user ArrayList
     */
    public ArrayList<User> getUsers() {
        return null;
    }

    /**
     * Gets all users of specific type
     * @param type 0 = Sudent, 1 = Employer
     * @return An array list of users
     */
    public ArrayList<User> getUsers(int type) {
        return null;
    }

    /**
     * Searches for users with profiles containing a given keyword
     * @param keyword word to search for
     * @return an array list of users
     */
    public ArrayList<User> getUsers(String keyword) {
        return null;
    }

    /**
     * Gets the current user
     * @return The current User
     */
    public User getUser() {
        return currentUser;
    }

    /**
     * Finds a user by UUID
     * @param user The UUID of the user to be returned
     * @return A User
     */
    public User getUser(UUID user) {
        return null;
    }

    /**
     * saves changes made to the currentUser to the user list
     */
    public void saveUser() {

    }

    /**
     * Saves updates to given user object in user list
     * @param user The modified User
     */
    public void saveUser(User user) {

    }

    /**
     * Changes current users email
     * checks if email is valid and if email is already used
     * @param email The new email
     * @return true if email is changed
     */
    public boolean updateEmail(String email) {
        return false;
    }

    /**
     * Changes current users password
     * check for valid password
     * @param password new password
     * @return true if password is changed
     */
    public boolean updatePassword(String password) {
        return false;
    }

    /**
     * Changes current users name
     * @param first New first name
     * @param last New last name
     * @return true if name is changed
     */
    public boolean updateName(String first, String last) {
        return false;
    }

    /**
     * returns the current users resume
     * @return current users resume
     */
    public Resume getResume() {
        return null;
    }

    /**
     * Returns a specific users Resume
     */
    public Resume getResume(UUID user) {
        return null;
    }

    /**
     * Adds the current users resume to an internship
     * @param Id The UUID of the internship being applied to
     */
    public boolean apply(UUID Id) {
        return false;
    }

    /**
     * Removes a User from the UserList
     * @param user The UUID of the user to be removed
     */
    public void removeUser(UUID user) {

    }

    /**
     * Accepts Applicant
     * Adds employer to student ratables and employer to students ratables
     * @param resume
     */
    public void acceptApplication(Resume resume) {

    }

    /**
     * Decline Aoolicant
     * @param resume
     */
    public void declineApplication(Resume resume) {

    }

    /**
     * delets a users resume
     * @param resume
     */
    public void deleteResume(User user) {
    }

    /**
     * Removes an internship from the internship list
     * @param id The internships UUID
     */
    public void removeInternship(UUID id) {

    }

    /**
     * Creates an internship using given values
     * @param title
     * @param employer
     * @param description
     * @param requiredSkills
     * @param startDate
     * @param endDate
     * @param hoursPerDay
     * @param endHour
     * @param expirationDate
     * @param salaryType
     */
    public void CreateInternship(String title, String employer, String description, String requiredSkills,
    String startDate, String endDate, int hoursPerDay, int endHour, String expirationDate,
    String salaryType) {
        
    }

    /**
     * Updates an internship in the internship list
     * @param internship
     */
    public void updateInternship(Internship internship) {

    }

    /**
     * Searches an array of Resumes for a key word
     * @param resumes List to be searched
     * @param keyword The word to search for
     * @return An array list of containing they key word
     */
    public ArrayList<Resume> searchResumes(ArrayList<Resume> resumes, String keyword) {
        return null;
    }

    /**
     * Promotes given user to admin
     * @param user
     */
    public void promoteUser(UUID user) {

    }
}
