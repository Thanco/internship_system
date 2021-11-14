package Testing;

/**
 * @author Wyatt Wilgus 2021
 * @author 
 * @author 
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import Model.*;
import UserInterface.InternshipApplication;

import java.util.*;
import java.io.File;
import java.time.LocalDate;

public class ApplicationTester {
    InternshipApplication application = new InternshipApplication();
    Student student;
    Employer employer;
    Admin admin;
    ArrayList<Internship> internships;

    /**
     * Initializes the application object
     */
    @BeforeEach
    public void setup() {
        internships = new ArrayList<>(Arrays.asList(
                new Internship("Test intern", "Frau Mustermann", "Test",
                        new ArrayList<>(Arrays.asList("python", "java")), LocalDate.now(), LocalDate.now().plusDays(1),
                        1, 15, LocalDate.now().plusDays(3), new FixedSalary(44)),
                new Internship("Intern Test", "Pete", "Test", new ArrayList<>(Arrays.asList("python", "java")),
                        LocalDate.now(), LocalDate.now().plusDays(1), 1, 15, LocalDate.now().plusDays(3),
                        new FixedSalary(44))));
        InternshipList.getInstance().setInternshipList(internships);
        student = new Student("John", "Doe", "JohnDoe@email.sc.edu", "Password1");
        employer = new Employer("Jane", "Doe", "JaneDoe@email.sc.edu", "Password2");
        admin = new Admin("Max", "Mustermann", "max@mustermann.de", "12345");
        ArrayList<User> users = new ArrayList<>(Arrays.asList(student, employer, admin));
        UserList.getInstance().setUserList(users);
    }

    /**
     * Sets the application object to null
     */
    @AfterEach
    public void tearDown() {
        UserList.getInstance().getUsers().clear();
        InternshipList.getInstance().getInternships().clear();
    }

    @Test
    public void testLoginValidReturn() {

    }

    @Test
    public void testLoginInValidReturn() {

    }

    @Test
    public void testLoginSuccesful() {

    }

    @Test
    public void testLoginUnsuccesful() {

    }

    @Test
    public void testLogoutSuccesful() {

    }

    @Test
    public void testGetCurrentWhenNull() {

    }

    @Test
    public void testCreateUserValid() {

    }

    @Test
    public void testCreateUserInValid() {

    }

    @Test
    public void testUserTypeValid() {

    }

    @Test
    public void testUserTypeWhenNull() {

    }

    @Test
    public void testUserTypeByIDValid() {

    }

    @Test
    public void testUserTypeByIDWhenNull() {

    }

    @Test
    public void testGetRatablesValid() {

    }

    @Test
    public void testGetRatablesInValid() {

    }

    @Test
    public void testAddRatingValid() {

    }

    @Test
    public void testAddRatingInValid() {

    }

    @Test
    public void testGetUsersValidType() {

    }

    @Test
    public void testGetUsersInValidType() {

    }

    @Test
    public void testGetUsersValidString() {

    }

    @Test
    public void testGetUsersInValidString() {

    }

    @Test
    public void testGetEmployeesValidUUID() {

    }

    @Test
    public void testGetEmployeesInValidUUID() {

    }

    @Test
    public void testUpdateEmailValid() {

    }

    @Test
    public void testUpdateEmailInValid() {

    }

    @Test
    public void testUpdatePasswordValid() {

    }

    @Test
    public void testUpdatePasswordInValid() {

    }

    @Test
    public void testUpdateNameValid() {

    }

    @Test
    public void testUpdateNameInValid() {

    }

    @Test
    public void testUpdateRemoveUserValid() {

    }

    @Test
    public void testUpdateRemoveUserInValid() {

    }

    @Test
    public void testUpdateGetRatingValid() {

    }

    @Test
    public void testUpdateGetRatingInValid() {

    }

    @Test
    public void testUpdateGetRatingsValid() {

    }

    @Test
    public void testUpdateGetRatingsInValid() {

    }

    @Test
    public void testUpdateResetRatingValid() {

    }

    @Test
    public void testUpdateResetRatingInValid() {

    }

    @Test
    public void testGetInternshipsIfListContainsInternships() {

        assertEquals(internships, application.getInternships());

    }

    @Test
    public void testGetInternshipIfListIsEmpty() {
        InternshipList.getInstance().setInternshipList(new ArrayList<>());
        assertEquals(new ArrayList<>(), application.getInternships());
    }

    @Test
    public void testGetInternshipsIfKeywordExists() {
        assertEquals(internships, application.getInternships("python"));
    }

    @Test
    public void testGetInternshipsIfKeywordNotExists() {
        assertEquals(new ArrayList<>(), application.getInternships("Does not exist"));
    }

    @Test
    public void testGetInternshipIfKeywordIsEmpty() {
        assertEquals(new ArrayList<>(), application.getInternships(""));
    }

    @Test
    public void testGetInternshipIfEmployerHasInternhsip() {
        Employer employerTest = new Employer("Pete", "Yo", "pete@yo.com", "123");
        Internship toTest = new Internship("Petes", "Frau Mustermann", "Pete",
                new ArrayList<>(Arrays.asList("python", "java")), LocalDate.now(), LocalDate.now().plusDays(1), 1, 15,
                LocalDate.now().plusDays(3), new FixedSalary(44));
        employerTest.createNewInternship("Petes", "Frau Mustermann", "Pete",
                new ArrayList<>(Arrays.asList("python", "java")), LocalDate.now(), LocalDate.now().plusDays(1), 1, 15,
                LocalDate.now().plusDays(3), new FixedSalary(44));

        assertEquals(toTest, application.getInternships(employerTest.getId()));
    }

    @Test
    public void testGetinternshipIfEmployerHasNoInternships() {
        assertEquals(new ArrayList<>(), application.getInternships(employer.getId()));
    }

    @Test
    public void testGetInternshipIfNoneEmployerIdGiven() {
        assertEquals(null, application.getInternships(student.getId()));
    }

    @Test
    public void testGetInternshipIfIdIsInList() {
        assertEquals(internships.get(0), application.getInternship(internships.get(0).getId()));
    }

    @Test
    public void testGetInternshipIfIdIsNotInList() {
        assertEquals(null, application.getInternship(UUID.randomUUID()));
    }

    @Test
    public void testGetInternshipIfIdIsNull() {
        assertEquals(null, application.getInternship(null));
    }

    @Test
    public void testGetApplicationsIfInternshipHasApplication() {
        WorkExperience workExperience = new WorkExperience("Test", "TestCompany", "02/10/2021", "03/10/2011");
        Education education = new Education("Test School", SchoolYear.FRESHMAN, "CS");
        Resume applicationResume = new Resume(UUID.randomUUID(), UUID.randomUUID(), "Max", "Mustermann", education,
                new ArrayList<>(Arrays.asList("python", "java")), new ArrayList<>(Arrays.asList(workExperience)),
                new ArrayList<>(Arrays.asList("baseball", "social work")));

        internships.get(0).addApplication(applicationResume);

        assertEquals(new ArrayList<>(Arrays.asList(applicationResume)),
                application.getApplications(internships.get(0).getId()));
    }

    @Test
    public void testGetApplicationsIfInternshipHasMultipleApplication() {
        WorkExperience workExperience = new WorkExperience("Test", "TestCompany", "02/10/2021", "03/10/2011");
        Education education = new Education("Test School", SchoolYear.FRESHMAN, "CS");
        Resume applicationResumeOne = new Resume(UUID.randomUUID(), UUID.randomUUID(), "Max", "Mustermann", education,
                new ArrayList<>(Arrays.asList("python", "java")), new ArrayList<>(Arrays.asList(workExperience)),
                new ArrayList<>(Arrays.asList("baseball", "social work")));
        Resume applicationResumeTwo = new Resume(UUID.randomUUID(), UUID.randomUUID(), "Pete", "Mustermann", education,
                new ArrayList<>(Arrays.asList("python", "java")), new ArrayList<>(Arrays.asList(workExperience)),
                new ArrayList<>(Arrays.asList("baseball", "social work")));

        internships.get(0).addApplication(applicationResumeOne);
        internships.get(0).addApplication(applicationResumeTwo);

        assertEquals(new ArrayList<>(Arrays.asList(applicationResumeOne, applicationResumeTwo)),
                application.getApplications(internships.get(0).getId()));
    }

    @Test
    public void testGetApplicationIfInternshipHasNoApplications() {
        assertEquals(new ArrayList<>(), application.getApplications(internships.get(0).getId()));

    }

    @Test
    public void testGetApplicationIfInternshipIdIsNull() {
        assertEquals(new ArrayList<>(), application.getApplications(null));
    }

    @Test
    public void testApplyIfCurrentUserIsStudentAndIdExists(){
        application.login("JohnDoe@email.sc.edu", "Password1");
        Education education = new Education("Test School", SchoolYear.FRESHMAN, "CS");
        student.createResume("Pete", "Musterman", education);
        Resume resume = student.getResume();
        application.apply(internships.get(0).getId());
        assertTrue(internships.get(0).getApplications().contains(resume));
    }

    @Test
    public void testApplyIfCurrentUserIsEmployerAndIdExists(){
        application.login("JaneDoe@email.sc.edu", "Password2");
        application.apply(internships.get(0).getId());
        assertTrue(internships.get(0).getApplications().size() == 0);
    }

    @Test
    public void testApplyIfCurrentUserIsStudentButInternshipIdNull(){
        application.login("JohnDoe@email.sc.edu", "Password1");
        Education education = new Education("Test School", SchoolYear.FRESHMAN, "CS");
        student.createResume("Pete", "Musterman", education);
        application.apply(null);
        assertTrue(internships.get(0).getApplications().size() == 0);
    }

    @Test
    public void testAcceptApplication(){
        application.login("JaneDoe@email.sc.edu", "Password2");
        Internship toTest = new Internship("Petes", "Frau Mustermann", "Pete",
                new ArrayList<>(Arrays.asList("python", "java")), LocalDate.now(), LocalDate.now().plusDays(1), 1, 15,
                LocalDate.now().plusDays(3), new FixedSalary(44));
        Education education = new Education("Test School", SchoolYear.FRESHMAN, "CS");
        student.createResume("Pete", "Musterman", education);


        application.acceptApplication(student.getResume());
        assertTrue(employer.getEmployees().contains(student.getId()));

    }

    @Test
    public void testAcceptApplicationIfResumeNull(){
        application.login("JaneDoe@email.sc.edu", "Password2");
        application.acceptApplication(null);
        assertTrue(employer.getEmployees().size() == 0);
    }

    @Test
    public void testdeclineApplication(){
        application.login("JaneDoe@email.sc.edu", "Password2");
        Education education = new Education("Test School", SchoolYear.FRESHMAN, "CS");
        student.createResume("Pete", "Musterman", education);
        application.declineApplication(internships.get(0).getId(), student.getResume().getUuid());
        assertTrue(!employer.getEmployees().contains(student.getId()));

    }

    @Test
    public void testRemoveInternshipIfIsInList(){
        Internship toRemove = internships.get(0);
        application.removeInternship(toRemove.getId());
        assertTrue(!InternshipList.getInstance().getInternships().contains(toRemove));
    }

    @Test
    public void testRemoveInternshipIfNotInList(){
        int listSize = InternshipList.getInstance().getInternships().size();
        application.removeInternship(UUID.randomUUID());
        assertTrue(listSize == InternshipList.getInstance().getInternships().size());
    }

    @Test
    public void testRemoveInternshipIfInternshipNull(){
        int listSize = InternshipList.getInstance().getInternships().size();
        application.removeInternship(null);
        assertTrue(listSize == InternshipList.getInstance().getInternships().size());
    }

}
