package traffic_monitor_application_v1;

import java.awt.Color;
import java.awt.HeadlessException;
import javax.swing.JFrame;

/**
 *
 * @author Moe
 */
public class Monitoring_Station extends JFrame
{

    public void Monitoring_Station()
    {
        Monitoring_Station station = new Monitoring_Station();
        station.run();
    }

    private void run()
    {
        setSize(800, 680);
        getContentPane().setBackground(new Color(255, 254, 235)); //Sets Jframe Background Color
        setLocationRelativeTo(null); //open in the middle of the screen. 
        setResizable(false);
        setVisible(true);
    }

}
