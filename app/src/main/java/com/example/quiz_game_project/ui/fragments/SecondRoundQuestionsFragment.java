package com.example.quiz_game_project.ui.fragments;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.quiz_game_project.PlayData.FirstRoundCounter;
import com.example.quiz_game_project.PlayData.PlayerFour;
import com.example.quiz_game_project.PlayData.PlayerOne;
import com.example.quiz_game_project.PlayData.PlayerThree;
import com.example.quiz_game_project.PlayData.PlayerTwo;
import com.example.quiz_game_project.QuestionCountdownTimer;
import com.example.quiz_game_project.R;
import com.example.quiz_game_project.ThreadUtils;
import com.example.quiz_game_project.data.QuestionRoundTwoRepo;
import com.example.quiz_game_project.data.QuestionsCategoryOneRepo;
import com.example.quiz_game_project.data.local.CurrentGame;
import com.example.quiz_game_project.databinding.FragmentSecondRoundCategoriesChoiceBinding;
import com.example.quiz_game_project.databinding.FragmentSecondRoundQuestionsBinding;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class SecondRoundQuestionsFragment extends Fragment {

    private String name1;
    private String name2;
    String [] winerRoundOneCategories;
    String [] winerRoundOneCategoriesFinal;
    String [] secondRoundOneCategories;
    String [] secondRoundOneCategoriesFinal;
    Random rand;
    int rand_int1;
    int counter;
    String activePlayer;
    Handler handler;
    int flag;
    int currentCategory;

    private FragmentSecondRoundQuestionsBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSecondRoundQuestionsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        name1 = getArguments().getString("name1");
        name2 = getArguments().getString("name2");
        winerRoundOneCategories = getArguments().getStringArray("winerCategories");
        secondRoundOneCategories = getArguments().getStringArray("secondCategories");
        for (int i = 0; i < 2; i++){
            Log.i("Info log", "---- ---- " + winerRoundOneCategories[i]);
            Log.i("Info log", "---- ---- " + secondRoundOneCategories[i]);
        }

        binding.txtPlayerName.setText(name1);

        activePlayer = name1;
        rand = new Random();
        rand_int1 = rand.nextInt(14);
        flag = 1;
        setQuestion(rand_int1);

        binding.txtTrue.setOnClickListener(view1 -> onAnswerTrueClicked(view1));
        binding.txtFalse.setOnClickListener(view1 -> onAnswerFalseClicked(view1));
        binding.txtPass1.setOnClickListener(view1 -> onAnswerPassClicked(view1));
        binding.txtPass2.setOnClickListener(view1 -> onAnswerPassClicked(view1));

        //няма нужда
        if (activePlayer.equals(name1)) {
            startTimer(CurrentGame.secondRoundDuration - 1, binding.timerRoundTwo, winerRoundOneCategories[0]);
            ThreadUtils.mainThread().post(() -> timer1(view));
        }
    }

    private void timer1(View view) {
        Log.i("Info log", "----TIMER1 ---- ");
        Log.i("Info log", "----TIMER1 ---- " + CurrentGame.secondRoundDuration);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                flag = 2;
                setQuestion(rand_int1);

                ThreadUtils.mainThread().post(() -> startTimer(CurrentGame.secondRoundDuration - 1,
                        binding.timerRoundTwo,
                        winerRoundOneCategories[1]));

                ThreadUtils.mainThread().post(() -> timer2(view));
            }
        }, CurrentGame.secondRoundDuration*1000);
    }

    private void timer2(View view) {
        Log.i("Info log", "----TIMER2 ---- ");
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                binding.txtPlayerName.setText(name2);
                activePlayer = name2;
                flag = 3;
                setQuestion(rand_int1);

                ThreadUtils.mainThread().post(() -> startTimer(CurrentGame.secondRoundDuration - 1, binding.timerRoundTwo,
                        secondRoundOneCategories[0])); //ново!!!!!

                ThreadUtils.mainThread().post(() -> timer3(view));
            }
        }, CurrentGame.secondRoundDuration*1000);
    }

    private void timer3(View view) {
        Log.i("Info log", "----TIMER3 ---- ");
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                flag = 4;
                setQuestion(rand_int1);

                ThreadUtils.mainThread().post(() -> startTimer(CurrentGame.secondRoundDuration - 1, binding.timerRoundTwo,
                        secondRoundOneCategories[1])); //ново!!!!!

                ThreadUtils.mainThread().post(() -> timer4(view));
            }
        }, CurrentGame.secondRoundDuration*1000);
    }

    private void timer4(View view) {
        Log.i("Info log", "----TIMER4 ---- ");
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                ThreadUtils.mainThread().post(() -> Navigation.findNavController(view).navigate(R.id.action_secondRoundQuestionsFragment2_to_finalResultsFragment));
            }
        }, CurrentGame.secondRoundDuration*1000);
    }

    private void setQuestion(int rundomNumber) {
        if (flag == 1) {
            if (winerRoundOneCategories[0].equals(CurrentGame.category1)){
                binding.txtQuestion.setText(QuestionRoundTwoRepo.getInstance().getQuestion(rundomNumber));
                currentCategory = 1;
                Log.i("Info log", "----category ---- " + CurrentGame.category1);
                Log.i("Info log",
                        "----answer ---- " + QuestionRoundTwoRepo.getInstance().getCorrectAnswer(rundomNumber));
            } else {
                binding.txtQuestion.setText(QuestionRoundTwoRepo.getInstance().getQuestion2(rundomNumber));
                currentCategory = 2;
                Log.i("Info log", "----category ---- " + CurrentGame.category2);
                Log.i("Info log",
                        "----answer ---- " + QuestionRoundTwoRepo.getInstance().getCorrectAnswer2(rundomNumber));
            }
        } else if (flag == 2) {
            if (winerRoundOneCategories[1].equals(CurrentGame.category1)){
                binding.txtQuestion.setText(QuestionRoundTwoRepo.getInstance().getQuestion(rundomNumber));
                currentCategory = 1;
                Log.i("Info log", "----category ---- " + CurrentGame.category1);
                Log.i("Info log",
                        "----answer ---- " + QuestionRoundTwoRepo.getInstance().getCorrectAnswer(rundomNumber));
            } else {
                binding.txtQuestion.setText(QuestionRoundTwoRepo.getInstance().getQuestion2(rundomNumber));
                currentCategory = 2;
                Log.i("Info log", "----category ---- " + CurrentGame.category2);
                Log.i("Info log",
                        "----answer ---- " + QuestionRoundTwoRepo.getInstance().getCorrectAnswer2(rundomNumber));
            }
        } else if (flag == 3) {
            if (secondRoundOneCategories[0].equals(CurrentGame.category1)){
                binding.txtQuestion.setText(QuestionRoundTwoRepo.getInstance().getQuestion(rundomNumber));
                currentCategory = 1;
                Log.i("Info log", "----category ---- " + CurrentGame.category1);
                Log.i("Info log",
                        "----answer ---- " + QuestionRoundTwoRepo.getInstance().getCorrectAnswer(rundomNumber));
            } else {
                binding.txtQuestion.setText(QuestionRoundTwoRepo.getInstance().getQuestion2(rundomNumber));
                currentCategory = 2;
                Log.i("Info log", "----category ---- " + CurrentGame.category2);
                Log.i("Info log",
                        "----answer ---- " + QuestionRoundTwoRepo.getInstance().getCorrectAnswer2(rundomNumber));
            }
        } else if (flag == 4){
            if (secondRoundOneCategories[1].equals(CurrentGame.category1)){
                binding.txtQuestion.setText(QuestionRoundTwoRepo.getInstance().getQuestion(rundomNumber));
                currentCategory = 1;
                Log.i("Info log", "----category ---- " + CurrentGame.category1);
                Log.i("Info log",
                        "----answer ---- " + QuestionRoundTwoRepo.getInstance().getCorrectAnswer(rundomNumber));
            } else {
                binding.txtQuestion.setText(QuestionRoundTwoRepo.getInstance().getQuestion2(rundomNumber));
                currentCategory = 2;
                Log.i("Info log", "----category ---- " + CurrentGame.category2);
                Log.i("Info log",
                        "----answer ---- " + QuestionRoundTwoRepo.getInstance().getCorrectAnswer2(rundomNumber));
            }
        }

    }

    private void onAnswerTrueClicked(View v) {
        if (currentCategory == 1 ){
            if (QuestionRoundTwoRepo.getInstance().getCorrectAnswer(rand_int1).equals("True")){
                addToScore();
                rand_int1 = rand.nextInt(14);
                setQuestion(rand_int1);
            } else {
                removeFromScore();
                rand_int1 = rand.nextInt(14);
                setQuestion(rand_int1);
            }
        } else if (currentCategory == 2 ) {
            if (QuestionRoundTwoRepo.getInstance().getCorrectAnswer2(rand_int1).equals("True")){
                addToScore();
                rand_int1 = rand.nextInt(14);
                setQuestion(rand_int1);
            } else {
                removeFromScore();
                rand_int1 = rand.nextInt(14);
                setQuestion(rand_int1);
            }
        }

    }

    private void onAnswerFalseClicked(View view1) {
        if (currentCategory == 1 ){
            if (QuestionRoundTwoRepo.getInstance().getCorrectAnswer(rand_int1).equals("False")){
                addToScore();
                rand_int1 = rand.nextInt(14);
                setQuestion(rand_int1);
            } else {
                removeFromScore();
                rand_int1 = rand.nextInt(14);
                setQuestion(rand_int1);
            }
        } else if (currentCategory == 2 ) {
            if (QuestionRoundTwoRepo.getInstance().getCorrectAnswer2(rand_int1).equals("False")){
                addToScore();
                rand_int1 = rand.nextInt(14);
                setQuestion(rand_int1);
            } else {
                removeFromScore();
                rand_int1 = rand.nextInt(14);
                setQuestion(rand_int1);
            }
        }
    }

    private void onAnswerPassClicked(View view1) {
        removeFromScore();
        rand_int1 = rand.nextInt(14);
        setQuestion(rand_int1);
    }

    private void addToScore() {
        if(activePlayer.equals(name1)){
            if (name1.equals(PlayerOne.name)) PlayerOne.playerOneScore++;
            if (name1.equals(PlayerTwo.name)) PlayerTwo.playerTwoScore++;
            if (name1.equals(PlayerThree.name)) PlayerThree.playerThreeScore++;
            if (name1.equals(PlayerFour.name)) PlayerFour.playerFourScore++;
        } else if(activePlayer.equals(name2)){
            if (name2.equals(PlayerOne.name)) PlayerOne.playerOneScore++;
            if (name2.equals(PlayerTwo.name)) PlayerTwo.playerTwoScore++;
            if (name2.equals(PlayerThree.name)) PlayerThree.playerThreeScore++;
            if (name2.equals(PlayerFour.name)) PlayerFour.playerFourScore++;
        }
    }

    private void removeFromScore() {
        if(activePlayer.equals(name1)){
            if (name1.equals(PlayerOne.name)) PlayerOne.playerOneScore--;
            if (name1.equals(PlayerTwo.name)) PlayerTwo.playerTwoScore--;
            if (name1.equals(PlayerThree.name)) PlayerThree.playerThreeScore--;
            if (name1.equals(PlayerFour.name)) PlayerFour.playerFourScore--;
        } else if(activePlayer.equals(name2)){
            if (name2.equals(PlayerOne.name)) PlayerOne.playerOneScore--;
            if (name2.equals(PlayerTwo.name)) PlayerTwo.playerTwoScore--;
            if (name2.equals(PlayerThree.name)) PlayerThree.playerThreeScore--;
            if (name2.equals(PlayerFour.name)) PlayerFour.playerFourScore--;
        }
    }

    private void startTimer(int sec, TextView tv, String text) {
        new QuestionCountdownTimer(tv, sec, text).reverseTimer(sec, tv);
    }


}