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
        applications = new ArrayList<>();
    }

    /**
     * Adds an application to the application list.
     * 
     * @param resume the application to add.
     */
    public void addApplication(Resume resume) {
        applications.add(resume);
    }

    /**
     * Removes an application from the application list
     * @param resume the application to remove
     */
    public void removeApplication(Resume resume) {
        applications.remove(resume);
    }

    /**
     * Method that prints the internship object in String format.
     * 
     * @return the internship in string format.
     */
    public String toString() {
        String output = employer;
        output += "\n\t" + title;

        output += "\nRequired Skills: ";
        for (String skill : requiredSkills) {
            output += skill + ", ";
        }
        output = output.substring(0, output.length() - 2);
        output += "\nDescription: ";
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

    /**
     * Gets the id.
     * @return the id.
     */
    public UUID getId() {
        return this.id;
    }

    /**
     * Sets the id.
     * @param id to set
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Gets the title.
     * @return the title to set
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Sets the title.
     * @param title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the employer.
     * @return the employer
     */
    public String getEmployer() {
        return this.employer;
    }

    /**
     * Sets the employer.
     * @param employer to set.
     */
    public void setEmployer(String employer) {
        this.employer = employer;
    }

    /**
     * Gets the description.
     * @return the description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the description.
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the required skills.
     * @return the required skills.
     */
    public ArrayList<String> getRequiredSkills() {
        return this.requiredSkills;
    }

    /**
     * Sets the required skills.
     * @param requiredSkills the required skills to set
     */
    public void setRequiredSkills(ArrayList<String> requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    /**
     * Sets the start date.
     * @param startDate to set
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets the start date. 
     * @return the start date
     */
    public LocalDate getStartDate() {
        return this.startDate;
    }

    /**
     * Gets the end date.
     * @return the end date
     */
    public LocalDate getEndDate() {
        return this.endDate;
    }

    /**
     * Sets the end date.
     * @param endDate to set
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * Sets the hours per day.
     * @param hoursPerDay to set
     */
    public void setHoursPerDay(int hoursPerDay) {
        this.hoursPerDay = hoursPerDay;
    }

    /**
     * Gets expiration date.
     * @return the expiration date
     */
    public LocalDate getExpirationDate() {
        return this.expirationDate;
    }

    /**
     * Sets thte expiration date.
     * @param expirationDate to set
     */
    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    /**
     * Gets the salary.
     * @return the salary.
     */
    public String getSalary() {
        return this.salaryType.getSalary();
    }

    /**
     * Sets the salary type.
     * @param salaryType to set.
     */
    public void setSalaryType(SalaryType salaryType) {
        this.salaryType = salaryType;
    }

    /**
     * Gets the applications.
     * @return the application
     */
    public ArrayList<Resume> getApplications() {
        return this.applications;
    }

    /**
     * Gets the hours per day.
     * @return the hours per day
     */
    public int getHoursPerDay() {
        return this.hoursPerDay;
    }

    /**
     * Returns the employer short string.
     * @return short string of the employer.
     */
    public String toStringShort() {
        return "" + this.employer + "\t| " + this.title + "\t| " + this.salaryType.getSalary() + "\t| " + this.expirationDate.toString();
    }
}
