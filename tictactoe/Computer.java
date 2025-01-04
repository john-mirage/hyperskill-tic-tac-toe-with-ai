package tictactoe;

import java.util.List;
import java.util.Random;

public class Computer extends Player {
    private final Difficulty difficulty;

    public Computer(char symbol, Difficulty difficulty) {
        super(symbol);
        this.difficulty = difficulty;
    }

    @Override
    public int[] getMove(Board board) {
        if (board == null) {
            throw new IllegalArgumentException("Board cannot be null");
        }
        if (difficulty == Difficulty.EASY) {
            System.out.println("Making move level \"easy\"");
            return getRandomMove(board);
        } else if (difficulty == Difficulty.MEDIUM) {
            System.out.println("Making move level \"medium\"");
            return getMediumMove(board);
        } else {
            System.out.println("Making move level \"hard\"");
            return getBestMove(board);
        }
    }

    private int[] getRandomMove(Board board) {
        if (board == null) {
            throw new IllegalArgumentException("Board cannot be null");
        }
        List<int[]> moves = board.getAvailableMoves();
        return moves.get(new Random().nextInt(moves.size()));
    }

    private int[] getMediumMove(Board board) {
        if (board == null) {
            throw new IllegalArgumentException("Board cannot be null");
        }
        // Check for winning or blocking moves
        for (int[] move : board.getAvailableMoves()) {
            // Try winning move
            board.makeMove(move[0], move[1], symbol);
            if (board.checkWinner(symbol)) {
                board.resetCell(move[0], move[1]);
                return move;
            }
            board.resetCell(move[0], move[1]);

            // Try blocking move
            char opponent = (symbol == 'X') ? 'O' : 'X';
            board.makeMove(move[0], move[1], opponent);
            if (board.checkWinner(opponent)) {
                board.resetCell(move[0], move[1]);
                return move;
            }
            board.resetCell(move[0], move[1]);
        }
        // Otherwise, random move
        return getRandomMove(board);
    }

    private int[] getBestMove(Board board) {
        if (board == null) {
            throw new IllegalArgumentException("Board cannot be null");
        }
        int bestScore = Integer.MIN_VALUE;
        int[] bestMove = null;
        for (int[] move : board.getAvailableMoves()) {
            board.makeMove(move[0], move[1], symbol);
            int score = minimax(board, false, symbol);
            board.resetCell(move[0], move[1]);
            if (score > bestScore) {
                bestScore = score;
                bestMove = move;
            }
        }
        return bestMove;
    }

    private int minimax(Board board, boolean isMaximizing, char playerSymbol) {
        if (board == null) {
            throw new IllegalArgumentException("Board cannot be null");
        }
        if (playerSymbol != 'X' && playerSymbol != 'O') {
            throw new IllegalArgumentException("Invalid symbol");
        }
        char opponent = (playerSymbol == 'X') ? 'O' : 'X';
        if (board.checkWinner(playerSymbol)) return 10;
        if (board.checkWinner(opponent)) return -10;
        if (board.isFull()) return 0;

        int bestScore;
        if (isMaximizing) {
            bestScore = Integer.MIN_VALUE;
            for (int[] move : board.getAvailableMoves()) {
                board.makeMove(move[0], move[1], playerSymbol);
                int score = minimax(board, false, playerSymbol);
                board.resetCell(move[0], move[1]);
                bestScore = Math.max(bestScore, score);
            }
        } else {
            bestScore = Integer.MAX_VALUE;
            for (int[] move : board.getAvailableMoves()) {
                board.makeMove(move[0], move[1], opponent);
                int score = minimax(board, true, playerSymbol);
                board.resetCell(move[0], move[1]);
                bestScore = Math.min(bestScore, score);
            }
        }
        return bestScore;
    }
}
