// Zhubo Deng
// Lab 1 - Object Theory; Inner class; File IO; Serialization
// 04/24/2018
// MAC OS, Eclipse

package util_lab1;

import java.io.*;
import model.Automotive;
import model.OptionSet;

public class FileIO {
	
	/**
	 * Constructor
	 * Only using when new a object
	 */
	public FileIO() {
		
	}
	
	/**
	 * Read and build at the same time
	 * must be non static method
	 * 
	 * @param fileName
	 * @return The created auto object
	 */
	public Automotive readAndCreateAuto(String fileName) {		
		try {
			FileReader file = new FileReader(fileName); 
			BufferedReader buff = new BufferedReader(file); 
			
			// read in first 3 lines; create Automotive Object
			String autoName = buff.readLine();   // line 1; Ford Wagon ZTW
			float basePrice = Float.parseFloat(buff.readLine());   // line 2; 18445.00
			int opsetSize = Integer.parseInt(buff.readLine());   // line 3
			
			Automotive auto = new Automotive(autoName, basePrice, opsetSize);			
			
			// for loop read in each option set one by one
			// 1st opt, color
			for (int i = 0; i < opsetSize; ++i) {
				String opsetName = buff.readLine();        // opsetName; color, transmission
				int opSize = Integer.parseInt(buff.readLine());   //opsize, how many colors
				auto.setOptionSetName(opsetName, i);       // set "color" into name var.
				auto.setOptionSetSize(opSize, i);
				
				// create each option; opt[j]
				for (int j = 0; j < opSize; ++j) {
					String opNameAndPrice = buff.readLine();
					String splitArr[] = opNameAndPrice.split(",");
					String opName = splitArr[0];
					//System.out.println(opName);
					float opPrice = Float.parseFloat(splitArr[1]);
					//System.out.println(opPrice);
					
					auto.setOptionName(opName, j, i);
					auto.setOptionPrice(opPrice, j, i);										
				}
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
			
		} catch (IOException e) {
			System.out.println("Error ­­ " + e.toString()); 
		}
		return null;		
	}
	
	
	/** 
	 * serializeAuto
	 * must be non static method
	 * @param auto
	 */
	public void serializeAuto(Automotive auto) {		
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
	public Automotive deserializeAuto(String fileName) {
		Automotive auto = null;     // item be returned ??? null
		try {
			
			ObjectInputStream in = 
					new ObjectInputStream(new FileInputStream(fileName));
			auto = (Automotive) in.readObject();
			in.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print("Error:"+e);
			System.exit(1);
		}
		return auto;
	}
}
