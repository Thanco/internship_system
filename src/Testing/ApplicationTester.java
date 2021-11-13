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
    InternshipApplication application;
    Student student;
    Employer employer;

    /**
     * Initializes the application object
     */
	@BeforeEach
	public void setup() {
		application = new InternshipApplication();
        student = new Student("John", "Doe", "JohnDoe@email.sc.edu", "Password1");
        employer = new Employer("Jane", "Doe", "JaneDoe@email.sc.edu", "Password2");
	}

    /**
     * Sets the application object to null
     */
	@AfterEach
	public void tearDown() {
		application = null;
        student = null;
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
}
