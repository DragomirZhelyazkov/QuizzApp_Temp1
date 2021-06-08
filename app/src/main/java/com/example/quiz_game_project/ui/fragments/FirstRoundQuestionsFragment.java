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
import android.widget.TextView;

import com.example.quiz_game_project.PlayData.FirstRoundCounter;
import com.example.quiz_game_project.PlayData.PlayerFour;
import com.example.quiz_game_project.PlayData.PlayerOne;
import com.example.quiz_game_project.PlayData.PlayerThree;
import com.example.quiz_game_project.PlayData.PlayerTwo;
import com.example.quiz_game_project.QuestionCountdownTimer;
import com.example.quiz_game_project.R;
import com.example.quiz_game_project.ThreadUtils;
import com.example.quiz_game_project.data.QuestionsCategoryOneRepo;
import com.example.quiz_game_project.data.QuestionsCategoryTwoRepo;
import com.example.quiz_game_project.data.local.CurrentGame;
import com.example.quiz_game_project.databinding.FragmentFirstRoundQuestionsBinding;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class FirstRoundQuestionsFragment extends Fragment {

    private FragmentFirstRoundQuestionsBinding binding;
    String[] incorrectAnswers;
    String[] allAnswers;
    List<String> stringList;
    String name1;
    String name2;
    int numberOfPlayers;
    String activePlayer;
    int correctAnswer;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFirstRoundQuestionsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments().size() == 1) {
            name1 = getArguments().getString("name1");
            binding.txtName1.setText(name1);
            numberOfPlayers = 1;

        } else {
            name1 = getArguments().getString("name1");
            binding.txtName1.setText(name1);
            name2 = getArguments().getString("name2");
            binding.txtName2.setText(name2);
            binding.txtName2.setAlpha((float) 0.3);

            numberOfPlayers = 2;
        }

        activePlayer = name1;
        correctAnswer = 0;

        //да се изведе във функция!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        allAnswers = new String[4];
        allAnswers[0] = getAllIncorrectAnwers()[0];
        allAnswers[1] = getAllIncorrectAnwers()[1];
        allAnswers[2] = getAllIncorrectAnwers()[2];
        allAnswers[3] = getCorrectAnswer();
        Log.i("Info log",
                "-------------------- " + getCorrectAnswer());
        stringList = Arrays.asList(allAnswers);
        Collections.shuffle(stringList);
        stringList.toArray(allAnswers);

        //да се изведе във функция!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        binding.txtAnswer1.setText(allAnswers[0]);
        binding.txtAnswer2.setText(allAnswers[1]);
        binding.txtAnswer3.setText(allAnswers[2]);
        binding.txtAnswer4.setText(allAnswers[3]);

        binding.txtQuestionNumber.setText(String.valueOf(FirstRoundCounter.roundNumber + 1));

        //да се изведе във функция!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        binding.txtAnswer1.setOnClickListener(view1 -> onAnswerClicked(allAnswers[0],
                1));
        binding.txtAnswer2.setOnClickListener(view1 -> onAnswerClicked(allAnswers[1], 2));
        binding.txtAnswer3.setOnClickListener(view1 -> onAnswerClicked(allAnswers[2], 3));
        binding.txtAnswer4.setOnClickListener(view1 -> onAnswerClicked(allAnswers[3], 4));

        if (numberOfPlayers == 1) {
            ThreadUtils.mainThread().post(() -> timer1(view));
            startTimer(CurrentGame.firstRoundQuestionAnswer - 1, binding.txtTimer, "time to answer");
            //ново!!!!!
        } else {
            ThreadUtils.mainThread().post(() -> timer2(view));
            startTimer(CurrentGame.firstRoundQuestionAnswer - 1, binding.txtTimer, "time to answer");//ново!!!!!
        }
    }

    private void startTimer(int sec, TextView tv, String text) {
        new QuestionCountdownTimer(tv, sec, text).reverseTimer(sec, tv);
    }

    private void timer1(View view){
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                goToPreveousScreen(view);
            }
        }, CurrentGame.firstRoundQuestionAnswer*1000);
    }

    private void timer2(View view){
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (correctAnswer == 1){
                    goToPreveousScreen(view);
                } else {
                    binding.txtName1.setAlpha((float) 0.3);
                    binding.txtName2.setAlpha((float) 1);
                    activePlayer = name2;
                    ThreadUtils.mainThread().post(() -> timer3(view));
                    ThreadUtils.mainThread().post(() -> startTimer(CurrentGame.firstRoundQuestionAnswer - 1, binding.txtTimer, "time to answer"));
                }
            }
        }, CurrentGame.firstRoundQuestionAnswer*1000);
    }

    private void timer3(View view){
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                goToPreveousScreen(view);
            }
        }, CurrentGame.firstRoundQuestionAnswer*1000);//ново!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    }

    private void goToPreveousScreen(View view){
        Bundle fragmentData = new Bundle();
        fragmentData.putString("name1", PlayerOne.name);
        fragmentData.putString("name2", PlayerTwo.name);
        fragmentData.putString("name3", PlayerThree.name);
        fragmentData.putString("name4", PlayerFour.name);
        ThreadUtils.mainThread().post(() -> Navigation.findNavController(view).navigate(R.id.action_firstRoundQuestionsFragment_to_firstRoundPutFingersFragment, fragmentData));
    }

    private void onAnswerClicked(String chosenAnswer, int index) {
        if(chosenAnswer.equals(getCorrectAnswer())) {
            switch (index){
                case 1 : binding.txtAnswer1.setBackgroundResource(R.color.yellow);
                    break;
                case 2 : binding.txtAnswer2.setBackgroundResource(R.color.yellow);
                    break;
                case 3 : binding.txtAnswer3.setBackgroundResource(R.color.yellow);
                    break;
                case 4 : binding.txtAnswer4.setBackgroundResource(R.color.yellow);
                    break;
            }
            ThreadUtils.executorService().submit(() -> addToScore());
            correctAnswer = 1;
        } else {
            switch (index){
                case 1 : binding.txtAnswer1.setBackgroundResource(R.color.purple_200);
                    break;
                case 2 : binding.txtAnswer2.setBackgroundResource(R.color.purple_200);
                    break;
                case 3 : binding.txtAnswer3.setBackgroundResource(R.color.purple_200);
                    break;
                case 4 : binding.txtAnswer4.setBackgroundResource(R.color.purple_200);
                    break;
            }
            ThreadUtils.executorService().submit(() -> removeFromScore());
        }
    }

    private String[] getAllIncorrectAnwers(){
        incorrectAnswers = new String[3];
        if (FirstRoundCounter.randNumber == 0) {
            incorrectAnswers =
                    QuestionsCategoryOneRepo.getInstance().getIncorrectAnswers(FirstRoundCounter.roundNumber - 1);
            return incorrectAnswers;
        } else {
            incorrectAnswers =
                    QuestionsCategoryTwoRepo.getInstance().getIncorrectAnswers(FirstRoundCounter.roundNumber - 1);
            return incorrectAnswers;
        }
    }

    private String getCorrectAnswer() {
        if (FirstRoundCounter.randNumber == 0) {
            String correctAnswer =
                    QuestionsCategoryOneRepo.getInstance().getCorrectAnswer(FirstRoundCounter.roundNumber - 1);
            return correctAnswer;
        } else {
            String correctAnswer =
                    QuestionsCategoryTwoRepo.getInstance().getCorrectAnswer(FirstRoundCounter.roundNumber - 1);
            return correctAnswer;
        }
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

}


