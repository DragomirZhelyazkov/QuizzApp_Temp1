package com.example.quiz_game_project.ui.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quiz_game_project.R;
import com.example.quiz_game_project.data.local.entities.GameEntity;

public class ActiveGameViewHolder extends CustomViewHolder<GameEntity> {

    private final TextView txtTitle;
    private final TextView txtFirstCategory;
    private final TextView txtSecondCategory;
    private final TextView icon;
    private final RatingBar dificulty;

    public ActiveGameViewHolder(@NonNull View itemView) {
        super(itemView);

        txtTitle = itemView.findViewById(R.id.textView4);
        txtFirstCategory = itemView.findViewById(R.id.txtFirstCategory);
        txtSecondCategory = itemView.findViewById(R.id.txtSecondCategory);
        icon = itemView.findViewById(R.id.imageView3);
        dificulty = itemView.findViewById(R.id.txtDificulty);
    }

    @Override
    public void setData(GameEntity game){
        txtTitle.setText(game.getName());
        txtFirstCategory.setText(game.getCategory1());
        txtSecondCategory.setText(game.getCategory2());
        icon.setText(game.getIcon());
        dificulty.setRating((float) game.getDifficulty());
    }
}
