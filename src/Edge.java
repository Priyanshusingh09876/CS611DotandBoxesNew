/**
 * Edge.java
 * Represents an edge in the Dots and Boxes game.
 * An edge can be horizontal or vertical and can be claimed by a player.
 *
 * @author CS611 Student
 * @version 1.0
 */
public class Edge {
    private int row;
    private int col;
    private boolean isHorizontal;
    private boolean isClaimed;
    private char claimedBy;

    /**
     * Constructor - creates an edge at specified position
     * @param row row position
     * @param col column position
     * @param isHorizontal true if horizontal, false if vertical
     */
    public Edge(int row, int col, boolean isHorizontal) {
        this.row = row;
        this.col = col;
        this.isHorizontal = isHorizontal;
        this.isClaimed = false;
        this.claimedBy = ' ';
    }

    /**
     * Gets the row position
     * @return row index
     */
    public int getRow() {
        return row;
    }

    /**
     * Gets the column position
     * @return column index
     */
    public int getCol() {
        return col;
    }

    /**
     * Checks if edge is horizontal
     * @return true if horizontal, false if vertical
     */
    public boolean isHorizontal() {
        return isHorizontal;
    }

    /**
     * Checks if edge has been claimed
     * @return true if claimed, false otherwise
     */
    public boolean isClaimed() {
        return isClaimed;
    }

    /**
     * Gets the symbol of the player who claimed this edge
     * @return player symbol or space if unclaimed
     */
    public char getClaimedBy() {
        return claimedBy;
    }

    /**
     * Claims this edge for a player
     * @param playerSymbol symbol of the player claiming the edge
     * @return true if successfully claimed, false if already claimed
     */
    public boolean claim(char playerSymbol) {
        if (isClaimed) {
            return false;
        }
        this.isClaimed = true;
        this.claimedBy = playerSymbol;
        return true;
    }

    /**
     * Returns string representation of the edge
     * @return formatted edge info
     */
    @Override
    public String toString() {
        String type = isHorizontal ? "H" : "V";
        String status = isClaimed ? "claimed by " + claimedBy : "unclaimed";
        return type + "(" + row + "," + col + ") " + status;
    }
}