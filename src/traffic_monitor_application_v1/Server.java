package traffic_monitor_application_v1;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Server used for communication between Monitoring stations and Monitor
 * application
 *
 * @author Moses
 */
public class Server
{
    // Constructor and while-accept loop all in one.

    public Server(int port) throws IOException
    {
// All we have to do is listen
        listen(port);
    }

    //Main routine
    // Usage: java Server >port<
    static public void main(String args[]) throws Exception
    {

        // Get the port # from the command line
        int port = Integer.parseInt(args[0]);
        
        //Create a new server object, which will automatically begin
        // accepting conecitons
        new Server( port );
    }
    /**
     * Listens for a connection on a port, accepts connections and creates threads
     * to deal with them
     * @param port
     * @throws IOException 
     */
  private void listen( int port ) throws IOException
  {
        // create the SeverSocket
        ServerSocket ss = new ServerSocket( port );
        
        // Confirmation that socket is listening
        System.out.println("Listening on: " + ss);
        
        //Keep accepting connections forever
        while (true){
            
            //grab the next incoming connection
            Socket s = ss.accept();
            
            // displays connection on console
            System.out.println("Connection from: " + s);
            
            // Create a DataOutputStream for writing data to the
            // other side
            DataOutputStream dout = new DataOutputStream(s.getOutputStream() );
            
            // Save this stream so we don't need to make it again
            outputStreams.put (s, dout);
            
            // Create a new thread for this connection, and then forget
            //about it
            new ServerThread( this, s);
        }
  }

}
