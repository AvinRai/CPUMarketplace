/**
 * BSTTest.java
 * @author Avin Rai
 * Team 1 Final Project
 */
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class BSTTest {
    @Test
    void testBST(){
        CpuNameComparator cmp = new CpuNameComparator();
        CPU[] arr = {new CPU("Ryzen 5 7800X3D", "AMD", 4.2, 8, 16, 349.00, 7),
                new CPU("i7-13700k", "Intel", 3.4, 16, 32, 370.99, 5),
                new CPU("i9-14900K", "Intel", 3.2, 24, 48, 544.54, 14)};
        BST<CPU> cpus = new BST<>();
        assertEquals(cpus.getSize(), 0);
        cpus = new BST<>(arr, cmp);
        assertEquals(cpus.getSize(), 3);
        assertEquals(cpus.getRoot(), new CPU("i7-13700k", "Intel", 3.4, 16, 32, 370.99, 5));
        BST<CPU> copied = new BST<>(cpus, cmp);
        assertEquals(copied.getRoot(), new CPU("i7-13700k", "Intel", 3.4, 16, 32, 370.99, 5));
    }

    @Test
    void testFindMin(){
        CpuNameComparator cmp = new CpuNameComparator();
        CPU[] arr = {new CPU("Ryzen 5 7800X3D", "AMD", 4.2, 8, 16, 349.00, 7),
                new CPU("i7-13700k", "Intel", 3.4, 16, 32, 370.99, 5),
                new CPU("i9-14900K", "Intel", 3.2, 24, 48, 544.54, 14)};
        BST<CPU> cpus = new BST<>(arr, cmp);
        assertEquals(cpus.findMin(), new CPU("Ryzen 5 7800X3D", "AMD", 4.2, 8, 16, 349.00, 7));
    }

    @Test
    void testFindMax(){
        CpuNameComparator cmp = new CpuNameComparator();
        CPU[] arr = {new CPU("Ryzen 5 7800X3D", "AMD", 4.2, 8, 16, 349.00, 7),
                new CPU("i7-13700k", "Intel", 3.4, 16, 32, 370.99, 5),
                new CPU("i9-14900K", "Intel", 3.2, 24, 48, 544.54, 14)};
        BST<CPU> cpus = new BST<>(arr, cmp);
        assertEquals(cpus.findMax(), new CPU("i9-14900K", "Intel", 3.2, 24, 48, 544.54, 14));
    }

    @Test
    void testSearch(){
        CpuNameComparator cmp = new CpuNameComparator();
        CPU[] arr = {new CPU("Ryzen 5 7800X3D", "AMD", 4.2, 8, 16, 349.00, 7),
                new CPU("i7-13700k", "Intel", 3.4, 16, 32, 370.99, 5),
                new CPU("i9-14900K", "Intel", 3.2, 24, 48, 544.54, 14)};
        BST<CPU> cpus = new BST<>(arr, cmp);
        assertEquals(cpus.search(new CPU("Ryzen 5 7800X3D", "AMD", 4.2, 8, 16, 349.00, 7), cmp),
                new CPU("Ryzen 5 7800X3D", "AMD", 4.2, 8, 16, 349.00, 7));
        assertEquals(cpus.search(new CPU("i9-14900K", "Intel", 3.2, 24, 48, 544.54, 14), cmp),
                new CPU("i9-14900K", "Intel", 3.2, 24, 48, 544.54, 14));
        assertNull(cpus.search(new CPU("not", "real", 1,2,4,5,6), cmp));
    }

    @Test
    void testInsert(){
        CpuNameComparator cmp = new CpuNameComparator();
        BST<CPU> cpus = new BST<>();
        cpus.insert(new CPU("Ryzen 5 7800X3D", "AMD", 4.2, 8, 16, 349.00, 7), cmp);
        assertEquals(cpus.getRoot(), new CPU("Ryzen 5 7800X3D", "AMD", 4.2, 8, 16, 349.00, 7));
        assertEquals(cpus.getSize(), 1);
        cpus.insert(new CPU("i7-13700k", "Intel", 3.4, 16, 32, 370.99, 5), cmp);
        assertEquals(cpus.getHeight(), 1);
    }

    @Test
    void testRemove() {
        CpuNameComparator cmp = new CpuNameComparator();
        CPU[] arr = {new CPU("Ryzen 5 7800X3D", "AMD", 4.2, 8, 16, 349.00, 7),
                new CPU("i7-13700k", "Intel", 3.4, 16, 32, 370.99, 5),
                new CPU("i9-14900K", "Intel", 3.2, 24, 48, 544.54, 14)};
        BST<CPU> cpus = new BST<>(arr, cmp);
        cpus.remove(new CPU("Ryzen 5 7800X3D", "AMD", 4.2, 8, 16, 349.00, 7), cmp);
        assertEquals(cpus.getSize(), 2);
        assertEquals(cpus.getRoot(), new CPU("i7-13700k", "Intel", 3.4, 16, 32, 370.99, 5));
        cpus.remove(new CPU("i7-13700k", "Intel", 3.4, 16, 32, 370.99, 5), cmp);
        assertEquals(cpus.getRoot(), new CPU("i9-14900K", "Intel", 3.2, 24, 48, 544.54, 14));
    }

    @Test
    void testPreOrderString() {
        CpuNameComparator cmp = new CpuNameComparator();
        CPU[] arr = {new CPU("Ryzen 5 7800X3D", "AMD", 4.2, 8, 16, 349.00, 7),
                new CPU("i7-13700k", "Intel", 3.4, 16, 32, 370.99, 5),
                new CPU("i9-14900K", "Intel", 3.2, 24, 48, 544.54, 14)};
        BST<CPU> cpus = new BST<>(arr, cmp);
        String result = new CPU("i7-13700k", "Intel", 3.4, 16, 32, 370.99, 5).toString() +
                new CPU("Ryzen 5 7800X3D", "AMD", 4.2, 8, 16, 349.00, 7).toString() +
                new CPU("i9-14900K", "Intel", 3.2, 24, 48, 544.54, 14).toString() + "\n";
        assertEquals(cpus.preOrderString(), result);
    }

    @Test
    void testInOrderString() {
        CpuNameComparator cmp = new CpuNameComparator();
        CPU[] arr = {new CPU("Ryzen 5 7800X3D", "AMD", 4.2, 8, 16, 349.00, 7),
                new CPU("i7-13700k", "Intel", 3.4, 16, 32, 370.99, 5),
                new CPU("i9-14900K", "Intel", 3.2, 24, 48, 544.54, 14)};
        BST<CPU> cpus = new BST<>(arr, cmp);
        String result = new CPU("Ryzen 5 7800X3D", "AMD", 4.2, 8, 16, 349.00, 7).toString() +
                new CPU("i7-13700k", "Intel", 3.4, 16, 32, 370.99, 5).toString() +
                new CPU("i9-14900K", "Intel", 3.2, 24, 48, 544.54, 14).toString() + "\n";
        assertEquals(cpus.inOrderString(), result);
    }

    @Test
    void testPostOrderString() {
        CpuNameComparator cmp = new CpuNameComparator();
        CPU[] arr = {new CPU("Ryzen 5 7800X3D", "AMD", 4.2, 8, 16, 349.00, 7),
                new CPU("i7-13700k", "Intel", 3.4, 16, 32, 370.99, 5),
                new CPU("i9-14900K", "Intel", 3.2, 24, 48, 544.54, 14)};
        BST<CPU> cpus = new BST<>(arr, cmp);
        String result = new CPU("Ryzen 5 7800X3D", "AMD", 4.2, 8, 16, 349.00, 7).toString() +
                new CPU("i9-14900K", "Intel", 3.2, 24, 48, 544.54, 14).toString() +
                new CPU("i7-13700k", "Intel", 3.4, 16, 32, 370.99, 5).toString() + "\n";
        assertEquals(cpus.postOrderString(), result);
    }


}
