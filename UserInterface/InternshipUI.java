package UserInterface;
import java.io.File;
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
public class InternshipUI extends InternshipApplication {
    private final int LINE_LENGTH = 80;
    private final int PAGE_LENGTH = 25;
    private final String OPTIONS = "Options.txt";
    private User currentUser;
    private Scanner in = new Scanner(System.in);
    private String[] Options;
    private int size;

    /**
     * Runs initialization for UI
     */
    public void run() {
        FillOptions();
        StartUI();
    }

    /**
     * Prints an options menu and returns a char
     * @param A list of options to be printed (01072027)
     * @return The users entry
     */
    private char UIOptionsLine(String options) {
        Scanner read = new Scanner(options);
        String[] ops = read.nextLine().split(",");
        PrintDivider();
        System.out.println();
        for (int i = 0; i < size; i += 2) {
            int index = Integer.parseInt(options.substring(i, i + 2)) - 1;
            System.out.print(Options[index] + "   ");
        }
        PrintDivider();
        System.out.println("Options: ");
        return in.nextLine().charAt(0);
    }

    /**
     * Prints a line of "="
     */
    private void PrintDivider() {
        for(int i = 0; i < LINE_LENGTH; i++) {
            System.out.print("=");
        }
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
    private void FillOptions() {
        File file = new File(OPTIONS);
        try {
            Scanner read = new Scanner(file);
            ArrayList<String> temp = new ArrayList<String>();
            while(read.hasNextLine()) {
                temp.add(read.nextLine());
            }
            size = temp.size();
            Options = new String[size];
            for (int i =  0; i < size; i++) {
                Options[i] = temp.get(i);
            }
        } catch (Exception e) {
            System.out.println("Error reading options file");
        }
    }

    /**
     * Logs User out
     */
    private void Logout() {
        super.saveUser(currentUser);
        currentUser = null;
        StartUI();
    }

    /**
     * Runs shut down code to save all system data
     */
    private void Exit() {

    }

    /**
     * Entry UI
     * @options (L)ogin, (C)reate Account, (E)xit
     * @options ""
     */
    public void StartUI() {
        clearPage();
        System.out.println("Start");
        char entry = UIOptionsLine("220617");
        if (entry == 'L') Login();
        else if (entry == 'C') CreateAccount();
        else if (entry == 'E') Exit();
        else StartUI();
    }

    /**
     * Collects email and passwrod then calls the login(email, password) application.
     * @if login() returns a user it saves that user in currentUser and calls the appropriate Main Menu
     * @else Returns to StartUI()
     */
    public void Login() {
        clearPage();
        System.out.println("Login");
        PrintDivider();
        System.out.println("Email: ");
        String email = in.nextLine();
        System.out.println("Password: ");
        String password = in.nextLine();
        currentUser = super.login(email, password);
        if(currentUser instanceof Employer ) EmployerMain();
        else if(currentUser instanceof Student ) StudentMain();
        else if(currentUser instanceof Admin) AdminMain();
        else StartUI();
    }

    /**
     * Asks user which type of account they want to create
     * @options (S)tudent, (E)mployer, (B)ack
     * @options "391504"
     */
    public void CreateAccount() {
        clearPage();
        char entry = UIOptionsLine("391504");
        if(entry == 'S') CreateStudent();
        else if(entry == 'E') CreateEmployer();
        else if(entry == 'B') StartUI();
        else CreateAccount();
    }

    /**
     * Creates a new student account
     * @complete StartUI()
     */
    public void CreateStudent() {
        clearPage();
        System.out.println("Student Account Creation");
        PrintDivider();
        System.out.println("First Name: ");
        String first = in.nextLine();
        System.out.println("Last Name");
        String last = in.nextLine();
        System.out.println("Email: ");
        String email = in.nextLine();
        System.out.println("Password: ");
        String password = in.nextLine();
        super.createUser(first, last,  email, password, 0);
        StartUI();
    }

    /**
     * Creates a new employer account
     * @complete StartUI()
     */
    public void CreateEmployer() {
        clearPage();
        System.out.println("Employer Account Creation");
        PrintDivider();
        System.out.println("First Name: ");
        String first = in.nextLine();
        System.out.println("Last Name");
        String last = in.nextLine();
        System.out.println("Email: ");
        String email = in.nextLine();
        System.out.println("Password: ");
        String password = in.nextLine();
        super.createUser(first, last,  email, password, 1);
        StartUI();
    }

    /**
     * Main menu for students after login
     * @options (C)reate Resume, (O)ptions, (A)dd Rating, (V)iew Internships, (L)ogout
     * @options "0825020423"
     */
    public void StudentMain() {
        clearPage();
        System.out.println("Main Menu");
        char entry = UIOptionsLine("0825020423");
        if (entry == 'C') CreateResume();
        else if (entry == 'o') AddRating();
        else if (entry == 'V') ViewInternshipList();
        else if (entry == 'L') Logout();
        else StudentMain();
    }

    /**
     * Mein menu for employers after login
     * @options (C)reate Internship, (V)iew Internships, (E)mployees, (L)ogout
     * @options "07421623"
     */
    public void EmployerMain() {
        clearPage();
        char entry = UIOptionsLine("07421623");
        if (entry == 'C') CreateInternship();
        else if (entry == 'V') ViewInternships();
        else if (entry == 'E') ViewEmployees();
        else if (entry == 'L') Logout();
        else EmployerMain(); 
    }

    /**
     * Main menu for admins after login
     * @options (S)tudents, (E)mployers, (I)nternships, (L)ogout
     * @options "39142022"
     */
    public void AdminMain() {
        clearPage();
        char entry = UIOptionsLine("39152123");
        if (entry == 'S') ViewStudents();
        else if (entry == 'E') ViewEmployers();
        else if (entry == 'I') ViewInternshipList();
        else if (entry == 'L') Logout();
        else AdminMain();
    }

    /**
     * UI for creating a new resume
     * @options (E)ducation, (W)ork Experience, (H)obbies, (B)ack, (L)ogout
     * @options "1344200423"
     */
    public void CreateResume() {
        clearPage();
        System.out.println("Resume Creation");
        PrintDivider();
        System.out.println("Type \"done\" when you have entered all of your skills");
        ArrayList<String> skills = new ArrayList<>();
        while(true) {
            System.out.println("Skill: ");
            String entry = in.nextLine();
            if (entry.equalsIgnoreCase("done")) {
                break;
            }
            else {
                skills.add(entry);
            }
        }
        super.createResume(skills);
        EditResume();
    }

    /**
     * Fills resume with additional information or edits existing information
     */
    public void EditResume() {
        clearPage();
        System.out.println("Resume Creation");
        char entry = UIOptionsLine("33134420110423");
        if (entry == 'S') EnterSkills();
        else if (entry == 'E') EnterEducation();
        else if (entry == 'W') EnterWork();
        else if (entry == 'H') EnterExtra();
        else if (entry == 'B') StudentMain();
        else if (entry == 'L') Logout();
        else EditResume();
    }
    
    /**
     * UI for entering education
     */
    public void EnterEducation() {

    }

    /**
     * UI for entering work experiance
     */
    public void EnterWork() {

    }

    /**
     * UI for entering extra-curricular
     */
    public void EnterExtra() {

    }

    /**
     * UI for entering skills
     */
    public void EnterSkills() {
        clearPage();
        System.out.println("Skills Entry");
        PrintDivider();
        ArrayList<String> skills = currentUser.getResume().getStudentSkills();
        System.out.println("Skills: ");
        for(int i = 0; i < skills.size(); i++) {
            System.out.print(skills.get(i) + ", ");
        }
        PrintDivider();
        System.out.println("Enter a skill to add/remove or enter 'done': ");
        String entry = in.nextLine();
        if(entry.equalsIgnoreCase("done")) currentUser.setStudentSkills(skills);
        else if (skills.contains(entry)) skills.remove(entry);
        else skills.add(entry);
        EnterSkills();
    }

    /**
     * Menu for student account settings
     * @options (P)rofile Options, (R)esume Options, (B)ack, (L)ogout
     * @options "30340423"
     */
    public void AccountOptions() {
        clearPage();
        char entry = UIOptionsLine("30340423");
        if (entry == 'P') ProfileOptions();
        else if (entry == 'R') ResumeOptions();
        else if (entry == 'B') StudentMain();
        else if (entry == 'L') Logout();
        else AccountOptions();
    }

    /**
     * Profile options menu
     * @options (E)mail, (P)assword, (B)ack, (L)ogout
     * @options "14260423"
     */
    public void ProfileOptions() {
        clearPage();
        System.out.println("Profile");
        PrintDivider();
        System.out.println(currentUser.getFirstName() + currentUser.getLastName());
        System.out.println(currentUser.getEmail());
        System.out.println(/**Get Rating*/);
        System.out.println(/**Get Current Employer*/);
        char entry = UIOptionsLine("14260423");
        if(entry == 'E') ChangeEmail();
        else if (entry == 'P') ChangePassword();
        else if (entry == 'B') AccountOptions();
        else if (entry == 'L') Logout();
        else ProfileOptions();
    }

    /**
     * UI for changing email
     */
    public void ChangeEmail() {
        clearPage();
        System.out.println("Change Email");
        PrintDivider();
        System.out.println("New Email: ");
        String entry = in.nextLine();
        /**currentUser.setEmail(entry); */
        ProfileOptions();
    }

    /**
     * UI for changing password
     */
    public void ChangePassword() {
        clearPage();
        System.out.println("Change Password");
        PrintDivider();
        System.out.println("New Password: ");
        String entry = in.nextLine();
        /**currentUser.setPassword(entry); */
        ProfileOptions();
    }

    /**
     * Menu for viewing and editing a resume
     * @options (S)kills, (W)ork Experience, (S)chool, (E)xtra-Curriculars, (C)urrent Employer, (B)ack, (L)ogout
     * @options ""
     */
    public void ResumeOptions() {
        clearPage();
        System.out.println("Resume Options");
        PrintDivider();
        currentUser.getResume().toString();
        char entry = UIOptionsLine("33134420110423");
        if (entry == 'S') EnterSkills();
        else if (entry == 'E') EnterEducation();
        else if (entry == 'W') EnterWork();
        else if (entry == 'H') EnterExtra();
        else if (entry == 'B') StudentMain();
        else if (entry == 'L') Logout();
        else EditResume();
    }

    /**
     * UI for adding a rating
     */
    public void AddRating(UUID user) {
        clearPage();
        System.out.println("Rate User");
        PrintDivider();
        System.out.println("Enter a rating (1-5): ");
        super.addRating(user, in.nextInt());
        in.nextLine();
        if(currentUser instanceof Student) StudentMain();
        else if(currentUser instanceof Employer) ViewEmployees();
    }

    /**
     * Menu for viewing internship lists
     * @options (F)ull List, (S)earch, (B)ack, (L)ogout
     * @options "19370423"
     */
    public void ViewInternshipList() {
        ArrayList<Internship> internships = getInternshipList();
        clearPage();
        System.out.println("View Internships");
        char entry = UIOptionsLine("19370423");
        if (entry == 'F') DisplayInternshipList(internships, 0, 0);
        else if (entry == 'S') SearchInternships(internships);
        else if (entry == 'B') StudentMain();
        else if (entry == 'L') Logout();
        else ViewInternshipList();
    }

    /**
     * Menu for searching internships
     * @options (C)ompany, (P)osition, (B)ack, (L)ogout
     * @options "05280423"
     */
    public void SearchInternships(ArrayList<Internship> internships) {
        clearPage();
        System.out.println("Search Internships");
        char entry = UIOptionsLine("05280423");
        if (entry == 'C') SearchCompany();
        else if (entry == 'P') SearchPosition();
        else if (entry == 'B') ViewInternshipList();
        else if (entry == 'L') Logout();
        else SearchInternships(internships);
    }

    /**
     * Menu for searching internships by company
     */
    public void SearchCompany() {
        clearPage();
        System.out.println("Search by Employer");
        PrintDivider();
        System.out.println("Enter a company to search for: ");
        String entry = in.nextLine();
        DisplayInternshipList(searchCompany(entry), 1, 0);
    }

    /**
     * UI for searching internships by position
     */
    public void SearchPosition() {
        clearPage();
        System.out.println("Search by Position");
        PrintDivider();
        System.out.println("Enter a position to search for: ");
        String entry = in.nextLine();
        DisplayInternshipList(searchPosition(entry), 2, 0);
    }

    /**
     * @options (#)of Listing, (N)ext, (P)revious, (B)ack, (L)ogout
     * @options ""
     * @param internships
     * @param back
     */
    public void DisplayInternshipList(ArrayList<Internship> internships, int back, int page) {
        ArrayList<Internship[]> pages = new ArrayList<Internship[]>();
        for (int i = 0; i < internships.size(); i += 16) {
            Internship temp[] = new Internship[16];
            for (int j = 0; j < 16 && j + (i * 16) < internships.size(); j++) {
                temp[i] = internships.get((i * 16) + j);
            }
            pages.add(temp);
        }
        clearPage();
        System.out.println("Internship List");
        PrintDivider();
        System.out.println("Page: " + page + "/" + pages.size());
        System.out.println("#	| Company	| Position	| Salary		| Open Until");
        System.out.println("____|___________|___________|_______________|___________");
        for (int i = 0; i < 16; i++) {
            Internship internship = pages.get(page)[i];
            System.out.println(i +1 + "\t|" + internship.toStringShort());
        }
        char entry = UIOptionsLine("4524290423");
        //Option Selection
    }

        /**
     * Displays and internships information
     * @options (A)pply, (B)ack, (L)ogout
     * @options ""
     */
    public void ViewInternship(Internship internship) {
        clearPage();
        System.out.println("Internship Information");
        PrintDivider();
        internship.toString();
        char entry = UIOptionsLine("030423");
        if (entry == 'A')/*Add resume t internship*/;
        else if (entry == 'B') ViewInternshipList();
        else if (entry == 'L') Logout();
        else ViewInternship(internship);
    }

    /**
     * UI for creating an internship
     */
    public void CreateInternship() {

    }

    /**
     * UI for adding salary to an internship
     */
    public void AddSalary() {

    }

    /**
     * UI for adding a schedule to an internship
     */
    public void AddSchedule() {

    }

    /**
     * Displays a list of employees
     * @options (#)of Listing, (N)ext, (P)revious, (B)ack, (L)ogout
     * @options ""
     */
    public void ViewEmployees(Employer employer) {

    }

    /**
     * UI for adding a rating to a employee
     */
    public void AddRating(/*Student*/) {

    }

    /**
     * UI for removing an employee
     */
    public void RemoveEmployee() {

    }

    /**
     * Displays a list of internships
     * @options (#)of Listing, (N)ext, (P)revious, (B)ack, (L)ogout
     * @options ""
     */
    public void ViewInternships(Employer employer) {

    }

    /**
     * Displays information about an internship
     * @options (E)dit, (R)emove, (V)iew Applicants, (B)ack, (L)ogout
     * @options ""
     */
    public void ViewInternship() {

    }

    /**
     * Menu for editing an internship
     * @options (C)ompany, (T)itle, (S)kills, (P)ay, (W)ork Schedule, (E)xperation Date, (B)ack, (L)ogout
     * @options ""
     */
    public void EditInternship() {

    }

    /**
     * Displays list of applicants
     * @options (#)of Listing, (N)ext, (P)revious, (B)ack, (L)ogout
     * @options ""
     */
    public void ViewApplications() {

    }

    /**
     * Displays an appicants resume
     * @options (A)ccept Applicant, (D)ecline Applicant, (B)ack, (L)ogout
     * @options ""
     */
    public void ViewResume(Resume resume) {

    }

    /**
     * Displays list of all student users
     * @options (#)of Listing, (N)ext, (P)revious, (B)ack, (L)ogout
     * @options ""
     */
    public void ViewStudents() {

    }

    /**
     * Displays a student profile
     * @options (P)romote, (R)emove, (V)iew Ratings, (B)ack, (L)ogout
     * @options ""
     */
    public void ViewStudent() {

    }

    /**
     * Displays a list of a students ratings
     * @options (#)to Remove, (R)eset Rating, (N)ext, (P)revious, (B)ack, (L)ogout
     * @options ""
     */
    public void ViewRatings(Student student) {

    }

    /**
     * Displays a student's resume
     * @options (R)emove, (B)ack, (L)ogout
     * @options ""
     */
    public void ViewResume() {

    }

    /**
     * Displays a list of employers
     */
    public void ViewEmployers() {

    }

    /**
     * Displays a list of internships
     * @options (#)of Listing, (N)ext, (P)revious, (B)ack, (L)ogout
     * @options ""
     */
    public void ViewInternships() {

    }

    /**
     * Views a list of employees
     * @options (#)of Listing, (N)ext, (P)revious, (B)ack, (L)ogout
     * @options ""
     */
    public void ViewEmployees() {

    }

    /**
     * Displays an employee profile
     * @options (A)dd Rating, (R)emove Employee, (B)ack, (L)ogout
     * @options ""
     */
    public void ViewEmployee() {

    }

    /**
     * Displays a users ratings
     * @options (#)to Remove, (R)eset Rating, (N)ext, (P)revious, (B)ack, (L)ogout
     * @options ""
     */
    public void ViewRatings() {

    }
}
