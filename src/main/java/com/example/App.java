package com.example;

public class App {

    public static void main(String[] args) {
        Utils.clearScreen();
        System.out.println(Utils.PrintColor("Welcome to tic tac toe!!!", Utils.YELLOW));
        Utils.printSpace(2);
        new Game();
    }
}
