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
	
	/**
     * get the uuid of resume.
     * @return uuid.
     */
	public UUID getUuid() {
		return uuid;
	}

	/**
     * get the uuid of the owner of the resume.
     * @return uuid of the owner of the resume.
     */
	public UUID getOwnerUUID() {
		return ownerUuid;
	}

	/**
     * get the first name on resume.
     * @return first name.
     */
	public String getFirstName() {
		return firstName;
	}

	/**
     * set the first name on resume.
     * @param String firstname.
     */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
     * get the last name on resume.
     * @return last name.
     */
	public String getLastName() {
		return lastName;
	}

	/**
     * set the uuid of resume.
     * @retrun uuid.
     */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
     * get the education of resume.
     * @return Education education.
     */
	public Education getEducation() {
		return education;
	}

	/**
     * set the uuid of resume.
     * @param Education education.
     */
	public void setEducation(Education education) {
		this.education = education;
	}

	/**
     * get the skills on resume.
     * @return ArrayList<String> studentskills.
     */
	public ArrayList<String> getStudentSkills() {
		return studentSkills;
	}

	/**
     * set the skills on resume.
     * @return ArrayList<String> studentskills.
     */
	public void setStudentSkills(ArrayList<String> studentSkills) {
		this.studentSkills = studentSkills;
	}

	/**
	 * gets the Work Experience array list
	 * @return ArrayList<WorkExperience> workExperienceList
	 */
	public ArrayList<WorkExperience> getWorkExperienceList() {
		return workExperienceList;
	}

	/**
	 * sets work experience list to passed in array
	 * @param ArrayList<WorkExperience> workExperienceList
	 */
	public void setWorkExperienceList(ArrayList<WorkExperience> workExperienceList) {
		this.workExperienceList = workExperienceList;
	}

	/**
	 * gets extra cirricularList array list
	 * @return ArrayList<String> extraCirricularList
	 */
	public ArrayList<String> getExtraCirricularList() {
		return extraCirricularList;
	}

	/**
	 * sets extra cirricularList array list
	 * @param ArrayList<String> extraCirricularList
	 */
	public void setExtraCirricularList(ArrayList<String> extraCirricularList) {
		this.extraCirricularList = extraCirricularList;
	}

	/**
	 * adds work exp object to WorkExp list
	 * @param WorkExperience workExperience
	 */
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
	
	/**
	 * sorts workExperience list and removes the oldest object in terms of dates
	 * @return void
	 */
	public void sortWorkExperienceList() {
		
		for(int i = 0; i < workExperienceList.size()-1; i++) {
			
			if(workExperienceList.get(i).getStart().compareTo(workExperienceList.get(i+1).getStart()) > 0) {
				
				WorkExperience temp = workExperienceList.get(i);
				workExperienceList.set(i, workExperienceList.get(i+1));
				workExperienceList.set(i+1, temp);
			}
		}
	}
	
	/**
	 * addsextraCirricular object to extraCirricular list
	 * @param String extraCirricular
	 */
	public void addExtraCirricular(String extraCirricular) {
		
		extraCirricularList.add(extraCirricular);
	}
	
	/**
	 * used to make Resume a string, displays name and major
	 * @returns resume as strings
	 */
	public String toStringShort() {		
		return "Name: " + this.getFirstName() + " " + this.getLastName() + " : " + this.getEducation().getMajor();
	}
	
	/**
	 * used to make resume a string, displays entire resume
	 * @returns resume as strings
	 */
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
