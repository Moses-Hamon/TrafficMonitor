
package traffic_monitor_application_v1;

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
            OutputStream dataOut = clientSocket.getOutputStream();
            InputStream dataIn = clientSocket.getInputStream();
            
            dataOut.write("Connection established".getBytes());
        } catch (IOException ex)
        {
            System.out.println("Error: " + ex);
        }
    }
}
