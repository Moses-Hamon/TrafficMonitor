
package traffic_monitor_application_v1;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import org.apache.commons.lang3.StringUtils;

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
    // Stream for receiving data
    private ObjectInputStream objectIn;
    //Tracks the name of the application using the thread
    private Traffic_Monitor_Application_v1 client;
    private Monitoring_Station mClient;
    private String appName;

    /**
     * Constructor for ClientThread
     * @param client - Monitoring station
     * @param socket - Socket for connection to server
     * @param name - Name of application
     */
    public ClientThread(Monitoring_Station client, Socket socket, String name)
    {
        this.appName = name;
        this.socket = socket;
        this.mClient = client;
        clientNumber++;
        open();
    }
    
    /**
     * Constructor creates new Thread using the Socket and ApplicationName Also
     * increments the client number for identification purposes.
     *
     * @param client - 
     * @param socket
     * @param name
     */
    public ClientThread(Traffic_Monitor_Application_v1 client, Socket socket, String name)
    {
        //initiate connection
        this.appName = name;
        this.socket = socket;
        this.client = client;
        clientNumber++;
        open();
//       
    }
    /**
     *Used to open the streams (runs in constructor) 
     */
    private void open()
    {
        try
        {
            //Opens stream
            objectIn = new ObjectInputStream(socket.getInputStream());
            
            // Provides information
            String msg = "data and object streams opened for ";
            msg = StringUtils.join(msg, appName);
            System.out.println(msg);

        } catch (IOException e)
        {
            System.out.println("Error getting input streams: " + e);
        }
    }
    
    /**
     * checks for incoming messages and objects.
     */
    @Override
    public void run()
    {

        while (true)
        {
            boolean hasSent = false;
            try
            {
                TrafficEntry entry = new Gson().fromJson(objectIn.readObject().toString(), TrafficEntry.class);

            } catch (IOException | ClassNotFoundException ex)
            {
                System.out.println("Error Receiving object " + ex);
                break;
            }
            // checks if the number at the end of appName (monitor1) is the same as the client 
            // number then update the status label
            if (appName.charAt(appName.length() - 1) == clientNumber)
            {
                hasSent = true;
                mClient.checkIfObjectSent(hasSent);
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
            
        } catch (IOException e)
        {
            System.out.println("Could not close connection: " + e);
        }
    }
    

    
    
    
    
}
