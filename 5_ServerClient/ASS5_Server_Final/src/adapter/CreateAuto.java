package adapter;

import exception.AutoException;

public interface CreateAuto {
	void buildAuto(String filename) throws AutoException;	
	void printAuto(String automobileModel);
}
