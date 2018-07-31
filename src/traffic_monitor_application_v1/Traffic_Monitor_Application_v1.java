
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
    private JLabel lblTitle, lblDataHeading, lblPreOrder, lblInOrder, lblPostOrder;
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
    }
    
    private void displayLabels(SpringLayout layout){
        
        lblTitle = LibraryComponents.LocateAJLabel(this, layout, "Monitoring Office", 300, 0);
        lblTitle.setFont(new Font("Serif", Font.PLAIN, 50));
        
        lblDataHeading = LibraryComponents.LocateAJLabel(this, layout, "Traffic Monitoring Data", 100, 75);
        
        lblPreOrder= LibraryComponents.LocateAJLabel(this, layout, "Pre-Order", 50, 565);
        setupLabel(lblPreOrder);
        lblInOrder = LibraryComponents.LocateAJLabel(this, layout, "In-Order", 400, 565);
        setupLabel(lblInOrder);
        lblPostOrder = LibraryComponents.LocateAJLabel(this, layout, "Post-Order", 800, 565);
        setupLabel(lblPostOrder);
        
    }
    private void displayButtons(SpringLayout layout)
    {
        btnPreOrderDisplay = LibraryComponents.LocateAJButton(this, this, layout, "Display", 50, 600, 75, 35);
        btnPreOrderSave = LibraryComponents.LocateAJButton(this, this, layout, "Save", 125, 600, 75, 35);
        
        btnInOrderDisplay = LibraryComponents.LocateAJButton(this, this, layout, "Display", 400, 600, 75, 35);
        btnInOrderSave  = LibraryComponents.LocateAJButton(this, this, layout, "Save", 475, 600, 75, 35);
        
        btnPostOrderDisplay = LibraryComponents.LocateAJButton(this, this, layout, "Display", 800, 600, 75, 35);
        btnPostOrderSave = LibraryComponents.LocateAJButton(this, this, layout, "Save", 875, 600, 75, 35);
    }
private void setupLabel(JLabel label){
    label.setBorder(new LineBorder(Color.BLACK));
    label.setPreferredSize(new Dimension(150, 35));
    label.setOpaque(true);
    label.setBackground(Color.GREEN);
    label.setHorizontalAlignment(JLabel.CENTER);
    label.setVerticalAlignment(JLabel.CENTER);
}


    @Override
    public void actionPerformed(ActionEvent e)
    {
        
    }
    

}
