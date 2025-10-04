/**
 * ColorPrinter.java
 *
 * Wraps common colors and simplifies colored printing with or without newlines.
 *
 * Author: Priyanshu and Lyu
 * Version: 1.1
 */
public class ColorPrinter {

    // ANSI codes for various colors used in the console output
    private static final String RESET = "\u001B[0m";

    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String MAGENTA = "\u001B[35m";
    private static final String CYAN = "\u001B[36m";

    /**
     * Prints text in given color *without* a newline.
     *
     * @param text to print
     * @param color ansi code for color
     */
    private static void printColored(String text, String color) {
        System.out.print(color + text + RESET);
    }

    /**
     * Prints text in given color *with* a newline.
     *
     * @param text to print
     * @param color ansi code for color
     */
    private static void printlnColored(String text, String color) {
        System.out.println(color + text + RESET);
    }

    // Overloaded print methods: with and without newline

    public static void printYellow(String text) { printColored(text, YELLOW); }
    public static void printlnYellow(String text) { printlnColored(text, YELLOW); }

    public static void printGreen(String text) { printColored(text, GREEN); }
    public static void printlnGreen(String text) { printlnColored(text, GREEN); }

    public static void printCyan(String text) { printColored(text, CYAN); }
    public static void printlnCyan(String text) { printlnColored(text, CYAN); }

    public static void printRed(String text) { printColored(text, RED); }
    public static void printlnRed(String text) { printlnColored(text, RED); }

    public static void printBlue(String text) { printColored(text, BLUE); }
    public static void printlnBlue(String text) { printlnColored(text, BLUE); }

    public static void printMagenta(String text) { printColored(text, MAGENTA); }
    public static void printlnMagenta(String text) { printlnColored(text, MAGENTA); }

    /**
     * Example usage — remove or comment out when integrating.
     */
    public static void main(String[] args) {
        printlnYellow("⚡ Printing in Yellow");
        printlnGreen("✔ This is Green text");
        printCyan("ℹ Info message ");  // no newline
        printlnCyan("- followed by cyan text");
    }
}
