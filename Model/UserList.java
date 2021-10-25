package Model;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Class that contains all users currently loaded into the program.
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
	 * 
	 * @param userId the id of the User to delete.
	 */
	public void removeUserById(UUID userId) {

		users.removeIf(user -> user.getId().equals(userId));
	}

	/**
	 * Add an user to the list.
	 * 
	 * @param user the User to add.
	 */
	public void addUser(User user) {
		users.add(user);
	}

	/**
	 * Logging in a user by returning the user object.
	 * 
	 * @param email    of the account.
	 * @param password of the account.
	 * @return user object if credentials match, null if either password or email do
	 *         not match.
	 */
	public User loginUser(String email, String password) {
		User user = getUserByEmail(email);

		if (user == null) {
			return null;
		}

		if (user.validatePassword(password)) {
			return user;
		} else {
			return null;
		}

	}

	/**
	 * Returns a user object if available with given email.
	 * @param email the email the account has to have.
	 * @return user object if email matches, null if no accout with email available.
	 */
	private User getUserByEmail(String email) {
		for (User user : users) {
			if (user.getEmail().equals(email)) {
				return user;
			}
		}
		return null;
	}

	public void saveList(){
		return;
	}

}
