import java.util.Random;

/**
 * PuzzleBoard.java
 * Represents the sliding puzzle board with tiles.
 * Handles board generation, scrambling, and move validation.
 *
 * @author CS611 Student
 * @version 1.0
 */
public class PuzzleBoard {
    private final int[][] tiles;
    private final int rowCount;
    private final int colCount;
    private int emptyRow;
    private int emptyCol;

    public static final int MIN_SIZE = 2;
    public static final int MAX_SIZE = 10;

    /**
     * Constructor - creates a puzzle board with specified dimensions
     * @param rows number of rows
     * @param cols number of columns
     */
    public PuzzleBoard(int rows, int cols) {
        if (rows < MIN_SIZE || cols < MIN_SIZE) {
            throw new IllegalArgumentException(
                    "Board too small! Must be at least " + MIN_SIZE + "x" + MIN_SIZE
            );
        }
        if (rows > MAX_SIZE || cols > MAX_SIZE) {
            throw new IllegalArgumentException(
                    "Board too large! Maximum is " + MAX_SIZE + "x" + MAX_SIZE
            );
        }
        this.rowCount = rows;
        this.colCount = cols;
        tiles = new int[rows][cols];
        setupBoard();
        scrambleBoard();
    }

    /**
     * Sets up the board in solved state
     */
    private void setupBoard() {
        int val = 1;
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                if (i == rowCount - 1 && j == colCount - 1) {
                    tiles[i][j] = 0;  // Empty spot
                    emptyRow = i;
                    emptyCol = j;
                } else {
                    tiles[i][j] = val++;
                }
            }
        }
    }

    /**
     * Scrambles the board by making random valid moves
     * This ensures the puzzle is always solvable
     */
    private void scrambleBoard() {
        Random rnd = new Random();
        int maxShuffle = rowCount * colCount * 100;

        for (int k = 0; k < maxShuffle; k++) {
            // Try a random direction for the empty tile to move
            int dir = rnd.nextInt(4);
            int newR = emptyRow;
            int newC = emptyCol;

            switch (dir) {
                case 0: newR = emptyRow - 1; break; // up
                case 1: newR = emptyRow + 1; break; // down
                case 2: newC = emptyCol - 1; break; // left
                case 3: newC = emptyCol + 1; break; // right
            }

            if (withinBounds(newR, newC)) {
                // Swap with empty space
                tiles[emptyRow][emptyCol] = tiles[newR][newC];
                tiles[newR][newC] = 0;
                emptyRow = newR;
                emptyCol = newC;
            }
        }
    }

    /**
     * Checks if coordinates are within board bounds
     * @param r row index
     * @param c column index
     * @return true if within bounds, false otherwise
     */
    private boolean withinBounds(int r, int c) {
        return r >= 0 && r < rowCount && c >= 0 && c < colCount;
    }

    /**
     * Checks if a tile at given position can be slid
     * @param r row index
     * @param c column index
     * @return true if tile can slide, false otherwise
     */
    public boolean canSlide(int r, int c) {
        if (!withinBounds(r, c)) return false;
        if (tiles[r][c] == 0) return false;

        // Must be adjacent to empty tile (orthogonally)
        if ((Math.abs(r - emptyRow) == 1 && c == emptyCol) ||
                (Math.abs(c - emptyCol) == 1 && r == emptyRow)) {
            return true;
        }
        return false;
    }

    /**
     * Attempts to slide a tile at given position
     * @param r row index
     * @param c column index
     * @return true if successfully slid, false otherwise
     */
    public boolean slideTile(int r, int c) {
        if (!canSlide(r, c)) return false;

        // Swap with empty position
        tiles[emptyRow][emptyCol] = tiles[r][c];
        tiles[r][c] = 0;
        emptyRow = r;
        emptyCol = c;
        return true;
    }

    /**
     * Checks if the puzzle is solved
     * @return true if solved, false otherwise
     */
    public boolean isSolved() {
        int expected = 1;
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                if (i == rowCount - 1 && j == colCount - 1) {
                    return tiles[i][j] == 0;
                }
                if (tiles[i][j] != expected) {
                    return false;
                }
                expected++;
            }
        }
        return true;
    }

    /**
     * Displays the current board state with colors
     */
    public void display() {
        System.out.println();

        // Print column headers
        System.out.print("     ");
        for (int j = 0; j < colCount; j++) {
            System.out.printf(" %2d ", j);
        }
        System.out.println();
        System.out.println("   " + "─".repeat(colCount * 4 + 1));

        // Print board
        for (int i = 0; i < rowCount; i++) {
            System.out.printf(" %2d │", i);
            for (int j = 0; j < colCount; j++) {
                if (tiles[i][j] == 0) {
                    ColorPrinter.printYellow("    "); // Empty space
                } else {
                    ColorPrinter.printCyan(String.format(" %2d ", tiles[i][j]));
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Gets the number of rows
     * @return row count
     */
    public int getRowCount() {
        return rowCount;
    }

    /**
     * Gets the number of columns
     * @return column count
     */
    public int getColCount() {
        return colCount;
    }
}