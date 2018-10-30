package traffic_monitor_application_v1;

import com.google.gson.Gson;
import com.sun.tracing.dtrace.ArgsAttributes;
import java.util.Random;
import java.util.Arrays;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.*;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.LineBorder;
import javax.swing.text.DefaultCaret;
import javax.swing.JOptionPane;
import org.apache.commons.lang3.StringUtils;




/**
 *
 * @author Moses
 */
public final class Traffic_Monitor_Application_v1 extends JFrame implements ActionListener, Runnable
{
    //Connection used for new thread.
    private Socket socket;
    //Data Streams
    ObjectInputStream objectIn;
    ObjectOutputStream objectOut;
    
    
    //host and ports for server connection
    private String host;
    private final int port = 5000;
    // The streams we communicate to the server; these come
    // from the socket
    private ClientThread clientThread;
    String appName = "Traffic_Application";

    
    private JButton btnSortLocation, btnSortVehicleNumber, btnSortVelocity, btnExit, btnPreOrderDisplay,
            btnPreOrderSave, btnInOrderDisplay, btnInOrderSave, btnPostOrderDisplay, btnPostOrderSave, btnBinaryTreeDisplay, btnTestConnection;
    private JTextArea txaLinkedList, txaBinaryTreeList, txaInformation;
    private JPanel pnlTrafficData, pnlInformation;
    private JLabel lblTitle, lblDataHeading, lblPreOrder, lblInOrder, lblPostOrder, lblLinkedList, lblBinaryTree, lblSort, informationHeading;
    private JTable tblTrafficData;
    private MyModel trafficModel;
    private JOptionPane configIpAddress;
    private final Color guiColor = new Color(0, 102, 0);
    private final Color guiColorPanel = new Color(247,249,241);
    private ArrayList<TrafficEntry> trafficData;
    DoubleLinkList.DList Dlist;
    BinaryTree trafficTree;

    String columnNames[] =
    {
        "Time", "Location", "Av.Vehicle#", "Av.Velocity"
    };

    public static void main(String[] args)
    {
        JFrame myFrame = new Traffic_Monitor_Application_v1();
        
        myFrame.setSize(800, 680);
        myFrame.getContentPane().setBackground(new Color(255, 254, 235)); //Sets Jframe Background Color
        myFrame.setLocationRelativeTo(null); //open in the middle of the screen. 
        myFrame.setResizable(false);
        myFrame.setVisible(true);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public Traffic_Monitor_Application_v1()
    {
        setTitle("Traffic Monitor Application");

        SpringLayout TrafficLayout = new SpringLayout();
        setLayout(TrafficLayout);

        displayLabels(TrafficLayout);
        setupLabels();
        displayButtons(TrafficLayout);
        displayTextFields(TrafficLayout);
        displayJTable(TrafficLayout);
        displayIncomingInformation(TrafficLayout);
        displayArrayToConsole(trafficData);
        setupDoubleLinkedList(trafficData);
        displayLinkedList(Dlist);
        setupBinaryTree(trafficData);
        getIpAddressForServerAndConnect();
        loadTest(trafficData);
        
        
        
        
        
     
        
    }
//<editor-fold defaultstate="collapsed" desc="Display GUI">

    /**
     * Sets up Labels
     * @see LibraryComponents#LocateAJLabel(javax.swing.JFrame, javax.swing.SpringLayout, java.lang.String, int, int)
     * @param layout - SpringLayout
     */
    private void displayLabels(SpringLayout layout)
    {
        lblTitle = LibraryComponents.LocateAJLabel(this, layout, "Monitoring Office", 0, 10);
        lblDataHeading = LibraryComponents.LocateAJLabel(this, layout, "Traffic Monitoring Data", 125, 75);
        lblPreOrder = LibraryComponents.LocateAJLabel(this, layout, "Pre-Order", 5, 575);
        lblInOrder = LibraryComponents.LocateAJLabel(this, layout, "In-Order", 322, 575);
        lblSort = LibraryComponents.LocateAJLabel(this, layout, "Sort: ", 105, 294);
        lblPostOrder = LibraryComponents.LocateAJLabel(this, layout, "Post-Order", 640, 575);
        lblLinkedList = LibraryComponents.LocateAJLabel(this, layout, "Linked List:", 5, 320);
        lblBinaryTree = LibraryComponents.LocateAJLabel(this, layout, "Binary Tree:", 5, 445);
    }
/**
 * Sets up all labels using the Library Component Method setupLabel()
 * @see LibraryComponents#setupLabel(javax.swing.JLabel, int, int, java.awt.Color) 
 */
    private void setupLabels()
    {
       LibraryComponents.setupLabel(lblTitle, 805, 60, guiColor);
        lblTitle.setFont(new Font("Aharoni", Font.PLAIN, 50));
        LibraryComponents.setupLabel(lblDataHeading, 160, 20, guiColor);
        LibraryComponents.setupLabel(lblPreOrder, 150, 35, guiColor);
        LibraryComponents.setupLabel(lblInOrder, 150, 35, guiColor);
        LibraryComponents.setupLabel(lblPostOrder, 150, 35, guiColor);
        LibraryComponents.setupLabel(lblLinkedList, 120, 20, guiColor);
        LibraryComponents.setupLabel(lblBinaryTree, 120, 20, guiColor);
    }


/**
 * Sets up all buttons using the LibraryComponents please see
 * @see LibraryComponents#LocateAJButton(javax.swing.JFrame, java.awt.event.ActionListener, javax.swing.SpringLayout, java.lang.String, int, int, int, int) 
 * @param layout    the SpringLayout used
 */
    private void displayButtons(SpringLayout layout)
    {
        btnPreOrderDisplay = LibraryComponents.LocateAJButton(this, this, layout, "Display", 5, 610, 75, 35);
        btnPreOrderSave = LibraryComponents.LocateAJButton(this, this, layout, "Save", 80, 610, 75, 35);
        btnInOrderDisplay = LibraryComponents.LocateAJButton(this, this, layout, "Display", 322, 610, 75, 35);
        btnInOrderSave = LibraryComponents.LocateAJButton(this, this, layout, "Save", 397, 610, 75, 35);
        btnPostOrderDisplay = LibraryComponents.LocateAJButton(this, this, layout, "Display", 640, 610, 75, 35);
        btnPostOrderSave = LibraryComponents.LocateAJButton(this, this, layout, "Save", 715, 610, 75, 35);
        btnExit = LibraryComponents.LocateAJButton(this, this, layout, "Exit", 500, 290, 150, 25);
        btnSortLocation = LibraryComponents.LocateAJButton(this, this, layout, "Location", 144, 288, 90, 25);
        btnSortVehicleNumber = LibraryComponents.LocateAJButton(this, this, layout, "Vehicle #", 234, 288, 90, 25);
        btnSortVelocity = LibraryComponents.LocateAJButton(this, this, layout, "Velocity", 324, 288, 80, 25);
        btnBinaryTreeDisplay = LibraryComponents.LocateAJButton(this, this, layout, "Display", 715, 441, 75, 25);
        //btnTestConnection = LibraryComponents.LocateAJButton(this, this, layout, "Test", 500, 300, 75, 35);
    }

    /**
     * Purpose: Setup for JTextArea
     * @see LibraryComponents#LocateAJTextArea(javax.swing.JFrame, javax.swing.SpringLayout, javax.swing.JTextArea, int, int, int, int)
     * @param layout 
     */
    private void displayTextFields(SpringLayout layout)
    {
        txaLinkedList = LibraryComponents.LocateAJTextArea(this, layout, txaLinkedList, 5, 340, 5, 87);
        txaBinaryTreeList = LibraryComponents.LocateAJTextArea(this, layout, txaBinaryTreeList, 5, 465, 5, 87);
    }

    /**
     * Method for setting up and displaying JTable to Hold incoming data.
     * @param layout spring layout
     *
     */
    private void displayJTable(SpringLayout layout)
    {
        // Create a panel to hold all other components
        pnlTrafficData = new JPanel();
        pnlTrafficData.setLayout(new BorderLayout());
        add(pnlTrafficData);
        // Create column names

        //add data
        trafficData = new ArrayList<TrafficEntry>();
        trafficData.add(new TrafficEntry("6:00:00",1,3,27,9,70));
        trafficData.add(new TrafficEntry("6:00:00",2, 2, 16, 8, 80));
        trafficData.add(new TrafficEntry("7:00:00",1, 3, 30, 10, 60));
        trafficData.add(new TrafficEntry("7:00:00", 2, 2, 20, 10, 60));
        trafficData.add(new TrafficEntry("8:00:00", 1, 3, 36, 12, 40));
        trafficData.add(new TrafficEntry("8:00:00",2, 2, 22, 11, 50));
        trafficData.add(new TrafficEntry("9:00:00", 1, 3, 33, 11, 50));
        trafficData.add(new TrafficEntry("9:00:00", 2, 2, 18, 9, 65));
        trafficData.add(new TrafficEntry("10:00:00", 1, 3, 24, 8, 80));
        trafficData.add(new TrafficEntry("10:00:00", 2, 2, 14, 7, 80));
        
        
        // constructor of JTable model
        trafficModel = new MyModel(trafficData, columnNames);
        // Create a new table instance
        tblTrafficData = new JTable(trafficModel);
        // Configure some of JTable's paramters
        tblTrafficData.isForegroundSet();
        tblTrafficData.setShowHorizontalLines(false);
        tblTrafficData.setRowSelectionAllowed(true);
        tblTrafficData.setColumnSelectionAllowed(true);
        add(tblTrafficData);
        // Change the text and background colours
        tblTrafficData.setSelectionForeground(Color.white);
        tblTrafficData.setSelectionBackground(guiColor);
        // Add the table to a scrolling pane, size and locate
        JScrollPane scrollPane = tblTrafficData.createScrollPaneForTable(tblTrafficData);
        pnlTrafficData.add(scrollPane, BorderLayout.CENTER);
        pnlTrafficData.setPreferredSize(new Dimension(400, 195));
        layout.putConstraint(SpringLayout.WEST, pnlTrafficData, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, pnlTrafficData, 95, SpringLayout.NORTH, this);
    }

    /**
     *
     * Method for setting up for incoming information text area.
     *
     * @param layout Utilizes SpringLayout to position panel on form
     *
     */
    private void displayIncomingInformation(SpringLayout layout)
    {
        txaInformation = LibraryComponents.LocateAJTextArea(this, layout, txaInformation, 350, 150, 5, 5);
        txaInformation.setBackground(guiColorPanel);
        txaInformation.setEditable(false);
        //Alows the info screen to scroll automatically
        DefaultCaret caret = (DefaultCaret)txaInformation.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        JScrollPane infoPane = new JScrollPane(txaInformation);
        infoPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        informationHeading = new JLabel("New data recieved from: ");
        informationHeading.setForeground(guiColor);

        pnlInformation = new JPanel();
        pnlInformation.setLayout(new BorderLayout());
        pnlInformation.setBorder(new LineBorder(Color.LIGHT_GRAY));
        this.add(pnlInformation);
        pnlInformation.setPreferredSize(new Dimension(370, 220));
        layout.putConstraint(SpringLayout.WEST, pnlInformation, 420, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, pnlInformation, 95, SpringLayout.NORTH, this);

        pnlInformation.add(infoPane, BorderLayout.CENTER);
        pnlInformation.add(informationHeading, BorderLayout.NORTH);
        pnlInformation.add(btnExit, BorderLayout.SOUTH);
    }

    public void displayArrayToConsole(ArrayList<TrafficEntry> entry)
    {
        for (int i = 0; i < entry.size(); i++)
        {
            System.out.println(Arrays.toString(entry.get(i).toStringArray()));
        }
        System.out.println("-----------------------------------------------------");
    }
    
    public void displayLinkedList(DoubleLinkList.DList Dlist)
    {
        txaLinkedList.append(Dlist.convertToString());
    }
   
    //</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Action and Key Listeners">  
    @Override
    public void actionPerformed(ActionEvent e)
    {
        
        
        
        if (e.getSource() == btnSortLocation)
        {
            
            //sort the trafficdata
            ArrayList<TrafficEntry> sortedList = bubbleSort(trafficData);
            //set the new sorted list into the Table
            tblTrafficData.setModel(new MyModel(sortedList, columnNames));
            //set the model back to the original so that we can add new entries
            tblTrafficData.setModel(trafficModel);
            //display the data to the console
            //displayArrayToConsole(trafficData);
        }
        if (e.getSource() == btnSortVehicleNumber)
        {
            tblTrafficData.setModel(new MyModel(selectionSort(trafficData), columnNames));
            tblTrafficData.setModel(trafficModel);
            //displayArrayToConsole(trafficData);
        }
        if (e.getSource() == btnSortVelocity)
        {
                       
            tblTrafficData.setModel(new MyModel(QuickSort(trafficData, 0, trafficData.size()), columnNames));
            tblTrafficData.setModel(trafficModel);
            //displayArrayToConsole(trafficData);
            
        }
        if (e.getSource() == btnInOrderDisplay)
        {
            setupBinaryTree(trafficData);
            txaBinaryTreeList.setText("");
            txaBinaryTreeList.append("Total Number Of Vehicles: ");
            ArrayList<TrafficEntry> temp = new ArrayList<TrafficEntry>();
            trafficTree.inOrderTraverseTree(trafficTree.root, temp);
                      
            for (int i = 0; i < temp.size(); i++)
            {
                txaBinaryTreeList.append("[");
                txaBinaryTreeList.append(String.valueOf(temp.get(i).totalNumberOfVehicles));
                txaBinaryTreeList.append("]");
                if (i < temp.size()-1)
                {
                    txaBinaryTreeList.append(", ");
                }
            }
        }
        
        if (e.getSource() == btnInOrderSave)
        {
            setupBinaryTree(trafficData);
            ArrayList<TrafficEntry> temp = new ArrayList<TrafficEntry>();
            trafficTree.inOrderTraverseTree(trafficTree.root, temp);
            writeSortedArrayListToFile(temp, "InOrder");
        }
        
        if (e.getSource() == btnPreOrderDisplay)
        {
            setupBinaryTree(trafficData);
            txaBinaryTreeList.setText("");
            txaBinaryTreeList.append("Total Number Of Vehicles: ");
            ArrayList<TrafficEntry> temp = new ArrayList<>();
            trafficTree.preorderTraverseTree(trafficTree.root, temp);
            for (int i = 0; i < temp.size(); i++)
            {
                txaBinaryTreeList.append("[");
                txaBinaryTreeList.append(String.valueOf(temp.get(i).totalNumberOfVehicles));
                txaBinaryTreeList.append("]");
                if (i < temp.size()-1)
                {
                    txaBinaryTreeList.append(", ");
                }
            }
        }
        
        if (e.getSource() == btnPreOrderSave)
        {
            setupBinaryTree(trafficData);
            ArrayList<TrafficEntry> temp = new ArrayList<>();
            trafficTree.preorderTraverseTree(trafficTree.root, temp);
            writeSortedArrayListToFile(temp, "PreOrder");
        }
        
        if (e.getSource() == btnPostOrderDisplay)
        {
            setupBinaryTree(trafficData);
            txaBinaryTreeList.setText("");
            txaBinaryTreeList.append("Total Number Of Vehicles: ");
            ArrayList<TrafficEntry> temp = new ArrayList<>();
            trafficTree.preorderTraverseTree(trafficTree.root, temp);
            for (int i = 0; i < temp.size(); i++)
            {
                txaBinaryTreeList.append("[");
                txaBinaryTreeList.append(String.valueOf(temp.get(i).totalNumberOfVehicles));
                txaBinaryTreeList.append("]");
                if (i < temp.size()-1)
                {
                    txaBinaryTreeList.append(", ");
                }
            }
        }
        
        if (e.getSource() == btnPostOrderSave)
        {
            setupBinaryTree(trafficData);
            ArrayList<TrafficEntry> temp = new ArrayList<>();
            trafficTree.postOrderTraverseTree(trafficTree.root, temp);
            writeSortedArrayListToFile(temp, "PostOrder");
        }

        if (e.getSource() == btnExit)
        {
            System.exit(0);
        }

    }
    //</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Sorting Functions">
    public static ArrayList<TrafficEntry> bubbleSort(ArrayList<TrafficEntry> entry)
    {
        //for each Traffic Entry
        for (int j = 0; j < entry.size(); j++)
        {
            //compare the first entry in Arraylist to the next entry
            for (int i = j + 1; i < entry.size(); i++)
            {
                //check if the next entry is greater than the selected entry
                if (entry.get(i).stationLocationID < entry.get(j).stationLocationID)
                {
                    //swap the two values being compared if the 
                    TrafficEntry temp = entry.get(j);
                    entry.set(j, entry.get(i));
                    entry.set(i, temp);
                }
            }
            System.out.println(entry.get(j).time + " - " + entry.get(j).stationLocationID + " - " + entry.get(j).avgNumberOfVehicles + " - " + entry.get(j).avgVelocity);

        }
        return entry;
    }

    public static ArrayList<TrafficEntry> selectionSort(ArrayList<TrafficEntry> entry)
    {

        //for each traffic entry in the array
        for (int i = 0; i < entry.size(); i++)
        {
            //find the minimum entry in the arraylist of traffic entries
            int minIndex = i;
            for (int j = i + 1; j < entry.size(); j++)
            {
                if (entry.get(j).avgNumberOfVehicles < entry.get(minIndex).avgNumberOfVehicles)
                {
                    minIndex = j;
                }
                //swap the minimum entry with first entry
            }
              //Move miniumum entry into temp
                TrafficEntry temp = entry.get(minIndex);
                //place the first entry into Minimum's spot in the Arraylist
                entry.set(minIndex, entry.get(i));
                //place temp into first index
                entry.set(i, temp);
        }
        return entry;
    }

    static ArrayList<TrafficEntry> QuickSort(ArrayList<TrafficEntry> entry, int left, int right)
    {
        
        int l = left;
        int r = right - 1;
        int size = right - left;
        if (size > 1)
        {
            //sets random entry (pivot for sort)
            Random rn = new Random();
            int rand = rn.nextInt(size);
            int pivot = entry.get(rand + l).avgVelocity;
//             System.out.println("\n new pivot: " + pivot + " index: " + rand);
            while (l < r)
            {
                while (entry.get(r).avgVelocity > pivot && r > l)
                {
//                     System.out.println(entry.get(r).avgVelocity + " > " + pivot + " & " + r + " > " + l);
                    r--;
                }
                while (entry.get(l).avgVelocity < pivot && l <= r)
                {
//                    System.out.println(StringUtils.join("Index ", l, ": ", entry.get(l).avgVelocity, " < ", pivot, " & ", l, "<= ", r));
                    l++;
                }
                if (l < r)
                {
                    TrafficEntry temp = entry.get(l); 
//                      System.out.print("swapping entry " + l + ": " + entry.get(l).avgVelocity + " with entry " + "" + r + ": " + entry.get(r).avgVelocity + "\n");
//                      System.out.println(StringUtils.join("swapping entry ", l, ": ", entry.get(l).avgVelocity, " with entry ", "" , r , ": " , entry.get(r).avgVelocity , "\n"));
                    entry.set(l, entry.get(r));
                    entry.set(r, temp);
                    l++;
//                    System.out.print("New order: ");
//                    entry.forEach((entry1) ->
//                    {
//                        System.out.print(entry1.avgVelocity + ", ");
//                        
//                    });
//                    System.out.println("\n ");
                }
            }
            QuickSort(entry, left, l);
            QuickSort(entry, r, right);
        }
        return entry;
    }
    //</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Data Management">
    /**
     * Prints the sorted list into a text file and provides hashes along with values of the entry
     * @param entryList - Sorted list to be printed
     * @param title - sortname for the list
     */
    private void writeSortedArrayListToFile(ArrayList<TrafficEntry> entryList, String title){
        String hash = "hash: ";
        BufferedWriter writer = null;
        try
        {
            writer = new BufferedWriter(new FileWriter(title + ".txt"));
            writer.write(title + " values for #OfVehicles");
            writer.newLine();
            
            for (TrafficEntry entry : entryList)
            {
                writer.write(hash + entry.hashCode() + " Value:  " + entry.totalNumberOfVehicles);
                writer.newLine();
            }
            writer.write("End of List...............");
            writer.close();

        } catch (IOException ex)
        {
            System.out.println("Could not be saved: " + ex);

        }
    }
    /**
     * Code used to setup the double linked list.
     * @param entry 
     */
    public void setupDoubleLinkedList(ArrayList<TrafficEntry> entry)
    {
        Dlist = new DoubleLinkList.DList(); 

        for (int i = 0; i < entry.size(); i++)
        {
            TrafficEntry temp = entry.get(i); //grab first Traffic Entry Object
            Dlist.head.append(new DoubleLinkList.Node(temp));
            
        }
        System.out.println("-----------------------------------------------------");
        Dlist.print();
    }
    /**
     * Used to setup Binary Tree for storing Traffic Entry Data.
     * @param entryList 
     */
    public void setupBinaryTree(ArrayList<TrafficEntry> entryList){
        
        trafficTree = new BinaryTree();
        
        for (int i = 0; i < entryList.size(); i++)
        {
        trafficTree.addNode(entryList.get(i));
        
        }
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Server Connection">
    
    /**
     * Will ask the user to enter the IPAddress that the server will be on and connect
     * the application and the monitoring stations to the server.
     */
    private void getIpAddressForServerAndConnect()
    {

 
        
        host = JOptionPane.showInputDialog(null, "PLease Enter the IP Adress of Server", "Server Address Input", JOptionPane.INFORMATION_MESSAGE);
        System.out.println(host);
        
        if (host == null){
            txaInformation.append("Please restart application and enter an IP Address");
            System.out.println("User Cancelled");
        }
        else if (!host.isEmpty())
        {
            connect(host, port);
            openStations(host);
        }
    }
    
    /**
     * Opens the Monitoring Stations using the server(host)
     * @param serverName 
     */
    private void openStations(String serverName)
    {
        for (int i = 0; i < 2; i++)
        {
            Monitoring_Station monitor = new Monitoring_Station(serverName);
            monitor.show();
        }
    }
    
    
    /**
     * Connects application to the server
     * @param serverName
     * @param serverPort 
     */
    private void connect(String serverName, int serverPort)
    {
        //displays info for connection
        txaInformation.append("connecting to server....\n");
        try
        {
            //opens connection with server
            socket = new Socket(serverName, serverPort);
            
            //upodates connection information
            txaInformation.append("connected on port: " + socket.getPort() + "\n");
            System.out.println("Connected on: " + socket);
            //runs open method for creating new thread for applicaiton.
            open();

            //Exception handling
        } catch (UnknownHostException uhe)
        {
            System.out.println("Host unknown: " + uhe.getMessage());
            txaInformation.append("Could Not Connect " + uhe.getMessage());
        } catch (IOException ioe)
        {
            System.out.println("Unexpected exception: " + ioe.getMessage());
            txaInformation.append("Could Not Connect " + ioe.getMessage());
        }
    }
    
    /**
     * Opens new thread for to handle incoming data and objects
     */
    private void open()
    {
        try
        {
            //opens the object stream in order of OUT then IN
            objectOut = new ObjectOutputStream(socket.getOutputStream());
            //in streams
            objectIn = new ObjectInputStream(socket.getInputStream());

            //Starts a new thread to handle incoming requests.
            new Thread(this).start();
            txaInformation.append("New Thread Created");

        } catch (Exception e)
        {
            System.out.println("Error creating thread on " + this.getName() + e);
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
//            clientThread.interrupt();
        } catch (IOException e)
        {
            System.out.println("Error closing connection!! :" + e);
        }
    }

    public void receiveObjectFromServer(TrafficEntry entry)
    {
        txaInformation.append("\n");
        txaInformation.append("New entry received from Station: "+ entry.stationLocationID);
        trafficData.add(entry);
        trafficModel.fireTableDataChanged();
        //sets focus to the bottom of the table (auto scrolls for each entry the traffic monitor receives)
        tblTrafficData.scrollRectToVisible(tblTrafficData.getCellRect(tblTrafficData.getRowCount()-1, tblTrafficData.getColumnCount(), true));
    }

    
/**
 * Thread for handling incoming data.
 */
    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                //creates new Gson 
                Gson gson = new Gson();
                // Converts the JSon file back into a Traffic Entry File
                receiveObjectFromServer(gson.fromJson(objectIn.readObject().toString(), TrafficEntry.class));

            } catch (IOException e)
            {
                System.out.println("Error receiving msg from server: " + e);
            } catch (ClassNotFoundException ex)
            {
                Logger.getLogger(Traffic_Monitor_Application_v1.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

  
//</editor-fold>

    private void hashExample()
    {
        
        
    }
    
    private void loadTest(ArrayList<TrafficEntry> data){
        
        
        Random rand = new Random();
        for (int i = 0; i < 50; i++)
        {
        data.add(new TrafficEntry(rand.nextInt(24)+":00:00",rand.nextInt(2)+1, rand.nextInt(5-1)+1, rand.nextInt(25-1)+1, rand.nextInt(12-1)+1, rand.nextInt(250-25)+25));    
        }
        
        
        
    }
}
