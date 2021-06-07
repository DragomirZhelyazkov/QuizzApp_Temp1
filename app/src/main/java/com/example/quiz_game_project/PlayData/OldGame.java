package com.example.quiz_game_project.PlayData;

import com.example.quiz_game_project.ui.adapters.AdapterData;

public class OldGame implements AdapterData {

    private String icon;
    private String name;
    private String date;
    private String topPlayer;
    private int numberPlayers;

    public OldGame(String icon, String name, String date, String topPlayer, int numberPlayers) {
        this.icon = icon;
        this.name = name;
        this.date = date;
        this.topPlayer = topPlayer;
        this.numberPlayers = numberPlayers;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTopPlayer(String topPlayer) {
        this.topPlayer = topPlayer;
    }

    public void setNumberPlayers(int numberPlayers) {
        this.numberPlayers = numberPlayers;
    }

    public String getIcon() {
        return icon;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getTopPlayer() {
        return topPlayer;
    }

    public int getNumberPlayers() {
        return numberPlayers;
    }
}
