package com.example.quiz_game_project.data.local;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.quiz_game_project.ThreadUtils;
import com.example.quiz_game_project.data.local.entities.GameEntity;

import java.util.List;

public class AsyncDatabase {

    private static AsyncDatabase instance;
    private final UserDao userDao;

    public static void init(Context context) {
        if(instance == null) {
            instance = new AsyncDatabase(context);
        }
    }

    public static AsyncDatabase getInstance() {
        if(instance == null) {
            throw new NullPointerException("AsyncDatabase is not initiliazized.");
        }
        return instance;
    }

    public AsyncDatabase(Context context) {
        userDao = AppDatabase.getInstance(context).userDao();
    }

    public void insert(final GameEntity gameEntity) {
        ThreadUtils.executorService().submit(() -> userDao.insert(gameEntity));
    }

    public void getUserByNameAndAge(String name, int age, DataReceivedListener<GameEntity> callback) {
        ThreadUtils.executorService().submit(() -> {
            GameEntity gameEntity = userDao.findByName(name);
            ThreadUtils.mainThread().post(() -> callback.onDataReceived(gameEntity));
        });
    }

    public void getAllGames(DataReceivedListener<List<GameEntity>> callback) {
        ThreadUtils.executorService().submit(() -> {
            List<GameEntity> list = userDao.getAll();
            ThreadUtils.mainThread().post(() -> callback.onDataReceived(list));

        });
    }

    public void getGameById(int id, DataReceivedListener<GameEntity> callback) {
        ThreadUtils.executorService().submit(() -> {
            GameEntity game = userDao.findById(id);
            ThreadUtils.mainThread().post(() -> callback.onDataReceived(game));

        });
    }

    public void delete(final GameEntity gameEntity) {
        ThreadUtils.executorService().submit(() -> userDao.delete(gameEntity));
    }

    public interface DataReceivedListener<T> {
        void onDataReceived(T data);
    }
}
