package com.example.quiz_game_project.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.quiz_game_project.data.local.entities.GameEntity;

@Database(entities = {GameEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public static AppDatabase getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context,
                    AppDatabase.class, "games.db").build();
        }
        return instance;
    }


    public abstract UserDao userDao();

}
