package Model;

import java.text.ParseException;
import java.util.Calendar;
import java.util.ArrayList;

public class WorkExperience {
	private String title;
	private String company;
	private Calendar start;
	private Calendar end;
	private ArrayList<String> descriptions;
	
	 /**
     * Instantiates a workExperience object with given company name, start date and end date
     * 
     * @param company          	the company name as string.
     * @param start       		the start date as a string.
	 * @param end       		the end date as a string.
     */
	public WorkExperience(String title, String company, String start, String end) {
		this.title = title;
		this.company = company;
		this.start = createCalendar(start);
		this.end = createCalendar(end);
		this.descriptions = new ArrayList<>();
	}
	
	/**
	 * creates a calendar object from string
	 * @param string date
	 */
	public Calendar createCalendar(String date) {
		
		if (date.contains("/")) {
			
			Calendar c = Calendar.getInstance();
			String[] dateArr = date.split("/");
			
			c.set(Calendar.MONTH, Integer.parseInt(dateArr[0]) - 1);
			c.set(Calendar.YEAR, Integer.parseInt(dateArr[1]));

			return c;
		}
		
		else
			return null;
	}
	
	/**
	 * Constructor for WorkExperience from json
	 * @param title the title they held
	 * @param company the company worked at
	 * @param start the start date of the workExperience
	 * @param end the end date of the workExperience
	 * @param descriptions what was completed at that job
	 */
	public WorkExperience(String title, String company, Calendar start, Calendar end, ArrayList<String> descriptions) throws ParseException {
		this.title = title;
		this.company = company;
		this.start = start;
		this.end = end;
		this.descriptions = descriptions;
	}

	/**
	 * Adds a description to the descriptions list
	 * @param description the description to add
	 */
	public void addDescription(String description) {
		descriptions.add(description);
	}

	/**
	 * sets descriptions in the descriptions list
	 * @param ArrayList<String> descriptions the description to add
	 */
	public void setDescriptions(ArrayList<String> descriptions) {
		this.descriptions = descriptions;
	}

	/**
	 * gets the title of the work experience
	 * @returns string title
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * gets the company of the work experience
	 * @returns string company
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * sets a company name to the work experience object
	 * @param company name
	 */
	public void setCompany(String company) {
		this.company = company;
	}

	/**
	 * gets the start date of the work experience
	 * @return calendar Start
	 */
	public Calendar getStart() {
		
		return start;
	}

	/**
	 * gets descriptions array list
	 * @return arraylist of strings of descriptions
	 */
	public ArrayList<String> getDescriptions() {
		return descriptions;
	}

	/**
	 * sets start date to education
	 * @param Calendar start
	 */
	public void setStart(Calendar start) {
		
		this.start = start;
	}

	/**
	 * gets end date
	 * @return Calendar end
	 */
	public Calendar getEnd() {
		return end;
	}

	/**
	 * sets end date to education
	 * @param Calendar end
	 */
	public void setEnd(Calendar end) {
		
		this.end = end;
	}
	
	/**
	 * sets title to education
	 * @param String title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * used to make work experience a string
	 * @returns work experience as strings
	 */
	public String toString() {
		String ret = "\tTitle: " + this.getTitle() +
					"\n\t\tCompany: " + this.getCompany() +
					"\n\t\tStart Date: " + (this.start.get(Calendar.MONTH) + 1) + "/" + this.start.get(Calendar.YEAR) + 
					"\n\t\tEnd Date: " + (this.end.get(Calendar.MONTH) + 1) + "/" + this.end.get(Calendar.YEAR) +
					"\n\t\tDdescriptions:";
		for (String description : this.getDescriptions()) {
			ret += "\n\t\t\t" + description;
		}
		return ret;
	}

}
