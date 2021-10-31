package UserInterface;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;
import Model.Internship;
import Model.Resume;
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
        // Scanner read = new Scanner(options);
        // String[] ops = read.nextLine().split(",");
        printDivider();
        for (int i = 0; i < options.length(); i = i + 2) {
            int index = Integer.parseInt(options.substring(i, i + 2)) - 1;
            System.out.print(Options[index] + "   ");
        }
        printDivider();
        System.out.print("Options: ");
        return Character.toUpperCase(in.nextLine().charAt(0));
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
            read.close();
        } catch (Exception e) {
            System.out.println("Error reading options file");
        }
    }

    /**
     * Entry UI
     * @options (L)ogin, (C)reate Account, (E)xit
     * @options ""
     */
    public void startUI() {
        while (true) {
            clearPage();
            System.out.print("Start");
            char entry = UIOptionsLine("220617");
            if (entry == 'L') login();
            else if (entry == 'C') createAccount();
            else if (entry == 'E') {
                in.close(); 
                application.end();
            }
        }
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
                else if(application.userType() == 2) adminMain();
            }
    }

    /**
     * Asks user which type of account they want to create
     * @options (S)tudent, (E)mployer, (B)ack
     * @options "391504"
     */
    public void createAccount() {
        while (true) {
            clearPage();
            System.out.print("Create Account");
            char entry = UIOptionsLine("391504");
            if(entry == 'S') createUser(0);
            else if(entry == 'E') createUser(1);
            else if(entry == 'B') return;
        }
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
            System.out.print("Last Name: ");
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
        return;
    }

    /**
     * Main menu for students after login
     * @options (C)reate Resume, (O)ptions, (A)dd Rating, (V)iew Internships, (L)ogout
     * @options "0825020423"
     */
    public void studentMain() {
        while (true) {
            clearPage();
            System.out.print("Main Menu");
            char entry = UIOptionsLine("0825024223");
            if (entry == 'C') createResume();
            else if (entry == 'O') accountOptions();
            else if (entry == 'A') addRating();
            else if (entry == 'V') viewInternshipMenu();
            else if (entry == 'L') {
                application.logout();
                return;
            }
        }
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
        else if (entry == 'V') viewInternshipMenu();
        else if (entry == 'E') displayUserList(application.getEmployees(), 0);
        else if (entry == 'L') {
            application.logout();
            return;
        }
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
        if (entry == 'S') displayUserList(application.getUsers(0), 0);
        else if (entry == 'E') displayUserList(application.getUsers(1), 0);
        else if (entry == 'I') viewInternshipMenu();
        else if (entry == 'L') {
            application.logout();
            return;
        }
    }

    /**
     * UI for creating a resume
     */
    public void createResume() {
        clearPage();
        System.out.print("Resume Creation");
        printDivider();
        System.out.print("Enter the name of your school: ");
        String school = in.nextLine();
        System.out.print("Enter your major: ");
        String major = in.nextLine();
        System.out.print("Enter your year (1-4): ");
        int year = in.nextInt();
        in.nextLine();
        boolean attempt = application.createResume(school, year, major);
        if (attempt) resumeOptions();
        else {
            System.out.println("Resume could not be created: Press enter to continiue");
            in.nextLine();
        }
    }

    /**
     * Menu for viewing and editing a resume
     * @options (S)kills, (W)ork Experience, (S)chool, (E)xtra-Curriculars, (B)ack, (L)ogout
     * @options ""
     */
    public void resumeOptions() {
        clearPage();
        System.out.print("Resume Options");
        printDivider();
        Resume resume = application.getResume();
        System.out.println(resume.toStringLong());
        char entry = UIOptionsLine("3313442004");
        if (entry == 'S') enterSkills();
        else if (entry == 'W') enterWork();
        else if (entry == 'E') enterEducation();
        else if (entry == 'H') enterExtra();
        else if (entry == 'B') return;
    }

    /**
     * UI for entering/editing skills
     */
    public void enterSkills() {
        clearPage();
        System.out.println("Skills Entry");
        printDivider();
        System.out.println("Skills: " + application.getResume().getStudentSkills());
        System.out.print("Enter a skill or \"done\"");
        printDivider();
        System.out.print("Skill: ");
        String skill = in.nextLine();
        if (skill.equalsIgnoreCase("done")) return;
        else {
            application.changeSkills(skill);
        }
    }

    /**
     * UI for entering a new education
     */
    public void enterEducation() {
        clearPage();
        System.out.print("Education Entry");
        printDivider();
        System.out.println("Enter the name of your school: ");
        String school = in.nextLine();
        System.out.println("Enter your major: ");
        String major = in.nextLine();
        System.out.println("Enter your year: ");
        String year = in.nextLine();
        application.changeEducation(school, major, year);
    }

    /**
     * UI for adding/editing extra curricular activities
     */ 
    public void enterExtra() {
        while (true) {
            clearPage();
            System.out.print("Extra-Curricular Entry");
            printDivider();
            System.out.println("Activity: " + application.getResume().getExtraCirricularList());
            System.out.print("Enter an activity or \"done\"");
            printDivider();
            System.out.print("Activity: ");
            String activity = in.nextLine();
            if (activity.equalsIgnoreCase("done")) return;
                application.changeExtra(activity);
        }
    }

    /**
     * UI for entering a new work experience
     */
    public void enterWork() {
        clearPage();
        System.out.print("Work Experience Entry");
        printDivider();
        //TODO job title and descritptions @Mr. Wyatt
        System.out.print("Enter a Company Name: ");
        String company = in.nextLine();
        System.out.print("Enter the start date (MM/YYYY): ");
        String start = in.nextLine();
        System.out.print("Enter the end date (MM/YYYY): ");
        String end = in.nextLine();
        application.changeWork(company, start, end);
    }

    /**
     * Menu for student account settings
     * @options (P)rofile Options, (R)esume Options, (B)ack, (L)ogout
     * @options "30340423"
     */
    public void accountOptions() {
        while (true) {
            clearPage();
            char entry = UIOptionsLine("303404");
            if (entry == 'P') profileOptions();
            else if (entry == 'R') resumeOptions();
            else if (entry == 'B') return;
        }
    }

    /**
     * Profile options menu
     * @options (E)mail, (P)assword, (B)ack, (L)ogout
     * @options "14260423"
     */
    public void profileOptions() {
        while (true) {
            clearPage();
            System.out.println("Profile");
            printDivider();
            System.out.println(application.getUser().getFirstName() + application.getUser().getLastName());
            System.out.println(application.getUser().getEmail());
            System.out.println(application.getRating());
            char entry = UIOptionsLine("142604");
            if(entry == 'E') updateEmail();
            else if (entry == 'P') updatePassword();
            else if (entry == 'B') return;
        }
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
    }

    /**
     * UI for adding a rating
     */
    public void addRating() {
        while (true) {
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
            String entry = in.nextLine().toUpperCase();
            if (entry.equals("B")) return;
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
                } catch (Exception e) {
                    System.out.println("");
                }
            }
        }
    }

    /**
     * Menu for viewing internship lists
     * @options (F)ull List, (S)earch, (B)ack, (L)ogout
     * @options "19370423"
     */
    public void viewInternshipMenu() {
        while (true) {
            ArrayList<Internship> internships = application.getInternships();
            clearPage();
            System.out.print("View Internships");
            char entry = UIOptionsLine("193704");
            if (entry == 'F') displayInternshipList(internships, 0);
            else if (entry == 'S') searchInternships();
            else if (entry == 'B') return;
        }
    }

    /**
     * 
     * @param internships
     */
    public void searchInternships() {
        clearPage();
        System.out.println("Search Internships");
        printDivider();
        System.out.print("Enter a keyword to search by: ");
        String keyword = in.nextLine();
        displayInternshipList(application.getInternships(keyword), 0);
    }

    /**
     * @options (#)of Listing, (N)ext, (P)revious, (B)ack, (L)ogout
     * @options ""
     * @param internships
     * @param back
     */
    public void displayInternshipList(ArrayList<Internship> internships, int page) {
        while (true) {
            int pages = internships.size() / (PAGE_LENGTH - 9);
            clearPage();
            System.out.print("Internship List");
            printDivider();
            System.out.println("Page: " + (page + 1) + "/" + (pages + 1));
            System.out.println("#\t| Company\t| Position\t| Salary\t\t| Open Until");
            System.out.println("________|_______________|_______________|_______________________|___________");
            for (int i = 0; i < (PAGE_LENGTH - 9) && i < internships.size(); i++) {
                Internship internship = internships.get((page * (PAGE_LENGTH - 9) + i));
                System.out.println(i +1 + "\t|" + internship.toStringShort());
            }
            printDivider();
            System.out.println("(#)of Listing   (N)ext   (P)revious   (B)ack");
            printDivider();
            System.out.print("Option: ");
            String entry = in.nextLine().toUpperCase();
            if (entry.equals("N") && page < pages)  page++;
            else if (entry.equals("P") && page > 0) page--;
            else if (entry.equals("B"))             return;
            else {
                try {
                    int index = Integer.parseInt(entry) - 1;
                    if (index < internships.size() && index >= 0) viewInternship(internships.get(index).getId());
                } catch (Exception e) {}
            }
        }
    }

    /**
     * Displays and internships information for a student
     * @options (A)pply, (B)ack, (L)ogout
     * @options (E)dit, (R)emove, (V)iew Applicants, (B)ack, (L)ogout
     */
    public void viewInternship(UUID internship) {
        clearPage();
        System.out.print("Internship Information");
        printDivider();
        internship.toString();
        //Student Menu
        if(application.userType() == 0) {
            char entry = UIOptionsLine("0304");
            if (entry == 'A') {
                boolean attempt = application.apply(internship);
                if (attempt) {
                    System.out.println("Application Succesful: Press \"Enter\" to return to the menu");
                } else {
                    System.out.println("Application Failed: Press \"Enter\" to return to the menu");
                }
                in.nextLine();
            }
            else if (entry == 'B') return;
        } 
        //Employer Menu
        else if (application.userType() == 1) {
            while (true) {
                char entry = UIOptionsLine("12324104");
                if (entry == 'E')       editInternship(internship);
                else if (entry == 'R')  application.removeInternship(internship);
                else if (entry == 'V')  viewApplications(application.getApplications(internship));
                else if (entry == 'B')  return;
            }
        } 
        //Admin Menu
        else if (application.userType() == 2) {
            char entry = UIOptionsLine("3204");
            if (entry == 'R') application.removeInternship(internship);
            else if (entry == 'B') return;
        }
    }

    /**
     * Displays list of applicants
     * @options (#)of Listing, (N)ext, (P)revious, (B)ack, (L)ogout
     * @options ""
     */
    public void viewApplications(ArrayList<Resume> applications) {
        while (true) {
            clearPage();
            System.out.print("Applications");
            printDivider();
            System.out.println("#\t|Name");
            System.out.println("________|_______________");
            for(int i = 0; i < applications.size(); i++) {
                System.out.println((i + 1) + "\t|" + applications.get(i).getFirstName() + " " + applications.get(i).getLastName());
            }
            printDivider();
            System.out.println("(#)of Listing   (B)ack   (L)ogout");
            String entry = in.nextLine().toUpperCase();
            if (entry.equals("B")) return;
            else {
                try {
                    int index = Integer.parseInt(entry) - 1;
                    viewResume(applications.get(index).getOwnerUUID());
                } catch (Exception e) {
                    System.out.println("Resume could not be opened: Press \"enter\" to continiue");
                    in.nextLine();
                }
            }
        }
    }

    /**
     * UI for searching resumes by a keyword
     * @param resumes
     */
    public void searchResumes(ArrayList<Resume> resumes) {
        clearPage();
        System.out.print("Search Applicants");
        printDivider();
        System.out.print("Enter a keyword to search for: ");
        String keyword = in.nextLine();
        ArrayList<Resume> newResumes = application.searchResumes(resumes, keyword);
        if (newResumes == null) {
            System.out.println("No applications contain that keyword: Press \"enter\" to continiue");
            in.nextLine();
        }
        else viewApplications(newResumes);
    }

    /**
     * Displays an appicants resume
     * @options (A)ccept Applicant, (D)ecline Applicant, (B)ack, (L)ogout
     * @options ""
     */
    public void viewResume(UUID user) {
        while (true) {
            Resume resume = application.getResume(user);
            clearPage();
            System.out.print("Resume");
            printDivider();
            System.out.println(resume.toStringLong());
            //Employer View
            if (application.userType() == 1) {
                char entry = UIOptionsLine("011004");
                if (entry == 'A') application.acceptApplication(resume);
                else if (entry == 'D') application.declineApplication(resume);
                else if (entry == 'B') return;
            }
            //Admin View
            else if (application.userType() == 2) {
                char entry = UIOptionsLine("3204");
                if (entry == 'R') application.deleteResume(user);
                else if (entry == 'B') return;
            }
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
        application.createInternship(title, employer, description, requiredSkills, startDate, endDate, hoursPerDay, endHour, expirationDate, salaryType);
    }

    /**
     * Menu for editing an internship
     * @options (C)ompany, (T)itle, (S)kills, (P)ay, (W)ork Schedule, (E)xperation Date, (B)ack, (L)ogout
     * @options "0540383536180423"
     */
    public void editInternship(UUID internship) {
        while (true) {
            clearPage();
            System.out.print("Edit Internship");
            char entry = UIOptionsLine("05403827361804");
            if (entry == 'C')       editCompany(internship);
            else if (entry == 'T')  editTitle(internship);
            else if (entry == 'S')  editSkills(internship);
            else if (entry == 'P')  editPay(internship);
            else if (entry == 'W')  editSchedule(internship);
            else if (entry == 'E')  editExpiration(internship);
            else if (entry == 'B')  return;
        }
    }

    /**
     * UI for chaning an internships company
     * @param internship the internship being modified
     */
    public void editCompany(UUID internship) {
        clearPage();
        System.out.print("Edit Company Name");
        printDivider();
        System.out.print("Current Name: " + application.getEmployer(internship));
        System.out.print("New Name: ");
        String employer = in.nextLine();
        application.changeEmployer(internship, employer);
    }

    /**
     * UI for chaning an internships job title
     * @param internship the internship being modified
     */
    public void editTitle(UUID internship) {
        clearPage();
        System.out.print("Edit Job Title");
        printDivider();
        System.out.print("Current Title: " + application.getTitle(internship));
        System.out.print("New Title: ");
        String title = in.nextLine();
        application.changeTitle(internship, title);
    }

    /**
     * UI for chaning an internships required skills
     * @param internship the internship being modified
     */
    public void editSkills(UUID internship) {
        while (true) {
            ArrayList<String> skills = application.getRequiredSkills(internship);
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
            String skill = in.nextLine();
            if (skill.equalsIgnoreCase("done")) {
                return;
            } else {
                application.changeRequiredSkills(internship, skill);
            }
        }
    }

    /**
     * UI for chaning an internships salary
     * @param internship the internship being modified
     */
    public void editPay(UUID internship) {
        clearPage();
        System.out.print("Salary Entry");
        printDivider();
        System.out.print("Enter a new salary: ");
        String salary = in.nextLine();
        application.changeSalary(internship, salary);
    }

    /**
     * UI for chaning an internships schedule
     * @param internship the internship being modified
     */
    public void editSchedule(UUID internship) {
        clearPage();
        System.out.print("Schedule Modification");
        printDivider();
        System.out.print("Enter a new start date (MM/DD/YYYY): ");
        String start = in.nextLine();
        System.out.print("Enter a new start date (MM/DD/YYYY): ");
        String end = in.nextLine();
        System.out.print("Enter new hours per day: ");
        int hours = in.nextInt();
        in.nextLine();
        application.changeSchedule(internship, start, end, hours);
    }

    /**
     * UI for chaning an internships experation date
     * @param internship the internship being modified
     */
    public void editExpiration(UUID internship) {
        clearPage();
        System.out.print("Edit Experation Date");
        printDivider();
        System.out.print("Enter a new date (MM/YYYY): ");
        String date = in.nextLine();
        application.changeExperation(internship, date);
    }

    /**
     * UI for menu for viewing Users
     */
    public void viewUsersMenu() {
        while (true) {
            clearPage();
            System.out.print("View User List Options");
            char entry = UIOptionsLine("1939154804");
            if (entry == 'F')       displayUserList(application.getUsers(), 0);
            else if (entry == 'S')  displayUserList(application.getUsers(0), 0);
            else if (entry == 'E')  displayUserList(application.getUsers(1), 0);
            else if (entry == 'K')  searchUsers();
            else if (entry == 'B')  return;
        }
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
            System.out.println("No Matches Found: Press \"Enter\" to return to the menu");
            in.nextLine();
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
        while (true) {
            int pages = users.size() / (PAGE_LENGTH - 9); 
            clearPage();
            System.out.print("Internship List");
            printDivider();
            System.out.println("Page: " + (page + 1) + "/" + pages);
            System.out.println("#	| Name      | Type      | Rating");
            System.out.println("____|___________|___________|_______");
            for (int i = 0; i < (PAGE_LENGTH - 9) && i < users.size(); i++) {
                User user = users.get((page * (PAGE_LENGTH - 9) + i));
                System.out.println(i +1 + "\t|" + user.toStringShort());
            }
            printDivider();
            System.out.print("(#)of Listing   (N)ext   (P)revious   (B)ack   (L)ogout");
            String entry = in.nextLine().toUpperCase();
            if (entry.equals("N") && page < pages)  displayUserList(users, page + 1);
            else if (entry.equals("P") && page > 0) displayUserList(users, page - 1);
            else if (entry.equals("B"))             return;
            else {
                try {
                    int index = Integer.parseInt(entry) - 1;
                    if (index < users.size() && index >= 0) viewUser(users.get(index).getId());
                } catch (Exception e) {}
            }
        }
    }

    /**
     * Displays a User profile
     * @options (P)romote, (R)emove, (V)iew Ratings, (B)ack, (L)ogout
     * @options ""
     */
    public void viewUser(UUID user) {
        while (true) {
            clearPage();
            System.out.print("User Profile");
            printDivider();
            user.toString();
            if (application.userType(user) == 0) {
                char entry = UIOptionsLine("3147433404");
                if (entry == 'P') {
                    application.promoteUser(user);
                    return;
                } else if (entry == 'D') {
                    application.removeUser(user);
                    return;
                } else if (entry == 'V') viewRatings(user);
                else if (entry == 'R') viewResume(user);
                else if (entry == 'B') return;
            } else if (application.userType(user) == 1) {
                char entry = UIOptionsLine("47164204");
                if (entry == 'D') application.removeUser(user);
                else if (entry == 'E') displayUserList(application.getEmployees(user), 0);
                else if (entry == 'V') displayInternshipList(application.getInternships(user), 0);
                else if (entry == 'B') return;
            }
        }
    }

    /**
     * Displays a list of a students ratings
     * @options (#)to Remove, (R)eset Rating, (N)ext, (P)revious, (B)ack, (L)ogout
     * @options ""
     */
    public void viewRatings(UUID user) {
        while (true) {
            clearPage();
            System.out.print("User Ratings");
            printDivider();
            int[] ratings = application.getRatings(user);
            System.out.println("#   |Rating");
            System.out.println("____|______");
            for (int i = 0; i < ratings.length; i++) {
                System.out.println((i + 1) + "\t|" + ratings[i]);
            }
            printDivider();
            System.out.print("(#)to Remove   (R)eset Rating   (B)ack   (L)ogout");
            printDivider();
            System.out.print("Option: ");
            String entry = in.nextLine().toUpperCase();
            if (entry.equals("R")) {
                application.resetRating(user);
                return;
            }
            else if (entry.equals("B")) return;
            else {
                try {
                    int index = Integer.parseInt(entry) - 1;
                    if (index < ratings.length && index >= 0) application.removeRating(user, index);
                } catch (Exception e) {}
            }
        }
    }
}
