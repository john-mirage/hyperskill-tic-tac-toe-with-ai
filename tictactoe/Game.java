package tictactoe;

public class Game {
    private final Board board;
    private final Player player1;
    private final Player player2;

    public Game(Player player1, Player player2) {
        this.board = new Board();
        this.player1 = player1;
        this.player2 = player2;
    }

    public void play() {
        Player currentPlayer = player1;
        while (true) {
            board.printBoard();
            int[] move = currentPlayer.getMove(board);
            board.makeMove(move[0], move[1], currentPlayer.getSymbol());

            if (board.checkWinner(currentPlayer.getSymbol())) {
                board.printBoard();
                System.out.printf("%c wins\n", currentPlayer.getSymbol());
                break;
            }

            if (board.isFull()) {
                board.printBoard();
                System.out.println("Draw");
                break;
            }

            currentPlayer = (currentPlayer == player1) ? player2 : player1;
        }
    }
}
