package exception;

import model.Automobile;
import model.OptionSet;

public class Fix1to100 {
	public Fix1to100() {
		
	}
	
	// Missing the name for Automobile in text file. 
	public void fix11(Automobile auto) {
		System.out.println("Missing Auto name, price or size"
				+ "in the txt file, automacitally set it to default value.");
		String name = "Ford Wagon ZTW";
		float bPrice = (float) 18445.0;
		int optionSetsize = 5;
		auto.setAutoName(name);
		auto.setAutoBasePrice(bPrice);
		auto.setOptionSetSize(optionSetsize);
	}
	
	// Missing OptionSet data (or part of it). 
	public void fix22() {
		System.out.println("Missing part of option set data, "
				+ "please check the txt file.");
		System.exit(0);
	}
	
	// Option Size; array out of bound.
	public void fix33() {
		System.out.println("Option size is too small, "
				+ "please check the txt file.");
		System.exit(0);
	}
	
	// Missing filename or wrong filename. 
	public void fix44() {
		System.out.println("File cannot be found, please check, "
				+ "please check the txt file.");
		System.exit(0);
	}
	
	// Missing Option name
	public void fix55() {
		System.out.println("Missing the option name for the option, "
				+ "please check the txt file.");
		System.exit(0);
	}
	
	// Missing Option price
	public void fix66() {
		System.out.println("Missing the option price for the option, "
				+ "please check the txt file.");
		System.exit(0);
	}
}
