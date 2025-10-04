/**
 * Represents a box in the Dots and Boxes game.
 */
public class Box {
    private int row;
    private int col;
    private Edge topEdge;
    private Edge bottomEdge;
    private Edge leftEdge;
    private Edge rightEdge;
    private boolean isCompleted;
    private char completedBy;

    public Box(int row, int col, Edge topEdge, Edge bottomEdge,
               Edge leftEdge, Edge rightEdge) {
        this.row = row;
        this.col = col;
        this.topEdge = topEdge;
        this.bottomEdge = bottomEdge;
        this.leftEdge = leftEdge;
        this.rightEdge = rightEdge;
        this.isCompleted = false;
        this.completedBy = ' ';
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public char getCompletedBy() {
        return completedBy;
    }

    /**
     * Checks if the box is completed and sets the player who completed it.
     * Assumes the box is completed by the player who claimed the last edge.
     *
     * @return true if just completed; false otherwise
     */
    public boolean checkAndComplete() {
        if (isCompleted) {
            return false;
        }

        if (topEdge.isClaimed() && bottomEdge.isClaimed() &&
                leftEdge.isClaimed() && rightEdge.isClaimed()) {
            isCompleted = true;
            completedBy = topEdge.getClaimedBy();
            return true;
        }
        return false;
    }

    public int getClaimedEdgeCount() {
        int count = 0;
        if (topEdge.isClaimed()) count++;
        if (bottomEdge.isClaimed()) count++;
        if (leftEdge.isClaimed()) count++;
        if (rightEdge.isClaimed()) count++;
        return count;
    }

    @Override
    public String toString() {
        String status = isCompleted ? "completed by " + completedBy : "incomplete";
        return "Box(" + row + "," + col + ") " + status;
    }
}
