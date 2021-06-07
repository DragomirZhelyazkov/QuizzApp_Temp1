package com.example.quiz_game_project.ui.fragments;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quiz_game_project.PlayData.FirstRoundCounter;
import com.example.quiz_game_project.R;
import com.example.quiz_game_project.ThreadUtils;
import com.example.quiz_game_project.data.QuestionsCategoryOneRepo;
import com.example.quiz_game_project.data.QuestionsCategoryTwoRepo;
import com.example.quiz_game_project.data.local.CurrentGame;
import com.example.quiz_game_project.databinding.FragmentFirstRoundPutFingersBinding;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class FirstRoundPutFingersFragment extends Fragment {

    private FragmentFirstRoundPutFingersBinding binding;
    private String [] names;
    int i;
    String name1;
    String name2;
    String name3;
    String name4;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFirstRoundPutFingersBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // да се изнесат във функции!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        name1 = getArguments().getString("name1");
        name2 = getArguments().getString("name2");
        name3 = getArguments().getString("name3");
        name4 = getArguments().getString("name4");

        setViews(name1, name2, name3, name4);

        if (FirstRoundCounter.roundNumber == CurrentGame.firsRounNumberOfQuestions) {
            Navigation.findNavController(getView()).navigate(R.id.action_firstRoundPutFingersFragment_to_firstRoundResultsFragment);

        } else {
            Random rand = new Random();
            int randNumber = rand.nextInt(2);
            setQuestion(randNumber);

            names = new String[20];
            i = 0;

            if (name1 != null) {
                binding.player1.setClickable(true);
                binding.player1.setOnClickListener(view1 -> onButtonAnswerClicked(view, name1));}
            if (name2 != null){
                binding.player2.setClickable(true);
                binding.player2.setOnClickListener(view1 -> onButtonAnswerClicked(view, name2));}
            if (name3 != null){
                binding.player3.setClickable(true);
                binding.player3.setOnClickListener(view1 -> onButtonAnswerClicked(view, name3));}
            if (name4 != null){
                binding.player4.setClickable(true);
                binding.player4.setOnClickListener(view1 -> onButtonAnswerClicked(view, name4));}


            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    Bundle fragmentData = new Bundle();
                    if (i == 0) {
//                    Log.i("Info log", "----------????? " + FirstRoundCounter.roundNumber);
                        restartFragment();
                    } else if (i == 1) {
                        fragmentData.putString("name1", names[0]);
                        ThreadUtils.mainThread().post(() -> next(view, fragmentData));
                    } else if (i >= 2) {
                        fragmentData.putString("name1", names[0]);
                        fragmentData.putString("name2", names[1]);
                        ThreadUtils.mainThread().post(() -> next(view, fragmentData));
                    }
                }

            }, CurrentGame.putFingersTimeToClick*1000);
        }

    }

    private void next(View view, Bundle fragmentData) {
        Navigation.findNavController(view).navigate(R.id.action_firstRoundPutFingersFragment_to_firstRoundQuestionsFragment, fragmentData);
    }

    private void restartFragment() {
        getFragmentManager()
                .beginTransaction()
                .detach(this)
                .attach(this)
                .commit();
    }

    private void setViews(String name1, String name2, String name3, String name4){
        binding.player1.setText(name1);
        binding.player2.setText(name2);
        binding.player3.setText(name3);
        binding.player4.setText(name4);
    }

    private void setQuestion(int randNumber){
        int roundCounter = FirstRoundCounter.roundNumber;
        if (randNumber == 0) {
            Log.i("Info log", "--------@@@@@@@@@-----  GeneralKnowlege");
            binding.txtQuestionBox.setText(QuestionsCategoryOneRepo.getInstance().getQuestion(roundCounter));
        } else {
            Log.i("Info log", "--------@@@@@@@@@-----  Geography");
            binding.txtQuestionBox.setText(QuestionsCategoryTwoRepo.getInstance().getQuestion(roundCounter));
        }
        roundCounter++;
        FirstRoundCounter.roundNumber = roundCounter;
        FirstRoundCounter.randNumber = randNumber;
//        Log.i("Info log",
//                "-------------------- " + FirstRoundCounter.roundNumber);
    }

    private void onButtonAnswerClicked(View view, String name) {
        names[i] = name;
        i = i + 1;

        if (name.equals(name1)) {
            binding.player1.setClickable(false);
        } else if(name.equals(name2)) {
            binding.player2.setClickable(false);
        } else if(name.equals(name3)) {
            binding.player3.setClickable(false);
        } else if(name.equals(name4)) {
            binding.player4.setClickable(false);
        }
    }


}