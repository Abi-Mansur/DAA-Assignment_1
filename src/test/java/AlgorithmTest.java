import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class AlgorithmTest {

    @Test
    void testMergeSortCorrectness() {
        Random rand = new Random();
        for (int i = 0; i < 100; i++) {
            int[] arr = rand.ints(1000, -10000, 10000).toArray();
            int[] expected = arr.clone();
            Arrays.sort(expected);

            Metrics metrics = new Metrics();
            MergeSort.sort(arr, metrics);

            assertArrayEquals(expected, arr);
        }
    }

    @Test
    void testQuickSortCorrectness() {
        Random rand = new Random();
        for (int i = 0; i < 100; i++) {
            int[] arr = rand.ints(1000, -10000, 10000).toArray();
            int[] expected = arr.clone();
            Arrays.sort(expected);

            Metrics metrics = new Metrics();
            QuickSort.sort(arr, metrics);

            assertArrayEquals(expected, arr);
        }
    }

    @Test
    void testQuickSelectCorrectness() {
        Random rand = new Random();
        for (int i = 0; i < 100; i++) {
            int[] arr = rand.ints(1000, -10000, 10000).toArray();
            int k = rand.nextInt(arr.length);

            int[] sorted = arr.clone();
            Arrays.sort(sorted);
            int expected = sorted[k];

            Metrics metrics = new Metrics();
            int actual = QuickSelect.select(arr, k, metrics);

            assertEquals(expected, actual);
        }
    }

    @Test
    void testEdgeCases() {
        Metrics metrics = new Metrics();

        int[] empty = {};
        MergeSort.sort(empty, metrics);
        QuickSort.sort(empty, metrics);


        int[] single = {42};
        MergeSort.sort(single, metrics);
        QuickSort.sort(single, metrics);
        assertEquals(42, single[0]);


        assertThrows(IllegalArgumentException.class, () -> QuickSelect.select(empty, 0, metrics));
        assertThrows(IllegalArgumentException.class, () -> QuickSelect.select(single, 5, metrics));


        int[] duplicates = {7, 7, 7, 7, 7};
        QuickSort.sort(duplicates, metrics);
        assertArrayEquals(new int[]{7, 7, 7, 7, 7}, duplicates);
    }

    @Test
    void testQuickSortDepthOnSortedArray() {
        int n = 100000;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = i;
        Metrics metrics = new Metrics();
        QuickSort.sort(arr, metrics);


        double maxAllowedDepth = 2 * (Math.log(n) / Math.log(2));
        assertTrue(metrics.getMaxDepth() <= maxAllowedDepth,
                "Max depth " + metrics.getMaxDepth() + " exceeded limit " + maxAllowedDepth);
    }
}