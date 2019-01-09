package model;

import java.io.Serializable;
import java.util.ArrayList;

import exception.AutoException;

public class OptionSet implements Serializable{

	private String name;
	private ArrayList<Option> opt;
	private Option choice;
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 * @param n name
	 * @param size option set size
	 */
	protected OptionSet(String n, int size) {
		name = n;
		opt = new ArrayList<Option>(size);
		for(int i = 0; i < size; i++) {
			opt.add(i, new Option());
		}
		choice = null;
	}

	/**
	 * Constructor with no argument.
	 */
	protected OptionSet() {
		this(null, 0);
	}

	/**
	 * Constructor with only name.
	 * @param n name
	 */
	protected OptionSet(String n) {
		this(n, 0);
	}

	/**
	 * Set option set name.
	 * @param name option set name
	 */
	protected void setName(String name) {
		this.name = name;
	}

	/**
	 * Set the size of the option array and initialize it.
	 * @param optionSize size of the option array
	 */
	public void setOptionSize(int optionSize) {
		opt = new ArrayList<Option>(optionSize);
		for(int i = 0; i < optionSize; i++) {
			opt.add(i, new Option());
		}
	}

	/**
	 * Set the name of an option identified by the index
	 * @param index option index
	 * @param optName option name
	 */
	protected void setOptionName(int index, String optName) {
		if (index >= 0 && index < opt.size()) {
			opt.get(index).setName(optName);
		}
	}

	/**
	 * Set the price of an option identified by the index
	 * @param index option index
	 * @param price option price
	 */
	protected void setOptionPrice(int index, float price) {
		if (index >= 0 && index < opt.size()) {
			opt.get(index).setPrice(price);
		}
	}

	/**
	 * Set the option identified by the index
	 * @param index option index
	 * @param option option
	 */
	protected void setOption(int index, Option option) {
		if (index >= 0 && index < opt.size()) {
			opt.set(index, option);
		}
	}

	/**
	 * Choose an option.
	 * @param optionName option name
	 * @throws AutoException auto exception when option not found
	 */
	protected void setOptionChoice(String optionName) throws AutoException {
		int index = findOption(optionName);
		choice = opt.get(index);
	}

	/**
	 * Get the option set name.
	 * @return option set name
	 */
	protected String getName() {
		return name;
	}

	/**
	 * Get an option by its index.
	 * @param index option index
	 * @return option
	 * @throws AutoException auto exception when option not found
	 */
	protected Option getOption(int index) throws AutoException {
		if (index >= 0 && index < opt.size()) {
			return opt.get(index);
		} else {
			throw new AutoException(7);
		}
	}

	/**
	 * Get an option name by its index.
	 * @param index option index
	 * @return option name
	 * @throws AutoException auto exception when option not found
	 */
	protected String getOptionName(int index) throws AutoException {
		if (index >= 0 && index < opt.size()) {
			return opt.get(index).getName();
		} else {
			throw new AutoException(7);
		}
	}

	/**
	 * Get an option price by its index.
	 * @param index option index
	 * @return option price
	 * @throws AutoException auto exception when option not found
	 */
	protected float getOptionPrice(int index) throws AutoException {
		if (index >= 0 && index < opt.size()) {
			return opt.get(index).getPrice();
		} else {
			throw new AutoException(7);
		}
	}

	/**
	 * Get all options.
	 * @return option list
	 */
	protected ArrayList<Option> getAllOptions() {
		return opt;
	}

	/**
	 * Get the size of the option set.
	 * @return option set size
	 */
	protected int getOptionSize() {
		return opt.size();
	}

	/**
	 * Get the selected option.
	 * @return selected option
	 */
	protected Option getOptionChoice() {
		return choice;
	}

	/**
	 * Find an option index by its name.
	 * @param optionName option name
	 * @return option index
	 * @throws AutoException auto exception when option not found
	 */
	protected int findOption(String optionName) throws AutoException {
		for (int i = 0; i < opt.size(); i++) {
			if (opt.get(i).getName().equals(optionName)) {
				return i;
			}
		}
		throw new AutoException(8); // if not found.		
	}

	/**
	 * Update the option name.
	 * @param oldName the old option name
	 * @param newName the new option name to set
	 * @throws AutoException auto exception when option not found
	 */
	protected void updateOptionName(String oldName, 
									String newName) throws AutoException {
		int index = findOption(oldName);
		opt.get(index).setName(newName);
	}

	/**
	 * Update the option price.
	 * @param optName the option name
	 * @param price the option price to set
	 * @throws AutoException auto exception when option not found
	 */
	protected void updateOptionPrice(String optName, 
										float price) throws AutoException {
		int index = findOption(optName);
		opt.get(index).setPrice(price);
	}

	/**
	 * Add an option to the option set.
	 * @param optionName option name
	 * @param price option price
	 */
	protected void addOption(String optionName, float price) {
		opt.add(new Option(optionName, price));
	}

	/**
	 * Insert an option to the specific index of the option set.
	 * @param optionName option name
	 * @param index index to insert
	 * @param price option price
	 */
	protected void addOption(String optionName, int index, float price) {
		opt.add(index, new Option(optionName, price));
	}

	/**
	 * Delete an option by its name.
	 * @param optionName option name
	 * @throws AutoException auto exception when option not found
	 */
	protected void deleteOption(String optionName) throws AutoException {
		int index = findOption(optionName);
		opt.remove(index);
	}

	/**
	 * Print option set information.
	 */
	protected void print() {
		System.out.printf(toString());
	}

	/**
	 * Return a string represents the option set information.
	 * @return a string represents the option set information
	 */
	protected String toStringCustomized() {
		StringBuilder sbd = new StringBuilder();
		sbd.append("[").append(name).append("]").append("\n");
		for (int i = 0; i < opt.size(); i++) {
			sbd.append(opt.get(i).toStringCustomized()).append("\n");
		}
		return sbd.toString();
	}

	/**
	 * Inner class represents option information.
	 * @author Jiaqi Zhang
	 *
	 */
	protected class Option implements Serializable{

		private String name;
		private float price;
		private static final long serialVersionUID = 1L;

		/**
		 * Constructor.
		 * @param name option name
		 * @param price option price
		 */
		protected Option(String name, float price) {
			this.name = name;
			this.price = price;
		}

		/**
		 * Constructor with no argument.
		 */
		protected Option() {
			this(null, 0);
		}

		/**
		 * Constructor with only name.
		 * @param name option name
		 */
		protected Option(String name) {
			this(name, 0);
		}

		/**
		 * Set the name of the option.
		 * @param name option name
		 */
		protected void setName(String name) {
			this.name = name;
		}

		/**
		 * Set the price of the option.
		 * @param price option price
		 */
		protected void setPrice(float price) {
			this.price = price;
		}

		/**
		 * Get the name of the option.
		 * @return option name
		 */
		protected String getName() {
			return name;
		}

		/**
		 * Get the price of the option.
		 * @return option price
		 */
		protected float getPrice() {
			return price;
		}

		/**
		 * Print the option information.
		 */
		protected void print() {
			System.out.printf(toString());
		}

		/**
		 * Return a string represents the option information.
		 * @return a string represents the option information
		 */
		protected String toStringCustomized() {
			StringBuilder sbd = new StringBuilder();
			sbd.append(name).append(" (");
			sbd.append(String.valueOf(price)).append(")");
			return sbd.toString();
		}
	}
}
