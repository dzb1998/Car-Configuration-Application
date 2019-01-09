package exception;

public class Fix1to6 {
	/**
	 * Fix error1.
	 * @param errorNo error number
	 */
	public void fix1(int errorNo) {
		System.out.println("Exception: " + errorNo
				+ "\nPlease check you input text, "
				+ "make sure it contains model price and try again.");
	}

	/**
	 * Fix error2.
	 * @param errorNo error number
	 */
	public void fix2(int errorNo) {
		System.out.println("Exception: " + errorNo
				+ "\nPlease check you input text, "
				+ "make sure it contains option set names and try again.");
	}

	/**
	 * Fix error3.
	 * @param errorNo error number
	 */
	public void fix3(int errorNo) {
		System.out.println("Exception: " + errorNo
				+ "\nPlease check you input file, "
				+ "make sure it contains option set sizes and try again.");
	}

	/**
	 * Fix error4.
	 * @param errorNo error number
	 */
	public void fix4(int errorNo) {
		System.out.println("Exception: " + errorNo
				+ "\nPlease check you input file, "
				+ "make sure it contains option names and try again.");
	}

	/**
	 * Fix error5.
	 * @param errorNo error number
	 */
	public void fix5(int errorNo) {
		System.out.println("Exception: " + errorNo
				+ "\nPlease check you input file, "
				+ "make sure it contains option prices and try again.");
	}

	/**
	 * Fix error6.
	 * @param errorNo error number
	 */
	public void fix6(int errorNo) {
		System.out.println("Exception: " + errorNo
				+ "\nCannot find the file. Please check you input file name, "
				+ "enter it and try again.");
	}
}
