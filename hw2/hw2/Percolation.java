package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grids;
    private WeightedQuickUnionUF unionUF;
    private int openGrids;
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        unionUF = new WeightedQuickUnionUF(N * N + 2);
        grids = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == 0) {
                    unionUF.connected(convert(i, j), 0);
                } else if (i == N - 1) {
                    unionUF.connected(convert(i, j), N * N + 1);
                }
                grids[i][j] = false;
            }
        }
        openGrids = 0;
    }               // create N-by-N grid, with all sites initially blocked
    private boolean checkBoundary(int x) {
        return x >= 0 && x < grids.length;
    }
    public void open(int row, int col) {
        if (!checkBoundary(row) || !checkBoundary(col)) {
            throw new IndexOutOfBoundsException();
        }
        if (!isOpen(row, col)) {
            grids[row][col] = true;
            merge(row, col);
            openGrids += 1;
        }
    }    // open the site (row, col) if it is not open already
    public boolean isOpen(int row, int col) {
        if (!checkBoundary(row) || !checkBoundary(col)) {
            throw new IndexOutOfBoundsException();
        }
        return grids[row][col];
    } // is the site (row, col) open?

    private int convert(int row, int col) {
        return row * grids.length + col + 1;
    }
    private void merge(int row, int col) {
        if (checkBoundary(row - 1) && checkBoundary(col) && isOpen(row - 1, col)) {
            unionUF.union(convert(row, col), convert(row - 1, col));
        }
        if (checkBoundary(row + 1) && checkBoundary(col) && isOpen(row + 1, col)) {
            unionUF.union(convert(row, col), convert(row + 1, col));
        }
        if (checkBoundary(row) && checkBoundary(col - 1) && isOpen(row, col - 1)) {
            unionUF.union(convert(row, col), convert(row, col - 1));
        }
        if (checkBoundary(row) && checkBoundary(col + 1) && isOpen(row, col + 1)) {
            unionUF.union(convert(row, col), convert(row, col + 1));
        }
    }
    public boolean isFull(int row, int col) {
        if (!checkBoundary(row) || !checkBoundary(col)) {
            throw new IndexOutOfBoundsException();
        }
        return unionUF.connected(convert(row, col), 0);
    } // is the site (row, col) full?
    public int numberOfOpenSites() {
        return openGrids;
    }          // number of open sites
    public boolean percolates() {
        return unionUF.connected(0, grids.length * grids.length + 1);
    }             // does the system percolate?
    public static void main(String[] args) {

    }  // use for unit testing (not required)
}
