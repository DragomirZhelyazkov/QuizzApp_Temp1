package com.example.quiz_game_project;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.startup.Initializer;

import com.example.quiz_game_project.data.local.AsyncDatabase;
import com.example.quiz_game_project.data.remote.questions.ApiWrapper;

import java.util.Arrays;
import java.util.List;

public class ApiWrapperInitializer implements Initializer<ApiWrapper> {


    @NonNull
    @Override
    public ApiWrapper create(@NonNull Context context) {
        ApiWrapper.init(AsyncDatabase.getInstance());
        return ApiWrapper.getInstance();
    }

    @NonNull
    @Override
    public List<Class<? extends Initializer<?>>> dependencies() {
        return Arrays.asList(AsyncDatabaseInitializer.class);
    }
}
