package Testing;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import JSON.DataLoader;
import JSON.DataWriter;
import Model.*;
import java.util.*;
import java.io.File;
import java.time.LocalDate;

/**
 * I wasn't sure how to split up the DataWriter and DataLoader, So I made this "json" tester to test writing and loading.
 *
 * @author Terry Hancock 2021
 */
public class JsonTester {
	ArrayList<Internship> internships = InternshipList.getInstance().getInternships();
	ArrayList<User> users = UserList.getInstance().getUsers();

	@BeforeClass
	public void jsonTestingSetup() {
		new File("src/JSON").mkdirs();
	}

	@BeforeEach
	public void setup() {
		internships.clear();
		users.clear();
		DataWriter.saveInternships();
		DataWriter.saveUsers();
	}

	@AfterEach
	public void tearDown() {
		internships.clear();
		users.clear();
		DataWriter.saveInternships();
		DataWriter.saveUsers();
	}

	@Test
	public void testWritingInternshipsBeforeInternshipListInitialization() {
		DataWriter.saveInternships();
		assertEquals(internships, DataLoader.getInternships());
	}

	@Test
	public void testWritingUsersBeforeUserListInitialization() {
		DataWriter.saveUsers();
		assertEquals(users, DataLoader.getUsers());
	}

	@Test
	public void testWritingEmptyUserList() {
		DataWriter.saveUsers();
		assertEquals(new ArrayList<User>(), DataLoader.getUsers());
	}

	@Test
	public void testWritingEmptyInternshipList() {
		DataWriter.saveInternships();
		assertEquals(new ArrayList<User>(), internships);
	}

	@Test
	public void testReadingWritingGoodStudent() {
		UUID uuid = UUID.randomUUID();
		Student templateStudent = new Student(uuid, "firstName", "lastName", "email", "password",
				new Resume(UUID.randomUUID(), uuid, "firstName", "lastName",
						new Education("schoolTitle", SchoolYear.FRESHMAN, "major"), new ArrayList<>(),
						new ArrayList<>(), new ArrayList<>()),
				new ArrayList<>(), new ArrayList<>());
		users.add(templateStudent);
		DataWriter.saveUsers();
		ArrayList<User> testUsers = DataLoader.getUsers();
		Student testStudent = (Student) testUsers.get(0);
		assertEquals(templateStudent.toString(), testStudent.toString());
	}

	@Test
	public void testReadingWritingNullUser() {
		users.add(null);
		DataWriter.saveUsers();
		assertEquals(null, DataLoader.getUsers().get(0));
	}

	@Test
	public void testReadingWritingStudentNullAttributes() {
		ArrayList<User> testUsers = new ArrayList<>();
		Student templateStudent = new Student(null, "", "", "", "",
				new Resume(null, null, "", "",
						new Education("", null, ""), null,
						null, null),
				null, null);
		users.add(templateStudent);
		DataWriter.saveUsers();
		testUsers = DataLoader.getUsers();
		Student testStudent = (Student) testUsers.get(0);
		assertEquals(null, testStudent.toString());
	}

	@Test
	public void testReadingWritingEmptyEmailStudent() {
		UUID uuid = UUID.randomUUID();
		Student templateStudent = new Student(uuid, "firstName", "lastName", "", "password",
				new Resume(UUID.randomUUID(), uuid, "firstName", "lastName",
						new Education("schoolTitle", SchoolYear.FRESHMAN, "major"), new ArrayList<>(),
						new ArrayList<>(), new ArrayList<>()),
				new ArrayList<>(), new ArrayList<>());
		users.add(templateStudent);
		DataWriter.saveUsers();
		ArrayList<User> testUsers = DataLoader.getUsers();
		Student testStudent = (Student) testUsers.get(0);
		assertEquals(templateStudent.toString(), testStudent.toString());
	}

	@Test
	public void testReadingWritingNullUuidStudent() {
		UUID uuid = null;
		Student templateStudent = new Student(uuid, "firstName", "lastName", "", "password",
				new Resume(UUID.randomUUID(), uuid, "firstName", "lastName",
						new Education("schoolTitle", SchoolYear.FRESHMAN, "major"), new ArrayList<>(),
						new ArrayList<>(), new ArrayList<>()),
				new ArrayList<>(), new ArrayList<>());
		users.add(templateStudent);
		DataWriter.saveUsers();
		assertEquals(templateStudent.toString(), DataLoader.getUsers().get(0).toString());
	}

	@Test
	public void testReadingWritingNullResumeUuidStudent() {
		UUID uuid = UUID.randomUUID();
		Student templateStudent = new Student(uuid, "firstName", "lastName", "", "password",
				new Resume(null, uuid, "firstName", "lastName",
						new Education("schoolTitle", SchoolYear.FRESHMAN, "major"), new ArrayList<>(),
						new ArrayList<>(), new ArrayList<>()),
				new ArrayList<>(), new ArrayList<>());
		users.add(templateStudent);
		DataWriter.saveUsers();
		ArrayList<User> testUsers = DataLoader.getUsers();
		Student testStudent = (Student) testUsers.get(0);
		assertEquals(templateStudent.toString(), testStudent.toString());
	}

	@Test
	public void testReadingWritingNullResumeOwnerUuidStudent() {
		UUID uuid = UUID.randomUUID();
		Student templateStudent = new Student(uuid, "firstName", "lastName", "", "password",
				new Resume(UUID.randomUUID(), null, "firstName", "lastName",
						new Education("schoolTitle", SchoolYear.FRESHMAN, "major"), new ArrayList<>(),
						new ArrayList<>(), new ArrayList<>()),
				new ArrayList<>(), new ArrayList<>());
		users.add(templateStudent);
		DataWriter.saveUsers();
		ArrayList<User> testUsers = DataLoader.getUsers();
		Student testStudent = (Student) testUsers.get(0);
		assertEquals(templateStudent.toString(), testStudent.toString());
	}

	@Test
	public void testReadingWritingEmptyPasswordStudent() {
		UUID uuid = UUID.randomUUID();
		Student templateStudent = new Student(uuid, "firstName", "lastName", "email", "",
				new Resume(UUID.randomUUID(), uuid, "firstName", "lastName",
						new Education("schoolTitle", SchoolYear.FRESHMAN, "major"), new ArrayList<>(),
						new ArrayList<>(), new ArrayList<>()),
				new ArrayList<>(), new ArrayList<>());
		users.add(templateStudent);
		DataWriter.saveUsers();
		ArrayList<User> testUsers = DataLoader.getUsers();
		Student testStudent = (Student) testUsers.get(0);
		assertEquals(templateStudent.toString(), testStudent.toString());
	}

	@Test
	public void testReadingWritingNullRatingsStudent() {
		UUID uuid = UUID.randomUUID();
		Student templateStudent = new Student(uuid, "firstName", "lastName", "email", "password",
				new Resume(UUID.randomUUID(), uuid, "firstName", "lastName",
						new Education("schoolTitle", SchoolYear.FRESHMAN, "major"), new ArrayList<>(),
						new ArrayList<>(), new ArrayList<>()),
				new ArrayList<>(), null);
		users.add(templateStudent);
		DataWriter.saveUsers();
		ArrayList<User> testUsers = DataLoader.getUsers();
		Student testStudent = (Student) testUsers.get(0);
		assertEquals(templateStudent.toString(), testStudent.toString());
	}

	@Test
	public void testReadingWritingTwoStudents() {
		UUID uuid = UUID.randomUUID();
		Student templateStudent = new Student(uuid, "firstName", "lastName", "email", "password",
				new Resume(UUID.randomUUID(), uuid, "firstName", "lastName",
						new Education("schoolTitle", SchoolYear.FRESHMAN, "major"), new ArrayList<>(),
						new ArrayList<>(), new ArrayList<>()),
				new ArrayList<>(), new ArrayList<>());
		users.add(templateStudent);
		users.add(templateStudent);
		DataWriter.saveUsers();
		assertEquals(2, DataLoader.getUsers().size());
	}

	@Test
	public void testReadingWritingFourStudentsWithSavesInBetween() {
		UUID uuid = UUID.randomUUID();
		Student templateStudent = new Student(uuid, "firstName", "lastName", "email", "password",
				new Resume(UUID.randomUUID(), uuid, "firstName", "lastName",
						new Education("schoolTitle", SchoolYear.FRESHMAN, "major"), new ArrayList<>(),
						new ArrayList<>(), new ArrayList<>()),
				new ArrayList<>(), new ArrayList<>());
		users.add(templateStudent);
		DataWriter.saveUsers();
		users = UserList.getInstance().getUsers();
		users.add(templateStudent);
		DataWriter.saveUsers();
		users = UserList.getInstance().getUsers();
		users.add(templateStudent);
		DataWriter.saveUsers();
		users = UserList.getInstance().getUsers();
		users.add(templateStudent);
		DataWriter.saveUsers();
		users = UserList.getInstance().getUsers();
		assertEquals(4, DataLoader.getUsers().size());
	}

	@Test
	public void testReadingWritingGoodEmployer() {
		UUID uuid = UUID.randomUUID();
		Employer templateEmployer = new Employer(uuid, "firstName", "lastName", "email", "password",
												true, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
		users.add(templateEmployer);
		DataWriter.saveUsers();
		assertEquals(templateEmployer.toString(), DataLoader.getUsers().get(0).toString());
	}

	@Test
	public void testReadingWritingBlankEmployer() {
		Employer templateEmployer = new Employer(null, "", "", "", "",
												true, null, null, null);
		users.add(templateEmployer);
		DataWriter.saveUsers();
		assertEquals(templateEmployer.toString(), DataLoader.getUsers().get(0).toString());
	}

	@Test
	public void testReadingWritingEmployerWithOnlyId() {
		Employer templateEmployer = new Employer(UUID.randomUUID(), "", "", "", "",
												true, null, null, null);
		users.add(templateEmployer);
		DataWriter.saveUsers();
		assertEquals(templateEmployer.toString(), DataLoader.getUsers().get(0).toString());
	}

	@Test
	public void testReadingWritingEmplyStringsEmployer() {
		Employer templateEmployer = new Employer(UUID.randomUUID(), "", "", "", "",
												true, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
		users.add(templateEmployer);
		DataWriter.saveUsers();
		assertEquals(templateEmployer.toString(), DataLoader.getUsers().get(0).toString());
	}

	@Test
	public void testReadingWritingNullEmployer() {
		users.add(null);
		DataWriter.saveUsers();
		assertEquals(null, DataLoader.getUsers().get(0).toString());
	}

	@Test
	public void testReadingWritingFourEmployers() {
		UUID uuid = UUID.randomUUID();
		Employer templateEmployer = new Employer(uuid, "firstName", "lastName", "email", "password",
												true, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
		users.add(templateEmployer);
		DataWriter.saveUsers();
		users = UserList.getInstance().getUsers();
		users.add(templateEmployer);
		DataWriter.saveUsers();
		users = UserList.getInstance().getUsers();
		users.add(templateEmployer);
		DataWriter.saveUsers();
		users = UserList.getInstance().getUsers();
		users.add(templateEmployer);
		DataWriter.saveUsers();
		users = UserList.getInstance().getUsers();
		assertEquals(4, DataLoader.getUsers().size());
	}

	@Test
	public void testReadingWritingGoodAdmin() {
		Admin templateAdmin = new Admin(UUID.randomUUID(), "firstName", "lastName", "email", "password");
		users.add(templateAdmin);
		DataWriter.saveUsers();
		assertEquals(templateAdmin.toString(), DataLoader.getUsers().get(0).toString());
	}

	@Test
	public void testReadingWritingBlankAdmin() {
		Admin templateAdmin = new Admin(null, "", "", "", "");
		users.add(templateAdmin);
		DataWriter.saveUsers();
		assertEquals(templateAdmin.toString(), DataLoader.getUsers().get(0).toString());
	}

	@Test
	public void testReadingWritingAdminOnlyId() {
		Admin templateAdmin = new Admin(UUID.randomUUID(), "", "", "", "");
		users.add(templateAdmin);
		DataWriter.saveUsers();
		assertEquals(templateAdmin.toString(), DataLoader.getUsers().get(0).toString());
	}

	@Test
	public void testReadingWritingUsersWithDifferentTypes() {
		UUID uuid = UUID.randomUUID();
		Student templateStudent = new Student(uuid, "firstName", "lastName", "email", "password",
				new Resume(UUID.randomUUID(), uuid, "firstName", "lastName",
						new Education("schoolTitle", SchoolYear.FRESHMAN, "major"), new ArrayList<>(),
						new ArrayList<>(), new ArrayList<>()),
				new ArrayList<>(), new ArrayList<>());
		Employer templateEmployer = new Employer(UUID.randomUUID(), "", "", "", "",
												true, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
		Admin templateAdmin = new Admin(UUID.randomUUID(), "firstName", "lastName", "email", "password");
		users.add(templateStudent);
		users.add(templateEmployer);
		users.add(templateAdmin);
		DataWriter.saveUsers();
		assertEquals(3, DataLoader.getUsers().size());
	}

	@Test 
	public void testReadingWritingGoodInternship() {
		LocalDate date = LocalDate.of(2000, 1, 1);
		Internship templateInternship = new Internship(UUID.randomUUID(), "employer", "title", "description", new ArrayList<>(), 
														date, date, 1, date, new HiddenSalary(), new ArrayList<>());
		internships.add(templateInternship);
		DataWriter.saveInternships();
		assertEquals(templateInternship.toString(), DataLoader.getInternships().get(0).toString());
	}

	@Test
	public void testReadingWritingBlankInternship() {
		Internship templateInternship = new Internship(null, "", "", "", null, 
														null, null, 0, null, null, null);
		internships.add(templateInternship);
		DataWriter.saveInternships();
		assertEquals(templateInternship.toString(), DataLoader.getInternships().get(0).toString());
	}

	@Test 
	public void testReadingWritingNullIdInternship() {
		LocalDate date = LocalDate.of(2000, 1, 1);
		Internship templateInternship = new Internship(null, "employer", "title", "description", new ArrayList<>(), 
														date, date, 1, date, new HiddenSalary(), new ArrayList<>());
		internships.add(templateInternship);
		DataWriter.saveInternships();
		assertEquals(templateInternship.toString(), DataLoader.getInternships().get(0).toString());
	}

	@Test
	public void testReadingWritingNullSalaryInternship() {
		LocalDate date = LocalDate.of(2000, 1, 1);
		Internship templateInternship = new Internship(UUID.randomUUID(), "employer", "title", "description", new ArrayList<>(), 
														date, date, 1, date, null, new ArrayList<>());
		internships.add(templateInternship);
		DataWriter.saveInternships();
		assertEquals(templateInternship.toString(), DataLoader.getInternships().get(0).toString());
	}

	@Test
	public void testReadingWritingNullDatesInternship() {
		LocalDate date = null;
		Internship templateInternship = new Internship(UUID.randomUUID(), "employer", "title", "description", new ArrayList<>(), 
														date, date, 1, date, new HiddenSalary(), new ArrayList<>());
		internships.add(templateInternship);
		DataWriter.saveInternships();
		assertEquals(templateInternship.toString(), DataLoader.getInternships().get(0).toString());
	}

	@Test
	public void testReadingWritingFourGoodInternships() {
		LocalDate date = LocalDate.of(2000, 1, 1);
		Internship templateInternship = new Internship(UUID.randomUUID(), "employer", "title", "description", new ArrayList<>(), 
														date, date, 1, date, new HiddenSalary(), new ArrayList<>());
		internships.add(templateInternship);
		DataWriter.saveInternships();
		internships = InternshipList.getInstance().getInternships();
		internships.add(templateInternship);
		DataWriter.saveInternships();
		internships = InternshipList.getInstance().getInternships();
		internships.add(templateInternship);
		DataWriter.saveInternships();
		internships = InternshipList.getInstance().getInternships();
		internships.add(templateInternship);
		DataWriter.saveInternships();
		assertEquals(4, DataLoader.getInternships().size());
	}
}
