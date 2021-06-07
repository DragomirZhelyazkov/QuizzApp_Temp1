package com.example.quiz_game_project.ui.fragments;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quiz_game_project.R;
import com.example.quiz_game_project.ThreadUtils;

import java.util.Timer;
import java.util.TimerTask;

public class SplashFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                ThreadUtils.mainThread().post(() -> Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_quizAppStart));
            }
        }, 2500);
    }
}