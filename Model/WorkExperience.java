package Model;

import java.text.ParseException;
import java.util.Calendar;
import java.util.ArrayList;

public class WorkExperience {

	private String company;
	private Calendar start;
	private Calendar end;
	private ArrayList<String> descriptions;
	
	public WorkExperience(String company, String start, String end) {
		
		this.company = company;
		this.start = createCalendar(start);
		this.end = createCalendar(end);
		this.descriptions = new ArrayList<>();
	}
	
	public Calendar createCalendar(String date) {
		
		if (date.contains("/")) {
			
			Calendar c = Calendar.getInstance();
			String[] dateArr = date.split("/");
			
			c.set(Calendar.MONTH, Integer.parseInt(dateArr[0]));
			c.set(Calendar.YEAR, Integer.parseInt(dateArr[1]));

			return c;
		}
		
		else
			return null;
	}
	
	/**
	 * Constructor for WorkExperience from json
	 * @param company the company worked at
	 * @param start the start date of the workExperience
	 * @param end the end date of the workExperience
	 * @param descriptions what was completed at that job
	 */
	public WorkExperience(String company, Calendar start, Calendar end, ArrayList<String> descriptions) throws ParseException {
		
		this.company = company;
		this.start = start;
		this.end = end;
		this.descriptions = descriptions;
	}

	public void addDescription(String description) {
		descriptions.add(description);
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
	
	
	public String toString() {
		String ret = "\tCompany: " + this.getCompany() +
					"\n\tStart Date: " + this.start.get(Calendar.MONTH) + "/" + this.start.get(Calendar.YEAR) + 
					"\n\tEnd Date: " + this.end.get(Calendar.MONTH) + "/" + this.end.get(Calendar.YEAR) +
					"\n\tDdescriptions:\n";
		for (String description : this.getDescriptions()) {
			ret += "\t\t" + description + "\n";
		}
		return ret;
	}

}
