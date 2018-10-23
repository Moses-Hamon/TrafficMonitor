package traffic_monitor_application_v1;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import static traffic_monitor_application_v1.Traffic_Monitor_Application_v1.applicationName;

/**
 *
 * @author Moe
 */
public class Monitoring_Station extends JFrame implements ActionListener
{
    static String applicationName = "Monitoring Station";
    //Socket used to connect to server
    private Socket socket;
    private String host = "DESKTOP-E8H27QU";
    private int port = 4444;
    // The streams we communicate to the server; these come
    // from the socket
    private DataOutputStream dataOut;
    private DataInputStream dataIn;
    
    static int monitorNumber = 0;
    JLabel lblTitle;
    JButton btnSubmit, btnExit;
    String[] lblHeadings = new String[]
    {
        "Time", "Location", "Number Of Lanes", "Total Number of Vehicles", "Avg Number of Vehicles", "Avg Velocity"
    };
    JLabel[] labels = new JLabel[6];
    JTextField[] textFields = new JTextField[6];
//    JLabel lblTime, lblLocation, lblNumberOfLanes, lblTotalNumOfVehicles, lblAvgNumOfVehicles, lblAvgVelocity;
//    JTextField txtTime, txtLocation, txtNumOfLabels, txtTotalNumOfVehicles, txtAvgNumOfVehicles, txtAvgVelocity;

    public Monitoring_Station()
    {
        monitorNumber++;
        this.setSize(450, 600);
        getContentPane().setBackground(new Color(255, 254, 235));
        SpringLayout springLayout = new SpringLayout();
        setLayout(springLayout);
        displayLabels(springLayout);
        displayTextFields(springLayout);
        displayButtons(springLayout);
        connectToServer();
    }

    void displayLabels(SpringLayout layout)
    {
        int yPos = 0;
        for (int i = 0; i < 6; i++)
        {
            yPos = yPos + 35;
            labels[i] = LibraryComponents.LocateAJLabel(this, layout, lblHeadings[i], 10, yPos);
        }
        lblTitle = LibraryComponents.LocateAJLabel(this, layout, "Monitoring Stn " + monitorNumber, 10, 10);
    }

    private void displayTextFields(SpringLayout springLayout)
    {
        int yPos = 0;
        for (int i = 0; i < textFields.length; i++)
        {
            yPos = yPos + 35;
            textFields[i] = LibraryComponents.LocateAJTextField(this, null, springLayout, 8, 200, yPos);
        }
    }
    
    private void displayButtons(SpringLayout springLayout)
    {
        btnSubmit = LibraryComponents.LocateAJButton(this, this, springLayout, "Submit", 10, 350, 125, 40);
        btnExit = LibraryComponents.LocateAJButton(this, this, springLayout, "Exit", 175, 350, 125, 40);
    }
//<editor-fold defaultstate="collapsed" desc="Action and Key Listeners">  
    @Override
    public void actionPerformed(ActionEvent e)
    {
         if (e.getSource() == btnExit)
        {
            this.dispose();
        }

    }
    //</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Server Connection">
    /**
     * connects the application to the server
     */
    private void connectToServer()
    {
        try
        {
            //initiate connection
            socket = new Socket(host, port);
            // Display if conneciton is successful
            System.out.println("Connected to "+socket);
            
            //Lets's grab the streams and create DataInput/Output streams from them
             dataIn = new DataInputStream(socket.getInputStream());
             dataOut = new DataOutputStream(socket.getOutputStream());
            
            //Start a background thread for receiving messages
            ClientThread clientThread = new ClientThread(socket, applicationName);
            clientThread.start();
            
        } catch (IOException ex)
        {
            System.out.println("Connection Failed" + ex);
        }
    }
//</editor-fold>
    

}
