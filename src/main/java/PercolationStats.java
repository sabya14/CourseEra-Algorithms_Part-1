import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;


public class PercolationStats {

    private int trials;
    private double[] openedSitesCount;
    private double mean;
    private double stdDev;
    public PercolationStats(int size, int trials) {
        if (size <=0 || trials <=0) {
            throw new IllegalArgumentException("Invalid args");
        }
        this.trials = trials;
        mean = 0;
        stdDev = 0;
        this.openedSitesCount = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(size);
            while (!percolation.percolates()) {
                int randX = StdRandom.uniform(1, size + 1);
                int randY = StdRandom.uniform(1, size + 1);
                percolation.open(randX, randY);
            }
            openedSitesCount[i] = (double) (percolation.numberOfOpenSites()) / (double) (size * size);
        }
    }

    public double mean() {
        if (mean == 0) {
            mean = StdStats.mean(this.openedSitesCount);
        }
        return mean;
    }

    public double stddev() {
        if (stdDev == 0) {
            stdDev = StdStats.stddev(this.openedSitesCount);
        }
        return stdDev;
    }

    public double confidenceLo() {
        return (this.mean() - ((1.96 * stddev()) / Math.sqrt(trials)));
    }

    public double confidenceHi() { return (this.mean() + ((1.96 * stddev()) / Math.sqrt(trials))); }

    public static void main(String[] args) {
        int size = 20;
        int trials = 10;
        PercolationStats percolationStats = new PercolationStats(size, trials);
        System.out.println("mean          = " + percolationStats.mean());
        System.out.println("stddev          = " + percolationStats.stddev());
        System.out.println("95% confidence interval          = [" + percolationStats.confidenceLo() + ", " + percolationStats.confidenceHi() + "]");
    }
}
