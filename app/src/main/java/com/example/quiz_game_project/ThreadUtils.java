package com.example.quiz_game_project;

import android.os.Handler;
import android.os.Looper;

import androidx.core.os.HandlerCompat;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadUtils {

    private static ExecutorService instance;
    private static Handler handlerInstance;

    public static ExecutorService executorService() {
        if(instance == null) instance = Executors.newFixedThreadPool(4);
        return instance;
    }

    public static Handler mainThread() {
        if(handlerInstance == null) handlerInstance = HandlerCompat.createAsync(Looper.getMainLooper());
        return handlerInstance;
    }

    private ThreadUtils() {

    }
}
