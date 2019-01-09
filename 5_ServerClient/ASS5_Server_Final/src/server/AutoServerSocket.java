package server;

import adapter.BuildAuto;
import model.Automobile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Properties;

public class AutoServerSocket extends DefaultSocketClient {

    private BuildCarModelOptions buildCarModelOptions;

    public AutoServerSocket(String strHost, int iPort, Socket clientSocket, BuildAuto buildAuto) {
        super(strHost, iPort);
        this.clientSocket = clientSocket;
        System.out.println("Client socket accepted on " + strHost + ":" + iPort);
        buildCarModelOptions = new BuildCarModelOptions(buildAuto);
    }

    @Override
    public boolean openConnection() {
        if (clientSocket.isConnected())
            return true;

        return false;
    }

    @Override
    public void handleSession() {
        Properties properties;
        char option;
        String model;
        Automobile autoToClient;
        Object tempObj;

        try {
            objOut = new ObjectOutputStream(clientSocket.getOutputStream());
            objIn = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException e) {
            if (DEBUG) System.err.println("Unable to obtain ObjectInputStream on port " + iPort);
            System.exit(1);
        }
        
        // zhubo fighting
        Object outputStoC = null;
        Object inputCtoS = null;
        AutoServerProtocol autoProtocol = new AutoServerProtocol();
        
        try {
            outputStoC = autoProtocol.processInput(null);   // kind of initialize
			objOut.writeObject(outputStoC);     // prompt option to choose
			
	        while( (inputCtoS = objIn.readObject()) != null ) {
	        	
	        	if ( inputCtoS instanceof Properties ) {  
					
					// reach here to add new car; new properties
					
	            	if ( (properties = (Properties) inputCtoS) != null ) {
	                    buildCarModelOptions.buildAutoFromProperties(properties);
//	                    System.out.println("Successfully added automobile " + properties.getProperty("CarModel") + " to the list");
	            	}

//	                System.out.println("\nNewly added automobile: ");
//	                buildCarModelOptions.printAuto(properties.getProperty("CarModel"));
	                
	                // should change state in protocol
	                // should prompt again
	                // kind of start over from the top
	                
	                outputStoC = autoProtocol.processInput("Uploaded");   // kind of initialize
	                //System.out.println("Wanna continue? y or n");
//	                System.out.println(outputStoC);
	    			//objOut.writeObject(outputStoC);     // prompt option to choose

	                // back to client
//	                System.out.println("Wanna continue? y or n");
	                objOut.writeObject("Wanna continue? y or n");
	                
	             
				} else if ( ((String) inputCtoS).equals("End Program") ) {
//	        		System.out.println("Server: Program Terminated.");
					break;
					
				} else if ( ((String) inputCtoS).equals("SendList") ) {
					
					ArrayList<String> modelList = buildCarModelOptions.getModelList();
//					modelList.forEach(System.out::println);
//					System.out.println("Which one do you want to configure? ");
					
					objOut.writeObject(modelList);
//					System.out.println("Sent auto list");

				} else if ( inputCtoS instanceof String && ((String) inputCtoS).length() == 1 ) {
					
		        	outputStoC = autoProtocol.processInput((String) inputCtoS); 
		        	// server start to receive
//		        	System.out.println("Test server");
		        	objOut.writeObject(outputStoC);  // uploading, configuring

				} else if ( inputCtoS instanceof String && ((String) inputCtoS).length() > 3 ) {
					
					// reach here means server side received the car name
					// configure the car now
					
//					System.out.println("Server: received car name");
					model = (String) inputCtoS;
					outputStoC = buildCarModelOptions.getAutomobile(model);
//					System.out.println(((Automobile)outputStoC).toString());
					objOut.writeObject(outputStoC);
//					System.out.println("Server: Wrote to client");
				} 
	        	
	        }

			
			
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
        	// objout is str; "server start to receive"
			e.printStackTrace();
		}
                
        
        /////////////
        
        
        
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
}
