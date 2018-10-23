package traffic_monitor_application_v1;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Hashtable;
import sun.security.x509.IPAddressName;

/**
 * Server used for communication between Monitoring stations and Monitor
 * application
 *
 * @author Moses
 */
public class Server
{
   //InetAddress address = InetAddress.getByName("60.230.168.124");
    // The ServerSocket we'll use for accepting new connections
    private ServerSocket ss;
    // A mapping from sockets to DataOutputStreams. This will
// help us avoid having to create a DataOutputStream each time
// we want to write to a stream.
private Hashtable outputStreams = new Hashtable();
private Hashtable objectOutputStreams = new Hashtable();
    
    // Constructor and while-accept loop all in one.
    public Server(int port) throws IOException
    {
// All we have to do is listen
        listen(port);
    }



    /**
     * Listens for a connection on a port, accepts connections and creates
     * threads to deal with them
     *
     * @param port
     * @throws IOException
     */
    private void listen(int port) throws IOException
    {
        // create the SeverSocket
        ServerSocket ss = new ServerSocket(port, 5, InetAddress.getLocalHost());

        // Confirmation that socket is listening
        System.out.println("Listening on: " + ss);

        //Keep accepting connections forever
        while (true)
        {

            //grab the next incoming connection
            Socket s = ss.accept();

            // displays connection on console
            System.out.println("Connection from: " + s);

            // Create a DataOutputStream for writing data to the
            // other side
            DataOutputStream dataOut = new DataOutputStream(s.getOutputStream());
            ObjectOutputStream objectOut = new ObjectOutputStream(s.getOutputStream());
            // Save this stream so we don't need to make it again
            outputStreams.put(s, dataOut);
            objectOutputStreams.put(s, objectOut);

            // Create a new thread for this connection, and then forget
            //about it
            new ServerThread(this, s);
        }
    }
    
    // Get an enumeration of all the OutputStreams, one for each client
    // connected to us
    Enumeration getOutputStreams() {
        return outputStreams.elements();
    }
    
    Enumeration getObjectOutputStreams() {
        return objectOutputStreams.elements();
    }
    
    //Send a message to all clients (utility routine)
    void sendToAll(String message)
    {
        // We synchronize on this because another thread might be
        // calling removeConnection() and this would screw us up
        // as we tried to walk through the list
        synchronized (outputStreams)
        {
            //For each Client...
            for (Enumeration e = getOutputStreams(); e.hasMoreElements();)
            {
                //... get the output stream ...
                DataOutputStream dataOut = (DataOutputStream) e.nextElement();

                //.. and send the message ...
                try
                {
                    dataOut.writeUTF(message);
                } catch (IOException ex)
                {
                    System.out.println("Error sending: " + ex);
                }
            }
        }
    }
    
    void sendObjectToAll(TrafficEntry entry)
    {
        synchronized (objectOutputStreams)
        {
            for (Enumeration e = getObjectOutputStreams(); e.hasMoreElements();)
            {
                ObjectOutputStream objectOut = (ObjectOutputStream) e.nextElement();

                try
                {
                    objectOut.writeObject(entry);
                } catch (IOException ex)
                {
                    System.out.println("Error sending Object: " + ex);
                }
            }
        }
    }

    // Remove a socket, and it's corresponding output stream, from our
    // list. This is usually called by a connection thread that has
    // discovered that the connectin to the client is dead.
    void removeConnection(Socket s)
    {
        // Synchronize so we don't mess up sendToAll() while it walks
        // down the list of all output streamsa

        synchronized (outputStreams)
        {
            // Tell the world
            System.out.println("Removing connection to " + s);
            // Remove it from our hashtable/list
            outputStreams.remove(s);
            objectOutputStreams.remove(s);
            // Make sure it's closed
            try
            {
                s.close();
            } catch (IOException ie)
            {
                System.out.println("Error closing " + s);
                ie.printStackTrace();
            }
        }
    }
    
        
    //Main routine
    // Usage: java Server >port<
        static public void main(String args[]) throws Exception
    {

        // Get the port # from the command line
        int port = 4444;

        //Create a new server object, which will automatically begin
        // accepting conecitons
        new Server(port);
    }
    
    

}
