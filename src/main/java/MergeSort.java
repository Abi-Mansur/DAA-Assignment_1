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


    private static void merge(int[] a, int[] temp, int low, int mid, int high, Metrics metrics) {

        for (int k = low; k <= high; k++) {
            temp[k] = a[k];
        }

        int i = low, j = mid + 1;
        for (int k = low; k <= high; k++) {
            if (i > mid) {
                a[k] = temp[j++];
            } else if (j > high) {
                a[k] = temp[i++];
            } else {
                metrics.addComparison();
                if (temp[j] < temp[i]) {
                    a[k] = temp[j++];
                } else {
                    a[k] = temp[i++];
                }
            }
        }
    }


    private static void insertionSort(int[] a, int low, int high, Metrics metrics) {
        for (int i = low + 1; i <= high; i++) {
            int key = a[i];
            int j = i - 1;
            while (j >= low) {
                metrics.addComparison();
                if (a[j] > key) {
                    a[j + 1] = a[j];
                    j--;
                } else {
                    break;
                }
            }
            a[j + 1] = key;
        }
    }
}