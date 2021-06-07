package com.example.quiz_game_project;

import android.app.Application;


import com.example.quiz_game_project.di.AppComponent;
import com.example.quiz_game_project.di.AppModule;
import com.example.quiz_game_project.di.DataModule;
import com.example.quiz_game_project.di.UserModule;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class QuizGameApplication extends Application {


//    private AppComponent appComponent;
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//
//         AppComponent appComponent= DaggerAppComponent.builder()
//                .appModule(new AppModule(this))
//                .dataModule(new DataModule())
//                .userModule(new UserModule())
//                .build();
//
//    }
//
//    public AppComponent getAppComponent() {
//        return appComponent;
//    }
}
