package Testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

public class ResumeTester {

    InternshipApplication application;
    Student testStudent =  new Student("user","test","test@email.com","testUser1");
    UUID testUUID = testStudent.getId();

    @Test
    public void testCreateResume() {
        testStudent.createResume("not clemson","freshman","computer science");
        Resume testResume = application.getResume(testStudent.getId);
        assertEquals(testStudent.getResume, testResume);
    }

    @Test
    public void testChangeSkills() {
        testStudent.changeSkills("voodoo");
        boolean skillTrue = false;
        for(int i = 0; i < testStudent.getStudentSkills.size();i++)
        {
            if(testStudent.getStudentSkills(i).equalsIgnoreCase("voodoo"))
            {
                skillTrue = true;
            }
        }

        assertEquals(true, skillTrue);
    }

    @Test
    public void testChangeEducation() {
        testStudent.changeEducation("USC","CIS","junior");
        Resume testRes = testStudent.getResume;
        boolean eduTrue = false;

        if(testRes.getEducation.getMajor.equalsIgnoreCase("CIS"))
        {
            eduTrue = true;
        }

        assertEquals(true, eduTrue);
    }

    @Test
    public void testChangeExtra() {
        testStudent.changeExtra("Garnet Game Jammers");
        boolean extraTrue = false;
        for(int i = 0; i < testStudent.getExtraCirricularList.size();i++)
        {
            if(testStudent.getExtraCirricularList(i).equalsIgnoreCase("Garnet Game Jammers"))
            {
                extraTrue = true;
            }
        }

        assertEquals(true, extraTrue);
    }

    @Test
    public void testChangeWork() {

        WorkExperience wrkExpTest = new WorkExperience("should be first","Amazon","12/1/2032","12/1/2045"); 
        testStudent.changeWork("should be first","Amazon","12/1/2032","12/1/2045");
        WorkExperience wrkExp = testStudent.getWorkExperienceList.get(0);

        assertEquals(wrkExpTest, wrkExp);
    
    }

    @Test
    public void testGetResume() {
        assertEquals(application.getResume(testStudent.getId), testStudent.getResume);
    }

    @Test
    public void testDeleteResume() {
        application.deleteResume(testStudent.getId);
        assertEquals(null, testStudent.getResume);
    }
    
    @Test
    public void testSearchResumes() {

        ArrayList<Resume> resumes = new ArrayList<>();
        resumes.add(testStudent.getResume());
        assertEquals(0, application.searchResumes(resumes, "java"));
    }




}