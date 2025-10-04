/**
 * Main.java
 * Entry point for the Dots and Boxes game application.
 * Initializes the game controller and starts the game loop.
 *
 * @author Priyanshu and Lyu
 * @version 1.0
 */
public class Main {
    /**
     * Main entry point - no logic here, just initialization
     * @param args command line arguments (unused)
     */
    public static void main(String[] args) {
        GameController controller = new GameController();
        controller.start();
    }
}
