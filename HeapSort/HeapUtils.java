
/**
 * The HeapUtils class contains the methods needed to perform HeapSort, 
 * which converts a complete binary tree given in array form into a max heap. 
 * This is done by first converting the elements of the array into a max heap, which 
 * is a complete binary tree where each node has a value greater than both of its 
 * children. 
 * 
 * Then, the node at the top of the tree (the greatest value),is moved to the end of the array 
 * as it is swapped with the last node in the tree. The heapSize is then reduced by 1. 
 * The remaining tree, excluding the previous root, is turned into a max heap again, bringing the 
 * next greatest value to the top of the tree. 
 * This step is repeated, meaning the root of the tree is continually added to the end of the tree 
 * and the next greatest value is bubbled up to the top of the tree, 
 * decrementing the size of the heap by 1. 
 * Once the size of the heap is one, all the values in the array should be in ascending order, and 
 * the heapSort will be completed. 
 * 
 * 
 * The class also contains the remove and insert methods, which delete and 
 * insert an element into a heap while maintaing the heap structure and heap property.
 * 
 *
 * @author Caden Lin 
 * @version Jan 25 2020 
 */
public class HeapUtils
{
    /**
     * Constructor for objects of class HeapUtils
     */
    public HeapUtils()
    {
    }

    /** The heapify method heapifies a complete binary tree (given in array form),
     * which means that it takes in an array that contains the heap data 
     * and rearranges the elements such that they represent a max heap.  
     * The tree must follow the heap structure, meaning it is a complete binary tree, 
     * and must follow the max heap property, meaning that any parent node is larger tan 
     * both of its child nodes. 
     * 
     * The time complexity is O(logn). 
     * 
     * @precondition heap is an array that contains the heap data for a complete binary tree
     *               the left and right subtrees of the root that is called must be max heaps
     *               heapSize is less than the length of heap 
     * @postcondition the elements of heap have been rearranged such that heap 
     *                now contains the heap data for a max heap
     *                heapSize is unchanged 
     * 
     *
     * @param heap the array that represents the complete binary team that is 
     * to be rearranged 
     * @param index the root of the tree that is being heapified 
     * @param heapSize the size of the heap 
     * 
     */
    public static void heapify(Comparable[] heap, int index, int heapSize) 
    {
        int indexL = index * 2;
        int indexR = indexL + 1;
        int indexMax;
        if (indexL > heapSize)
        {
            indexMax = indexR;
        }
        else if (indexR > heapSize)
        {
            indexMax = indexL;
        }
        else if (((Integer)heap[indexL]).compareTo((Integer)heap[indexR]) >= 0)
        {
            indexMax = indexL;
        }
        else
        {
            indexMax = indexR;
        }

        if (indexL <= heapSize && heap[index].compareTo(heap[indexMax]) < 0)
        {             
            heap = swapValues(heap, index, indexMax);
            heapify(heap, indexMax, heapSize);
        }    
    }

    /** The buildHeap method heapifies every nonleaf node in a complete binary tree 
     *  (given in array form), turning the tree into a max Heap. It begins at the last 
     *  nonleaf node, which has index n/2 (where n is the total number of nodes in the tree)
     *  and then undergoes a backwards level order traversal, heapifying every node before this one.
     *  The method is finished when the method reaches the root of the tree and has heapified every 
     *  nonleaf node in the tree. 
     *  
     *  
     *  The time complexity is O(nlog(n)). 
     *  @precondition heap is an array that contains the heap data for a complete binary tree
     *                heapSize is less than the length of heap 
     *  @postcondition the elements of heap have been rearranged such that heap 
     *                 now contains the heap data for a max heap
     *                 heapSize is unchanged 
     *  
     * @param heap an array with the heap data for a complete binary tree
     * 
     * @param heapSize the size of the heap 
     * 
     */
    public static void buildHeap(Comparable[] heap, int heapSize) 
    {
        int parent = heapSize / 2 ;
        while(parent>0)
        {
            heapify(heap,parent,heapSize); 
            parent--; 
        }

    }

    /** The remove method removes the root of a complete binary tree by swapping with 
     *  the last node in the tree and heapifying the swapped node. The root node is 
     *  swapped with the last node in the heap and the size of the heap is then reduced by 1.
     *  The new value at the root of the true is then heapified and bubbled down to 
     *  its correct place such that the tree follows the heap structure and max heap property. 
     *  
     *  @precondition heap is an array that contains the heap data for a complete binary tree
     *                heapSize is less than the length of heap 
     *  @postcondition the elements of heap have been rearranged such that heap 
     *                  now contains the heap data for a max heap
     *                  heapSize is decremented by 1 
     *  The time complexity is O(log(n)). 
     *
     * @param heap an array with the heap data for a complete binary tree
     * 
     * @param heapSize the size of the heap 
     * @return the value that is removed 
     * 
     */
    public static Comparable remove(Comparable[] heap, int heapSize) 
    {
        Comparable originalRoot = heap[1];
        heap = swapValues(heap, 1, heapSize); 
        heap[heapSize] = null; 
        heapSize = heapSize - 1; 
        heapify(heap,1,heapSize); 
        return originalRoot; 
    }

    /** The insert method inserts a value into the complete binary tree. The element is added 
     * to the heap as the last element, and the value is bubbled up to the correct location
     *  such that the heap structure and the heap property are maintained. 
     *  The time complexity is O(log(n)). 
     *  @precondition heap is an array that contains the heap data for a complete binary tree
     *                heapSize is less than the length of heap 
     *  @postcondition the elements of heap have been rearranged such that heap 
     *                  now contains the heap data for a max heap
     *                  heapSize is increased by 1  
     *
     * @param heap an array with the heap data for a complete binary tree
     * 
     * @param heapSize the size of the heap 
     * @param item the value of the new element to be inserted 
     * @return the array with the new value inserted in the appropriate location 
     * 
     */
    public static Comparable[] insert(Comparable[] heap, Comparable item, int heapSize) 
    {
        if (heapSize + 1 >= heap.length)
        {
            Comparable[] doubledHeap = new Comparable[heap.length * 2];
            for (int i = 1; i < heap.length; i++)
            {
                doubledHeap[i] = heap[i];                
            }
            heap = doubledHeap;
        }
        heap[heapSize + 1] = item;
        int parent = (heapSize + 1) / 2;
        while (heap[parent].compareTo(item) < 0 && parent >= 0)
        {
            heap = swapValues(heap, parent, heapSize + 1);
            parent = (heapSize + 1) / 2;
        }
        return heap; 
    }

    /** The heapSort method is an algorithm that sorts the elements in an array 
     *  into ascending order. The algorithm works by converting the data set into a 
     *  max Heap by calling buildHeap once.
     *  
     *  Then, it repeatedly removes the root of the tree and moves it 
     *  to the end of the array until the heapSize is 1 (by calling the delete method)
     *  and the elements of the array are in ascending order, all while maintaing 
     *  the heap structure and property. 
     *  The time complxity is O(nlogn). 
     *  @precondition heap is an array that contains the heap data for a complete binary tree
     *                heapSize is less than the length of heap 
     *  @postcondition the elements of heap have been rearranged such that heap 
     *                  now contains the heap data for a max heap
     *                  heapSize is unchanged 
     * @param heap an array with the heap data for a complete binary tree
     * 
     * @param heapSize the size of the heap 
     * 
     */
    public static void heapSort(Comparable[] heap, int heapSize)
    {
        buildHeap(heap, heapSize); 
        while (heapSize > 1)
        {
            Comparable originalRoot = remove(heap, heapSize);
            heap[heapSize] = originalRoot;
            heapSize--;            
        }    
    }

    /** The swapValues method swaps two values in an array.  
     *  @postcondition the two values in the array are swapped 
     *  @param array an array that contains the heap data 
     *  @param index1 the index of one element to be swapped 
     *  @param index2 the index of another element to be swapped 
     *  @return the array with the two elements swapped 
     * 
     */
    private static Comparable[] swapValues(Comparable[] array, int index1, int index2)
    {
        Comparable val1 = array[index1];
        Comparable val2 = array[index2];
        array[index1]=val2;
        array[index2]=val1; 
        return array;
    }

}
