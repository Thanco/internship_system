package Model;

import java.util.ArrayList;

public class ExtraCirricular {
	
	private ArrayList<String> extraCirricularList = new ArrayList<String>();
	
	public ExtraCirricular(String extraCirricular) {
		
		this.extraCirricularList = makeArrayList(extraCirricular);
	}

	public ArrayList<String> getExtraCirricularList() {
		return extraCirricularList;
	}

	public void setExtraCirricularList(ArrayList<String> extraCirricularList) {
		this.extraCirricularList = extraCirricularList;
	}
	
	public ArrayList<String> makeArrayList(String extraCirricular) {
		
		String[] tempStr = extraCirricular.split(",");
		ArrayList<String> temp = new ArrayList<String>();
		
		for (int i =0; i < tempStr.length; i++) {
			
			temp.add(tempStr[i]);
		}
		
		return temp;
	}
}
