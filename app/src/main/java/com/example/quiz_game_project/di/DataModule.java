package com.example.quiz_game_project.di;

import android.content.Context;

//Да се преименоват всички имена на класове! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! !

import com.example.quiz_game_project.data.local.AsyncDatabase;
import com.example.quiz_game_project.data.remote.questions.ApiWrapper;
import com.example.quiz_game_project.data.remote.questions.QuestionsApiService;
import com.example.quiz_game_project.data.remote.questions.ApiWrapper;
import com.example.quiz_game_project.data.remote.questions.QuestionsApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class DataModule {

//    @Provides
//    public ApiWrapper providesApiWrapper(AsyncDatabase asyncDatabase, QuestionsApiService service) {
//        return new ApiWrapper(asyncDatabase, service);
//    }
//
//    @Provides
//    public AsyncDatabase providesAsyncdatabase(Context context) {
//        return new AsyncDatabase(context);
//    }
//
//    @Singleton
//    @Provides
//    Retrofit provideRetrofit() {
//        return new Retrofit.Builder()
//                .baseUrl("https://opentdb.com")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//    }
//
//    @Singleton
//    @Provides
//    QuestionsApiService providesQuestionsService(Retrofit retrofit) {
//        return retrofit.create(QuestionsApiService.class);
//    }
}
