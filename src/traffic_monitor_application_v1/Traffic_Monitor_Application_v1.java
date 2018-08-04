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

    private JButton btnSortLocation, btnSortVehicleNumber, btnSortVelocity, btnExit, btnPreOrderDisplay, btnPreOrderSave, btnInOrderDisplay, btnInOrderSave, btnPostOrderDisplay, btnPostOrderSave, btnBinaryTreeDisplay;
    private JTextArea txtLinkedList, txtBinaryTreeList;
    private JPanel pnlTrafficData, pnlInformation;
    private JLabel lblTitle, lblDataHeading, lblPreOrder, lblInOrder, lblPostOrder, lblLinkedList, lblBinaryTree;
    private JTable tblTrafficData;
    private MyModel trafficModel;
    private Color guiColor = new Color(0, 102, 0);

    public static void main(String[] args)
    {
        JFrame myFrame = new Traffic_Monitor_Application_v1();

        myFrame.setSize(800, 700);
        myFrame.getContentPane().setBackground(new Color(255, 254, 235)); //Sets Jframe Background Color
        myFrame.setLocationRelativeTo(null); //open in the middle of the screen. 
        myFrame.setResizable(true);
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
    }

    private void displayLabels(SpringLayout layout)
    {
        lblTitle = LibraryComponents.LocateAJLabel(this, layout, "Monitoring Office", 0, 15);
        lblTitle.setFont(new Font("Aharoni", Font.PLAIN, 50));
        lblDataHeading = LibraryComponents.LocateAJLabel(this, layout, "Traffic Monitoring Data", 100, 75);
        lblPreOrder = LibraryComponents.LocateAJLabel(this, layout, "Pre-Order", 50, 575);
        lblInOrder = LibraryComponents.LocateAJLabel(this, layout, "In-Order", 400, 575);
        lblPostOrder = LibraryComponents.LocateAJLabel(this, layout, "Post-Order", 800, 575);
        lblLinkedList = LibraryComponents.LocateAJLabel(this, layout, "Linked List:", 5, 320);
        lblBinaryTree = LibraryComponents.LocateAJLabel(this, layout, "Binary Tree:", 5, 445);
    }
    private void setupLabels()
    {
        setupLabel(lblTitle, 800, 60, guiColor);
        setupLabel(lblPreOrder, 150, 35, Color.GREEN);
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
        btnPreOrderDisplay = LibraryComponents.LocateAJButton(this, this, layout, "Display", 50, 610, 75, 35);
        btnPreOrderSave = LibraryComponents.LocateAJButton(this, this, layout, "Save", 125, 610, 75, 35);
        btnInOrderDisplay = LibraryComponents.LocateAJButton(this, this, layout, "Display", 400, 610, 75, 35);
        btnInOrderSave = LibraryComponents.LocateAJButton(this, this, layout, "Save", 475, 610, 75, 35);
        btnPostOrderDisplay = LibraryComponents.LocateAJButton(this, this, layout, "Display", 800, 610, 75, 35);
        btnPostOrderSave = LibraryComponents.LocateAJButton(this, this, layout, "Save", 875, 610, 75, 35);
        btnExit = LibraryComponents.LocateAJButton(this, this, layout, "Exit", 750, 300, 200, 25);
        btnSortLocation = LibraryComponents.LocateAJButton(this, this, layout, "Location", 190, 300, 90, 25);
        btnSortVehicleNumber = LibraryComponents.LocateAJButton(this, this, layout, "Vehicle #", 280, 300, 90, 25);
        btnSortVelocity = LibraryComponents.LocateAJButton(this, this, layout, "Velocity", 370, 300, 80, 25);
    }



    private void displayTextFields(SpringLayout layout)
    {
        txtLinkedList = LibraryComponents.LocateAJTextArea(this, layout, txtLinkedList, 5, 340, 5, 108);
        txtBinaryTreeList = LibraryComponents.LocateAJTextArea(this, layout, txtBinaryTreeList, 5, 465, 5, 108);
    }

    private void displayJTable(SpringLayout layout)
    {
        // Create a panel to hold all other components
        JPanel pnlTrafficData = new JPanel();
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
            "Yes", "No" , "h", "k" 
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
        tblTrafficData.setSelectionBackground(Color.red);

        // Add the table to a scrolling pane, size and locate
        JScrollPane scrollPane = tblTrafficData.createScrollPaneForTable(tblTrafficData);
        pnlTrafficData.add(scrollPane, BorderLayout.CENTER);
        pnlTrafficData.setPreferredSize(new Dimension(400, 250));
        layout.putConstraint(SpringLayout.WEST, pnlTrafficData, 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, pnlTrafficData, 75, SpringLayout.NORTH, this);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

    }

}
