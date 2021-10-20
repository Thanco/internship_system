
import java.util.UUID;

/** Abstract User class, that serves as a parent class for Admin, Employer and Student.
 * 
 * @author Bjorn Sauter
 */
public abstract class User {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    /**
     * Instantiates User object with given UUID
     * 
     * @param id        is the uuid of the object.
     * @param firstName the first name of the user.
     * @param lastName  the last name of the user.
     * @param email     the email of the user.
     * @param password  the password of the user.
     */
    public User(UUID id, String firstName, String lastName, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    /**
     * Instantiates User object and generates uuid while instantiating
     * @param firstName the first name of the user.
     * @param lastName  the last name of the user.
     * @param email     the email of the user.
     * @param password  the password of the user.
     */
    public User(String firstName, String lastName, String email, String password){
        this.id = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;

    }

    /**
     * Checks if the provided password matches the password of the user.
     * 
     * @param password the password to check against the users password.
     * @return true if password matches, false if not
     */
    public boolean validatePassword(String password) {
        return this.password.equals(password);
    }

    /**
     * Validates email against provided email.
     * @param email provided mail to validate against
     * @return true if email matches, false otherwise
     * */
    public boolean validateEmail(String email){
        return this.password.equals(email);
    }

    /**
     * Function to change the current password
     * @param newPassword the new password.
     */
    public void changePassword(String newPassword){
        this.password = newPassword;
    }
}