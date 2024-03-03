/**
 * Defines a Queue class
 * @author Avin Rai
 * @author Victor Fugere
 * @author Myles Vongnakhone
 * @author Aung Aung
 * @author Trista Chen
 * @author Shreyana Bolleddu
 * @param <T> the generic data stored in the Queue
 */
import java.util.NoSuchElementException;

public class Queue<T> implements Q<T> {
    private class Node {
        private T data;
        private Node next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private int size;
    private Node front;
    private Node end;

    /****CONSTRUCTORS****/

    /**
     * Default constructor for the Queue class
     * @postcondition a new Queue object with all fields
     * assigned default values
     */
    public Queue() {
        front = end = null;
        size = 0;
    }

    /**
     * Converts an array into a Queue
     * @param array the array to copy into
     * the Queue
     */
    public Queue(T[] array) {
        this();
        if(array != null && array.length != 0){
            for (T data : array){
                this.enqueue(data);
            }
        }
    }

    /**
     * Copy constructor for the Queue class
     * Makes a deep copy of the parameter
     * @param original the Queue to copy
     * @postcondition the Queue deep copies the original Queue
     */
    public Queue(Queue<T> original) {
        this();
        if(original != null && original.getSize() != 0){
            Node temp = original.front;
            while(temp != null){
                this.enqueue(temp.data);
                temp = temp.next;
            }
            size = original.size;
        }
    }

    /****ACCESSORS****/

    /**
     * Returns the value stored at the front
     * of the Queue
     * @return the value at the front of the queue
     * @precondition !isEmpty()
     * @throws NoSuchElementException when the
     * precondition is violated
     */
    public T getFront() throws NoSuchElementException {
        if(size == 0){
            throw new NoSuchElementException("getFront: list is empty");
        }
        return front.data;
    }

    /**
     * Returns the size of the Queue
     * @return the size from 0 to n
     */
    public int getSize() {
        return size;
    }

    /**
     * Determines whether a Queue is empty
     * @return whether the Queue contains no elements
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /****MUTATORS****/

    /**
     * Inserts a new value at the end of the Queue
     * @param data the new data to insert
     * @postcondition data added to the end of Queue
     */
    public void enqueue(T data) {
        Node newEnd = new Node(data);
        if (size == 0){
            front = newEnd;
            end = newEnd;
        } else {
            end.next = newEnd;
            end = newEnd;
        }
        size++;
    }

    /**
     * Removes the front element in the Queue
     * @precondition !isEmpty()
     * @throws NoSuchElementException when
     * the precondition is violated
     * @postcondition first node is removed from Queue
     */
    public void dequeue() throws NoSuchElementException {
        if(size == 0) {
            throw new NoSuchElementException("dequeue: isEmpty()");
        } else if (size == 1){
            front = null;
            end = null;
            size = 0;
        } else {
            front = front.next;
            size--;
        }

    }

    /****ADDITIONAL OPERATIONS****/

    /**
     * Returns the values stored in the Queue
     * as a String, separated by a blank space
     * with a new line character at the end
     * @return a String of Queue values
     */
    @Override public String toString() {
        StringBuilder result = new StringBuilder();
        Node temp = front;
        while (temp != null){
            result.append(temp.data + " ");
            temp = temp.next;
        }
        return result + "\n";
    }

    /**
     * Determines whether two Queues contain
     * the same values in the same order
     * @param obj the Object to compare to this
     * @return whether obj and this are equal
     */
    @SuppressWarnings("unchecked") // good practice to remove warning here
    @Override public boolean equals(Object obj)  {
        if(obj == this){
            return true;
        } else if (!(obj instanceof Queue)){
            return false;
        } else {
            Queue <T> otherQueue = (Queue<T>)(obj);
            if(this.size != otherQueue.getSize()){
                return false;
            }
            Node temp1 = this.front;
            Node temp2 = otherQueue.front;
            while (temp1 != null){
                if (temp1.data != null || temp2.data != null) {
                    if (!temp1.data.equals(temp2.data)) {
                        return false;
                    }
                }
                temp1 = temp1.next;
                temp2 = temp2.next;
            }
        }
        return true;
    }
}