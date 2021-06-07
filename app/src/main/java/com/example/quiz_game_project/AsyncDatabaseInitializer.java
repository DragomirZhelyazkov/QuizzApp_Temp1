package com.example.quiz_game_project;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.startup.Initializer;

import com.example.quiz_game_project.data.local.AsyncDatabase;

import java.util.Collections;
import java.util.List;

public class AsyncDatabaseInitializer implements Initializer<AsyncDatabase> {

    @NonNull
    @Override
    public AsyncDatabase create(@NonNull Context context) {
        AsyncDatabase.init(context);
        return AsyncDatabase.getInstance();
    }

    @NonNull
    @Override
    public List<Class<? extends Initializer<?>>> dependencies() {
        return Collections.emptyList();
    }
}
