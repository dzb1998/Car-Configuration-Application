package driver;

import client.DefaultSocketClient;

public class Driver {

	public static void main(String [] args) {
		String strHost = "localhost";
        int port = 8899;
        DefaultSocketClient clientSocket = new DefaultSocketClient(strHost, port);
        clientSocket.start();
	}
	
}
