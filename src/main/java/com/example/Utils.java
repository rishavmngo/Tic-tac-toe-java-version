package com.example;

public class Utils {

    public static String RESET = "\u001B[0m";
    public static String RED = "\u001B[31m";
    public static String GREEN = "\u001B[32m";
    public static String YELLOW = "\u001B[33m";
    public static String WHITE = "\u001B[47m";

    public static void printSpace(int numberOfSpace) {

        for (int i = 0; i < numberOfSpace; i++) {
            System.out.println();
        }

    }

    public static String PrintColor(String string, String color) {

        return color + string + RESET;

    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J"); // ANSI escape codes for clearing the
        // screen
        System.out.flush();
    }
}
