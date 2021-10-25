package Model;

import java.text.ParseException;
import java.util.ArrayList;

public class Resume {

	private String firstName;
	private String lastName;
	private Education education;
	private WorkExperience workExpierence;
	private ArrayList<String> studentSkills;
	private ExtraCirricular extraCirricular;
	
	public Resume(String firstName, String lastName, String school, SchoolYear schoolClass, String major, String company, 
			int length, String start, String end, ArrayList<String> studentSkills, String extraCirricular) throws ParseException {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.education = new Education(school, schoolClass, major);
		this.workExpierence = new WorkExperience(company, length, start, end);
		this.studentSkills = studentSkills;
		this.extraCirricular = new ExtraCirricular(extraCirricular);
		//this.currentWork = new CurrentWork();
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

	public WorkExperience getWorkExpierence() {
		return workExpierence;
	}

	public void setWorkExpierence(WorkExperience workExpierence) {
		this.workExpierence = workExpierence;
	}

	public ArrayList<String> getStudentSkills() {
		return studentSkills;
	}

	public void setStudentSkills(ArrayList<String> studentSkills) {
		this.studentSkills = studentSkills;
	}

	public ExtraCirricular getExtraCirricular() {
		return extraCirricular;
	}

	public void setExtraCirricular(ExtraCirricular extraCirricular) {
		this.extraCirricular = extraCirricular;
	}
	
}
