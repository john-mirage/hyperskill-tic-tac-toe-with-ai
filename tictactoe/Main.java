package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Input command: ");
            String command = scanner.nextLine();
            if (command.matches("^start (?:user|easy|medium|hard) (?:user|easy|medium|hard)$|^exit$")) {
                String[] commandParts = command.split(" ");
                if (commandParts[0].equals("start")) {
                    Player player1 = getPlayer(commandParts[1], 'X');
                    Player player2 = getPlayer(commandParts[2], 'O');
                    Game game = new Game(player1, player2);
                    game.play();
                } else {
                    break;
                }
            } else {
                System.out.println("Bad parameters!");
            }
        }
    }

    private static Player getPlayer(String input, char symbol) {
        if (symbol != 'X' && symbol != 'O') {
            throw new IllegalArgumentException("Invalid symbol");
        }
        return switch (input) {
            case "user" -> new Human(symbol);
            case "easy" -> new Computer(symbol, Difficulty.EASY);
            case "medium" -> new Computer(symbol, Difficulty.MEDIUM);
            case "hard" -> new Computer(symbol, Difficulty.HARD);
            default -> throw new IllegalArgumentException("Invalid symbol");
        };
    }
}
