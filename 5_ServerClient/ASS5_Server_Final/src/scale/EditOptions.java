package scale;

import model.Automobile;

import java.util.LinkedHashMap;

public class EditOptions extends Thread {

    public void updateOptionSetName(LinkedHashMap<String, Automobile> automobiles, String automobileModel, String optionSetName, String newName) {
        automobiles.get(automobileModel).updateOptionSetName(optionSetName, newName);
    }

    public void updateOptionPrice(LinkedHashMap<String, Automobile> automobiles, String automobileModel, String optionSetName, String optionName, double newPrice) {
        automobiles.get(automobileModel).updateOptionPrice(optionSetName, optionName, newPrice);
    }
}
