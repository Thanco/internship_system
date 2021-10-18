
public class education {

	private String schoolTitle;
	private TimeUnit enrollmentLength;
	private SchoolYear schoolClass;
	private String major;
	
	public education() 
	{
		
	}
	
	public education(String schoolTitle, TimeUnit enrollmentLength, SchoolYear schoolClass, resume resume)
	{
		try 
		{
			
		}
		catch(DimensionException e)
		{
			System.out.println(e.getMessage());
		}
	}

	public String getSchoolTitle() {
		return schoolTitle;
	}

	public void setSchoolTitle(String schoolTitle) {
		this.schoolTitle = schoolTitle;
	}

	public TimeUnit getEnrollmentLength() {
		return enrollmentLength;
	}

	public void setEnrollmentLength(TimeUnit enrollmentLength) {
		this.enrollmentLength = enrollmentLength;
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
		
		return "needs to be coded";
	}
	
}
