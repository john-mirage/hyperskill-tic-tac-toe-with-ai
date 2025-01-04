package tictactoe;

public abstract class Player {
    protected char symbol;

    public Player(char symbol) {
        if (symbol != 'X' && symbol != 'O') {
            throw new IllegalArgumentException("Invalid symbol");
        }
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public abstract int[] getMove(Board board);
}
