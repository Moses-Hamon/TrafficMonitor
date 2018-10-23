package traffic_monitor_application_v1;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.net.Socket;
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
    static String applicationName = "Monitoring Station";
    //Socket used to connect to server
    private Socket socket;
    private String host = "DESKTOP-E8H27QU";
    private int port = 4444;
    // The streams we communicate to the server; these come
    // from the socket
    private ClientThread monitorClient;
    
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
             TrafficEntry data = collectStationData();
             System.out.println(data.convertToString());
             monitorClient.sendObject(data);
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
     * connects the application to the server
     */
    private void connectToServer()
    {
            monitorClient = new ClientThread(host, port, applicationName, null);
            
    }
//</editor-fold>
    

}
