package traffic_monitor_application_v1;

import java.io.DataInputStream;
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
            //Create a DataInputStream for communication; the client
            // is using a DataOutputStream to write to us
            DataInputStream dataIn = new DataInputStream(socket.getInputStream());

            //Over and over forever
            while (true)
            {
                //read the text message
                String message = dataIn.readUTF();
                
                // write to console
                System.out.println("Message :" + message);
                
                // Send message/object to all clients
                server.sendToAll( message );
            }
        } catch (Exception ie)
        {
            ie.printStackTrace();
        }
        finally{
            //The connection is closed for one reason or another,
            // so have the server dealing with it
            server.removeConnection( socket ); 
        }
    }
}
