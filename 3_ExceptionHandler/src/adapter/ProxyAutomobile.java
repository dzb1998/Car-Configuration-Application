package adapter;

import java.awt.RenderingHints.Key;
import java.util.LinkedHashMap;

import exception.AutoException;
import model.*;
import util.FileIO;

public abstract class ProxyAutomobile {
	
	//private static Automobile a1;
	private static LinkedHashMap<String, Automobile> autoMap = new LinkedHashMap<>();
	
	// Given a text file name a method called BuildAuto can be written to build an 
	// instance of Automobile. This method does not have to return the Auto instance.
	public void buildAuto(String fileName) {
		FileIO fileIO = new FileIO();
		Automobile a1 = new Automobile();
		try {
			a1 = fileIO.readAndCreateAuto(fileName);
		} catch (AutoException e) {
			// TODO Auto-generated catch block
			e.fix(a1);
		}
		String autoKey = a1.getAutoName();
		autoMap.put(autoKey, a1);
	}
	
	// This function searches and prints the properties of a given Automobile.
	public void printAuto(String modelName) {
		System.out.println(autoMap.get(modelName));
	}
	
	// This function searches the Model for a given OptionSet and sets the 
	// name of OptionSet to newName.
	public void updateOptionSetName(String modelName, String optionSetName, String newName) {
		autoMap.get(modelName).updateOpsetName(optionSetName, newName);
	}
	
	// This function searches the Model for a given OptionSet and Option name, 
	// and sets the price to newPrice.
	public void updateOptionPrice(String modelName, String optionSetName, String optionName, float newPrice) {
		autoMap.get(modelName).updateOptionPrice(optionSetName, optionName, newPrice);
	}
	
	
}
