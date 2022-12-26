package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {

    private double[] history;
    private Percolation percolation;

    private int times;
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        history = new double[T];
        times = T;
        for (int i = 0; i < T; i++) {
            percolation = pf.make(N);
            while (!percolation.percolates()) {
                int place = chooseASite(N);
                int row = (place - 1) / N;
                int col = (place - 1) % N;
                percolation.open(row, col);
            }
            history[i] = percolation.numberOfOpenSites() / (double) (N * N);
        }

    }  // perform T independent experiments on an N-by-N grid

    private int chooseASite(int N) {
        int number = StdRandom.uniform(1, N * N + 1);
        int row = (number - 1) / N;
        int col = (number - 1) % N;
        while (percolation.isOpen(row, col)) {
            number = StdRandom.uniform(1, N * N + 1);
            row = (number - 1) / N;
            col = (number - 1) % N;
        }
        return number;
    }
    public double mean() {
        return StdStats.mean(history);
    }                                          // sample mean of percolation threshold
    public double stddev() {
        return StdStats.stddev(history);
    }                                        // sample standard deviation of percolation threshold
    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(times);
    }                                 // low endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(times);
    }                                // high endpoint of 95% confidence interval
}
