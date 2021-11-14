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
    public void testGetInternshipsIfListContainsInternships(){

        assertEquals(internships, application.getInternships());

    }

    @Test
    public void testGetInternshipIfListIsEmpty(){
        InternshipList.getInstance().setInternshipList(new ArrayList<>());
        assertEquals(new ArrayList<>(), application.getInternships());
    }

    @Test
    public void testGetInternshipsIfKeywordExists(){
        assertEquals(internships, application.getInternships("python"));
    }

    @Test
    public void testGetInternshipsIfKeywordNotExists(){
        assertEquals(new ArrayList<>(), application.getInternships("Does not exist"));
    }

    @Test
    public void testGetInternshipIfKeywordIsEmpty(){
        assertEquals(new ArrayList<>(), application.getInternships(""));
    }

    @Test
    public void testGetInternshipIfEmployerHasInternhsip(){
        Employer employerTest = new Employer("Pete", "Yo", "pete@yo.com", "123");
        Internship toTest = new Internship("Petes", "Frau Mustermann", "Pete",
        new ArrayList<>(Arrays.asList("python", "java")), LocalDate.now(), LocalDate.now().plusDays(1),
        1, 15, LocalDate.now().plusDays(3), new FixedSalary(44));
        employerTest.createNewInternship("Petes", "Frau Mustermann", "Pete",
        new ArrayList<>(Arrays.asList("python", "java")), LocalDate.now(), LocalDate.now().plusDays(1),
        1, 15, LocalDate.now().plusDays(3), new FixedSalary(44));

        assertEquals(toTest, application.getInternships(employerTest.getId()));
    }

    @Test
    public void testGetinternshipIfEmployerHasNoInternships(){
        assertEquals(null, application.getInternship(employer.getId()));
    }

}
