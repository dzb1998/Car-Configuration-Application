package client;
import java.net.*;
import java.util.Properties;
import java.util.Scanner;

import model.Automobile;

import java.io.*;

public class DefaultSocketClient extends Thread
			implements SocketClientInterface, SocketClientConstants, ModelDataInterface {

    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Socket sock;
    private String strHost;
    private int iPort;

    /**
     * Constructor.
     * @param strHost host
     * @param iPort port number
     */
    public DefaultSocketClient(String strHost, int iPort) {       
            setPort(iPort);
            setHost(strHost);
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
    		sock = new Socket(strHost, iPort);
    	} catch(IOException socketError) {
    		if (DEBUG) System.err.println
    	        ("Unable to connect to " + strHost);
    		return false;
    	}
    	try {
            in = new ObjectInputStream(sock.getInputStream());
            out = new ObjectOutputStream(sock.getOutputStream());
    	} catch (Exception e) {
    		if (DEBUG) System.err.println
    	       ("Unable to obtain stream to/from " + strHost);
    		return false;
    	}
    	return true;
    }

    @Override
    public void handleSession() {
    	if (DEBUG) {
    		System.out.println ("Handling session with " + strHost + ":" + iPort);
    	}
    	String str = "";
    	Scanner scanner = null;
    	try {
    		scanner = new Scanner(System.in);
    		System.out.println("Please choose an operation:\n\t"
    				+ "1. Upload Properties file. \n\t"
    				+ "2. Configure a car.\n\t" 
    				+ "3. Enter # to quit.");
    		while ((str = scanner.nextLine()) != null && !str.equals("#")) {
    			if(str.equals("1")) {
    				System.out.println("Please input the property file name:");
    				String fileName = scanner.nextLine();
    				Properties prop = CarModelOptionsIO.readPropFile(fileName);
    				out.writeObject("upload");
    				out.flush();
                    out.writeObject(prop);
                    out.flush();
                    handleInput();
    			} else if (str.equals("2")) {
    				out.writeObject("configure");
    				out.flush();
					String list = (String) in.readObject();
    				String choice = SelectCarOption.chooseModel(list, scanner);
    				out.writeObject(choice);
    				out.flush();
    				Automobile auto = (Automobile) in.readObject();
                    if (auto != null) {
                    	SelectCarOption.selectOptions(auto, scanner);
                    } else {
                    	System.out.println("Automobile not found.");
                    }
    			} else {
    				System.out.println("Command unknown, please check and try again...");
    			}
    			System.out.println("Please choose an operation:\n\t"
    					+ "1. Upload Properties file. \n\t"
        				+ "2. Configure a car.\n\t" 
    					+ "3. Enter # to quit.");
    		}
    		System.out.println("Finished.");
    		scanner.close();
    	} catch (Exception e) {
    		e.printStackTrace();
    		if (DEBUG) { 
    			System.out.println ("Handling session with " + strHost + ":" + iPort);
    		}
    	}
    }

    /**
     * Handle input.
     */
    public void handleInput(){
        try {
            String response = "";
            if ((response = (String) in.readObject()) != null) {
            	CarModelOptionsIO.receiveResponse(response);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String getModelList() {
    	String list = null;
    	if (openConnection()) {
        	try {
    	    	out.writeObject("configure");
    			out.flush();
    			list = (String)in.readObject();
        	} catch (Exception e){
                System.out.println(e.getMessage());
            }
            closeSession();
        }
    	return list;
    }

    @Override
    public Automobile getModelInfo(String model) {
    	Automobile auto = null;
    	if (openConnection()) {
	    	try {
	    		out.writeObject("configure");
    			out.flush();
    			in.readObject();
	    		out.writeObject(model);
				out.flush();
				auto = (Automobile) in.readObject();
	    	} catch (Exception e){
	            System.out.println(e.getMessage());
	        } finally {
	        	closeSession();
	        }
    	}
    	return auto;
    }

    @Override
    public void closeSession() {
    	try {
    		in.close();
    		out.close();
    		sock.close();
    	} catch (IOException e) {
    		if (DEBUG) {
    			System.err.println("Error closing socket to " + strHost);
    		}
    	}       
    }

    /**
     * Set host.
     * @param strHost host
     */
    public void setHost(String strHost) {
    	this.strHost = strHost;
    }

    /**
     * Set port number.
     * @param iPort port number
     */
    public void setPort(int iPort) {
    	this.iPort = iPort;
    }

}
