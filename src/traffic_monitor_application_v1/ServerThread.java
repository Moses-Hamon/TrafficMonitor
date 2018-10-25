package traffic_monitor_application_v1;

import java.io.DataInputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
/**
 *
 * @author Moses
 */
public class ServerThread extends Thread
{

    private final Server server;
    private final Socket socket;

    //Constructor
    public ServerThread(Server server, Socket socket)
    {
        this.server = server;
        this.socket = socket;
        start();
    }

    // This runs in a separate thread when start() is called in the
    // constructor
    
    public void run()
    {
        try
        {
            //Create a ObjectInputStream for communication; the client
            // is using a ObjectOutputStream to write to us
            ObjectInputStream objectIn = new ObjectInputStream(socket.getInputStream());
            //Over and over forever
            while (true)
            {
                //Read all traffic entries 
                TrafficEntry entry = (TrafficEntry) objectIn.readObject();
                // if the entry exists then log it and send it on.
                if (entry != null)
                {
                    System.out.println("Received Traffic Entry from " + entry.stationLocationID);
                    server.sendObjectToAll(entry);
                }
            }
        } catch (Exception ie)
        {
        }
        finally{
            //The connection is closed for one reason or another,
            // so have the server dealing with it
            server.removeConnection( socket ); 
        }
    }
}
