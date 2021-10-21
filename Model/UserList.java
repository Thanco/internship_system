import java.util.ArrayList;
import java.util.UUID;

public class UserList {
    private static UserList UserList;
    private ArrayList<User> users;

    public UserList() {
        return;
    }

    public static UserList getInstance() {
        return new UserList();
    }

    public User getUser(String email) {
        return new Admin(new UUID(2, 3), "Test", "Test", "Test@test.de", "123");
    }

    public void saveList() {
        return;
    }

    public ArrayList<User> getUsers() {
        return this.users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
}
