
import java.util.Random;

public class QuickSelect {
    private static final Random random = new Random();

    public static int select(int[] a, int k, Metrics metrics) {

        if (a == null || a.length == 0 || k < 0 || k >= a.length) {
            throw new IllegalArgumentException("Invalid array or k out of range: " + k);
        }

        int low = 0, high = a.length - 1;
        while (low <= high) {
            if (low == high) return a[low];

            int pivotIndex = low + random.nextInt(high - low + 1);
            swap(a, low, pivotIndex);
            int pivot = a[low];

            int lt = low;
            int gt = high;
            int i = low + 1;


            while (i <= gt) {
                metrics.addComparison();
                if (a[i] < pivot) {
                    swap(a, lt++, i++);
                } else if (a[i] > pivot) {
                    swap(a, i, gt--);
                } else {
                    i++;
                }
            }


            if (k >= lt && k <= gt) {
                return a[k];
            } else if (k < lt) {
                high = lt - 1;
            } else {
                low = gt + 1;
            }
        }
        return a[low];
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}