package traffic_monitor_application_v1;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

/**
 *
 * @author Moe
 */
public class Monitoring_Station extends JFrame implements ActionListener
{
    
    //Socket used to connect to server
    private Socket socket;
    private final String serverName = "DESKTOP-E8H27QU";
    private final int serverPort = 5000;
    // The streams we communicate to the server; these come
    // from the socket
    DataOutputStream dataOut;
    ObjectOutputStream objectOut;
    
    //Global variable for the Thread
    private ClientThread monitorClient;
    
    static int monitorNumber = 0;
    String appName = "MonitorStation";
    JLabel lblTitle, lblStatus;
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
        connect(serverName, serverPort);
    }
//<editor-fold defaultstate="collapsed" desc="Gui">  
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
            if (i==0)
            {
                textFields[i].addMouseListener(new MouseAdapter(){
                    @Override
                    public void mouseClicked(MouseEvent e){
                        textFields[0].setText(collectCurrentTime());
                    }
                });
            }
        }
        
    }
    
    private void displayButtons(SpringLayout springLayout)
    {
        btnSubmit = LibraryComponents.LocateAJButton(this, this, springLayout, "Submit", 10, 350, 125, 40);
        btnExit = LibraryComponents.LocateAJButton(this, this, springLayout, "Exit", 175, 350, 125, 40);
    }
    //</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Action and Key Listeners">  
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == btnExit)
        {
            monitorClient.close();
            this.dispose();
        }
        if (e.getSource() == btnSubmit)
        {
//            TrafficEntry data = collectStationData();
//             System.out.println(data.convertToString());
            try
            {
//                sendObject(data);
                sendMsg("Test");
            } catch (Exception ex)
            {
                System.out.println("Error Error");
            }
        }
    }
    
    //</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Methods">  
    private TrafficEntry collectStationData()
    {
        TrafficEntry entry = new TrafficEntry();
        entry.time = textFields[0].getText();
        entry.stationLocationID = Integer.parseInt(textFields[1].getText());
        entry.numberOfLanes = Integer.parseInt(textFields[2].getText());
        entry.totalNumberOfVehicles = Integer.parseInt(textFields[3].getText());
        entry.avgNumberOfVehicles = Integer.parseInt(textFields[4].getText());
        entry.avgVelocity = Integer.parseInt(textFields[5].getText());
        
        return entry;
    }
    
    private String collectCurrentTime(){
        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }

 
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Server Connection">

  /**
     * Connects application to the server
     * @param serverName
     * @param serverPort 
     */
    private void connect(String serverName, int serverPort)
    {
        //displays info for connection
        
        try
        {
            //opens connection with server
            socket = new Socket(serverName, serverPort);
            
            //upodates connection information
            
            System.out.println("Connected on: " + socket);
            //runs open method for creating new thread for applicaiton.
            open();

            //Exception handling
        } catch (UnknownHostException uhe)
        {
            System.out.println("Host unknown: " + uhe.getMessage());
        } catch (IOException ioe)
        {
            System.out.println("Unexpected exception: " + ioe.getMessage());
        }
    }
    
    /**
     * Opens new thread for to handle incoming data and objects
     * Also opens DataOutputStream for sending data.
     */
    private void open()
    {
        try
        {
            dataOut = new DataOutputStream(socket.getOutputStream());
//            objectOut = new ObjectOutputStream(dataOut);
            
            monitorClient = new ClientThread(this, socket, appName+monitorNumber);
            monitorClient.start();
            
            dataOut.writeUTF(appName+monitorNumber + " has DataOutStream");

        } catch (Exception e)
        {
            System.out.println("Error creating thread on "+ this.getName() + e);
        }
    }
    
    /**
     * Method for closing connection to the server (server also automatically 
     * handles connections on server side)
     */
    public void close()
    {
        try
        {
            if (socket != null)
            {
                socket.close();
            }
            if (objectOut != null)
            {
                objectOut.close();
            }
            if (dataOut != null)
            {
                dataOut.close();
            }
            else{
                
            }
        } catch (IOException e)
        {
            System.out.println("Error closing connection!! :" + e);
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
//            dataOut.flush();
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
        } catch (IOException e)
        {
            System.out.println("Error sending Object: " + e);
        }
    }
    
    public void receiveMsgFromServer(String msg)
    {
        String receivedMsg = msg;
    }
    
     
    
//</editor-fold>
    

}
