
package traffic_monitor_application_v1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

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
    private String applicationName;
    
    //handles the data input and output
    private DataOutputStream dataOut;
    private DataInputStream dataIn;
    //for object serialization
    private ObjectOutputStream objectOut;
    private ObjectInputStream objectIn;
    
    /**
     * Constructor creates new Thread using the Socket and ApplicationName
     * Also increments the client number for identification purposes.
     * @param host
     * @param port
     * @param applicationName 
     * @param app 
     */
    public ClientThread(String host, int port, String applicationName, Traffic_Monitor_Application_v1 app)
    {
        try
        {
            //initiate connection
            socket = new Socket(host, port);
            this.applicationName = applicationName;
            clientNumber++;
            start();
        } 
        catch (IOException ex)
        {
            System.out.println("Connection Failed: " + ex);
        }
    }
    
    @Override
    public void run(){
        try
        {
            dataOut = new DataOutputStream(socket.getOutputStream());
            dataIn = new DataInputStream(socket.getInputStream());
            objectOut = new ObjectOutputStream(dataOut);
            objectIn = new ObjectInputStream(dataIn);
            
            dataOut.writeUTF("Connection established with " + applicationName + " " +clientNumber + "\n");
            
//            while (true)
//            {
//                try
//                {
//                    TrafficEntry entry = (TrafficEntry) objectIn.readObject();
//                    System.out.println(applicationName + "Has received: \n" + entry.toString());
//                    
//                } catch (Exception e)
//                {
//                    System.out.println(applicationName + " Error: " + e);
//                }
//                finally{
//            //The connection is closed for one reason or another,
//            // so have the server dealing with it
//            socket.close();
//                }
//            }
            
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
            this.socket.close();
        } catch (Exception e)
        {
            System.out.println("Could not close connection: " + e);
        }
    }
    
    /**
     * Sends a message to the server. Used for displaying connection information and other messages
     * @param msg 
     */
    void sendMsg(String msg)
    {
        try
        {
            dataOut.writeUTF(msg);
        } catch (IOException ex)
        {
            System.out.println("Error Sending Message: " + ex);
        }
    }

    /**
     * Receives a Traffic Entry and sends it on the stream
     * @param entry 
     */
    public void sendObject(TrafficEntry entry)
    {
        try
        {
            objectOut.writeObject(entry);
            sendMsg("Item has been sent");
        } catch (Exception e)
        {
            System.out.println("Error sending Object: " + e);
        }
    }
    
    
    
}
