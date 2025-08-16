package WeekFive;

// PA5 is a battleship board design to demonstrate an understanding of arrays
// Created by Aman Sarosh

import java.util.Scanner;


public class PA5 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[][] board = createBoard();

        // Putting ships down
        placeShip(board, 1, 1, 5, "H");  // Carrier
        placeShip(board, 3, 5, 4, "V"); // Battleship
        placeShip(board, 8, 2, 3, "H"); // Cruiser
        placeShip(board, 10, 7, 3, "V");// Submarine
        placeShip(board, 17, 2, 2, "H"); // Destroyer

        printBoard(board);

        // Let user fire two shots and save input
        for (int i = 0; i < 2; i++) {
            System.out.print("\nEnter row (1-20): ");
            int r = sc.nextInt();
            System.out.print("Enter col (1-20): ");
            int c = sc.nextInt();

            boolean hitResult = damage(board, r, c);
            System.out.println(hitResult ? "Hit" : "Miss!");
            printBoard(board);
        }

        sc.close();
    }

    // Board size and Symbols
    private static final int board_size = 20;

    private static final char ship  = '~';
    private static final char hit   = 'X';
    private static final char miss  = '?';
    private static final char empty = '0';

    // Technically array is 21x21, but this way of formatting allows for easier readability
    public static char[][] createBoard() {
        char[][] board = new char[board_size + 1][board_size + 1];
        for (int r = 1; r <= board_size; r++) {
            for (int c = 1; c <= board_size; c++) {
                board[r][c] = empty;
            }
        }
        return board;
    }

    // Logic for placing the ship with handling if overlapped vertically or horizontally
    public static boolean placeShip(char[][] board, int row, int col, int length, String direction) {
        if (direction.equalsIgnoreCase("H")) {
            if (col + length - 1 > board_size) return false; // off board
            for (int c = col; c < col + length; c++) {
                if (board[row][c] == ship) return false; // overlap
            }
            for (int c = col; c < col + length; c++) {
                board[row][c] = ship;
            }
        } else if (direction.equalsIgnoreCase("V")) {
            if (row + length - 1 > board_size) return false; // off of board
            for (int r = row; r < row + length; r++) {
                if (board[r][col] == ship) return false; // overlap
            }
            for (int r = row; r < row + length; r++) {
                board[r][col] = ship;
            }
        } else {
            return false;
        }
        return true;
    }

    // Symbol Logic
    public static boolean damage(char[][] board, int row, int col) {
        if (row < 1 || row > board_size || col < 1 || col > board_size) {
            System.out.println("Coordinates not on board");
            return false;
        }
        if (board[row][col] == ship) {
            board[row][col] = hit;
            return true;
        } else if (board[row][col] == empty) {
            board[row][col] = miss;
            return false;
        } else {
            // already shot here
            return false;
        }
    }

    // Board Labels
    public static void printBoard(char[][] board) {
        // Print column labels
        System.out.print("    ");
        for (int c = 1; c <= board_size; c++) {
            System.out.printf("%2d ", c);
        }
        System.out.println();

        // Row labels
        for (int r = 1; r <= board_size; r++) {
            System.out.printf("%2d | ", r);
            for (int c = 1; c <= board_size; c++) {
                System.out.print(board[r][c] + "  ");
            }
            System.out.println();
        }
    }
}
