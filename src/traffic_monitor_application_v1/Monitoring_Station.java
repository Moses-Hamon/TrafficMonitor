package traffic_monitor_application_v1;

import java.awt.Color;
import java.awt.HeadlessException;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Moe
 */
public class Monitoring_Station extends JFrame
{

    JButton Btntest, test3;

    public void Monitoring_Station()
    {   //Why won't these show up in the application when instantiated.
        JFrame test = new Monitoring_Station();
        getContentPane().setBackground(Color.yellow);
        test.setBounds(10,10,300, 400);
        test.setVisible(rootPaneCheckingEnabled);
        test.add(Btntest);
        add(test3);

    }

}
