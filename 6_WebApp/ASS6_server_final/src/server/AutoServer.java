package server;


import exception.AutoException;
import model.Automobile;

public interface AutoServer {

	public void addAuto(Automobile auto) throws AutoException;
	public String provideModelList();
	public Automobile getAuto(String autoName);
	
}
