package adapter;

public interface UpdateAuto {
    void updateOptionSetName(String automobileModel, String optionSetName, String newName);
    void updateOptionPrice(String automobileModel, String optionSetName, String optionName, double newPrice);
}
