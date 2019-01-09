package scale;

import exception.AutoException;
import model.Automobile;

public class EditOptions extends Thread {

	private Automobile auto;
	private String optionSetName;
	private String optionName;
	private String newOptionName;
	private float price;
	private String editType;

	/**
	 * Constructor for setting option price.
	 * @param a Automobile object
	 * @param optionset option set name
	 * @param option option name
	 * @param p option price to set
	 */
	public EditOptions(Automobile a,
						String optionset, String option, float p) {
		auto = a;
		optionSetName = optionset;
		optionName = option;
		price = p;
		editType = "option price";
	}

	/**
	 * Constructor for editing option name.
	 * @param a Automobile object
	 * @param optionset option set name
	 * @param option option name
	 * @param newOptName new option name
	 */
	public EditOptions(Automobile a,
			String optionset, String option, String newOptName) {
		auto = a;
		optionSetName = optionset;
		optionName = option;
		newOptionName = newOptName;
		editType = "option name";
	}

	@Override
	public void run() {
		try {
			if (editType.equals("option price")) {
				auto.updateOptionPrice(optionName, price, optionSetName);
			} else if (editType.equals("option name")) {
				auto.updateOptionName(optionName, newOptionName, optionSetName);
			}
		} catch (AutoException e) {
			e.fix(e.getErrorNo());
		}
	}
}
