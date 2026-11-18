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

public class UDPClient {  // Defines the UDPClient class
    public static void main(String[] args) throws IOException {  // Main method with IOException handling
        
        int port = 1234;  // Server port number
        InetAddress addr = InetAddress.getByName("localhost");  // Server address
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));  // Standard input stream
        byte[] msg = {0};  // Message buffer

        // Create a DatagramSocket object to activate the Client's Socket port. 
        // It's also possible not to specify the port since an empty constructor 
        // can be used which will take an available one.
        // Creation of the Socket for sending the Datagram with dynamic Client port
        DatagramSocket datagramSocket = new DatagramSocket();  // Creates UDP socket with dynamic port

        // Create a DatagramPacket object to specify the byte array to use, 
        // its length, the server address and port.
        // Creation of the packet to send to the Server
        DatagramPacket datagramPacket = new DatagramPacket(msg, msg.length, addr, port);  // Creates UDP packet
        
        System.out.println("Remote port " + datagramPacket.getPort());  // Prints remote port

        // Infinite loop for Client text input
        while (true) {  // Continuous loop for user input
            System.out.print("Enter: ");  // Prompts user input
            String userInput = stdIn.readLine();  // Reads user input
            msg = userInput.getBytes();  // Converts string to bytes
            datagramPacket.setData(msg);  // Sets packet data
            datagramPacket.setLength(msg.length);  // Sets packet length
            System.out.println("Sending byte message: " + msg.length);  // Logs message length
            
            // Send the created packet on the datagram socket through 
            // the send() method of the DatagramSocket object.
            // Send packet on Socket
            datagramSocket.send(datagramPacket);  // Sends UDP packet
        }
    }
}  // Ends UDPClient class
