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
        student = null;
        employer = null;
        UserList.getInstance().getUsers().clear();
        InternshipList.getInstance().getInternships().clear();
    }

    @Test
    public void testLoginValidReturn() {
        assertTrue(application.login("JohnDoe@email.sc.edu", "Password1"));
    }

    @Test
    public void testLoginInValidReturn() {
        assertFalse(application.login("JohnDoe@email.sc.edu", "Password2"));
    }

    @Test
    public void testLoginSuccesful() {
        application.login("JohnDoe@email.sc.edu", "Password1");
        assertNotEquals(null, application.getCurrent());    
    }

    @Test
    public void testLoginUnsuccesful() {
        application.login("JohnDoe@email.sc.edu", "Password2");
        assertEquals(null, application.getCurrent()); 
    }

    @Test
    public void testLogoutSuccesful() {
        if (application.login("JohnDoe@email.sc.edu", "Password1")) {
            application.logout();
            assertEquals(null, application.getCurrent());
        }
    }

    @Test
    public void testGetCurrentWhenNull() {
        assertEquals(null, application.getCurrent());
    }

    @Test
    public void testCreateUserValid() {
        assertTrue(application.createUser("Portia", "Portia", "PPlante@email.sc.edu", "Password3", 1));
    }

    @Test
    public void testCreateUserInValid() {
        assertFalse(application.createUser("Portia", "Portia", "PPlanteATemail.sc.edu", "pwrd", 1));
    }

    @Test
    public void testUserTypeValid() {
        application.login("JohnDoe@email.sc.edu", "Password1");
        assertEquals(0, application.userType());
    }

    @Test
    public void testUserTypeWhenNull() {
        assertEquals(-1, application.userType());
    }

    @Test
    public void testUserTypeByIDValid() {
        assertEquals(1, application.userType(employer.getId()));
    }

    @Test
    public void testUserTypeByIDWhenNull() {
        assertEquals(-1, application.userType(null));
    }

    @Test
    public void testGetRatablesValid() {
        application.login("JohnDoe@email.sc.edu", "Password1");
        student.addFormerEmployer(employer.getId());
        assertEquals(employer.getId(), application.getRatables().get(0));
    }

    @Test
    public void testAddRatingValid() {
        boolean test = true;
        application.addRating(student.getId(), 5);
        application.addRating(student.getId(), 4);
        application.addRating(student.getId(), 3);
        application.addRating(student.getId(), 2);
        application.addRating(student.getId(), 1);
        for (int i = 0; i < student.getRatings().size(); i++) {
            if (!student.getRatings().contains(i + 1)) {
                test = false;
            }
        }
        assertEquals(true, test);
    }

    @Test
    public void testGetUsersValidType() {
        assertTrue(application.getUsers(0).get(0) == student); 
    }

    @Test
    public void testGetUsersInValidType() {
        assertEquals(null, application.getUsers(5));
    }

    @Test
    public void testGetUsersValidString() {
        assertEquals(student, application.getUsers("John").get(0));
    }

    @Test
    public void testGetUsersInValidString() {
        assertEquals(null, application.getUsers(""));
    }

    @Test
    public void testGetEmployeesValidUUID() {
        employer.addEmployee(student.getId());
        assertEquals(employer.getEmployees(), application.getEmployees(employer.getId()));
    }

    @Test
    public void testGetEmployeesInValidUUID() {
        assertEquals(null, application.getEmployees(student.getId()));
    }

    @Test
    public void testUpdateEmailValid() {
        application.login("JohnDoe@email.sc.edu", "Password1");
        application.updateEmail("DoeJohn@email.sc.edu");
        assertEquals("DoeJohn@email.sc.edu", student.getEmail());
    }

    @Test
    public void testUpdateEmailInValid() {
        application.login("JohnDoe@email.sc.edu", "Password1");
        application.updateEmail("DoeJohnATemail.sc.edu");
        assertEquals("JohnDoe@email.sc.edu", student.getEmail());
    }

    @Test
    public void testUpdatePasswordValid() {
        String old = student.getPassword();
        application.login("JohnDoe@email.sc.edu", "Password1");
        application.updatePassword("Password3");
        assertNotEquals(old, application.getUser().getPassword());
    }

    @Test
    public void testUpdatePasswordInValid() {
        String old = student.getPassword();
        application.login("JohnDoe@email.sc.edu", "Password1");
        application.updatePassword("");
        assertEquals(old, application.getUser().getPassword());
    }

    @Test
    public void testUpdateNameValid() {
        String oldF = student.getFirstName();
        String oldL = student.getLastName();
        application.login("JohnDoe@email.sc.edu", "Password1");
        application.updateName("Jimmy", "Johns");
        assertFalse(student.getFirstName() == oldF && student.getLastName() == oldL);
    }

    @Test
    public void testRemoveUserValid() {
        application.removeUser(student.getId());
        assertEquals(null, application.getUser(student.getId()));
    }

    @Test
    public void testGetRatingValid() {
        for (int i = 0; i < 5; i++) {
            student.addRating(i + 1);
        }
        assertEquals(0.0, application.getRating());
    }

    @Test
    public void testResetRatingValid() {
        for (int i = 0; i < 5; i++) {
            student.addRating(i + 1);
        }
        application.resetRating(student.getId());
        assertEquals(0.0, application.getRating());
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

    @Test
    public void testCreateInternshipIfUserIsEmployer(){
        application.login("JaneDoe@email.sc.edu", "Password2");
        application.createInternship("Petes", "Frau Mustermann", "Pete",
       "python,Java", "10/10/2020", "11/10/2021", 1, 15,
        "12/10/2021", "44");
        assertTrue(employer.getInternshipList().size() == 1);
    }

    @Test
    public void testCreateInternshipIfUserIsNotEmployer(){
        application.login("JohnDoe@email.sc.edu", "Password1");
        application.createInternship("Search for this", "Frau Mustermann", "Pete",
       "python,Java", "10/10/2020", "11/10/2021", 1, 15,
        "12/10/2021", "44");
        assertEquals(new ArrayList<>(), application.getInternships("Search for this"));
    }

    @Test
    public void testGetEmployerIfInternshipIdExists(){
        employer.createNewInternship("Petes", "Frau Mustermann", "Pete",
                new ArrayList<>(Arrays.asList("python", "java")), LocalDate.now(), LocalDate.now().plusDays(1), 1, 15,
                LocalDate.now().plusDays(3), new FixedSalary(44));
        assertEquals("Frau Mustermann", application.getEmployer(employer.getInternshipList().get(0)));

    }

    @Test
    public void testGetEmployerIfInternshipNotExists(){
        assertEquals("", application.getEmployer(UUID.randomUUID()));

    }

    @Test
    public void changeEmployerIfInternshipExists(){
        application.changeEmployer(internships.get(0).getId(), "NewName");
        assertEquals(internships.get(0).getEmployer(), "NewName");
    }

    @Test
    public void testChangeEmployerIfInternshipNotExists(){
        application.changeEmployer(UUID.randomUUID(), "NewName");
        assertTrue(application.getInternships("NewName").size() == 0);
    }

    @Test
    public void testChangeEmployerIfEmployerNameNull(){
        application.changeEmployer(internships.get(0).getId(), null);
        assertEquals(internships.get(0).getEmployer(), null);
    }

    @Test
    public void testGetTitleIfInternshipExists(){
        assertEquals(internships.get(0).getTitle(), application.getTitle(internships.get(0).getId()));
    }

    @Test
    public void testGetTitleIfInternshipDoesNotExists(){
        assertEquals("", application.getTitle(UUID.randomUUID()));
    }

    @Test
    public void testGetTitleIfInternshipIDIsNull(){
        assertEquals("", application.getTitle(null));
    }

    @Test
    public void testChangeTitleIfInternshipExists(){
        application.changeTitle(internships.get(0).getId(), "NewName");
        assertEquals(internships.get(0).getTitle(), "NewName");
    }

    @Test
    public void testChangeTitleIfInternshipNotExists(){
        application.changeTitle(UUID.randomUUID(), "NewName");
        assertTrue(application.getInternships("NewName").size() == 0);
    }

    @Test
    public void testChangeTitleIfEmployerNameNull(){
        application.changeTitle(internships.get(0).getId(), null);
        assertEquals(internships.get(0).getTitle(), null);
    }

    @Test
    public void testGetRequiredSkillsIfInternshipExists(){
        assertEquals(internships.get(0).getRequiredSkills(), application.getRequiredSkills(internships.get(0).getId()));
    }

    @Test
    public void testGetRequiredSkillsIfInternshipNotExists(){
        assertEquals(new ArrayList<>(), application.getRequiredSkills(UUID.randomUUID()));
    }

    @Test
    public void testGetRequiredSkillsIfInternshipIDIsNull(){
        assertEquals(new ArrayList<>(), application.getRequiredSkills(null));
    }

    @Test
    public void testChangeRequiredSkillsIfInternshipExists(){
        application.changeRequiredSkills(internships.get(0).getId(), "ChangeSkill");
        assertEquals(internships.get(0).getRequiredSkills().get(0), "ChangeSkill");
    }

    @Test
    public void testChangeRequiredSkillsIfInternshipNotExists(){
        application.changeRequiredSkills(UUID.randomUUID(), "ChangeSkill");
        assertTrue(application.getInternships("ChangeSkill").size() == 0);
    }

    @Test
    public void testChangeRequiredSkillsIfEmployerNameNull(){
        application.changeRequiredSkills(internships.get(0).getId(), null);
        assertEquals(internships.get(0).getRequiredSkills().get(0), null);
    }

    @Test
    public void testChangeSalaryTypeIfInternshipExists(){
        application.changeSalary(internships.get(0).getId(), "9999");
        assertEquals("9999", internships.get(0).getSalary());
    }

    @Test
    public void testChangeSalaryTypeIfInternshipNotExists(){
        application.changeSalary(UUID.randomUUID(), "9999");
        assertEquals("9999", internships.get(0).getSalary());
    }

    @Test
    public void testChangeSalaryTypeIfSalaryNull(){
        application.changeSalary(internships.get(0).getId(), null);
        assertEquals("To be determined", internships.get(0).getSalary());
    }

    @Test
    public void testChangeSalaryTypeIfSalaryNotNumber(){
        application.changeSalary(internships.get(0).getId(), "aaa");
        assertEquals("To be determined", internships.get(0).getSalary());
    }

    @Test
    public void testChangeExperationIfInternshipExistsAndDateValid(){
        application.changeExperation(internships.get(0).getId(), "10/10/2020");
        assertEquals("2020-10-10", internships.get(0).getExpirationDate().toString());
    }

    @Test
    public void testChangeExperationIfInternshipNotExistsAndDateValid(){
        application.changeExperation(UUID.randomUUID(), "10/10/2020");
        assertEquals(null, internships.get(0).getExpirationDate().toString());
    }

    @Test
    public void testChangeExperationIfInternshipExistsAndDateInValid(){
        application.changeExperation(internships.get(0).getId(), "99/99/2020");
        assertEquals(null, internships.get(0).getExpirationDate().toString());
    }
}
