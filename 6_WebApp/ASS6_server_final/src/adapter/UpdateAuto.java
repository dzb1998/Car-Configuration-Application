package adapter;

import exception.AutoException;

public interface UpdateAuto {

	public void updateOptionSetName(String modelName, 
			String optionSetName, String newName) throws AutoException;

	public void deleteOptionSet(String modelName, 
						String optionSetName) throws AutoException;


	public void deleteOption(String modelName, String optionSetName,
			String optionName) throws AutoException;

	public void deleteModel(String modelName);

	public void setOptionChoice(String modelName, String optionSetName,
			String choice) throws AutoException;

	public float getOptionPrice(String modelName, String optionSetName,
					String optionName) throws AutoException;

	public String getOptionChoice(String modelName, 
					String optionSetName) throws AutoException;

	public float getAutoTotalPrice(String modelName);

}
