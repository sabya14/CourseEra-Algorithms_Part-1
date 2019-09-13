import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {


    private boolean[][] matrix;
    private int n;
    private int openSitesCount = 0;
    private WeightedQuickUnionUF weightedQuickUnionUF;
    private WeightedQuickUnionUF weightedQuickUnionFull;

    public Percolation(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size must be greater than zero");
        }
        n = size;
        weightedQuickUnionUF = new WeightedQuickUnionUF((size * size) + 2);
        weightedQuickUnionFull = new WeightedQuickUnionUF((size * size) + 1);
        matrix = new boolean[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = false;
            }
        }
    }

    boolean[][] getMatrix() {
        return matrix;
    }

    int convert2dTo1d(int x, int y) {
        return ((x - 1) * n + y);
    }

    public void open(int x, int y) {
        checkBound(x, y);
        if (!isOpen(x, y)) {
            matrix[x - 1][y - 1] = true;
            openSitesCount++;
            int[] openSites1DCoOrdinates = this.findAdjacentOpenSites(x, y);
            for (int openSites1DCoOrdinate : openSites1DCoOrdinates) {
                if(openSites1DCoOrdinate != 0) {
                    weightedQuickUnionUF.union(convert2dTo1d(x, y), openSites1DCoOrdinate);
                    weightedQuickUnionFull.union(convert2dTo1d(x, y), openSites1DCoOrdinate);

                }
            }

        }
        if (x == 1) {
            weightedQuickUnionUF.union(0, convert2dTo1d(x, y));
            weightedQuickUnionFull.union(0, convert2dTo1d(x, y));
        }
        if (x == n) {
            weightedQuickUnionUF.union((n * n) + 1, convert2dTo1d(x, y));
        }
    }

    public int numberOfOpenSites() {
        return openSitesCount;
    }

    public boolean isFull(int x, int y) {
        checkBound(x, y);
        return isOpen(x, y) && (weightedQuickUnionFull.connected(0, convert2dTo1d(x, y)));
    }

    public boolean isOpen(int x, int y) {
        checkBound(x, y);
        return matrix[x - 1][y - 1];
    }

    public boolean percolates() {
        return weightedQuickUnionUF.connected(0, (n * n) + 1);
    }

    private void checkBound(int i, int j) {
        if (i < 1 || i > n) {
            throw new IllegalArgumentException("row index i out of bounds");
        }
        if (j < 1 || j > n) {
            throw new IllegalArgumentException("row index j out of bounds");
        }
    }

    int[] findAdjacentOpenSites(int x, int y) {
        int[] openSites1DCoOrdinates = new int[4];
        int counter = 0;
        int [][]checkPosition = {{0, 1}, {0, -1}, {1, 0}, {-1,0}};
        for (int [] position:checkPosition) {
            try {
                if (isOpen(x + position[0], y + position[1])) {
                    openSites1DCoOrdinates[counter] = convert2dTo1d(x + position[0], y + position[1]);
                    counter++;
                }
            } catch (IllegalArgumentException E) {
                continue;
            }
        }
        return openSites1DCoOrdinates;
    }
}
