package com.example.quiz_game_project.ui.fragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.quiz_game_project.R;
import com.example.quiz_game_project.data.local.AsyncDatabase;
import com.example.quiz_game_project.data.local.CurrentGame;
import com.example.quiz_game_project.data.local.entities.GameEntity;
import com.example.quiz_game_project.databinding.FragmentExistingGamesBinding;

public class ExistingGamesFragment extends Fragment {

    private FragmentExistingGamesBinding binding;
    private NavController navController;
    private int id;
    private GameEntity game;
    CurrentGame currentGame;
    String questionSource = "local";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentExistingGamesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        id = getArguments().getInt("game_id");

        initFragmentViews();
        setupToolbar(view);
        initSwitch();

        binding.btnPlay.setOnClickListener(view1 -> onButtonPlayClicked(view));

    }

    private void initFragmentViews() {// Да се оправи!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        AsyncDatabase.getInstance().getGameById(id,
                new AsyncDatabase.DataReceivedListener<GameEntity>() {
            @Override
            public void onDataReceived(GameEntity existingGame) {
                game = existingGame;
                Log.i("Info log","--------------------Id is " + game.getName() );
                binding.textView11.setText(game.getCategory1());
                binding.textView12.setText(game.getCategory2());
                binding.textView17.setText(game.getLastplayed());
                binding.textView18.setText(game.getTopplayer());
                binding.imageView4.setText(game.getIcon());
                binding.ratingBar.setRating((float) game.getDifficulty());
                binding.ratingBar.setRating((float) game.getDifficulty());
                binding.txtGame.setText("QUIZZ GAME");

//                currentGame = new CurrentGame();
                CurrentGame.uid = game.getUid();
                CurrentGame.icon=game.getIcon();
                CurrentGame.name=game.getName();
                CurrentGame.category1=game.getCategory1();
                CurrentGame.category2=game.getCategory2();
                CurrentGame.firsRounNumberOfQuestions =game.getQuestiondeley();
                CurrentGame.firstRoundQuestionAnswer =game.getFirstRoundQuestionAnswer();
                CurrentGame.putFingersTimeToClick =game.getSecondRoundQuestionAnswer();
                CurrentGame.secondRoundDuration =game.getSecondRoundDuration();
                CurrentGame.difficulty=game.getDifficulty();
                CurrentGame.lastPlayed =game.getLastplayed();
                CurrentGame.topPlayer =game.getTopplayer();
                CurrentGame.source=game.getSource();
                CurrentGame.flag=game.getFlag();
                binding.txtTitleTool.setText(CurrentGame.name);
            }
        });
    }

    private void  initSwitch() {
        Switch simpleSwitch = binding.switch1;
        simpleSwitch.setChecked(true);
        simpleSwitch.setTextOn("Cloud");
        simpleSwitch.setTextOff("Local");
        simpleSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    questionSource = "local";
                } else {
                    questionSource = "remote";
                }
            }
        });
    }

    private void onButtonPlayClicked(@NonNull View view) {
        //пращаме questionSource в следващия фрагмент------------------------------------
        Bundle fragmentData = new Bundle();
        fragmentData.putString("source", questionSource);
        Navigation.findNavController(view).navigate(R.id.action_existingGames_to_addPlayerFragment, fragmentData);
    }

    private void setupToolbar(@NonNull View view) {//в този метод свързваме toolbar и navController
        //създаваме Нав-контролер, containerView - къде се намира nav_graph
        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_container);
        //долното - готова схема копирана от сайт
        AppBarConfiguration appBarConfiguration =
                new AppBarConfiguration.Builder(navController.getGraph()).build();
        //създаваме тоолбар
        Toolbar toolbar = view.findViewById(R.id.toolbar3);
//        toolbar.setTitle(CurrentGame.name);
        //свързваме toolbar с navController
        NavigationUI.setupWithNavController(
                toolbar, navController, appBarConfiguration);
    }

}