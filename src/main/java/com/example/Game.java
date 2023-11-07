package com.example;

import java.util.Random;
import java.util.Scanner;

public class Game {

    char[][] board = { { '1', '2', '3' }, { '4', '5', '6' }, { '7', '8', '9' } };
    Player player1;
    Player player2;
    int winnerID;
    int moves = 0;

    private void printBoard() {

        System.out.println(Utils.PrintColor("-------------", Utils.YELLOW));
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (j == 0) {

                    System.out.print(Utils.PrintColor("| ", Utils.YELLOW));
                }
                System.out.print(board[i][j]);
                if (j != board[0].length - 1) {
                    System.out.print(Utils.PrintColor(" | ", Utils.YELLOW));
                } else {

                    System.out.print(Utils.PrintColor(" |", Utils.YELLOW));
                }
            }
            System.out.print(Utils.PrintColor("\n-------------\n", Utils.YELLOW));
        }

    }

    Game() {

        moves = 0;
        player1 = new Player(1);
        player2 = new Player(2);
        toss();
        start();

    }

    private Player currentPlayer() {

        if (moves % 2 == 0) {

            return winnerID == player1.id ? player1 : player2;

        } else {

            return winnerID == player1.id ? player2 : player1;
        }

    }

    private int takeInput() {

        Utils.printSpace(2);
        Scanner placeInput = new Scanner(System.in);
        System.out.printf(Utils.PrintColor("Player %d\n", Utils.GREEN), currentPlayer().id);
        System.out.printf("Where do you want to put %c (1-9)" + Utils.PrintColor("(0 to exit): ", Utils.RED),
                currentPlayer().assignedChar);
        char place = placeInput.next().charAt(0);
        if (place > '9' || place < '0') {
            return -2;
        }

        if (place == '0') {
            return -1;
        }

        int ans = Character.getNumericValue(place) - 1;

        int row = ans / 3;
        int column = ans % 3;
        board[row][column] = currentPlayer().assignedChar;
        moves++;

        return 0;
    }

    public void start() {

        int inp = 0;
        for (;;) {
            if (inp != -2) {

                Utils.clearScreen();
                printBoard();
            }
            inp = takeInput();
            if (inp == -1) {
                break;
            } else if (inp == -2) {
                System.out.println(Utils.PrintColor("Invalid input", Utils.RED));
            }

            if (moves == 9) {
                System.out.println("Game has ended with draw!");
                break;
            }

            char res = checkForWin();

            if (res != 'a') {

                Utils.clearScreen();
                Player winnerPlayer = player1.assignedChar == res ? player1 : player2;
                System.out.printf("Winner is Player %d\n", winnerPlayer.id);
                printBoard();
                break;
            }
        }

    }

    public void toss() {
        Random rand = new Random();
        int winner = rand.nextInt(2 - 1 + 1) + 1;

        if (winner == 2) {
            winnerID = player2.id;
        } else {
            winnerID = player1.id;
        }

        Scanner input = new Scanner(System.in);
        System.out.printf(Utils.PrintColor("Player %d won the toss.\n", Utils.GREEN), winner);
        System.out.println("Do you want to pick X. Y/N ?");
        char ans = input.next().charAt(0);
        ans = Character.toLowerCase(ans);
        System.out.println(ans);
        if ((ans == 'y' && winner == 1) || (ans == 'n' && winner == 2)) {
            player1.assignedChar = 'x';
            player2.assignedChar = '0';
        } else {
            player1.assignedChar = '0';
            player2.assignedChar = 'x';
        }
        System.out.printf("Player 1 is %c.\n", player1.assignedChar);
        System.out.printf("Player 2 is %c.\n", player2.assignedChar);
    }

    private char checkForWin() {

        // check for rows
        for (int i = 0; i < board.length; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return board[i][0];
            }
        }

        // check for columns
        for (int i = 0; i < board.length; i++) {
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                return board[0][i];
            }
        }

        // check for diagnoals
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return board[0][0];
        }

        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {

            return board[1][1];
        }

        return 'a';

    }
}
