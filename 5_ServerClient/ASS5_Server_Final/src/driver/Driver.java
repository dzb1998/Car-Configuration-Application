package driver;

import adapter.BuildAuto;
import server.*;
import server.AutoServerSocket;

import java.io.*;
import java.net.*;

public class Driver {

    public static void main(String[] args) {
    	
        // Create BuildAuto object
        BuildAuto buildAuto = new BuildAuto();
        String focusFilename = "FordZTW.txt";
        String golfFileName = "VWGolf.txt";

        // Build couple of automobiles
        buildAuto.buildAuto(focusFilename);
        buildAuto.buildAuto(golfFileName);

        // Run server socket thread
        String strLocalHost = null;

        try {
            strLocalHost = InetAddress.getLocalHost().getHostAddress().toString();
        } catch (UnknownHostException e) {
            System.err.println("Unable to find local host");
            System.exit(1);
        }

        AutoServerSocket aServerSocket = null;
        try {
            ServerSocket serverSocket = new ServerSocket(SocketClientConstants.iDAYTIME_PORT);
            System.out.println("Listening on port " + SocketClientConstants.iDAYTIME_PORT);
            aServerSocket = new AutoServerSocket(strLocalHost, SocketClientConstants.iDAYTIME_PORT, serverSocket.accept(), buildAuto);
        } catch (IOException e) {
            System.err.println("Could not listen on port " + SocketClientConstants.iDAYTIME_PORT);
            System.exit(1);
        }

        aServerSocket.start();
        
    }
}
