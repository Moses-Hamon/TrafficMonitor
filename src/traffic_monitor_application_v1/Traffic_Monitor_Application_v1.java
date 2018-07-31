
package traffic_monitor_application_v1;


import java.awt.BorderLayout;
import java.awt.Color;
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


/**
 *
 * @author Moses
 */
public class Traffic_Monitor_Application_v1 extends JFrame implements ActionListener
{
    
    private JButton btnSortLocation, btnSortVehicleNumber, btnSortVelocity, btnExit, btnPreOrderDisplay, btnPreOrderSave, btnInOrderDisplay,  btnInOrderSave, btnPostOrderDisplay, btnPostOrderSave, btnBinaryTreeDisplay ;
    private JTextArea txtLinkedList, txtBinaryTreeList;
    private JPanel pnlTrafficData, pnlInformation;
    private JLabel lblTitle, lblDataHeading, lblDataTime, lblDataLocation, lblDataAverageVehicleNum, lblDataAverageVelocity;
    private JTable tblTrafficData;

    public static void main(String[] args)
    {
        JFrame myFrame = new Traffic_Monitor_Application_v1();
        
        myFrame.setSize(1000,700);
        myFrame.setLocationRelativeTo(null);
        myFrame.setResizable(true);
        myFrame.setVisible(true);
    }
    
    private void Traffic_Monitor_Application_v1()
    {
        setTitle("Traffic Monitor Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        SpringLayout TrafficLayout = new SpringLayout();
        setLayout(TrafficLayout);
        
        displayLabels(TrafficLayout);
        displayButtons(TrafficLayout);
    }
    
    private void displayLabels(SpringLayout layout){
        lblTitle = LibraryComponents.LocateAJLabel(this, layout, "Monitoring Office", 50, 50);
        lblDataTime = LibraryComponents.LocateAJLabel(this, layout, "Time", 50, 50);
    }
    private void displayButtons(SpringLayout layout)
    {
        btnPreOrderDisplay = LibraryComponents.LocateAJButton(this, this, layout, "Pre-Order Display", 50, 600, 150, 35);
        
    }
   


    @Override
    public void actionPerformed(ActionEvent e)
    {
        
    }
    

}
