package traffic_monitor_application_v1;

import java.util.Random;
import java.util.Arrays;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.table.AbstractTableModel;
import javax.swing.*;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;




/**
 *
 * @author Moses
 */
public class Traffic_Monitor_Application_v1 extends JFrame implements ActionListener
{
    static String applicationName = "Traffic Monitor Main";
    //Socket used to connect to server
    private Socket socket;
    private String host = "DESKTOP-E8H27QU";
    private int port = 4444;
    // The streams we communicate to the server; these come
    // from the socket
    private ClientThread clientThread;

    
    private JButton btnSortLocation, btnSortVehicleNumber, btnSortVelocity, btnExit, btnPreOrderDisplay,
            btnPreOrderSave, btnInOrderDisplay, btnInOrderSave, btnPostOrderDisplay, btnPostOrderSave, btnBinaryTreeDisplay, btnTestConnection;
    private JTextArea txaLinkedList, txaBinaryTreeList, txaInformation;
    private JPanel pnlTrafficData, pnlInformation;
    private JLabel lblTitle, lblDataHeading, lblPreOrder, lblInOrder, lblPostOrder, lblLinkedList, lblBinaryTree, lblSort, informationHeading;
    private JTable tblTrafficData;
    private MyModel trafficModel;
    private Color guiColor = new Color(0, 102, 0);
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
        connectToServer();
        
        for (int i = 0; i < 2; i++)
        {
            Monitoring_Station monitor = new Monitoring_Station();
            monitor.show();
        }
     
        
    }
//<editor-fold defaultstate="collapsed" desc="Display GUI">

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
        btnTestConnection = LibraryComponents.LocateAJButton(this, this, layout, "Test", 500, 300, 75, 35);
    }

    private void displayTextFields(SpringLayout layout)
    {
        txaLinkedList = LibraryComponents.LocateAJTextArea(this, layout, txaLinkedList, 5, 340, 5, 87);
        txaBinaryTreeList = LibraryComponents.LocateAJTextArea(this, layout, txaBinaryTreeList, 5, 465, 5, 87);
    }

    /**
     * Method for setting up and displaying JTable to Hold incoming data.
     *
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

        trafficData.add(new TrafficEntry("6:00:00 AM",1,3,27,9,70));
        trafficData.add(new TrafficEntry("6:00:00 AM",2, 2, 16, 8, 80));
        trafficData.add(new TrafficEntry("7:00:00 AM",1, 3, 30, 10, 60));
        trafficData.add(new TrafficEntry("7:00:00 AM", 2, 2, 20, 10, 60));
        trafficData.add(new TrafficEntry("8:00:00 AM", 1, 3, 36, 12, 40));
        trafficData.add(new TrafficEntry("8:00:00 AM",2, 2, 22, 11, 50));
        trafficData.add(new TrafficEntry("9:00:00 AM", 1, 3, 33, 11, 50));
        trafficData.add(new TrafficEntry("9:00:00 AM", 2, 2, 18, 9, 65));
        trafficData.add(new TrafficEntry("10:00:00 AM", 1, 3, 24, 8, 80));
        trafficData.add(new TrafficEntry("10:00:00 AM", 2, 2, 14, 7, 80));
        
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
        txaInformation.setEditable(false);

        informationHeading = new JLabel("New data recieved from: ");
        informationHeading.setForeground(guiColor);

        pnlInformation = new JPanel();
        pnlInformation.setLayout(new BorderLayout());
        pnlInformation.setBorder(new LineBorder(Color.LIGHT_GRAY));
        this.add(pnlInformation);
        pnlInformation.setPreferredSize(new Dimension(370, 220));
        layout.putConstraint(SpringLayout.WEST, pnlInformation, 420, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, pnlInformation, 95, SpringLayout.NORTH, this);

        pnlInformation.add(txaInformation, BorderLayout.CENTER);
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
        if (e.getSource() == btnTestConnection)
        {
            clientThread.sendMsg(trafficData.get(1).convertToString());
        }
        if (e.getSource() == btnSortLocation)
        {
            tblTrafficData.setModel(new MyModel(bubbleSort(trafficData), columnNames));
            displayArrayToConsole(trafficData);
        }
        if (e.getSource() == btnSortVehicleNumber)
        {
            tblTrafficData.setModel(new MyModel(selectionSort(trafficData), columnNames));
            displayArrayToConsole(trafficData);
        }
        if (e.getSource() == btnSortVelocity)
        {
            
            tblTrafficData.setModel(new MyModel(QuickSort(trafficData, 0, trafficData.size()), columnNames));
        }
        if (e.getSource() == btnInOrderDisplay)
        {
            txaBinaryTreeList.setText("");
            ArrayList temp = new ArrayList();
            trafficTree.inOrderTraverseTree(trafficTree.root, temp);
            for (int i = 0; i < temp.size(); i++)
            {
                txaBinaryTreeList.append("[");
                txaBinaryTreeList.append(temp.get(i).toString());
                txaBinaryTreeList.append("] ");
            }
        }
        if (e.getSource() == btnPreOrderDisplay)
        {
            txaBinaryTreeList.setText("");
            ArrayList temp = new ArrayList();
            trafficTree.preorderTraverseTree(trafficTree.root, temp);
            for (int i = 0; i < temp.size(); i++)
            {
                txaBinaryTreeList.append("[");
                txaBinaryTreeList.append(temp.get(i).toString());
                txaBinaryTreeList.append("] ");
            }
        }
        if (e.getSource() == btnPostOrderDisplay)
        {
            txaBinaryTreeList.setText("");
            ArrayList temp = new ArrayList();
            trafficTree.preorderTraverseTree(trafficTree.root, temp);
            for (int i = 0; i < temp.size(); i++)
            {
                txaBinaryTreeList.append("[");
                txaBinaryTreeList.append(temp.get(i).toString());
                txaBinaryTreeList.append("] ");
            }
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
            int pivot = entry.get(rn.nextInt(size) + l).avgVelocity;
//            System.out.println(pivot);
            while (l < r)
            {
                while (entry.get(r).avgVelocity > pivot && r > l)
                {
//                    System.out.println(entry.get(r).avgVelocity + " > " + pivot + " & " + r + " > " + l);
                    r--;
                }
                while (entry.get(l).avgVelocity < pivot && l <= r)
                {
//                    System.out.println(entry.get(l).avgVelocity  + " < " + pivot + " & " + l + " <= " + r);
                    l++;
                }
                if (l < r)
                {
                    TrafficEntry temp = entry.get(l);
//                    System.out.print("swapping entry " + l + " with entry " + "" + r + "\n");
                    entry.set(l, entry.get(r));
                    entry.set(r, temp);
                    l++;
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
     * connects the application to the server
     */
    private void connectToServer()
    {
        try
        {
            //initiate connection
            socket = new Socket(host, port);
            //Start a background thread for receiving messages
            clientThread = new ClientThread(socket, applicationName);
            clientThread.start();
            
        } catch (IOException ex)
        {
            System.out.println("Connection Failed" + ex);
        }
    }
//</editor-fold>
}
