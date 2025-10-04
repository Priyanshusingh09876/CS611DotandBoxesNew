/**
 * GameController.java
 * Handles the overall game flow and lets players choose between games.
 * Manages switching between games and keeps track of player stats.
 *
 * @author CS611 Student
 * @version 1.0
 */
public class GameController {
    private InputHandler inputHandler;
    private PlayerStatistics statistics;

    private static final String WELCOME_MESSAGE =
            "\n" +
                    "╔═══════════════════════════════════════════════════╗\n" +
                    "║     WELCOME TO THE GAME COLLECTION                 ║\n" +
                    "║     CS 611 - Assignment #2                          ║\n" +
                    "╚═══════════════════════════════════════════════════╝\n";

    /**
     * Initialize the input handler and statistics at startup
     */
    public GameController() {
        inputHandler = new InputHandler();
        statistics = new PlayerStatistics();
    }

    /**
     * Run the main menu loop until the user chooses to quit
     */
    public void start() {
        ColorPrinter.printlnCyan(WELCOME_MESSAGE);

        boolean running = true;
        while (running) {
            int choice = showMainMenu();

            switch (choice) {
                case 1:
                    playPuzzleGame();
                    break;
                case 2:
                    playDotsAndBoxes();
                    break;
                case 3:
                    statistics.displayAllStatistics();
                    break;
                case 4:
                    running = false;
                    showFinalStatistics();
                    break;
                default:
                    // just in case, though inputHandler restricts choices
                    ColorPrinter.printlnRed("Invalid choice, please try again.");
            }
        }

        ColorPrinter.printlnGreen("\nThanks for playing! Take care!\n");
    }

    /**
     * Display the main menu options and get user's selection
     * @return number corresponding to the selected option
     */
    private int showMainMenu() {
        ColorPrinter.printlnYellow("\n=== MAIN MENU ===");
        System.out.println("1. Play Sliding Puzzle");
        System.out.println("2. Play Dots and Boxes");
        System.out.println("3. View Statistics");
        System.out.println("4. Quit");

        return inputHandler.getIntInRange("Enter your choice: ", 1, 4);
    }

    /**
     * Set up and start the Sliding Puzzle game
     */
    private void playPuzzleGame() {
        ColorPrinter.printlnCyan("\n╔═══════════════════════════════════════╗");
        ColorPrinter.printlnCyan("║        SLIDING PUZZLE SETUP           ║");
        ColorPrinter.printlnCyan("╚═══════════════════════════════════════╝");

        ColorPrinter.printlnYellow("\n--- Player Setup ---");
        System.out.print("What's your name? ");
        String playerName = inputHandler.getNonEmptyString();

        ColorPrinter.printlnYellow("\n--- Board Setup ---");
        int rows = inputHandler.getIntInRange(
                "Enter number of rows (2-10): ",
                PuzzleBoard.MIN_SIZE, PuzzleBoard.MAX_SIZE);

        int cols = inputHandler.getIntInRange(
                "Enter number of columns (2-10): ",
                PuzzleBoard.MIN_SIZE, PuzzleBoard.MAX_SIZE);

        try {
            SlidingPuzzleGame game = new SlidingPuzzleGame(rows, cols, playerName, statistics);
            game.play();
        } catch (IllegalArgumentException e) {
            ColorPrinter.printlnRed("Oops! Invalid board setup: " + e.getMessage());
        }
    }

    /**
     * Set up and start the Dots and Boxes game
     */
    private void playDotsAndBoxes() {
        ColorPrinter.printlnCyan("\n╔═══════════════════════════════════════╗");
        ColorPrinter.printlnCyan("║        DOTS AND BOXES SETUP            ║");
        ColorPrinter.printlnCyan("╚═══════════════════════════════════════╝");

        ColorPrinter.printlnYellow("\n--- Player Setup ---");
        System.out.print("Player 1 name: ");
        String p1Name = inputHandler.getNonEmptyString();

        System.out.print("Player 2 name: ");
        String p2Name = inputHandler.getNonEmptyString();

        Player player1 = new Player(p1Name, 'X');
        Player player2 = new Player(p2Name, 'O');

        ColorPrinter.printlnYellow("\n--- Board Setup ---");
        int rows = inputHandler.getIntInRange(
                "Enter number of rows (2-10): ",
                DotsAndBoxesBoard.MIN_SIZE, DotsAndBoxesBoard.MAX_SIZE);

        int cols = inputHandler.getIntInRange(
                "Enter number of columns (2-10): ",
                DotsAndBoxesBoard.MIN_SIZE, DotsAndBoxesBoard.MAX_SIZE);

        try {
            DotsAndBoxesGame game = new DotsAndBoxesGame(player1, player2, rows, cols, statistics);
            game.play();
        } catch (IllegalArgumentException e) {
            ColorPrinter.printlnRed("Oops! Could not create game: " + e.getMessage());
        }
    }

    /**
     * Show the final game stats before exiting
     */
    private void showFinalStatistics() {
        ColorPrinter.printlnCyan("\n╔═══════════════════════════════════════╗");
        ColorPrinter.printlnCyan("║            FINAL GAME SUMMARY          ║");
        ColorPrinter.printlnCyan("╚═══════════════════════════════════════╝");
        statistics.displayAllStatistics();
    }
}
