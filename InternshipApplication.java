import java.util.ArrayList;
import java.util.UUID;

/**
 * Used by UI to acces other classes
 */
public class InternshipApplication {
    /**
     * Checks user list for matching email/password
     * @param email Entered email
     * @param Password Entered Password
     * @return A user if credentials match
     * @return null if incorrect
     */
    public User login(String email, String Password) {

    }

    /**
     * Adds a new user to the arraylist of users
     * @param name Name of company/student
     * @param email Users email
     * @param password Users password
     * @param type Type of user: 0 = _, 1 = _
     */
    public void createUser(String name, String email, String password, int type) {

    }

    /**
     * idk
     */
    public void createResume(/*???*/) {

    }

    /**
     * Adds rating to a user
     * @param user The UUID of a student/employer
     * @param rating the entered rating
     */
    public void AddRating(UUID user, int rating) {

    }

    /**
     * gets a full list of internships
     * @return An array list of Internships
     */
    public ArrayList<Internship> getInternshipList() {
        
    }

    /**
     * Overwrites Student with matching UUID to save any changes
     * @param user The Student who is logging out
     */
    public void saveUser(Student user) {

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

    }

    /**
     * Gets the full user list
     * @return The user ArrayList
     */
    public ArrayList<User> getUserList() {

    }

    /**
     * Removes a User from the UserList
     * @param user The UUID of the user to be removed
     */
    public void removeUser(UUID user) {

    }
}