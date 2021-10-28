package Model;

import java.text.ParseException;
import java.util.Calendar;

public class WorkExperience {

	private String company;
	private Calendar start;
	private Calendar end;
	
	public WorkExperience(String company, String start, String end) {
		
		this.company = company;
		this.start = createCalendar(start);
		this.end = createCalendar(end);
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
	
	
	public WorkExperience(String company, Calendar start, Calendar end) throws ParseException {
		
		this.company = company;
		this.start = start;
		this.end = end;
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
		
		return "Work Experience: /n" + this.start.get(Calendar.MONTH) + "/" + this.start.get(Calendar.YEAR);
	}

}
