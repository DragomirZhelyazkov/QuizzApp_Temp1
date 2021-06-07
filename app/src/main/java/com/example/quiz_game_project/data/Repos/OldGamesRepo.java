package com.example.quiz_game_project.data.Repos;

import com.example.quiz_game_project.PlayData.OldGame;
import com.example.quiz_game_project.data.CategoryRepository;
import com.example.quiz_game_project.data.remote.questions.models.Category;

import java.util.ArrayList;
import java.util.List;

public class OldGamesRepo {

    private List<OldGame> data;

    private static OldGamesRepo instance;

    public static OldGamesRepo getInstance() {
        if(instance == null) instance = new OldGamesRepo();
        return instance;
    }

    private OldGamesRepo() {
        this.data = new ArrayList<>();
    }

    public void setData(List<OldGame> list) {
        this.data = list;
    }

    public List<OldGame> getData() {
        return data;
    }

    public void addOldGame(OldGame game){
        data.add(game);
    }


}
