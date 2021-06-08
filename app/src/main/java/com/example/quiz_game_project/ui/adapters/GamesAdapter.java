package com.example.quiz_game_project.ui.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quiz_game_project.PlayData.OldGame;
import com.example.quiz_game_project.ThreadUtils;
import com.example.quiz_game_project.data.QuestionRoundTwoRepo;
import com.example.quiz_game_project.data.local.entities.GameEntity;
import com.squareup.picasso.Picasso;
import com.example.quiz_game_project.R;

import java.util.ArrayList;
import java.util.List;

public class GamesAdapter extends RecyclerView.Adapter<CustomViewHolder> {//===

    private List<AdapterData> games;
    private OnRowClickedListener listener;
    private static final int TYPE_GAME = 1;
    private static final int TYPE_HEADER = 2;
    private static final int TYPE_HISTORY = 3;

    public GamesAdapter(List<AdapterData> games) {
        this.games = games;
    }

    @Override
    public int getItemViewType(int position) {
        AdapterData dataItem = games.get(position);
        if(dataItem instanceof GameEntity) return TYPE_GAME;
        if(dataItem instanceof Header) return TYPE_HEADER;
        if(dataItem instanceof OldGame) return TYPE_HISTORY;
        Log.i("Info log", "-----@@@@@@@@------ " + super.getItemViewType(position));
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == TYPE_GAME){
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_image,
                    parent,
                    false);
            return new ActiveGameViewHolder(itemView);
        } else if(viewType == TYPE_HEADER) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item_header, parent, false);
            return new HeaderViewHolder(view);
        } else if(viewType == TYPE_HISTORY) {
            View historyView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item_history, parent, false);
            return new OldGamesViewHolder(historyView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        AdapterData game = games.get(position);
        holder.setData(game);

        if(game instanceof GameEntity){
            int id = ((GameEntity) game).getUid();
            String categoryOne = ((GameEntity) game).getCategory1();
            String categoryTwo = ((GameEntity) game).getCategory2();
            int difficulty = ((GameEntity) game).getDifficulty();
            holder.itemView.findViewById(R.id.imgDel).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onDelClicked(id);
//                GamesAdapter.this.removeRow(position);
                }
            });
            holder.itemView.setOnClickListener(view -> listener.onRowClicked(id, categoryOne,
                    categoryTwo, difficulty));
        } else if(game instanceof OldGame){
            holder.itemView.setOnClickListener(view -> ThreadUtils.mainThread()
                    .post(() -> Navigation.findNavController(view)
                            .navigate(R.id.action_quizAppStart_to_gameHistory)));
        }
    }

    private void removeRow(int position) {
        games.remove(position);//трием от List data
        notifyItemRemoved(position);//вграден метод на Recicle View за наместване на елементите
        // след изтриване на елемент на List data оправя и редовете в Recicle View
    }

    public void setOnRowClickedListener(OnRowClickedListener listener) {
        this.listener = listener;
    }

    public interface OnRowClickedListener {
        void onRowClicked(int id, String categoryOne, String categoryTwo, int difficulty);
        void onDelClicked(int id);
    }

    @Override
    public int getItemCount() {
        return games.size();
    }

}
