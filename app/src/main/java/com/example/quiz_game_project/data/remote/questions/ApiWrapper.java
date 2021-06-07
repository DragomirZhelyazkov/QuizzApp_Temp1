package com.example.quiz_game_project.data.remote.questions;

import android.util.Log;

import com.example.quiz_game_project.data.QuestionsCategoryOneRepo;
import com.example.quiz_game_project.data.local.AsyncDatabase;
import com.example.quiz_game_project.data.remote.questions.models.AllCategories;
import com.example.quiz_game_project.data.remote.questions.models.Category;
import com.example.quiz_game_project.data.remote.questions.models.QuestionsResponse;
import com.example.quiz_game_project.data.remote.questions.models.Result;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiWrapper {

//    private final QuestionsApiService service;
//
//    public ApiWrapper(AsyncDatabase asyncDatabase, QuestionsApiService service) {
//        this.service = service;
//    }

    private static ApiWrapper instance;
    private final QuestionsApiService service;

    public static void init(AsyncDatabase asyncDatabase) {
        if(instance == null) instance = new ApiWrapper(asyncDatabase);
    }

    public static ApiWrapper getInstance() {
        return instance;
    }

    private ApiWrapper(AsyncDatabase asyncDatabase) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://opentdb.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(QuestionsApiService.class);
    }

    public void getAllCategories(OnApiResultListener<Category []> callback) {
        service.getAllCategs().enqueue(new Callback<AllCategories>() {
            @Override
            public void onResponse(Call<AllCategories> call, Response<AllCategories> response) {
//                List<Category> allCategory = new ArrayList<>();
                if (response.isSuccessful()) {
                    AllCategories categories = response.body();


                    callback.onSuccess(categories.getAllCategorys());
                } else {
                    callback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<AllCategories> call, Throwable t) {
                callback.onFailure();
            }
        });
    }




    public void getTheQuestions(int numberOfQuestions, int chosenCategory, int difficulty,
                                String type1, OnApiResultListener<Result[]> callback) {
        String difficultyLevel = setDificulty(difficulty);
        service.getQuestions(numberOfQuestions, chosenCategory, difficultyLevel,
                type1).enqueue(new Callback<QuestionsResponse>() {
            @Override
            public void onResponse(Call<QuestionsResponse> call, Response<QuestionsResponse> response) {
                if (response.isSuccessful()) {
                   QuestionsResponse questionsResponse = response.body();
                   callback.onSuccess(questionsResponse.getResults());
                } else {
                    callback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<QuestionsResponse> call, Throwable t) {
                callback.onFailure();
            }
        });
    }
    //ново!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public void getRoundTwoQuestions(int numberOfQuestions, int chosenCategory, int difficulty,
                                     String type, OnApiResultListener<Result[]> callback) {
//        Log.i("Info log",
//                "-----@@@@@@@@------ "  + numberOfQuestions + chosenCategory + type + difficulty);
        String difficultyLevel = setDificulty(difficulty);
//        Log.i("Info log",
//                "-----@@@@@@@@------ "  + difficultyLevel);
        service.getRoundTwoQuestions(numberOfQuestions, chosenCategory, difficultyLevel,
                type).enqueue(new Callback<QuestionsResponse>() {
            @Override
            public void onResponse(Call<QuestionsResponse> call, Response<QuestionsResponse> response) {
                if (response.isSuccessful()) {
                    QuestionsResponse questionsResponse = response.body();
                    callback.onSuccess(questionsResponse.getResults());
                } else {
                    callback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<QuestionsResponse> call, Throwable t) {
                callback.onFailure();
            }
        });
    }






    private String setDificulty(int difficulty) {
        String level = "easy";
        switch (difficulty){
            case 1:
                level = "easy";
                break;
            case 2:
                level = "medium";
                break;
            case 3:
                level = "hard";
                break;
        }
        return level;
    }

    public interface OnApiResultListener<T> {
        void onSuccess(T data);
        void onFailure();
    }
}
