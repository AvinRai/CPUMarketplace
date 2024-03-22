/**
 * BST.java
 * @author Avin Rai
 * Team 1 Final Project
 */
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class HeapTest {
    @Test
    void buildHeap() {
        cpuNameComparator nameCMP = new cpuNameComparator();
        ArrayList<CPU> data = new ArrayList<>();
        data.add(new CPU("i9-14900K", "Intel", 3.2, 24, 48, 544.54, 14));
        data.add(new CPU("i7-13700k", "Intel", 3.4, 16, 32, 370.99, 5));
        data.add(new CPU("Ryzen 5 7800X3D", "AMD", 4.2, 8, 16, 349.00, 7));
        Heap<CPU> CPUs = new Heap<>(data, nameCMP);
        assertEquals(CPUs.getHeapSize(), 3);
        assertEquals(CPUs.getMax(), new CPU("i9-14900K", "Intel", 3.2, 24, 48, 544.54, 14));
        assertEquals(CPUs.getElement(3), new CPU("Ryzen 5 7800X3D", "AMD", 4.2, 8, 16, 349.00, 7));
    }

    @Test
    void insert() {
        cpuNameComparator nameCMP = new cpuNameComparator();
        ArrayList<CPU> data = new ArrayList<>();
        data.add(new CPU("i9-14900K", "Intel", 3.2, 24, 48, 544.54, 14));
        data.add(new CPU("i7-13700k", "Intel", 3.4, 16, 32, 370.99, 5));
        data.add(new CPU("Ryzen 5 7800X3D", "AMD", 4.2, 8, 16, 349.00, 7));
        Heap<CPU> CPUs = new Heap<>(data, nameCMP);
        CPUs.insert(new CPU("Ryzen 5 7600X", "AMD", 4.7, 6, 12, 226.00, 3));
        assertEquals(CPUs.getHeapSize(), 4);
        assertEquals(CPUs.getElement(3), new CPU("Ryzen 5 7800X3D", "AMD", 4.2, 8, 16, 349.00, 7));
        assertEquals(CPUs.getElement(CPUs.getLeft(2)), new CPU("Ryzen 5 7600X", "AMD", 4.7, 6, 12, 226.00, 3));
    }

    @Test
    void remove() {
        cpuNameComparator nameCMP = new cpuNameComparator();
        ArrayList<CPU> data = new ArrayList<>();
        data.add(new CPU("i9-14900K", "Intel", 3.2, 24, 48, 544.54, 14));
        data.add(new CPU("i7-13700k", "Intel", 3.4, 16, 32, 370.99, 5));
        data.add(new CPU("Ryzen 5 7800X3D", "AMD", 4.2, 8, 16, 349.00, 7));
        Heap<CPU> CPUs = new Heap<>(data, nameCMP);
        CPUs.remove(3);
        assertEquals(CPUs.getHeapSize(), 2);
        assertEquals(CPUs.getElement(2), new CPU("i7-13700k", "Intel", 3.4, 16, 32, 370.99, 5));
        CPUs.remove(2);
        assertEquals(CPUs.getHeapSize(), 1);
        assertEquals(CPUs.getElement(1), new CPU("i9-14900K", "Intel", 3.2, 24, 48, 544.54, 14));
    }

    @Test
    void testToString() {
        cpuNameComparator nameCMP = new cpuNameComparator();
        ArrayList<CPU> data = new ArrayList<>();
        data.add(new CPU("i9-14900K", "Intel", 3.2, 24, 48, 544.54, 14));
        data.add(new CPU("i7-13700k", "Intel", 3.4, 16, 32, 370.99, 5));
        data.add(new CPU("Ryzen 5 7800X3D", "AMD", 4.2, 8, 16, 349.00, 7));
        Heap<CPU> CPUs = new Heap<>(data, nameCMP);
        assertEquals(CPUs.toString(), "Name: i9-14900K\n" +
                "Brand: Intel\n" +
                "Clock Speed: 3.2\n" +
                "Cores: 24\n" +
                "Threads: 48\n" +
                "Price: $544.54\n" +
                "Stock: 14\n" +
                ", Name: i7-13700k\n" +
                "Brand: Intel\n" +
                "Clock Speed: 3.4\n" +
                "Cores: 16\n" +
                "Threads: 32\n" +
                "Price: $370.99\n" +
                "Stock: 5\n" +
                ", Name: Ryzen 5 7800X3D\n" +
                "Brand: AMD\n" +
                "Clock Speed: 4.2\n" +
                "Cores: 8\n" +
                "Threads: 16\n" +
                "Price: $349.00\n" +
                "Stock: 7\n");
    }

    @Test
    void testSort() {
        ArrayList<CPU> equal = new ArrayList<>();
        equal.add(new CPU("Ryzen 5 7800X3D", "AMD", 4.2, 8, 16, 349.00, 7));
        equal.add(new CPU("i7-13700k", "Intel", 3.4, 16, 32, 370.99, 5));
        equal.add(new CPU("i9-14900K", "Intel", 3.2, 24, 48, 544.54, 14));
        cpuNameComparator nameCMP = new cpuNameComparator();
        ArrayList<CPU> data = new ArrayList<>();
        data.add(new CPU("i9-14900K", "Intel", 3.2, 24, 48, 544.54, 14));
        data.add(new CPU("i7-13700k", "Intel", 3.4, 16, 32, 370.99, 5));
        data.add(new CPU("Ryzen 5 7800X3D", "AMD", 4.2, 8, 16, 349.00, 7));
        Heap<CPU> CPUs = new Heap<>(data, nameCMP);
        ArrayList<CPU> testSort = CPUs.sort();
        assertEquals(testSort, equal);
    }
}