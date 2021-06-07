package com.example.quiz_game_project.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quiz_game_project.PlayData.OldGame;
import com.example.quiz_game_project.PlayData.PlayerFour;
import com.example.quiz_game_project.PlayData.PlayerOne;
import com.example.quiz_game_project.PlayData.PlayerThree;
import com.example.quiz_game_project.PlayData.PlayerTwo;
import com.example.quiz_game_project.R;
import com.example.quiz_game_project.data.Repos.OldGamesRepo;
import com.example.quiz_game_project.data.local.CurrentGame;
import com.example.quiz_game_project.databinding.FragmentFinalResultsBinding;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class FinalResultsFragment extends Fragment {

    private FragmentFinalResultsBinding binding;
    List<String> names;
    List<Integer> scores;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFinalResultsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        scores = new ArrayList();
        scores.add(PlayerOne.playerOneScore);
        scores.add(PlayerTwo.playerTwoScore);
        scores.add(PlayerThree.playerThreeScore);
        scores.add(PlayerFour.playerFourScore);
        Collections.sort(scores);
        Collections.reverse(scores);

        setWinner();
        setSecondPlace();
        createOldGame();

        binding.btnEnd.setOnClickListener(v -> Navigation.findNavController(view).navigate(R.id.action_finalResultsFragment_to_splashFragment));


    }

    private void setWinner() {
        if (PlayerOne.playerOneScore == scores.get(0)) {
            binding.txtPlayerOneScore.setText(PlayerOne.name + "  " + scores.get(0));
        } else if (PlayerTwo.playerTwoScore == scores.get(0)) {
            binding.txtPlayerOneScore.setText(PlayerTwo.name + "  " + scores.get(0));
        } else if (PlayerThree.playerThreeScore == scores.get(0)) {
            binding.txtPlayerOneScore.setText(PlayerThree.name + "  " + scores.get(0));
        } else {
            binding.txtPlayerOneScore.setText(PlayerFour.name + "  " + scores.get(0));
        }
    }

    private void setSecondPlace() {
        if (PlayerOne.playerOneScore == scores.get(1)) {
            binding.txtPlayerTwoScore.setText(PlayerOne.name + "  " + scores.get(1));
        } else if (PlayerTwo.playerTwoScore == scores.get(1)) {
            binding.txtPlayerTwoScore.setText(PlayerTwo.name + "  " + scores.get(1));
        } else if (PlayerThree.playerThreeScore == scores.get(1)) {
            binding.txtPlayerTwoScore.setText(PlayerThree.name + "  " + scores.get(1));
        } else {
            binding.txtPlayerTwoScore.setText(PlayerFour.name + "  " + scores.get(1));
        }
    }

    private void createOldGame() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String data = df.format(new Date());
        OldGame game = new OldGame(CurrentGame.icon, CurrentGame.name, data,
                binding.txtPlayerOneScore.getText().toString(), CurrentGame.numberOfPlayers);
        OldGamesRepo.getInstance().addOldGame(game);
        Log.i("Info log", "--------@@@@@@@@@-----  " + CurrentGame.icon);
        Log.i("Info log", "--------@@@@@@@@@-----  " + CurrentGame.name);
        Log.i("Info log", "--------@@@@@@@@@-----  " + data);
        Log.i("Info log", "--------@@@@@@@@@-----  " + CurrentGame.numberOfPlayers);
        Log.i("Info log", "--------@@@@@@@@@-----  " + binding.txtPlayerOneScore.getText().toString());

    }
}