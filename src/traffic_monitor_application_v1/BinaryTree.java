package traffic_monitor_application_v1;

import java.util.ArrayList;

/**
 * Binary Tree - Source:
 * http://www.newthinktank.com/2013/03/binary-tree-in-java/
 *
 * @author Moe
 */
public class BinaryTree
{

    class BTNode
    {

        TrafficEntry entry;

        BTNode leftChild;
        BTNode rightChild;

        BTNode(TrafficEntry entry)
        {

            this.entry = entry;

        }


    }
    BTNode root;

    public void addNode(TrafficEntry trafficEntry)
    {

        // Create a new Node and initialize it
        BTNode newNode = new BTNode(trafficEntry);

        // If there is no root this becomes root
        if (root == null)
        {

            root = newNode;
            System.out.println("newNode was null" + "Adding" + newNode.entry.convertToString());

        } else
        {

            // Set root as the Node we will start
            // with as we traverse the tree
            BTNode focusNode = root;

            // Future parent for our new Node
            BTNode parent;

            while (true)
            {
                // root is the top parent so we start
                // there
                parent = focusNode;

                // Check if the new node should go on
                // the left side of the parent node
                if (trafficEntry.totalNumberOfVehicles < focusNode.entry.totalNumberOfVehicles)
                {

                    // Switch focus to the left child
                    focusNode = focusNode.leftChild;

                    // If the left child has no children
                    if (focusNode == null)
                    {

                        // then place the new node on the left of it
                        parent.leftChild = newNode;
                        return; // All Done

                    }

                } else
                { // If we get here put the node on the right

                    focusNode = focusNode.rightChild;

                    // If the right child has no children
                    if (focusNode == null)
                    {

                        // then place the new node on the right of it
                        parent.rightChild = newNode;
                        return; // All Done

                    }

                }

            }
        }

    }

    // All nodes are visited in ascending order
    // Recursion is used to go to one node and
    // then go to its child nodes and so forth
    public ArrayList<TrafficEntry> inOrderTraverseTree(BTNode focusNode, ArrayList<TrafficEntry> temp)
    {
        if (focusNode != null)
        {
            // Traverse the left node
            inOrderTraverseTree(focusNode.leftChild, temp);
            System.out.println(focusNode.entry.convertToString());
            // Visit the currently focused on node
            temp.add(focusNode.entry);
            // Traverse the right node
            inOrderTraverseTree(focusNode.rightChild, temp);

        }
        
        return temp;
    }

    public ArrayList<TrafficEntry> preorderTraverseTree(BTNode focusNode, ArrayList<TrafficEntry> temp)
    {

        if (focusNode != null)
        {

            System.out.println(focusNode.entry.convertToString());
            temp.add(focusNode.entry);

            preorderTraverseTree(focusNode.leftChild, temp);
            preorderTraverseTree(focusNode.rightChild, temp);

        }
return temp;
    }

    public ArrayList<TrafficEntry> postOrderTraverseTree(BTNode focusNode, ArrayList<TrafficEntry> temp)
    {

        if (focusNode != null)
        {

            postOrderTraverseTree(focusNode.leftChild, temp);
            postOrderTraverseTree(focusNode.rightChild, temp);

            System.out.println(focusNode.entry.convertToString());
            temp.add(focusNode.entry);

        }
        return temp;

    }

    public BTNode findNode(int key)
    {

        // Start at the top of the tree
        BTNode focusNode = root;

        // While we haven't found the Node
        // keep looking
        while (focusNode.entry.totalNumberOfVehicles != key)
        {
            // If we should search to the left
            if (key < focusNode.entry.totalNumberOfVehicles)
            {
                // Shift the focus Node to the left child
                focusNode = focusNode.leftChild;
            } else
            {
                // Shift the focus Node to the right child
                focusNode = focusNode.rightChild;
            }
            // The node wasn't found
            if (focusNode == null)
            {
                return null;
            }
        }

        return focusNode;

    }

}
