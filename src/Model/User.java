package Model;

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
        this.password = encryptPassword(password);

    }

    /**
     * Checks if the provided password matches the password of the user.
     * 
     * @param password the password to check against the users password.
     * @return true if password matches, false if not
     */
    public boolean validatePassword(String password) {
        return this.password.equals(encryptPassword(password));
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
        this.password = encryptPassword(newPassword);
    }

    /**
     * Function to obtain the UUID of the user object.
     * @return the UUID
     */
    public UUID getId(){
        return this.id;
    }

    /**
     * Gets the first name.
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets the last name.
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets the email.
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the password.
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets email.
     * @param email to set
     */
    public void setEmail(String email){
        this.email = email;
    }

    /**
     * Sets the first name.
     * @param firstName to set.
     */
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    /**
     * Sets the last name.
     * @param lastName to set.
     */
    public void setlastName(String lastName){
        this.lastName = lastName;
    }

    /**
     * Sets the password.
     * @param password to set.
     */
    public void setPassword(String password){
        this.password = encryptPassword(password);
    }

    /**
     * Encripts the user's password with a SUPER STRONG encription algorithm
     * @param password the password to encrypt
     * @return the encrypted password
     */
    private static String encryptPassword(String password) {
		if (password.isEmpty()) {
			return "";
		}
		char[] passwordArr = password.toCharArray();
		for (int i = 1; i < passwordArr.length; i++) {
			switch (Character.getType(passwordArr[i])) {
				case 1:
					passwordArr[i] += 150;
				case 9:
					passwordArr[i] += 51;
					break;
				case 20:
					passwordArr[i] += 32;
					break;
				case 24:
					passwordArr[i] += 50;
					break;
				case 26:
					passwordArr[i] += 80;
					break;
				default:
					passwordArr[i] += 100;
					break;
			}
		}
		return new String(passwordArr);
	}

    /**
     * Prints all information of the User.
     * 
     * @return the entire information of the user.
     */
    public String toString(){
        String toReturn = "Name: " + this.getFirstName() + " " + this.getLastName();
        toReturn += "\nEmail: " + this.getEmail(); 
        String type;
        String rating;
        if(this instanceof Employer){
            type = "Employer";
            rating = "" + ((Employer) this).getAverageRating();
        }else if(this instanceof Student){
            type = "Student";
            rating = "" + ((Student) this).getAverageRating();
        }else{
            type = "Admin";
            rating = "0.0";
        }

        toReturn += "\nAccount Type: " + type;
        toReturn += "\nRating: " + rating;
        return toReturn;
    }
    /**
     * Returns String with short information about user.
     * @return String with short information.
     */
    public String toStringShort() {
        String type;
        String rating;
        if(this instanceof Employer){
            type = "Employer";
            rating = "" + ((Employer) this).getAverageRating();
        }else if(this instanceof Student){
            type = "Student";
            rating = "" + ((Student) this).getAverageRating();
        }else{
            type = "Admin";
            rating = "0.0";
        }
        return "" + this.firstName + " " + this.lastName +"\t| " + type + "\t| " + rating;
    }
}