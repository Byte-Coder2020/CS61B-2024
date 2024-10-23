import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    // TODO: Add any necessary instance variables.
    private boolean[][] grid;
    private int N;
    private WeightedQuickUnionUF uf;
    // avoid backwash
    private WeightedQuickUnionUF ufWithoutBackWash;

    private int numberOfOpenSites;

    public Percolation(int N) {
        // TODO: Fill in this constructor.
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        this.N = N;
        // Default value is false;
        grid = new boolean[N][N];
        uf = new WeightedQuickUnionUF(N * N + 2);
        ufWithoutBackWash = new WeightedQuickUnionUF(N * N + 1); // 只包含顶部虚拟节点
        numberOfOpenSites = 0;
    }

    public void open(int row, int col) {
        // TODO: Fill in this method.
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IllegalArgumentException();
        }
        if (isOpen(row, col)) {
            return;
        }
        grid[row][col] = true;
        numberOfOpenSites++;
        int[][] neighbour = new int[][]{
                {0, -1},
                {0, 1},
                {-1, 0},
                {1, 0}
        };
        for (int i = 0; i < 4; i++){
            int flagRow = row + neighbour[i][0];
            int flagCol = col + neighbour[i][1];
            if (flagCol < 0 || flagCol >= N) {
                continue;
            }
            // 两个uf对象都需要使用顶行的虚拟点
            if (flagRow < 0) {
                ufWithoutBackWash.union(xyTo1D(row, col), N * N);
                uf.union(xyTo1D(row, col), N * N);
                continue;
            }
            if (flagRow >= N ) {
                uf.union(xyTo1D(row, col), N * N + 1);
                continue;
            }
            //
            if (isOpen(flagRow, flagCol)) {
                uf.union(xyTo1D(flagRow, flagCol), xyTo1D(row, col));
                ufWithoutBackWash.union(xyTo1D(flagRow, flagCol), xyTo1D(row, col));
            }
        }

    }

    public boolean isOpen(int row, int col) {
        // TODO: Fill in this method.
        return grid[row][col];
    }

    public boolean isFull(int row, int col) {
        // TODO: Fill in this method.
        if  (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IllegalArgumentException();
        }
        // 判断是否为full时，不涉及底部的虚拟节点
        return ufWithoutBackWash.connected(xyTo1D(row, col), N * N);
    }

    public int numberOfOpenSites() {
        // TODO: Fill in this method.
        return numberOfOpenSites;
    }

    public boolean percolates() {
        // TODO: Fill in this method.
        return uf.connected(N * N, N * N + 1);
    }

    // TODO: Add any useful helper methods (we highly recommend this!).
    // TODO: Remove all TODO comments before submitting.
    public int xyTo1D(int row, int col) {
        return row * N + col;
    }

}
