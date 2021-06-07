package com.example.quiz_game_project.data.remote.questions;

import com.example.quiz_game_project.data.remote.questions.models.AllCategories;
import com.example.quiz_game_project.data.remote.questions.models.QuestionsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface QuestionsApiService {

    @GET("api_category.php")
    Call<AllCategories> getAllCategs();

    @GET("api.php")
    Call<QuestionsResponse> getQuestions(@Query("amount") int numberOfQuestions,
                                         @Query("category") int categoryID,
                                         @Query("difficulty") String difficultyLevel,
                                         @Query("type") String type1);

    //ново
    @GET("api.php")
    Call<QuestionsResponse> getRoundTwoQuestions(
            @Query("amount") int numberOfQuestions,
            @Query("category") int categoryID,
            @Query("difficulty") String difficultyLevel,
            @Query("type") String type);
}

