package driver;

import adapter.*;
import exception.AutoException;

public class Driver {

	public static void main(String [] arg) throws AutoException {
		
		CreateAuto a1 = new BuildAuto();
		a1.buildAuto("FordZTW.txt");
		a1.printAuto("FordWagonZTW");		
		
		UpdateAuto a2 = new BuildAuto();
		a2.updateOptionSetName("Ford Wagon ZTW", "Transmission", "Trans");
		a1.printAuto("FordWagonZTW");
		a2.updateOptionPrice("Ford Wagon ZTW", "Color", 
				"Fort Knox Gold Clearcoat Metallic", (float) 100.0);
		a1.printAuto("FordWagonZTW");
		
	}	
	
}
