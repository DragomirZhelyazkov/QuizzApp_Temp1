package com.example.quiz_game_project.data;

import com.example.quiz_game_project.data.remote.questions.models.Category;
import com.example.quiz_game_project.data.remote.questions.models.Result;

public class QuestionsCategoryOneRepo {

    private Result[] categotyOneQuestions;

    private static QuestionsCategoryOneRepo instance;

    public static QuestionsCategoryOneRepo getInstance() {
        if(instance == null) instance = new QuestionsCategoryOneRepo();
        return instance;
    }

    private QuestionsCategoryOneRepo() {
        this.categotyOneQuestions = new Result[]{};
    }

    public Result[] getCategotyOneQuestions() {
        return categotyOneQuestions;
    }

    public void setCategotyOneQuestions(Result[] categotyOneQuestions) {
        this.categotyOneQuestions = categotyOneQuestions;
    }

    public String getQuestion(int i){
        return categotyOneQuestions[i].question;
    }

    public String[] getIncorrectAnswers(int i){
        return categotyOneQuestions[i].incorrect_answers;
    }

    public String getCorrectAnswer(int i){
        return categotyOneQuestions[i].correct_answer;
    }
}
