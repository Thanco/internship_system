package JSON;

import java.util.*;
import Model.*;
import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Contains methods to load objects from json files
 */
public class DataLoader extends DataConstants {
	
	/**
	 * Loads an ArrayList of Users from the users json file
	 * @return An ArrayList of Users
	 */
	public static ArrayList<User> loadUsers() {
		ArrayList<User> users = new ArrayList<User>();
		try {
			FileReader reader = new FileReader(USERS_FILE_NAME);
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

	/**
	 * Loads an ArrayList of Resumes from the resumes json file
	 * @return An ArrayList of Resumes
	 */
	public static ArrayList<Resume> loadResumes() {
		ArrayList<Resume> resumes = new ArrayList<>();
		try {
			FileReader reader = new FileReader(RESUMES_FILE_NAME);
			JSONArray resumesJSON = (JSONArray)new JSONParser().parse(reader);
			for (int i = 0; i < resumesJSON.size(); i++) {
				JSONObject resumeJSON = (JSONObject)resumesJSON.get(i);
				UUID id = (UUID)resumeJSON.get(RESUMES_ID);
				JSONArray educationArrJSON = (JSONArray)new JSONParser().parse(RESUMES_EDUCATION);
				for (int j = 0; j < educationArrJSON.size(); j++) {
					JSONObject educationJSON = (JSONObject)educationArrJSON.get(j);
					String schoolTitle = (String)educationJSON.get(RESUMES_SCHOOL_TITLE);
					SchoolYear schoolClass = null;
					switch ((String)educationJSON.get(RESUMES_SCHOOL_TITLE)) {
						case "freshman":
							schoolClass = SchoolYear.FRESHMAN;
							break;
						case "sophomore":
							schoolClass = SchoolYear.SOPHOMORE;
							break;
						case "junior":
							schoolClass = SchoolYear.JUNIOR;
							break;
						case "senior":
							schoolClass = SchoolYear.SENIOR;
							break;
						default:
							break;
					}
					String major = (String)educationJSON.get(RESUMES_MAJOR);
					Education education = new Education(schoolTitle, schoolClass, major);
				}
				JSONArray workExperienceArrJSON = (JSONArray)new JSONParser().parse(RESUMES_EDUCATION);
				ArrayList<WorkExperience> workExperience = new ArrayList<>();
				for (int j = 0; j < workExperienceArrJSON.size(); j++) {
					JSONObject workExperienceJSON = (JSONObject)workExperienceArrJSON.get(j);
					String employer = (String)workExperienceJSON.get(RESUMES_EMPLOYER);
					String startDateString = (String)workExperienceJSON.get(RESUMES_START_DATE);
					String[] startDateArr = startDateString.split("/");
					Calendar startDate = Calendar.getInstance();
					startDate.set(Calendar.MONTH, Integer.parseInt(startDateArr[0]));
					startDate.set(Calendar.YEAR, Integer.parseInt(startDateArr[1]));
					String endDateString = (String)workExperienceJSON.get(RESUMES_START_DATE);
					String[] endDateArr = endDateString.split("/");
					Calendar endDate = Calendar.getInstance();
					endDate.set(Calendar.MONTH, Integer.parseInt(endDateArr[0]));
					endDate.set(Calendar.YEAR, Integer.parseInt(endDateArr[1]));
					// workExperience.add(new WorkExperience(employer, startDate, endDate));
					workExperience.add(new WorkExperience("company", 1, "start", "end"));
				}
				ArrayList<String> skills = jsonToStringArr(RESUMES_STUDENT_SKILLS);
				ArrayList<String> extraCurricular = jsonToStringArr(RESUMES_EXTRA_CURRICULAR);
				String currentEmployer = (String)resumeJSON.get(RESUMES_EMPLOYER);
				// resumes.add(new Resume(firstName, lastName, school, schoolClass, major, company, length, start, end, studentSkills, extraCirricular));
			}
			return resumes;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Loads an ArrayList of Internships from the internships json file
	 * @return An ArrayList of Internships
	 */
	public static ArrayList<Internship> loadInternships() {
		return new ArrayList<Internship>();
	}

	private static ArrayList<String> jsonToStringArr(String toLoad) {
		try {
			JSONArray JSONArr = (JSONArray)new JSONParser().parse(toLoad);
			ArrayList<String> arr = new ArrayList<>();
			Iterator<?> arrIterator = JSONArr.iterator();
			while (arrIterator.hasNext()) {
				arr.add((String)arrIterator.next());
			}
			return arr;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
