package JSON;

import java.util.*;
import Model.*;
import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataLoader extends DataConstants {
	
	public static ArrayList<User> loadUsers() {
		ArrayList<User> users = new ArrayList<User>();
		try {
			FileReader reader = new FileReader(USERS_FILE_NAME);
			JSONParser parser = new JSONParser();
			JSONArray usersJSON = (JSONArray)new JSONParser().parse(reader);
			for (int i = 0; i < usersJSON.size(); i++) {
				JSONObject userJSON = (JSONObject)usersJSON.get(i);
				UUID id = (UUID)userJSON.get(USERS_ID);
				String firstName = (String)userJSON.get(USERS_FIRST_NAME);
				String lastName = (String)userJSON.get(USERS_LAST_NAME);
				String email = (String)userJSON.get(USERS_EMAIL);
				String password = (String)userJSON.get(USERS_PASSWORD);
				switch ((String)userJSON.get(USERS_TYPE)) {
					case "s": {
						JSONArray jsonRatings = (JSONArray)userJSON.get(USERS_RATINGS);
						ArrayList<Integer> ratings = new ArrayList<>();
						Iterator<?> ratingIterator = jsonRatings.iterator();
						while (ratingIterator.hasNext()) {
							ratings.add(((Long)ratingIterator.next()).intValue());
						}
						Resume resume = (Resume)userJSON.get(STUDENTS_RESUME);
						users.add(new Student(id, firstName, lastName, email, password, resume, ratings));
						break;
					}
					case "e": {
						JSONArray jsonRatings = (JSONArray)userJSON.get(USERS_RATINGS);
						ArrayList<Integer> ratings = new ArrayList<>();
						Iterator<?> ratingIterator = jsonRatings.iterator();
						while (ratingIterator.hasNext()) {
							ratings.add(((Long)ratingIterator.next()).intValue());	
						}
						Boolean verificationStatus = (Boolean)userJSON.get(EMPLOYERS_VERIFICATION_STATUS);
						JSONArray jsonInternships = (JSONArray)userJSON.get(EMPLOYERS_INTERNSHIP_LIST);
						ArrayList<UUID> internshipList = new ArrayList<>();
						Iterator<?> internshipsIterator = jsonInternships.iterator();
						while (internshipsIterator.hasNext()) {
							internshipList.add((UUID)internshipsIterator.next());	
						}
						JSONArray jsonEmployees = (JSONArray)userJSON.get(EMPLOYERS_EMPLOYEES);
						ArrayList<UUID> employees = new ArrayList<>();
						Iterator<?> employeeIterator = jsonEmployees.iterator();
						while (employeeIterator.hasNext()) {
							employees.add((UUID)employeeIterator.next());
						}
						users.add(new Employer(id, firstName, lastName, email, password, verificationStatus, internshipList, ratings, employees));
						break;
					}
					case "a": {
						users.add(new Admin(firstName, lastName, email, password));
						break;
					}
					default:
						break;
				}
			}
			return users;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static ArrayList<Resume> loadResumes() {
		return new ArrayList<Resume>();
	}

	public static ArrayList<Internship> loadInternships() {
		return new ArrayList<Internship>();
	}
}
