package scale;

import java.util.LinkedHashMap;

import adapter.*;
import model.Automobile;

public class EditOptions extends Thread {

	// copy Hello.java
	// LHM is private - EditOption cannot access it - what is the fix?
	// Write access methods in ProxyAuto.
	
	private LinkedHashMap<String, Automobile> autoMap;
	private int threadNo;
	private int operationNo;
	private String [] input;
	
	public EditOptions(LinkedHashMap<String, Automobile> auto, int thrdNo, 
			int oprtnNo, String [] inputArr) {
		// TODO Auto-generated constructor stub
		this.autoMap = auto;
		this.threadNo = thrdNo;
		this.operationNo = oprtnNo;
		this.input = inputArr;
	}
	
	public synchronized void syncUpdateOptionSetName(String modelName, 
			String optionSetName, String newName) {
		autoMap.get(modelName).updateOpsetName(optionSetName, newName);
	}
	
	public synchronized void syncUpdateOptionPrice(String modelName, 
			String optionSetName, String optionName, float newPrice) {
		autoMap.get(modelName).updateOptionPrice(optionSetName, optionName, newPrice);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		switch (operationNo) {
		case 1:
			System.out.println("Start Thread " + threadNo + "\n");
			syncUpdateOptionSetName(input[0], input[1], input[2]);
			break;
		case 2:
			System.out.println("Start Thread " + threadNo + "\n");
			syncUpdateOptionPrice(input[0], input[1], input[2], Float.parseFloat(input[3]));
			break;
		default:
			break;
		}
	}
	
	public void ops() {
		// for future use
	}

}
