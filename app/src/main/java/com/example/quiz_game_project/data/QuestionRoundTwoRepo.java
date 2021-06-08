package com.example.quiz_game_project.data;

import com.example.quiz_game_project.data.remote.questions.models.Result;

public class QuestionRoundTwoRepo {

    private Result[] roundTwoQuestions;
    private Result[] roundTwoQuestions2;
    private static QuestionRoundTwoRepo instance;

    public static QuestionRoundTwoRepo getInstance() {
        if(instance == null) instance = new QuestionRoundTwoRepo();
        return instance;
    }

    private QuestionRoundTwoRepo() {
        this.roundTwoQuestions = new Result[]{};
        this.roundTwoQuestions2 = new Result[]{};
    }

    public Result[] getRoundTwoQuestions() {
        return roundTwoQuestions;
    }
    public void setRoundTwoQuestions(Result[] roundTwoQuestions) {
        this.roundTwoQuestions = roundTwoQuestions;
    }

    public Result[] getRoundTwoQuestions2() {
        return roundTwoQuestions2;
    }
    public void setRoundTwoQuestions2(Result[] roundTwoQuestions2) {
        this.roundTwoQuestions2 = roundTwoQuestions2;
    }

    public String getQuestion(int i) {
        return roundTwoQuestions[i].question;
    }
    public String getQuestion2(int i) {
        return roundTwoQuestions2[i].question;
    }

    public String[] getIncorrectAnswers(int i) {
        return roundTwoQuestions[i].incorrect_answers;
    }
    public String[] getIncorrectAnswers2(int i) {
        return roundTwoQuestions2[i].incorrect_answers;
    }


    public String getCorrectAnswer(int i) {
        return roundTwoQuestions[i].correct_answer;
    }
    public String getCorrectAnswer2(int i) {
        return roundTwoQuestions2[i].correct_answer;
    }


}
