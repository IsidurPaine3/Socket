package tcpclient;  // Declares the package name for this Java TCP client class.

/**
 * Javadoc comment for the TCPClient class.
 * @author Max + IsidurPaine
 */
import java.io.*;   // Imports java.io package containing classes for input/output streams.
import java.net.*;  // Imports java.net package providing classes for network operations, including Socket and ServerSocket.

// The java.io package contains classes for input and output streams.
// The java.net package provides classes for networking. In particular,
// it contains the Socket and ServerSocket classes.

class TCPClient {  // Defines the TCPClient class.
    public static void main(String argv[]) throws Exception {  // Main method, entry point of the application.
        String serverName = "localhost";  // Server IP address or hostname.
        int port = 1234;  // Server listening port.
        
        // The request string will contain characters typed from the keyboard through the keyboardInput stream.
        String request = ".";  // Client request message.
        String response;  // Server response message.
        
        // The keyboardInput stream is an input stream for the process connected to the keyboard (standard input).
        BufferedReader keyboardInput = new BufferedReader(new InputStreamReader(System.in));  // Keyboard input stream.
        
        // Creates the keyboardInput stream object of the BufferedReader class. 
        // The InputStreamReader constructor is passed System.in, 
        // which connects the stream to the keyboard (standard input).	
        // In turn, an InputStreamReader object is passed 
        // to the BufferedReader class constructor, thus creating 
        // a buffered flow between keyboard and process.
        
        // The socket for the connection is clientSocket.
        Socket clientSocket = new Socket(serverName, port);  // Creates TCP connection to server.
        
        // Creates the clientSocket instance of the Socket class  
        // which starts the TCP connection between client and server. 
        // The "serverName" string must be replaced with the hostname 
        // or the server's IP number (for example, "luigidellerba.edu.it" or 160.80.1.16).

        // If the hostname is used, before the TCP connection actually starts, 
        // the client performs a DNS lookup on the hostname to get the host's IP address.
        // The number 1234 is the port number which must be the same used by the 
        // server side of the application (listening port).
        // As already mentioned, the host's IP address and port number
        // identify the server process.
        
        System.out.println("Remote port: " + clientSocket.getPort());  // Prints remote port.
        System.out.println("Local port: " + clientSocket.getLocalPort());  // Prints local port.
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());  // Output stream to server.
        
        // The fromServer stream is an input stream to the process connected 
        // to the socket that allows retrieving data arriving from the network.
        BufferedReader fromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));  // Input stream from server.
        
        // These are stream objects connected to the socket:
        // - the outToServer stream is the process output to the socket.
        // - the fromServer stream is the input from the socket to the process.
        
        while (!request.equals("exit")) {  // Loops until user types "exit".
            System.out.print("Enter a phrase: ");  // Prompts user for input.
            
            request = keyboardInput.readLine();  // Reads line from keyboard (blocking method).
            // blocking method
	
            // The outToServer stream is an output stream from the process connected to the socket.
            outToServer.writeBytes(request + "\n");  // Sends request to server.
            
            // Sends the request string to the outToServer stream. 
            // The string passes through the client's TCP socket. 
            // The client then waits for a response message from the server.
        
            response = fromServer.readLine();  // Reads server response (blocking method).
            // blocking method
        
            // The sequence of characters arriving from the server, through 
            // the fromServer stream, is assigned to the response string, 
            // until the line ends with a carriage return ('\n').
        
            System.out.println("Server response: " + response);  // Prints server response.
            // prints the response string sent by the server to the monitor.
        }
        
        // closes the connection socket and, therefore, the TCP connection between client and server.
        clientSocket.close();  // Closes TCP connection.
    } 
}  // Ends TCPClient class.
