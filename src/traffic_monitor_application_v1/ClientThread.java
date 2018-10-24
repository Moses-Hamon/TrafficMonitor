
package traffic_monitor_application_v1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
    private Socket socket;
    //Tracks the name of the application using the thread
    private Traffic_Monitor_Application_v1 client;
    private Monitoring_Station mClient;
    //handles the data input and output
    private DataOutputStream dataOut;
    private DataInputStream dataIn;
    //for object serialization
    private ObjectOutputStream objectOut;
    private ObjectInputStream objectIn;
    
    public ClientThread(Monitoring_Station client, Socket socket)
    {
        //initiate connection
        this.socket = socket;
        this.mClient = client;
        clientNumber++;
        open();
        start();
    }
    
    /**
     * Constructor creates new Thread using the Socket and ApplicationName Also
     * increments the client number for identification purposes.
     *
     * @param client
     * @param socket
     */
    public ClientThread(Traffic_Monitor_Application_v1 client, Socket socket)
    {
        //initiate connection
        this.socket = socket;
        this.client = client;
        clientNumber++;
        open();
        start();
    }
    
    private void open()
    {
        try
        {
            dataIn = new DataInputStream(socket.getInputStream());
            objectIn = new ObjectInputStream(dataIn);
            System.out.println("data and object streams opened for " + client.getName() + clientNumber);
            
        } catch (IOException e)
        {
            System.out.println("Error getting input streams: " + e);
            
        }
    }
    /**
     * checks for incoming messages and objects.
     */
    public void run()
    {
        
        while (true)
        {
            try
            {
                client.receiveMsgFromServer(dataIn.readUTF());
                client.receiveObjectFromServer((TrafficEntry) objectIn.readObject());
                
            } catch (IOException e)
            {
                System.out.println("Error " + e);
            } catch (ClassNotFoundException ex)
            {
                
            }
        }
    }

    /**
     * Closes the connection to the Server
     */
    void close()
    {
        try
        {
            this.socket.close();
        } catch (Exception e)
        {
            System.out.println("Could not close connection: " + e);
        }
    }
    
   
    
    
    
}
