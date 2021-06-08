package com.example.quiz_game_project.controllers;

import android.util.Log;

import com.example.quiz_game_project.data.QuestionRoundTwoRepo;
import com.example.quiz_game_project.data.local.AsyncDatabase;
import com.example.quiz_game_project.data.local.entities.GameEntity;
import com.example.quiz_game_project.data.remote.questions.ApiWrapper;
import com.example.quiz_game_project.data.remote.questions.models.Category;
import com.example.quiz_game_project.data.remote.questions.models.Result;

import java.util.List;

import javax.inject.Inject;

public class QuizAppStartController {

    private QuizAppStartControllerCallback callback;
    private AsyncDatabase.DataReceivedListener callback1;
    private ApiWrapper apiWrapper;
    private GameEntity gameEntity;

    @Inject
    public QuizAppStartController(QuizAppStartControllerCallback callback) {
        this.callback = callback;
    }

    public void setCallback(QuizAppStartControllerCallback callback) {
        this.callback = callback;
    }

    public void onUiLoaded() {
        AsyncDatabase.getInstance().getAllGames(new AsyncDatabase.DataReceivedListener<List<GameEntity>>() {
            @Override
            public void onDataReceived(List<GameEntity> data) {
                callback.showGames(data);
            }
        });
    }

    public void saveAllCategories() {
        ApiWrapper.getInstance().getAllCategories(new ApiWrapper.OnApiResultListener<Category[]>() {
            @Override
            public void onSuccess(Category[] data) {
                callback.saveCategories(data);
            }

            @Override
            public void onFailure() {

            }
        });
    }

    public void saveGameQuestions(int categoryNumber, String categoryOne, String categoryTwo,
                                  int difficulty) {
        ApiWrapper.getInstance().getTheQuestions(15,
                categoryNumber, difficulty, "multiple",
                new ApiWrapper.OnApiResultListener<Result[]>() {
                    @Override
                    public void onSuccess(Result[] data) {
                        callback.saveQuestions(data, categoryOne, categoryTwo);
                    }

                    @Override
                    public void onFailure() {

                    }
                });
    }

    public void saveRoundTwoQuestions(int categoryNumber, String categoryOne, String categoryTwo,
                                      int difficulty) {
        ApiWrapper.getInstance().getRoundTwoQuestions(15,
                categoryNumber, difficulty, "boolean",
                new ApiWrapper.OnApiResultListener<Result[]>() {
                    @Override
                    public void onSuccess(Result[] data) {
                        callback.saveRoundTwoQuestions(data, categoryOne, categoryTwo);
                    }

                    @Override
                    public void onFailure() {

                    }
                });
    }



    public void removeGame (int id) {
        AsyncDatabase.getInstance().getGameById(id, new AsyncDatabase.DataReceivedListener<GameEntity>() {
            @Override
            public void onDataReceived(GameEntity game) {
                gameEntity = game;
            }
        });
        AsyncDatabase.getInstance().delete(gameEntity);
    }

    public interface QuizAppStartControllerCallback {
        void showGames(List<GameEntity> data);
        void saveCategories(Category[] data);
        void saveQuestions(Result[] data, String categoryOne, String categoryTwo);
        void saveRoundTwoQuestions(Result[] data, String categoryOne, String categoryTwo);
    }
}
