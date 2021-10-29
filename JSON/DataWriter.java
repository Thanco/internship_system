package JSON;

import Model.*;
import java.io.*;
import java.time.LocalDate;
import java.util.*;
import org.json.simple.*;

import Model.UserList;

/**
 * Contains methods to write objects to json files 
 * 
 * @author Terry Hancock
 */
public class DataWriter extends DataConstants {
	
	/**
	 * Writes the users in the UserList to users.json
	 */
	public static void saveUsers() {
		UserList users = UserList.getInstance();
		ArrayList<User> usersArr = users.getUsers();
		ArrayList<Student> studentArr = new ArrayList<>();
		JSONArray usersJSON = new JSONArray();
		for (int i = 0; i < usersArr.size(); i++) {
			usersJSON.add(getUserJSON(usersArr.get(i), studentArr));
		}
		saveResumes(studentArr);
		try (FileWriter file = new FileWriter(USERS_FILE_NAME)) {
			file.write(usersJSON.toJSONString());
			file.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Converts a user to a JSONObject and adds students to the studentArr
	 * @param user the user to be converted
	 * @param studentArr an ArrayList of students
	 * @return user in JSONObject format
	 */
	private static JSONObject getUserJSON(User user, ArrayList<Student> studentArr) {
		JSONObject userInformation = new JSONObject();
		userInformation.put(USERS_ID, user.getId().toString());
		userInformation.put(USERS_FIRST_NAME, user.getFirstName());
		userInformation.put(USERS_LAST_NAME, user.getLastName());
		userInformation.put(USERS_EMAIL, user.getEmail());
		userInformation.put(USERS_PASSWORD, user.getPassword());
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

	/**
	 * Adds student specific variables to the user JSONObject
	 * @param user the student that is being converted to a json
	 * @param userInformation the student JSONObject that variables are being added to
	 */
	private static void getStudentInformation(Student user, JSONObject userInformation) {
		userInformation.put(USERS_RATINGS, user.getRatings());
		if (user.getResume() != null) {
			userInformation.put(STUDENTS_RESUME, user.getResume().getUuid().toString());
		} else {
			userInformation.put(STUDENTS_RESUME, null);
		}
		ArrayList<UUID> formerEmployersUUID = user.getFormerEmployers();
		ArrayList<String> formerEmployersStr = new ArrayList<>();
		for (UUID i : formerEmployersUUID) {
			formerEmployersStr.add(i.toString());
		}
		userInformation.put(SUTDENTS_FORMER_EMPLOYERS, formerEmployersStr);
	}

	/**
	 * Adds employer specific variables to the user JSONObject
	 * @param user the employer that is being converted to a json
	 * @param userInformation the employer JSONObject that variables are being added to
	 */
	private static void getEmployerInformation(Employer user, JSONObject userInformation) {
		userInformation.put(USERS_RATINGS, user.getRatings());
		userInformation.put(EMPLOYERS_VERIFICATION_STATUS, user.getVerificationStatus());
		ArrayList<UUID> internshipListUUID = user.getInternshipList();
		ArrayList<String> internshipListStr = new ArrayList<>();
		for (UUID i : internshipListUUID) {
			internshipListStr.add(i.toString());
		}
		userInformation.put(EMPLOYERS_INTERNSHIP_LIST, internshipListStr);
		ArrayList<UUID> employeesUUID = user.getEmployees();
		ArrayList<String> employeesStr = new ArrayList<>();
		for (UUID i : employeesUUID) {
			employeesStr.add(i.toString());
		}
		userInformation.put(EMPLOYERS_EMPLOYEES, employeesStr);
	}

	/**
	 * Writes resumes from the students in studentArr to resuemes.json
	 * @param studentArr ArrayList of students to get resumes from
	 */
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

	/**
	 * Converts a resume to a JSONObject and adds students to the studentArr
	 * @param student the student that owns the resume
	 * @return resume in JSONObject format
	 */
	private static JSONObject getResumeJSON(Student student) {
		JSONObject resumeInformation = new JSONObject();
		resumeInformation.put(RESUMES_ID, student.getResume().getUuid().toString());
		JSONObject education = new JSONObject();
		education.put(RESUMES_SCHOOL_TITLE, student.getResume().getEducation().getSchoolTitle());
		switch (student.getResume().getEducation().getSchoolClass()) {
			case FRESHMAN:
				education.put(RESUMES_SCHOOL_CLASS, "freshman");
			break;
			case SOPHOMORE:
				education.put(RESUMES_SCHOOL_CLASS, "sophomore");
			break;
			case JUNIOR:
				education.put(RESUMES_SCHOOL_CLASS, "junior");
			break;
			case SENIOR:
				education.put(RESUMES_SCHOOL_CLASS, "senior");
			break;
			default:
				break;
		}
		education.put(RESUMES_MAJOR, student.getResume().getEducation().getMajor());
		resumeInformation.put(RESUMES_EDUCATION, education);
		ArrayList<WorkExperience> workExperienceArr = student.getResume().getWorkExperienceList();
		JSONArray workExperienceJSONs = new JSONArray();
		for (int i = 0; i < workExperienceArr.size(); i++) {
			JSONObject workExperienceJSON = new JSONObject();
			workExperienceJSON.put(RESUMES_EMPLOYER, workExperienceArr.get(i).getCompany());
			workExperienceJSON.put(RESUMES_START_DATE, calendarToJsonDate(workExperienceArr.get(i).getStart()));
			workExperienceJSON.put(RESUMES_END_DATE, calendarToJsonDate(workExperienceArr.get(i).getEnd()));
			workExperienceJSONs.add(workExperienceJSON);
		}
		resumeInformation.put(RESUMES_WORK_EXPERIENCE, workExperienceJSONs);
		resumeInformation.put(RESUMES_STUDENT_SKILLS, student.getResume().getStudentSkills());
		resumeInformation.put(RESUMES_EXTRA_CURRICULAR, student.getResume().getExtraCirricularList());
		resumeInformation.put(RESUMES_OWNER_ID, student.getResume().getOwnerUUID().toString());
		return resumeInformation;
	}
	

	/**
	 * Writes internships from InternshipList to internships.json
	 */
	public static void saveInternships() {
		InternshipList internships = InternshipList.getInstance();
		ArrayList<Internship> internshipsArr = internships.getInternships();
		JSONArray internshipsJSON = new JSONArray();
		for (int i = 0; i < internshipsArr.size(); i++) {
			internshipsJSON.add(getInternshipJSON(internshipsArr.get(i)));
		}
		try (FileWriter file = new FileWriter(INTERNSHIPS_FILE_NAME)) {
			file.write(internshipsJSON.toJSONString());
			file.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Converts a java internship object to a JSONObject
	 * @param internship the intership to covert
	 * @return the JSONObject format of the internship
	 */
	private static JSONObject getInternshipJSON(Internship internship) {
		JSONObject internshipInformation = new JSONObject();
		internshipInformation.put(INTERNSHIPS_ID, internship.getId().toString());
		internshipInformation.put(INTERNSHIPS_TITLE, internship.getTitle());
		internshipInformation.put(INTERNSHIPS_EMPLOYER, internship.getEmployer());
		internshipInformation.put(INTERNSHIPS_DESCRIPTION, internship.getDescription());
		internshipInformation.put(INTERNSHIPS_REQUIRED_SKILLS, internship.getRequiredSkills());
		internshipInformation.put(INTERNSHIPS_START_DATE, localDateToJsonDate(internship.getStartDate()));
		internshipInformation.put(INTERNSHIPS_END_DATE, localDateToJsonDate(internship.getEndDate()));
		internshipInformation.put(INTERNSHIPS_HOURS_PER_DAY, internship.getHoursPerDay());
		internshipInformation.put(INTERNSHIPS_EXPERATION_DATE, localDateToJsonDate(internship.getExpirationDate()));
		JSONObject salary = new JSONObject();
		if (internship.getSalary().contains("-")) {
			salary.put(INTERNSHIPS_SALARY_TYPE, "r");
			String[] salaryStr = internship.getSalary().split("-");
			for (String i : salaryStr) {
				i.trim();
			}
			salary.put(INTERNSHIPS_SALARY_LOWER, Integer.parseInt(salaryStr[0]));
			salary.put(INTERNSHIPS_SALARY_LOWER, Integer.parseInt(salaryStr[1]));
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
			salary.put(INTERNSHIPS_SALARY_VALUE, Integer.parseInt(internship.getSalary()));
		} else {
			salary.put(INTERNSHIPS_SALARY_TYPE, "h");
			salary.put(INTERNSHIPS_SALARY_VALUE, internship.getSalary());
		}
		internshipInformation.put(INTERNSHIPS_SALARY, salary);
		ArrayList<String> applicantsUUID = new ArrayList<>();
		for (Resume i : internship.getApplications()) {
			applicantsUUID.add(i.getUuid().toString());
		}
		internshipInformation.put(INTERNSHIPS_APPLICATIONS, applicantsUUID);
		return internshipInformation;
	}

	// /**
	//  * Prints a student's resume to a plain text file
	//  * @param student the sudent to print the resume from
	//  */
	// public static printResumeToFile(Student student) {

	// }

	// /**
	//  * Converts a resume to a string arraylist
	//  * @param student 
	//  * @return
	//  */
	// private static ArrayList<String> resuemToStringList(Student student) {
		
	// }

	/**
	 * Converts a calender object to the json string format
	 * @param calendar the calender to convert
	 * @return A string format of the calender (MM/YYYY)
	 */
	private static String calendarToJsonDate(Calendar calendar) {
		String month = String.valueOf(calendar.get(Calendar.MONTH));
		String year = String.valueOf(calendar.get(Calendar.YEAR));
		return month + "/" + year;
	}

	/**
	 * Converts a locadDate object to the json string format
	 * @param calendar the locadDate to convert
	 * @return A string format of the locadDate (MM/YYYY)
	 */
	private static String localDateToJsonDate(LocalDate date) {
		return date.getMonthValue() + "/" + date.getYear();
	}
}
