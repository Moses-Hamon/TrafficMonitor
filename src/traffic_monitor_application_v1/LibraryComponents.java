/**
 * --------------------------------------------------------
 * Class: LibraryComponents
 *
 * @author Mark O'Reilly
 * Developed: 2017
 *
 * Purpose: To contain a library of utility methods that can be accessed from other Java applications
 *
 * Currently: 
 *  - LocateAJLabel - for positioning a JLabel using the layout manager: SpringLayout 
 *  - LocateAJTextField - for positioning a JTextField using SpringLayout 
 *  - LocateAJButton - for positioning a JButton using SpringLayout 
 *  - LocateAJTextArea - for positioning a JTextArea using SpringLayout 
 *
 * ----------------------------------------------------------
 */


package traffic_monitor_application_v1;


import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import javax.swing.border.LineBorder;


public class LibraryComponents
{
    
    /** --------------------------------------------------------
    * Purpose: Locate a single JLabel within the JFrame.
    * @param   Layout_manager, JLabel_Caption, Width, X_position, Y_Position
    * @returns The JLabel.
    * ----------------------------------------------------------
    */
    public static JLabel LocateAJLabel(JFrame myJFrame, SpringLayout myJLabelLayout, String JLabelCaption, int x, int y)
    {
	// Instantiate the JLabel
        JLabel myJLabel = new JLabel(JLabelCaption);
	// Add the JLabel to the screen
        myJFrame.add(myJLabel); 
	// Set the position of the JLabel (From left hand side of the JFrame (West), and from top of JFrame (North))
        myJLabelLayout.putConstraint(SpringLayout.WEST, myJLabel, x, SpringLayout.WEST, myJFrame);
        myJLabelLayout.putConstraint(SpringLayout.NORTH, myJLabel, y, SpringLayout.NORTH, myJFrame);
        myJLabel.setFont(new Font("Arial", Font.PLAIN, 16));
	// Return the label to the calling method
        return myJLabel;
    }
   
        
    /**
     * @param myJFrame - JFrame to implement into
     * @param myKeyLstnr - Key Listener
     * @param myJTextFieldLayout - SpringLayout
     * @param width - Width
     * @param x - XPos
     * @param y - YPos
     * @return JTextField
     */
    public static JTextField LocateAJTextField(JFrame myJFrame, KeyListener myKeyLstnr, SpringLayout myJTextFieldLayout, int width, int x, int y)
    {
        JTextField myJTextField = new JTextField(width);
        myJFrame.add(myJTextField);  
        myJTextField.addKeyListener(myKeyLstnr);
       
        myJTextFieldLayout.putConstraint(SpringLayout.WEST, myJTextField, x, SpringLayout.WEST, myJFrame);
        myJTextFieldLayout.putConstraint(SpringLayout.NORTH, myJTextField, y, SpringLayout.NORTH, myJFrame);
        return myJTextField;
    }

        
    /** --------------------------------------------------------
    * Purpose: Locate a single JButton within the JFrame.
    * @param   Layout_manager, JButton_name, JButton_caption, X_position, Y_Position, Width, Height
    * @returns The JButton.
    * ----------------------------------------------------------
    */
    public static JButton LocateAJButton(JFrame myJFrame, ActionListener myActnLstnr, SpringLayout myJButtonLayout, String  JButtonCaption, int x, int y, int w, int h)
    {    
        JButton myJButton = new JButton(JButtonCaption);
        myJFrame.add(myJButton);
        myJButton.addActionListener(myActnLstnr);
        myJButtonLayout.putConstraint(SpringLayout.WEST, myJButton, x, SpringLayout.WEST, myJFrame);
        myJButtonLayout.putConstraint(SpringLayout.NORTH, myJButton, y, SpringLayout.NORTH, myJFrame);
        myJButton.setPreferredSize(new Dimension(w,h));
        return myJButton;
    }

    
    /** --------------------------------------------------------
    * Purpose: Locate a single JTextArea within the JFrame.
    * @param   Layout_manager, JTextArea_name, X_position, Y_Position, Width, Height
    * @returns The JTextArea.
    * ----------------------------------------------------------
    */
    public static JTextArea LocateAJTextArea(JFrame myJFrame, SpringLayout myLayout, JTextArea myJTextArea, int x, int y, int w, int h)
    {    
        
        myJTextArea = new JTextArea(w,h);
        myJTextArea.setWrapStyleWord(true);
        myJTextArea.setLineWrap(true);
        myJTextArea.setEditable(false);
        myJTextArea.setFont(new Font("Consolas", Font.PLAIN, 16));
        JScrollPane myScrollPane = new JScrollPane(myJTextArea);
        myJFrame.add(myScrollPane);
        myLayout.putConstraint(SpringLayout.WEST, myScrollPane, x, SpringLayout.WEST, myJFrame);
        myLayout.putConstraint(SpringLayout.NORTH, myScrollPane, y, SpringLayout.NORTH, myJFrame);
        
        return myJTextArea;
    }
//    public static JPanel LocateAJPanel(JFrame myJFrame, SpringLayout myLayout, JPanel myJPanel){
//            myJPanel = new JPanel();
//            
//            myJFrame.add(myJPanel);
//            return myJPanel;
//        
//    }


    public static void setupLabel(JLabel label, int x, int y, Color color)
    {
        label.setBorder(new LineBorder(Color.BLACK));
        label.setPreferredSize(new Dimension(x, y));
        label.setOpaque(true);
        label.setBackground(color);
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
    }
}
