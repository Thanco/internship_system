package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class Internship {
    private UUID id;
    private String title;
    private String employer;
    private String description;
    private ArrayList<String> requiredSkills;
    private LocalDate startDate;
    private LocalDate endDate;
    private int hoursPerDay;
    private LocalDate expirationDate;
    private SalaryType salaryType;
    private ArrayList<Resume> applications;

    /**
     * Instantiates an Internship object with given UUID. Used for loading from JSON
     * into the program.
     * 
     * @param id             the uuid of the internship offer.
     * @param title          the title as string.
     * @param employer       the employer name as a string.
     * @param description    the description of the internship offer.
     * @param requiredSkills the skills required for the internship
     * @param startDate      the date when the internship is supposed to start.
     * @param endDate        the date when the internship is supposed to end.
     * @param hoursPerDay    the hours the student is supposed to work.
     * @param expirationDate the date when the offer expires.
     * @param salaryType     the type of the salary.
     * @param applications   the applications already received.
     */
    public Internship(UUID id, String employer, String title, String description, ArrayList<String> requiredSkills,
            LocalDate startDate, LocalDate endDate, int hoursPerDay, LocalDate expirationDate,
            SalaryType salaryType, ArrayList<Resume> applications) {
        this.id = id;
        this.title = title;
        this.employer = employer;
        this.description = description;
        this.requiredSkills = requiredSkills;
        this.startDate = startDate;
        this.endDate = endDate;
        this.hoursPerDay = hoursPerDay;
        this.expirationDate = expirationDate;
        this.salaryType = salaryType;
        this.applications = applications;
    }

    /**
     * Instantiates an Internship object and generates a UUID. Supposed to be used
     * when creating a completely new user.
     * 
     * @param title          the title as string.
     * @param employer       the employer name as a string.
     * @param description    the description of the internship offer.
     * @param requiredSkills the skills required for the internship
     * @param startDate      the date when the internship is supposed to start.
     * @param endDate        the date when the internship is supposed to end.
     * @param hoursPerDay    the hours the student is supposed to work.
     * @param expirationDate the date when the offer expires.
     * @param salaryType     the type of the salary.
     */
    public Internship(String title, String employer, String description, ArrayList<String> requiredSkills,
            LocalDate startDate, LocalDate endDate, int hoursPerDay, int endHour, LocalDate expirationDate,
            SalaryType salaryType) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.employer = employer;
        this.description = description;
        this.requiredSkills = requiredSkills;
        this.startDate = startDate;
        this.endDate = endDate;
        this.hoursPerDay = hoursPerDay;
        this.expirationDate = expirationDate;
        this.salaryType = salaryType;
    }

    /**
     * Adds an application the the application list.
     * 
     * @param resume the application to add.
     */
    public void addApplication(Resume resume) {
        applications.add(resume);
    }

    /**
     * Method that prints the internship object in String format.
     * 
     * @return the internship in string format.
     */
    public String toString() {
        String output = employer;
        output += "\n	" + title;

        output += "\nRequired Skills: ";
        for (String skill : requiredSkills) {
            output += skill;
        }

        output += "\nDescription:";
        output += description;
        output += "\nPay: " + salaryType.getSalary();
        output += "\nSchedule:";
        output += "\n   Start Day: " + startDate.getMonthValue() + "/" + startDate.getDayOfMonth() + "/"
                + startDate.getYear();
        output += "\n   End Day: " + endDate.getMonthValue() + "/" + endDate.getDayOfMonth() + "/" + endDate.getYear();
        output += "\n   Hours per Day: " + hoursPerDay;
        output += "\nOpen Until: " + expirationDate.getMonthValue() + "/" + expirationDate.getDayOfMonth() + "/"
                + expirationDate.getYear();

        return output;

    }

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

    public ArrayList<String> getRequiredSkills() {
        return this.requiredSkills;
    }

    public void setRequiredSkills(ArrayList<String> requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setHoursPerDay(int hoursPerDay) {
        this.hoursPerDay = hoursPerDay;
    }

    public LocalDate getExpirationDate() {
        return this.expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getSalary() {
        return this.salaryType.getSalary();
    }

    public void setSalaryType(SalaryType salaryType) {
        this.salaryType = salaryType;
    }

    public ArrayList<Resume> getApplications() {
        return this.applications;
    }

    public int getHoursPerDay() {
        return this.hoursPerDay;
    }

    public String toStringShort() {
        return "<<Display Internship Short>>";
    }
}
