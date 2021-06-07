package com.example.quiz_game_project.data.remote.questions.models;

public class QuestionsSportResponse {

    String category;
    String type;
    String difficulty;
    String question;
    String correct_answer;
    String [] incorrect_answers;

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
