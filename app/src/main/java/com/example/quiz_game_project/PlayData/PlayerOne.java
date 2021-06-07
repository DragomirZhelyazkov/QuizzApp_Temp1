package com.example.quiz_game_project.PlayData;

import androidx.core.content.ContextCompat;

public class PlayerOne {

    public static String name;
    public static String color = "yellow";
    public static int playerOneScore;

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.playerOneScore = playerOneScore;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return playerOneScore;
    }
}
