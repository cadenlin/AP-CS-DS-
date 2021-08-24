/**
 *  The Sorter class includes the algorithsm for selectionSort,
 *  mergeSort, insertionSort, and quickSort.
 *  @author Caden Lin 
 *  @version March 9, 2021 
 *  @
 */
public class Sorter
{
    private SortDisplay display;

    /**
     * main method instantiates a sorter instance
     * Usage: called directly by the IDE or when Java is launched 
     * ------------------------------------------
     * Creates a Sorter object, but calls no methods from Sorter 
     * because the GUI SortDisplay calls sort methods in Sorter
     * 
     * @param args an array of arguments for legacy command line
     *              the values are not used
     */
    public static void main(String[] args)
    {
        Sorter sorter = new Sorter();
    }

    /**
     * Constructor: Sorter()
     * Useage:  Sorter aSorter = new Sorter()
     * ________________________________________
     * constructor for Sorter objects.  Creates a new display, which controls
     * all of the sorting by means of call-backs to this class.
     */
    public Sorter()
    {
        display = new SortDisplay(this);
    }

    /**
     * Method: indexOfMin()
     * Usage: Finds the minimum element in an array starting from a given index
     * through a linear search of the array. 
     * @param a the array to search
     * @param startIndex the index to start the search from 
     * @return the minimum element in the array after the given index 
     */
    public int indexOfMin(Comparable[] a, int startIndex)
    {
        if(a.length == 0)
            return -1; 
        if(a.length == 1)
            return 0;
        int minIndex = startIndex ; 
        for(int i = minIndex ; i<a.length ; i++)
        {
            if(a[i].compareTo(a[minIndex]) < 0)
            {
                minIndex = i; 
            }
        }
        return minIndex;
    }

    /**
     * Method: selectionSort()
     * Usage: Sorts an array using the selection sort method. 
     * In selection sort, the array is divided into a sorted and an 
     * unsorted portion. With each iteration, the minimum element 
     * in the unsorted array is found and moved to the end of the sorted subarray,
     * increasing the size of the sorted portion. The program continues
     * until the entire array is sorted.
     * @postcondition the array is sorted in ascending order 
     * @param a the array to search
     */
    public void selectionSort(Comparable[] a)
    {
        for(int j = 0 ; j < a.length ; j++)
        {
            int minIndex = j;
            minIndex = indexOfMin(a, j) ; 
            Comparable min = a[minIndex];
            a[minIndex] = a[j];
            display.update(); 
            a[j] = min; 
            display.update(); 
        }
    }

    /**
     * Method: insert()
     * Usage: Moves a given element to its proper, sorted position 
     * in the array. The element at the specified index is compared to 
     * each element in the sorted array (all the elements to the left),
     * until its appropriate location is found, with the elements in the 
     * sorted array shifting to the right with each comparison.
     * @precondition all elements to the left of the specified index 
     * are sorted in ascending order
     * @postcondition the specified element has been sorted properly 
     * @param a the array to search
     * @param nextIndex the index of the element to sort 
     */
    public void insert(Comparable[] a, int nextIndex)
    {
        Comparable val = a[nextIndex];
        int i = nextIndex - 1; 
        while( i >= 0 && a[i].compareTo(val) > 0)
        {
            a[i+1] = a[i];
            i-- ;
        }
        a[i+1]=val; 
    }

    /**
     * Method: insertionSort()
     * Usage: Sorts the array using insertion sort. 
     * The array is divided into a sorted and an unsorted portion. 
     * With each iteration, the first element in the unsorted portion is compared 
     * to the elements in the sorted portion until its correct location is found. 
     * With each comparison, the elements of the sorted portion slide to the 
     * right, creating a hole for the new element to enter at the end. 
     * @postcondition the elements are sorted in ascending order 
     * @param a the array to search
     * 
     */
    public void insertionSort(Comparable[] a)
    {
        for(int i = 1 ; i < a.length ; i++)
        {
            insert(a,i); 
            display.update();
        }

    }

    /**
     * Method: mergesort()
     * Usage: Sorts the array using mergesort.
     * Mergesort is a divide and conquer algorithm that 
     * continuously divides the array in half until the arrays 
     * are of size 1 (and therefore sorted). Then, the arrays of 
     * size one are merged together in the appropriate order, and 
     * then these arrays of size 
     * two are merged together with other arrays of size two, 
     * such that they are also sorted. This process continues 
     * until all the subarrays merge together. 
     * @postcondition the elements are sorted in ascending order 
     * @param a the array to search
     * 
     */
    public void mergesort(Comparable[] a)
    {
        mergesortHelp(a,0,a.length-1); 
    }

    /**
     * Method: mergesortHelp()
     * Usage: The mergesortHelp method is helper method for mergesort. 
     * Mergesort help recursively divides an array into two subarrays 
     * until the subarrays are of size 1. Then, these subarrays are 
     * recursively merged together such that they are sorted 
     * in ascending order.
     * @postcondition the array is sorted in ascending order from 
     * lowIndex to highIndex. 
     * @param a the array to search
     * @param lowIndex the lower index to begin the sort at
     * @param highIndex the upper index to end the sort at 
     */
    private void mergesortHelp(Comparable[] a, int lowIndex, int highIndex)
    {   
        if(lowIndex < highIndex)
        {
            int mid = (lowIndex+highIndex)/2;
            mergesortHelp(a,lowIndex,mid);
            mergesortHelp(a,mid+1,highIndex);
            merge(a,lowIndex,mid,highIndex); 
        }
    }

    /**
     * method merge()
     * Useage: merge(inputArray, lowIndex, midIndex, highIndex)
     *_______________________________________________
     * Merges the two halves of the input array into one.  The method assumes
     * that each half of the input array is sorted as follows:
     * 
     *                a[lowIndex] to a[midIndex] are in increasing order.
     *                a[midIndex + 1] to a[highIndex] are in increasing order.
     * The method creates an array to hold the output.  It then establishes 
     * two pointers into the two halves of the input array.  The values at the
     * pointer locations are compared, and the smallest is added to the output
     * array.  The corresponding pointer is then increased by one.  In the event
     * either half becomes empty, the remaining values are copied to the output
     * array.
     * Postcondition: a[lowIndex] to a[highIndex] are in increasing order.
     *
     * @param a is the input array of Comparable values
     * @param lowIndex is the index into the array a corresponding to the beginning
     *        of the first half of the array to merge
     * @param midIndex is the index of the last value in the first half of the array
     * @param highIndex is the index of the last value in the second half of the array
     */
    private void merge(Comparable[] a, int lowIndex, int midIndex, int highIndex)
    {
        Comparable[] copy = new Comparable[a.length];
        for (int i = lowIndex; i <= highIndex; i++)
            copy[i] = a[i];
        int left = lowIndex;
        int right = midIndex + 1;
        for (int i = lowIndex; i <= highIndex; i++)
        {
            if (right > highIndex ||(left <= midIndex && copy[left].compareTo(copy[right]) < 0))
            {
                a[i] = copy[left];
                left++;
            }
            else
            {
                a[i] = copy[right];
                right++;
            }
            display.update();
        }
    }

    /**     
     * Method: quicksort()
     * Usage: sorter.quicksort(inputArray)
     * -------------------------------------
     * quicksort() does not actual do the sorting,
     * just calls quicksortHelp with parameters (a, 0, a.length-1),
     * which does the actual quick sorting
     * 
     * Postcondition: a[lowIndex] to a[highIndex] are in increasing order
     * @param a - array of comparable elements to be sorted with quick sort
     */
    public void quicksort(Comparable[] a)
    {
        /* To be implemented post the AP Exam */
    }

    /**
     * Method: quicksortHelp()
     * Usage: quicksortHelp(a, low, high)
     * ------------------------------------------
     * Quick soritng is a recursive sorting algorithm that sets a 
     * pivot point (lowIndex in this case)
     * and calls partition which rough sort: puts every element less than pivot l
     * eft of pivot, and every element bigger than pivot right of pivot
     * then quicksortHelp is called on the sections left & right of the pivot point
     * 
     * Base case: section of the array given by low & highIndex has 1 element (high <= low), 
     *         which is "sorted" by definition, therefore nothing is done to it
     * Recursive reduction: the element at lowIndex is sorted as the pivot using partition()
     * and the index where it lands is returned.
     *         The array is then divided from (low,pivot-1) & (pivot+1,high) 
     *         because index pivot is already sorted 
     *         and quicksortHelp is used again on sections left & right of the pivot element
     * 
     * Postcondition: a[lowIndex] to a[highIndex] are in increasing order
     * @param a - array of comparable elements to be sorted with quick sort
     * @param lowIndex - beginning index of section of array to be sorted
     * @param highIndex - ending index of section of array to be sorted
     */
    private void quicksortHelp(Comparable[] a, int lowIndex, int highIndex)
    {   
        /* To be implemented post the AP Exam */
    }

    /**
     * Method partition
     * Usuage: int pivotIndex = partition(a, lowIndex, highIndex)
     *___________________________________________________________
     *
     *Returns the index of the pivot element defined as follows:
     *                All elements on the left side of the pivot (from lowIndex)
     *                are less than or equal to the pivot.
     *                All elements on the right side of the pivot (through highIndex)
     *                are greater than or equal to the pivot.
     * The computation is performed in place.
     * @param a the array to partion
     * @param lowIndex is the index of the start of the part of array a to consider
     * @param highIndex is the index of the end of the part of array a to consider
     * @return the index of the pivot element in array a
     */
    private int partition(Comparable[] a, int lowIndex, int highIndex)
    {
        /* To be implemented post the AP Exam */
        return -1;
    }
}