package exception;

public class Fix7to8 {

	/**
	 * Fix error7.
	 * @param errorNo error number
	 */
	public void fix7(int errorNo) {
		System.out.println("Exception: " + errorNo
				+ "\nOption set not found, please check again.");		
	}

	/**
	 * Fix error8.
	 * @param errorNo error number
	 */
	public void fix8(int errorNo) {
		System.out.println("Exception: " + errorNo
				+ "\nOption not found, please check again.");
	}
}
