public class MergeSort {
    private static final int CUTOFF = 15;

    public static void sort(int[] a, Metrics metrics) {
        if (a == null || a.length <= 1) return;

        int[] temp = new int[a.length];
        sort(a, temp, 0, a.length - 1, metrics);
    }

    private static void sort(int[] a, int[] temp, int low, int high, Metrics metrics) {

        if (high - low + 1 <= CUTOFF) {
            insertionSort(a, low, high, metrics);
            return;
        }

        metrics.enterRecursion();
        int mid = low + (high - low) / 2;

        sort(a, temp, low, mid, metrics);
        sort(a, temp, mid + 1, high, metrics);

        merge(a, temp, low, mid, high, metrics);
        metrics.exitRecursion();
    }



}