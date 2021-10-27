package Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WorkExperience {

	private String company;
	private int workLength;
	private Date start;
	private Date end;
	
	public WorkExperience(String company, int workLength, String start, String end) {
		
		this.company = company;
		this.workLength = workLength;
		this.start = new SimpleDateFormat("dd/MM/yyyy").parse(start);
		this.end = new SimpleDateFormat("dd/MM/yyyy").parse(end);
	}

	public int getWorkLength() {
		return workLength;
	}

	public void setWorkLength(int workLength) {
		this.workLength = workLength;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}
	
	public String toString() {
		
		return "Work Experience: /n" + this.company + ": " + this.start + "-" + this.end;
	}
}
