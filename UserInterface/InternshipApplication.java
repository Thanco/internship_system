package UserInterface;
import java.util.ArrayList;
import java.util.UUID;
import Model.Employer;
import Model.Internship;
import Model.InternshipList;
import Model.SchoolYear;
import Model.Student;
import Model.User;
import Model.UserList;

/**
 * Used by UI to acces other classes
 */
public class InternshipApplication {

    private InternshipList internshipList;
    private UserList userList;
    private User currentUser;

    public InternshipApplication(){
        this.internshipList = InternshipList.getInstance();
        this.userList = UserList.getInstance();
    }
    /**
     * Checks user list for matching email/password
     * @param email Entered email
     * @param password Entered Password
     * @return A user if credentials match
     * @return null if incorrect
     */
    public User login(String email, String password) {
        this.currentUser = userList.loginUser(email, password);
        return this.currentUser;

    }

    /**
     * Adds a new user to the arraylist of users
     * @param name Name of company/student
     * @param email Users email
     * @param password Users password
     * @param type Type of user: 0 = Student, 1 = Employer, 2 = Admin
     */
    public void createUser(String first, String last, String email, String password, int type) {
        this.currentUser = userList.createUser(first, last, email, password, type);
    }

    /**
     * TO FIX
     */
    public void createResume(String firstName, String lastName, String school, SchoolYear schoolClass, String major, String company, 
    int length, String start, String end, ArrayList<String> studentSkills, String extraCirricular) {
        if(this.currentUser instanceof Student){
            Student student = (Student) this.currentUser;
            student.createResume(firstName, lastName, school, schoolClass, major, company, length, start, end, studentSkills, extraCirricular);
        }

    }

    /**
     * Adds rating to a user
     * @param user The UUID of a student/employer
     * @param rating the entered rating
     */
    public void addRating(UUID userId, int rating) {
        User user = userList.getUserById(userId);
        if(user instanceof Employer){
            Employer employer = (Employer) user;
            employer.addRating(rating);
        }else if(user instanceof Student){
            Student student = (Student) user;
            student.addRating(rating);
        }
    }

    /**
     * gets a full list of internships
     * @return An array list of Internships
     */
    public ArrayList<Internship> getInternshipList() {
        return this.internshipList.getInternships();
    }

    /**
     * Overwrites Student with matching UUID to save any changes
     * @param user The Student who is logging out
     */
    public void saveUser(User user) {

    }

    /**
     * Overwrites Employer with matching UUID to save any changes
     * @param user The Employer who is logging out
     */
    public void saveUser(Employer user) {

    }

    /**
     * Gets a specific user from the user list
     * @param user The UUID of the user to be returned
     * @return A User
     */
    public User getUser(UUID user) {
        return null;
    }

    /**
     * Gets the full user list
     * @return The user ArrayList
     */
    public ArrayList<User> getUserList() {
        return null;
    }

    /**
     * Removes a User from the UserList
     * @param user The UUID of the user to be removed
     */
    public void removeUser(UUID user) {

    }

    public ArrayList<Internship> searchCompany(String entry) {
        return null;
    }

    public ArrayList<Internship> searchPosition(String entry) {
        return null;
    }
}
