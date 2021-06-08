package com.example.quiz_game_project.ui.fragments;

import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quiz_game_project.MyComparator;
import com.example.quiz_game_project.PlayData.PlayerFour;
import com.example.quiz_game_project.PlayData.PlayerOne;
import com.example.quiz_game_project.PlayData.PlayerThree;
import com.example.quiz_game_project.PlayData.PlayerTwo;
import com.example.quiz_game_project.R;
import com.example.quiz_game_project.data.local.CurrentGame;
import com.example.quiz_game_project.databinding.FragmentFirstRoundResultsBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;


public class FirstRoundResultsFragment extends Fragment {

    private FragmentFirstRoundResultsBinding binding;
    List<String> names;
    List<Integer> scores;
    String winner1;
    String winner2;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFirstRoundResultsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.txtPlayerOneScore.setText(PlayerOne.name + "   " + String.valueOf(PlayerOne
                        .playerOneScore));
        binding.txtPlayerTwoScore.setText(PlayerTwo.name + "   " + String.valueOf(PlayerTwo.playerTwoScore));
        if (CurrentGame.numberOfPlayers > 2){
            binding.txtPlayerThreeScore.setText(PlayerThree.name + "   " + String.valueOf(PlayerThree.playerThreeScore));
        } else {
            PlayerThree.playerThreeScore = -100;
            PlayerFour.playerFourScore = -100;
        }
        if (CurrentGame.numberOfPlayers > 3) {
            binding.txtPlayerFourScore.setText(PlayerFour.name + "   " + String.valueOf(PlayerFour.playerFourScore));
        } else PlayerFour.playerFourScore = -100;


        sortResults();
        setWinner();

        Bundle fragmentData = new Bundle();
        fragmentData.putString("name1", winner1);
        fragmentData.putString("name2", winner2);
        binding.btnRoundTwo.setOnClickListener(view1 -> Navigation.findNavController(view1).navigate(R.id.action_firstRoundResultsFragment_to_secondRoundCategoriesChoiceFragment, fragmentData));
    }

    private void sortResults() {
        scores = new ArrayList();
        scores.add(PlayerOne.playerOneScore);
        scores.add(PlayerTwo.playerTwoScore);
        scores.add(PlayerThree.playerThreeScore);
        scores.add(PlayerFour.playerFourScore);
        Collections.sort(scores);
        Collections.reverse(scores);
    }

    private void setWinner() {
        if (PlayerOne.playerOneScore == scores.get(0)) {
            binding.txtPlayerOneScore.setAlpha((float) 1);
            setSecondPlace(PlayerOne.name);
            winner1 = PlayerOne.name;
        } else if (PlayerTwo.playerTwoScore == scores.get(0)) {
            binding.txtPlayerTwoScore.setAlpha((float) 1);
            setSecondPlace(PlayerTwo.name);
            winner1 = PlayerTwo.name;
        } else if (CurrentGame.numberOfPlayers > 2 && PlayerThree.playerThreeScore == scores.get(0)) {
            binding.txtPlayerThreeScore.setAlpha((float) 1);
            setSecondPlace(PlayerThree.name);
            winner1 = PlayerThree.name;
        } else if (CurrentGame.numberOfPlayers > 3 && PlayerFour.playerFourScore == scores.get(0)) {
            binding.txtPlayerFourScore.setAlpha((float) 1);
            setSecondPlace(PlayerFour.name);
            winner1 = PlayerFour.name;
        }
    }

    private void setSecondPlace(String playerName) {
        if (PlayerOne.playerOneScore == scores.get(1) && !PlayerOne.name.equals(playerName)) {
            binding.txtPlayerOneScore.setAlpha((float) 1);
            winner2 = PlayerOne.name;
        } else if (PlayerTwo.playerTwoScore == scores.get(1) && !PlayerTwo.name.equals(playerName)) {
            binding.txtPlayerTwoScore.setAlpha((float) 1);
            winner2 = PlayerTwo.name;
        } else if (PlayerThree.playerThreeScore == scores.get(1) && !PlayerThree.name.equals(playerName)) {
            binding.txtPlayerThreeScore.setAlpha((float) 1);
            winner2 = PlayerThree.name;
        } else {
            binding.txtPlayerFourScore.setAlpha((float) 1);
            winner2 = PlayerFour.name;
        }
    }

}