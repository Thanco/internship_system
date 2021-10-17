package Model;
import java.util.UUID;

public class Admin extends User {

    public Admin(UUID id, String firstName, String lastName, String email, String password) {
        super(id, firstName, lastName, email, password);
    }

    @Override
    protected boolean validateEmail(String email) {
        return false;
    }

    public boolean verifyEmployer(Employer employer) {
        return true;
    }

    public boolean editInternship() {
        return true;
    }

}
