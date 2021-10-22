import java.util.ArrayList;
import java.util.UUID;

/**
 * Class that contains all internship listings currently loaded into the
 * program.
 * 
 * @author Bjorn Sauter
 */
public class InternshipList {

	private static InternshipList internshipList;
	private ArrayList<Internship> internships;

	/**
	 * Private constructor used in Singleton design pattern.
	 */
	private InternshipList() {
		this.internships = new ArrayList<>();
	}

	/**
	 * Returns instance of internship list.
	 * 
	 * @return the instance
	 */
	public static InternshipList getInstance() {
		if (internshipList == null) {
			internshipList = new InternshipList();
		}
		return internshipList;
	}

	/**
	 * Sets the provided internships
	 * 
	 * @param internshipList the provided internships as array list.
	 */
	public void setInternshipList(ArrayList<Internship> internshipList) {
		this.internships = internshipList;
	}

	/**
	 * Returns the internship that matches a given .
	 * 
	 * @param internshipId the given id.
	 * @return the internship with the id.
	 */
	public Internship getInternshipById(UUID internshipId) {
		for (Internship internship : internships) {
			if (internship.getId().equals(internshipId)) {
				return internship;
			}
		}
		return null;
	}

	/**
	 * Removes an internship with a given Id
	 * @param internshipId the id of the internship to delete.
	 */
	public void removeInternshipById(UUID internshipId) {

		internships.removeIf(internship -> internship.getId().equals(internshipId));
	}

}
