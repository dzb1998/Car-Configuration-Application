package server;
import java.net.*;
import java.util.Properties;

import adapter.BuildAuto;
import model.Automobile;

import java.io.*;

public class DefaultSocketClient extends Thread 
			implements SocketClientInterface, SocketClientConstants {

    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Socket sock;
    
    public DefaultSocketClient(Socket s) {       
    	sock = s;
    }

    @Override
    public void run() {
       if (openConnection()) {
          handleSession();
          closeSession();
       }
    }

    @Override
    public boolean openConnection() {
    	try {
            out = new ObjectOutputStream(sock.getOutputStream());
            in = new ObjectInputStream(sock.getInputStream());
    	} catch (Exception e) {
    		if (DEBUG) System.err.println
    	       ("Unable to obtain stream");
    		return false;
    	}
    	return true;
    }

    @Override
    public void handleSession() {
    	if (DEBUG) System.out.println ("Handling session");
    	String request = null;
    	AutoServer build = new BuildAuto();
        try {
        	request = (String)in.readObject();
            System.out.println("Client request: " + request);
	        while(request != null && !request.equals("#")) {
	            if (request.equals("upload")) {
	            	// upload the properties
	                try {
	                    Properties props = (Properties) in.readObject();
	                    Automobile auto = BuildCarModelOptions.createAutoByProps(props);
	                    if(auto.getName() != null) {
	                    	build.addAuto(auto);
	                    	String response = "Automobile " + auto.getName() 
	                    							+ " built successfully.";
	                        out.writeObject(response);
	                        out.flush();
	                        System.out.println(response);
	                    } else {
	                    	String response = "Building failed.";
	                        out.writeObject(response);
	                        out.flush();
	                        System.out.println(response);
	                    }
	                } catch (Exception e) {
	                	String response = "Building failed.";
                        out.writeObject(response);
                        out.flush();
                        System.out.println(response);
	                }
	            } else if (request.equals("configure")) {
	            	// configure the car
	            	String response = build.provideModelList();
	            	out.writeObject(response);
	            	out.flush();
	            	System.out.println("Send the model list to client.");
	            	String autoName = (String)in.readObject();
	                Automobile a = build.getAuto(autoName);
	                out.writeObject(a);
	                out.flush();
	                System.out.println("Send the selected automobile to client.");
	            }
	            request = (String)in.readObject();
	            System.out.println("Client request: " + request);
	        }
	        System.out.println("Finished.");
        } catch (Exception e){
            if (DEBUG) System.err.println("Handling session");
        }
    }

    @Override
    public void closeSession() {
    	try {
    		out.close();
    		in.close();
    		sock.close();
    	} catch (IOException e) {
    		if (DEBUG) {
    			System.err.println("Error closing socket");
    		}
    	}
    }
}
