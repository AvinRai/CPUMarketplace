/**
 * HashTableTest.java
 * @author Avin Rai
 * Team 1 Final Project
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class HashTableTest {
    private final int SIZE = 10;

    @Test
    void testDefaultHashTable() {
        HashTable<CPU> cpus = new HashTable<>(SIZE);
        assertEquals(0, cpus.getNumElements());
        cpus.add(new CPU("i9-14900K", "Intel", 3.2, 24, 48, 544.54, 2));
        assertEquals(1, cpus.getNumElements());
        assertEquals(0.1, cpus.getLoadFactor());
        assertThrows(IllegalArgumentException.class, () -> {
            new HashTable<String>(-1);
        });
    }

    @Test
    void testHashTableTArrayInt() {
        CPU[] array = null;
        HashTable<CPU> ht = new HashTable<>(array, SIZE);
        assertEquals(0, ht.getNumElements());
        array = new CPU[]{new CPU(),
                new CPU("i9-14900K", "Intel", 3.2, 24, 48, 544.54, 2),
                new CPU()};
        ht = new HashTable<>(array, 10);
        assertEquals(3, ht.getNumElements());
        assertEquals(0.3, ht.getLoadFactor());
        assertEquals(4, ht.find(new CPU("i9-14900K", "Intel", 3.2, 24, 48, 544.54, 2)));
        assertEquals("Name: i9-14900K\n" +
                "Brand: Intel\n" +
                "Clock Speed: 3.2\n" +
                "Cores: 24\n" +
                "Threads: 48\n" +
                "Price: $544.54\n" +
                "Stock: 2\n \n", ht.bucketToString(4));
        String[] array2 = {"a", "b", "c"};
        assertThrows(IllegalArgumentException.class, () -> {
            new HashTable<String>(array2, -1);
        });
    }

    @Test
    void testCountBucket() {
        HashTable<CPU> ht = new HashTable<>(new CPU[]{new CPU(),
                new CPU(), new CPU(), new CPU()}, SIZE);
        assertEquals(4, ht.countBucket(0));

        assertThrows(IndexOutOfBoundsException.class, () -> {
            ht.countBucket(-1);
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            ht.countBucket(10);
        });
    }

    @Test
    void testGetNumElements() {
        HashTable<CPU> empty = new HashTable<>(SIZE);
        assertEquals(0, empty.getNumElements());
        HashTable<CPU> ht = new HashTable<>(new CPU[]{new CPU(),
                new CPU(), new CPU(), new CPU()}, SIZE);
        assertEquals(4, ht.getNumElements());
    }

    @Test
    void testFind() {
        HashTable<CPU> empty = new HashTable<>(SIZE);
        assertEquals(-1, empty.find(new CPU("i9-14900K", "Intel", 3.2, 24, 48, 544.54, 2)));
        HashTable<CPU> ht = new HashTable<>(new CPU[]{new CPU ("i9-14900K", "Intel", 3.2, 24, 48, 544.54, 2),
                new CPU("Ryzen 5 7800X3D", "AMD", 4.2, 8, 16, 349.00, 4),
                new CPU("i7-14700K", "Intel", 3.4, 20, 40, 389.99, 7),
                new CPU("Ryzen 7 7700X", "AMD", 4.5, 8, 16, 288.60, 3)}, SIZE);
        assertEquals(9, ht.find(new CPU("Ryzen 7 7700X", "AMD", 4.5, 8, 16, 288.60, 3)));
        assertEquals(-1, ht.find(new CPU("N/A", "N/A", 0, 0, 0, 0, 0)));
        CPU noCPU = null;
        assertThrows(NullPointerException.class, () -> {
            ht.find(noCPU);
        });
    }

    @Test
    void testContains() {
        HashTable<CPU> empty = new HashTable<>(SIZE);
        assertFalse(empty.contains(new CPU("i9-14900K", "Intel", 3.2, 24, 48, 544.54, 2)));
        HashTable<CPU> ht = new HashTable<>(new CPU[]{new CPU("i9-14900K", "Intel", 3.2, 24, 48, 544.54, 2),
                new CPU("Ryzen 5 7800X3D", "AMD", 4.2, 8, 16, 349.00, 4),
                new CPU("i7-14700K", "Intel", 3.4, 20, 40, 389.99, 7),
                new CPU("Ryzen 7 7700X", "AMD", 4.5, 8, 16, 288.60, 3)}, SIZE);
        assertTrue(ht.contains(new CPU("i9-14900K", "Intel", 3.2, 24, 48, 544.54, 2)));
        assertFalse(ht.contains(new CPU("N/A", "N/A", 0, 0, 0, 0, 0)));
        CPU noCPU = null;
        assertThrows(NullPointerException.class, () -> {
            ht.contains(noCPU);
        });
    }

    @Test
    void testAdd() {
        HashTable<CPU> empty = new HashTable<>(SIZE);
        empty.add(new CPU("i9-14900K", "Intel", 3.2, 24, 48, 544.54, 2));
        assertEquals(1, empty.getNumElements());
        assertTrue(empty.contains(new CPU("i9-14900K", "Intel", 3.2, 24, 48, 544.54, 2)));
        empty.add(new CPU("Ryzen 5 7800X3D", "AMD", 4.2, 8, 16, 349.00, 4));
        assertEquals(2, empty.getNumElements());
        assertEquals("Name: Ryzen 5 7800X3D\n" +
                "Brand: AMD\n" +
                "Clock Speed: 4.2\n" +
                "Cores: 8\n" +
                "Threads: 16\n" +
                "Price: $349.00\n" +
                "Stock: 4\n \n", empty.bucketToString(7));
        CPU noCPU = null;
        assertThrows(NullPointerException.class, () -> {
            empty.add(noCPU);
        });
    }

    @Test
    void testDelete() {
        HashTable<CPU> ht = new HashTable<>(new CPU[]{new CPU("i9-14900K", "Intel", 3.2, 24, 48, 544.54, 2),
                new CPU("Ryzen 5 7800X3D", "AMD", 4.2, 8, 16, 349.00, 4),
                new CPU("i7-14700K", "Intel", 3.4, 20, 40, 389.99, 7),
                new CPU("Ryzen 7 7700X", "AMD", 4.5, 8, 16, 288.60, 3)}, SIZE);
        assertTrue(ht.delete(new CPU("i9-14900K", "Intel", 3.2, 24, 48, 544.54, 2)));
        assertFalse(ht.contains(new CPU("i9-14900K", "Intel", 3.2, 24, 48, 544.54, 2)));
        assertEquals(0, ht.countBucket(4));
        assertTrue(ht.delete(new CPU("Ryzen 5 7800X3D", "AMD", 4.2, 8, 16, 349.00, 4)));
        assertFalse(ht.contains(new CPU("Ryzen 5 7800X3D", "AMD", 4.2, 8, 16, 349.00, 4)));
        assertEquals(0, ht.countBucket(7));
        assertFalse(ht.delete(new CPU("i9-14900K", "Intel", 3.2, 24, 48, 544.54, 2)));

        HashTable<CPU> empty = new HashTable<>(SIZE);
        assertFalse(empty.contains(new CPU("N/A", "N/A", 0, 0, 0, 0, 0)));

        CPU noCPU = null;
        assertThrows(NullPointerException.class, () -> {
            ht.delete(noCPU);
        });
    }

    @Test
    void testClear() {
        HashTable<CPU> ht = new HashTable<>(new CPU[]{new CPU("i9-14900K", "Intel", 3.2, 24, 48, 544.54, 2),
                new CPU("Ryzen 5 7800X3D", "AMD", 4.2, 8, 16, 349.00, 4),
                new CPU("i7-14700K", "Intel", 3.4, 20, 40, 389.99, 7),
                new CPU("Ryzen 7 7700X", "AMD", 4.5, 8, 16, 288.60, 3)}, SIZE);
        ht.clear();
        for (int i = 0; i < SIZE; i++) {
            assertEquals(0, ht.countBucket(i));
        }
        assertEquals(0, ht.getNumElements());
        assertFalse(ht.contains(new CPU("Ryzen 7 7700X", "AMD", 4.5, 8, 16, 288.60, 3)));
    }

    @Test
    void testGetLoadFactor() {
        HashTable<CPU> empty = new HashTable<>(SIZE);
        assertEquals(0.0, empty.getLoadFactor());
        HashTable<CPU> ht = new HashTable<>(new CPU[]{new CPU("i9-14900K", "Intel", 3.2, 24, 48, 544.54, 2),
                new CPU("Ryzen 5 7800X3D", "AMD", 4.2, 8, 16, 349.00, 4),
                new CPU("i7-14700K", "Intel", 3.4, 20, 40, 389.99, 7),
                new CPU("Ryzen 7 7700X", "AMD", 4.5, 8, 16, 288.60, 3)}, SIZE);
        assertEquals(.4, ht.getLoadFactor());
        ht.delete(new CPU("Ryzen 7 7700X", "AMD", 4.5, 8, 16, 288.60, 3));
        assertEquals(.3, ht.getLoadFactor());
    }

    @Test
    void testBucketToString() {
        HashTable<CPU> empty = new HashTable<>(SIZE);
        assertEquals("\n", empty.bucketToString(0));
        HashTable<CPU> ht = new HashTable<>(new CPU[]{new CPU("i9-14900K", "Intel", 3.2, 24, 48, 544.54, 2),
                new CPU("Ryzen 5 7800X3D", "AMD", 4.2, 8, 16, 349.00, 4),
                new CPU("i7-14700K", "Intel", 3.4, 20, 40, 389.99, 7),
                new CPU("Ryzen 7 7700X", "AMD", 4.5, 8, 16, 288.60, 3)}, SIZE);
        assertEquals("Name: Ryzen 5 7800X3D\n" +
                "Brand: AMD\n" +
                "Clock Speed: 4.2\n" +
                "Cores: 8\n" +
                "Threads: 16\n" +
                "Price: $349.00\n" +
                "Stock: 4\n \n", ht.bucketToString(7));
        assertEquals("\n", ht.bucketToString(1));

        assertThrows(IndexOutOfBoundsException.class, () -> {
            ht.bucketToString(-1);
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            ht.bucketToString(10);
        });
    }

    @Test
    void testRowToString() {
        HashTable<CPU> empty = new HashTable<>(SIZE);
        assertEquals("Bucket 0: empty\n" +
                        "Bucket 1: empty\n" +
                        "Bucket 2: empty\n" +
                        "Bucket 3: empty\n" +
                        "Bucket 4: empty\n" +
                        "Bucket 5: empty\n" +
                        "Bucket 6: empty\n" +
                        "Bucket 7: empty\n" +
                        "Bucket 8: empty\n" +
                        "Bucket 9: empty\n",
                empty.rowToString());

        HashTable<CPU> ht = new HashTable<>(new CPU[]{new CPU("i9-14900K", "Intel", 3.2, 24, 48, 544.54, 2),
                new CPU("Ryzen 5 7800X3D", "AMD", 4.2, 8, 16, 349.00, 4),
                new CPU("i7-14700K", "Intel", 3.4, 20, 40, 389.99, 7),
                new CPU("Ryzen 7 7700X", "AMD", 4.5, 8, 16, 288.60, 3)}, SIZE);
        assertEquals("Bucket 0: Name: i7-14700K\n" +
                        "Brand: Intel\n" +
                        "Clock Speed: 3.4\n" +
                        "Cores: 20\n" +
                        "Threads: 40\n" +
                        "Price: $389.99\n" +
                        "Stock: 7\n" +
                        "\n" +
                        "Bucket 1: empty\n" +
                        "Bucket 2: empty\n" +
                        "Bucket 3: empty\n" +
                        "Bucket 4: Name: i9-14900K\n" +
                        "Brand: Intel\n" +
                        "Clock Speed: 3.2\n" +
                        "Cores: 24\n" +
                        "Threads: 48\n" +
                        "Price: $544.54\n" +
                        "Stock: 2\n" +
                        "\n" +
                        "Bucket 5: empty\n" +
                        "Bucket 6: empty\n" +
                        "Bucket 7: Name: Ryzen 5 7800X3D\n" +
                        "Brand: AMD\n" +
                        "Clock Speed: 4.2\n" +
                        "Cores: 8\n" +
                        "Threads: 16\n" +
                        "Price: $349.00\n" +
                        "Stock: 4\n" +
                        "\n" +
                        "Bucket 8: empty\n" +
                        "Bucket 9: Name: Ryzen 7 7700X\n" +
                        "Brand: AMD\n" +
                        "Clock Speed: 4.5\n" +
                        "Cores: 8\n" +
                        "Threads: 16\n" +
                        "Price: $288.60\n" +
                        "Stock: 3\n" +
                        "\n",
                ht.rowToString());
    }

    @Test
    void testToString() {
        HashTable<CPU> empty = new HashTable<>(SIZE);
        System.out.print(empty);
        assertEquals("\n", empty.toString());
        HashTable<CPU> ht = new HashTable<>(new CPU[]{new CPU("i9-14900K", "Intel", 3.2, 24, 48, 544.54, 2),
                new CPU("Ryzen 5 7800X3D", "AMD", 4.2, 8, 16, 349.00, 4),
                new CPU("i7-14700K", "Intel", 3.4, 20, 40, 389.99, 7),
                new CPU("Ryzen 7 7700X", "AMD", 4.5, 8, 16, 288.60, 3)}, SIZE);
        assertEquals("Name: i7-14700K\n" +
                "Brand: Intel\n" +
                "Clock Speed: 3.4\n" +
                "Cores: 20\n" +
                "Threads: 40\n" +
                "Price: $389.99\n" +
                "Stock: 7\n" +
                " \n" +
                "Name: i9-14900K\n" +
                "Brand: Intel\n" +
                "Clock Speed: 3.2\n" +
                "Cores: 24\n" +
                "Threads: 48\n" +
                "Price: $544.54\n" +
                "Stock: 2\n" +
                " \n" +
                "Name: Ryzen 5 7800X3D\n" +
                "Brand: AMD\n" +
                "Clock Speed: 4.2\n" +
                "Cores: 8\n" +
                "Threads: 16\n" +
                "Price: $349.00\n" +
                "Stock: 4\n" +
                " \n" +
                "Name: Ryzen 7 7700X\n" +
                "Brand: AMD\n" +
                "Clock Speed: 4.5\n" +
                "Cores: 8\n" +
                "Threads: 16\n" +
                "Price: $288.60\n" +
                "Stock: 3\n" +
                " \n" +
                "\n", ht.toString());
    }

}
