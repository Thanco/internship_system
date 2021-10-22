package UserInterface;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

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
    private void ClearPage() {
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
        currentUser = null;
        StartUI();
    }

    /**
     * Entry UI
     * @options (L)ogin, (C)reate Account, (E)xit
     * @options ""
     */
    public void StartUI() {
        ClearPage();
        System.out.println("Start");
        UIOptionsLine("220617");
    }

    /**
     * Collects email and passwrod then calls the login(email, password) application.
     * @if login() returns a user it saves that user in currentUser and calls the appropriate Main Menu
     * @else Returns to StartUI()
     */
    public void Login() {
        ClearPage();
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
        ClearPage();
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
        ClearPage();
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
        ClearPage();
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
     * @options "07421522"
     */
    public void EmployerMain() {
        
    }

    /**
     * Main menu for admins after login
     * @options (S)tudents, (E)mployers, (I)nternships, (L)ogout
     * @options "39142022"
     */
    public void AdminMain() {
        
    }

    /**
     * UI for creating a new resume
     * @options (S)kills, (E)ducation, (W)ork Experience, (H)obbies, (D)one, (B)ack, (L)ogout
     * @options "364419"
     */
    public void CreateResume() {

    }

    /**
     * UI for entering skills
     */
    public void EnterSkills() {

    }
    
    /**
     * UI for entering ecucation
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
     * Menu for student account settings
     * @options (P)rofile Options, (R)esume Options, (B)ack, (L)ogout
     * @options "30340423"
     */
    public void AccountOptions() {

    }

    /**
     * Profile options menu
     * @options (E)mail, (P)assword, (C)urrent Employer, (B)ack, (L)ogout
     * @options ""
     */
    public void ProfileOptions() {

    }

    /**
     * UI for changing email
     */
    public void ChangeEmail() {

    }

    /**
     * UI for changing password
     */
    public void ChangePassword() {

    }

    /**
     * UI for changing current employer
     */
    public void ChangeEmployer() {

    }

    /**
     * Menu for viewing and editing a resume
     * @options (S)kills, (W)ork Experience, (S)chool, (E)xtra-Curriculars, (C)urrent Employer, (B)ack, (L)ogout
     * @options ""
     */
    public void ResumeOptions() {

    }

    /**
     * UI for editing a list of skills
     */
    public void EditSkills() {

    }

    /**
     * UI for adding work Experience
     */
    public void AddWork() {

    }

    /**
     * UI for editing education
     */
    public void EditEducation() {

    }

    /**
     * UI for editing extra-curriculars
     */
    public void EditExtra() {

    }

    /**
     * UI for editing current employer
     */
    public void EditCurrent() {

    }

    /**
     * UI for adding a rating
     */
    public void AddRating(Employer employer) {

    }

    /**
     * Menu for viewing internship lists
     * @options (F)ull List, (S)earch, (B)ack, (L)ogout
     * @options
     */
    public void ViewInternshipList() {

    }

    /**
     * Displays and internships information
     * @options (A)pply, (B)ack, (L)ogout
     * @options ""
     */
    public void ViewInternship(Internship internship) {

    }

    /**
     * Menu for searching internships
     * @options (C)ompany, (P)osition, (S)alary, (B)ack, (L)ogout
     * @options ""
     */
    public void SeachInternships() {

    }

    /**
     * Menu for searching internships by company
     */
    public void SearchCompany() {

    }

    /**
     * UI for searching internships by position
     */
    public void SearchPosition() {

    }

    /**
     * Displays a list of internships
     * @options (#)of Listing, (N)ext, (P)revious, (B)ack, (L)ogout
     * @options ""
     */
    public void DisplayResults() {

    }

    /**
     * Displays information about an internship
     * @options (A)pply, (B)ack, (L)ogout
     * @options ""
     */
    public void InternshipInfo() {

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
