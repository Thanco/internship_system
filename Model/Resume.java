package Model;

import java.util.ArrayList;
import java.util.UUID;

public class Resume {

	private UUID uuid;
	private UUID ownerUuid;
	private String firstName;
	private String lastName;
	private Education education;
	private ArrayList<String> studentSkills;
	private ArrayList<WorkExperience> workExperienceList;
	private ArrayList<String> extraCirricularList;
	
	/**
     * Instantiates a Resume object with given resume uuid, owner uuid, first name, last name, education, student skills array, 
	 * work experience array, and extra cirricular list array from JSON
     * 
     * @param uuid             the uuid of the internship offer.
     * @param ownerUUID          the title as string.
     * @param firstName       the employer name as a string.
     * @param lastName    the description of the internship offer.
     * @param education the skills required for the internship
     * @param studentSkills      the date when the internship is supposed to start.
     * @param workExperienceList        the date when the internship is supposed to end.
     * @param extraCirricularList    the hours the student is supposed to work.
	 * 
     */
	public Resume(UUID uuid, UUID ownerUuid, String firstName, String lastName, Education education, ArrayList<String> studentSkills, 
			ArrayList<WorkExperience> workExperienceList, ArrayList<String> extraCirricularList ) {
		this.ownerUuid = ownerUuid;
		this.uuid = uuid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.education = education;
		this.studentSkills = studentSkills;
		this.workExperienceList = workExperienceList;
		this.extraCirricularList = extraCirricularList;	
	}
	
	/**
     * Instantiates a Resume object with given params for a new student
     * 
     * @param ownerUuid          	the ownerUuid as uuid.
     * @param firstName       		the first name as a string.
	 * @param lastName       		the last name as a string.
	 * @param education       		the user's education as an education object.
	 * 
     */
	public Resume(UUID ownerUuid, String firstName, String lastName, Education education) {
		
		this.uuid = UUID.randomUUID();
		this.ownerUuid = ownerUuid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.education = education;
		this.studentSkills = new ArrayList<String>();
		this.workExperienceList = new ArrayList<WorkExperience>();
		this.extraCirricularList = new ArrayList<String>();
	}
	
	public UUID getUuid() {
		return uuid;
	}

	public UUID getOwnerUUID() {
		return ownerUuid;
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

	public ArrayList<WorkExperience> getWorkExperienceList() {
		return workExperienceList;
	}

	public void setWorkExperienceList(ArrayList<WorkExperience> workExperienceList) {
		this.workExperienceList = workExperienceList;
	}

	public ArrayList<String> getExtraCirricularList() {
		return extraCirricularList;
	}

	public void setExtraCirricularList(ArrayList<String> extraCirricularList) {
		this.extraCirricularList = extraCirricularList;
	}

	public void addWorkExperience(WorkExperience workExperience) {
		
		if (workExperienceList.size() >= 3) {
			
			workExperienceList.remove(2);
			workExperienceList.add(workExperience);
			sortWorkExperienceList();
		}
		
		else {
			
			workExperienceList.add(workExperience);
			sortWorkExperienceList();
		}
		
	}
	
	public void sortWorkExperienceList() {
		
		for(int i = 0; i < workExperienceList.size()-1; i++) {
			
			if(workExperienceList.get(i).getStart().compareTo(workExperienceList.get(i+1).getStart()) > 0) {
				
				WorkExperience temp = workExperienceList.get(i);
				workExperienceList.set(i, workExperienceList.get(i+1));
				workExperienceList.set(i+1, temp);
			}
		}
	}
	
	public void addExtraCirricular(String extraCirricular) {
		
		extraCirricularList.add(extraCirricular);
	}
	
	public String toStringShort() {		
		return "Name: " + this.getFirstName() + " " + this.getLastName() + " : " + this.getEducation().getMajor();
	}
	
	public String toStringLong() {
		String ret = "Name: " + this.getFirstName() + " " + this.getLastName() + 
					"\nEducation:\n" + this.getEducation().toString();
		if (this.getStudentSkills().size() > 0) {
			ret += "\nSkills:\n\t";
			for (String skill : this.getStudentSkills()) {
				ret += skill + ", ";
			}
			ret = ret.substring(0, ret.length()-2);
		}
		if (this.getWorkExperienceList().size() > 0) {
			ret += "\nWork Experience:";
			for (WorkExperience experiece : this.getWorkExperienceList()) {
				ret += "\n" + experiece.toString();
			}
		}
		if (this.getExtraCirricularList().size() > 0) {
			ret += "\nExtra Curricular Activities:";
			for (String extraCiccicular : this.getExtraCirricularList()) {
				ret +=  "\n\t" + extraCiccicular;
			}
		}
		return ret;
	}

}
