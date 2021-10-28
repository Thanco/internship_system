package Model;

import java.util.ArrayList;
import java.util.UUID;

public class Resume {

	private UUID uuid;
	private UUID uuidRes;
	private String firstName;
	private String lastName;
	private Education education;
	private ArrayList<String> studentSkills = new ArrayList<String>();
	private ArrayList<WorkExperience> workExperienceList = new ArrayList<WorkExperience>();
	private ArrayList<ExtraCirricular> extraCirricularList = new ArrayList<ExtraCirricular>();
	
	public Resume(UUID uuid, UUID uuidRes, String firstName, String lastName, Education education, ArrayList<String> studentSkills, 
			ArrayList<WorkExperience> workExperienceList, ArrayList<ExtraCirricular> extraCirricularList ) {
		
		this.uuid = uuid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.education = education;
		this.studentSkills = studentSkills;
		this.workExperienceList = workExperienceList;
		this.extraCirricularList = extraCirricularList;	
	}
	
	public Resume(UUID uuid, String firstName, String lastName, Education education, ArrayList<String> studentSkills, 
			ArrayList<WorkExperience> workExperienceList, ArrayList<ExtraCirricular> extraCirricularList ) {
		
		this.uuid = uuid;
		this.uuidRes = UUID.randomUUID();
		this.firstName = firstName;
		this.lastName = lastName;
		this.education = education;
		this.studentSkills = studentSkills;
		this.workExperienceList = workExperienceList;
		this.extraCirricularList = extraCirricularList;	
	}
	
	public UUID getUuid() {
		return uuid;
	}

	public UUID getUuidRes() {
		return uuidRes;
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

	public ArrayList<ExtraCirricular> getExtraCirricularList() {
		return extraCirricularList;
	}

	public void setExtraCirricularList(ArrayList<ExtraCirricular> extraCirricularList) {
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
	
	public void addExtraCirricular(ExtraCirricular extraCirricular) {
		
		extraCirricularList.add(extraCirricular);
	}

}
