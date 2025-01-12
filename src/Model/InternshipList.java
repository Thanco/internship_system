package Model;

import java.util.ArrayList;
import java.util.UUID;

import JSON.DataLoader;

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
		this.internships = DataLoader.getInternships();
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

	/**
	 * Add an internship to the list.
	 * @param internship the internship to add.
	 */
	public void addInternship(Internship internship){
		internships.add(internship);
	}

	/**
	 * Returns internship list. 
	 * @return internship list.
	 */
	public ArrayList<Internship> getInternships() {
		return this.internships;
	}

	/**
	 * Get an internships by keyword.
	 * @param keyword all internships that contain the keyword
	 * @return the internships with the keyword.
	 */
	public ArrayList<Internship> getInternshipByKeyword(String keyword){
		ArrayList<Internship> internshipsWithKeyword = new ArrayList<>();
		for(Internship internship : internships){
			if(internship.toString().toUpperCase().contains(keyword.toUpperCase())){
				internshipsWithKeyword.add(internship);
			}
		}
		return internshipsWithKeyword;
	}
}
