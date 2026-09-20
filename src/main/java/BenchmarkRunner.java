import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;

public class BenchmarkRunner {
    private static final int[] SIZES = {1000, 10000, 100000, 1000000}; //
    private static final String[] TYPES = {"random", "sorted", "duplicates"}; //
    private static final int RUNS = 5; //

    public static void main(String[] args) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("results.csv"))) {
            writer.println("algorithm,input,n,time_ms,comparisons,max_depth"); //

            for (int n : SIZES) {
                for (String type : TYPES) {
                    runBenchmark("MergeSort", type, n, writer);
                    runBenchmark("QuickSort", type, n, writer);
                    runBenchmark("QuickSelect", type, n, writer);
                }
            }
            System.out.println("Benchmark completed successfully! Check results.csv in project root.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void runBenchmark(String algo, String type, int n, PrintWriter writer) {
        long[] times = new long[RUNS];
        long[] comps = new long[RUNS];
        int[] depths = new int[RUNS];

        for (int r = 0; r < RUNS; r++) {
            int[] arr = generateArray(n, type);
            Metrics metrics = new Metrics();
            int k = n / 2;

            long startTime = System.nanoTime();
            if (algo.equals("MergeSort")) {
                MergeSort.sort(arr, metrics);
            } else if (algo.equals("QuickSort")) {
                QuickSort.sort(arr, metrics);
            } else if (algo.equals("QuickSelect")) {
                QuickSelect.select(arr, k, metrics);
            }
            long endTime = System.nanoTime();

            times[r] = (endTime - startTime) / 1_000_000; // ms
            comps[r] = metrics.getComparisons();
            depths[r] = metrics.getMaxDepth();
        }


        Integer[] indices = {0, 1, 2, 3, 4};
        Arrays.sort(indices, (i1, i2) -> Long.compare(times[i1], times[i2]));
        int medianIdx = indices[RUNS / 2];

        writer.printf("%s,%s,%d,%d,%d,%d\n",
                algo, type, n, times[medianIdx], comps[medianIdx], depths[medianIdx]);
    }

    private static int[] generateArray(int n, String type) {
        Random rand = new Random(42 + n);
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            if (type.equals("random")) {
                arr[i] = rand.nextInt();
            } else if (type.equals("sorted")) {
                arr[i] = i;
            } else if (type.equals("duplicates")) {
                arr[i] = rand.nextInt(10);
            }
        }
        return arr;
    }
}