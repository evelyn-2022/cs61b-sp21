package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author Evelyn
 */
public class MyHashMap<K, V> implements Map61B<K, V>, Iterable<K> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private static final int INITIAL_SIZE = 16;
    private static final double LOAD_FACTOR = 0.75;

    /* Instance Variables */
    private Collection<Node>[] buckets;
    private int tableSize;
    private double maxLoad;
    private int size;

    /** Constructors */
    public MyHashMap() {
        this(INITIAL_SIZE, INITIAL_SIZE * LOAD_FACTOR);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, LOAD_FACTOR * initialSize);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        tableSize = initialSize;
        this.maxLoad = maxLoad;
        buckets = createTable(tableSize);
        size = 0;
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        Collection<Node>[] newTable = new Collection[tableSize];
        for (int i = 0; i < newTable.length; i++) {
            newTable[i] = createBucket();
        }
        return newTable;
    }

    @Override
    public void clear() {
        buckets = createTable(tableSize);
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        int hashCode = Math.floorMod(key.hashCode(), tableSize);
        Collection<Node> targetBucket = buckets[hashCode];
        for (Node node : targetBucket) {
            if (node.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(K key) {
        int hashCode = Math.floorMod(key.hashCode(), tableSize);
        Collection<Node> targetBucket = buckets[hashCode];
        for (Node n : targetBucket) {
            if (n.key.equals(key)) {
                return n.value;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        if (size >= maxLoad) {
            resize(tableSize * 2);
        }

        int hashCode = Math.floorMod(key.hashCode(), tableSize);
        Collection<Node> targetBucket = buckets[hashCode];
        Node newNode = createNode(key, value);

        for (Node n : targetBucket) {
            if (n.key.equals(key)) {
                n.value = value;
                return;
            }
        }

        targetBucket.add(newNode);
        size++;
    }

    private void resize(int capacity) {
        Collection<Node>[] temp = createTable(capacity);
        for (K key: this) {
            Node n = new Node(key, get(key));
            int hashCode = Math.floorMod(key.hashCode(), capacity);
            temp[hashCode].add(n);
        }
        maxLoad = LOAD_FACTOR * capacity;
        tableSize = capacity;
        buckets = temp;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<>();
        for (K key : this) {
            keySet.add(key);
        }
        return keySet;
    }

    @Override
    public V remove(K key) {
        return null;
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        return new hashMapIterator();
    }

    private class hashMapIterator implements Iterator<K> {
        int index = 0;
        Iterator<Node> iterator = buckets[0].iterator();

        @Override
        public boolean hasNext() {
            while (!iterator.hasNext()) {
                index++;
                if (index >= tableSize) {
                    return false;
                }
                iterator = buckets[index].iterator();
            }
            return true;
        }

        @Override
        public K next() {
            return iterator.next().key;
        }
    }

    public static void main(String[] args) {
        MyHashMap<String, Integer> mymap = new MyHashMap<>();
        for (int i = 0; i < 3; i++) {
            mymap.put("i" + i, i);
        }

        for (String item : mymap) {
            System.out.println("Key: " + item + ", value: " + mymap.get(item));
        }
    }
}
