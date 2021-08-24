
/**
 * A tester class to test the displayHeap class. 
 *
 * @author Caden Lin 
 * @version Jan 22 2020 
 */
public class Main
{
    /**
     * Constructor for objects of class Main
     */
    public Main()
    {
    }

    /**
     * A main method to test the displayHeap class 
     * @postcondition a random tree is displayed
     */
    public static void test()
    {
        Integer[] intArray = new Integer[12];
        for(int i = 1; i < 12 ; i++)
        {
            intArray[i] = (int) (Math.random()*100);
        }
        HeapDisplay display = new HeapDisplay(); 
        display.displayHeap(intArray, 11); 
    }
}
