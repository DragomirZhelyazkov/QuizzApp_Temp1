package com.example.quiz_game_project.di;

import com.example.quiz_game_project.MainActivity;


//RegisterFragment да сепреименова !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

import com.example.quiz_game_project.controllers.QuizAppStartController;
import com.example.quiz_game_project.ui.fragments.QuizAppStartFragment;


import dagger.Component;


@Component(modules = {DataModule.class, AppModule.class})
public interface AppComponent {

    void inject(QuizAppStartFragment fragment);


}
