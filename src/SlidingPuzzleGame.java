/**
 * SlidingPuzzleGame.java
 * Manages the game flow for a single Sliding Puzzle game.
 * Handles player moves and win detection.
 *
 * @author Priyanshu and Lyu
 * @version 1.0
 */
public class SlidingPuzzleGame {
    private PuzzleBoard board;
    private int movesMade;
    private InputHandler inputHandler;
    private boolean gameActive;
    private String playerName;
    private PlayerStatistics statistics;  // ADD THIS LINE

    /**
     * Constructor - creates a new puzzle game
     * @param rows number of rows
     * @param cols number of columns
     * @param playerName name of the player
     * @param statistics statistics tracker
     */
    public SlidingPuzzleGame(int rows, int cols, String playerName, PlayerStatistics statistics) {
        this.board = new PuzzleBoard(rows, cols);
        this.movesMade = 0;
        this.inputHandler = new InputHandler();
        this.gameActive = true;
        this.playerName = playerName;
        this.statistics = statistics;  // ADD THIS LINE
    }

    /**
     * Main game loop
     */
    public void play() {
        displayGameStart();

        while (gameActive && !board.isSolved()) {
            board.display();
            displayStatus();

            if (!makeMove()) {
                // Game was quit or error occurred
                if (!gameActive) break;
            }
        }

        if (gameActive && board.isSolved()) {
            handleWin();
        } else {
            handleQuit();
        }
    }

    /**
     * Displays game start information
     */
    private void displayGameStart() {
        ColorPrinter.printlnCyan("\n╔═══════════════════════════════════════╗");
        ColorPrinter.printlnCyan("║      SLIDING PUZZLE GAME             ║");
        ColorPrinter.printlnCyan("╚═══════════════════════════════════════╝");
        System.out.println("Player: " + playerName);
        System.out.println("Board size: " + board.getRowCount() + " x " + board.getColCount());
        ColorPrinter.printlnYellow("\nHow to play:");
        System.out.println("- Enter the row and column of the tile you want to move");
        System.out.println("- Only tiles adjacent to the empty space can move");
        System.out.println("- Arrange tiles in order from 1 to " +
                ((board.getRowCount() * board.getColCount()) - 1));
        System.out.println("- Type 'quit' anytime to exit");
        System.out.println();
    }

    /**
     * Displays current game status
     */
    private void displayStatus() {
        System.out.println("═".repeat(50));
        ColorPrinter.printYellow("Moves made: ");
        ColorPrinter.printlnCyan(String.valueOf(movesMade));
        System.out.println("═".repeat(50));
    }

    /**
     * Handles a player's move
     * @return true if move was processed, false if quit
     */
    private boolean makeMove() {
        System.out.println("Enter tile position to move:");

        int row = inputHandler.getIntInRange(
                "Row (0-" + (board.getRowCount() - 1) + "): ",
                0,
                board.getRowCount() - 1
        );

        if (row == -1) {
            gameActive = false;
            return false;
        }

        int col = inputHandler.getIntInRange(
                "Col (0-" + (board.getColCount() - 1) + "): ",
                0,
                board.getColCount() - 1
        );

        if (col == -1) {
            gameActive = false;
            return false;
        }

        if (board.slideTile(row, col)) {
            movesMade++;
            ColorPrinter.printlnGreen("✓ Tile moved!");
            return true;
        } else {
            ColorPrinter.printlnRed("✗ Can't move that tile! Choose a tile next to the empty space.");
            return true;
        }
    }

    /**
     * Handles winning the game
     */
    private void handleWin() {
        board.display();

        ColorPrinter.printlnGreen("\n╔═══════════════════════════════════════╗");
        ColorPrinter.printlnGreen("║          PUZZLE SOLVED!              ║");
        ColorPrinter.printlnGreen("╚═══════════════════════════════════════╝");

        System.out.println("\n🎉 Congratulations, " + playerName + "! 🎉");
        System.out.println("You solved the puzzle in " + movesMade + " moves!");

        // Record statistics
        statistics.recordPuzzleGame(playerName, movesMade);

        // Ask to play again
        if (inputHandler.getYesNo("\nWould you like to play again?")) {
            if (inputHandler.getYesNo("Use same board size?")) {
                SlidingPuzzleGame newGame = new SlidingPuzzleGame(
                        board.getRowCount(),
                        board.getColCount(),
                        playerName,
                        statistics  // Pass statistics to new game
                );
                newGame.play();
            } else {
                // Return to main menu (don't start another game here)
                ColorPrinter.printlnCyan("Returning to main menu...");
            }
        }
    }

    /**
     * Handles graceful quit
     */
    private void handleQuit() {
        ColorPrinter.printlnYellow("\n═══════════════════════════════════════");
        ColorPrinter.printlnYellow("Game ended by player.");
        ColorPrinter.printlnYellow("═══════════════════════════════════════");
        System.out.println("Moves made: " + movesMade);
    }

    /**
     * Gets the number of moves made
     * @return move count
     */
    public int getMovesMade() {
        return movesMade;
    }
}