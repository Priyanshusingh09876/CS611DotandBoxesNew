import java.util.HashMap;
import java.util.Map;

/**
 * PlayerStatistics.java
 *
 * This class keeps track of players' stats for two different games: Dots and Boxes and Sliding Puzzle.
 *
 * Author: CS611 Student
 * Version: 1.0
 */
public class PlayerStatistics {
    private Map<String, PlayerRecord> records;
    private int totalDotsAndBoxesGames;
    private int totalPuzzleGames;

    /**
     * This little class holds stats for just one player.
     * It remembers wins, losses, ties for Dots and Boxes,
     * along with puzzle-related stats for Sliding Puzzle.
     */
    private class PlayerRecord {
        String name;
        int wins;
        int losses;
        int ties;
        int totalBoxes;      // total number of boxes the player scored in Dots and Boxes
        int puzzlesSolved;
        int totalMoves;      // total moves made in sliding puzzle games
        int bestMoves;       // best (lowest) number of moves in a puzzle game

        PlayerRecord(String name) {
            this.name = name;
            wins = 0;
            losses = 0;
            ties = 0;
            totalBoxes = 0;
            puzzlesSolved = 0;
            totalMoves = 0;
            bestMoves = Integer.MAX_VALUE; // start with a very high number so we can track improvements
        }
    }

    /**
     * Just sets everything up so the stats system is ready to go.
     */
    public PlayerStatistics() {
        records = new HashMap<>();
        totalDotsAndBoxesGames = 0;
        totalPuzzleGames = 0;
    }

    /**
     * Call this whenever a Dots and Boxes game ends.
     * It records who won, who lost, or if it was a tie,
     * plus it adds the number of boxes each player scored.
     *
     * @param player1 One player in the match
     * @param player2 The other player
     * @param winner The player that won, or null if it was a tie
     */
    public void recordGame(Player player1, Player player2, Player winner) {
        totalDotsAndBoxesGames++;

        PlayerRecord p1Record = getOrCreateRecord(player1.getName());
        PlayerRecord p2Record = getOrCreateRecord(player2.getName());

        p1Record.totalBoxes += player1.getScore();
        p2Record.totalBoxes += player2.getScore();

        if (winner == null) {
            p1Record.ties++;
            p2Record.ties++;
        } else if (winner == player1) {
            p1Record.wins++;
            p2Record.losses++;
        } else {
            p2Record.wins++;
            p1Record.losses++;
        }
    }

    /**
     * When a player finishes a Sliding Puzzle game, call this to save how many moves they took.
     * It will also check if it's their best score so far.
     *
     * @param playerName The name of the player
     * @param moves How many moves it took to solve the puzzle
     */
    public void recordPuzzleGame(String playerName, int moves) {
        totalPuzzleGames++;

        PlayerRecord record = getOrCreateRecord(playerName);
        record.puzzlesSolved++;
        record.totalMoves += moves;

        if (moves < record.bestMoves) {
            record.bestMoves = moves;
        }
    }

    /**
     * Looks up a player's stats record. If the player isn’t in the system yet,
     * it creates a new record for them to start tracking.
     *
     * @param playerName The player’s name
     * @return The stats record for that player
     */
    private PlayerRecord getOrCreateRecord(String playerName) {
        if (!records.containsKey(playerName)) {
            records.put(playerName, new PlayerRecord(playerName));
        }
        return records.get(playerName);
    }

    /**
     * This shows you the stats for every player we know about.
     * If no games have happened yet, it tells you so.
     */
    public void displayAllStatistics() {
        if (totalDotsAndBoxesGames == 0 && totalPuzzleGames == 0) {
            ColorPrinter.printlnYellow("\nNo games played yet!");
            return;
        }

        ColorPrinter.printlnCyan("\n╔═══════════════════════════════════════════════════════════╗");
        ColorPrinter.printlnCyan("║                     PLAYER STATS                          ║");
        ColorPrinter.printlnCyan("╚═══════════════════════════════════════════════════════════╝");

        System.out.println("\nTotal Dots and Boxes Games: " + totalDotsAndBoxesGames);
        System.out.println("Total Sliding Puzzle Games: " + totalPuzzleGames);
        System.out.println("\n" + "─".repeat(60));

        for (PlayerRecord record : records.values()) {
            displayPlayerRecord(record);
            System.out.println("─".repeat(60));
        }
    }

    /**
     * Prints out the stats for one player in a nice, readable format.
     * Shows both games they played and their best scores.
     */
    private void displayPlayerRecord(PlayerRecord record) {
        ColorPrinter.printCyan("\n★ Player: ");
        ColorPrinter.printlnYellow(record.name);

        int dotsGamesPlayed = record.wins + record.losses + record.ties;
        if (dotsGamesPlayed > 0) {
            double winRate = (record.wins * 100.0 / dotsGamesPlayed);
            double avgBoxes = (record.totalBoxes * 1.0 / dotsGamesPlayed);

            ColorPrinter.printlnCyan("\n  Dots and Boxes:");
            System.out.println("    Games Played: " + dotsGamesPlayed);
            ColorPrinter.printGreen("    Wins: " + record.wins);
            System.out.println();
            ColorPrinter.printRed("    Losses: " + record.losses);
            System.out.println();
            ColorPrinter.printYellow("    Ties: " + record.ties);
            System.out.println();
            System.out.printf("    Win Rate: %.1f%%\n", winRate);
            System.out.printf("    Average Boxes per Game: %.1f\n", avgBoxes);
            System.out.println("    Total Boxes Scored: " + record.totalBoxes);
        }

        if (record.puzzlesSolved > 0) {
            double avgMoves = (record.totalMoves * 1.0 / record.puzzlesSolved);

            ColorPrinter.printlnCyan("\n  Sliding Puzzle:");
            System.out.println("    Puzzles Solved: " + record.puzzlesSolved);
            System.out.println("    Best Score: " + record.bestMoves + " moves");
            System.out.printf("    Average Moves: %.1f\n", avgMoves);
            System.out.println("    Total Moves: " + record.totalMoves);
        }

        if (dotsGamesPlayed == 0 && record.puzzlesSolved == 0) {
            ColorPrinter.printlnYellow("\n  No games played yet!");
        }
    }

    /**
     * Just a quick way to get the total Dots and Boxes games played.
     */
    public int getTotalDotsAndBoxesGames() {
        return totalDotsAndBoxesGames;
    }

    /**
     * Same thing for Sliding Puzzle games.
     */
    public int getTotalPuzzleGames() {
        return totalPuzzleGames;
    }
}
