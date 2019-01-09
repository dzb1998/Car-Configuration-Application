package client;

import model.Automobile;

public interface ModelDataInterface {

    public String getModelList();
    public Automobile getModelInfo(String model);
    
}
