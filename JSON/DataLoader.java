package JSON;

import java.util.*;
import Model.*;
import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.time.LocalDate;

@SuppressWarnings("unchecked")

/**
 * Contains methods to load objects from json files
 * 
 * @author Terry Hancock
 */
public class DataLoader extends DataConstants {
	
	/**
	 * Loads an ArrayList of Users from the users json file
	 * @return An ArrayList of Users
	 */
	public static ArrayList<User> getUsers() {
		ArrayList<User> users = new ArrayList<User>();
		ArrayList<Resume> resumes = getResumes();
		try {
			FileReader reader = new FileReader(USERS_FILE_NAME);
			JSONArray usersJSON = (JSONArray)new JSONParser().parse(reader);
			for (int i = 0; i < usersJSON.size(); i++) {
				JSONObject userJSON = (JSONObject)usersJSON.get(i);
				UUID id = UUID.fromString((String)userJSON.get(USERS_ID));
				String firstName = (String)userJSON.get(USERS_FIRST_NAME);
				String lastName = (String)userJSON.get(USERS_LAST_NAME);
				String email = (String)userJSON.get(USERS_EMAIL);
				String password = (String)userJSON.get(USERS_PASSWORD);
				switch ((String)userJSON.get(USERS_TYPE)) {
					case "s": {
						ArrayList<Integer> ratings = (ArrayList<Integer>)jsonToArr(USERS_RATINGS, userJSON, "int");
						UUID resumeUUID = null;
						if (userJSON.get(STUDENTS_RESUME) != null) {
							resumeUUID = UUID.fromString((String)userJSON.get(STUDENTS_RESUME));
						}
						Resume resume = null;
						for (Resume j : resumes) {
							if (j.getUuid().equals(resumeUUID)) {
								resume = j;
								resume.setFirstName(firstName);
								resume.setLastName(lastName);
							}
						}
						ArrayList<UUID> formerEmployers = (ArrayList<UUID>)jsonToArr(SUTDENTS_FORMER_EMPLOYERS, userJSON, "uuid");
						users.add(new Student(id, firstName, lastName, email, password, resume, ratings, formerEmployers));
						break;
					}
					case "e": {
						ArrayList<Integer> ratings = (ArrayList<Integer>)jsonToArr(USERS_RATINGS, userJSON, "int");
						Boolean verificationStatus = (Boolean)userJSON.get(EMPLOYERS_VERIFICATION_STATUS);
						ArrayList<UUID> internshipList = (ArrayList<UUID>)jsonToArr(EMPLOYERS_INTERNSHIP_LIST, userJSON, "uuid");
						ArrayList<UUID> employees = (ArrayList<UUID>)jsonToArr(EMPLOYERS_EMPLOYEES, userJSON, "uuid");
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
	public static ArrayList<Resume> getResumes() {
		ArrayList<Resume> resumes = new ArrayList<>();
		try {
			FileReader reader = new FileReader(RESUMES_FILE_NAME);
			JSONArray resumesJSON = (JSONArray)new JSONParser().parse(reader);
			for (int i = 0; i < resumesJSON.size(); i++) {
				JSONObject resumeJSON = (JSONObject)resumesJSON.get(i);
				UUID id = UUID.fromString((String)resumeJSON.get(RESUMES_ID));
				UUID ownerID = UUID.fromString((String)resumeJSON.get(RESUMES_OWNER_ID));
				JSONObject educationJSON = (JSONObject)resumeJSON.get(RESUMES_EDUCATION);
				Education education = null;
					String schoolTitle = (String)educationJSON.get(RESUMES_SCHOOL_TITLE);
					SchoolYear schoolClass = null;
					switch ((String)educationJSON.get(RESUMES_SCHOOL_CLASS)) {
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
					education = new Education(schoolTitle, schoolClass, major);
				JSONArray workExperienceArrJSON = (JSONArray)resumeJSON.get(RESUMES_WORK_EXPERIENCE);
				ArrayList<WorkExperience> workExperience = new ArrayList<>();
				for (int j = 0; j < workExperienceArrJSON.size(); j++) {
					JSONObject workExperienceJSON = (JSONObject)workExperienceArrJSON.get(j);
					String employer = (String)workExperienceJSON.get(RESUMES_EMPLOYER);
					Calendar startDate = jsonDateToCalender(RESUMES_START_DATE, workExperienceJSON);
					Calendar endDate = jsonDateToCalender(RESUMES_END_DATE, workExperienceJSON);
					ArrayList<String> descriptions = (ArrayList<String>)jsonToArr(RESUMES_DESCRIPTIONS, workExperienceJSON, "string");
					workExperience.add(new WorkExperience(employer, startDate, endDate, descriptions));
				}
				ArrayList<String> studentSkills = (ArrayList<String>)jsonToArr(RESUMES_STUDENT_SKILLS, resumeJSON, "string");
				ArrayList<String> extraCirricular = (ArrayList<String>)jsonToArr(RESUMES_EXTRA_CURRICULAR, resumeJSON, "string");
				resumes.add(new Resume(id, ownerID, "", "", education, studentSkills, workExperience, extraCirricular));
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
	public static ArrayList<Internship> getInternships() {
		ArrayList<Internship> internships = new ArrayList<Internship>();
		try {
			FileReader reader = new FileReader(INTERNSHIPS_FILE_NAME);
			JSONArray internshipsJSON = (JSONArray)new JSONParser().parse(reader);
			for (int i = 0; i < internshipsJSON.size(); i++) {
				JSONObject internshipJSON = (JSONObject)internshipsJSON.get(i);
				UUID id = UUID.fromString((String)internshipJSON.get(INTERNSHIPS_ID));
				String title = (String)internshipJSON.get(INTERNSHIPS_TITLE);
				String employer = (String)internshipJSON.get(INTERNSHIPS_EMPLOYER);
				String description = (String)internshipJSON.get(INTERNSHIPS_DESCRIPTION);
				ArrayList<String> requiredSkills = (ArrayList<String>)jsonToArr(INTERNSHIPS_REQUIRED_SKILLS, internshipJSON, "string");
				LocalDate startDate = jsonDateToLocalDate(INTERNSHIPS_START_DATE, internshipJSON);
				LocalDate endDate = jsonDateToLocalDate(INTERNSHIPS_END_DATE, internshipJSON);
				int hoursPerDay = ((Long)internshipJSON.get(INTERNSHIPS_HOURS_PER_DAY)).intValue();
				LocalDate expirationDate = jsonDateToLocalDate(INTERNSHIPS_EXPERATION_DATE, internshipJSON);
				JSONObject salaryJSON = (JSONObject)internshipJSON.get(INTERNSHIPS_SALARY);
				SalaryType salaryType;
				switch ((String)salaryJSON.get(INTERNSHIPS_SALARY_TYPE)) {
					case "f": {
						salaryType = new FixedSalary(((Long)salaryJSON.get(INTERNSHIPS_SALARY_VALUE)).intValue());
						break;
					}
					case "r": {
						salaryType = new RangeSalary(((Long)salaryJSON.get(INTERNSHIPS_SALARY_LOWER)).intValue(),
													 ((Long)salaryJSON.get(INTERNSHIPS_SALARY_UPPER)).intValue());
						break;
					}
					default:
						salaryType = new HiddenSalary();
						break;
				}
				ArrayList<UUID> applicationsUUID = (ArrayList<UUID>)jsonToArr(INTERNSHIPS_APPLICATIONS, internshipJSON, "uuid");
				UserList users = UserList.getInstance();
				ArrayList<Resume> applications = new ArrayList<>();
				for (UUID j : applicationsUUID) {
					applications.add((users.getStudentByResumeId(j)).getResume());
				}
				internships.add(new Internship(id, employer, title, description, requiredSkills, startDate, endDate, hoursPerDay, expirationDate, salaryType, applications));
			}
			return internships;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Converts an array in a json file to an ArrayList
	 * @param toLoad the name of the array to load
	 * @param type the type of array ("string", "int", "uuid")
	 * @return A java ArrayList object of the parameter type
	 */
	private static ArrayList<?> jsonToArr(String toLoad, JSONObject loadFrom, String type) {
		try {
			JSONArray JSONArr = (JSONArray)loadFrom.get(toLoad);
			switch (type) {
				case "string": {
					ArrayList<String> arr = new ArrayList<>();
					Iterator<?> arrIterator = JSONArr.iterator();
					while (arrIterator.hasNext()) {
						arr.add((String)arrIterator.next());
					}
					return arr;
				}
				case "int": {
					ArrayList<Integer> arr = new ArrayList<>();
					Iterator<?> arrIterator = JSONArr.iterator();
					while (arrIterator.hasNext()) {
						arr.add(((Long)arrIterator.next()).intValue());
					}
					return arr;
				}
				case "uuid": {
					ArrayList<UUID> arr = new ArrayList<>();
					Iterator<?> arrIterator = JSONArr.iterator();
					while (arrIterator.hasNext()) {
						arr.add(UUID.fromString((String)arrIterator.next()));
					}
					return arr;
				}
				default:
					break;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Converts a json date string (MM/YYYY) to a Calender object
	 * @param toLoad the json date variable name
	 * @param object the json object being loded from
	 * @return A java Calender object with the date from the json stirng
	 */
	private static Calendar jsonDateToCalender(String toLoad, JSONObject object) {
		String dateString = (String)object.get(toLoad);
		String[] startDateArr = dateString.split("/");
		Calendar date = Calendar.getInstance();
		date.set(Calendar.MONTH, Integer.parseInt(startDateArr[0]));
		date.set(Calendar.YEAR, Integer.parseInt(startDateArr[1]));
		return date;
	}

	private static LocalDate jsonDateToLocalDate(String toLoad, JSONObject object) {
		String dateString = (String)object.get(toLoad);
		String[] startDateArr = dateString.split("/");
		LocalDate date = LocalDate.of(Integer.parseInt(startDateArr[1]), Integer.parseInt(startDateArr[0]), 1);
		return date;
	}
}
