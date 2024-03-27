/**
 * LinkedListTest.java.java
 * @author Avin Rai
 * Team 1 Final Project
 */
import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {

    @Test
    void testDefaultConstructor(){
        LinkedList<Integer> L = new LinkedList<>();
        assertEquals(0, L.getLength());
    }

    @Test
    void testCopyConstructor(){
        LinkedList<Integer> nullList = null;
        LinkedList<Integer> copyList = new LinkedList<>(nullList);
        assertEquals(0, copyList.getLength());
        LinkedList<Integer> emptyList = new LinkedList<>();
        LinkedList<Integer> copyList2 = new LinkedList<>(emptyList);
        assertEquals(0, copyList2.getLength());
        LinkedList<Integer> toCopy = new LinkedList<>();
        toCopy.addLast(1);
        toCopy.addLast(2);
        LinkedList<Integer> copyList3 = new LinkedList<>(toCopy);
        assertEquals(2, copyList3.getLength());
    }

    @Test
    void testCopyArrayConstructor(){
        String[] arr = {"A", "B", "C"};
        LinkedList<String> L = new LinkedList<>(arr);
        L.positionIterator();
        L.advanceIterator();
        assertEquals("A", L.getFirst());
        assertEquals("B", L.getIterator());
        assertEquals("C", L.getLast());

    }

    @Test
    void testGetFirst() {
        LinkedList<String> L = new LinkedList<>();
        assertThrows(NoSuchElementException.class, L::getFirst);
        L.addFirst("A");
        assertEquals("A", L.getFirst());
        L.addFirst("B");
        assertEquals("B", L.getFirst());
        L.addFirst("C");
        assertEquals("C", L.getFirst());
    }

    @Test
    void testGetLast() {
        LinkedList<String> L = new LinkedList<>();
        assertThrows(NoSuchElementException.class, L::getLast);
        L.addFirst("A");
        assertEquals("A", L.getLast());
        L.addLast("B");
        assertEquals("B", L.getLast());
        L.positionIterator();
        L.addIterator("C");
        assertEquals("B", L.getLast());
    }

    @Test
    void testGetLength() {
        LinkedList<String> L = new LinkedList<>();
        assertEquals(0, L.getLength());
        L.addFirst("A");
        L.addFirst("B");
        L.addFirst("C");
        assertEquals(3, L.getLength());
    }

    @Test
    void testIsEmpty() {
        LinkedList<String> L = new LinkedList<>();
        assertTrue(L.isEmpty());
        L.addFirst("A");
        assertFalse(L.isEmpty());
    }

    @Test
    void testAddFirst() {
        LinkedList<String> L = new LinkedList<>();
        L.addFirst("A");
        assertEquals("A", L.getFirst());
        L.addFirst("B");
        assertEquals("B", L.getFirst());
        L.addFirst("C");
        assertEquals("C", L.getFirst());
    }

    @Test
    void testAddLast() {
        LinkedList<String> L = new LinkedList<>();
        L.addLast("A");
        assertEquals("A", L.getLast());
        L.addLast("B");
        assertEquals("B", L.getLast());
        L.addLast("C");
        assertEquals("C", L.getLast());
    }

    @Test
    void testRemoveFirst() {
        LinkedList<String> L = new LinkedList<>();
        L.addLast("A");
        L.addLast("B");
        L.addLast("C");
        L.addLast("D");
        L.removeFirst();
        assertEquals("B", L.getFirst());
        L.removeFirst();
        assertEquals("C", L.getFirst());
        L.removeFirst();
        assertEquals("D", L.getFirst());
    }

    @Test
    void testRemoveLast() {
        LinkedList<String> L = new LinkedList<>();
        L.addLast("A");
        L.addLast("B");
        L.addLast("C");
        L.addLast("D");
        L.removeLast();
        assertEquals("C", L.getLast());
        L.removeLast();
        assertEquals("B", L.getLast());
        L.removeLast();
        assertEquals("A", L.getLast());
    }

    @Test
    void testToString() {
        LinkedList<String> L = new LinkedList<>();
        assertEquals("\n", L.toString());
        L.addFirst("A");
        assertEquals("A \n", L.toString());
        L.addLast("B");
        assertEquals("A B \n", L.toString());
    }

    @Test
    void testPositionIterator(){
        LinkedList<String> L = new LinkedList<>();
        L.addFirst("A");
        L.addLast("B");
        L.positionIterator();
        assertEquals("A", L.getIterator());
        L.advanceIterator();
        L.positionIterator();
        assertEquals("A", L.getIterator());
        L.removeIterator();
        L.positionIterator();
        assertEquals("B", L.getIterator());
    }

    @Test
    void testGetIterator(){
        LinkedList<String> L = new LinkedList<>();
        L.addFirst("A");
        L.addLast("B");
        L.addLast("C");
        assertThrows(NullPointerException.class, L::getIterator);
        L.positionIterator();
        assertEquals("A", L.getIterator());
        L.advanceIterator();
        L.positionIterator();
        assertEquals("A", L.getIterator());
        L.removeFirst();
        assertEquals("B", L.getIterator());
    }

    @Test
    void testOffEnd(){
        LinkedList<String> L = new LinkedList<>();
        L.addFirst("A");
        assertTrue(L.offEnd());
        L.positionIterator();
        assertFalse(L.offEnd());
        L.advanceIterator();
        assertTrue(L.offEnd());
        L.positionIterator();
        L.removeIterator();
        assertTrue(L.offEnd());
    }

    @Test
    void testAdvanceIterator(){
        LinkedList<String> L = new LinkedList<>();
        L.addLast("A");
        L.addLast("B");
        L.addLast("C");
        L.positionIterator();
        L.advanceIterator();
        assertEquals("B", L.getIterator());
        L.advanceIterator();
        assertEquals("C", L.getIterator());
        L.advanceIterator();
        assertThrows(NullPointerException.class, L::getIterator);
    }

    @Test
    void testReverseIterator(){
        LinkedList<String> L = new LinkedList<>();
        L.addLast("A");
        L.addLast("B");
        L.addLast("C");
        L.positionIterator();
        L.advanceIterator();
        L.advanceIterator();
        L.reverseIterator();
        assertEquals("B", L.getIterator());
        L.reverseIterator();
        assertEquals("A", L.getIterator());
        L.reverseIterator();
        assertThrows(NullPointerException.class, L::getIterator);
    }

    @Test
    void testAddIterator(){
        LinkedList<String> L = new LinkedList<>();
        assertThrows(NullPointerException.class, ()->L.addIterator("A"));
        L.addFirst("A");
        L.positionIterator();
        L.addIterator("B");
        L.advanceIterator();
        L.addIterator("C");
        L.positionIterator();
        L.advanceIterator();
        assertEquals("B", L.getIterator());
        L.advanceIterator();
        assertEquals("C", L.getIterator());
    }

    @Test
    void testRemoveIterator(){
        LinkedList<String> L = new LinkedList<>();
        L.addLast("A");
        L.addLast("B");
        L.addLast("C");
        L.addLast("D");
        L.positionIterator();
        L.advanceIterator();
        L.advanceIterator();
        L.advanceIterator();
        L.removeIterator();
        L.positionIterator();
        L.advanceIterator();
        L.advanceIterator();
        assertEquals("C", L.getIterator());
        L.positionIterator();
        L.removeIterator();
        L.positionIterator();
        assertEquals("B", L.getIterator());
        L.removeIterator();
        L.positionIterator();
        L.removeIterator();
        assertThrows(NullPointerException.class, L::removeIterator);
    }

    @Test
    void testClear(){
        LinkedList<Integer> L = new LinkedList<>();
        L.addLast(1);
        L.addLast(2);
        L.addLast(3);
        L.clear();
        assertEquals(0, L.getLength());
        assertThrows(NoSuchElementException.class, L::getFirst);
        L.addLast(1);
        assertEquals(1, L.getFirst());
    }

    @Test
    void testEquals(){
        LinkedList<Integer> main = new LinkedList<>();
        LinkedList<Integer> same = new LinkedList<>();
        assertTrue(main.equals(main));
        assertFalse(main.equals(2));
        assertTrue(main.equals(same));
    }

    @Test
    void testSpinList(){
        LinkedList<Integer> L = new LinkedList<>();
        L.addLast(1);
        L.addLast(2);
        L.addLast(3);
        L.addLast(4);
        L.spinList(3);
        assertEquals("2 3 4 1 \n", L.toString());
        L.clear();
        L.addLast(2);
        L.addLast(3);
        L.addLast(4);
        L.addLast(1);
        L.spinList(5);
        assertEquals("1 2 3 4 \n", L.toString());
    }

    @Test
    void testAltLists(){
        LinkedList<Integer> L1 = new LinkedList<>();
        LinkedList<Integer> L2 = new LinkedList<>();
        LinkedList<Integer> empty = new LinkedList<>();
        L1.addLast(1);
        L1.addLast(2);
        L1.addLast(3);
        L2.addLast(4);
        L2.addLast(5);
        L2.addLast(6);
        assertEquals("1 4 2 5 3 6 \n", L1.altLists(L2).toString());
        assertEquals("1 2 3 \n", L1.altLists(empty).toString());
        assertEquals("1 2 3 \n", L1.altLists(null).toString());
    }

    @Test
    void testFindIndex(){
        LinkedList<Integer> L1 = new LinkedList<>();
        L1.addLast(1);
        L1.addLast(2);
        L1.addLast(3);
        assertEquals(-1, L1.findIndex(100));
        assertEquals(0, L1.findIndex(1));
        assertEquals(2, L1.findIndex(3));
    }

    @Test
    void testAdvanceIteratorToIndex() {
        LinkedList<Integer> L1 = new LinkedList<>();
        L1.addLast(1);
        L1.addLast(2);
        L1.addLast(3);
        assertThrows(IndexOutOfBoundsException.class,
                () -> L1.advanceIteratorToIndex(100));
        L1.advanceIteratorToIndex(0);
        assertEquals(1, L1.getIterator());
        L1.advanceIteratorToIndex(2);
        assertEquals(3, L1.getIterator());
    }
}