/*
 * Per far sì che il server gestisca più connessioni simultaneamente 
 * dobbiamo affidarci a un meccanismo multithread per la gestione della 
 * ricezione e dell'invio dei messaggi, poichè non è prevedibile stabilire 
 * il numero di client da gestire e il momento in cui ogni client fa richiesta 
 * di invio di un messaggio.
 * Il programma comprende due classi: ServerThread e Server. 
 * Vediamo come svilupparle.
*/
package serverthread;

/**
 *
 * @author Max
 */
// La prima cosa da fare è di includere entrambe le 
// librerie .net per i sockets e .io per l'input/output.
import java.net.*;
import java.io.*;

// Chiamiamo la nostra prima classe ServerThread ed estendiamola alla classe Thread.
public class ServerThread extends Thread {
    // Inizializziamo cinque variabili di classe
     private ServerSocket server;
     private Socket client;
     private String str;
     private BufferedReader dalClient;
     private DataOutputStream alClient;
     // Definiamo il costruttore, che si occuperà di settare il client.
     public ServerThread(Socket socket) {
          this.client = socket;
     }
     // Ora definiamo il metodo run() che si limita ad eseguire 
     // il metodo comunica() e a gestire delle eccezzioni.
     public void run() {
          try {
               comunica();
          } catch(Exception e) {
               e.printStackTrace(System.out);
          }
     }
     // Il metodo comunica() avvia un ciclo infinito che termina 
     // quando un client invia la stringa FINE.
     public void comunica() throws Exception {
          // buffer in ingresso 
          dalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
          // buffer in uscita
          alClient = new DataOutputStream(client.getOutputStream());
          // ciclo infinito
          for(;;) {
               // leggo buffer in ingresso
               str = dalClient.readLine();
               if(str == null || str.equals("exit")) {
                    // invio al client la comunicazione di chiusura connessione
                    alClient.writeBytes("Server in chiusura\n");
                    System.out.println("Comunicazione client in chiusura\n");
                    break;
               } else {
                    // invio al client la comunicazione di aver ricevuto dei dati
                    alClient.writeBytes("Stringa "+str+" ricevuta dal server\n");
                    System.out.println("Stringa "+str+" ricevuta da "+client+'\n');
               }
          }
          // chiudo buffer in uscita
          alClient.close();
          // chiudo buffer in ingresso
          dalClient.close();
          System.out.println("Chiusura socket "+client);
          client.close();
     }
}
    
