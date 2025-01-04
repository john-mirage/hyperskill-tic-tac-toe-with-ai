package tictactoe;

import java.util.Scanner;

public class Human extends Player {
    private final Scanner scanner;

    public Human(char symbol) {
        super(symbol);
        scanner = new Scanner(System.in);
    }

    @Override
    public int[] getMove(Board board) {
        while (true) {
            System.out.print("Enter the coordinates: ");
            String input = this.scanner.nextLine();
            if (input.matches("^\\d+ \\d+$")) {
                if (input.matches("^[1-3] [1-3]$")) {
                    String[] coordinates = input.split(" ");
                    int row = Integer.parseInt(coordinates[0]) - 1;
                    int col = Integer.parseInt(coordinates[1]) - 1;
                    if (board.isEmptyCell(row, col)) {
                        return new int[] {row, col};
                    } else {
                        System.out.println("This cell is occupied! Choose another one!");
                    }
                } else {
                    System.out.println("Coordinates should be from 1 to 3!");
                }
            } else {
                System.out.println("You should enter numbers!");
            }
        }
    }
}
