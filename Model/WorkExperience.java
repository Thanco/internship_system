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
	
	public WorkExperience(String company, String start, String end) {
		//TODO implement title and descritpions to front end
		this.company = company;
		this.start = createCalendar(start);
		this.end = createCalendar(end);
		this.descriptions = new ArrayList<>();
	}
	
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

	public String getTitle() {
		return this.title;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Calendar getStart() {
		
		return start;
	}

	public ArrayList<String> getDescriptions() {
		return descriptions;
	}

	public void setStart(Calendar start) {
		
		this.start = start;
	}

	public Calendar getEnd() {
		
		return end;
	}

	public void setEnd(Calendar end) {
		
		this.end = end;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String toString() {
		String ret = "\tTitle: " + this.getTitle() +
					"\n\tCompany: " + this.getCompany() +
					"\n\tStart Date: " + (this.start.get(Calendar.MONTH) + 1) + "/" + this.start.get(Calendar.YEAR) + 
					"\n\tEnd Date: " + (this.end.get(Calendar.MONTH) + 1) + "/" + this.end.get(Calendar.YEAR) +
					"\n\tDdescriptions:\n";
		for (String description : this.getDescriptions()) {
			ret += "\t\t" + description + "\n";
		}
		return ret;
	}

}
