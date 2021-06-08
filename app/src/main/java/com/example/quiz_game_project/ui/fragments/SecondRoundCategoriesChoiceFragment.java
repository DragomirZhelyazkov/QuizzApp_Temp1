package com.example.quiz_game_project.ui.fragments;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quiz_game_project.PlayData.PlayerFour;
import com.example.quiz_game_project.PlayData.PlayerOne;
import com.example.quiz_game_project.PlayData.PlayerThree;
import com.example.quiz_game_project.PlayData.PlayerTwo;
import com.example.quiz_game_project.R;
import com.example.quiz_game_project.ThreadUtils;
import com.example.quiz_game_project.data.local.CurrentGame;
import com.example.quiz_game_project.databinding.FragmentSecondRoundCategoriesChoiceBinding;

import java.util.ArrayList;
import java.util.List;


public class SecondRoundCategoriesChoiceFragment extends Fragment {

    private FragmentSecondRoundCategoriesChoiceBinding binding;
    private String name1;
    private String name2;
    private boolean playerColor;
    String winerFirstRoundColor;
    String secondInFirstRoundColor;
    List<String> winerRoundOneCategories;
    List<String> secondInFirstRoundCategories;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSecondRoundCategoriesChoiceBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        name1 = getArguments().getString("name1");
        name2 = getArguments().getString("name2");
        Log.i("Info log", "----------????? " + name2);

        getPlayerOneColor(name1);
        getPlayerTwoColor(name2);

        //вкарай във функция---------------------------------------------------
        binding.txtPlayerName.setText(name2);

        binding.txtCategory1.setText(CurrentGame.category1);
        binding.txtCategory2.setText(CurrentGame.category2);
        binding.txtCategory3.setText(CurrentGame.category2);
        binding.txtCategory4.setText(CurrentGame.category1);

        //вкарай във функция---------------------------------------------------
        binding.txtCategory1.setOnClickListener(view1 -> onClick(view1));
        binding.txtCategory2.setOnClickListener(view1 -> onClick(view1));
        binding.txtCategory3.setOnClickListener(view1 -> onClick(view1));
        binding.txtCategory4.setOnClickListener(view1 -> onClick(view1));

        binding.btnNextScreen.setOnClickListener(v -> goToLastScreen(view));

    }

    private void goToLastScreen(View view) {
        winerRoundOneCategories = getPlayerOneCategories();
        String[] winerCategories = new String[winerRoundOneCategories.size()];
        winerCategories = winerRoundOneCategories.toArray(winerCategories);

        secondInFirstRoundCategories = getPlayerTwoCategories();
        String[] secondCategories = new String[secondInFirstRoundCategories.size()];
        secondCategories = secondInFirstRoundCategories.toArray(secondCategories);

        if (winerCategories.length == secondCategories.length){
            Bundle fragmentData = new Bundle();
            fragmentData.putString("name1", name1);
            fragmentData.putString("name2", name2);
            fragmentData.putStringArray("winerCategories",
                    winerCategories);
            fragmentData.putStringArray("secondCategories",
                    secondCategories);
            Log.i("Info log", "----------????? " + winerCategories[0]);
            Log.i("Info log", "----------????? " + winerCategories[1]);
            Log.i("Info log", "----------????? " + secondCategories[0]);
            Log.i("Info log", "----------????? " + secondCategories[1]);
            ThreadUtils.mainThread().post(() -> Navigation.findNavController(view).navigate(R.id.action_secondRoundCategoriesChoiceFragment_to_secondRoundQuestionsFragment2,
                    fragmentData));
        }
    }

    private List<String> getPlayerOneCategories() {
        winerRoundOneCategories = new ArrayList<>();
        if(binding.txtCategory1.getAlpha() == 1f) winerRoundOneCategories.add(binding.txtCategory1.getText().toString());
        if(binding.txtCategory2.getAlpha() == 1f) winerRoundOneCategories.add(binding.txtCategory2.getText().toString());
        if(binding.txtCategory3.getAlpha() == 1f) winerRoundOneCategories.add(binding.txtCategory3.getText().toString());
        if(binding.txtCategory4.getAlpha() == 1f) winerRoundOneCategories.add(binding.txtCategory4.getText().toString());
        return winerRoundOneCategories;
    }

    private List<String> getPlayerTwoCategories() {
        secondInFirstRoundCategories = new ArrayList<>();
        if(binding.txtCategory1.getAlpha() == 0.95f) secondInFirstRoundCategories.add(binding.txtCategory1.getText().toString());
        if(binding.txtCategory2.getAlpha() == 0.95f) secondInFirstRoundCategories.add(binding.txtCategory2.getText().toString());
        if(binding.txtCategory3.getAlpha() == 0.95f) secondInFirstRoundCategories.add(binding.txtCategory3.getText().toString());
        if(binding.txtCategory4.getAlpha() == 0.95f) secondInFirstRoundCategories.add(binding.txtCategory4.getText().toString());
        return secondInFirstRoundCategories;
    }

    private void getPlayerOneColor(String name) {
        if (PlayerOne.name.equals(name)) {
            winerFirstRoundColor = PlayerOne.color;
        } else if (PlayerTwo.name.equals(name)){
            winerFirstRoundColor = PlayerTwo.color;
        } else if (PlayerThree.name.equals(name)){
            winerFirstRoundColor = PlayerThree.color;
        } else winerFirstRoundColor = PlayerFour.color;
//        Log.i("Info log", "-------COLOR----- " + playerOneColor);

    }

    private void getPlayerTwoColor(String name) {
        if (PlayerOne.name.equals(name)) {
            secondInFirstRoundColor = PlayerOne.color;
        } else if (PlayerTwo.name.equals(name)){
            secondInFirstRoundColor = PlayerTwo.color;
        } else if (PlayerThree.name.equals(name)){
            secondInFirstRoundColor = PlayerThree.color;
        } else secondInFirstRoundColor = PlayerFour.color;
//        Log.i("Info log", "-------COLOR2----- " + playerTwoColor);
    }

    private void onClick(View view) {
        playerColor = !playerColor;
        if (playerColor){
            view.setBackgroundColor(Color.parseColor(winerFirstRoundColor));
            view.setAlpha(1f);
        } else{
            view.setBackgroundColor(Color.parseColor(secondInFirstRoundColor));
            view.setAlpha(0.95f);
        }
    }
}