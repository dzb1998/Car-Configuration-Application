package server;

public class AutoServerProtocol {
	
	private static final int WAITING = 0;
	private static final int CHOOSING = 1;
	private static final int UPLOAD = 2;
	private static final int CONFIG = 3;
	private static final int END = 4;
	
	private int state = WAITING;
	
	public AutoServerProtocol() {
		
	}
	
	public String processInput(String theInput) {
		
		// return value into fromServer (in the defClient)
		String caseStr = null;   
		
		if (state == WAITING) {
//			System.out.println("Server started to receive.\n" + "What would you like to do? (Enter a, b, or c)\n" 
//					+ "\ta. Upload Properties file\n"
//					+ "\tb. Configure a car\n");
			caseStr = "Choosing";
			state = CHOOSING;
		} else if (theInput.equalsIgnoreCase("y")) {
//			System.out.println("Server started to receive.\n" + "What would you like to do? (Enter a, b, or c)\n" 
//					+ "\ta. Upload Properties file\n"
//					+ "\tb. Configure a car\n");
			caseStr = "Choosing";
			state = CHOOSING;
		} else if (state == CHOOSING) {
			
			// caseStr = "State: Choosing option.";
			if (theInput.equalsIgnoreCase("a")) {
//				System.out.println("Server: Client chose a. Upload; Server started to upload");
				caseStr = "Uploading";
				state = UPLOAD;
			} else if (theInput.equalsIgnoreCase("b")) {
//				System.out.println("Server: configuring. ");
				caseStr = "Configuring";
				state = CONFIG;
			} else if (theInput.equalsIgnoreCase("c")) {
//				System.out.println("Server: Client choose to end program.");
				caseStr = "End program";
				state = END;
			} else if (theInput.equalsIgnoreCase("y")) {
//				System.out.println("Server: Client want to see the option again.");
//				System.out.println("Server started to receive.\n" + "What would you like to do? (Enter a, b, or c)\n" 
//						+ "\ta. Upload Properties file\n"
//						+ "\tb. Configure a car\n");
				caseStr = "Choosing";
				state = CHOOSING;
			}
			
		} else if (state == UPLOAD) {
			
			// reach here when uploading finished
//			System.out.println("Uploading finished");
//			System.out.println(state);
			// change state to "Choosing"; loop back; prompt again
			caseStr = "Choosing";
			state = CHOOSING;
			
		} else if (state == CONFIG) {
			
			// reach here when configuring finished
//			System.out.println("Configuring finished");
			// change state to "Choosing"; loop back; prompt again
			caseStr = "Choosing";
			state = CHOOSING;

		} else if (state == END) {
			
		}
		
		return caseStr;
	}
}