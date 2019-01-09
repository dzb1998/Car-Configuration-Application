package driver;

import java.io.IOException;
import java.net.ServerSocket;

import adapter.BuildAuto;
import exception.AutoException;
import server.DefaultSocketClient;

public class Driver {

	public static void main(String [] args) {
		
		// First save one properties in the LinkedHashedMap
        // Create BuildAuto object
        BuildAuto buildAuto = new BuildAuto();
        String focusFilename = "Focus.properties";

        // Build couple of automobiles
        try {
			buildAuto.buildAuto(focusFilename, 1);
		} catch (AutoException e1) {
			e1.printStackTrace();
		}
		
        // after save the properties in LinkedHashedMap, start the server
		ServerSocket serverSocket = null;
		
        try {
            serverSocket = new ServerSocket(8899);
        } catch (IOException e) {
            System.out.println("Cannot listen on the port.");
        }
        try {
        	System.out.println("Server is running now...");
	        while(true) {
	        	// listen to connections
	        	try {
		            DefaultSocketClient client = new DefaultSocketClient(
		            									serverSocket.accept());
		            client.start();
	        	} catch (IOException e) {
	        		System.err.println("Accept failed.");
	        		System.exit(1);
				}
	        }
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	System.out.println("Stop.");
        	if (serverSocket != null) {
	            try {
					serverSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        }
	}
}
