package com.example.quiz_game_project.ui.adapters;

import com.example.quiz_game_project.data.local.entities.GameEntity;

public class Header implements AdapterData {
    private String text;

    public Header(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
