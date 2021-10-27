package JSON;

import Model.*;
import java.io.*;
import java.util.*;
import org.json.simple.*;

import Model.UserList;

/**
 * Contains methods to write objects to json files 
 * 
 * @author Terry Hancock
 */
public class DataWriter extends DataConstants {
	
	public static void saveUsers() {
		UserList users = UserList.getInstance();
		// ArrayList<User> usersArr = users.getUsers();
		ArrayList<Student> studentArr;
		JSONArray usersJSON = new JSONArray();
		// for (int i = 0; i < usersArr.size(); i++) {
		// 	usersJSON.add(getUserJSON(usersArr.get(i), studentArr));
		// }
		// saveResumes(studentArr);
		try (FileWriter file = new FileWriter(USERS_FILE_NAME)) {
			file.write(usersJSON.toJSONString());
			file.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static JSONObject getUserJSON(User user, ArrayList<Student> studentArr) {
		JSONObject userInformation = new JSONObject();
		userInformation.put(USERS_ID, user.getId());
		userInformation.put(USERS_FIRST_NAME, user.getFirstName());
		userInformation.put(USERS_LAST_NAME, user.getLastName());
		userInformation.put(USERS_EMAIL, user.getEmail());
		//userInformation.put(USERS_PASSWORD, user.getPassword());
		if (user instanceof Student) {
			userInformation.put(USERS_TYPE, "s");
			getStudentInformation((Student)user, userInformation);
			studentArr.add((Student)user);
		} else if (user instanceof Employer) {
			userInformation.put(USERS_TYPE, "e");
			getEmployerInformation((Employer)user, userInformation);
		} else if (user instanceof Admin) {
			userInformation.put(USERS_TYPE, "a");
		}
	return userInformation;
	}

	private static void getStudentInformation(Student user, JSONObject userInformation) {
		// userInformation.put(USERS_RATINGS, user.getRatings());
		// userInformation.put(STUDENTS_RESUME, user.getResume().getID());
	}

	private static void getEmployerInformation(Employer user, JSONObject userInformation) {
		//userInformation.put(USERS_RATINGS, user.getRatings());
		//userInformation.put(EMPLOYERS_INTERNSHIP_LIST, user.getInternshipList().getID);
	}

	private static void saveResumes(ArrayList<Student> studentArr) {
		JSONArray resumesJSON = new JSONArray();
		for (int i = 0; i < studentArr.size(); i++) {
			if (studentArr.get(i).getResume() != null)
				resumesJSON.add(getResumeJSON(studentArr.get(i)));
		}
		try (FileWriter file = new FileWriter(RESUMES_FILE_NAME)) {
			file.write(resumesJSON.toJSONString());
			file.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static JSONObject getResumeJSON(Student student) {
		JSONObject resumeInformation = new JSONObject();
		// resumeInformation.put(RESUMES_ID, student.getResume().getID());
		JSONObject education = new JSONObject();
		education.put(RESUMES_SCHOOL_TITLE, student.getResume().getEducation().getSchoolTitle());
		education.put(RESUMES_SCHOOL_CLASS, student.getResume().getEducation().getSchoolClass());
		education.put(RESUMES_MAJOR, student.getResume().getEducation().getMajor());
		resumeInformation.put(RESUMES_EDUCATION, education);
		JSONObject workExperience = new JSONObject();
		// workExperience.put(RESUMES_EMPLOYER, student.getResume().getWorkExpierence().getEmployer());
		// workExperience.put(RESUMES_START_DATE, calendarToJsonDate(student.getResume().getWorkExpierence().getStart()));
		// workExperience.put(RESUMES_END_DATE, calendarToJsonDate(student.getResume().getWorkExpierence().getEnd()));
		resumeInformation.put(RESUMES_WORK_EXPERIENCE, workExperience);
		JSONObject studentSkills = new JSONObject();
		resumeInformation.put(RESUMES_STUDENT_SKILLS, student.getResume().getStudentSkills());
		resumeInformation.put(RESUMES_EXTRA_CURRICULAR, student.getResume().getExtraCirricular());
		// resumeInformation.put(RESUMES_EMPLOYER, student.getResume().getEmployer());
		return resumeInformation;
	}
	


	public static void saveInternships() {
		InternshipList internships = InternshipList.getInstance();
		//ArrayList<Internship> internshipsArr = internships.getInternships();
		JSONArray internshipsJSON = new JSONArray();
		// for (int i = 0; i < internshipsArr.size(); i++) {
		// 	internshipsJSON.add(getInternshipJSON(internshipsArr.get(i)));
		// }
		try (FileWriter file = new FileWriter(INTERNSHIPS_FILE_NAME)) {
			file.write(internshipsJSON.toJSONString());
			file.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static JSONObject getInternshipJSON(Internship internship) {
		JSONObject internshipInformation = new JSONObject();
		internshipInformation.put(INTERNSHIPS_ID, internship.getId());
		internshipInformation.put(INTERNSHIPS_TITLE, internship.getTitle());
		internshipInformation.put(INTERNSHIPS_EMPLOYER, internship.getEmployer());
		// internshipInformation.put(INTERNSHIPS_DESCRIPTION, internship.getDescription());
		// internshipInformation.put(INTERNSHIPS_REQUIRED_SKILLS, internship.getRequiredSkills());
		// internshipInformation.put(INTERNSHIPS_START_DATE, calendarToJsonDate(internship.getStartDate()));
		// internshipInformation.put(INTERNSHIPS_END_DATE, calendarToJsonDate(internship.getEndDate()));
		// internshipInformation.put(INTERNSHIPS_HOURS_PER_DAY, internship.getHoursPerDay());
		// internshipInformation.put(INTERNSHIPS_EXPERATION_DATE, calendarToJsonDate(internship.getExpirationDate()));
		JSONObject salary = new JSONObject();
		if (internship.getSalary().contains("-")) {
			salary.put(INTERNSHIPS_SALARY_TYPE, "r");
			String[] salaryStr = internship.getSalary().split("-");
			for (String i : salaryStr) {
				i.trim();
			}
			salary.put(INTERNSHIPS_SALARY_LOWER, salaryStr[0]);
			salary.put(INTERNSHIPS_SALARY_LOWER, salaryStr[1]);
		} else if (internship.getSalary().contains("0") || 
				   internship.getSalary().contains("1") || 
				   internship.getSalary().contains("2") || 
				   internship.getSalary().contains("3") || 
				   internship.getSalary().contains("4") || 
				   internship.getSalary().contains("5") || 
				   internship.getSalary().contains("6") || 
				   internship.getSalary().contains("7") || 
				   internship.getSalary().contains("8") || 
				   internship.getSalary().contains("9")) {
			salary.put(INTERNSHIPS_SALARY_TYPE, "f");
			salary.put(INTERNSHIPS_SALARY_VALUE, internship.getSalary());
		} else {
			salary.put(INTERNSHIPS_SALARY_TYPE, "h");
			salary.put(INTERNSHIPS_SALARY_VALUE, internship.getSalary());
		}
		internshipInformation.put(INTERNSHIPS_SALARY, salary);
		internshipInformation.put(INTERNSHIPS_APPLICATIONS, internship.getApplications());
		return internshipInformation;
	}

	private static String calendarToJsonDate(Calendar calendar) {
		String month = String.valueOf(calendar.get(Calendar.MONTH));
		String year = String.valueOf(calendar.get(Calendar.YEAR));
		return month + "/" + year;
	}
}
