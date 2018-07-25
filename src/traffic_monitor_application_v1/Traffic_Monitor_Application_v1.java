
package traffic_monitor_application_v1;

import javax.swing.*;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        Traffic_Monitor_Application_v1 TrafficApplication = new Traffic_Monitor_Application_v1();
        TrafficApplication.run();
    }
    
    private void run()
    {
        setSize(1000,700); //sets size
        setLocationRelativeTo(null);
        setVisible(true);
        setTitle("Traffic Monitor Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        displayGUI();
    }
    public void displayGUI()
    {
        SpringLayout TrafficLayout = new SpringLayout();
        setLayout(TrafficLayout);
        displayPanels(TrafficLayout);
        displayButtons(TrafficLayout);
        
    }
    private void displayButtons(SpringLayout layout)
    {
        btnPreOrderDisplay = LibraryComponents.LocateAJButton(this, this, layout, "Pre-Order Display", 50, 600, 150, 35);
        
    }
    private void displayPanels(SpringLayout layout)
    {
        pnlTrafficData = new JPanel();
        pnlTrafficData.setLayout(layout);
        
        pnlTrafficData.setVisible(true);
        pnlTrafficData.setBackground(Color.yellow);
        this.add(pnlTrafficData);
    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        
    }
    

}
