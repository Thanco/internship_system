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

	public String getSchoolTitle() {
		return schoolTitle;
	}

	public void setSchoolTitle(String schoolTitle) {
		this.schoolTitle = schoolTitle;
	}

	public SchoolYear getSchoolClass() {
		return schoolClass;
	}

	public void setSchoolClass(SchoolYear schoolClass) {
		this.schoolClass = schoolClass;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

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
	
	public String toString() {
		return "\tSchool: " + this.getSchoolTitle() + 
				"\n\tYear: " + this.getSchoolClassString() + 
				"\n\tMajor: " + this.getMajor();
	}
	
}
