package server;

import model.Automobile;
import util.AutomobileIO;
import java.net.*;
import java.io.*;
import java.util.*;

import org.w3c.dom.html.HTMLHRElement;

public class DefaultSocketClient extends Thread implements SocketClientInterface, SocketClientConstants {

    protected ObjectInputStream objIn;
    protected ObjectOutputStream objOut;
    protected Socket clientSocket;
    protected String strHost;
    protected int iPort;

    public DefaultSocketClient(String strHost, int iPort) {
        setPort(iPort);
        setHost(strHost);
    }//constructor

    public static void main(String[] args) {
        /* debug main; does daytime on local host */
        String strLocalHost = null;

        try {
            strLocalHost = InetAddress.getLocalHost().getHostAddress().toString();
        } catch (UnknownHostException e) {
            System.err.println("Unable to find local host");
        }

        DefaultSocketClient d = new DefaultSocketClient(strLocalHost, iDAYTIME_PORT);
        d.start();
    }

    @Override
    public void run() {
        if (openConnection()) {
            handleSession();
            closeSession();
        }
    }//run

    @Override
    public boolean openConnection() {
        try {
            clientSocket = new Socket(strHost, iPort);
            System.out.println("Client Socket opened on: " + strHost + ":" + iPort);
        } catch (IOException socketError) {
            if (DEBUG) System.err.println
                    ("Unable to connect to " + strHost);
            return false;
        }
        try {
            objOut = new ObjectOutputStream(clientSocket.getOutputStream());
            objIn = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException e) {
            if (DEBUG) System.err.println("Unable to obtain stream to/from " + strHost);
            return false;
        }
        return true;
    }

    @Override
    public void handleSession() {
        AutomobileIO automobileIO = new AutomobileIO();
        Scanner scanner = new Scanner(System.in);
        String clientInput = null;
        Properties properties = null;
        Automobile automobileForConfig = null;
        
        
        // zhubo fighting
        Object inServerToC = null;
        Object outClientToS = null;
        
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        Object fromServer; // or obj?
        Object fromUser;
        
        // here objin is str like "server start to receive"
        try {
            while ((fromServer = objIn.readObject()) != null) {
            	
            	//System.out.println("Server: " + fromServer);
            	
            	if (fromServer instanceof ArrayList<?>) {
					
					// reach here means auto list received on client side
					
					// let client make choice
					System.out.println("Received list of automobiles.");
					ArrayList<String> modelList = (ArrayList<String>) fromServer;
					modelList.forEach(System.out::println);
					System.out.println("Choose one to configure.");
        			
					// let client make choice
					fromUser = stdIn.readLine();
        			if (fromUser != null) {
						System.out.println("Client chose: " + fromUser);
					} else {
						System.out.println("Invalid input!");
						System.exit(1);
					}
        			
        			// send car to server
        			if (modelList.contains( (String) fromUser)) {
						objOut.writeObject(fromUser);
					} else {
						System.out.println("Automobile not found!");
						System.exit(1);
					}
            	}

        			
            	if ( fromServer instanceof Automobile ) {
					System.out.println("Client: Received automobile!");
					System.out.println("Print the automobile");
					Automobile auto = (Automobile) fromServer;
					System.out.println(auto.toString());
					
					// client start to configure their choices
					System.out.println();
					String choice = null;
					String optSetName = null;
					for (int n = 0; n < auto.getOptionSets().size(); n++) {
						optSetName = auto.getOptionSetName(n);
						System.out.println(auto.getOptionSet(n));
						System.out.println("Choose option in option set" + optSetName);
						choice = stdIn.readLine();
						auto.setOptionChoice(optSetName, choice);
					}
					
					// client finished choosing, print out the choice and total price
					for (int n = 0; n < auto.getOptionSets().size(); n++) {
						optSetName = auto.getOptionSetName(n);
						System.out.println(optSetName + ": " + auto.getOptionChoiceName(optSetName));
					}
					System.out.println("Total price: " + auto.getTotalPrice());
					
					// client want to go back to choose option
					// ???
					
					System.out.println("Wanna continue? y or n");
					fromUser = stdIn.readLine();
					if ( ((String)fromUser).equalsIgnoreCase("y") ) {
						objOut.writeObject(fromUser);
					} else if ( ((String)fromUser).equalsIgnoreCase("n") ) {
						System.out.println("Client chose: Terminate the program.");
						objOut.writeObject("End Program");
						break;
					} 

					
				}
            	//////
            	if ( ((fromServer instanceof ArrayList<?>) != true) &&
            			((fromServer instanceof Automobile) != true)) {
					
				
	            	if ( ((String) fromServer).equals("Choosing") ) {
	           			System.out.println("What would you like to do? (Enter a, b, or c)\n" 
	        					+ "\ta. Upload Properties file\n"
	        					+ "\tb. Configure a car\n");
	        			System.out.println("Client now choosing...");
	        			System.out.println("Enter your option: ");
	
	        			fromUser = stdIn.readLine();
	        			
	                	if (fromUser != null) {
	        				System.out.println("Client chose: " + fromUser);  // one of a, b, c
	        				objOut.writeObject(fromUser);
	        			}
	
	        			
	
					} else if ( ((String) fromServer).equals("Uploading") ) {
	        			System.out.println("Client now choose to upload the file.");
	        			System.out.println("Enter .properties file name: ");
	        			
	        			fromUser = stdIn.readLine();
	        			if (fromUser != null) {
							System.out.println("Client provided: " + fromUser);
							properties = automobileIO.parseProperties( (String) fromUser);
							System.out.println("Succeed! Sending file to server");
							objOut.writeObject(properties);
							//System.out.println("Automobile added!");
						}
	        			
	        			
					} else if ( ((String) fromServer).equals("Configuring") ) {
						
	        			System.out.println("Client now choose to configure the car.");
	        			System.out.println("Waiting for server to send the list...");
	        			// tell server to send the list
	        			String sendList = "SendList";
	        			objOut.writeObject(sendList);
	        									
						
					} else if ( ((String) fromServer).equals("Wanna continue? y or n") ) {
						System.out.println("Wanna continue? y or n");
						fromUser = stdIn.readLine();
						if ( ((String)fromUser).equalsIgnoreCase("y") ) {
							objOut.writeObject(fromUser);
						} else if ( ((String)fromUser).equalsIgnoreCase("n") ) {
							System.out.println("Client chose: Terminate the program.");
							objOut.writeObject("End Program");
							break;
						} 
						
					} else if ( ((String) fromServer).equals("End program") ) {
						System.out.println("Program terminated.");
						break;
					}
            	}
            	
//    			fromUser = stdIn.readLine();
//    			
//            	if (fromUser != null) {
//    				System.out.println("Client: " + fromUser);  // one of a, b, c
//    				objOut.writeObject(fromUser);
//    			}
            	
            }

        } catch (IOException e) {
			e.printStackTrace();
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

    }

    @Override
    public void closeSession() {
        try {
            objIn = null;
            objOut = null;
            clientSocket.close();
        } catch (IOException e) {
            if (DEBUG)
                System.err.println("Error closing socket to " + strHost);
        }
    }

    public void setHost(String strHost) {
        this.strHost = strHost;
    }

    public void setPort(int iPort) {
        this.iPort = iPort;
    }
}
