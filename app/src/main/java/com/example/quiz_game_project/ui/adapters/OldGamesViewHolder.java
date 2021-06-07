package com.example.quiz_game_project.ui.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quiz_game_project.PlayData.OldGame;
import com.example.quiz_game_project.R;

public class OldGamesViewHolder extends CustomViewHolder<OldGame> {

    private final TextView icon;
    private final TextView name;
    private final TextView date;

    public OldGamesViewHolder(@NonNull View itemView) {
        super(itemView);
        icon = itemView.findViewById(R.id.txtHistoryIcon);
        name = itemView.findViewById(R.id.txtHistoryName);
        date = itemView.findViewById(R.id.txtHistoryDate);
    }

    @Override
    public void setData(OldGame oldGame) {
        icon.setText(oldGame.getIcon());
        name.setText(oldGame.getName());
        date.setText(oldGame.getDate());
    }


}
