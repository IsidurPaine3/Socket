/*
 * Server class, the core of the program:
 * Creates a thread by passing the socket to its constructor and then starts it.
 */
package serverthread;  // Declares the package name for this Java class.

/**
 * Javadoc comment for the Server class.
 * @author Max + IsidurPaine
 */
import java.net.*;  // Imports all classes from the java.net package, including ServerSocket and Socket.

public class Server {  // Defines the public Server class.
     public void start() {  // Public method to start the server, returns void.
         int port = 1234;  // Declares and initializes the server port number to 1234.
          try {  // Begins a try block to handle potential exceptions during socket operations.
               // Opening the server socket on the specified port.
               ServerSocket server = new ServerSocket(port);  // Creates a new ServerSocket instance bound to port 1234.
               for(;;) {  // Infinite loop (for(;;)) to continuously accept client connections.
                    System.out.println("The server is waiting...");  // Prints status message to console indicating server readiness.
                    // Waiting for a connection from a client.
                    Socket socket = server.accept();  // Accepts incoming client connection, returns a Socket for communication.
                    System.out.println("New socket " + socket);  // Logs the new client socket details to console.
                    // Creates a thread by passing the socket to its constructor.
                    ServerThread serverThread = new ServerThread(socket);  // Instantiates a new ServerThread with the client socket.
                    // Starts the thread to handle the client independently.
                    serverThread.start();  // Invokes the start() method to begin thread execution.
               }  // Ends the infinite loop block.
          } catch(Exception e) {  // Catches any Exception thrown during server operations.
               System.out.println(e.getMessage());  // Prints the error message to console for debugging.
               System.exit(1);  // Terminates the program with exit code 1 indicating failure.
          }  // Ends the catch block.
     }  // Ends the start() method.
     public static void main(String[] args) {  // Main method, entry point of the Java application.
          Server server = new Server();  // Creates a new instance of the Server class.
          server.start();  // Calls the start() method to begin server operations.
     }  // Ends the main method.
}  // Ends the Server class definition.
