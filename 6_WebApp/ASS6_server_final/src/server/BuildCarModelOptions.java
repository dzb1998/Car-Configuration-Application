package server;

import java.util.Properties;

import adapter.BuildAuto;
import exception.AutoException;
import model.Automobile;

public class BuildCarModelOptions implements AutoServer {
	
	public static Automobile createAutoByProps(Properties props){
        Automobile auto = new Automobile();
        try {
        	String carName = props.getProperty("CarName");
        	if (!carName.equals(null)) {
	        	String carModel = props.getProperty("CarModel");
	            String carMake = props.getProperty("CarMake");
	            float basePrice = Float.parseFloat(props.getProperty("Price"));
	            String option1 = props.getProperty("Option1");
	            String optionValue1a = props.getProperty("OptionValue1a");
	            float optPrice1a = Float.parseFloat(
	            						props.getProperty("OptionValuePrice1a"));
	            String optionValue1b = props.getProperty("OptionValue1b");
	            float optPrice1b = Float.parseFloat(
	            						props.getProperty("OptionValuePrice1b"));
	            String option2 = props.getProperty("Option2");
	            String optionValue2a = props.getProperty("OptionValue2a");
	            float optPrice2a = Float.parseFloat(
	            						props.getProperty("OptionValuePrice1a"));
	            String optionValue2b = props.getProperty("OptionValue2b");
	            float optPrice2b = Float.parseFloat(
	            						props.getProperty("OptionValuePrice2b"));
	            auto.setName(carName);
	            auto.setModel(carModel);
	            auto.setMake(carMake);
	            auto.setBasePrice(basePrice);
	            auto.addOptionSet(option1);
	            auto.addOption(optionValue1a, optPrice1a, option1);
	            auto.addOption(optionValue1b, optPrice1b, option1);
	            auto.addOptionSet(option2);
	            auto.addOption(optionValue2a, optPrice2a, option2);
	            auto.addOption(optionValue2b, optPrice2b, option2);
        	}
        } catch (AutoException e){
            e.fix(e.getErrorNo());
        } catch (Exception e) {
        	System.out.println(e.getMessage());
        }
        return auto;
    }

	@Override
	public void addAuto(Automobile auto) throws AutoException {
		AutoServer build = new BuildAuto();
		build.addAuto(auto);
	}

	@Override
	public String provideModelList() {
		AutoServer build = new BuildAuto();
		return build.provideModelList();
	}

	@Override
	public Automobile getAuto(String autoName) {
		AutoServer build = new BuildAuto();
		return build.getAuto(autoName);
	}
}
