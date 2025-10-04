/**
 * DotsAndBoxesBoard.java
 *
 * Represents the game board for Dots and Boxes.
 * Manages edges, boxes, and game state.
 *
 * Author: CS611 Student
 * Version: 1.0
 */
public class DotsAndBoxesBoard {
    public static final int MIN_SIZE = 2;
    public static final int MAX_SIZE = 10;

    private int rows;
    private int cols;

    // Edges are stored separately based on orientation:
    // Horizontal edges: one more row than boxes
    private Edge[][] horizontalEdges;
    // Vertical edges: one more column than boxes
    private Edge[][] verticalEdges;

    // Boxes grid
    private Box[][] boxes;

    private int totalBoxes;
    private int completedBoxes;

    /**
     * Constructor for a board of given number of rows and cols (boxes).
     * @param rows number of rows of boxes
     * @param cols number of columns of boxes
     * @throws IllegalArgumentException if dimensions are out of allowed bounds
     */
    public DotsAndBoxesBoard(int rows, int cols) {
        if (rows < MIN_SIZE || rows > MAX_SIZE || cols < MIN_SIZE || cols > MAX_SIZE) {
            throw new IllegalArgumentException(
                    "Board size must be between " + MIN_SIZE + " and " + MAX_SIZE);
        }

        this.rows = rows;
        this.cols = cols;

        totalBoxes = rows * cols;
        completedBoxes = 0;

        initializeEdges();
        initializeBoxes();
    }

    /**
     * Sets up all the edges for the board, creating the horizontal and vertical edges.
     */
    private void initializeEdges() {
        // Create horizontal edges: (rows + 1) x cols
        horizontalEdges = new Edge[rows + 1][cols];
        for (int r = 0; r <= rows; r++) {
            for (int c = 0; c < cols; c++) {
                horizontalEdges[r][c] = new Edge(r, c, true);
            }
        }

        // Create vertical edges: rows x (cols + 1)
        verticalEdges = new Edge[rows][cols + 1];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c <= cols; c++) {
                verticalEdges[r][c] = new Edge(r, c, false);
            }
        }
    }

    /**
     * Creates boxes and assigns their surrounding edges
     */
    private void initializeBoxes() {
        boxes = new Box[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                // Each box references its top, bottom, left, and right edges
                boxes[r][c] = new Box(
                        r,
                        c,
                        horizontalEdges[r][c],      // top edge
                        horizontalEdges[r + 1][c],  // bottom edge
                        verticalEdges[r][c],        // left edge
                        verticalEdges[r][c + 1]     // right edge
                );
            }
        }
    }

    public int getRows() { return rows; }
    public int getCols() { return cols; }
    public int getTotalBoxes() { return totalBoxes; }
    public int getCompletedBoxes() { return completedBoxes; }

    /**
     * Check if the game has ended, i.e. all boxes are completed
     * @return true if all boxes claimed, false otherwise
     */
    public boolean isGameOver() {
        return completedBoxes == totalBoxes;
    }

    /**
     * Claims a horizontal edge if possible.
     * @param row the row coordinate of the edge (0 to rows)
     * @param col the column coordinate of the edge (0 to cols-1)
     * @param symbol player symbol claiming the edge
     * @return true if edge was successfully claimed, false if invalid or already claimed
     */
    public boolean claimHorizontalEdge(int row, int col, char symbol) {
        if (row < 0 || row > rows || col < 0 || col >= cols) return false;
        return horizontalEdges[row][col].claim(symbol);
    }

    /**
     * Claims a vertical edge if possible.
     * @param row the row coordinate of the edge (0 to rows-1)
     * @param col the column coordinate of the edge (0 to cols)
     * @param symbol player symbol claiming the edge
     * @return true if edge was successfully claimed, false if invalid or already claimed
     */
    public boolean claimVerticalEdge(int row, int col, char symbol) {
        if (row < 0 || row >= rows || col < 0 || col > cols) return false;
        return verticalEdges[row][col].claim(symbol);
    }

    /**
     * After a move claiming a particular edge, check if any boxes adjacent to that edge got completed.
     * Increments completedBoxes accordingly.
     *
     * @param row row coordinate of the claimed edge
     * @param col column coordinate of the claimed edge
     * @param horizontal true if horizontal edge, false if vertical edge
     * @return number of boxes completed by this claim
     */
    public int checkForCompletedBoxes(int row, int col, boolean horizontal) {
        int boxesCompletedThisMove = 0;

        if (horizontal) {
            // Check box above this edge if any
            if (row > 0 && row <= rows) {
                if (boxes[row - 1][col].checkAndComplete()) {
                    boxesCompletedThisMove++;
                    completedBoxes++;
                }
            }

            // Check box below this edge if any
            if (row < rows) {
                if (boxes[row][col].checkAndComplete()) {
                    boxesCompletedThisMove++;
                    completedBoxes++;
                }
            }

        } else {
            // vertical edge

            // Check box to the left
            if (col > 0 && col <= cols) {
                if (boxes[row][col - 1].checkAndComplete()) {
                    boxesCompletedThisMove++;
                    completedBoxes++;
                }
            }

            // Check box to the right
            if (col < cols) {
                if (boxes[row][col].checkAndComplete()) {
                    boxesCompletedThisMove++;
                    completedBoxes++;
                }
            }
        }

        return boxesCompletedThisMove;
    }

    // Getter methods for edges and boxes with bounds checking

    public Edge getHorizontalEdge(int row, int col) {
        if (row < 0 || row > rows || col < 0 || col >= cols) return null;
        return horizontalEdges[row][col];
    }

    public Edge getVerticalEdge(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col > cols) return null;
        return verticalEdges[row][col];
    }

    public Box getBox(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= cols) return null;
        return boxes[row][col];
    }

    /**
     * Displays the current state of the board on the console.
     */
    public void display() {
        System.out.println();

        // Display column numbers
        System.out.print("    ");
        for (int c = 0; c < cols; c++) {
            System.out.print("  " + c + "   ");
        }
        System.out.println();

        for (int r = 0; r <= rows; r++) {
            // Row label at start of line
            if (r < rows) {
                System.out.print("  " + r + " ");
            } else {
                System.out.print("    ");
            }

            // Dots and horizontal edges
            for (int c = 0; c < cols; c++) {
                ColorPrinter.printYellow("●");
                if (horizontalEdges[r][c].isClaimed()) {
                    ColorPrinter.printGreen("─────");
                } else {
                    System.out.print("     ");
                }
            }
            ColorPrinter.printlnYellow("●");

            // Vertical edges and box contents, skip after last line
            if (r < rows) {
                System.out.print("    ");
                for (int c = 0; c <= cols; c++) {
                    // For all columns except one past the last box
                    if (c < cols) {
                        if (verticalEdges[r][c].isClaimed()) {
                            ColorPrinter.printGreen("│");
                        } else {
                            System.out.print(" ");
                        }

                        if (boxes[r][c].isCompleted()) {
                            ColorPrinter.printCyan("  " + boxes[r][c].getCompletedBy() + "  ");
                        } else {
                            System.out.print("     ");
                        }

                    } else { // Rightmost vertical edge
                        if (verticalEdges[r][c].isClaimed()) {
                            ColorPrinter.printlnGreen("│");
                        } else {
                            System.out.println(" ");
                        }
                    }
                }
            }
        }
        System.out.println();
    }
}
