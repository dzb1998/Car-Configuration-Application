package exception;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AutoException extends Exception {

	private int errorNo;
	private String errorMsg;
	private static final int [] ERROR_NO_SET 
							= new int [] {1, 2, 3, 4, 5, 6, 7, 8};	
	private static final String [] ERROR_MSG_SET 
							= new String [] {"Missing Model Price",
											"Missing OptionSet Name",
											"Missing OptionSet Size",
											"Missing Option Name",
											"Missing Option Price",
											"Missing Filename or Wrong Filename",
											"Option Set Not Found",
											"Option Not Found"};
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor with no argument.
	 */
	public AutoException() {
		super();
		printAutoException();
		logAutoException();
	}

	/**
	 * Constructor.
	 * @param errorNo error number
	 */
	public AutoException(int errorNo) {
		super();
		int index = findErrorNo(errorNo);
		if (index != -1) {
			this.errorNo = errorNo;
		}
		errorMsg = ERROR_MSG_SET[index];
		printAutoException();
		logAutoException();
	}

	/**
	 * Constructor.
	 * @param errorMsg error message
	 */
	public AutoException(String errorMsg) {
		super();
		int index = findErrorMsg(errorMsg);
		if (index != -1) {
			this.errorMsg = errorMsg;
		}
		errorNo = ERROR_NO_SET[index];
		printAutoException();
		logAutoException();
	}

	/**
	 * Constructor.
	 * @param errorNo error number
	 * @param errorMsg error message
	 */
	public AutoException(int errorNo, String errorMsg) {
		super();
		if (validateError(errorNo, errorMsg)) {
			this.errorNo = errorNo;
			this.errorMsg = errorMsg;
		}
		printAutoException();
		logAutoException();
	}

	/**
	 * Get error number.
	 * @return error number
	 */
	public int getErrorNo() {
		return errorNo;
	}

	/**
	 * Set error number.
	 * @param errorNo error number
	 */
	public void setErrorno(int errorNo) {
		if (findErrorNo(errorNo) != -1) {
			this.errorNo = errorNo;
		}
	}

	/**
	 * Get the error message.
	 * @return error message
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * Set error message.
	 * @param errorMsg error message
	 */
	public void setErrormsg(String errorMsg) {
		if (findErrorMsg(errorMsg) != -1) {
			this.errorMsg = errorMsg;
		}
	}

	/**
	 * Print auto exception.
	 */
	public void printAutoException() {
		System.out.println("AutoException: [errorNo=" + errorNo 
							+ ", errorMsg=" + errorMsg); 
	}

	/**
	 * Log the auto exception information.
	 */
	public void logAutoException() {
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyyMMddHHmmss");
		String time=format.format(date);
		BufferedWriter br = null;
		try {
			br = new BufferedWriter(
						new FileWriter("AutoException.log", true));
			br.write("AutoException: [errorNo=" + errorNo 
								+ ", errorMsg=" + errorMsg
								+ ", time=" + time + "\n");
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Fix the exception.
	 * @param errorNo error number
	 */
	public void fix(int errorNo) {
		Fix1to6 f16 = new Fix1to6();
		Fix7to8 f78 = new Fix7to8();
		switch(errorNo) {
			case 1: f16.fix1(errorNo);	break;
			case 2: f16.fix2(errorNo);	break;
			case 3: f16.fix3(errorNo);	break;
			case 4: f16.fix4(errorNo);	break;
			case 5: f16.fix5(errorNo);	break;
			case 6: f16.fix6(errorNo);	break;
			case 7: f78.fix7(errorNo);	break;
			case 8: f78.fix8(errorNo);	break;
			default: break;
		}
	}

	/**
	 * Find the corresponding error number index.
	 * @param no error number
	 * @return index
	 */
	private int findErrorNo(int no) {
		for(int i = 0; i < ERROR_NO_SET.length; i++) {
			if (no == ERROR_NO_SET[i]) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Find the corresponding error message index.
	 * @param msg error message
	 * @return index
	 */
	private int findErrorMsg(String msg) {
		for (int i = 0; i < ERROR_MSG_SET.length; i++) {
			if (msg.equals(ERROR_MSG_SET[i])) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Validate the error information.
	 * @param no error number
	 * @param msg error message
	 * @return true if valid, false otherwise
	 */
	private boolean validateError(int no, String msg) {
		for (int i = 0; i < ERROR_NO_SET.length; i++) {
			if (ERROR_NO_SET[i] == no && ERROR_MSG_SET[i].equals(msg)) {
				return true;
			}
		}
		return false;
	}
}

