package com.example;

public class Player {
    public String name;
    public int id;
    public char assignedChar;
    public Boolean winner;

    Player(int id) {
        this.winner = false;
        this.id = id;
    }
}
