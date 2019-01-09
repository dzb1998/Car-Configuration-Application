package exception;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import model.Automobile;

public class AutoException extends Exception {
	
	private String errorMsg;
	private int errorNum;
	
	// static final
	// Missing price for Automobile in text file. 
	public static final int MISSING_PRICE_IN_TEXTFILE = 11;
	// Missing OptionSet data (or part of it). 
	public static final int MISSING_OPTIONSET_DATA = 22;
	// Missing Option Data.
	public static final int ERROR_OPTION_SIZE = 33;
	// Missing filename or wrong filename. 
	public static final int ERROR_FILENAME = 44;
	// Missing Option name
	public static final int MISSING_OPTION_NAME = 55;
	// Missing Option price
	public static final int MISSING_OPTION_PRICE = 66;
	
	public AutoException() {
		super();
		printProblem();
	}
	
	public AutoException(String msg) {
		super(msg);
		this.errorMsg = msg;
		printProblem();
	}
	
	public AutoException(int num) {
		super();
		this.errorNum = num;
		printProblem();
	}
	
	public AutoException(String msg, int num) {
		super(msg);
		this.errorMsg = msg;
		this.errorNum = num;
		printProblem();
	}
	
	public String getErrorMessage() {
		return errorMsg;
	}
	
	public int getErrorNumber() {
		return errorNum;
	}
	
	public void setErrorMessage(String msg) {
		this.errorMsg = msg;
	}
	
	public void setErrorNumber(int num) {
		this.errorNum = num;
	}
	
	public void fix(Automobile auto) {
		Fix1to100 f1 = new Fix1to100();
		
		switch (errorNum) {
		case 11:
			f1.fix11(auto);       // Missing price for Automobile in text file. 
			break;
		case 22:
			f1.fix22();           // Missing OptionSet data (or part of it). 
			break;			
		case 33:
			f1.fix33();           // Option Size; array out of bound.
			break;
		case 44:
			f1.fix44(); 	      // Missing filename or wrong filename. 
			break;
		case 55:
			f1.fix55();           // Missing option name
			break;
		case 66:
			f1.fix66();           // Missing option price
			break;
		default:
			break;
		}
	}
	
	// actually is toString the problem
	public void printProblem() {
		System.out.println("Error " + errorNum + ": " + errorMsg);
	}	
	
	public void logExceptions(String err) {
		try {
			File logExp = new File("logExp.txt");
			PrintWriter pw = new PrintWriter(new FileWriter(logExp, true));
			pw.append(err);
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
