import java.util.Scanner;

/**
 * InputHandler.java
 * Centralized class for handling all user input with validation.
 * Provides methods for getting validated integers, strings, and coordinates.
 *
 * @author CS611 Student
 * @version 1.0
 */
public class InputHandler {
    private Scanner scanner;

    /**
     * Constructor - initializes the scanner
     */
    public InputHandler() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Gets a non-empty string from the user
     * @return validated non-empty string
     */
    public String getNonEmptyString() {
        String input;
        while (true) {
            input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            ColorPrinter.printlnRed("Input cannot be empty. Please try again: ");
        }
    }

    /**
     * Gets an integer within a specified range from the user
     * @param prompt message to display
     * @param min minimum valid value (inclusive)
     * @param max maximum valid value (inclusive)
     * @return validated integer within range
     */
    public int getIntInRange(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            // Check for quit command
            if (input.equalsIgnoreCase("quit") || input.equalsIgnoreCase("q")) {
                return -1; // Special value to indicate quit
            }

            try {
                int value = Integer.parseInt(input);
                if (value >= min && value <= max) {
                    return value;
                } else {
                    ColorPrinter.printlnRed(
                            "Please enter a number between " + min + " and " + max + "."
                    );
                }
            } catch (NumberFormatException e) {
                ColorPrinter.printlnRed(
                        "Invalid input. Please enter a valid number or 'quit' to exit."
                );
            }
        }
    }

    /**
     * Gets a string input from the user (allows empty)
     * @param prompt message to display
     * @return user input string
     */
    public String getString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    /**
     * Gets a yes/no confirmation from the user
     * @param prompt message to display
     * @return true if yes, false if no
     */
    public boolean getYesNo(String prompt) {
        while (true) {
            System.out.print(prompt + " (y/n): ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("y") || input.equals("yes")) {
                return true;
            } else if (input.equals("n") || input.equals("no")) {
                return false;
            } else {
                ColorPrinter.printlnRed("Please enter 'y' or 'n'.");
            }
        }
    }

    /**
     * Closes the scanner (call when done with input)
     */
    public void close() {
        scanner.close();
    }
}