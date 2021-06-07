package com.example.quiz_game_project.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.quiz_game_project.data.local.entities.GameEntity;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM games")
    List<GameEntity> getAll();

    @Query("SELECT * FROM games WHERE name LIKE :userName LIMIT 1")
    GameEntity findByName(String userName);

    @Query("SELECT * FROM games WHERE uid LIKE :id LIMIT 1")
    GameEntity findById(int id);


    @Insert
    void insert(GameEntity game);

    @Delete
    void delete(GameEntity game);
}
