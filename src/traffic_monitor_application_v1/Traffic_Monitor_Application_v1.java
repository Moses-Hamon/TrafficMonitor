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
    
    private JButton btnSortLocation, btnSortVehicleNumber, btnSortVelocity, btnExit, btnPreOrderDisplay, btnPreOrderSave, btnInOrderDisplay,  btnInOrderSave, btnPostOrderDisplay, btnPostOrderSave, btnBinaryTreeDisplay ;
    private JTextArea txtLinkedList, txtBinaryTreeList;
    private JPanel pnlTrafficData, pnlInformation;
    private JLabel lblTitle, lblDataHeading, lblPreOrder, lblInOrder, lblPostOrder, lblLinkedList, lblBinaryTree;
    private JTable tblTrafficData;

    public static void main(String[] args)
    {
        JFrame myFrame = new Traffic_Monitor_Application_v1();
        
        myFrame.setSize(1000,700);
        myFrame.setLocationRelativeTo(null);
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
        displayButtons(TrafficLayout);
        displayTextFields(TrafficLayout);
        displayJTable(TrafficLayout);
    }
    
    private void displayLabels(SpringLayout layout){
        
        lblTitle = LibraryComponents.LocateAJLabel(this, layout, "Monitoring Office", 300, 0);
        lblTitle.setFont(new Font("Serif", Font.PLAIN, 50));
        
        lblDataHeading = LibraryComponents.LocateAJLabel(this, layout, "Traffic Monitoring Data", 100, 75);
        
        lblPreOrder= LibraryComponents.LocateAJLabel(this, layout, "Pre-Order", 50, 575);
        setupLabel(lblPreOrder);
        
        lblInOrder = LibraryComponents.LocateAJLabel(this, layout, "In-Order", 400, 575);
        setupLabel(lblInOrder);
        
        lblPostOrder = LibraryComponents.LocateAJLabel(this, layout, "Post-Order", 800, 575);
        setupLabel(lblPostOrder);
        
        lblLinkedList = LibraryComponents.LocateAJLabel(this, layout, "Linked List:", 5, 320);
        lblBinaryTree = LibraryComponents.LocateAJLabel(this, layout, "Binary Tree:", 5, 445);
        
    }
    private void displayButtons(SpringLayout layout)
    {
        btnPreOrderDisplay = LibraryComponents.LocateAJButton(this, this, layout, "Display", 50, 610, 75, 35);
        btnPreOrderSave = LibraryComponents.LocateAJButton(this, this, layout, "Save", 125, 610, 75, 35);
        
        btnInOrderDisplay = LibraryComponents.LocateAJButton(this, this, layout, "Display", 400, 610, 75, 35);
        btnInOrderSave  = LibraryComponents.LocateAJButton(this, this, layout, "Save", 475, 610, 75, 35);
        
        btnPostOrderDisplay = LibraryComponents.LocateAJButton(this, this, layout, "Display", 800, 610, 75, 35);
        btnPostOrderSave = LibraryComponents.LocateAJButton(this, this, layout, "Save", 875, 610, 75, 35);
        
        btnExit = LibraryComponents.LocateAJButton(this, this, layout, "Exit", 750, 300, 200, 25);
        btnSortLocation = LibraryComponents.LocateAJButton(this, this, layout, "Location", 190, 300, 90, 25);
        btnSortVehicleNumber = LibraryComponents.LocateAJButton(this, this, layout, "Vehicle #", 280, 300, 90, 25);
        btnSortVelocity = LibraryComponents.LocateAJButton(this, this, layout, "Velocity", 370, 300, 80, 25);
    }
private void setupLabel(JLabel label){
    label.setBorder(new LineBorder(Color.BLACK));
    label.setPreferredSize(new Dimension(150, 35));
    label.setOpaque(true);
    label.setBackground(Color.GREEN);
    label.setHorizontalAlignment(JLabel.CENTER);
    label.setVerticalAlignment(JLabel.CENTER);
}
private void displayTextFields(SpringLayout layout){
    
    txtLinkedList = LibraryComponents.LocateAJTextArea(this, layout, txtLinkedList, 5, 340, 5, 108);
    
   txtBinaryTreeList = LibraryComponents.LocateAJTextArea(this, layout, txtBinaryTreeList, 5, 465, 5, 108);
}
private void displayJTable(SpringLayout layout){
    tblTrafficData = new JTable();
    add(tblTrafficData);
    JScrollPane tablePane = new JScrollPane(tblTrafficData);
    layout.putConstraint(SpringLayout.WEST, tablePane, 50, SpringLayout.WEST, this);
    layout.putConstraint(SpringLayout.NORTH, tablePane, 50, SpringLayout.NORTH, this);
    this.add(tablePane);
}


    @Override
    public void actionPerformed(ActionEvent e)
    {
        
    }
    

}
