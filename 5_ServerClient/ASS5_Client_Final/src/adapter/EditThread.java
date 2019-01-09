package adapter;

public interface EditThread {
	void threadedUpdateOptionSetName(String automobileModel, String optionSetName, String newName);
	void threadedUpdateOptionPrice(String automobileModel, String optionSetName, String optionName, double newPrice);
}
