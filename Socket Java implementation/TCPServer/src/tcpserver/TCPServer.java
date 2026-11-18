package tcpserver;  // Declares the package name for this Java TCP server class.

/**
 * Javadoc comment for the TCPServer class.
 * @author Max + IsidurPaine - Indicates the authors of this code.
 */
import java.io.*;   // Imports java.io package for input/output stream classes.
import java.net.*;  // Imports java.net package for network classes, including ServerSocket and Socket.

class TCPServer {  // Defines the TCPServer class.
    public static void main(String argv[]) throws Exception {  // Main method, entry point of the application.
        String request = "";  // Client request message.
        String response;  // Server response message.
        int port = 1234;  // Listening port.
        
        // Creates the listeningSocket object of the ServerSocket class
        // that the server uses to receive client requests. 
        // The server's IP address and port number 1234 identify the server process.
        ServerSocket listeningSocket = new ServerSocket(port);  // Creates server socket on port 1234.
        
        System.out.println("Server listening on port " + port);  // Announces server readiness.
        Socket connectionSocket = listeningSocket.accept();  // Accepts client connection (blocking method).
        
        // The accept method of the ServerSocket class is blocking; it creates a new socket,  
        // connectionSocket, when a client request message arrives at listeningSocket. 
        // The new socket created, connectionSocket, has the same port number as listeningSocket.
        // TCP then creates a point-to-point logical channel between the clientSocket on the 
        // client side and the connectionSocket on the server side. From this moment, client and server  
        // can exchange data through the logical channel.
 
        // The program now creates stream objects, similar to the stream objects 
        // created in the client.

        BufferedReader fromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));  // Input stream from client.
        DataOutputStream toClient = new DataOutputStream(connectionSocket.getOutputStream());  // Output stream to client.

        while (!request.equals("exit")) {  // Loops until client sends "exit".
            request = fromClient.readLine();  // Reads client request (blocking method).
            // blocking method
            
            // Converts the request string to uppercase.
            response = request.toUpperCase() + "\n";  // Prepares uppercase response.
            
            toClient.writeBytes(response);  // Sends response to client.
        }
        
        listeningSocket.close();  // Closes listening socket.
        connectionSocket.close();  // Closes connection socket.
    } 
}  // Ends TCPServer class.