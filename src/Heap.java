/**
 * Heap.java
 * @author Avin Rai
 * CIS 22C, Lab 18
 */
import java.util.ArrayList;
import java.util.Comparator;

public class Heap<T> {
    private int heapSize;
    private ArrayList<T> heap;
    private Comparator<T> cmp;

    /**Constructors/

     /**
     * Constructor for the Heap class.
     * Sets heapSize to data size, stores parameters, inserts null at heap
     * element 0, and calls buildHeap().
     * @param data an unordered ArrayList, where element 0 is not used.
     * @param cmp that determines organization of heap
     * based on priority.
     */
    public Heap(ArrayList<T> data, Comparator<T> cmp) {
        this.heapSize = data.size();
        this.heap = data;
        this.heap.add(0, null);
        this.cmp = cmp;
        buildHeap();
    }

    /**Mutators*/

    /**
     * Converts an ArrayList into a valid max heap. Called by constructor.
     * Calls helper method heapify.
     */
    public void buildHeap() {
        for (int i = heapSize / 2; i > 0; i--) {
            heapify(i);
        }
    }

    /**
     * Helper method to buildHeap, remove, and sort.
     * Bubbles an element down to its proper location within the heap.
     * @param index an index in the heap
     */
    private void heapify(int index) {
        int maxIndex = index;
        int left = getLeft(index);
        int right = getRight(index);
        if (left <= heapSize && cmp.compare(heap.get(left), heap.get(index)) > 0) {
            maxIndex = left;
        }
        if (right <= heapSize && cmp.compare(heap.get(right), heap.get(maxIndex)) > 0) {
            maxIndex = right;
        }
        if (index != maxIndex) {
            T temp = heap.get(index);
            heap.set(index, heap.get(maxIndex));
            heap.set(maxIndex, temp);
            heapify(maxIndex);
        }
    }

    /**
     * Inserts the given data into heap.
     * Calls helper method heapIncreaseKey.
     * @param key the data to insert
     */
    public void insert(T key) {
        heapSize++;
        heap.add(key);
        heapIncreaseKey(heapSize, key);
    }

    /**
     * Helper method for insert.
     * Bubbles an element up to its proper location
     * @param index the current index of the key
     * @param key the data
     */
    private void heapIncreaseKey(int index, T key) {
        if (cmp.compare(key, heap.get(index)) < 0) {
            heap.set(index, key);
        }
        while (index > 1 && cmp.compare(heap.get(getParent(index)), heap.get(index)) < 0) {
            T temp = heap.get(index);
            heap.set(index, heap.get(getParent(index)));
            heap.set(getParent(index), temp);
            index = getParent(index);
        }
    }

    /**
     * Removes the element at the specified index.
     * Calls helper method heapify
     * @param index the index of the element to remove
     */
    public void remove(int index) {
        if (index >= 0 && index <= heapSize) {
            T temp = heap.get(index);
            heap.set(index, heap.get(heapSize));
            heap.set(heapSize, temp);
            heap.remove(heapSize);
            heapSize--;
            if (heapSize > 1) {
                heapify(1);
            }
        }
    }

    /**Accessors*/

    /**
     * Returns the heap size (current number of elements)
     * @return the size of the heap
     */
    public int getHeapSize() {
        return heapSize;
    }

    /**
     * Returns the location (index) of the
     * left child of the element stored at index.
     * @param index the current index
     * @return the index of the left child.
     * @precondition 0 < index <= heap_size
     * @throws IndexOutOfBoundsException when precondition is violated.
     */
    public int getLeft(int index) throws IndexOutOfBoundsException {
        if (1 > index || index > heapSize) {
            throw new IndexOutOfBoundsException("getLeft: index out of bounds");
        }
        return index * 2;
    }

    /**
     * Returns the location (index) of the right child of the element
     * stored at index.
     * @param index the current index
     * @return the index of the right child
     * @precondition 0 < i <= heap_size
     * @throws IndexOutOfBoundsException when precondition is violated.
     */
    public int getRight(int index) throws IndexOutOfBoundsException {
        if (1 > index || index > heapSize) {
            throw new IndexOutOfBoundsException("getRight: index out of bounds");
        }
        return index * 2 + 1;
    }

    /**
     * Returns the location (index) of the
     * parent of the element stored at index.
     * @param index the current index
     * @return the index of the parent
     * @precondition 1 < i <= heap_size
     * @throws IndexOutOfBoundsException when precondition is violated.
     */
    public int getParent(int index) throws IndexOutOfBoundsException {
        if (1 >= index || index > heapSize) {
            throw new IndexOutOfBoundsException("getParent: index out of bounds");
        }
        return index / 2;
    }

    /**
     * Returns the maximum element (highest priority)
     * @return the max value
     */
    public T getMax() {
        return heap.get(1);
    }

    /**
     * Returns the element at a specific index.
     * @param index an index in the heap.
     * @return the data at the index.
     * @precondition 0 < i <= heap_size
     * @throws IndexOutOfBoundsException when precondition is violated.
     */
    public T getElement(int index) throws IndexOutOfBoundsException {
        if (0 >= index || index > heapSize) {
            throw new IndexOutOfBoundsException("getElement: index out of bounds");
        }
        return heap.get(index);
    }

    /**Additional Operations*/

    /**
     * Creates a String of all elements in the heap, separated by ", ".
     * @return a String of all elements in the heap, separated by ", ".
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < this.heap.size(); i++) {
            if (i != heap.size() - 1) {
                result.append(this.heap.get(i));
            } else {
                result.append(this.heap.get(i));
            }
        }
        return result + "";
    }

    /**
     * Uses the heap sort algorithm to sort the heap into ascending order.
     * Calls helper method heapify.
     * @return an ArrayList of sorted elements
     * @postcondition heap remains a valid heap
     */
    public ArrayList<T> sort() {
        ArrayList<T> save = new ArrayList<>(heap);
        int n = heapSize;
        for (int i = n; i >= 2; i--) {
            T temp = heap.get(i);
            heap.set(i, heap.get(1));
            heap.set(1, temp);
            heapSize--;
            heapify(1);
        }
        ArrayList<T> result = new ArrayList<>();
        for (int i = 1; i < save.size(); i++) {
            result.add(heap.get(i));
        }
        heap = save;
        return result;
    }
}
