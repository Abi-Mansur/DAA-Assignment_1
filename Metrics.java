
public class Metrics {
    private long comparisons = 0;
    private int maxDepth = 0;
    private int currentDepth = 0;


    public void addComparison() {
        this.comparisons++;
    }


    public void enterRecursion() {
        this.currentDepth++;
        if (this.currentDepth > this.maxDepth) {
            this.maxDepth = this.currentDepth;
        }
    }


    public void exitRecursion() {
        this.currentDepth--;
    }

    public long getComparisons() {
        return comparisons;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public void reset() {
        this.comparisons = 0;
        this.maxDepth = 0;
        this.currentDepth = 0;
    }
}