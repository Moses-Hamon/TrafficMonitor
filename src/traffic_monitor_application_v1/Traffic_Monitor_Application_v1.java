//This is a test
package traffic_monitor_application_v1;

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
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 *
 * @author Moses
 */
public class Traffic_Monitor_Application_v1 extends JFrame implements ActionListener
{

    private JButton btnSortLocation, btnSortVehicleNumber, btnSortVelocity, btnExit, btnPreOrderDisplay,
            btnPreOrderSave, btnInOrderDisplay, btnInOrderSave, btnPostOrderDisplay, btnPostOrderSave, btnBinaryTreeDisplay;
    private JTextArea txaLinkedList, txaBinaryTreeList, txaInformation;
    private JPanel pnlTrafficData, pnlInformation;
    private JLabel lblTitle, lblDataHeading, lblPreOrder, lblInOrder, lblPostOrder, lblLinkedList, lblBinaryTree, lblSort, informationHeading;
    private JTable tblTrafficData;
    private MyModel trafficModel;
    private Color guiColor = new Color(0, 102, 0);

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
    }

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
        setupLabel(lblTitle, 805, 60, guiColor);
        lblTitle.setFont(new Font("Aharoni", Font.PLAIN, 50));
        setupLabel(lblDataHeading, 160, 20, guiColor);
        setupLabel(lblPreOrder, 150, 35, guiColor);
        setupLabel(lblInOrder, 150, 35, guiColor);
        setupLabel(lblPostOrder, 150, 35, guiColor);
        setupLabel(lblLinkedList, 120, 20, guiColor);
        setupLabel(lblBinaryTree, 120, 20, guiColor);
    }

    private void setupLabel(JLabel label, int x, int y, Color color)
    {
        label.setBorder(new LineBorder(Color.BLACK));
        label.setPreferredSize(new Dimension(x, y));
        label.setOpaque(true);
        label.setBackground(color);
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
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
        btnSortLocation = LibraryComponents.LocateAJButton(this, this, layout, "Location", 144, 290, 90, 25);
        btnSortVehicleNumber = LibraryComponents.LocateAJButton(this, this, layout, "Vehicle #", 234, 290, 90, 25);
        btnSortVelocity = LibraryComponents.LocateAJButton(this, this, layout, "Velocity", 324, 290, 80, 25);
    }

    private void displayTextFields(SpringLayout layout)
    {
        txaLinkedList = LibraryComponents.LocateAJTextArea(this, layout, txaLinkedList, 5, 340, 5, 87);
        txaBinaryTreeList = LibraryComponents.LocateAJTextArea(this, layout, txaBinaryTreeList, 5, 465, 5, 87);
    }

    private void displayJTable(SpringLayout layout)
    {
        // Create a panel to hold all other components
        pnlTrafficData = new JPanel();
        pnlTrafficData.setLayout(new BorderLayout());
        add(pnlTrafficData);
        // Create column names
        String columnNames[] =
        {
            "Time", "Location", "Av.Vehicle#", "Av.Velocity"
        };
        // Create some data
        ArrayList<Object[]> dataValues = new ArrayList();
        dataValues.add(new Object[]
        {
            "Yes", "No", "h", "k"
        });
        // constructor of JTable model
        trafficModel = new MyModel(dataValues, columnNames);
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
        pnlTrafficData.add(scrollPane, BorderLayout.NORTH);
        pnlTrafficData.setPreferredSize(new Dimension(400, 195));
        layout.putConstraint(SpringLayout.WEST, pnlTrafficData, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, pnlTrafficData, 95, SpringLayout.NORTH, this);
    }

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

    @Override
    public void actionPerformed(ActionEvent e)
    {

    }

}
