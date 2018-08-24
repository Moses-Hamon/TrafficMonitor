package traffic_monitor_application_v1;

import java.awt.Color;
import java.awt.HeadlessException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

/**
 *
 * @author Moe
 */
public class Monitoring_Station extends JFrame
{

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

    }

    void displayLabels(SpringLayout layout)
    {
        int yPos = 0;
        for (int i = 0; i < 6; i++)
        {
            yPos = yPos + 25;
            labels[i] = LibraryComponents.LocateAJLabel(this, layout, lblHeadings[i], 10, yPos);
        }
        lblTitle = LibraryComponents.LocateAJLabel(this, layout, "Monitoring Stn " + monitorNumber, 10, 10);
    }

}
