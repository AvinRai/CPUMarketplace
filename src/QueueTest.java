/**
 * QueueTest.java
 * @author Avin Rai
 * Team 1 Final Project
 */
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;

class QueueTest {
    private  Queue<String> q1;
    private Queue<Integer> q2;
    private Queue<Double> q3;

    void setUp() {
        q1 = new Queue<>();
        for(int i = 'A'; i < 'M'; i++) {
            q1.enqueue("" + (char) i);
        }
        q2 = new Queue<>();
        q3 = new Queue<>(new Double[]{1.1, 2.2, 3.3, 4.4});
    }

    @Test
    void testQueue() {
        setUp();
        assertThrows(NoSuchElementException.class, ()->{q2.getFront();});
        assertTrue(q2.isEmpty());

    }

    @Test
    void testQueueTArray() {
        setUp();
        assertEquals(1.1, q3.getFront());
        assertEquals("1.1 2.2 3.3 4.4 \n", q3.toString());
        assertEquals(4, q3.getSize());
        String[] array = null;
        assertEquals(0, new Queue<String>(array).getSize());
    }

    @Test
    void testQueueQueueOfT() {
        setUp();
        Queue<String> nullQ = null;
        Queue<String> copy = new Queue<>(nullQ);
        assertEquals(0, copy.getSize());
        copy = new Queue<>(q1);
        assertEquals("A", copy.getFront());
        assertEquals(q1.toString(), copy.toString());
        //checking for deep copy
        copy.enqueue("D");
        assertNotEquals(copy.getSize(),q1.getSize());
    }

    @Test
    void testGetFront() {
        setUp();
        assertThrows(NoSuchElementException.class, ()->{q2.getFront();});
        assertEquals("A", q1.getFront());
        assertEquals(1.1, q3.getFront());
    }

    @Test
    void testGetSize() {
        setUp();
        assertEquals(12, q1.getSize());
        assertEquals(0, q2.getSize());
        assertEquals(4, q3.getSize());
    }

    @Test
    void testIsEmpty() {
        setUp();
        assertTrue(q2.isEmpty());
        assertFalse(q1.isEmpty());
    }

    @Test
    void testEnqueue() {
        setUp();
        assertEquals("A", q1.getFront());
        assertEquals(12, q1.getSize());
        assertEquals(1.1, q3.getFront());
    }

    @Test
    void testDequeue() {
        setUp();
        assertThrows(NoSuchElementException.class, ()->{q2.dequeue();});
        q1.dequeue();
        assertEquals("B", q1.getFront());
        q3.dequeue();
        q3.dequeue();
        q3.dequeue();
        q3.dequeue();
        assertTrue(q3.isEmpty());
    }

    @Test
    void testToString() {
        setUp();
        assertEquals("A B C D E F G H I J K L \n", q1.toString());
        assertEquals("1.1 2.2 3.3 4.4 \n", q3.toString());
        assertEquals("\n", q2.toString());
    }

    @Test
    void testEqualsObject() {
        setUp();
        assertTrue(q1.equals(q1));
        assertFalse(q1.equals(new String("A B C D E F G H I J K L")));
        for(int i = 0; i < 9; i++) {
            q1.dequeue();
        }
        System.out.println(q1);
        Queue<String> test = new Queue<>();
        test.enqueue(new String("J"));
        test.enqueue(new String("K"));
        test.enqueue(new String("L"));
        assertTrue(q1.equals(test));
        test.dequeue();
        assertFalse(q1.equals(test));
        Queue<Double> test2 = new Queue<>(new Double[]{1.1, 2.2, 3.2, 4.4});
        assertFalse(test2.equals(q3));
        assertFalse(test2.equals(test));
    }
}