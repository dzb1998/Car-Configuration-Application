// Zhubo Deng
// Lab 1 - Object Theory; Inner class; File IO; Serialization
// 04/24/2018
// MAC OS, Eclipse

package util;

import java.io.*;
import model.*;
import exception.*;

public class FileIO {
	
	/**
	 * Constructor
	 * Only using when new a object
	 */
	public FileIO() {
		
	}
	
	// In short we try to catch exceptions thrown by others.
	// Solution ways: "throw new xxx" must be with "try block" or "throws xxx"
	
	// "throw": 
	// Whenever a method wants to throw exception it just says throw me 
	// and it can catch that exception there it self using a try catch block. 
	// (See InvalidUserNameException). 

	// "throws": used for when this function being called, 
	//           whether there is a error or not, deal outside this function
	// If method doesn't want to catch it but want to give this responsibility 
	// to caller whoever is calling, then it throws the exception. In such a 
	// case if you want to use checkUser() function, you should catch that 
	// exception or pass the responsibility to its caller until somebody 
	// handles it.
	
	/**
	 * Read and build at the same time
	 * must be non static method
	 * 
	 * @param fileName
	 * @return The created auto object
	 */
	public Automobile readAndCreateAuto(String fileName) throws AutoException {		
		
		String autoName;
		float basePrice;
		int opsetSize;
		Automobile auto = new Automobile();
		
		try {
			FileReader file = new FileReader(fileName); 
			BufferedReader buff = new BufferedReader(file); 
			
			try {
				// read in first 3 lines; create Automotive Object
				autoName = buff.readLine();   // line 1; Ford Wagon ZTW
				basePrice = Float.parseFloat(buff.readLine());   // line 2; 18445.00
				opsetSize = Integer.parseInt(buff.readLine());   // line 3
				
				auto = new Automobile(autoName, basePrice, opsetSize);		
			} catch (IllegalArgumentException e) {
				// TODO: handle exception
				AutoException ae = new AutoException("Missing name, price or size!", 
						AutoException.MISSING_PRICE_IN_TEXTFILE);	
				ae.fix(auto);
			}

			// for loop read in each option set one by one
			// 1st opt, color
			try {
				opsetSize = auto.getOptionSetSize();
				//while (buff.readLine() != null) {
				//for(String line = buff.readLine(); line != null; line = buff.readLine()){
					
					for (int i = 0; i < opsetSize; ++i) {
						//System.out.println(line);
						String opsetName;  // = buff.readLine();
						String line;
						
						// skip the empty line
						do {
							opsetName = buff.readLine();
						} while (opsetName.equals(""));
						
						// line != null; line is "color" (the 1st)
						// i == 0; special case, only the first time run through here
						/*
						if (line != null && i == 0) {
							opsetName = line;
						}
						else {
							opsetName = buff.readLine();   // opsetName; color, transmission
						}
						*/
						
						int opSize = Integer.parseInt(buff.readLine());   //opsize, how many colors
						auto.setOptionSetName(opsetName, i);       // set "color" into name var.
						auto.setOptionSetSize(opSize, i);
						
						// create each option; opt[j]
						for (int j = 0; j < opSize; ++j) {
							String opNameAndPrice = buff.readLine();
							String splitArr[] = opNameAndPrice.split(",");
							String opName = splitArr[0];
							if (opName == null) 
								throw new AutoException("Missing price or size!", 
										AutoException.MISSING_OPTION_NAME);
							
							float opPrice = Float.parseFloat(splitArr[1]);
							auto.setOptionName(opName, j, i);
							auto.setOptionPrice(opPrice, j, i);										
						}
					}

				//}

			} catch (AutoException e) {
				// TODO: handle exception
				e.fix(auto);
			} catch (ArrayIndexOutOfBoundsException e) {
				// TODO: handle exception
				throw new AutoException("Option size too small!", 
						AutoException.ERROR_OPTION_SIZE);
			} catch (NumberFormatException e) {
				// TODO: handle exception
				throw new AutoException("Missing price or size!", 
						AutoException.MISSING_OPTION_PRICE);
			}
			
			
			boolean eof = false; 
			while (!eof) {
				String line = buff.readLine(); 
				if (line == null)
					eof = true; 
				else
					System.out.println(line);
			}		
			buff.close();
			file.close();
			return auto;
		}
		catch (IOException e) {
			// TODO: handle exception
			throw new AutoException("File not found!", AutoException.ERROR_FILENAME);
			// throw new == will be caught by outside function
			//AutoException aException = new AutoException();
			// just initiate a new object
		}
		finally {
			
		}
	}
	
	
	/** 
	 * serializeAuto
	 * must be non static method
	 * @param auto
	 */
	public void serializeAuto(Automobile auto) {		
		try {
						
			ObjectOutputStream out = 
					new ObjectOutputStream(new FileOutputStream("auto.ser"));
			out.writeObject(auto);
			out.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print("Error:"+e);          // expectation requirement ???
			System.exit(1);
		}
	}	
	
	/** 
	 * deserializeAuto
	 * must be non static method
	 * 
	 * @param fileName
	 *        The file.ser which was already created by serializing
	 * @return The auto object
	 */
	public Automobile deserializeAuto(String fileName) {
		Automobile auto = null;     // item be returned ??? null
		try {
			
			ObjectInputStream in = 
					new ObjectInputStream(new FileInputStream(fileName));
			auto = (Automobile) in.readObject();
			in.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print("Error:"+e);
			System.exit(1);
		}
		return auto;
	}
}
