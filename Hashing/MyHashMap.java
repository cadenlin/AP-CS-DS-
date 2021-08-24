import java.util.*;
/**
 * MyHashMap acts like a map.
 * 
 * @author  Caden Lin 
 * @version Feb 9 2021 
 * @param <K>   the type of key
 * @param <V>   the type of value
 */
public class MyHashMap<K, V> implements Map<K, V>
{
    private static final int NUM_BUCKETS = 5;
    private LinkedList<MapEntry<K, V>>[] buckets;
    private int size;

    /**
     * a constructor for MyHashMap objects 
     */
    public MyHashMap()
    {

        buckets = new LinkedList[NUM_BUCKETS];
        size = 0;
        for(int i = 0 ; i < NUM_BUCKETS ; i++)
            buckets[i]=new LinkedList<MapEntry<K, V>>(); 

    }

    /** Determines the bucket index of an object
     * @param obj the object to find the bucket index for
     * @return the correct bucket index for that object
     */
    private int toBucketIndex(Object obj)
    {
        return Math.abs(obj.hashCode()) % NUM_BUCKETS;
    }

    /** Determines the size of the map  
     * @return the number of elements in the map 
     * 
     */
    public int size()
    {
        return size;
    }

    /** Determines whether the map is empty or not 
     * @return true if the map is empty, false otherwise 
     * 
     */
    public boolean isEmpty()
    {
        return size == 0;
    }

    /** Determines if the map contains a certain key 
     * @param key the key to search for in the map 
     * @return true if the map contains the key, false otherwise 
     * 
     */
    @Override
    public boolean containsKey(Object key)
    {
        int index = toBucketIndex(key); 
        if(buckets[index].size() == 0 )
            return false;
        else
        {
            for(int i = 0 ; i < buckets[index].size() ; i++)
            {
                if(buckets[index].get(i).getKey().equals(key))
                    return true;
            }
            return false; 

        }
    }

    /** Determines if the map contains a certain value 
     * @param value the value to search for in the map 
     * @return true if the map contains the value, false otherwise 
     */
    public boolean containsValue(Object value)
    {
        for(int i = 0 ; i < buckets.length ; i++)
        {
            for(int j = 0 ; j <buckets[i].size() ; j++)
            {
                if(buckets[i].get(j).getValue().equals(value))
                    return true; 
            }
        }
        return false; 
    }

    /** Determines the value associated with a certain key 
     * @param key the key to get the value associated with 
     * @return the value associated with the key, 
     *          null if the map does not contain the key 
     * 
     */
    public V get(Object key)
    {
        if(!containsKey(key))
            return null;

        int index = toBucketIndex(key); 
        for(int i = 0 ; i < buckets[index].size() ; i++)
        {
            if(buckets[index].get(i).getKey().equals(key))
                return buckets[index].get(i).getValue();
        }
        return null; 

    }

    /** Adds a new key and value pair to the map or 
     * replaces the value associated with an existing key
     * @param key the key to add or replace the value associated with 
     * @param value the new value associated with key 
     * @postcondition a MapEntry with key and value are added to the map if 
     *                  there was not an entry with key beforehand and size is incremented by 1
     *                  the value of the MapEntry with key is replaced with 
     *                   value if there was a MapEntry with key beforehand 
     * @return the old value associated with key if the map already contained key, 
     *          null otherwise 
     * 
     */
    public V put(K key, V value)
    {
        if(containsKey(key))
        {
            int index = toBucketIndex(key);
            int found = 0; 
            for(int i = 0 ; i < buckets[index].size() ; i++)
            {
                if(buckets[index].get(i).getKey().equals(key))
                {
                    found = i;
                }

            }
            V oldValue = buckets[index].get(found).getValue(); 
            buckets[index].get(found).setValue(value);
            return oldValue; 
        }

        else
        {
            int index = toBucketIndex(key); 
            buckets[index].add(new MapEntry<K,V>(key,value)); 
            size++ ; 
            return null; 
        }

    }

    /** Removes an element from the map 
     * @param key the key to remove from the map 
     * @postcondition if the map contains the key, 
     *              the key is removed from the map and the size is decremented by 1 
     * @return the value associated with the map if the map contained the key, 
     *          null otherwise 
     * 
     */
    public V remove(Object key)
    {
        if(!containsKey(key))
            return null;
        int index = toBucketIndex(key);
        int found = 0; 
        for(int i = 0 ; i < buckets[index].size() ; i++)
        {
            if(buckets[index].get(i).getKey().equals(key))
            {
                found = i;
            }

        }  
        V oldValue = buckets[index].get(found).getValue();
        buckets[index].remove(found);
        size--;
        return oldValue; 

    }

    /** Copies all the keys from the given map to this map
     * @param m the map to copy keys from 
     * @postcondition all the keys from the given map are copied to this map 
     * 
     */
    public void putAll(Map<? extends K, ? extends V> m)
    {
        for (K key : m.keySet())
        {
            put(key, m.get(key));
        }
    }

    /**
     *  Clears the map 
     *  @postcondition all the elements in the map are null 
     */
    public void clear()
    {
        for (int i = 0; i < NUM_BUCKETS; i++)
        {
            buckets[i] = null;
        }
    }

    /** Creates a set of all the keys in the map 
     * @return a set with all the keys in the map 
     * 
     */
    public Set<K> keySet()
    {
        Set<K> keySet = new HashSet<K>(); 
        for(int i = 0; i < NUM_BUCKETS; i++) 
        {
            for(int j = 0 ; j < buckets[i].size() ; j++)
            {
                keySet.add(buckets[i].get(j).getKey());
            }
        }
        return keySet;
    }

    /** Creates a collection of all the values in the map 
     * @return an ArrayList containing each value in the map 
     * 
     */
    public Collection<V> values()
    {
        Collection<V> valuesList = new ArrayList<V>(); 
        for(int i = 0; i < NUM_BUCKETS; i++) 
        {
            for(int j = 0 ; j < buckets[i].size() ; j++)
            {
                valuesList.add(buckets[i].get(j).getValue());
            }
        }
        return valuesList;
    }

    /** Creates a set view of the mappings in the map 
     * @return a set with all the mapEntrys of the map 
     * 
     */
    @Override
    public Set<java.util.Map.Entry<K, V>> entrySet()
    {
        Set<java.util.Map.Entry<K, V>> entrySet = new HashSet<java.util.Map.Entry<K, V>>(); 
        for(int i = 0; i < NUM_BUCKETS; i++) 
        {
            for(int j = 0 ; j < buckets[i].size() ; j++)
            {
                entrySet.add(buckets[i].get(j));
            }
        }
        return entrySet; 
    }

    /**
     * returns the contents of the hashmap as a string
     * @return a string that represents the contents of this hashmap
     */
    public String toString()
    {
        String s  = "";
        s += "{";        
        for (int i = 0; i < NUM_BUCKETS; i ++)
        {
            if (buckets[i] != null)
            {
                s += i + "=" ; 
                for(int j = 0 ; j < buckets[i].size() ; j++)
                    s += ( (LinkedList<MapEntry<K, V>>) buckets[i].get(j).getValue()) + ", ";
                s += "|"  ; 
            }
        }
        if (s.length() > 2)
        {
            s = s.substring(0, s.length() - 2);
        }
        s+= "}";
        return s;
    }
}
