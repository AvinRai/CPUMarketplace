/**
 * HashTable.java
 * @author Avin Rai
 * Team 1 Final Project
 */
import java.util.ArrayList;

public class HashTable<T> {

    private int numElements;
    private ArrayList<LinkedList<T>> table;

    /**
     * Constructor for the HashTable class. Initializes the Table to be sized
     * according to value passed in as a parameter. Inserts size empty Lists into
     * the table. Sets numElements to 0
     *
     * @param size the table size
     * @precondition size > 0
     * @throws IllegalArgumentException when size <= 0
     */
    public HashTable(int size) throws IllegalArgumentException {
        if (size <= 0) {
            throw new IllegalArgumentException("HashTable: size must be > 0");
        }
        this.numElements = 0;
        this.table = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            table.add(new LinkedList<>());
        }
    }

    /**
     * Constructor for HashTable class.
     * Inserts the contents of the given array
     * into the Table at the appropriate indices
     * @param array an array of elements to insert
     * @param size the size of the Table
     * @precondition size > 0
     * @throws IllegalArgumentException when size <= 0
     */
    public HashTable(T[] array, int size) throws IllegalArgumentException {
        this(size);
        if (size <= 0) {
            throw new IllegalArgumentException("HashTable: size must be > 0");
        }
        if (array != null) {
            for (T data : array) {
                table.get(hash(data)).addLast(data);
                numElements++;
            }
        }
    }

    /** Accessors */

    /**
     * Returns the hash value in the table for a given Object.
     * @param obj the Object
     * @return the index in the table
     */
    private int hash(T obj) {
        int code = obj.hashCode();
        return code % table.size();
    }

    /**
     * Counts the number of elements at this index.
     * @param index the index in the table
     * @precondition 0 <= index < table.size
     * @return the count of elements at this index
     * @throws IndexOutOfBoundsException when the precondition is violated
     */
    public int countBucket(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > table.size()) {
            throw new IndexOutOfBoundsException("countBucket: index out of bounds");
        }
        return table.get(index).getLength();
    }

    /**
     * Determines total number of elements in the table
     * @return total number of elements
     */
    public int getNumElements() {
        return numElements;
    }

    /**
     * Accesses a specified key in the Table
     * @param elmt the key to search for
     * @return the value to which the specified key is mapped,
     * or null if this table contains no mapping for the key.
     * @precondition elmt == null
     * @throws NullPointerException when the precondition is violated.
     */
    public T get(T elmt) throws NullPointerException {
        if (elmt == null) {
            throw new NullPointerException("get: null element");
        }
        int index = table.get(hash(elmt)).findIndex(elmt);
        if (index == -1) {
            return null;
        }
        table.get(hash(elmt)).advanceIteratorToIndex(index);
        return table.get(hash(elmt)).getIterator();
    }

    /**
     * Accesses a specified element in the table.
     * @param elmt the element to locate
     * @return the bucket number where the element
     * is located or -1 if it is not found.
     * @precondition elmt != null
     * @throws NullPointerException when the precondition is violated.
     */
    public int find(T elmt) throws NullPointerException{
        if (elmt == null) {
            throw new NullPointerException("find: null element");
        }
        int index = hash(elmt);
        if (table.get(index).findIndex(elmt) == -1) {
            return -1;
        }
        return index;
    }

    /**
     * Determines whether a specified element is in the table.
     * @param elmt the element to locate
     * @return whether the element is in the table
     * @precondition elmt != null
     * @throws NullPointerException when the precondition is violated
     */
    public boolean contains(T elmt) throws NullPointerException {
        if (elmt == null) {
            throw new NullPointerException("contains: elmt is null");
        }
        return table.get(hash(elmt)).findIndex(elmt) != -1;
    }

    /** Mutators */

    /**
     * Inserts a new element in the table at the end of the chain
     * of the correct bucket.
     * @param elmt the element to insert
     * @precondition elmt != null
     * @throws NullPointerException when the precondition is violated.
     */
    public void add(T elmt) throws NullPointerException {
        if (elmt == null) {
            throw new NullPointerException("add: null element");
        }
        table.get(hash(elmt)).addLast(elmt);
        numElements++;
    }

    /**
     * Removes the given element from the table.
     * @param elmt the element to remove
     * @precondition elmt != null
     * @return whether elmt exists and was removed from the table
     * @throws NullPointerException when the precondition is violated
     */
    public boolean delete(T elmt) throws NullPointerException {
        if (elmt == null) {
            throw new NullPointerException("delete: elmt is null");
        }
        if (this.contains(elmt)) {
            int bucket = hash(elmt);
            int remove = table.get(bucket).findIndex(elmt);
            table.get(bucket).advanceIteratorToIndex(remove);
            table.get(bucket).removeIterator();
            numElements--;
            return true;
        }
        return false;
    }

    /**
     * Resets the hash table back to the empty state, as if the one argument
     * constructor has just been called.
     */
    public void clear() {
        this.numElements = 0;
        for (int i = 0; i < table.size(); i++) {
            table.get(i).clear();
        }
    }

    /** Additional Methods */

    /**
     * Computes the load factor.
     * @return the load factor
     */
    public double getLoadFactor() {
        return (double) numElements / table.size();
    }

    /**
     * Creates a String of all elements at a given bucket
     * @param bucket the index in the table
     * @return a String of elements, separated by spaces with a new line character
     *         at the end
     * @precondition bucket >= 0 && bucket < table.size()
     * @throws IndexOutOfBoundsException when bucket is
     * out of bounds
     */
    public String bucketToString(int bucket) throws IndexOutOfBoundsException {
        if (bucket < 0 || bucket >= table.size()) {
            throw new IndexOutOfBoundsException("bucketToString: index out of bounds");
        }
        return table.get(bucket).toString();
    }

    /**
     * Creates a String of the bucket number followed by a colon followed by
     * the first element at each bucket followed by a new line. For empty buckets,
     * add the bucket number followed by a colon followed by empty.
     *
     * @return a String of all first elements at each bucket.
     */
    public String rowToString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < table.size(); i++) {
            if (table.get(i).getLength() != 0) {
                result.append("Bucket ").append(i).append(": ").append(table.get(i).getFirst()).append("\n");
            } else {
                result.append("Bucket ").append(i).append(": ").append("empty").append("\n");
            }
        }
        return result + "";
    }

    /**
     * Starting at the 0th bucket, and continuing in order until the last
     * bucket, concatenates all elements at all buckets into one String, with
     * a new line between buckets and one more new line at the end of the
     * entire String.
     * @return a String of all elements in this HashTable.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
            for (LinkedList<T> list : table) {
                if (!list.isEmpty()){
                    result.append(list);
                }
        }
        return result + "\n";
    }
}