/** The TreeNode class creates nodes for the binary tree data structure. 
 * Each node can hold a value, as
 * well as a reference to its left and right child nodes. 
 * @author Caden Lin 
 * @version Jan 9, 2020 
 * 
 */
public class TreeNode
{
    private Object value;
    private TreeNode left;
    private TreeNode right;

    /** Constructor for objects of the TreeNode class 
     * @param initValue the initial value to be stored in the node
     * 
     */
    public TreeNode(Object initValue)
    { 
        this(initValue, null, null);
    }

    /** Constructor for objects of the TreeNode class 
     * @param initValue the initial value to be stored in the node 
     * @param initLeft the initial reference to the left child node 
     * @param initRight the initial reference to the right child node 
     * 
     */
    public TreeNode(Object initValue, TreeNode initLeft, TreeNode initRight)
    { 
        value = initValue; 
        left = initLeft; 
        right = initRight; 
    }

    /** Determines the value stored in the node 
     * @return the value of the node 
     * 
     */
    public Object getValue() 
    { 
        return value; 
    }

    /** Determines the left child of the node 
     * @return a reference to the left child of the node 
     * 
     */
    public TreeNode getLeft() 
    { 
        return left; 
    }

    /** Determines the right child of the node 
     * @return a reference to the right child of the node 
     * 
     */
    public TreeNode getRight() 
    {
        return right; 
    }

    /** Sets a new value for the node 
     * @param theNewValue the new value of the node 
     * 
     */
    public void setValue(Object theNewValue) 
    { 
        value = theNewValue; 
    }

    /** Sets a new left child of the node 
     * @param theNewLeft the new left child of the node
     * 
     */
    public void setLeft(TreeNode theNewLeft) 
    {
        left = theNewLeft; 
    }

    /** Sets a new right child of the node 
     * @param theNewRight the new right child of the node
     * 
     */
    public void setRight(TreeNode theNewRight) 
    {
        right = theNewRight; 
    }
}