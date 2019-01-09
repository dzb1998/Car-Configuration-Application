// Zhubo Deng
// Lab 1 - Object Theory; Inner class; File IO; Serialization
// 04/24/2018
// MAC OS, Eclipse

package driver;

import util_lab1.FileIO;
import model.*;

class Driver {
	public static void main(String [] args) {
		// Build Automobile Object from a file.
		// (Some instance method in a class of Util package)		
		FileIO fileIO = new FileIO();
		Automotive FordZTW = fileIO.readAndCreateAuto("FordZTW.txt");
		
		// Print attributes before serialization		
		// FordZTW.print();  // print the FordZTW object
		System.out.println(FordZTW);
		
		// Serialize the object	
		fileIO.serializeAuto(FordZTW);
		
		// Deserialize the object and read it into memory.
		Automotive newFordZTW = fileIO.deserializeAuto("auto.ser"); 
		
		// Print new attributes.		
		// newFordZTW.print();  // print the NEW FordZTW object
		System.out.println(newFordZTW);
	} 
}