/** The ListNode class creates nodes for a doubly-linked list, in which each node has a value, 
 * as well as a reference to the next and previous node in the list.
 * @author Caden Lin 
 * @version November 10 2020 
 */
public class ListNode
{
    private Object value; //the value that the node stores
    private ListNode next; //the reference to the next node, if there is one 

    /**Constructor for ListNode objects
     * 
     * @param v the value that is stored in the node
     * 
     */
    public ListNode(Object v)
    {
        value = v;
        next = null;
    }

    /** Gets the value of the node 
     * 
     * @return the value stored in the node
     * 
     */
    public Object getValue()
    {
        return value;
    }


    /** Gets the reference to the previous node
     * 
     * @return the reference the next node (null if it is the last node) 
     *     
     * 
     */
    public ListNode getNext()
    {
        return next;
    }

    /** Replaces the value of the node with a new value 
     * 
     * @postcondition the value of the node is replaced with the new object
     * 
     */
    public void setValue(Object v)
    {
        value = v;
    }


    /** Changes the node after this node 
     * 
     * @postcindition the reference to the next node (given by the variable next)
     *                  is replaced with a new reference 
     */
    public void setNext(ListNode n)
    {
        next = n;
    }
}