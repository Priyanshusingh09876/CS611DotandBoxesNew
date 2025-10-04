/**
 * DotsAndBoxesGame.java
 * Manages the game flow for a single Dots and Boxes match.
 * @author Priyanshu and Lyu
 * @version 1.0
 */
public class DotsAndBoxesGame {
    private DotsAndBoxesBoard board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private InputHandler inputHandler;
    private PlayerStatistics statistics;
    private boolean gameActive;

    /**
     * Constructor - creates a new game instance
     * @param player1 first player
     * @param player2 second player
     * @param rows number of box rows
     * @param cols number of box columns
     * @param statistics statistics tracker
     */
    public DotsAndBoxesGame(Player player1, Player player2, int rows, int cols,
                            PlayerStatistics statistics) {
        this.board = new DotsAndBoxesBoard(rows, cols);
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
        this.inputHandler = new InputHandler();
        this.statistics = statistics;
        this.gameActive = true;

        // Reset scores for new game
        player1.resetScore();
        player2.resetScore();
    }

    /**
     * Main game loop
     */
    public void play() {
        displayGameStart();
        board.display();

        // Main game loop
        while (gameActive && !board.isGameOver()) {
            displayCurrentTurn();

            boolean validMove = false;
            while (!validMove && gameActive) {
                validMove = makeMove();
            }

            // Check if game was quit
            if (!gameActive) {
                break;
            }
        }

        // End game handling
        if (gameActive) {
            endGame();
        } else {
            handleQuit();
        }
    }

    /**
     * Displays game start information
     */
    private void displayGameStart() {
        ColorPrinter.printlnCyan("\n╔═══════════════════════════════════════╗");
        ColorPrinter.printlnCyan("║         GAME START!                  ║");
        ColorPrinter.printlnCyan("╚═══════════════════════════════════════╝");
        System.out.println(player1 + " vs " + player2);
        System.out.println("Board size: " + board.getRows() + " x " + board.getCols());
        System.out.println("Total boxes: " + board.getTotalBoxes());
        ColorPrinter.printlnYellow("\nHow to play:");
        System.out.println("- Enter 'H' for horizontal edge or 'V' for vertical edge");
        System.out.println("- Then enter row and column numbers");
        System.out.println("- Complete boxes to score points!");
        System.out.println("- Type 'quit' anytime to exit");
    }

    /**
     * Displays current player's turn information
     */
    private void displayCurrentTurn() {
        System.out.println("\n" + "═".repeat(50));
        ColorPrinter.printYellow("Current Player: ");
        ColorPrinter.printlnCyan(currentPlayer.toString());
        System.out.println("Score: " + player1.getName() + " = " + player1.getScore() +
                " | " + player2.getName() + " = " + player2.getScore());
        System.out.println("Boxes remaining: " +
                (board.getTotalBoxes() - board.getCompletedBoxes()));
    }

    /**
     * Handles a player's move
     * @return true if move was valid and completed, false otherwise
     */
    private boolean makeMove() {
        // Get edge type (horizontal or vertical)
        System.out.print("\nEnter edge type (H/V) or 'quit': ");
        String edgeType = inputHandler.getString("").toUpperCase();

        if (edgeType.equals("QUIT") || edgeType.equals("Q")) {
            gameActive = false;
            return true;
        }

        if (!edgeType.equals("H") && !edgeType.equals("V")) {
            ColorPrinter.printlnRed("Invalid input! Please enter 'H' for horizontal or 'V' for vertical.");
            return false;
        }

        boolean isHorizontal = edgeType.equals("H");

        // Get row and column
        int maxRow = isHorizontal ? board.getRows() : board.getRows() - 1;
        int maxCol = isHorizontal ? board.getCols() - 1 : board.getCols();

        System.out.println("Enter row (0-" + maxRow + ") and column (0-" + maxCol + ")");

        int row = inputHandler.getIntInRange("Row: ", 0, maxRow);
        if (row == -1) {
            gameActive = false;
            return true;
        }

        int col = inputHandler.getIntInRange("Col: ", 0, maxCol);
        if (col == -1) {
            gameActive = false;
            return true;
        }

        // Attempt to claim the edge
        boolean claimed;
        if (isHorizontal) {
            claimed = board.claimHorizontalEdge(row, col, currentPlayer.getSymbol());
        } else {
            claimed = board.claimVerticalEdge(row, col, currentPlayer.getSymbol());
        }

        if (!claimed) {
            ColorPrinter.printlnRed("That edge has already been claimed! Try again.");
            return false;
        }

        // Check if any boxes were completed
        int boxesCompleted = board.checkForCompletedBoxes(row, col, isHorizontal);

        // Update display
        board.display();

        // Handle box completion
        if (boxesCompleted > 0) {
            currentPlayer.incrementScore();

            if (boxesCompleted == 1) {
                ColorPrinter.printlnGreen("★ " + currentPlayer.getName() +
                        " completed a box! Go again! ★");
            } else {
                ColorPrinter.printlnGreen("★★ " + currentPlayer.getName() +
                        " completed " + boxesCompleted + " boxes! Go again! ★★");
            }

            // Player goes again, don't switch turns
        } else {
            // Switch to other player
            switchPlayer();
        }

        return true;
    }

    /**
     * Switches the current player
     */
    private void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    /**
     * Handles end of game
     */
    private void endGame() {
        ColorPrinter.printlnCyan("\n╔═══════════════════════════════════════╗");
        ColorPrinter.printlnCyan("║            GAME OVER!                ║");
        ColorPrinter.printlnCyan("╚═══════════════════════════════════════╝");

        // Display final scores
        System.out.println("\nFinal Scores:");
        System.out.println(player1.getName() + ": " + player1.getScore());
        System.out.println(player2.getName() + ": " + player2.getScore());

        // Determine winner
        Player winner = null;
        if (player1.getScore() > player2.getScore()) {
            winner = player1;
            ColorPrinter.printlnGreen("\n🎉 " + player1.getName() + " WINS! 🎉");
        } else if (player2.getScore() > player1.getScore()) {
            winner = player2;
            ColorPrinter.printlnGreen("\n🎉 " + player2.getName() + " WINS! 🎉");
        } else {
            ColorPrinter.printlnYellow("\n🤝 It's a TIE! 🤝");
        }

        // Update statistics
        player1.recordGame();
        player2.recordGame();
        if (winner != null) {
            winner.recordWin();
        }

        statistics.recordGame(player1, player2, winner);

        // Ask to play again
        System.out.println();
        if (inputHandler.getYesNo("Would you like to play again?")) {
            // Create new game with same players
            int rows = board.getRows();
            int cols = board.getCols();

            if (inputHandler.getYesNo("Use same board size (" + rows + "x" + cols + ")?")) {
                DotsAndBoxesGame newGame = new DotsAndBoxesGame(
                        player1, player2, rows, cols, statistics
                );
                newGame.play();
            } else {
                // Get new dimensions
                rows = inputHandler.getIntInRange(
                        "Enter number of rows (2-10): ",
                        DotsAndBoxesBoard.MIN_SIZE,
                        DotsAndBoxesBoard.MAX_SIZE
                );
                cols = inputHandler.getIntInRange(
                        "Enter number of columns (2-10): ",
                        DotsAndBoxesBoard.MIN_SIZE,
                        DotsAndBoxesBoard.MAX_SIZE
                );

                DotsAndBoxesGame newGame = new DotsAndBoxesGame(
                        player1, player2, rows, cols, statistics
                );
                newGame.play();
            }
        }
    }

    /**
     * Handles graceful quit during gameplay
     */
    private void handleQuit() {
        ColorPrinter.printlnYellow("\n═══════════════════════════════════════");
        ColorPrinter.printlnYellow("Game ended by player.");
        ColorPrinter.printlnYellow("═══════════════════════════════════════");
        System.out.println("\nCurrent Scores:");
        System.out.println(player1.getName() + ": " + player1.getScore());
        System.out.println(player2.getName() + ": " + player2.getScore());
    }
}