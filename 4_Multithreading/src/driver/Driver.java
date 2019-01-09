package driver;

import adapter.*;
import exception.*;
import scale.*;

public class Driver {

	public static void main(String [] arg) throws AutoException {
				
		CreateAuto a1 = new BuildAuto();
		a1.buildAuto("FordZTW.txt");
		a1.printAuto("Ford Wagon ZTW");		

		Scalable scalable = new BuildAuto();
		String input1[] = {"Ford Wagon ZTW", "Transmission", "Trans"};
		scalable.operation(1, input1);
		a1.printAuto("Ford Wagon ZTW");		
		
		String input2[] = {"Ford Wagon ZTW", "Color", 
				"Fort Knox Gold Clearcoat Metallic", "100.0"};
		scalable.operation(2, input2);
		a1.printAuto("Ford Wagon ZTW");		
		
	}	
	
}
