package adapter;

import exception.AutoException;

public interface EditThread {

	public void updateOptionName(String modelName, String optionSetName,
			String optionName, String newName) throws AutoException;

	public void updateOptionPrice(String modelName, String optionSetName,
			String optionName, float newPrice) throws AutoException;

}
