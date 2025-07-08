import java.util.Scanner;

public class TicTacToe {
    static char[] board = { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' };
    static char currentPlayer = 'X';
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Tic Tac Toe ===");
        printBoard();

        while (true) {
            playerMove();
            printBoard();

            if (checkWinner()) {
                System.out.println("🎉 Player " + currentPlayer + " wins!");
                break;
            } else if (isDraw()) {
                System.out.println("It's a draw!");
                break;
            }

            switchPlayer();
        }
    }

    static void printBoard() {
        System.out.println();
        System.out.println(" " + board[0] + " | " + board[1] + " | " + board[2]);
        System.out.println("---+---+---");
        System.out.println(" " + board[3] + " | " + board[4] + " | " + board[5]);
        System.out.println("---+---+---");
        System.out.println(" " + board[6] + " | " + board[7] + " | " + board[8]);
        System.out.println();
    }

    static void playerMove() {
        int move;
        while (true) {
            System.out.print("Player " + currentPlayer + ", enter your move (1-9): ");
            String input = scanner.nextLine();
            try {
                move = Integer.parseInt(input) - 1;
                if (move >= 0 && move < 9 && board[move] == ' ') {
                    board[move] = currentPlayer;
                    break;
                } else {
                    System.out.println("❌ Invalid move. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Please enter a number from 1 to 9.");
            }
        }
    }

    static void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    static boolean checkWinner() {
        int[][] combos = {
            {0,1,2}, {3,4,5}, {6,7,8}, // rows
            {0,3,6}, {1,4,7}, {2,5,8}, // columns
            {0,4,8}, {2,4,6}           // diagonals
        };
        for (int[] combo : combos) {
            if (board[combo[0]] == currentPlayer &&
                board[combo[1]] == currentPlayer &&
                board[combo[2]] == currentPlayer) {
                return true;
            }
        }
        return false;
    }

    static boolean isDraw() {
        for (char c : board) {
            if (c == ' ') return false;
        }
        return true;
    }
}
