// Zhubo Deng
// Lab 1 - Object Theory; Inner class; File IO; Serialization
// 04/24/2018
// MAC OS, Eclipse

package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * using dynamic binding
 * containment / pseudocode 
 * option in optionSet, protected; fileIO, public
 * NOTHING static
 * @author dengzhubo
 *
 */
public class OptionSet implements Serializable {
	
	private String opsetName;
	private ArrayList<Option> opt;
	private Option choice;

	// constructors
	/**
	 * default constructor
	 */
	protected OptionSet() { 
		
	}
	
	/**
	 * Constructor
	 * @param n
	 *        the option set name
	 */
	protected OptionSet(String n) {
		opsetName = n;
	}

	/**
	 * Constructor
	 * @param n
	 *        the option set name
	 * @param size
	 *        the option set size; how mant options
	 */
	protected OptionSet(String n, int size) {
		opsetName = n;
		opt = new ArrayList<>(size);
	}	
	
	// getters
	/**
	 * Get the option set name
	 * @return the option set name
	 */
	protected String getOpsetName() {
		return opsetName;
	}
	
	/**
	 * Get the option array
	 * @return the option array
	 */
	protected ArrayList<Option> getOpt() {
		return opt;
	}
	
	// setters
	/**
	 * Set the option set name
	 * @param n
	 *        the option set name
	 */
	protected void setOpsetName(String n) {
		opsetName = n;
	}

	/**
	 * Set the option set size
	 * @param size
	 *        the option set size
	 */
	protected void setSize(int size) {
		opt = new ArrayList<>(size);
	}
	
	/**
	 * Set the option object
	 * @param newOpt
	 *        the new option object
	 * @param i
	 *        index
	 */
	protected void setOPObj(Option newOpt, int i) {
		for (Option option : opt) {
			if (opt.indexOf(option) == i) {
				option = newOpt;
			}
		}
	}
	
	/**
	 * Set the name of the option
	 * Has a linked method in Automotive class to initiate
	 * 
	 * @param n
	 *        name
	 * @param i
	 *        The index of option[]
	 */
	protected void setOPName(String n, int i) {
		for (Option option : opt) {
			if (opt.indexOf(option) == i) {
				option = new Option(n);
			}
		}
	}
	
	/**
	 * Set the price of the option
	 * Has a linked method in Automotive class to initiate
	 * 
	 * @param p
	 *        price
	 * @param i
	 *        The index of option[]
	 */
	protected void setOPPrice(float p, int i) {
		for (Option option : opt) {
			if (opt.indexOf(option) == i) {
				option.setOpPrice(p);
			}
		}
	}	
	
	/**
	 * Add option to ArrayList instead of using index
	 * @param opName
	 * @param opPrice
	 */
	protected void addOption(String opName, float opPrice) {
		opt.add(new Option(opName, opPrice));
	}
	
	/**
	 * Find option object by its name; 
	 * (Find "blue" option in "color" option set
	 * Update in lab 2; using "string.equals()" instead of "=="
	 * 
	 * @param n
	 *        name like ("blue", "red", ...)
	 * @return If found, return the option object
	 *         If not found, return null
	 */
	protected Option findOptionWithName(String n) {
		for (Option option : opt) {
			if (option.getOpName().equals(n)) {
				return option;
			}
		}
		return null;
	}		
	
	/**
	 * Delete one option object (in inner class)
	 * Has a linked method in Automotive class to initiate
	 * 
	 * @param n
	 *        The name used for finding
	 */
	protected void deleteOp(String n) {
		for (Option option : opt) {
			if (option.getOpName().equals(n)) {
				opt.remove(option);
			}
		}
	}
		
	/**
	 * Update one option object
	 * 
	 * @param n
	 *        name ("blue", "red") to find the onject first
	 * @param newOpObj
	 *        the new object to replaced with
	 */
	protected void updateOpObj(String n, Option newOpObj) {
		Option op = findOptionWithName(n);
		op = newOpObj;
	}
	
	/**
	 * Update the name of the option object
	 * 
	 * @param oldOpName
	 *        The old option name used for finding the OptObj
	 * @param newOpName
	 *        The new option name
	 */
	protected void updateOpName(String oldOpName, String newOpName) {
		Option op = findOptionWithName(oldOpName);
		op.setOpName(newOpName);
	}
	
	/**
	 * Update the price of the option object
	 * 
	 * @param n
	 *        The name used for finding the OptObj
	 * @param newOpPrice
	 *        The new price be replaced with
	 */
	protected void updateOpPrice(String n, float newOpPrice) {
		Option op = findOptionWithName(n);
		op.setOpPrice(newOpPrice);
	}
	
	//+   getOptionChoice():   Option
	//+   setOptionChoice(optionName:   String):   void
	
	// The following 2 methods implemented after lab 3
	
	/**
	 * Get option choice
	 * 
	 * @return choice
	 */
	protected Option getOptionChoice() {
		return choice;
	}
	
	/**
	 * Set option choice; option name is given
	 * 
	 * @param optionName option name
	 */
	protected void setOptionChoice(String optionName) {
		for (Option option : opt) {
			if (option.getOpName().equals(optionName)) {
				choice = option;
			}
		}
	}
		
	/**
	 * Override toString; used for printing
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Option Set: ");       // "  " indent auto->opset;
		sb.append(opsetName + "\n");
		for (Option option : opt) {
			sb.append(option.toString());
		}
		return sb.toString();
	}
}

