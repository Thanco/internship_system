package UserInterface;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;
import java.util.regex.Pattern;

import JSON.DataWriter;
import Model.*;

/**
 * Used by UI to acces other classes
 */
public class InternshipApplication {
    private User currentUser;
    private InternshipList internshipList;
    private UserList userList;

    /**
     * Constructor
     */
    public InternshipApplication() {
        this.internshipList = InternshipList.getInstance();
        this.userList = UserList.getInstance();
    }

    /**
     * Checks user list for matching email/password and saves that user to the
     * instance variable
     * 
     * @param email    Entered email
     * @param password Entered Password
     * @return True/False if login is succesful
     */
    public boolean login(String email, String password) {
        this.currentUser = userList.loginUser(email, password);
        if (this.currentUser == null) {
            return false;
        }
        return true;
    }

    /**
     * Gets the current users UUID
     * 
     * @return current users UUID
     */
    public UUID getCurrent() {
        return currentUser.getId();
    }

    /**
     * Saves saves all data to jsons
     */
    public void end() {

    }

    /**
     * Adds a new user to the arraylist of users Checks for valid entries (No white
     * space, valid email, etc..)
     * 
     * @param first    Users first name
     * @param last     Users last name
     * @param email    Users email
     * @param password Users password
     * @param type     Type of user: 0 = Student, 1 = Employer, 2 = Admin
     * @return Returns true/false if account was/wasnot created
     */
    public boolean createUser(String first, String last, String email, String password, int type) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
        if (!pattern.matcher(email).matches() || first.isEmpty() || last.isEmpty()) {
            return false;
        }

        this.currentUser = userList.createUser(first, last, email, password, type);
        if (this.currentUser == null) {
            return false;
        }
        return true;
    }

    /**
     * @return 0 for Student
     * @return 1 for Employer
     * @return 2 for Admin
     * @return else -1
     */
    public int userType() {
        return userType(this.currentUser.getId());
    }

    /**
     * @param userId UUID of user
     * @return 0 for Student
     * @return 1 for Employer
     * @return 2 for Admin
     * @return else -1
     */
    public int userType(UUID userId) {
        User user = userList.getUserById(userId);
        if (user instanceof Student) {
            return 0;
        } else if (user instanceof Employer) {
            return 1;
        } else if (user instanceof Admin) {
            return 2;
        } else {
            return -1;
        }
    }

    /**
     * Creates resume for current user
     * 
     * @params String schoolTitle, int schoolClass, String major of user
     */
    public boolean createResume(String schoolTitle, int schoolClass, String major) {
        if (schoolTitle == null) {
            return false;
        }
        else {
            SchoolYear classEnum = null;
            switch (schoolClass) {
            case 0:
                classEnum = SchoolYear.FRESHMAN;
                break;
            case 1:
                classEnum = SchoolYear.SOPHOMORE;
                break;
            case 2:
                classEnum = SchoolYear.JUNIOR;
                break;
            case 3:
                classEnum = SchoolYear.SENIOR;
                break;
            default:
                return false;
            }
            Education newEducation = new Education(schoolTitle, classEnum, major);
            ((Student)currentUser).setResume(new Resume(currentUser.getId(), currentUser.getFirstName(), currentUser.getLastName(),
            newEducation));
            return true;
        }

    }

    /**
     * Add a new skill to the resume or removes if it is already there
     * 
     * @param skill the skill to add/remove
     */
    public void changeSkills(String skill) {

        User user = userList.getUserById(currentUser.getId());
        if (user instanceof Student) {
            Resume resTemp = ((Student) user).getResume();
            ArrayList<String> skillsTemp = resTemp.getStudentSkills();
        
            for(int i = 0; i < skillsTemp.size(); i++) {

                if (skillsTemp.get(i).equalsIgnoreCase(skill)) {

                    skillsTemp.remove(i);
                }

                else {
                    
                    skillsTemp.add(skill);
                }
            }

            ((Student) user).getResume().setStudentSkills(skillsTemp);
        
        }

    }

    /**
     * Overwrites the existing education for a resume
     * 
     * @param school
     * @param major
     * @param year
     */
    public void changeEducation(String school, String major, String year) {

        User user = userList.getUserById(currentUser.getId());
        if (user instanceof Student) {
            
            SchoolYear classEnum = SchoolYear.valueOf(year);
            Education eduTemp = new Education(school, classEnum, major);
            ((Student) user).getResume().setEducation(eduTemp);
        }

    }

    /**
     * Add a new extra-curricular to the resume or removes if it is already there
     * 
     * @param activity the activity to add/remove
     */
    public void changeExtra(String activity) {

        User user = userList.getUserById(currentUser.getId());
        if (user instanceof Student) {
            Resume resTemp = ((Student) user).getResume();
            ArrayList<String> extraCirTemp = resTemp.getExtraCirricularList();
        
            for(int i = 0; i < extraCirTemp.size(); i++) {

                if (extraCirTemp.get(i).equalsIgnoreCase(activity)) {

                    extraCirTemp.remove(i);
                }

                else {
                    
                    extraCirTemp.add(activity);
                }
            }

            ((Student) user).getResume().setExtraCirricularList(extraCirTemp);
        
        }
    }

    /**
     * Adds a work experience to a resume
     * 
     * @param company
     * @param start
     * @param end
     */
    public void changeWork(String company, String start, String end) {
        
        User user = userList.getUserById(currentUser.getId());
        if (user instanceof Student) {
            
            WorkExperience workTemp = new WorkExperience(company, start, end);
            ((Student) user).getResume().addWorkExperience(workTemp);
        }
    }

    /**
     * Gets a list of UUIDs that the current user can add a rating to
     * 
     * @return for student return an array list of past employer UUIDs
     * @return for employer return an array list of past employee UUIDs
     */
    public ArrayList<UUID> getRatables() {
        if (currentUser instanceof Student) {
            return ((Student) currentUser).getFormerEmployers();
        } else if (currentUser instanceof Employer) {
            return ((Employer) currentUser).getEmployees();
        }

        return null;
    }

    /**
     * Adds rating to a users array list of ratings
     * 
     * @param user   The UUID of a user
     * @param rating the entered rating
     */
    public void addRating(UUID userId, int rating) {
        User user = userList.getUserById(userId);
        if (user instanceof Student) {
            Student student = (Student) user;
            student.addRating(rating);
        } else if (user instanceof Employer) {
            Employer employer = (Employer) user;
            employer.addRating(rating);
        }
    }

    /**
     * gets a full list of internships
     * 
     * @return The full list of Internships
     */
    public ArrayList<Internship> getInternships() {
        return this.internshipList.getInternships();
    }

    /**
     * Searches the internship list using a specified keyword
     * 
     * @param entry A search criteria ented by the user
     * @return An array list that contains any internships with info that matches
     *         the entry (Company, Position, or Skill)
     */
    public ArrayList<Internship> getInternships(String keyword) {
        return internshipList.getInternshipByKeyword(keyword);
    }

    /**
     * Gets an all of the internships created by a specific user
     * 
     * @param user The users UUID
     * @return An array list of internships
     */
    public ArrayList<Internship> getInternships(UUID userId) {
        User user = userList.getUserById(userId);
        if (user instanceof Employer) {
            Employer employer = (Employer) user;
            ArrayList<UUID> internshipIds = employer.getInternshipList();
            ArrayList<Internship> internships = new ArrayList<>();
            for (UUID internship : internshipIds) {
                internships.add(internshipList.getInternshipById(internship));
            }

            return internships;
        }
        return null;
    }

    /**
     * Gets the full user list
     * 
     * @return The user ArrayList
     */
    public ArrayList<User> getUsers() {
        return this.userList.getUsers();
    }

    /**
     * Gets all users of specific type
     * 
     * @param type 0 = Sudent, 1 = Employer
     * @return An array list of users
     */
    public ArrayList<User> getUsers(int type) {
        return this.userList.getUsersByType(type);
    }

    /**
     * Searches for users with profiles containing a given keyword
     * 
     * @param keyword word to search for
     * @return an array list of users
     */
    public ArrayList<User> getUsers(String keyword) {
        return null;
    }

    /**
     * creates an array list of users from the array list of UUIDs returns null if
     * array list is empty or current user is not an employer
     * 
     * @return An array list of an employers employees
     */
    public ArrayList<User> getEmployees() {
        return getEmployees(this.currentUser.getId());
    }

    /**
     * Returns an array list of employees for a given employer
     * 
     * @param user The uuid of the user to get employees of
     * @return an array list of users
     * @return null if given user is not an employer or has no employees
     */
    public ArrayList<User> getEmployees(UUID userId) {
        User user = userList.getUserById(userId);
        if (!(user instanceof Employer)) {
            return null;
        }
        Employer employer = (Employer) user;
        ArrayList<UUID> employeeIds = employer.getEmployees();

        ArrayList<User> employees = new ArrayList<>();
        for (UUID employeeId : employeeIds) {
            employees.add(userList.getUserById(employeeId));
        }

        return employees;
    }

    /**
     * Gets the current user
     * 
     * @return The current User
     */
    public User getUser() {
        return currentUser;
    }

    /**
     * Finds a user by UUID
     * 
     * @param user The UUID of the user to be returned
     * @return A User
     */
    public User getUser(UUID userId) {
        return userList.getUserById(userId);
    }

    /**
     * saves changes made to the currentUser to the user list
     */
    public void saveUser() {
        DataWriter.saveUsers();
        DataWriter.saveInternships();
    }

    /**
     * Saves updates to given user object in user list
     * 
     * @param user The modified User
     */
    public void saveUser(User user) {
        DataWriter.saveUsers();
    }

    /**
     * Changes current users email checks if email is valid and if email is already
     * used
     * 
     * @param email The new email
     * @return true if email is changed
     */
    public boolean updateEmail(String email) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
        if (!pattern.matcher(email).matches()) {
            return false;
        }
        this.currentUser.setEmail(email);
        return true;
    }

    /**
     * Changes current users password check for valid password
     * 
     * @param password new password
     * @return true if password is changed
     */
    public boolean updatePassword(String password) {
        this.currentUser.setPassword(password);
        return true;
    }

    /**
     * Changes current users name
     * 
     * @param first New first name
     * @param last  New last name
     * @return true if name is changed
     */
    public boolean updateName(String first, String last) {
        if (first.isEmpty() || last.isEmpty()) {
            return false;
        }

        this.currentUser.setFirstName(first);
        this.currentUser.setlastName(last);
        return true;
    }

    /**
     * gets a list of resumes from an internship
     * 
     * @param internship
     * @return
     */
    public ArrayList<Resume> getApplications(UUID internshipId) {
        return internshipList.getInternshipById(internshipId).getApplications();
    }

    /**
     * returns the current users resume
     * 
     * @return current users resume
     */
    public Resume getResume() {
        return getResume(this.currentUser.getId());
    }

    /**
     * Returns a specific users Resume
     */
    public Resume getResume(UUID userId) {
        User user = userList.getUserById(userId);
        if (user instanceof Student) {
            return ((Student) user).getResume();
        }
        return null;
    }

    /**
     * Adds the current users resume to an internship
     * 
     * @param Id The UUID of the internship being applied to
     */
    public boolean apply(UUID Id) {
        return false;
    }

    /**
     * Removes a User from the UserList
     * 
     * @param user The UUID of the user to be removed
     */
    public void removeUser(UUID userId) {
        userList.removeUserById(userId);
    }

    /**
     * Accepts Applicant Adds employer to student ratables and employer to students
     * ratables
     * 
     * @param resume
     */
    public void acceptApplication(Resume resume) {
        
    }

    /**
     * Decline Applicant
     * 
     * @param resume
     */
    public void declineApplication(Resume resume) {

    }

    /**
     * delets a users resume
     * 
     * @param resume
     */
    public void deleteResume(UUID user) {
       
        User userTemp = userList.getUserById(user);
        if(userTemp instanceof Student) {
            ((Student) userTemp).setResume(null);
        }
    }

    /**
     * Removes an internship from the internship list
     * 
     * @param id The internships UUID
     */
    public void removeInternship(UUID id) {

        InternshipList list = InternshipList.getInstance();
        list.removeInternshipById(id);
        
    }

    /**
     * Creates an internship using given values
     * 
     * @param title of the internship
     * @param employer of the internship
     * @param description of the internship
     * @param requiredSkills for the internship
     * @param startDate of the internship
     * @param endDate of the internship
     * @param hoursPerDay of the internship
     * @param endHour of the internship
     * @param expirationDate of the internship
     * @param salaryType of the internship
     */
    public void createInternship(String title, String employer, String description, String requiredSkills,
            String startDate, String endDate, int hoursPerDay, int endHour, String expirationDate, String salaryType) {

        if (this.currentUser instanceof Employer) {
            Employer user = (Employer) this.currentUser;
            String[] startArray = startDate.split("/");
            String[] endArray = endDate.split("/");
            String[] expiration = expirationDate.split("/");
            ArrayList<String> skills = new ArrayList<String>(Arrays.asList(requiredSkills.split(",")));
            SalaryType salary;
            if (salaryType == null) {
                salary = new HiddenSalary();
            } else {
                salary = new FixedSalary(Integer.parseInt(salaryType));
            }
            user.createNewInternship(title, employer, description, skills, LocalDate.of(Integer.parseInt(startArray[2]), Integer.parseInt(startArray[0]),Integer.parseInt(startArray[1])), LocalDate.of(Integer.parseInt(endArray[2]), Integer.parseInt(endArray[0]),Integer.parseInt(endArray[1])), hoursPerDay, endHour, LocalDate.of(Integer.parseInt(expiration[2]), Integer.parseInt(expiration[0]),Integer.parseInt(expiration[1])), salary);

        }
    }

    /**
     * Returns the employer string for an internship
     * 
     * @param internshipId The internships UUID
     * @return Employer String
     */
    public String getEmployer(UUID internshipId) {
        return internshipList.getInternshipById(internshipId).getEmployer();
    }

    /**
     * Modifies an Internships employer object
     * 
     * @param internship The internship being modified
     * @param employer   The new employer string
     */
    public void changeEmployer(UUID internshipId, String employer) {
        internshipList.getInternshipById(internshipId).setEmployer(employer);
    }

    /**
     * Gets the job title from an internship
     * 
     * @param internshipId the id of the internship
     * @return title of the internsip
     */
    public String getTitle(UUID internshipId) {
        return internshipList.getInternshipById(internshipId).getTitle();
    }

    /**
     * Changes the job title of an internship
     * 
     * @param internshipId the id of the internship
     * @param title        the new title
     */
    public void changeTitle(UUID internshipId, String title) {
        internshipList.getInternshipById(internshipId).setTitle(title);
    }

    /**
     * Returns an internships arraylist of required skills
     * 
     * @param internshipId
     * @return
     */
    public ArrayList<String> getRequiredSkills(UUID internshipId) {
        return internshipList.getInternshipById(internshipId).getRequiredSkills();
    }

    /**
     * changes an internships required skills Adds entry if it is not included,
     * removes if it is
     * 
     * @param internship
     * @return
     */
    public void changeRequiredSkills(UUID internshipId, String skill) {
        String[] modifySkills = skill.split("\n");
        ArrayList<String> toAdd = new ArrayList<>();
        ArrayList<String> skills = internshipList.getInternshipById(internshipId).getRequiredSkills();
        for (int i = 0; i < modifySkills.length; i++) {
            if (!skill.contains(modifySkills[i])) {
                toAdd.add(modifySkills[i]);
            } else {
                skills.remove(modifySkills[i]);
            }
        }

        skills.addAll(toAdd);
    }

    /**
     * Converts string to int and stores, if param is null set to hidden
     * 
     * @param salary to change to.
     */
    public void changeSalary(UUID internshipId, String salary) {
        SalaryType salaryType;
        if (salary == null) {
            salaryType = new HiddenSalary();
        } else {
            salaryType = new FixedSalary(Integer.parseInt(salary));
        }
        internshipList.getInternshipById(internshipId).setSalaryType(salaryType);
    }

    /**
     * Changes the schedule details of the internship.
     * 
     * @param internshipId the id of the internship to modify.
     * @param start        the new start date.
     * @param end          the new end date.
     * @param hours        the new hours per day.
     */
    public void changeSchedule(UUID internshipId, String start, String end, int hours) {
        Internship internship = internshipList.getInternshipById(internshipId);
        String[] startArray = start.split("/");
        String[] endArray = end.split("/");
        internship.setHoursPerDay(hours);
        internship.setEndDate(LocalDate.of(Integer.parseInt(endArray[2]), Integer.parseInt(endArray[0]),
                Integer.parseInt(endArray[1])));
        internship.setStartDate(LocalDate.of(Integer.parseInt(startArray[2]), Integer.parseInt(startArray[0]),
                Integer.parseInt(startArray[1])));
    }

    /**
     * Searches an array of Resumes for a key word
     * 
     * @param resumes List to be searched
     * @param keyword The word to search for
     * @return An array list of containing they key word
     */
    public ArrayList<Resume> searchResumes(ArrayList<Resume> resumes, String keyword) {
        ArrayList<Resume> resumesWithKeyword = new ArrayList<>();
        for (Resume resume : resumes) {
            if (resume.toStringLong().contains(keyword)) {
                resumesWithKeyword.add(resume);
            }
        }

        return resumesWithKeyword;
    }

    /**
     * Promotes given user to admin
     * 
     * @param user
     */
    public void promoteUser(UUID user) {
        return;
    }

    /**
     * Gets the average overall rating of the current user
     * 
     * @return Avg rating
     */
    public double getRating() {
        return getRating(this.currentUser.getId());
    }

    /**
     * Gets the average overall rating of a given user
     * 
     * @return Avg rating
     */
    public double getRating(UUID userId) {
        User user = userList.getUserById(userId);
        if (user instanceof Student) {
            return ((Student) user).getAverageRating();
        } else if (user instanceof Employer) {
            return ((Employer) user).getAverageRating();
        } else {
            return 0;
        }
    }

    /**
     * Gets a users list of ratings
     * 
     * @param user The user to get ratings of
     * @return The users ratings
     */
    public int[] getRatings(UUID userId) {
        User user = userList.getUserById(userId);
        if (user instanceof Student) {
            return ((Student) user).getRatings().stream().mapToInt(i -> i).toArray();
        } else if (user instanceof Employer) {
            return ((Employer) user).getRatings().stream().mapToInt(i -> i).toArray();
        } else {
            return null;
        }
    }

    /**
     * Removes a specific rating from the users profile
     * 
     * @param userId The user whos rating is being modified
     * @param index  The index of the rating to be removed
     */
    public void removeRating(UUID userId, int index) {
        User user = userList.getUserById(userId);
        if (user instanceof Student) {
            ((Student) user).getRatings().remove(index);
        } else if (user instanceof Employer) {
            ((Employer) user).getRatings().remove(index);
        }
    }

    /**
     * Clears a users ratings.
     * 
     * @param userid is the id of the user whose ratings to clear
     */
    public void resetRating(UUID userId) {
        User user = userList.getUserById(userId);
        if (user instanceof Student) {
            ((Student) user).setRatings(new ArrayList<>());
        } else if (user instanceof Employer) {
            ((Employer) user).setRatings(new ArrayList<>());
        }
    }
}
