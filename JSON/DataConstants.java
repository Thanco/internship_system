package JSON;

/**
 * Contains constants for json writer and loader
 * 
 * @author Terry Hancock
 */
public abstract class DataConstants {
	
	/**
	 * User definitions
	 */
	protected static final String USERS_FILE_NAME = "JSON/users.json";
	protected static final String USERS_TYPE = "userType";
	protected static final String USERS_ID = "id";
	protected static final String USERS_FIRST_NAME = "firstName";
	protected static final String USERS_LAST_NAME = "lastName";
	protected static final String USERS_EMAIL = "email";
	protected static final String USERS_PASSWORD = "password";
	protected static final String USERS_RATINGS = "rating";
	
	/**
	 * Student definitions
	 */
	protected static final String STUDENTS_RESUME = "resume";

	/**
	 * Employer definitions
	 */
	protected static final String EMPLOYERS_VERIFICATION_STATUS = "verificationStatus";
	protected static final String EMPLOYERS_INTERNSHIP_LIST = "internshipList";
	protected static final String EMPLOYERS_EMPLOYEES = "employees";
	
	/**
	 * Resume definitions
	 */
	protected static final String RESUMES_FILE_NAME = "JSON/resumes.json";
	protected static final String RESUMES_ID = "id";
	protected static final String RESUMES_FIRST_NAME = "firstName";
	protected static final String RESUMES_LAST_NAME = "lastName";
	protected static final String RESUMES_EDUCATION = "education";
	protected static final String RESUMES_SCHOOL_TITLE = "schoolTitle";
	protected static final String RESUMES_SCHOOL_CLASS = "schoolClass";
	protected static final String RESUMES_MAJOR = "major";
	protected static final String RESUMES_WORK_EXPERIENCE = "workExperience";
	protected static final String RESUMES_EMPLOYER = "employer";
	protected static final String RESUMES_START_DATE = "startDate";
	protected static final String RESUMES_END_DATE = "endDate";
	protected static final String RESUMES_STUDENT_SKILLS = "studentSkills";
	protected static final String RESUMES_EXTRA_CURRICULAR = "extraCurricular";

	/**
	 * Internship definitions
	 */
	protected static final String INTERNSHIPS_FILE_NAME = "JSON/internships.json";
	protected static final String INTERNSHIPS_ID = "id";
	protected static final String INTERNSHIPS_TITLE = "title";
	protected static final String INTERNSHIPS_EMPLOYER = "employer";
	protected static final String INTERNSHIPS_DESCRIPTION = "description";
	protected static final String INTERNSHIPS_REQUIRED_SKILLS = "requiredSkills";
	protected static final String INTERNSHIPS_START_DATE = "startDate";
	protected static final String INTERNSHIPS_END_DATE = "endDate";
	protected static final String INTERNSHIPS_HOURS_PER_DAY = "hoursPerDay";
	protected static final String INTERNSHIPS_EXPERATION_DATE = "experationDate";
	protected static final String INTERNSHIPS_SALARY = "salary";
	protected static final String INTERNSHIPS_SALARY_VALUE = "salaryValue";
	protected static final String INTERNSHIPS_SALARY_LOWER = "lowRange";
	protected static final String INTERNSHIPS_SALARY_UPPER = "highRange";
	protected static final String INTERNSHIPS_SALARY_TYPE = "salaryType";
	protected static final String INTERNSHIPS_APPLICATIONS = "applications";
}
