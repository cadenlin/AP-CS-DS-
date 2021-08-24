import java.util.*;
/**
 * The MyHashSet class imitates the HashSet class in Java.
 * @author Caden Lin 
 * @version Feb 8 2021 
 * @param <E> the type of data stored in the MyHashSet 
 */

public class MyHashSet<E>
{
    private static final int NUM_BUCKETS = 5;
    private LinkedList<E>[] buckets;
    private int size;

    /** Constructor for MyHashSet objects 
     * 
     *
     */
    public MyHashSet()
    {
        buckets = new LinkedList[NUM_BUCKETS];
        size = 0;
        for(int i = 0 ; i < NUM_BUCKETS ; i++)
            buckets[i]=new LinkedList<E>(); 
    }

    /** Finds the index of the bucket where an object might be found 
     * @param obj the object to find the bucket of 
     * @return the index of the bucket where obj might be found 
     * 
     */
    private int toBucketIndex(Object obj)
    {
        return obj.hashCode()%NUM_BUCKETS ; 
    }

    /** finds the size of the set 
     * @return the number of elements in the set 
     * 
     */
    public int size()
    {
        return size;
    }

    /** determines whether an object is in a set or not 
     * @param obj the object to look for in the set 
     * @return true if obj is in the set, false otherwise
     * 
     */
    public boolean contains(Object obj)
    {
        int index = toBucketIndex(obj) ;
        return buckets[index].contains(obj); 
    }

    /** adds an object to the set if it is not in the set already 
     * @param obj the object to be added 
     * @postcondition obj is added to the set if it is not in the set previously 
     * @return true if the object was not in the set previously and was added, 
     *          false otherwise 
     * 
     */
    public boolean add(E obj)
    {
        if(contains(obj))
            return false; 
        buckets[toBucketIndex(obj)].add(obj); 
        size++; 
        return true;
    }

    /** removes an object from the set if it is in the set  
     * @param obj the object to be removed 
     * @postcondition obj is removed from the set if it was in the set
     * @return true if the object was in the set previously and was removed, 
     *          false otherwise 
     * 
     */
    public boolean remove(Object obj)
    {
        if(!contains(obj))
            return false; 
        buckets[toBucketIndex(obj)].remove(obj);
        size-- ;
        return true; 
    }

    /** Converts the set into a string 
     * @return the elements of the set as a string 
     * 
     */
    public String toString()
    {
        String s = "";
        for (int i = 0; i < buckets.length; i++)
            if (buckets[i].size() > 0)
                s += i + ":" + buckets[i] + " ";
        return s;

    }

    /** Returns an iterator for the set 
     * @return an iterator for the set 
     * 
     */
    public Iterator<E> iterator()
    {
        return new MyHashSetIterator();
    }

    /** The MyHashSetIterator imitates an iterator for a HashSet 
     * @author Caden Lin 
     * @version Feb 9 2021 
     * 
     */
    private class MyHashSetIterator implements Iterator<E>
    {
        private int nextIndex; 
        private int bucketIndex; 
        private int location; 
        /** Constructor for MyHashSetIterator objects 
         * 
         *
         */
        public MyHashSetIterator()
        {
            nextIndex = 0 ; 
            bucketIndex = 0; 
            location = 0 ; 
        }

        /** Determines if there is a next element in the set 
         * @return true if there is a next element, false otherwise 
         * 
         */
        public boolean hasNext()
        {

            return location < size; 

        }

        /** Determines the next element in the set 
         * @return the next element in the set if there is one, 
         *          NoSuchElementException if there is none 
         * 
         */
        public E next()
        {
            if(nextIndex < buckets.length - 1 && bucketIndex >= buckets[nextIndex].size())
            {
                nextIndex++; 
                bucketIndex = 0;
                while( buckets[nextIndex].size() == 0)
                {
                    nextIndex++; 
                    if(nextIndex >= buckets.length - 1)
                        throw new NoSuchElementException();
                }

            }
            bucketIndex++; 
            location++;
            return buckets[nextIndex].get(bucketIndex-1);  
        }

        /** Removes the next element in the set 
         * @postconditon the next element in the set has been removed 
         * 
         */
        public void remove()
        {
            int tempNextIndex = nextIndex;
            int tempBucketIndex = bucketIndex;
            if(nextIndex < buckets.length - 1 && bucketIndex >= buckets[nextIndex].size())
            {
                tempNextIndex++;
                tempBucketIndex = 0 ; 
                while( buckets[tempNextIndex].size() == 0)
                {
                    tempNextIndex++; 
                    if(tempNextIndex >= buckets.length - 1)
                        throw new NoSuchElementException();
                }
            }
            buckets[nextIndex].remove(buckets[tempNextIndex].get(bucketIndex));  
        }
    }
}