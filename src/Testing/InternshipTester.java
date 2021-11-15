package Testing;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import org.junit.Test;

import Model.Education;
import Model.FixedSalary;
import Model.HiddenSalary;
import Model.Internship;
import Model.RangeSalary;
import Model.Resume;
import Model.SchoolYear;
import Model.WorkExperience;

public class InternshipTester {

    Internship internship = new Internship("Test intern", "Frau Mustermann", "Test",
            new ArrayList<>(Arrays.asList("python", "java")), LocalDate.of(2020, 10, 10), LocalDate.of(2020, 10, 11), 1, 15,
            LocalDate.of(2020, 10, 13), new FixedSalary(44));

    @Test
    public void testToStringWithFixedSalary() {
        assertEquals("Frau Mustermann\n\tTest intern\nRequired Skills: python, java\nDescription: Test\nPay: 44\nSchedule:\n   Start Day: 10/10/2020\n   End Day: 10/11/2020\n   Hours per Day: 1\nOpen Until: 10/13/2020", internship.toString());
    }

    @Test
    public void testToStringWithRangeSalary() {
        this.internship.setSalaryType(new RangeSalary(1, 2));
        assertEquals("Frau Mustermann\n\tTest intern\nRequired Skills: python, java\nDescription: Test\nPay: 1 - 2\nSchedule:\n   Start Day: 10/10/2020\n   End Day: 10/11/2020\n   Hours per Day: 1\nOpen Until: 10/13/2020", internship.toString());
    }

    @Test
    public void testToStringWithHiddenSalary() {
        this.internship.setSalaryType(new HiddenSalary());
        assertEquals("Frau Mustermann\n\tTest intern\nRequired Skills: python, java\nDescription: Test\nPay: To be determined\nSchedule:\n   Start Day: 10/10/2020\n   End Day: 10/11/2020\n   Hours per Day: 1\nOpen Until: 10/13/2020", internship.toString());
    }

    @Test
    public void testToShortString(){
        assertEquals("Frau Mustermann	| Test intern	| 44	| 2020-10-13", internship.toStringShort());
    }

    @Test
    public void testAddAplication(){
        WorkExperience workExperience = new WorkExperience("Test", "TestCompany", "02/10/2021", "03/10/2011");
        Education education = new Education("Test School", SchoolYear.FRESHMAN, "CS");
        Resume application = new Resume(UUID.randomUUID(), UUID.randomUUID(), "Max", "Mustermann", education, new ArrayList<>(Arrays.asList("python", "java")), new ArrayList<>(Arrays.asList(workExperience)), new ArrayList<>(Arrays.asList("baseball", "social work")));
        this.internship.addApplication(application);
        assertEquals(application, this.internship.getApplications().get(0)); 
    }

    @Test
    public void testAddApplicationIsNull(){
        this.internship.addApplication(null);
        assertEquals(null, this.internship.getApplications().get(0));
    }

    @Test
    public void testRemoveApplicationIfApplicationIsInList(){
        WorkExperience workExperience = new WorkExperience("Test", "TestCompany", "02/10/2021", "03/10/2011");
        Education education = new Education("Test School", SchoolYear.FRESHMAN, "CS");
        Resume application = new Resume(UUID.randomUUID(), UUID.randomUUID(), "Max", "Mustermann", education, new ArrayList<>(Arrays.asList("python", "java")), new ArrayList<>(Arrays.asList(workExperience)), new ArrayList<>(Arrays.asList("baseball", "social work")));
        this.internship.addApplication(application);
        this.internship.removeApplication(application);
        assertTrue(!this.internship.getApplications().contains(application));
    } 

    @Test
    public void testRemoveApplicationIfApplicationNotInList(){
        WorkExperience workExperience = new WorkExperience("Test", "TestCompany", "02/10/2021", "03/10/2011");
        Education education = new Education("Test School", SchoolYear.FRESHMAN, "CS");
        Resume application = new Resume(UUID.randomUUID(), UUID.randomUUID(), "Max", "Mustermann", education, new ArrayList<>(Arrays.asList("python", "java")), new ArrayList<>(Arrays.asList(workExperience)), new ArrayList<>(Arrays.asList("baseball", "social work")));
        Resume applicationTwo = new Resume(UUID.randomUUID(), UUID.randomUUID(), "Peter", "Test", education, new ArrayList<>(Arrays.asList("python", "java")), new ArrayList<>(Arrays.asList(workExperience)), new ArrayList<>(Arrays.asList("baseball", "social work")));
        
        this.internship.addApplication(applicationTwo);
        this.internship.removeApplication(application);
        assertTrue(!this.internship.getApplications().contains(application) && this.internship.getApplications().contains(applicationTwo));
    } 
}
