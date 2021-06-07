package com.example.quiz_game_project.data;

import com.example.quiz_game_project.data.remote.questions.models.Result;

public class QuestionsCategoryTwoRepo {

    private Result[] categotyTwoQuestions;

    private static QuestionsCategoryTwoRepo instance;

    public static QuestionsCategoryTwoRepo getInstance() {
        if(instance == null) instance = new QuestionsCategoryTwoRepo();
        return instance;
    }

    private QuestionsCategoryTwoRepo() {
        this.categotyTwoQuestions = new Result[]{};
    }

    public Result[] getCategotyTwoQuestions() {
        return categotyTwoQuestions;
    }

    public void setCategotyTwoQuestions(Result[] categotyTwoQuestions) {
        this.categotyTwoQuestions = categotyTwoQuestions;
    }

    public String getQuestion(int i){
        return categotyTwoQuestions[i].question;
    }

    public String[] getIncorrectAnswers(int i){
        return categotyTwoQuestions[i].incorrect_answers;
    }

    public String getCorrectAnswer(int i){
        return categotyTwoQuestions[i].correct_answer;
    }
}
