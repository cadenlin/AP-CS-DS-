import java.util.*;
/** The MyTreeSet class imitates the TreeSet class in Java. It includes 
 * methods such as contains, add, and remove. 
 * @author Caden Lin 
 * @version Jan 27 2021
 * @param <E> the data type of the set 
 */
public class MyTreeSet<E>
{
    private TreeNode root;
    private int size;
    private TreeDisplay display;

    /** Constructor for MyTreeSet objects 
     * 
     * 
     */
    public MyTreeSet()
    {
        root = null;
        size = 0;
        display = new TreeDisplay();

        //wait 1 millisecond when visiting a node
        display.setDelay(1);
    }

    /** Finds the size of the TreeSet
     * @return the size of the TreeSet 
     * 
     */
    public int size()
    {
        return size;
    }

    /** Determines if the TreeSet contains a certain object 
     * @param obj the object to check if it exists in the TreeSet 
     * @return true if the object is in the set, false otherwise
     * 
     */
    public boolean contains(Object obj)
    {
        return BSTUtilities.contains(root, (Comparable) obj, display);
    }

    /** Adds the object to the set if it is not in the set already 
     * @param obj the object to add to the set 
     * @return true if the object was not in the set previously and now is, 
     *          false otherwise
     * 
     */
    public boolean add(E obj)
    {
        if(contains(obj))
            return false;
        else
        {
            root = BSTUtilities.insert(root, (Comparable) obj,  display);
            size++;
            return true; 
        }
    }   

    /** Removes a given object from the set if the given object is in the set 
     * @param obj the object to remove from the set
     * @return true if the object was in the set and was removed, false otherwise 
     * 
     */
    public boolean remove(Object obj)
    {
        if(contains(obj))
        {
            root = BSTUtilities.delete(root, (Comparable) obj, display);
            size--;
            return true; 
        }
        return false; 
    }

    /** Converts the values in the TreeSet to strings 
     * @return a string with all the values of the set
     * 
     */
    public String toString()
    {
        return toString(root);
    }

    /** Converts the values in the TreeSet to strings
     * @param t the root of the tree to converts the values of 
     * @return a string with all the values in the tree
     */
    private String toString(TreeNode t)
    {
        if (t == null)
            return " ";
        return toString(t.getLeft()) + t.getValue() + toString(t.getRight());
    }

    /** Returns an iterator for the set 
     * @return an iterator for the set 
     * 
     */
    public Iterator<E> iterator()
    {
        return new MyTreeSetIterator(); 
    }

    /** The MyTreeSetIterator describes an iterator for a MyTreeSet. 
     * 
     * @author Caden Lin 
     * @version Feb 18 2021 
     * 
     */
    private class MyTreeSetIterator implements Iterator<E> 
    {
        private ArrayList<E> vals;
        private int loc; 
        /** Constructor for MyTreeSetIterator 
         * 
         */
        public MyTreeSetIterator()
        {
            vals = new ArrayList<E>(); 
            loc = 0; 
            fill(root); 
        }

        /** Fills the internal ArrayList with the elements of the set in ascending order 
         * @postcondition the ArrayList contains the elements of the set in 
         *                  ascending order 
         * @param t the node to begin the tree traversal from 
         * 
         */
        private void fill(TreeNode t)
        {
            if(t!=null)
            {
                fill(t.getLeft()) ; 
                vals.add( (E) t.getValue());
                fill(t.getRight());
            }

        }

        /** Determines if there is a next element in the set 
         * @return true if there is a next element, false otherwise 
         * 
         */
        public boolean hasNext()
        {
            return loc < size; 
        }

        /** Returns the next element in the set 
         * @postcondition loc is incremented by 1 
         * @return the next element in the set 
         * 
         * 
         */
        public E next()
        {
            if(!hasNext())
                throw new NoSuchElementException(); 
            loc++;
            return vals.get(loc-1) ; 
        }

        /** Removes the next element in the set 
         * @postcondition the next element in the set is removed 
         * 
         */
        public void remove()
        {
            BSTUtilities.delete(root, (Comparable) vals.get(loc), display);
        }
    }

}