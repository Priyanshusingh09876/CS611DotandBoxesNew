public class Player {
    private String name;
    private char symbol;
    private int score;
    private int gamesWon;
    private int gamesPlayed;

    public Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
        this.score = 0;
        this.gamesWon = 0;
        this.gamesPlayed = 0;
    }

    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }

    public int getScore() {
        return score;
    }

    public void incrementScore() {
        score++;
    }

    public void resetScore() {
        score = 0;
    }

    public void recordGame() {
        gamesPlayed++;
    }

    public void recordWin() {
        gamesWon++;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    @Override
    public String toString() {
        return name + " (" + symbol + ")";
    }
}
