
package traffic_monitor_application_v1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Moe
 */
public class ClientThread extends Thread
{
    //Used for counting number of clients
    static int clientNumber = 0;
    //Connection used for new thread.
    private final Socket clientSocket;
    //Tracks the name of the application using the thread
    private final String applicationName;
    
    //handles the data input and output
    private DataOutputStream dataOut;
    private DataInputStream dataIn;
    
    /**
     * Constructor creates new Thread using the Socket and ApplicationName
     * Also increments the client number for identification purposes.
     * @param clientSocket
     * @param applicationName 
     */
    public ClientThread(Socket clientSocket, String applicationName)
    {
        this.clientSocket = clientSocket;
        this.applicationName = applicationName;
        clientNumber++;
    }
    
    @Override
    public void run(){
        try
        {
            dataOut = new DataOutputStream(clientSocket.getOutputStream());
            dataIn = new DataInputStream(clientSocket.getInputStream());
            
            dataOut.writeUTF("Connection established with " + applicationName + " " +clientNumber + "\n");
            
        } catch (IOException ex)
        {
            System.out.println("Error: " + ex);
        }
    }
    
    /**
     * Closes the connection to the Server
     */
    void close()
    {
        try
        {
            this.clientSocket.close();
        } catch (Exception e)
        {
            System.out.println("Could not close connection: " + e);
        }
    }
    
    void sendMsg(String msg){
        try
        {
            dataOut.writeUTF(msg);
        } catch (IOException ex)
        {
            System.out.println("Error Sending Message: " + ex);
        }
    }
}
