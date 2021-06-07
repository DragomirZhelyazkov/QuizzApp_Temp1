package com.example.quiz_game_project.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quiz_game_project.R;
import com.example.quiz_game_project.data.local.entities.GameEntity;

import java.util.List;

public class ArchivAdapter {
//    public class GamesAdapter extends RecyclerView.Adapter<CustomViewHolder> {//===
//
//        private List<GameEntity> games;
//        //private List<String> selectedUrls;
//        private com.example.quiz_game_project.ui.adapters.GamesAdapter.OnRowClickedListener listener;
//
//        public GamesAdapter(List<GameEntity> games) {
//            this.games = games;
//            //this.selectedUrls = new ArrayList<>();
//        }
//
////    @Override
////    public int getItemViewType(int position) {//да се имплементира !!!!!!!!!!!!!!!!!!!!!!!!!!!!
////        return position;
////    }
//
//        @NonNull
//        @Override
//        public ActiveGameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_image,
//                    parent,
//                    false);
//            return new ActiveGameViewHolder(itemView);
//        }
//
//        @Override
//        public void onBindViewHolder(@NonNull ActiveGameViewHolder holder, int position) {
//            GameEntity game = games.get(position);
//            holder.bindData(game);
//            int id = game.getUid();
//            String categoryOne = game.getCategory1();
//            String categoryTwo = game.getCategory2();
//            int difficulty = game.getDifficulty();
//            holder.itemView.findViewById(R.id.imgDel).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    listener.onDelClicked(id);
////                GamesAdapter.this.removeRow(position);
//                }
//            });
//            holder.itemView.setOnClickListener(view -> listener.onRowClicked(id, categoryOne,
//                    categoryTwo, difficulty));
//        }
//
//        private void removeRow(int position) {
//            games.remove(position);//трием от List data
//            notifyItemRemoved(position);//вграден метод на Recicle View за наместване на елементите
//            // след изтриване на елемент на List data оправя и редовете в Recicle View
//        }
//
//        public void setOnRowClickedListener(com.example.quiz_game_project.ui.adapters.GamesAdapter.OnRowClickedListener listener) {
//            this.listener = listener;
//        }
//
//        public interface OnRowClickedListener {
//            void onRowClicked(int id, String categoryOne, String categoryTwo, int difficulty);
//            void onDelClicked(int id);
//        }
//
//        @Override
//        public int getItemCount() {
//            return games.size();
//        }
//
//    }
}
