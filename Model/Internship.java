package Model;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Internship {
    private UUID id;
    private String title;
    private String employer;
    private String description;
    private String requiredStrings;
    private WorkSchedule workschedule;
    private Date expirationDate;
    private SalaryType salaryType;
    private ArrayList<Resume> applications;

    public Internship(UUID id, String title, String employer, String description, String requiredSkills, WorkSchedule workSchedule, Date expirationDate, SalaryType salaryType){
        return;
    }

    public boolean addApplication(Resume resume){
        return true;
    }

    private Resume getResumesWithSkill(String skill){
        return new Resume();
    }

    public String toString(){
        return ""
 ;   }
    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmployer() {
        return this.employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequiredStrings() {
        return this.requiredStrings;
    }

    public void setRequiredStrings(String requiredStrings) {
        this.requiredStrings = requiredStrings;
    }

    public WorkSchedule getWorkschedule() {
        return this.workschedule;
    }

    public void setWorkschedule(WorkSchedule workschedule) {
        this.workschedule = workschedule;
    }

    public Date getExpirationDate() {
        return this.expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public SalaryType getSalaryType() {
        return this.salaryType;
    }

    public void setSalaryType(SalaryType salaryType) {
        this.salaryType = salaryType;
    }

    public ArrayList<Resume> getApplications() {
        return this.applications;
    }

    public void setApplications(ArrayList<Resume> applications) {
        this.applications = applications;
    }

}
