/**
 * a collection of static methods for operating on binary search trees. 
 * the class can insert a node, check if a tree contains a value, and delete a node 
 * with specific value. 
 * @author Caden Lin 
 * @version January 7, 2021 
 *  
 */
public abstract class BSTUtilities
{

    /** 
     * determines if a tree contains a node with a certain value 
     * @precondition:  t is a binary search tree in ascending order
     * @param t the tree to search 
     * @param x the value that is being searched for 
     * @param display displays the searching of the tree 
     *  @return true if t contaisn the value x, false otherwise
     */
    public static boolean contains(TreeNode t, Comparable x, TreeDisplay display)
    {
        if(t ==null)
            return false; 
        display.visit(t);
        Comparable value = (Comparable) t.getValue() ; 
        if(x.compareTo(value)==0)
            return true; 

        else if (x.compareTo(value)<0)
            return contains(t.getLeft(),x,display);
        return contains(t.getRight(),x,display);

    }

    /**
     *  inserts a node with a given value into a new if there is not 
     *  already a node with that value 
     *  @precondition t is a binary search tree in ascending order 
     *  @postcondition only one new node is added to the tree 
     *  @param t the tree to add a node to
     *  @param x the value of the node that is to be added 
     *  @param display displays the traversal of the tree
     *  @return  a new tree containing x if the tree was empty
     *               otherwise, return t with a new node inserted at the appropriate location 
     *                 with the value x 
     */
    public static TreeNode insert(TreeNode t, Comparable x, TreeDisplay display)
    {
        TreeNode  tree = new TreeNode(x);
        if(t==null)
            return tree;
        display.visit(t);
        Comparable value = (Comparable) t.getValue() ; 
        if(contains(t,x,display))
            return t;
        else 
        {
            if(x.compareTo(value)<0 )
            {
                t.setLeft(insert(t.getLeft(),x,display)); 
            }
            if(x.compareTo(value)>0 )
            {
                t.setRight(insert(t.getRight(),x,display)); 
            }
        }
        return t;
    }

    /** 
     * deletes a node from a binary search tree 
     * @precondition:  t is a binary search tree in ascending order
     * @postcondition: nno new TreeNodes have been created)
     * @param t the node to be deleted 
     * @param display the display the shows the traversal of the tree 
     * @return a reference to the tree where the value at t has been deleted 
     * 
     */
    private static TreeNode deleteNode(TreeNode t, TreeDisplay display)
    {
        if(t.getLeft()==null && t.getRight()==null)
        {
            display.visit(t); 
            return null; 
        }
        if(t.getLeft()==null)
        {
            display.visit(t);
            return t.getRight();
        }
        if (t.getRight() ==null)
        {
            display.visit(t);
            return t.getLeft(); 
        }
        else
        {
            display.visit(t);
            TreeNode deletedNode = leftMostNode(t.getRight());
            Comparable value = (Comparable) deletedNode.getValue(); 
            delete(t,value,display);
            t.setValue(value); 
            return t;
        }

    }

    /**
     *  deletes a node frm a BST with a specific value 
     *  @precondition:  t is a binary search tree in ascending order
     *  @postcondition no new TreeNodes have been created 
     *  @param t the root of the tree to delete a node from 
     *  @param x the value of the node that is to be deleted 
     *  @param display displays the traversal of the tree 
     *  @return a reference to a tree where the specific node with value x 
     *  has been deleted 
     */
    public static TreeNode delete(TreeNode t, Comparable x, TreeDisplay display)
    {
        if(t==null)
            return null;
        if(!contains(t,x,display))
            return null;
        Comparable value = (Comparable) t.getValue(); 
        if(value.compareTo(x)==0)
        {
            t = deleteNode(t, display);
            return t;
        }

        TreeNode parent = findParentOfNode(t,x);
        Comparable parentValue = (Comparable) parent.getValue();
        if(parentValue.compareTo(x) < 0)
        {
            parent.setRight(deleteNode(parent.getRight(),display));
            return t; 
        }
        parent.setLeft(deleteNode(parent.getLeft(),display));
        return t;

    }
    /** finds the leftmost node of a tree
     * @param t the root of the tree that is to be searched
     * @return the leftmost node of the tree 
     * 
     */
    public static TreeNode leftMostNode(TreeNode t)
    {
        if(t==null || t.getLeft() == null)
            return t;
        return leftMostNode(t.getLeft()); 

    }

    /** Finds the parent of a node with a given value 
     * @param t the root of the tree to search 
     * @param x the value of the node whose parent is to be found 
     * @return the parent node of the node with the given value 
     * 
     */
    public static TreeNode findParentOfNode(TreeNode t, Comparable x)
    {
        if(t==null)
            return null;
        Comparable value = (Comparable) t.getValue();
        if(value.compareTo(x)==0)
            return null;
        if(t.getLeft()==null && t.getRight()==null)
            return null;
        if(t.getLeft()!=null)
        {
            Comparable childValue = (Comparable) t.getLeft().getValue();
            if(childValue.compareTo(x)==0)
                return t;
        }
        if(t.getRight()!=null)
        {
            Comparable childValue = (Comparable) t.getRight().getValue();
            if(childValue.compareTo(x)==0)
                return t;
        }
        if(value.compareTo(x)<0)
            return findParentOfNode(t.getRight(),x);
        return findParentOfNode(t.getLeft(),x);
    }
}