package com.example.quiz_game_project.data.remote.questions.models;

import com.google.gson.JsonElement;

public class QuestionsResponse {

    public int response_code;

    public Result[] results;

    public Result[] getResults() {
        return results;
    }

}
