package tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {
    private final char[][] grid;
    private static final int SIZE = 3;

    public Board() {
        this.grid = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            Arrays.fill(grid[i], ' ');
        }
    }

    public boolean isFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (this.grid[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isEmptyCell(int row, int col) {
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
            throw new IllegalArgumentException("Invalid row or column");
        }
        return this.grid[row][col] == ' ';
    }

    public void makeMove(int row, int col, char symbol) {
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
            throw new IllegalArgumentException("Invalid row or column");
        }
        if (symbol != 'X' && symbol != 'O') {
            throw new IllegalArgumentException("Invalid symbol");
        }
        if (isEmptyCell(row, col)) {
            this.grid[row][col] = symbol;
        }
    }

    public boolean checkWinner(char symbol) {
        if (symbol != 'X' && symbol != 'O') {
            throw new IllegalArgumentException("Invalid symbol");
        }
        for (int i = 0; i < SIZE; i++) {
            if ((grid[i][0] == symbol && grid[i][1] == symbol && grid[i][2] == symbol) ||
                (grid[0][i] == symbol && grid[1][i] == symbol && grid[2][i] == symbol)) {
                return true;
            }
        }
        return (grid[0][0] == symbol && grid[1][1] == symbol && grid[2][2] == symbol) ||
                (grid[0][2] == symbol && grid[1][1] == symbol && grid[2][0] == symbol);
    }

    public void printBoard() {
        System.out.printf("""
               ---------
               | %c %c %c |
               | %c %c %c |
               | %c %c %c |
               ---------
               """,
                  this.grid[0][0]
                , this.grid[0][1]
                , this.grid[0][2]
                , this.grid[1][0]
                , this.grid[1][1]
                , this.grid[1][2]
                , this.grid[2][0]
                , this.grid[2][1]
                , this.grid[2][2]);
    }

    public List<int[]> getAvailableMoves() {
        List<int[]> moves = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (this.isEmptyCell(i, j)) {
                    moves.add(new int[]{i, j});
                }
            }
        }
        return moves;
    }

    public void resetCell(int row, int col) {
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
            throw new IllegalArgumentException("Invalid row or column");
        }
        grid[row][col] = ' ';
    }
}
