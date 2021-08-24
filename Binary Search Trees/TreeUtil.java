import java.util.*;

/**
 * TreeUtil contains the following methods for manipulating binary trees:
 * leftMost, rightMost, maxDepth, countNodes, countLeaves, preOrder, 
 * postOrder, inOrder, fillList, saveTree, loadTree, copy, sameShape, and MorseCode methods 
 * 
 * @author Bobby Wang
 * @version 12-6-19
 *
 */
public class TreeUtil
{
    //used to prompt for command line input
    private static Scanner in = new Scanner(System.in);
    private static final boolean DEBUG = false;

    /**
     * this method returns value of the leftmost treenode 
     * 
     * @param t this is the refrence to the starting root of the tree
     * @return  this method returns the value of the leftmost treenode of the given tree
     */
    public static Object leftmost(TreeNode t)
    {
        TreeNode leftMost = t;
        if(t == null)
        {
            return null;
        }
        while(leftMost.getLeft() != null)
        {
            leftMost = leftMost.getLeft();
        }
        return leftMost.getValue();
    }

    /**
     * this method returns value of the rightmost treenode 
     * 
     * @param t this is the refrence to the starting root of the tree
     * @return  this method returns the value of the rightmost treenode of the given tree
     */
    public static Object rightmost(TreeNode t)
    {
        if(t == null)
        {
            return null;
        }
        if(t.getRight() == null)
        {
            return t.getValue();    
        }
        return rightmost(t.getRight());
    }

    /**
     * this method returns the maximum depth of a given tree
     * 
     * @param t this is the refrence of the root of the tree
     * @return  this method returns the depth of the tree
     */
    public static int maxDepth(TreeNode t)
    {
        if(t == null)
        {
            return 0;
        }
        return 1 + Math.max(maxDepth(t.getRight()), maxDepth(t.getLeft()));
    }

    /**
     * create a random tree of the specified depth.  No attempt to balance the tree
     * is provided.
     * 
     * @param depth of the tree
     * @return TreeNode object that points to the generated tree
     */
    public static TreeNode createRandom(int depth)
    {
        if (Math.random() * Math.pow(2, depth) < 1)
            return null;
        return new TreeNode(((int)(Math.random() * 10)),
            createRandom(depth - 1),
            createRandom(depth - 1));
    }

    /**
     * this method counts the number of nodes in a given tree
     * 
     * @param t this is the root of the given tree
     * @return  this method returns the number of nodes in the given tree
     */
    public static int countNodes(TreeNode t)
    {
        if(t == null)
        {
            return 0;
        }
        return 1 + countNodes(t.getLeft()) + countNodes(t.getRight());
    }

    /**
     * this method counts the number of leaf nodes in a given tree
     * 
     * @param t this is the root of the given tree
     * @return  this method returns the number of leaf nodes in the given tree
     */
    public static int countLeaves(TreeNode t)
    {
        if(t == null)
        {
            return 0;
        }
        if(t.getRight() == null && t.getLeft() == null)
        {
            return 1;
        }
        return countLeaves(t.getRight()) + countLeaves(t.getLeft());
    }

    /**
     * this method visits every node in a given  tree in preOrder
     * 
     * @param t       this is the given tree who will be visited
     * @param display the display that will show the tree being traversed
     */
    public static void preOrder(TreeNode t, TreeDisplay display)
    {
        display.visit(t);
        if(t.getLeft() != null)
        {
            preOrder(t.getLeft(), display);
        }
        if(t.getRight() != null)
        {
            preOrder(t.getRight(), display);
        }
    }

    /**
     * this method visits every node in a given  tree in inOrder
     * 
     * @param t       this is the given tree who will be visited
     * @param display this is the treedisplay that will visit each node 
     */
    public static void inOrder(TreeNode t, TreeDisplay display)
    {
        if(t.getLeft() != null)
        {
            inOrder(t.getLeft(), display);
        }
        display.visit(t);
        if(t.getRight() != null)
        {
            inOrder(t.getRight(), display);
        }
    }

    /**
     * this method visits every node in a given  tree in postOrder
     * 
     * @param t       this is the given tree who will be visited
     * @param display this is the treedisplay that will visit each node 
     */
    public static void postOrder(TreeNode t, TreeDisplay display)
    {
        if(t.getLeft() != null)
        {
            postOrder(t.getLeft(), display);
        }
        if(t.getRight() != null)
        {
            postOrder(t.getRight(), display);
        }
        display.visit(t);       
    }

    /**
     * this method fills a list with the value of a given tree
     * 
     * @param t    this is the root of the tree whose contents will be put in a list
     * @param list this is the list that will be filled.
     */
    public static void fillList(TreeNode t, List<String> list)
    {
        if(t == null)
        {
            list.add("$");
        }
        else
        {
            list.add(t.getValue().toString());
            fillList(t.getLeft(), list);
            fillList(t.getRight(), list);
        }        
    }

    /**
     * saveTree uses the FileUtil utility class to save the tree rooted at t
     * as a file with the given file name
     * 
     * @param fileName is the name of the file to create which will hold the data
     *                 values in the tree
     * @param t        is the root of the tree to save
     */
    public static void saveTree(String fileName, TreeNode t)
{ 
    }

    /**
     * buildTree takes in an iterator which will iterate through a valid description of
     * a binary tree with String values.  Null nodes are indicated by "$" markers
     * 
     * @param it the iterator which will iterate over the tree description
     * @return   a pointer to the root of the tree built by the iteration
     */
    public static TreeNode buildTree(Iterator<String> it)
    {
        String character = it.next();
        if(character.equals("$"))
        {
            return null;
        }
        return new TreeNode(character, buildTree(it), buildTree(it));
    }

    /**
     * read a file description of a tree and then build the tree
     * 
     * @param fileName is a valid file name for a file that describes a binary tree
     * @return         a pointer to the root of the tree
     */
    public static TreeNode loadTree(String fileName)
    {
        return null; 
    }

    /**
     * utility method that waits for a user to type text into Std Input and then press enter
     * 
     * @return the string entered by the user
     */
    private static String getUserInput()
    {
        return in.nextLine();
    }

    /**
     * plays a single round of 20 questions
     * postcondition:  plays a round of twenty questions, asking the user questions as it
     *                 walks down the given knowledge tree, lighting up the display as it goes;
     *                 modifies the tree to include information learned.
     *                 
     * @param t a pointer to the root of the game tree
     * @param   display which will show the progress of the game
     */
    private static void twentyQuestionsRound(TreeNode t, TreeDisplay display)
    {
        throw new RuntimeException("Write ME!");
    }

    /** 
     * plays a game of 20 questions
     * Begins by reading in a starting file and then plays multiple rounds
     * until the user enters "quit".  Then the final tree is saved
     */
    public static void twentyQuestions()
    {
        throw new RuntimeException("Write ME!");
    }

    /**
     * copy a binary tree
     * 
     * @param t the root of the tree to copy
     * @return  a new tree, which is a complete copy
     *          of t with all new TreeNode objects
     *          pointing to the same values as t (in the same order, shape, etc)
     */
    public static TreeNode copy(TreeNode t)
    {
        if(t == null)
        {
            return null;
        }
        return new TreeNode(t.getValue(), copy(t.getLeft()), copy(t.getRight()));
    }

    /**
     * tests to see if two trees have the same shape, but not necessarily the
     * same values.  Two trees have the same shape if they have TreeNode objects
     * in the same locations relative to the root
     * 
     * @param t1 pointer to the root of the first tree
     * @param t2 pointer to the root of the second tree
     * @return   true if t1 and t2 describe trees having the same shape, false otherwise
     */
    public static boolean sameShape(TreeNode t1, TreeNode t2)
    {
        if(t1 == null && t2 == null)
        {
            return true;
        }
        if(t1 == null || t2 == null)
        {
            return false;
        }
        return sameShape(t1.getLeft(), t2.getLeft()) && sameShape(t1.getRight(), t2.getRight());
    }

    /**
     * Generate a tree for decoding Morse code
     * 
     * @param display the display that will show the decoding tree
     * @return        the decoding tree
     */
    public static TreeNode createDecodingTree(TreeDisplay display)
    {
        TreeNode tree = new TreeNode("Morse Tree");
        display.displayTree(tree);
        insertMorse(tree, "a", ".-", display);
        insertMorse(tree, "b", "-...", display);
        insertMorse(tree, "c", "-.-.", display);
        insertMorse(tree, "d", "-..", display);
        insertMorse(tree, "e", ".", display);
        insertMorse(tree, "f", "..-.", display);
        insertMorse(tree, "g", "--.", display);
        insertMorse(tree, "h", "....", display);
        insertMorse(tree, "i", "..", display);
        insertMorse(tree, "j", ".---", display);
        insertMorse(tree, "k", "-.-", display);
        insertMorse(tree, "l", ".-..", display);
        insertMorse(tree, "m", "--", display);
        insertMorse(tree, "n", "-.", display);
        insertMorse(tree, "o", "---", display);
        insertMorse(tree, "p", ".--.", display);
        insertMorse(tree, "q", "--.-", display);
        insertMorse(tree, "r", ".-.", display);
        insertMorse(tree, "s", "...", display);
        insertMorse(tree, "t", "-", display);
        insertMorse(tree, "u", "..-", display);
        insertMorse(tree, "v", "...-", display);
        insertMorse(tree, "w", ".--", display);
        insertMorse(tree, "x", "-..-", display);
        insertMorse(tree, "y", "-.--", display);
        insertMorse(tree, "z", "--..", display);
        return tree;
    }

    /**
     * helper method for building a Morse code decoding tree.
     * postcondition:  inserts the given letter into the decodingTree,
     *                 in the appropriate position, as determined by
     *                 the given Morse code sequence; lights up the display
     *                 as it walks down the tree
     *                 
     * @param decodingTree is the partial decoding tree
     * @param letter       is the letter to add
     * @param code         is the Morse code for letter
     * @param display      is the display that will show progress as the method walks 
     *                     down the tree
     */
    private static void insertMorse(TreeNode decodingTree, String letter,
    String code, TreeDisplay display)
    {
        TreeNode temp = decodingTree;
        while(code.length() > 0)
        {
            if(code.substring(0,1).equals("."))
            {
                if(temp.getLeft() == null)
                {
                    temp.setLeft(new TreeNode("$",null,null));
                }
                temp = temp.getLeft();
            }
            else if(code.substring(0,1).equals("-"))
            {
                if(temp.getRight() == null)
                {
                    temp.setRight(new TreeNode("$",null,null));
                }
                temp = temp.getRight();
            }
            display.visit(temp);
            code = code.substring(1);
        }
        temp.setValue(letter);
    }

    /**
     * decodes Morse code by walking the decoding tree according to the input code
     * 
     * @param decodingTree is the Morse code decoding tree
     * @param cipherText   is Morse code consisting of dots, dashes, and spaces
     * @param display      is the display object that will show the decoding progress
     * @return             the string represented by cipherText
     */
    public static String decodeMorse(TreeNode decodingTree, String cipherText, TreeDisplay display)
    {        
        String text = "";
        TreeNode temp = decodingTree;
        for(int i = 0; i < cipherText.length(); i++)
        {
            if(cipherText.substring(i,i+1).equals("."))
            {
                temp = temp.getLeft();
            }
            else if(cipherText.substring(i,i+1).equals("-"))
            {
                temp = temp.getRight();                
            }
            else if(cipherText.substring(i,i+1).equals(" "))
            {               
                text += temp.getValue() + " ";
                display.visit(temp);
                temp = decodingTree;
            }
        }
        text += temp.getValue();
        display.visit(temp);        
        return text;
    }

    /**
     * optional work 
     */
    public static int eval(TreeNode expTree)
    {
        throw new RuntimeException("Write ME!");
    }

    /**
     * optional work
     */
    public static TreeNode createExpressionTree(String exp)
    {
        throw new RuntimeException("Write ME!");
    }

    /**
     * debug printout
     * postcondition: out is printed to System.out
     * @param out the string to send to System.out
     */

    private static void debugPrint(String out)
    {
        if(DEBUG) 
        {
            System.out.println("debug: " + out);
        }
    }
}