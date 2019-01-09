package util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Properties;
import java.util.StringTokenizer;

import exception.AutoException;
import model.Automobile;

public class FileIO {

	/**
	 * Build an automotive object by reading information from the file.
	 * @param filename input text file name
	 * @return automobile
	 * @throws AutoException 
	 */
	public static Automobile buildAutoObject(String filename) 
										throws AutoException {
		Automobile auto = null;
		BufferedReader buff = null;
		try {
			buff = new BufferedReader(new FileReader(filename));
			String line = buff.readLine();
			StringTokenizer st = new StringTokenizer(line, ",");
			String autoName = st.nextToken();
			String autoMake = st.nextToken();
			String autoModel = st.nextToken();
			if (!st.hasMoreTokens()) {
				throw new AutoException(1);
			}
			float basePrice = 0;
			try {
				basePrice = Float.parseFloat(st.nextToken());
			} catch (NumberFormatException ne) {
				throw new AutoException(1);
			}
			if (!st.hasMoreTokens()) {
				throw new AutoException(1);
			}
			int setSize = Integer.parseInt(st.nextToken());
			auto = new Automobile(autoName, autoMake,
									autoModel, basePrice, setSize);
			for (int i = 0; i < setSize; i++) {
				line = buff.readLine();
				st = new StringTokenizer(line, ",");
				if (!st.hasMoreTokens()) {
					throw new AutoException(2);
				}
				String opsetName = st.nextToken();
				if (!st.hasMoreTokens()) {
					throw new AutoException(3);
				}
				int optSize = Integer.parseInt(st.nextToken());
				auto.setOptionSetName(i, opsetName);
				auto.setOptionSize(i, optSize);
				for (int j = 0; j < optSize; j++) {
					line = buff.readLine();
					st = new StringTokenizer(line, ",");
					if (!st.hasMoreTokens()) {
						throw new AutoException(4);
					}
					auto.setOptionName(j, st.nextToken(), opsetName);
					if (!st.hasMoreTokens()) {
						throw new AutoException(5);
					}
					float price = 0;
					try {
						price = Float.parseFloat(st.nextToken());
					} catch (NumberFormatException ne) {
						throw new AutoException(5);
					}
					auto.setOptionPrice(j, price, opsetName);
				}
			}
		} catch (FileNotFoundException fe) {
			throw new AutoException(6);
		} catch (IOException e) {
			System.out.println("IOException: " + e.toString());
		} finally {
			if (buff != null) {
				try {
					buff.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return auto;
	}

	/**
	 * Build an automotive object by reading information from the property file.
	 * @param filename input property file name
	 * @return automobile
	 * @throws AutoException 
	 */
	public static Automobile buildAutoFromProp(String propFilename) 
										throws AutoException {
		Automobile auto = new Automobile();
		Properties props= new Properties();
		try {
			FileInputStream in = new FileInputStream(propFilename);
			props.load(in);
			String carName = props.getProperty("CarName");
        	if (!carName.equals(null)) {
				String carModel = props.getProperty("CarModel");
	            String carMake = props.getProperty("CarMake");
	            float basePrice = Float.parseFloat(props.getProperty("Price"));
	            String option1 = props.getProperty("Option1");
	            String optionValue1a = props.getProperty("OptionValue1a");
	            float optPrice1a = Float.parseFloat(
	            					props.getProperty("OptionValuePrice1a"));
	            String optionValue1b = props.getProperty("OptionValue1b");
	            float optPrice1b = Float.parseFloat(
	            					props.getProperty("OptionValuePrice1b"));
	            String option2 = props.getProperty("Option2");
	            String optionValue2a = props.getProperty("OptionValue2a");
	            float optPrice2a = Float.parseFloat(
	            					props.getProperty("OptionValuePrice1a"));
	            String optionValue2b = props.getProperty("OptionValue2b");
	            float optPrice2b = Float.parseFloat(
	            					props.getProperty("OptionValuePrice2b"));
	            auto.setName(carName);
	            auto.setModel(carModel);
	            auto.setMake(carMake);
	            auto.setBasePrice(basePrice);
	            auto.addOptionSet(option1);
	            auto.addOption(optionValue1a, optPrice1a, option1);
	            auto.addOption(optionValue1b, optPrice1b, option1);
	            auto.addOptionSet(option2);
	            auto.addOption(optionValue2a, optPrice2a, option2);
	            auto.addOption(optionValue2b, optPrice2b, option2);
        	}
        	in.close();
		} catch (FileNotFoundException fe) {
			throw new AutoException(6);
		} catch (IOException e) {
			System.out.println("IOException: " + e.toString());
		}
		return auto;
	}

	/**
	 * Serialize an automotive object.
	 * @param auto automotive
	 * @param filename output file name
	 */
	public static void serializeAuto(Automobile auto, String filename) {
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream(filename));
			out.writeObject(auto);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Deserialize an automotive object.
	 * @param filename input file name
	 * @return automotive
	 */
	public static Automobile deserializeAuto(String filename) {
		ObjectInputStream in = null;
		Automobile auto = null;
		try {
			in = new ObjectInputStream(new FileInputStream(filename));
			auto = (Automobile) in.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}  finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return auto;
	}
}
