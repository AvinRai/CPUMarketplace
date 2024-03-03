/**
 * Defines a doubly-linked list class
 * @author Avin Rai
 */
import java.util.NoSuchElementException;

public class LinkedList<T> {
    private class Node {
        private T data;
        private Node next;
        private Node prev;

        public Node(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    private int length;
    private Node first;
    private Node last;
    private Node iterator;

    /**** CONSTRUCTORS ****/

    /**
     * Instantiates a new LinkedList with default values
     * @postcondition LinkedList is set up and empty
     */
    public LinkedList() {
        first = null;
        last = null;
        iterator = null;
        length = 0;
    }

    /**
     * Converts the given array into a LinkedList
     * @param array the array of values to insert into this LinkedList
     * @postcondition a new List object which has all the elements of a given separate array
     */
    public LinkedList(T[] array) {
        this();
        if(array != null && array.length != 0){
            for (T data : array){
                addLast(data);
            }
        }
    }

    /**
     * Instantiates a new LinkedList by copying another List
     * @param original the LinkedList to copy
     * @postcondition a new List object, which is an identical,
     * but separate, copy of the LinkedList original
     */
    public LinkedList(LinkedList<T> original) {
        this();
        if(original != null && original.length != 0){
            Node temp = original.first;
            while(temp != null){
                addLast(temp.data);
                temp = temp.next;
            }
            iterator = null;
        }
    }

    /**** ACCESSORS ****/

    /**
     * Returns the value stored in the first node
     * @precondition length != 0
     * @return the value stored at node first
     * @throws NoSuchElementException "getFirst: list is empty"
     */
    public T getFirst() throws NoSuchElementException {
        if (length == 0)
            throw new NoSuchElementException("getFirst: list is empty");
        return first.data;
    }

    /**
     * Returns the value stored in the last node
     * @precondition length != 0
     * @return the value stored in the node last
     * @throws NoSuchElementException "getLast: list is empty"
     */
    public T getLast() throws NoSuchElementException {
        if (length == 0){
            throw new NoSuchElementException("getLast: list is empty");
        }
        return last.data;
    }

    /**
     * Returns the data stored in the iterator node
     * @precondition !offEnd()
     * @return the data stored in the iterator node
     * @throws NullPointerException "getIterator: iterator is null"
     */
    public T getIterator() throws NullPointerException {
        if(offEnd()){
            throw new NullPointerException("getIterator: iterator is null");
        }
        return iterator.data;
    }

    /**
     * Returns the current length of the LinkedList
     * @return the length of the LinkedList from 0 to n
     */
    public int getLength() {
        return length;
    }

    /**
     * Returns whether the LinkedList is currently empty
     * @return whether the LinkedList is empty
     */
    public boolean isEmpty() {
        return length == 0;
    }

    /**
     * Returns whether the iterator is offEnd, i.e. null
     * @return whether the iterator is null
     */
    public boolean offEnd() {
        return iterator == null;
    }

    /**** MUTATORS ****/

    /**
     * Creates a new first element
     * @param data the data to insert at the front of the LinkedList
     * @postcondition data is added to the beginning of the list
     */
    public void addFirst(T data) {
        Node newFirst = new Node(data);
        if (length == 0){
            first = newFirst;
            last = newFirst;
        } else {
            newFirst.next = first;
            first.prev = newFirst;
            first = newFirst;
        }
        length += 1;
    }

    /**
     * Creates a new last element
     * @param data the data to insert at the end of the LinkedList
     * @postcondition data is added to the end of the list
     */
    public void addLast(T data) {
        Node newLast = new Node(data);
        if (length == 0){
            first = newLast;
            last = newLast;
        } else {
            last.next = newLast;
            newLast.prev = last;
            last = newLast;
        }
        length += 1;
    }

    /**
     * Inserts a new element after the iterator
     * @param data the data to insert
     * @precondition !offEnd()
     * @postcondition a new node has been added after the iterator
     * @throws NullPointerException "addIterator: iterator is null"
     */
    public void addIterator(T data) throws NullPointerException{
        if (offEnd()){
            throw new NullPointerException("addIterator: iterator is null");
        } else if (iterator == last) {
            addLast(data);
        } else {
            Node newNode = new Node(data);
            newNode.next = iterator.next;
            newNode.prev = iterator;
            iterator.next.prev = newNode;
            iterator.next = newNode;
            length++;
        }
    }

    /**
     * removes the element at the front of the LinkedList
     * @precondition length != 0
     * @postcondition list returned with original first node removed
     * @throws NoSuchElementException "removeFirst: list is empty"
     */
    public void removeFirst() throws NoSuchElementException {
        if (length == 0){
            throw new NoSuchElementException("removeFirst: list is empty");
        } else if (length == 1) {
            first = null;
            last = null;
            iterator = null;
            length = 0;
        } else {
            if (iterator == first){
                advanceIterator();
            }
            first = first.next;
            first.prev = null;
            length--;
        }
    }

    /**
     * removes the element at the end of the LinkedList
     * @precondition length != 0
     * @postcondition list returned with original last node removed
     * @throws NoSuchElementException "removeLast: list is empty"
     */
    public void removeLast() throws NoSuchElementException {
        if (length == 0){
            throw new NoSuchElementException("removeLast: list is empty");
        } else if (length == 1) {
            first = null;
            last = null;
            iterator = null;
            length = 0;
        } else {
            if(iterator == last){
                reverseIterator();
            }
            last = last.prev;
            last.next = null;
            length--;
        }
    }

    /**
     * removes the element referenced by the iterator
     * @precondition !offEnd
     * @postcondition Node at iterator is removed from list
     * @throws NullPointerException "removeIterator: iterator is null"
     */
    public void removeIterator() throws NullPointerException {
        if (offEnd()) {
            throw new NullPointerException("removeIterator: iterator is null");
        } else if (iterator == first) {
            removeFirst();
            iterator = null;
        } else if (iterator == last) {
            removeLast();
            iterator = null;
        } else {
            iterator.prev.next = iterator.next;
            iterator.next.prev = iterator.prev;
            iterator = null;
            length--;
        }
    }

    /**
     * places the iterator at the first node
     * @postcondition iterator set to the beginning of list
     */
    public void positionIterator(){
        iterator = first;
    }

    /**
     * Moves the iterator one node towards the last
     * @precondition !offEnd()
     * @postcondition iterator is moved one towards the last
     * @throws NullPointerException "advanceIterator: iterator is null"
     */
    public void advanceIterator() throws NullPointerException {
        if(offEnd()){
            throw new NullPointerException("advanceIterator: iterator is null");
        }
        iterator = iterator.next;
    }

    /**
     * Moves the iterator one node towards the first
     * @precondition !offEnd()
     * @postcondition iterator is moved one towards the first
     * @throws NullPointerException "reverseIterator: iterator is null"
     */
    public void reverseIterator() throws NullPointerException {
        if(offEnd()){
            throw new NullPointerException("reverseIterator: iterator is null");
        }
        iterator = iterator.prev;
    }

    /**** ADDITIONAL OPERATIONS ****/

    /**
     * Re-sets LinkedList to empty as if the
     * default constructor had just been called
     */
    public void clear() {
        first = null;
        last = null;
        iterator = null;
        length = 0;
    }

    /**
     * Converts the LinkedList to a String, with each value separated by a blank
     * line At the end of the String, place a new line character
     * @return the LinkedList as a String
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        Node temp = first;
        while (temp != null){
            result.append(temp.data + " ");
            temp = temp.next;
        }

        return result + "\n";
    }

    /**
     * Determines whether the given Object is
     * another LinkedList, containing
     * the same data in the same order
     * @param obj another Object
     * @return whether there is equality
     */
    @SuppressWarnings("unchecked") //good practice to remove warning here
    @Override public boolean equals(Object obj) {
        if(obj == this){
            return true;
        } else if (!(obj instanceof LinkedList)) {
            return false;
        } else {
            LinkedList<T> other = (LinkedList<T>)(obj);
            if(length != other.getLength()){
                return false;
            }
            Node temp1 = this.first;
            Node temp2 = other.first;
            while(temp1 != null){
                if (temp1.data == null || temp2.data == null) {
                    if(temp1.data != temp2.data){
                        return false;
                    }
                } else if (!(temp1.data.equals(temp2.data))){
                    return false;
                }
                temp1 = temp1.next;
                temp2 = temp2.next;
            }
        }
        return true;
    }

    /**CHALLENGE METHODS*/

    /**
     * Moves all nodes in the list towards the end
     * of the list the number of times specified
     * Any node that falls off the end of the list as it
     * moves forward will be placed the front of the list
     * For example: [1, 2, 3, 4, 5], numMoves = 2 -> [4, 5, 1, 2 ,3]
     * For example: [1, 2, 3, 4, 5], numMoves = 4 -> [2, 3, 4, 5, 1]
     * For example: [1, 2, 3, 4, 5], numMoves = 7 -> [4, 5, 1, 2 ,3]
     * @param numMoves the number of times to move each node.
     * @precondition numMoves >= 0
     * @postcondition iterator position unchanged (i.e. still referencing
     * the same node in the list, regardless of new location of Node)
     * @throws IllegalArgumentException when numMoves < 0
     */
    public void spinList(int numMoves) throws IllegalArgumentException{
        if (numMoves < 0){
            throw new IllegalArgumentException("spinList: negative moves");
        }
        if (length != 0) {
            Node tail = first;
            while (tail.next != null){
                tail = tail.next;
            }
            numMoves = numMoves % length;
            positionIterator();
            for (int i = 0; i < length - numMoves - 1; i++) {
                advanceIterator();
            }
            Node head = iterator.next;
            head.prev = null;
            tail.next = first;
            first.prev = tail;
            iterator.next = null;
            first = head;
            last = iterator;

            positionIterator();
            for (int i = 0; i < numMoves; i++) {
                advanceIterator();
            }
        }
    }


    /**
     * Splices together two LinkedLists to create a third List
     * which contains alternating values from this list
     * and the given parameter
     * For example: [1,2,3] and [4,5,6] -> [1,4,2,5,3,6]
     * For example: [1, 2, 3, 4] and [5, 6] -> [1, 5, 2, 6, 3, 4]
     * For example: [1, 2] and [3, 4, 5, 6] -> [1, 3, 2, 4, 5, 6]
     * @param list the second LinkedList
     * @return a new LinkedList, which is the result of
     * interlocking this and list
     * @postcondition this and list are unchanged
     */
    public LinkedList<T> altLists(LinkedList<T> list) {
        LinkedList<T> L = new LinkedList<>();
        if (list == null){
            return this;
        }
        Node temp1 = first;
        Node temp2 = list.first;
        while(temp1 != null || temp2 != null){
            if (temp1 == null) {
                L.addLast(temp2.data);
                temp2 = temp2.next;
            } else if (temp2 == null) {
                L.addLast(temp1.data);
                temp1 = temp1.next;
            } else {
                L.addLast(temp1.data);
                L.addLast(temp2.data);
                temp1 = temp1.next;
                temp2 = temp2.next;
            }
        }
        return L;
    }

    /**
     * Returns each element in the LinkedList along with its
     * numerical position from 1 to n, followed by a newline.
     * @return list of string with respective indexes
     */
    public String numberedListString() {
        StringBuilder out = new StringBuilder();
        positionIterator();
        for (int i = 1; i <= length; i++) {
            out.append(i).append(". ").append(getIterator()).append("\n");
            advanceIterator();
        }
        return out + "\n";
    }

    /**
     * Searches the LinkedList for a given element's index.
     * @param data the data whose index to locate.
     * @return the index of the data or -1 if the data is not contained
     * in the LinkedList.
     */
    public int findIndex(T data) {
        int index = 0;
        LinkedList<T> temp = new LinkedList<>(this);
        if (temp.length == 0){
            return -1;
        }
        while (temp.first != null) {
            if (temp.getFirst().equals(data)) {
                return index;
            }
            index++;
            temp.removeFirst();
        }
        return -1;
    }

    /**
     * Advances the iterator to location within the LinkedList
     * specified by the given index.
     * @param index the index at which to place the iterator.
     * @precondition index >= 0,  index < length
     * @throws IndexOutOfBoundsException when index is out of bounds
     */
    public void advanceIteratorToIndex(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("advanceIteratorToIndex: index out of bounds");
        }
        positionIterator();
        for (int i = 0; i < index; i++) {
            advanceIterator();
        }
    }
}