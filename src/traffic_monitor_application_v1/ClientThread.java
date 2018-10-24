
package traffic_monitor_application_v1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    //Tracks the name of the application using the thread
    private Traffic_Monitor_Application_v1 client;
    private Monitoring_Station mClient;
    private String appName;
    //handles the data input and output
//    private DataOutputStream dataOut;
    private DataInputStream dataIn;
//    //for object serialization
//    private ObjectOutputStream objectOut;
    private ObjectInputStream objectIn;
    
    public ClientThread(Monitoring_Station client, Socket socket, String name)
    {
        this.appName = name;
        this.socket = socket;
        this.mClient = client;
        clientNumber++;
        open();
//        start();
    }
    
    /**
     * Constructor creates new Thread using the Socket and ApplicationName Also
     * increments the client number for identification purposes.
     *
     * @param client
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
//        start();
    }
    
    private void open()
    {
        try
        {
             dataIn = new DataInputStream(socket.getInputStream());
//            objectIn = new ObjectInputStream(dataIn);
//            dataOut = new DataOutputStream(socket.getOutputStream());
            

            String msg = "data and object streams opened for ";
            if (client != null)
            {
                //string Joiner used to join Variables.
                //Msg for the Main Application connecting
                msg = StringUtils.join(msg, appName, clientNumber);
                System.out.println(msg);
//                System.out.println("data and object streams opened for " + client.getTitle()+ clientNumber);
            } else
            {
                //msg for the Traffic Monitors
                msg = StringUtils.join(msg, appName);
                System.out.println(msg);
//                System.out.println("data and object streams opened for " + mClient.getTitle()+ clientNumber);
            }
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
                    String msg = dataIn.readUTF();
                    
                    if (msg != null)
                    {
                        System.out.println("received message: " + msg);
                        
                        
                            client.receiveMsgFromServer(msg);
                        
                        
                    }
                    
                    msg = null;
                    
                } catch (IOException ex)
                {
                    Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                    //breaks out of infinite loop
                    break;
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
