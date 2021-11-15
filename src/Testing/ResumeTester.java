package Testing;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import java.util.ArrayList;

import UserInterface.*;
import Model.*;

public class ResumeTester {

    InternshipApplication application = new InternshipApplication();
    Student testStudent =  new Student("user","test","test@email.com","testUser1");
    UUID testUUID = testStudent.getId();

    @Test
    public void testCreateResume() {
        application.login("test@email.com","testUser1");
        application.createResume("not clemson","freshman","computer science");
        Resume testResume = application.getResume(testStudent.getId());
        assertEquals(testStudent.getResume(), testResume);
    }

    @Test
    public void testChangeSkills() {
        application.login("test@email.com","testUser1");
        application.changeSkills("voodoo");
        boolean skillTrue = false;

        for(int i = 0; i < testStudent.getResume().getStudentSkills().size(); i++)
        {
            if(testStudent.getResume().getStudentSkills().get(i).equalsIgnoreCase("voodoo"))
            {
                skillTrue = true;
            }
        }

        assertEquals(true, skillTrue);
    }

    @Test
    public void testChangeEducation() {
        application.login("test@email.com","testUser1");
        application.changeEducation("USC","CIS","junior");
        Resume testRes = testStudent.getResume();
        boolean eduTrue = false;

        if(testRes.getEducation().getMajor().equalsIgnoreCase("CIS"))
        {
            eduTrue = true;
        }

        assertEquals(true, eduTrue);
    }

    @Test
    public void testChangeExtra() {
        application.login("test@email.com","testUser1");
        application.changeExtra("Garnet Game Jammers");
        boolean extraTrue = false;
        for(int i = 0; i < testStudent.getResume().getExtraCirricularList().size();i++)
        {
            if(testStudent.getResume().getExtraCirricularList().get(i).equalsIgnoreCase("Garnet Game Jammers"))
            {
                extraTrue = true;
            }
        }

        assertEquals(true, extraTrue);
    }

    @Test
    public void testChangeWork() {
        WorkExperience wrkExpTest = new WorkExperience("should be first","Amazon","12/1/2032","12/1/2045"); 
        application.login("test@email.com","testUser1");
        application.changeWork("should be first","Amazon","12/1/2032","12/1/2045");
        WorkExperience wrkExp = testStudent.getResume().getWorkExperienceList().get(0);
        assertEquals(wrkExpTest, wrkExp);
    }

    @Test
    public void testGetResume() {
        application.login("test@email.com","testUser1");
        assertEquals(application.getResume(testStudent.getId()), testStudent.getResume());
    }

    @Test
    public void testDeleteResume() {
        application.login("test@email.com","testUser1");
        application.deleteResume(testStudent.getId());
        assertEquals(null, testStudent.getResume());
    }
    
    @Test
    public void testSearchResumes() {
        ArrayList<Resume> resumes = new ArrayList<Resume>();
        resumes.add(testStudent.getResume());
        assertEquals(0, application.searchResumes(resumes, "java"));
    }

}