package Model;

public class Education {

	private String schoolTitle;
	private SchoolYear schoolClass;
	private String major;
	
	/**
     * Instantiates an Education object with given school name, school year and user major
     * 
     * @param schoolTitle          	the title of the school as string.
     * @param schoolClass       	the school classing as a schoolyear enumeration.
	 * @param major       			the user major as a string.
     */
	public Education(String schoolTitle, SchoolYear schoolClass, String major)
	{
		this.schoolTitle = schoolTitle;
		this.schoolClass = schoolClass;
		this.major = major;
	}

	/**
	 * gets the title of the school
	 * @return String schoolTitle
	 */
	public String getSchoolTitle() {
		return schoolTitle;
	}

	/**
	 * sets the title of the school
	 * @param schoolTitle
	 */
	public void setSchoolTitle(String schoolTitle) {
		this.schoolTitle = schoolTitle;
	}

	/**
	 * gets the users class
	 * @returns schoolYear 
	 */
	public SchoolYear getSchoolClass() {
		return schoolClass;
	}

	/**
	 * sets the school class to enumeration
	 * @param SchoolYear schoolClass
	 */
	public void setSchoolClass(SchoolYear schoolClass) {
		this.schoolClass = schoolClass;
	}

	/**
	 * gets users major
	 * @return String major
	 */
	public String getMajor() {
		return major;
	}

	/**
	 * sets major of user
	 * @param String major
	 */
	public void setMajor(String major) {
		this.major = major;
	}

	/**
	 * gets school class as a string
	 * @returns String school class
	 */
	public String getSchoolClassString() {
		switch (this.getSchoolClass()) {
			case FRESHMAN:
				return "Freshman";
			case SOPHOMORE:
				return "Sophomore";
			case JUNIOR:
				return "Junior";
			case SENIOR:
				return "Senior";
			default:
				return "None";
		}
	}
	
	/**
	 * gets strings to display education object
	 * @return String of all elements of Education
	 */
	public String toString() {
		return "\tSchool: " + this.getSchoolTitle() + 
				"\n\tYear: " + this.getSchoolClassString() + 
				"\n\tMajor: " + this.getMajor();
	}
	
}
