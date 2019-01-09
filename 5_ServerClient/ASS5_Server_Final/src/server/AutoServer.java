package server;

import model.Automobile;

import java.util.ArrayList;
import java.util.Properties;

public interface AutoServer {
    void buildAutoFromProperties(Properties properties);

    ArrayList<String> getModelList();

    Automobile getAutomobile(String automobileModel);
}