package com.example.quiz_game_project;

import android.os.CountDownTimer;
import android.widget.TextView;

public class QuestionCountdownTimer {

    String text;
    TextView text1;
    private static final String FORMAT = "%02d:%02d:%02d";
    int seconds;

    public QuestionCountdownTimer(TextView text1, int seconds, String text) {
        this.text1 = text1;
        this.seconds = seconds;
        this.text = text;
    }

    public void reverseTimer(int Seconds, TextView tv) {
        new CountDownTimer(Seconds * 1000 + 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                tv.setText(text + "\n" + seconds);
            }

            public void onFinish() {
//                tv.setText("Completed");
            }
        }.start();
    }
}
