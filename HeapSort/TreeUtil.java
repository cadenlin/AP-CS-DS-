import java.util.*;
/**
 * TreeUtil contains the following methods for manipulating binary trees:
 * leftMost, rightMost, maxDepth, countNodes, countLeaves, preOrder, 
 * postOrder, inOrder, fillList, saveTree, loadTree, copy, sameShape, and MorseCode methods 
 * @author Caden Lin 
 * @version Jan 5, 2021 
 *
 */
public class TreeUtil
{
    //used to prompt for command line input
    private static Scanner in = new Scanner(System.in);

    private static final boolean DEBUG = false;

    /**
     * determines the value of leftmost node in the tree
     * @param t a reference to the root of the tree
     * @return the value of the leftmost node in the tree 
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
     * determines the value of rightmost node in the tree
     * @param t a reference to the root of the tree
     * @return the value of the rightmost node in the tree 
     */
    public static Object rightmost(TreeNode t)
    {
        if(t==null || t.getRight() == null)
            return t.getValue();; 
        return rightmost(t.getRight()); 
    }

    /**
     * determines the maximum depth of the tree 
     * @param t a reference to the root of the tree
     * @return the maximum depth of the tree 
     */
    public static int maxDepth(TreeNode t)
    {
        if(t==null)
            return 0; 
        int left = maxDepth(t.getLeft());
        int right = maxDepth(t.getRight());
        return 1 + Math.max(left,right);

    }

    /**
     * create a random tree of the specified depth.  No attempt to balance the tree
     * is provided.
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

    /** determines the number of nodes in the tree 
     * @param t a reference to the root of the tree
     * @return the number of nodes in the tree
     */
    public static int countNodes(TreeNode t)
    {
        if(t==null)
            return 0; 
        return 1+countNodes(t.getLeft()) + countNodes(t.getRight()); 
    }

    /** determines the number of leaves (nodes with no children) 
     * in the tree 
     * @param t a reference to the root of the tree
     * @return the number of leaves in the tree
     */
    public static int countLeaves(TreeNode t)
    {
        if(t==null)
            return 0; 
        if(t.getLeft()==null && t.getRight() ==null) 
            return 1; 
        return countLeaves(t.getLeft()) + countLeaves(t.getRight()); 
    }

    /** traverses the tree with pre order depth first traversal 
     * @param t a reference to the root of the tree
     * @param display display that will show the traversal of the tree
     * 
     */
    public static void preOrder(TreeNode t, TreeDisplay display)
    {
        display.visit(t);
        if(t.getLeft()!=null)
            preOrder(t.getLeft(), display);
        if(t.getRight()!=null) 
            preOrder(t.getRight(), display);
    }

    /** traverses the tree with in order depth first traversal 
     * @param t a reference to the root of the tree
     * @param display display that will show the traversal of the tree
     */
    public static void inOrder(TreeNode t, TreeDisplay display)
    {
        if(t.getLeft()!=null)
            inOrder(t.getLeft(), display);
        display.visit(t);
        if(t.getRight()!=null) 
            inOrder(t.getRight(), display);
    }

    /** traverses the tree with post order depth first traversal 
     * @param t a reference to the root of the tree
     * @param display display that will show the traversal of the tree
     */
    public static void postOrder(TreeNode t, TreeDisplay display)
    {
        if(t.getLeft()!=null)
            postOrder(t.getLeft(), display);
        if(t.getRight()!=null) 
            postOrder(t.getRight(), display);
        display.visit(t);

    }

    /**
     * fills a list of the values of a tree, using $ to represent
     * null values 
     * @param t a reference to the root of the tree
     * @param list the list of values of the tree that will be filled 
     * 
     */
    public static void fillList(TreeNode t, List<String> list)
    {
        if(t==null)
            list.add("$");
        else
        {
            list.add( t.getValue().toString()); 
            fillList(t.getLeft(), list);
            fillList(t.getRight(),list); 
        }

    }

    /**
     * saveTree uses the FileUtil utility class to save the tree rooted at t
     * as a file with the given file name
     * @param fileName is the name of the file to create which will hold the data
     *        values in the tree
     * @param t is the root of the tree to save
     */
    public static void saveTree(String fileName, TreeNode t)
    {
        List<String> list = new ArrayList<String>(); 
        fillList(t,list); 
        Iterator<String> iterator = list.iterator();
        FileUtil.saveFile(fileName, iterator); 
    }

    /**
     * buildTree takes in an iterator which will iterate through a valid description of
     * a binary tree with String values.  Null nodes are indicated by "$" markers
     * @param it the iterator which will iterate over the tree description
     * @return a pointer to the root of the tree built by the iteration
     */
    public static TreeNode buildTree(Iterator<String> it)
    {
        String chars = it.next();
        if(chars.equals("$"))
        {
            return null;
        }
        return new TreeNode(chars, buildTree(it), buildTree(it));

    }

    /**
     * read a file description of a tree and then build the tree
     * @param fileName is a valid file name for a file that describes a binary tree
     * @return a pointer to the root of the tree
     */
    public static TreeNode loadTree(String fileName)
    {
        Iterator<String> iterator = FileUtil.loadFile(fileName); 
        return buildTree(iterator); 
    }

    /**
     * utility method that waits for a user to type text into Std Input and then press enter
     * @return the string entered by the user
     */
    private static String getUserInput()
    {
        return in.nextLine();
    }

    /**
     * plays a single round of 20 questions
     * @postcondition:  plays a round of twenty questions, asking the user questions as it
     *                 walks down the given knowledge tree, lighting up the display as it goes;
     *                 modifies the tree to include information learned.
     * @param t a pointer to the root of the game tree
     * @param display which will show the progress of the game
     */
    private static void twentyQuestionsRound(TreeNode t, TreeDisplay display)
    {   
        if(t == null)
        {
            System.out.println("I win!\n");
        }
        display.visit(t);
        System.out.println("Is it " + t.getValue()+"?");
        String str = getUserInput();
        if(str.equals("quit"))
            System.out.println("Bye");
        if(str.equals("yes"))
            twentyQuestionsRound(t.getRight(), display);
        else if(t.getLeft()==null)
        {
            System.out.println("I give up. Who/what is it?");
            str = getUserInput();
            if(str.equals("quit"))
                System.out.println("Bye");
            System.out.println("What distinguishes " + str+" from " + t.getValue());
            String original = (String) t.getValue();
            String userAnswer = getUserInput();
            if(userAnswer.equals("quit"))
                System.out.println("Bye");
            t.setValue(userAnswer);
            t.setRight(new TreeNode(str));
            t.setLeft(new TreeNode(original));
        }
        else
            twentyQuestionsRound(t.getLeft(), display);
    }

    /** 
     * plays a game of 20 questions
     * Begins by reading in a starting file and then plays multiple rounds
     * until the user enters "quit".  Then the final tree is saved
     * @postcondition the tree is updated if need be 
     */
    public static void twentyQuestions()
    {
        TreeNode t = loadTree("twentyquestions.txt");
        System.out.println("Do you want to quit");
        String str = getUserInput();
        while(str.equals("no")) 
        {
            twentyQuestionsRound(t,new TreeDisplay());
            System.out.println("Do you want to quit");
            str = getUserInput();
        }
        saveTree("twentyquestions.txt", t);
    }

    /**
     * copy a binary tree
     * @param t the root of the tree to copy
     * @return a new tree, which is a complete copy
     *         of t with all new TreeNode objects
     *         pointing to the same values as t (in the same order, shape, etc)
     */
    public static TreeNode copy(TreeNode t)
    {
        if(t == null)
            return null;
        return new TreeNode(t.getValue(), copy(t.getLeft()), copy(t.getRight()));    
    }

    /**
     * tests to see if two trees have the same shape, but not necessarily the
     * same values.  Two trees have the same shape if they have TreeNode objects
     * in the same locations relative to the root
     * @param t1 pointer to the root of the first tree
     * @param t2 pointer to the root of the second tree
     * @return true if t1 and t2 describe trees having the same shape, false otherwise
     */
    public static boolean sameShape(TreeNode t1, TreeNode t2)
    {
        if(t1==null)
        {
            if(t2!=null) return false;
            return true;
        }
        if(t2==null) return false;
        return sameShape(t1.getLeft(), t2.getLeft()) && sameShape(t1.getRight(), t2.getRight());
    }

    /**
     * Generate a tree for decoding Morse code
     * @param display the display that will show the decoding tree
     * @return the decoding tree
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
     * @param decodingTree is the partial decoding tree
     * @param letter is the letter to add
     * @param code is the Morse code for letter
     * @param display is the display that will show progress as the method walks 
     *        down the tree
     */
    private static void insertMorse(TreeNode decodingTree, String letter,
    String code, TreeDisplay display)
    {
        int length = code.length(); 
        for(int i=0 ; i < length; i++)
        {
            if(code.substring(i,i+1).equals("."))
            {
                if(decodingTree.getLeft()==null)
                    decodingTree.setLeft(new TreeNode("$"));
                decodingTree = decodingTree.getLeft();
            }
            if(code.substring(i,i+1).equals("-"))
            {
                if(decodingTree.getRight()==null)
                    decodingTree.setRight(new TreeNode("$"));
                decodingTree = decodingTree.getRight();
            }
            display.visit(decodingTree);
        }

        decodingTree.setValue(letter); 
    }

    /**
     * decodes Morse code by walking the decoding tree according to the input code
     * @param decodingTree is the Morse code decoding tree
     * @param cipherText is Morse code consisting of dots, dashes, and spaces
     * @param display is the display object that will show the decoding progress
     * @return the string represented by cipherText
     */
    public static String decodeMorse(TreeNode decodingTree, String cipherText, TreeDisplay display)
    {

        TreeNode temp = decodingTree; 
        String message = "";
        int length = cipherText.length(); 
        for(int i=0 ; i < length ; i++)
        {
            if(cipherText.substring(i,i+1).equals("."))
            {
                display.visit(temp);
                temp=temp.getLeft();

            }
            else if(cipherText.substring(i,i+1).equals("-"))
            {
                display.visit(temp);
                temp=temp.getRight();
            }
            else if(cipherText.substring(i,i+1).equals(" "))
            {
                message += temp.getValue() + " ";
                display.visit(temp);
                temp = decodingTree;
            }
        }
        message += temp.getValue();

        return message; 

    }

    /**
     * optional work
     * @param expTree 
     * @return a
     */
    public static int eval(TreeNode expTree)
    {
        throw new RuntimeException("Write ME!");
    }

    /**
     * optional work
     * @param exp 
     * @return a 
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
        if(DEBUG) System.out.println("debug: " + out);
    }
}
 