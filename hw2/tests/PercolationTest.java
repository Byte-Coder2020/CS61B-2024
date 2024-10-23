import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

public class PercolationTest {

    /**
     * Enum to represent the state of a cell in the grid.
     */
    private enum Cell {
        CLOSED, OPEN, INVALID, FULL
    }

    /**
     * Creates a Cell[][] based off of what Percolation p returns.
     * Use this method in your tests to see if isOpen and isFull are returning the
     * correct things.
     */
    private static Cell[][] getState(int N, Percolation p) {
        Cell[][] state = new Cell[N][N];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                int open = p.isOpen(r, c) ? 1 : 0;
                int full = p.isFull(r, c) ? 2 : 0;
                state[r][c] = Cell.values()[open + full];
            }
        }
        return state;
    }

    @Test
    public void basicTest() {
        int N = 5;
        Percolation p = new Percolation(N);
        int[][] openSites = {
                {0, 1},
                {2, 0},
                {3, 1},
                {4, 1},
                {1, 0},
                {1, 1}
        };
        Cell[][] expectedState = {
                {Cell.CLOSED, Cell.FULL, Cell.CLOSED, Cell.CLOSED, Cell.CLOSED},
                {Cell.FULL, Cell.FULL, Cell.CLOSED, Cell.CLOSED, Cell.CLOSED},
                {Cell.FULL, Cell.CLOSED, Cell.CLOSED, Cell.CLOSED, Cell.CLOSED},
                {Cell.CLOSED, Cell.OPEN, Cell.CLOSED, Cell.CLOSED, Cell.CLOSED},
                {Cell.CLOSED, Cell.OPEN, Cell.CLOSED, Cell.CLOSED, Cell.CLOSED}
        };
        for (int[] site : openSites) {
            p.open(site[0], site[1]);
        }
        assertThat(getState(N, p)).isEqualTo(expectedState);
        assertThat(p.percolates()).isFalse();
    }

    @Test
    public void oneByOneTest() {
        int N = 1;
        Percolation p = new Percolation(N);
        p.open(0, 0);
        Cell[][] expectedState = {
                {Cell.FULL}
        };
        assertThat(getState(N, p)).isEqualTo(expectedState);
        assertThat(p.percolates()).isTrue();
    }

    @Test
    public void percolationTest2x2() {
        int N = 2;
        Percolation p = new Percolation(N);
        p.open(0, 0); // Top-left
        p.open(1, 0); // Bottom-left
        assertThat(p.percolates()).isTrue(); // Should percolate
        Cell[][] expectedState = {
                {Cell.FULL, Cell.CLOSED},
                {Cell.FULL, Cell.CLOSED}
        };
        assertThat(getState(N, p)).isEqualTo(expectedState);
    }

    @Test
    public void noPercolationTest3x3() {
        int N = 3;
        Percolation p = new Percolation(N);
        p.open(0, 1); // Open site in top row, middle column
        p.open(1, 1); // Open site directly below
        p.open(2, 2); // Bottom row, but disconnected
        assertThat(p.percolates()).isFalse(); // Should NOT percolate
        Cell[][] expectedState = {
                {Cell.CLOSED, Cell.FULL, Cell.CLOSED},
                {Cell.CLOSED, Cell.FULL, Cell.CLOSED},
                {Cell.CLOSED, Cell.CLOSED, Cell.OPEN}
        };
        assertThat(getState(N, p)).isEqualTo(expectedState);
    }

    @Test
    public void percolationComplexTest4x4() {
        int N = 4;
        Percolation p = new Percolation(N);
        int[][] openSites = {
                {0, 0}, {0, 1}, {0, 2}, {0, 3}, // Open all top row
                {1, 1}, {2, 1}, {3, 1}  // Vertical path down column 1
        };
        for (int[] site : openSites) {
            p.open(site[0], site[1]);
        }
        assertThat(p.percolates()).isTrue(); // Should percolate
        Cell[][] expectedState = {
                {Cell.FULL, Cell.FULL, Cell.FULL, Cell.FULL},
                {Cell.CLOSED, Cell.FULL, Cell.CLOSED, Cell.CLOSED},
                {Cell.CLOSED, Cell.FULL, Cell.CLOSED, Cell.CLOSED},
                {Cell.CLOSED, Cell.FULL, Cell.CLOSED, Cell.CLOSED}
        };
        assertThat(getState(N, p)).isEqualTo(expectedState);
    }

    @Test
    public void edgeCasePercolationTest() {
        int N = 3;
        Percolation p = new Percolation(N);
        p.open(2, 2); // Open bottom-right corner
        p.open(0, 0); // Open top-left corner
        assertThat(p.percolates()).isFalse(); // Should not percolate yet
        p.open(1, 0); // Open a site to create connection
        p.open(2, 0); // Open the site to complete the percolation
        assertThat(p.percolates()).isTrue(); // Now it should percolate
    }

    @Test
    public void fullTest5x5() {
        int N = 5;
        Percolation p = new Percolation(N);
        int[][] openSites = {
                {0, 0}, {1, 0}, {2, 0}, {3, 0}, {4, 0},  // Left column
                {4, 4}, {3, 4}, {2, 4}, {1, 4}, {0, 4}   // Right column
        };
        for (int[] site : openSites) {
            p.open(site[0], site[1]);
        }
        Cell[][] expectedState = {
                {Cell.FULL, Cell.CLOSED, Cell.CLOSED, Cell.CLOSED, Cell.FULL},
                {Cell.FULL, Cell.CLOSED, Cell.CLOSED, Cell.CLOSED, Cell.FULL},
                {Cell.FULL, Cell.CLOSED, Cell.CLOSED, Cell.CLOSED, Cell.FULL},
                {Cell.FULL, Cell.CLOSED, Cell.CLOSED, Cell.CLOSED, Cell.FULL},
                {Cell.FULL, Cell.CLOSED, Cell.CLOSED, Cell.CLOSED, Cell.FULL}
        };
        assertThat(getState(N, p)).isEqualTo(expectedState);
        assertThat(p.percolates()).isTrue(); // Should percolate
    }

    @Test
    public void invalidPositionTest() {
        int N = 3;
        Percolation p = new Percolation(N);
        try {
            p.open(-1, 0); // Invalid row
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Expected exception
        }

        try {
            p.open(0, 3); // Invalid column
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Expected exception
        }
    }

    @Test
    public void allClosedTest() {
        int N = 4;
        Percolation p = new Percolation(N);
        Cell[][] expectedState = {
                {Cell.CLOSED, Cell.CLOSED, Cell.CLOSED, Cell.CLOSED},
                {Cell.CLOSED, Cell.CLOSED, Cell.CLOSED, Cell.CLOSED},
                {Cell.CLOSED, Cell.CLOSED, Cell.CLOSED, Cell.CLOSED},
                {Cell.CLOSED, Cell.CLOSED, Cell.CLOSED, Cell.CLOSED}
        };
        assertThat(getState(N, p)).isEqualTo(expectedState);
        assertThat(p.percolates()).isFalse(); // No open sites, no percolation
    }

    @Test
    public void diagonalTestNoPercolation() {
        int N = 4;
        Percolation p = new Percolation(N);
        int[][] openSites = {
                {0, 0}, {1, 1}, {2, 2}, {3, 3}  // Diagonal open
        };
        for (int[] site : openSites) {
            p.open(site[0], site[1]);
        }
        Cell[][] expectedState = {
                {Cell.FULL, Cell.CLOSED, Cell.CLOSED, Cell.CLOSED},
                {Cell.CLOSED, Cell.OPEN, Cell.CLOSED, Cell.CLOSED},
                {Cell.CLOSED, Cell.CLOSED, Cell.OPEN, Cell.CLOSED},
                {Cell.CLOSED, Cell.CLOSED, Cell.CLOSED, Cell.OPEN}
        };
        assertThat(getState(N, p)).isEqualTo(expectedState);
        assertThat(p.percolates()).isFalse(); // Diagonal path does not percolate
    }

    @Test
    public void backwashTest() {
        int N = 3;
        Percolation p = new Percolation(N);

        // Open bottom row
        p.open(2, 0);
        p.open(2, 1);
        p.open(2, 2);

        // Open one top cell to create percolation
        p.open(0, 0);

        // Open one middle cell to connect top and bottom
        p.open(1, 0);

        // After these operations, the system should percolate.
        assertThat(p.percolates()).isTrue();

        // Bottom row is open, but they should not be full due to backwash
        assertThat(p.isFull(2, 1)).isTrue();
        assertThat(p.isFull(2, 2)).isTrue();
        assertThat(p.isFull(2, 0)).isTrue();

        // The only full cells should be the ones connected to the top
        assertThat(p.isFull(0, 0)).isTrue();
        assertThat(p.isFull(1, 0)).isTrue();
    }
}