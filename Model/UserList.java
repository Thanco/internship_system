import java.util.ArrayList;
import java.util.UUID;

/**
 * Class that contains all internship listings currently loaded into the
 * program.
 * 
 * @author Bjorn Sauter
 */
public class UserList {

	private static UserList userList;
	private ArrayList<User> users;

	/**
	 * Private constructor used in Singleton design pattern.
	 */
	private UserList() {
		this.users = new ArrayList<>();
	}

	/**
	 * Returns instance of User list.
	 * 
	 * @return the instance
	 */
	public static UserList getInstance() {
		if (userList == null) {
			userList = new UserList();
		}
		return userList;
	}

	/**
	 * Sets the provided Users
	 * 
	 * @param userList the provided Users as array list.
	 */
	public void setUserList(ArrayList<User> userList) {
		this.users = userList;
	}

	/**
	 * Returns the User that matches a given .
	 * 
	 * @param UserId the given id.
	 * @return the User with the id.
	 */
	public User getUserById(UUID userId) {
		for (User user : users) {
			if (user.getId().equals(userId)) {
				return user;
			}
		}
		return null;
	}

	/**
	 * Removes an user with a given Id
	 * @param userId the id of the User to delete.
	 */
	public void removeUserById(UUID userId) {

		users.removeIf(user -> user.getId().equals(userId));
	}

	/**
	 * Add an user to the list.
	 * @param user the User to add.
	 */
	public void addUser(User user){
		users.add(user);
	}

}
