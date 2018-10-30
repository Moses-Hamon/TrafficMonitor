package traffic_monitor_application_v1;

import java.util.Arrays;

/**
 *
 * @author Moe
 */
public class DoubleLinkList
{

    //node class for double linked list
    public static class Node
    {
        Node prev;  // previous Node in a doubly-linked list
        Node next;  // next Node in a doubly-linked list
        TrafficEntry trafficEntry;  // data stored in this Node

        Node()
        {       // constructor for head Node 
            prev = this;
            next = this;
            trafficEntry = new TrafficEntry();
        }

        Node(TrafficEntry Entry)
        {       // constructor for head Node 
            prev = this;
            next = this;
            trafficEntry = Entry;
        }

        Node(String time, int station, int lanes, int numOfVehicles, int avgVehicles, int avgVelocity)
        {   // constructor for a Node with data
            prev = null;
            next = null;
            trafficEntry = new TrafficEntry(time, station, lanes, numOfVehicles, avgVehicles, avgVelocity);
        }

        public void append(Node newNode)
        {   //attach a new node after this node
            newNode.prev = this;
            newNode.next = next;
            if (next != null)
            {
                next.prev = newNode;
            }
            next = newNode;
            System.out.println("Node added with Traffic Data " + Arrays.toString(newNode.trafficEntry.toStringArray()) + " added ontop of  " 
            + prev.trafficEntry.convertToString());
            
        }

        public void insert(Node newNode)
        {   // attach newNode before this Node
            newNode.prev = prev;
            prev.next = newNode;
            prev = newNode;
            System.out.println("Node with Traffic Data " + Arrays.toString(newNode.trafficEntry.toStringArray()) + " Inserted before Node with Traffic Data " + Arrays.toString(trafficEntry.toStringArray()));
        }

        public void remove()
        {   // remove this node
            next.prev = prev;
            prev.next = next;
            System.out.println("Node with Traffic Data " + Arrays.toString(trafficEntry.toStringArray()) + " Removed");
        }
    }

    public static class DList
    {

        Node head;

        public DList()
        {
            head = new Node();
        }

        public DList(TrafficEntry entry)
        {
            head = new Node(entry);
        }

        public DList(String time, int station, int lanes, int numOfVehicles, int avgVehicles, int avgVelocity)
        {
            head = new Node(time, station, lanes, numOfVehicles, avgVehicles, avgVelocity);
        }

//--------------------------------------------------
        public void print()
        {                  // print content of list
            if (head.next == head)
            {             // list is empty, only header Node
                System.out.println("List empty");
                return;
            }
            System.out.println("List content = ");
            for (Node current = head.next; current != head; current = current.next)
            {
                System.out.println(" " + Arrays.toString(current.trafficEntry.toStringArray()));
            }
            System.out.println("");
        }

        public String convertToString()
        {
            String str = "";
            if (head.next == head)
            {             // list is empty, only header Node
                return "List Empty";
            }
            for (Node current = head.next; current != head && current != null; current = current.next)
            {
                str = " [" + current.trafficEntry.convertToString() + "] " + str;
            }
            return str;
        }
        
        public TrafficEntry searchLinkedList(int value)
        {
            if (head.next == head)
            { // list is empty, only header Node
                return null;
            }
            //for each node in the double linked list
            for (Node current = head.next; current != head; current = current.next)
            {
                //value matches
                if (current.trafficEntry.totalNumberOfVehicles == value)
                {
                    return current.trafficEntry;
                }
            }
            //return null if nothing is found
            return null;
        }
    }

}
