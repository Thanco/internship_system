package Testing;
/**
 * @author Terry Hancock 2021
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.BeforeClass;
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
 * I wasn't sure how to split up the DataWriter and DataLoader, 
 */
public class JsonTester {
	ArrayList<Internship> internships = InternshipList.getInstance().getInternships();
	ArrayList<User> users = UserList.getInstance().getUsers();
	/**Internship templaInternship = new Internship(uuid, "employer", "title", "description", new ArrayList<>(), LocalDate.of(1, 1, 1), 
										LocalDate.of(1, 1, 1), 1, LocalDate.of(1, 1, 1), new HiddenSalary(), new ArrayList<>()); */

	@BeforeClass
	public static void jsonTestingSetup() {
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
	public void testReadingWritingNullStudent() {
		users.add(null);
		DataWriter.saveUsers();
		assertEquals(null, DataLoader.getUsers().get(0));
	}

	@Test
	public void testReadingWritingStudentNullAttributes() {
		ArrayList<User> testUsers = new ArrayList<>();
		users.add(new Student(null, "", "", "", "",
		new Resume(UUID.randomUUID(), null, "", "",
				new Education("", null, ""), null,
				null, null),
				null, null));
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
		assertEquals(null, DataLoader.getUsers().get(0));
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
		Student templateStudent = new Student(uuid, "firstName", "lastName", "email", "",
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
		users.add(templateStudent);
		DataWriter.saveUsers();
		users.add(templateStudent);
		DataWriter.saveUsers();
		users.add(templateStudent);
		DataWriter.saveUsers();
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


}
