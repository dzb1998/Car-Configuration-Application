package model;

import java.io.Serializable;
import java.util.ArrayList;

import exception.AutoException;
import model.OptionSet.Option;

public class Automobile implements Serializable{

	private String name;
	private String make;
	private String model;
	private float basePrice;
	private volatile ArrayList<OptionSet> optionSets;
	private ArrayList<Option> choices;
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 * @param name automobile name
	 * @param make automobile make
	 * @param model automobile model
	 * @param basePrice base price
	 * @param optionSetSize set of options
	 */
	public Automobile(String name, String make, String model, 
					float basePrice, int optionSetSize) {
		this.name = name;
		this.make = make;
		this.model = model;
		this.basePrice = basePrice;
		optionSets = new ArrayList<OptionSet>(optionSetSize);
		for (int i = 0; i < optionSetSize; i++) {
			optionSets.add(i, new OptionSet());
		}
		choices = new ArrayList<Option>(optionSetSize);
	}

	/**
	 * Constructor with no argument.
	 */
	public Automobile() {
		this(null, null, null, 0, 0);
	}

	/**
	 * Set the automobile name.
	 * Synchornized so that different threads cannot modify the same model simultaneously.
	 * @param name automobile name
	 */
	public synchronized void setName(String name) {
		this.name = name;
	}

	/**
	 * Set the automobile make.
	 * Synchornized so that different threads cannot modify the same model simultaneously.
	 * @param make automobile make
	 */
	public synchronized void setMake(String make) {
		this.make = make;
	}

	/**
	 * Set the automobile model.
	 * Synchornized so that different threads cannot modify the same model simultaneously.
	 * @param model automobile model
	 */
	public synchronized void setModel(String model) {
		this.model = model;
	}

	/**
	 * Set the base price.
	 * Synchornized so that different threads cannot modify the same model simultaneously.
	 * @param p base price
	 */
	public synchronized void setBasePrice(float p) {
		basePrice = p;
	}

	/**
	 * Set the option choice.
	 * Synchornized so that different threads cannot modify the same model simultaneously.
	 * @param setName option set name
	 * @param optionName option name
	 * @throws AutoException auto exception when option set or option not found
	 */
	public synchronized void setOptionChoice(String setName, 
								String optionName) throws AutoException {
		int index = findOptionSet(setName);
		optionSets.get(index).setOptionChoice(optionName);
	}

	/**
	 * Set the name of the option set.
	 * Synchornized so that different threads cannot modify the same model simultaneously.
	 * @param index option set index
	 * @param optionSetName option set name
	 */
	public synchronized void setOptionSetName(int index, String optionSetName) {
		if (index >= 0 && index < optionSets.size()) {
			optionSets.get(index).setName(optionSetName);
		}
	}

	/**
	 * Set the option set.
	 * Synchornized so that different threads cannot modify the same model simultaneously.
	 * @param index option set index
	 * @param optSet option set
	 */
	public synchronized void setOptionSet(int index, OptionSet optSet) {
		if (index >= 0 && index < optionSets.size()) {
			optionSets.set(index, optSet);
		}
	}

	/**
	 * Set the size of the option set and initialize the option set.
	 * Synchornized so that different threads cannot modify the same model simultaneously.
	 * @param optionSetSize option set array size
	 */
	public synchronized void setOptionSetSize(int optionSetSize) {
		optionSets = new ArrayList<OptionSet>(optionSetSize);
		for (int i = 0; i < optionSetSize; i++) {
			optionSets.add(i, new OptionSet());
		}
	}

	/**
	 * Set the option name.
	 * Synchornized so that different threads cannot modify the same model simultaneously.
	 * @param index option index
	 * @param optionName option name to set
	 * @param optionSetName name of the option set the option belonging to
	 * @throws AutoException auto exception when option set not found
	 */
	public synchronized void setOptionName(int index, String optionName, 
								String optionSetName) throws AutoException {
		int i = findOptionSet(optionSetName);
		optionSets.get(i).setOptionName(index, optionName);
	}

	/**
	 * Set option price.
	 * Synchornized so that different threads cannot modify the same model simultaneously.
	 * @param index option index
	 * @param price option price
	 * @param optionSetName name of the option set the option belonging to
	 * @throws AutoException auto exception when option set not found
	 */
	public synchronized void setOptionPrice(int index, float price, 
								String optionSetName) throws AutoException {
		int i = findOptionSet(optionSetName);
		optionSets.get(i).setOptionPrice(index, price);
	}

	/**
	 * Set the size of the option set and initialize it.
	 * Synchornized so that different threads cannot modify the same model simultaneously.
	 * @param index index of the option set
	 * @param optionSize size of the option array
	 */
	public synchronized void setOptionSize(int index, int optionSize) {
		optionSets.get(index).setOptionSize(optionSize);
	}

	/**
	 * Get the automobile name.
	 * Synchornized to make sure getting the data correctly.
	 * @return automobile name
	 */
	public synchronized String getName() {
		return name;
	}

	/**
	 * Get the automobile make.
	 * Synchornized to make sure getting the data correctly.
	 * @return automobile make
	 */
	public synchronized String getMake() {
		return make;
	}

	/**
	 * Get the automobile model.
	 * Synchornized to make sure get the data correctly.
	 * @return automobile model
	 */
	public synchronized String getModel() {
		return model;
	}

	/**
	 * Get the base price.
	 * Synchornized to make sure getting the data correctly.
	 * @return base price
	 */
	public synchronized float getBasePrice() {
		return basePrice;
	}

	/**
	 * Get the selected option name for the specific option set.
	 * Synchornized to make sure getting the data correctly.
	 * @param setName option set name
	 * @return option name
	 * @throws AutoException auto exception when option set not found
	 */
	public synchronized String getOptionChoice(String setName) 
										throws AutoException {
		int index = findOptionSet(setName);
		Option choice = optionSets.get(index).getOptionChoice();
		if (choice != null) {
			return choice.getName();
		} else {
			return null;
		}
	}

	/**
	 * Get the selected option price for the specific option set.
	 * Synchornized to make sure getting the data correctly.
	 * @param setName option set name
	 * @return option price
	 * @throws AutoException auto exception when option set not found
	 */
	public synchronized float getOptionChoicePrice(String setName) 
								throws AutoException {
		int index = findOptionSet(setName);
		Option choice = optionSets.get(index).getOptionChoice();
		if (choice != null) {
			return choice.getPrice();
		} else {
			return 0;
		}
	}

	/**
	 * Get the size of the option set array.
	 * Synchornized to make sure getting the data correctly.
	 * @return option set array size
	 */
	public synchronized int getOptionSetSize() {
		return optionSets.size();
	}

	/**
	 * Get the option set.
	 * Synchornized to make sure getting the data correctly.
	 * @param index option set index
	 * @return option set
	 */
	public synchronized OptionSet getOptionSet(int index) {
		if (index >= 0 && index < optionSets.size()) {
			return optionSets.get(index);
		} else {
			return null;
		}
	}

	/**
	 * Get the option set name.
	 * Synchornized to make sure getting the data correctly.
	 * @param index option set index
	 * @return option set
	 */
	public synchronized String getOptionSetName(int index) {
		if (index >= 0 && index < optionSets.size()) {
			return optionSets.get(index).getName();
		} else {
			return null;
		}
	}

	/**
	 * Get the size of the option set.
	 * Synchornized to make sure getting the data correctly.
	 * @index index of the option set
	 * @return option set size
	 */
	public synchronized int getOptionSize(int index) {
		return optionSets.get(index).getOptionSize();
	}

	/**
	 * Get the option name by index.
	 * Synchornized to make sure getting the data correctly.
	 * @param optionSetName name of the option set the option belonging to
	 * @param index the option index in option set
	 * @return option name
	 * @throws AutoException auto exception when option set or option not found
	 */
	public synchronized String getOptionName(String optionSetName, int index) throws AutoException {
		int i = findOptionSet(optionSetName);
		OptionSet set = optionSets.get(i);
		return set.getOptionName(index);
	}

	/**
	 * Get the option price.
	 * Synchornized to make sure getting the data correctly.
	 * @param optName the option name
	 * @param optionSetName name of the option set the option belonging to
	 * @return option price
	 * @throws AutoException auto exception when option set or option not found
	 */
	public synchronized float getOptionPrice(String optName, 
			String optionSetName) throws AutoException {
		int index = findOptionSet(optionSetName);
		OptionSet set = optionSets.get(index);
		int optIndex = set.findOption(optName);
		return set.getOptionPrice(optIndex);
	}

	/**
	 * Get total price of the automobile and selected options.
	 * Synchornized to make sure getting the data correctly.
	 * @return total price
	 */
	public synchronized float getTotalPrice() {
		float totalPrice = basePrice;
		for (OptionSet set : optionSets) {
			totalPrice += set.getOptionChoice().getPrice();
			choices.add(set.getOptionChoice());
		}
		return totalPrice;
	}

	/**
	 * Find the option set index by its name.
	 * Synchornized to make sure getting the data correctly.
	 * @param optionSetName option set name
	 * @return option set index
	 * @throws AutoException auto exception when option set not found
	 */
	public synchronized int findOptionSet(String optionSetName) 
									throws AutoException {
		for (int i = 0; i < optionSets.size(); i++) {
			if (optionSets.get(i).getName().equals(optionSetName)) {
				return i;
			}
		}
		throw new AutoException(7); // if not found.
	}

	/**
	 * Find the option index by its name.
	 * Synchornized to make sure getting the data correctly.
	 * @param optionName option name
	 * @param optionSetName name of the option set the option belonging to
	 * @return option index
	 * @throws AutoException auto exception when option not found
	 */
	public synchronized int findOption(String optionName, 
							String optionSetName) throws AutoException {
		int index = findOptionSet(optionSetName);
		return optionSets.get(index).findOption(optionName);
	}

	/**
	 * Add an option set to the model.
	 * Synchornized so that different threads cannot modify the same model simultaneously.
	 * @param optionSetName option set name
	 * @param size option set size
	 */
	public synchronized void addOptionSet(String optionSetName, int size) {
		optionSets.add(new OptionSet(optionSetName, size));
	}

	/**
	 * Add an option set to the model in the specific index.
	 * Synchornized so that different threads cannot modify the same model simultaneously.
	 * @param optionSetName option set name
	 * @param size option set size
	 * @param index index to insert
	 */
	public synchronized void addOptionSet(String optionSetName, 
								int size, int index) {
		optionSets.add(index, new OptionSet(optionSetName, size));
	}

	/**
	 * Add an option set to the model.
	 * Synchornized so that different threads cannot modify the same model simultaneously.
	 * @param optionSetName option set name
	 */
	public synchronized void addOptionSet(String optionSetName) {
		optionSets.add(new OptionSet(optionSetName));
	}

	/**
	 * Add an option to the specific option set.
	 * Synchornized so that different threads cannot modify the same model simultaneously.
	 * @param optionName option name
	 * @param price option price
	 * @param optSetName option set name
	 * @throws AutoException auto exception when option set or option not found
	 */
	public synchronized void addOption(String optionName, float price, 
							String optSetName) throws AutoException {
		int index = findOptionSet(optSetName);
		optionSets.get(index).addOption(optionName, price);
	}

	/**
	 * Delete the option set by its name.
	 * Synchornized so that different threads cannot modify the same model simultaneously.
	 * @param optionSetName option set name
	 * @throws AutoException auto exception when option set not found
	 */
	public synchronized void deleteOptionSet(String optionSetName) 
									throws AutoException {
		int index = findOptionSet(optionSetName);
		optionSets.remove(index);
	}

	/**
	 * Delete the option by its name.
	 * Synchornized so that different threads cannot modify the same model simultaneously.
	 * @param optionName option name
	 * @param optionSetName name of the option set the option belonging to
	 * @throws AutoException auto exception when option set or option not found
	 */
	public synchronized void deleteOption(String optionName, 
								String optionSetName) throws AutoException {
		int index = findOptionSet(optionSetName);
		optionSets.get(index).deleteOption(optionName);
	}

	/**
	 * Update the option set name.
	 * Synchornized so that different threads cannot modify the same model simultaneously.
	 * @param oldName option set old name
	 * @param newName option set new name to set
	 * @throws AutoException auto exception when option set not found
	 */
	public synchronized void updateOptionSetName(String oldName, 
									String newName) throws AutoException {
		int index = findOptionSet(oldName);
		optionSets.get(index).setName(newName);
	}

	/**
	 * Update the option name.
	 * Synchornized so that different threads cannot modify the same model simultaneously.
	 * @param oldName the old option name
	 * @param newName the new option name to set
	 * @param optionSetName name of the option set the option belonging
	 * @throws AutoException auto exception when option set or option not found
	 */
	public synchronized void updateOptionName(String oldName, 
			String newName, String optionSetName) throws AutoException {
		int index = findOptionSet(optionSetName);
		optionSets.get(index).updateOptionName(oldName, newName);
		System.out.println("Option name of " + oldName + " in " + name 
				+ " are updated to " + newName);
	}

	/**
	 * Update the option price.
	 * Synchornized so that different threads cannot modify the same model simultaneously.
	 * @param optName the option name
	 * @param price the option price to set
	 * @param optionSetName name of the option set the option belonging to
	 * @throws AutoException auto exception when option set or option not found
	 */
	public synchronized void updateOptionPrice(String optName, 
			float price, String optionSetName) throws AutoException {
		int index = findOptionSet(optionSetName);
		optionSets.get(index).updateOptionPrice(optName, price);
		System.out.println("Price of " + optionSetName + " - " 
							+ optName + " in " + name + " are updated to " + price);
	}

	/**
	 * Print the automotive information.
	 */
	public void print() {
		System.out.printf(toString());
	}

	/**
	 * Get the choices information.
	 * @return choices information
	 */
	public String choicesInfo() {
		StringBuilder sbd = new StringBuilder("***Auto: " + name + "***\n\n");
		boolean choiced = false;
		sbd.append("Choices:\n");
		for (OptionSet set : optionSets) {
			Option choice = set.getOptionChoice();
			if (choice != null) {
				sbd.append(set.getName() + ": " + choice.toStringCustomized() + "\n");
				choiced = true;
			}
		}
		if (!choiced) {
			sbd.append("Have not made any choice.\n");
		}
		return sbd.toString();
	}

	@Override
	public String toString() {
		StringBuilder sbd = new StringBuilder("***Model Information***\n\n");
		boolean choiced = false;
		sbd.append("Name: ").append(name).append("\n\n");
		sbd.append("Make: ").append(make).append("\n\n");
		sbd.append("Model: ").append(model).append("\n\n");
		sbd.append("Base Price: ").append(String.valueOf(basePrice));
		sbd.append("\n\nProperties (with corresponding prices): ").append("\n");
		for (OptionSet optionSet : optionSets) {
			sbd.append(optionSet.toStringCustomized()).append("\n");
		}
		sbd.append("Choices:\n");
		for (Option choice : choices) {
			if (choice != null) {
				sbd.append(choice.toStringCustomized() + "\n");
				choiced = true;
			}
		}
		if (!choiced) {
			sbd.append("Have not made any choice.\n");
		}
		return sbd.toString();
	}
}