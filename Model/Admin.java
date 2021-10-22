package Model;

import java.util.UUID;

public class Admin extends User {

    /**
     * Instantiates Admin object and generates UUID while instantiating
     * @param id        is the uuid of the object.
     * @param firstName the first name of the user.
     * @param lastName  the last name of the user.
     * @param email     the email of the user.
     * @param password  the password of the user.
     */
    public Admin(UUID id, String firstName, String lastName, String email, String password) {
        super(id, firstName, lastName, email, password);
    }

    /**
     * Instantiates User object and generates uuid while instantiating
     * @param id        is the uuid of the object.
     * @param firstName the first name of the user.
     * @param lastName  the last name of the user.
     * @param email     the email of the user.
     * @param password  the password of the user.
    */
    public Admin(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password);
    }

    /**
     * Sets the verification status of a given employer to true.
     * @param employer to verify.
     */
    public void verifyEmployer(Employer employer) {
        employer.setVerificationStatus(true);
    }
}
