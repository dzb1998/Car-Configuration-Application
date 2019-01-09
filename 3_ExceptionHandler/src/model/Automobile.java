// Zhubo Deng
// Lab 1 - Object Theory; Inner class; File IO; Serialization
// 04/24/2018
// MAC OS, Eclipse

package model;

import java.io.Serializable;
import java.util.ArrayList;

/** 
 * This class will represent the Model. 
 * @author dengzhubo
 *
 */
public class Automobile implements Serializable { 
	
	private String name;
	private float baseprice;
	private ArrayList<OptionSet> opset;
	private ArrayList<Option> choiceArr;
	
	/** 
	 * Constructor
	 * For newly created object
	 */
	public Automobile() {
		
	}
	
	/**
	 * Constructor
	 * Use while create a new Automotive object
	 * MUST initialize "new option set" to avoid NullPtrExp
	 * 
	 * @param n
	 *        The auto name
	 * 
	 * @param bPrice
	 *        The auto base price
	 *        
	 * @param optionSetsize
	 *        The array size for auto option set array.
	 */
	public Automobile(String n, float bPrice, int optionSetsize) {		
		name = n;
		baseprice = bPrice;
		opset = new ArrayList<>(optionSetsize);
		choiceArr = new ArrayList<>(optionSetsize);
	}	
	
	/** 
	 * Getter
	 * 
	 * @return The auto name 
	 */
	public String getAutoName() {
		return name;
	}
	
	/** 
	 * Getter
	 * 
	 * @return The auto base price 
	 */
	public float getBasePrice() {
		return baseprice;
	}
	
	/**
	 * Getter
	 * 
	 * @param index
	 *        index for option set
	 *        
	 * @return Only one option set; the (index)th option set
	 */
	public OptionSet getOptionSet(int index) {
		return opset.get(index);
	}
	
	public int getOptionSetSize() {
		return opset.size();
	}
	
	/**
	 * Setter
	 * Use while only change the name of this auto
	 * 
	 * @param n
	 *        The name be set
	 */
	public void setAutoName(String n) {
		name = n;
	}
	
	/**
	 * Setter
	 * Use while only change the base price of this auto
	 * 
	 * @param bPrice
	 *        The base price be set
	 */
	public void setAutoBasePrice(float bPrice) {
		baseprice = bPrice;
	}
	
	/** 
	 * Setter
	 * Set (replace) the whole option set to a new option set array
	 * 
	 * @param newOpset
	 *        The new option set array
	 */
	public void setOpset(ArrayList<OptionSet> newOpset) {
		this.opset = newOpset;
	}
	
	/**
	 * Set option set size
	 * @param size option set size
	 * 
	 * update in lab 2
	 */
	public void setOptionSetSize(int size) {
		opset = new ArrayList<>(size); 
	}
	
	// use the following 3 methods ONLY while creating a NEW object 
	// in class notes
	/**
	 * Create a new option set
	 * @param size
	 *        index
	 */
	public void createOptionSet(int size) {
		opset = new ArrayList<>(size);
	}
	
	/**
	 * Set (pass) the name
	 * @param name
	 * @param i
	 *        index
	 */
	public void setOptionSetName(String name, int i) {
		for (OptionSet optionSet : opset) {
			if (opset.indexOf(optionSet) == i) {
				optionSet = new OptionSet(name);
			}
		}
	}
	
	/**
	 * Set (pass) the size
	 * Traverse first (from lab 3)
	 * 
	 * @param size
	 * @param i
	 *        index
	 */
	public void setOptionSetSize(int size, int i) {
		for (OptionSet optionSet : opset) {
			if (opset.indexOf(optionSet) == i) {
				optionSet.setSize(size);
			}
		}
	}
	// end in class notes
	
	/**
	 * Set the option inner class to a new object
	 * Traverse first (from lab 3)
	 * 
	 * @param newOp
	 *        The new option object
	 * @param i
	 *        The index of option[]
	 * @param j
	 *        The index of optionSet[]
	 */
	public void setOptionObj(Option newOp, int i, int j) {
		for (OptionSet optionSet : opset) {
			if (opset.indexOf(optionSet) == j) {
				optionSet.setOPObj(newOp, i);
			}
		}
	}
	
	/**
	 * Set the name of the option (inner class)
	 * 
	 * @param n
	 *        The name
	 * @param i
	 *        The index of option[]
	 * @param j
	 *        The index of optionSet[]
	 */
	public void setOptionName(String n, int i, int j) {
		for (OptionSet optionSet : opset) {
			if (opset.indexOf(optionSet) == j) {
				optionSet.setOPName(n, i);
			}
		}
	}
	
	/**
	 * Set the price of the option (inner class)
	 * 
	 * @param p
	 *        The price
	 * @param i
	 *        The index of option[]
	 * @param j
	 *        The index of optionSet[]
	 */
	public void setOptionPrice(float p, int i, int j) {
		for (OptionSet optionSet : opset) {
			if (opset.indexOf(optionSet) == j) {
				optionSet.setOPPrice(p, i);
			}
		}
	}
	
	// update after lab 3
	
	/**
	 * Add option set to ArrayList
	 * @param opSetName
	 * @param opSetSize
	 */
	public void addOptionSet(String opSetName, int opSetSize) {
		opset.add(new OptionSet(opSetName, opSetSize));
	}
	
	/**
	 * Add option to ArrayList
	 */
	public void addOptionToOpset(String opSetName, 
			String opName, float opPrice) {
		
		for (OptionSet optionSet : opset) {
			if (optionSet.getOpsetName().equals(opSetName)) {
				optionSet.addOption(opName, opPrice);
			}
		}
	}
	
	
	// USE enhanced for loop for ArrayList (collection)
	// to change each element
	/**
	 * Find option set with name (the only find method in this lab)
	 * Using the given name to find the option set object, the array element
	 * Update in lab 2; string.equal() 
	 * 
	 * @param n
	 *        The name of the option set be searched
	 * 
	 * @return If found, return the option set array element
	 *         If not found, return null
	 */
	public OptionSet findOpsetWithName(String n) {		
		for (OptionSet optionSet : opset) {
			if (optionSet.getOpsetName().equals(n)) {
				return optionSet;
			}
		}
		return null;
	}
	
	/**
	 * Delete an option set; search by name (color, transmission, ...)
	 * Delete an element from the option set array; set it to null for Lab1
	 * 
	 * @param n
	 *        The name of the option set be found
	 */
	public void deleteOpSet(String n) {
		for (OptionSet optionSet : opset) {
			if (optionSet.getOpsetName().equals(n)) {
				// remove one element from option set
				// optionSet: the element being traversed
				opset.remove(optionSet);
			}
		}
	}
	
	/**
	 * Delete the option from the certain option set
	 * (To access the inner class option; Nested for loop find items; 
	 * one method in Auto, one method MUST in inner option class in option set)
	 * 
	 * @param n
	 *        The name of the option set be found
	 */
	public void deleteOp(String n) {
		for (OptionSet optionSet : opset) {
			optionSet.deleteOp(n);
		}
	}
	
	/**
	 * Update one option set; one array element of opset[]
	 * Using FIND and SET
	 * Find with the given name, and replace with the whole option set
	 * 
	 * @param n
	 *        The given name used for finding
	 *        
	 * @param newOpset
	 *        The given new option set to replace
	 */
	public void updateOpset(String n, OptionSet newOpset) {
		for (OptionSet optionSet : opset) {
			if (optionSet.getOpsetName().equals(n)) {
				optionSet = newOpset;
			}
		}
	}
	
	/**
	 * Update the name in one array item 
	 * Using FIND and SET
	 * (eg. change "transmission" to "trans")
	 * Update lab 2; use string.equals(obj) to compare
	 * 
	 * @param oldName
	 *        The old name used for finding certain object
	 *        
	 * @param newName
	 *        The new name for replacing
	 */
	public void updateOpsetName(String oldName, String newName) {
		for (OptionSet optionSet : opset) {
			if (optionSet.getOpsetName().equals(oldName)) {
				optionSet.setOpsetName(newName);
			}
		}
	}
	
	/**
	 * Update option object in inner class option
	 * (To access in inner class; one method in Auto, one MUST in OptionSet)
	 * Using FIND and SET
	 * Replace the option object to a new one
	 * 
	 * @param opsetName
	 *        Find with name; in which option set we should start searching
	 *        
	 * @param n
	 *        The option name ("Blue", "Red") used for searching
	 *        
	 * @param newOpObj
	 *        The new option object be replaced with
	 */
	public void updateOptionObject(String opsetName, String n, Option newOpObj) {
		OptionSet ops = findOpsetWithName(opsetName);
		ops.updateOpObj(n, newOpObj);
	}
	
	/** 
	 * Update the name of certain option object (the inner class)
	 * (To access in inner class; one method in Auto, one MUST in OptionSet)
	 * 
	 * @param opsetName
	 *        Find option set with name; in which we should start searching
	 *        
	 * @param oldOpName
	 *        The old option name used for searching ("Blue", "Red", ...)
	 *        
	 * @param newOpName
	 *        The new option name be replaced with
	 */
	public void updateOptionName(String opsetName, String oldOpName, String newOpName) {		
		OptionSet ops = findOpsetWithName(opsetName);
		ops.updateOpName(oldOpName, newOpName);			
	}
	
	
	/** 
	 * Update the price of certain option object (the inner class)
	 * (To access inner class; one method in Auto, one MUST in OptionSet)
	 * 
	 * @param opsetName
	 *        Find option set with name; in which we should start searching
	 *        
	 * @param n
	 *        The old option name used for searching ("Blue", "Red", ...)
	 *        
	 * @param newOpPrice
	 *        The new option price be replaced with
	 */
	public void updateOptionPrice(String opsetName, String n, float newOpPrice) {
		OptionSet ops = findOpsetWithName(opsetName);
		ops.updateOpPrice(n, newOpPrice);
	}
	
	//+   getOptionChoice(setName:   String):   String
	//+   getOptionChoicePrice(setName:   String):   int
	//+   setOptionChoice(setName:   String,   optionName:   String):   void 
	//+   getTotalPrice():   int
	
	// The following 4 methods implemented after lab 3
	
	/**
	 * Get option choice name, option choice already existed in the option set
	 * 
	 * @param setName like "transmission"
	 * @return option name
	 */
	public String getOptionChoiceName(String setName) {
		for (OptionSet optionSet : opset) {
			if (optionSet.getOpsetName().equals(setName)) {
				return optionSet.getOptionChoice().getOpName();
			}
		}
		return null;
	}
	
	/**
	 * Get option choice price, option choice already existed in the option set
	 * 
	 * @param setName like "transmission"
	 * @return option price
	 */
	public float getOptionChoicePrice(String setName) {
		for (OptionSet optionSet : opset) {
			if (optionSet.getOpsetName().equals(setName)) {
				return optionSet.getOptionChoice().getOpPrice();
			}
		}
		return 0;
	}
	
	/**
	 * Set choice array, put every choice from option set to the ArrayList
	 * 
	 * @param setName setName like "transmission"
	 * @param optionName like "standard"
	 */
	public void setOptionChoice(String setName, String optionName) {
		for (OptionSet optionSet : opset) {
			if (optionSet.getOpsetName().equals(optionName)) {
				optionSet.setOptionChoice(optionName);
				choiceArr.add(optionSet.getOptionChoice());
			}
		}
	}
	
	/**
	 * Get total price; add up all the price in the choice ArrayList
	 * 
	 * @return totalPrice
	 */
	public float getTotalPrice() {
		float totalPrice = baseprice;
		for (Option opChoice : choiceArr) {
			totalPrice += opChoice.getOpPrice();
		}
		return totalPrice;
	}
	
	/**
	 * toString; use for printing, print(object)
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Name: ");
		sb.append(name + "\n");
		sb.append("Base Price: ");
		sb.append(baseprice + "\n");
		for (OptionSet optionSet : opset) {
			sb.append(optionSet.toString());
		}
		return sb.toString();	
	}
}

