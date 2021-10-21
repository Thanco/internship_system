public class Resume {

	private String firstName;
	private String lastName;
	private Education education;
	private workExpierence;
	private ArrayList<String> studentSkills;
	private String extraCirricular;
	private String currentEmployment;
	
	public resume() {
		//defaults
	}
	
	public resume(String firstName, String lastName, ArrayList<String> studentSkills) {
		try 
		{
			//
		}
		catch(DimensionException e)
		{
			//
		}
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Education getEducation() {
		return education;
	}

	public void setEducation(Education education) {
		this.education = education;
	}

	public ArrayList<String> getStudentSkills() {
		return studentSkills;
	}

	public void setStudentSkills(ArrayList<String> studentSkills) {
		this.studentSkills = studentSkills;
	}

	public String getExtraCirricular() {
		return extraCirricular;
	}

	public void setExtraCirricular(String extraCirricular) {
		this.extraCirricular = extraCirricular;
	}

	public String getCurrentEmployment() {
		return currentEmployment;
	}

	public void setCurrentEmployment(String currentEmployment) {
		this.currentEmployment = currentEmployment;
	}
	
	public String toString() {
			
			return "needs to be coded";
	}
	
}
