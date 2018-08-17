
package traffic_monitor_application_v1;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Moe
 */
public class MyModel extends AbstractTableModel
    {
        ArrayList<TrafficEntry> al;

        // the headers
        String[] header;

        // constructor 
        MyModel(ArrayList<TrafficEntry> obj, String[] header)
        {
            // save the header
            this.header = header;
            // and the data
            al = obj;
        }

        // method that needs to be overload. The row count is the size of the ArrayList

        public int getRowCount()
        {
            return al.size();
        }

        // method that needs to be overload. The column count is the size of our header
        public int getColumnCount()
        {
            return header.length;
        }

        // method that needs to be overload. The object is in the arrayList at rowIndex
        public Object getValueAt(int rowIndex, int columnIndex)
        {
//            return al.get(rowIndex).getTrafficEntry(columnIndex);
            return al.get(rowIndex).ToArray()[columnIndex];
        }
        // a method to return the column name 
        public String getColumnName(int index)
        {
            return header[index];
        }

        // a method to add a new line to the table
        
        //Need to Fix-----------------------------------------
        
//        void add(String word1, String word2)
//        {
//            // make it an array[2] as this is the way it is stored in the ArrayList
//            // (not best design but we want simplicity)
//            String[] str = new String[2];
//            str[0] = word1;
//            str[1] = word2;
//            al.add(str);
//            // inform the GUI that I have change
//            fireTableDataChanged();
//        }
    }