package Model;

public class Education {

	private String schoolTitle;
	private SchoolYear schoolClass;
	private String major;
	
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
