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
	
	public void customize() {
		
	}
	
	public String toString() {
		
		return "Education: /n" + this.schoolTitle + "-" + this.schoolClass + "-" + this.schoolClass;
	}
	
}
