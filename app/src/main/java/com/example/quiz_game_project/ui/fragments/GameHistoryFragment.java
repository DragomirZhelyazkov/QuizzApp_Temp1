package com.example.quiz_game_project.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.quiz_game_project.PlayData.OldGame;
import com.example.quiz_game_project.R;
import com.example.quiz_game_project.data.Repos.OldGamesRepo;


public class GameHistoryFragment extends Fragment {

    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game_history, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupToolbar(view);

        TextView text = view.findViewById(R.id.txtGamesHistory);
        text.setText(OldGamesRepo.getInstance().getData().get(0).getIcon() + "  "
                + OldGamesRepo.getInstance().getData().get(0).getName() + "  "
                + OldGamesRepo.getInstance().getData().get(0).getDate());
    }

    private void setupToolbar(@NonNull View view) {//в този метод свързваме toolbar и navController
        //създаваме Нав-контролер, containerView - къде се намира nav_graph
        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_container);
        //долното - готова схема копирана от сайт
        AppBarConfiguration appBarConfiguration =
                new AppBarConfiguration.Builder(navController.getGraph()).build();
        //създаваме тоолбар
        Toolbar toolbar = view.findViewById(R.id.toolbar5);
        //свързваме toolbar с navController
        NavigationUI.setupWithNavController(
                toolbar, navController, appBarConfiguration);
    }
}