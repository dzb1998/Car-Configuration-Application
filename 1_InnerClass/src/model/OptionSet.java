// Zhubo Deng
// Lab 1 - Object Theory; Inner class; File IO; Serialization
// 04/24/2018
// MAC OS, Eclipse

package model;

import java.io.Serializable;

/**
 * using dynamic binding
 * containment / pseudocode 
 * option in optionSet, protected; fileIO, public
 * NOTHING static
 * @author dengzhubo
 *
 */
public class OptionSet implements Serializable {
	
	private Option opt[];     // already null here
	private String opsetName;

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
		opt = new Option[size];
		opsetName = n;
		
		for(int i=0;i<opt.length;i++)
			opt[i] = new Option();
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
	protected Option[] getOpt() {
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
		opt = new Option[size];
	}
	
	/**
	 * Set the option object
	 * @param newOpt
	 *        the new option object
	 * @param i
	 *        index
	 */
	protected void setOPObj(Option newOpt, int i) {
		opt[i] = newOpt;
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
		opt[i] = new Option(n);
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
		opt[i].setOpPrice(p);
	}	
	
	/**
	 * Find option object by its name; 
	 * (Find "blue" option in "color" option set
	 * 
	 * @param n
	 *        name like ("blue", "red", ...)
	 * @return If found, return the option object
	 *         If not found, return null
	 */
	protected Option findOptionWithName(String n) {
		for (int i = 0; i < opt.length; ++i) {
			if (opt[i].getOpName() == n) {
				return opt[i];
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
		for (int i = 0; i < opt.length; ++i) {
			if (opt[i].getOpName() == n) 
				opt[i] = null;			
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
	
	/**
	 * Override toString; used for printing
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Option Set: ");       // "  " indent auto->opset;
		sb.append(opsetName + "\n");
		for(int i = 0; i < opt.length; ++i) 
			sb.append(opt[i].toString());
		return sb.toString();
	}
	
	/**
	 * Inner class Option
	 * @author dengzhubo
	 *
	 */
	protected class Option implements Serializable {
		
		private String opName;
		private float opPrice;
		
		// 4 constructors
		protected Option() {
			
		}
		
		protected Option(String n) {
			opName = n;
		}
		
		protected Option(float p) {
			opPrice = p;
		}

		protected Option(String n, float p) {
			opName = n;
			opPrice = p;
		}
		
		// 2 getters; get name and get price
		protected String getOpName() {
			return opName;
		}
		
		protected float getOpPrice() {
			return opPrice;
		}
		
		// 2 setters; set name and set price
		protected void setOpName(String n) {
			opName = n;
		}
		
		protected void setOpPrice(float p) {
			opPrice = p;
		}
		
		/**
		 * Override toString; used for printing
		 */
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("\t" + "Option: ");      // indent twice auto->opset->op
			sb.append(opName + "; ");
			sb.append("Price: ");
			sb.append("$").append(opPrice + "\n");
			return sb.toString();			
		}
	}
}

