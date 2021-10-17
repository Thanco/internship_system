import java.util.UUID;

public abstract class User{
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    private boolean validatePassword(String password){
        return true;
    }

    protected abstract boolean validateEmail(String email);

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(UUID id, String firstName, String lastName, String email, String password){
        return;
    }

}