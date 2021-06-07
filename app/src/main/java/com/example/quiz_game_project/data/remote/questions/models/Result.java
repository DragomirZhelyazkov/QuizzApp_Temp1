package com.example.quiz_game_project.data.remote.questions.models;

public class Result {

    public String category;
    public String type;
    public String difficulty;
    public String question;
    public String correct_answer;
    public String [] incorrect_answers;

    public String getCategory() {
        return category;
    }

    public String getType() {
        return type;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getQuestion() {
        return question;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public String[] getIncorrect_answers() {
        return incorrect_answers;
    }

}

