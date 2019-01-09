package adapter;

import exception.AutoException;

public interface CreateAuto {

	public void buildAuto(String fileName, int fileType) throws AutoException;

	public void printAuto(String modelName) throws AutoException;

	public void printAllAuto() throws AutoException;
	
}
