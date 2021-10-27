package UserInterface;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

import Model.Admin;
import Model.Employer;
import Model.Internship;
import Model.Resume;
import Model.Student;
import Model.User;

/**
 * Contains all user interfaces.
 * @author Wyatt Wilgus
 */
public class InternshipUI {
    public static void main(String args[]) {
        InternshipUI UI = new InternshipUI();
        UI.run();
    }

    private final int LINE_LENGTH = 80;
    private final int PAGE_LENGTH = 25;
    private final String OPTIONS = "UserInterface/Options.txt";
    private InternshipApplication application;
    private Scanner in = new Scanner(System.in);
    private String[] Options;

    /**
     * Runs initialization for UI
     */
    public void run() {
        fillOptions();
        application = new InternshipApplication();
        startUI();
    }

    /**
     * Prints an options menu and returns a char
     * @param A list of options to be printed (01072027)
     * @return The users entry
     */
    private char UIOptionsLine(String options) {
        Scanner read = new Scanner(options);
        String[] ops = read.nextLine().split(",");
        printDivider();
        for (int i = 0; i < options.length(); i = i + 2) {
            int index = Integer.parseInt(options.substring(i, i + 2)) - 1;
            System.out.print(Options[index] + "   ");
        }
        printDivider();
        System.out.print("Options: ");
        return in.nextLine().charAt(0);
    }

    /**
     * Prints a line of "="
     */
    private void printDivider() {
        System.out.println();
        for(int i = 0; i < LINE_LENGTH; i++) {
            System.out.print("=");
        }
        System.out.println();
    }

    /**
     * Prints blank lines to "clear" the output
     */
    private void clearPage() {
        for (int i = 0; i < PAGE_LENGTH; i++) {
            System.out.println();
        }
    }

    /**
     * Reads the list of options from a text file into and array
     */
    private void fillOptions() {
        File file = new File(OPTIONS);
        try {
            Scanner read = new Scanner(file);
            ArrayList<String> temp = new ArrayList<String>();
            while(read.hasNextLine()) {
                temp.add(read.nextLine());
            }
            Options = new String[temp.size()];
            for (int i =  0; i < temp.size(); i++) {;
                Options[i] = temp.get(i);
            }
        } catch (Exception e) {
            System.out.println("Error reading options file");
        }
    }

    /**
     * Logs User out
     */
    private void logout() {
        application.saveUser();
        startUI();
    }

    /**
     * Runs shut down code to save all system data
     */
    private void exit() {
        application.end();
        System.exit(0);
    }

    /**
     * Entry UI
     * @options (L)ogin, (C)reate Account, (E)xit
     * @options ""
     */
    public void startUI() {
        clearPage();
        System.out.print("Start");
        char entry = UIOptionsLine("220617");
        if (entry == 'L') login();
        else if (entry == 'C') createAccount();
        else if (entry == 'E') exit();
        else startUI();
    }

    /**
     * Collects email and passwrod then calls the login(email, password) application.
     * @if login() returns a user it saves that user in application and calls the appropriate Main Menu
     * @else Returns to StartUI()
     */
    public void login() {
        clearPage();
        System.out.print("Login");
        printDivider();
        System.out.print("Email: ");
        String email = in.nextLine();
        System.out.print("Password: ");
        String password = in.nextLine();
        boolean attempt = application.login(email, password);
        if(attempt) {
            if(application.userType() == 0) studentMain();
            else if(application.userType() == 1) employerMain();
            else if(application.userType() == 3) adminMain();
        } else {
            startUI();
        }
    }

    /**
     * Asks user which type of account they want to create
     * @options (S)tudent, (E)mployer, (B)ack
     * @options "391504"
     */
    public void createAccount() {
        clearPage();
        System.out.print("Create Account");
        char entry = UIOptionsLine("391504");
        if(entry == 'S') createUser(0);
        else if(entry == 'E') createUser(1);
        else if(entry == 'B') startUI();
        else createAccount();
    }

    /**
     * Creates a new student account
     * @complete StartUI()
     */
    public void createUser(int type) {
        clearPage();
        System.out.print("Student Account Creation");
        printDivider();
        while(true) {
            System.out.print("First Name: ");
            String first = in.nextLine();
            System.out.print("Last Name");
            String last = in.nextLine();
            System.out.print("Email: ");
            String email = in.nextLine();
            System.out.print("Password: ");
            String password = in.nextLine();
            boolean attempt = application.createUser(first, last,  email, password, type);
            if (attempt == true) {
                System.out.println("Account created succesfully. Press \"Enter\" to complete");
                in.nextLine();
                break;
            } else {
                System.out.println("Account creation Failed. Press \"Enter\" to return to main menu");
                in.nextLine();
                break;
            }
        }
        startUI();
    }

    /**
     * Main menu for students after login
     * @options (C)reate Resume, (O)ptions, (A)dd Rating, (V)iew Internships, (L)ogout
     * @options "0825020423"
     */
    public void studentMain() {
        clearPage();
        System.out.print("Main Menu");
        char entry = UIOptionsLine("0825020423");
        if (entry == 'C') createResume();
        else if (entry == 'o') addRating();
        else if (entry == 'V') viewInternshipMenu();
        else if (entry == 'L') logout();
        else studentMain();
    }

    /**
     * Mein menu for employers after login
     * @options (C)reate Internship, (V)iew Internships, (E)mployees, (L)ogout
     * @options "07421623"
     */
    public void employerMain() {
        clearPage();
        System.out.print("Main Menu");
        char entry = UIOptionsLine("07421623");
        if (entry == 'C') createInternship();
        else if (entry == 'V') ViewInternships();
        else if (entry == 'E') viewEmployees();
        else if (entry == 'L') logout();
        else employerMain(); 
    }

    /**
     * Main menu for admins after login
     * @options (S)tudents, (E)mployers, (I)nternships, (L)ogout
     * @options "39142022"
     */
    public void adminMain() {
        clearPage();
        System.out.print("Admin Main");
        char entry = UIOptionsLine("39152123");
        if (entry == 'S') viewStudents();
        else if (entry == 'E') viewEmployers();
        else if (entry == 'I') viewInternshipMenu();
        else if (entry == 'L') logout();
        else adminMain();
    }

    /**
     * UI for creating a resume
     */
    public void createResume() {

    }

    /**
     * Menu for student account settings
     * @options (P)rofile Options, (R)esume Options, (B)ack, (L)ogout
     * @options "30340423"
     */
    public void accountOptions() {
        clearPage();
        char entry = UIOptionsLine("30340423");
        if (entry == 'P') profileOptions();
        else if (entry == 'R') ResumeOptions();
        else if (entry == 'B') studentMain();
        else if (entry == 'L') logout();
        else accountOptions();
    }

    /**
     * Profile options menu
     * @options (E)mail, (P)assword, (B)ack, (L)ogout
     * @options "14260423"
     */
    public void profileOptions() {
        clearPage();
        System.out.println("Profile");
        printDivider();
        System.out.println(application.getUser().getFirstName() + application.getUser().getLastName());
        System.out.println(application.getUser().getEmail());
        System.out.println(application.getUser().getRating());
        System.out.println(application.getUser().getCurrentEmployer());
        char entry = UIOptionsLine("14260423");
        if(entry == 'E') updateEmail();
        else if (entry == 'P') updatePassword();
        else if (entry == 'B') accountOptions();
        else if (entry == 'L') logout();
        else profileOptions();
    }

    /**
     * Menu for viewing and editing a resume
     * @options (S)kills, (W)ork Experience, (S)chool, (E)xtra-Curriculars, (C)urrent Employer, (B)ack, (L)ogout
     * @options ""
     */
    public void ResumeOptions() {
        clearPage();
        System.out.println("Resume Options");
        printDivider();
        Resume resume = application.getResume();
        resume.toString();
        char entry = UIOptionsLine("33134420110423");
    }

    /**
     * UI for changing the current users name
     */
    public void updateName() {
        clearPage();
        System.out.print("Change Email");
        printDivider();
        System.out.print("New First Name: ");
        String first = in.nextLine();
        System.out.print("New Last Name: ");
        String last = in.nextLine();
        boolean attempt = application.updateName(first, last);
        if(attempt) {
            System.out.println("Name Updated: Press \"Enter\" to return to your profile");
        } else {
            System.out.println("Name could not be changed: Press \"Enter\" to return to your profile");
        }
        in.nextLine();
        profileOptions();
    }

    /**
     * UI for changing email
     */
    public void updateEmail() {
        clearPage();
        System.out.print("Change Email");
        printDivider();
        System.out.print("New Email: ");
        String email = in.nextLine();
        boolean attempt = application.updateEmail(email);
        if(attempt) {
            System.out.println("Email Updated: Press \"Enter\" to return to your profile");
        } else {
            System.out.println("Email could not be changed: Press \"Enter\" to return to your profile");
        }
        in.nextLine();
        profileOptions();
    }

    /**
     * UI for changing password
     */
    public void updatePassword() {
        clearPage();
        System.out.print("Change Password");
        printDivider();
        System.out.print("New Password: ");
        String password = in.nextLine();
        boolean attempt = application.updatePassword(password);
        if(attempt) {
            System.out.println("Password Updated: Press \"Enter\" to return to your profile");
        } else {
            System.out.println("Password could not be changed: Press \"Enter\" to return to your profile");
        }
        in.nextLine();
        profileOptions();
    }

    /**
     * UI for adding a rating
     */
    public void addRating() {
        clearPage();
        System.out.print("Rate User");
        printDivider();
        ArrayList<UUID> ratables = application.getRatables();
        System.out.println("#   |Name");
        System.out.println("____|_______________");
        for (int i = 0; i < ratables.size(); i++) {
            System.out.println((i+1) + "   |" + application.getUser(ratables.get(i)).getFirstName() + " " + application.getUser(ratables.get(i)).getFirstName());
        }
        printDivider();
        System.out.println("(#) to Rate   (B)ack   (L)ogout");
        printDivider();
        System.out.println("Option: ");
        String entry = in.nextLine();
        if (entry.equals("B") && application.userType() == 0) studentMain();
        else if (entry.equals("B") && application.userType() == 1) employerMain();
        else if (entry.equals("L")) logout();
        else {
            try {
                int index = Integer.parseInt(entry) - 1;
                UUID user = ratables.get(index);
                clearPage();
                System.out.println("Enter a rating (1-5)");
                printDivider();
                System.out.print("Rating: ");
                int rating = in.nextInt();
                in.nextLine();
                if(rating <= 5 && rating >=1) {
                    application.addRating(user, rating);
                }
                addRating();
            } catch (Exception e) {
                addRating();
            }
        } 
    }

    /**
     * Menu for viewing internship lists
     * @options (F)ull List, (S)earch, (B)ack, (L)ogout
     * @options "19370423"
     */
    public void viewInternshipMenu() {
        ArrayList<Internship> internships = application.getInternships();
        clearPage();
        System.out.println("View Internships");
        char entry = UIOptionsLine("19370423");
        if (entry == 'F') displayInternshipList(internships, 0);
        else if (entry == 'S') searchInternships();
        else if (entry == 'B') studentMain();
        else if (entry == 'L') logout();
        else viewInternshipMenu();
    }

    /**
     * 
     * @param internships
     */
    public void searchInternships() {
        
    }

    /**
     * @options (#)of Listing, (N)ext, (P)revious, (B)ack, (L)ogout
     * @options ""
     * @param internships
     * @param back
     */
    public void displayInternshipList(ArrayList<Internship> internships, int page) {
        int pages = internships.size() / (PAGE_LENGTH - 9);
        if (internships.size() % 0 > 0) pages++; 
        clearPage();
        System.out.println("Internship List");
        printDivider();
        System.out.println("Page: " + page + "/" + pages);
        System.out.println("#	| Company	| Position	| Salary		| Open Until");
        System.out.println("____|___________|___________|_______________|___________");
        for (int i = 0; i < 16; i++) {
            Internship internship = internships.get((page * (PAGE_LENGTH - 9) + i));
            System.out.println(i +1 + "\t|" + internship.toStringShort());
        }
        printDivider();
        System.out.println("(#)of Listing   (N)ext   (P)revious   (B)ack   (L)ogout");
        String entry = in.nextLine();
        if (entry.equals("N") && page < pages)  displayInternshipList(internships, page + 1);
        else if (entry.equals("P") && page > 0) displayInternshipList(internships, page - 1);
        else if (entry.equals("B"))             viewInternshipMenu();
        else if (entry.equals("L"))             logout();
        else {
            try {
                int index = Integer.parseInt(entry) - 1;
                if (index < internships.size() && index <= 0) viewInternship(internships.get(index));
                else displayInternshipList(internships, page);
            } catch (Exception e) {
                displayInternshipList(internships, page);
            }
        }
    }

    /**
     * Displays and internships information for a student
     * @options (A)pply, (B)ack, (L)ogout
     * @options (E)dit, (R)emove, (V)iew Applicants, (B)ack, (L)ogout
     */
    public void viewInternship(Internship internship) {
        clearPage();
        System.out.println("Internship Information");
        printDivider();
        internship.toString();
        //Student Menu
        if(application.userType() == 0) {
            char entry = UIOptionsLine("030423");
            if (entry == 'A') {
                boolean attempt = application.apply(internship);
                if (attempt) {
                    System.out.println("Application Succesful: Press \"Enter\" to return to the menu");
                } else {
                    System.out.println("Application Failed: Press \"Enter\" to return to the menu");
                }
                in.nextLine();
                viewInternshipMenu();
            }
            else if (entry == 'B') viewInternshipMenu();
            else if (entry == 'L') logout();
            else viewInternship(internship);
        } 
        //Employer Menu
        else if (application.userType() == 1) {
            char entry = UIOptionsLine("1232410423");
            if (entry == 'E')       editInternship(internship);
            else if (entry == 'R')  application.removeInternship(internship.getId());
            else if (entry == 'V')  viewApplications(internship.getApplications());
            else if (entry == 'B')  employerMain();
            else if (entry == 'L')  logout();
            else viewInternship(internship);
        } 
        //Admin Menu
        else if (application.userType() == 2) {
            char entry = UIOptionsLine("320423");
            if (entry == 'R') application.removeInternship(internship.getId());
            else if (entry == 'B') adminMain();
            else if (entry == 'L') logout();
            else viewInternship(internship);
        }
    }

    /**
     * Displays list of applicants
     * @options (#)of Listing, (N)ext, (P)revious, (B)ack, (L)ogout
     * @options ""
     */
    public void viewApplications(ArrayList<Resume> applications) {
        clearPage();
        System.out.print("Applications");
        printDivider();
        System.out.println("#   |Name");
        System.out.println("____|_______________");
        for(int i = 0; i < applications.size(); i++) {
            System.out.println(i + "\t|" + applications.get(i).getFirstName() + " " + applications.get(i).getLastName());
        }
        printDivider();
        System.out.println("(#)of Listing   (B)ack   (L)ogout");
        String entry = in.nextLine();
        if (entry.equals("B")) employerMain();
        else if (entry.equals("L")) logout();
        else {
            try {
                int index = Integer.parseInt(entry) - 1;
                if (index < applications.size() && index <= 0) viewResume(applications.get(index));
                else viewApplications(applications);
            } catch (Exception e) {
                viewApplications(applications);
            }
        }
    }

    /**
     * UI for searching resumes by a keyword
     * @param resumes
     */
    public void searchResumes(ArrayList<Resume> resumes) {

    }

    /**
     * Displays an appicants resume
     * @options (A)ccept Applicant, (D)ecline Applicant, (B)ack, (L)ogout
     * @options ""
     */
    public void viewResume(User user) {
        Resume resume = user.getResume();
        clearPage();
        System.out.print("Resume");
        printDivider();
        resume.toString();
        printDivider();
        //Employer View
        if (application.userType() == 1) {
            char entry = UIOptionsLine("01100423");
            if (entry == 'A') application.acceptApplication(resume);
            else if (entry == 'D') application.declineApplication(resume);
            else if (entry == 'B') employerMain();
            else if (entry == 'L') logout();
            else viewResume(user);
        }
        //Admin View
        else if (application.userType() == 2) {
            char entry = UIOptionsLine("320423");
            if (entry == 'R') application.deleteResume(user);
            else if (entry == 'B') adminMain();
            else if (entry == 'L') logout();
            else viewResume(user);
        }
    }

    /**
     * UI for creating an internship
     */
    public void createInternship() {
        clearPage();
        System.out.print("Internship Creation");
        printDivider();
        System.out.print("Enter a Company Name: ");
        String employer = in.nextLine();
        System.out.print("Enter a Job Title: ");
        String title = in.nextLine();
        System.out.print("Enter a brief description: ");
        String description = in.nextLine();
        System.out.print("Enter Required Skills (skill,skill,skill): ");
        String requiredSkills = in.nextLine();
        System.out.print("Enter a start date (mm/dd/yyyy): ");
        String startDate = in.nextLine();
        System.out.print("Enter an end date (mm/dd/yyyy): ");
        String endDate = in.nextLine();
        System.out.print("Enter an experation date (mm/dd/yyyy): ");
        String expirationDate = in.nextLine();
        System.out.print("Enter the number of hours per day: ");
        int hoursPerDay = in.nextInt();
        in.nextLine();
        System.out.print("Enter an off time: ");
        int endHour = in.nextInt();
        in.nextLine();
        System.out.print("Enter a salary type: ");
        String salaryType = in.nextLine();
        application.CreateInternship(title, employer, description, requiredSkills, startDate, endDate, hoursPerDay, endHour, expirationDate, salaryType);
        employerMain();
    }

    /**
     * Menu for editing an internship
     * @options (C)ompany, (T)itle, (S)kills, (P)ay, (W)ork Schedule, (E)xperation Date, (B)ack, (L)ogout
     * @options "0540383536180423"
     */
    public void editInternship(Internship internship) {
        clearPage();
        System.out.print("Edit Internship");
        char entry = UIOptionsLine("0540382736180423");
        if (entry == 'C')       editCompany(internship);
        else if (entry == 'T')  editTitle(internship);
        else if (entry == 'S')  editSkills(internship);
        else if (entry == 'P')  editPay(internship);
        else if (entry == 'W')  editSchedule(internship);
        else if (entry == 'E')  editExpiration(internship);
        else if (entry == 'B')  {
            application.updateInternship(internship);
            employerMain();
        }
        else if (entry == 'L') {
            application.updateInternship(internship);
            logout();
        }
        else editInternship(internship);
    }

    /**
     * UI for chaning an internships company
     * @param internship the internship being modified
     */
    public void editCompany(Internship internship) {
        clearPage();
        System.out.print("Edit Company Name");
        printDivider();
        System.out.println("Current Name: " + internship.getEmployer());
        System.out.print("New Name: ");
        String employer = in.nextLine();
        internship.setEmployer(employer);
        editInternship(internship);
    }

    /**
     * UI for chaning an internships job title
     * @param internship the internship being modified
     */
    public void editTitle(Internship internship) {
        clearPage();
        System.out.print("Edit Job Title");
        printDivider();
        System.out.println("Current Title: " + internship.getTitle());
        System.out.print("New Title: ");
        String title = in.nextLine();
        internship.setTitle(title);
        editInternship(internship);
    }

    /**
     * UI for chaning an internships required skills
     * @param internship the internship being modified
     */
    public void editSkills(Internship internship) {
        ArrayList<String> skills = internship.getRequiredSkills();
        clearPage(); 
        System.out.print("Edit Required Skills");
        printDivider();
        System.out.print("Current Required Skills: ");
        for(int i = 0; i < skills.size(); i++) {
            System.out.print(skills.get(i));
            if (i + 1 < skills.size()) {
                System.out.print(", ");
            }
        }
        printDivider();
        System.out.print("Enter a skill to add/remove (Enter done when finished)");
        String entry = in.nextLine();
        if (entry.equalsIgnoreCase("done")) {
            editInternship(internship);
        } else if (entry.isBlank()) {
            editSkills(internship);
        } else {
            if (skills.contains(entry)) {
                skills.remove(entry)
                internship.setRequiredSkills(skills);
                editSkills(internship);
            } else {
                skills.add(entry);
                internship.setRequiredSkills(skills);
                editSkills(internship);
            }
        }
    }

    /**
     * UI for chaning an internships salary
     * @param internship the internship being modified
     */
    public void editPay(Internship internship) {

    }

    /**
     * UI for chaning an internships schedule
     * @param internship the internship being modified
     */
    public void editSchedule(Internship internship) {

    }

    /**
     * UI for chaning an internships experation date
     * @param internship the internship being modified
     */
    public void editExpiration(Internship internship) {

    }

    /**
     * UI for menu for viewing Users
     */
    public void viewUsersMenu() {
        clearPage();
        System.out.print("View User List Options");
        char entry = UIOptionsLine("193915480423");
        if (entry == 'F')       displayUserList(application.getUsers(), 0);
        else if (entry == 'S')  displayUserList(application.getUsers(0), 0);
        else if (entry == 'E')  displayUserList(application.getUsers(1), 0);
        else if (entry == 'K')  searchUsers();
        else if (entry == 'B')  adminMain();
        else if (entry == 'L')  logout();
    }

    /**
     * UI Menu for searching by user 
     */
    public void searchUsers() {
        clearPage();
        System.out.print("Enter a keyword to search by");
        printDivider();
        System.out.print("Keyword: ");
        String keyword = in.nextLine();
        ArrayList<User> users = application.getUsers(keyword);
        if (users == null) {
            System.out.println("No Matches Found: Press \"Enter\" to return to the menuu");
            in.nextLine();
            viewUsersMenu();
        } else {
            displayUserList(users, 0);
        }
    }

    /**
     * Displays a list of users
     * @param users the list to be displayed
     * @param page page number to be displayed
     */
    public void displayUserList(ArrayList<User> users, int page) {
        int pages = users.size() / (PAGE_LENGTH - 9);
        if (users.size() % 0 > 0) pages++; 
        clearPage();
        System.out.println("Internship List");
        printDivider();
        System.out.println("Page: " + page + "/" + pages);
        System.out.println("#	| Name      | Type      | Rating");
        System.out.println("____|___________|___________|_______");
        for (int i = 0; i < 16; i++) {
            User user = users.get((page * (PAGE_LENGTH - 9) + i));
            System.out.println(i +1 + "\t|" + user.toStringShort());
        }
        printDivider();
        System.out.println("(#)of Listing   (N)ext   (P)revious   (B)ack   (L)ogout");
        String entry = in.nextLine();
        if (entry.equals("N") && page < pages)  displayUserList(users, page + 1);
        else if (entry.equals("P") && page > 0) displayUserList(users, page - 1);
        else if (entry.equals("B"))             viewInternshipMenu();
        else if (entry.equals("L"))             logout();
        else {
            try {
                int index = Integer.parseInt(entry) - 1;
                if (index < users.size() && index <= 0) viewUser(users.get(index));
                else displayUserList(users, page);
            } catch (Exception e) {
                displayUserList(users, page);
            }
        }
    }

    /**
     * Displays a student profile
     * @options (P)romote, (R)emove, (V)iew Ratings, (B)ack, (L)ogout
     * @options ""
     */
    public void viewUser(User user) {
        clearPage();
        System.out.print("User Profile");
        printDivider();
        user.toString();
        if (application.userType(user.getId()) == 0) {
            char entry = UIOptionsLine("314743340423");
            if (entry == 'P') {
                application.promoteUser(user.getId());
                adminMain();
            } else if (entry == 'D') {
                application.removeUser(user.getId());
                adminMain();
            } else if (entry == 'V') viewRatings(user);
            else if (entry == 'R') viewResume(user);
            else if (entry == 'B') adminMain();
            else if (entry == 'L') logout();
            else viewUser(user);
        }
    }

    /**
     * Displays a list of a students ratings
     * @options (#)to Remove, (R)eset Rating, (N)ext, (P)revious, (B)ack, (L)ogout
     * @options ""
     */
    public void viewRatings(User user) {
        
    }
}
