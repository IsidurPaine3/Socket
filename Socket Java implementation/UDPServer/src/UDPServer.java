/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Max + IsidurPaine
 */
// Import the necessary packages
import java.net.*;  // Imports networking classes
import java.io.*;   // Imports input/output stream classes

public class UDPServer {  // Defines the UDPServer class
    public static void main(String[] args) throws IOException {  // Main method with IOException handling

        int port = 1234;  // Server listening port
        
        // Create a DatagramSocket object using the DatagramSocket(int port) constructor 
        // that accepts the Server's listening port as a parameter for the service to activate. 
        // In this example, port 1234 is used.  
        // Activate the Socket on the Server listening on port 1234
        DatagramSocket s = new DatagramSocket(port);  // Creates UDP server socket on port 1234

        // Server listening information
        // InetAddress address = s.getLocalAddress();
        // String server = address.getHostAddress();
        int serverPort = s.getLocalPort();  // Gets local port
        System.out.println("UDP Server listening on port: " + serverPort);  // Announces server readiness

        // Infinite loop for listening to Clients
        while (true) {  // Continuous loop for client requests

            // Create a DatagramPacket object that allows us to read (in this case) 
            // data from the client as a byte array. The constructor accepts, 
            // in addition to the byte array and its length, also the client address and port.
            // Preparation of information to receive
            byte[] buf = new byte[65536];  // Receive buffer
            System.out.println("Waiting for calls from Clients... ");  // Status message
            DatagramPacket recv = new DatagramPacket(buf, buf.length);  // Creates receive packet
            
            // Call the receive() method on the DatagramSocket object: 
            // this way it's possible to receive the packet from the client contained in the DatagramPacket object.
            s.receive(recv);  // Receives UDP packet (blocking)

            // Information about the Client that made the call
            InetAddress address = recv.getAddress();  // Client IP address
            String client = address.getHostAddress();  // Client IP as string
            port = recv.getPort();  // Client port
            System.out.println("Calling Client: " + client + " port: " + port);  // Logs client info

            // The received packet will contain the data, structured as a byte array, 
            // which can be obtained using the getData() method of the DatagramPacket object 
            // and can be converted to a string for printing.
            // Message received from Client
            String message = (new String(recv.getData()).trim());  // Converts bytes to trimmed string
            System.out.println("The Client wrote: " + message);  // Prints received message
        }
    }
}  // Ends UDPServer class
