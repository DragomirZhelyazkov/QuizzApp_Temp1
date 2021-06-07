package com.example.quiz_game_project.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.FragmentNavigator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.quiz_game_project.PlayData.OldGame;
import com.example.quiz_game_project.QuizGameApplication;
import com.example.quiz_game_project.R;
import com.example.quiz_game_project.controllers.QuizAppStartController;
import com.example.quiz_game_project.data.CategoryRepository;
import com.example.quiz_game_project.data.QuestionRoundTwoRepo;
import com.example.quiz_game_project.data.QuestionsCategoryOneRepo;
import com.example.quiz_game_project.data.QuestionsCategoryTwoRepo;
import com.example.quiz_game_project.data.Repos.OldGamesRepo;
import com.example.quiz_game_project.data.local.AsyncDatabase;
import com.example.quiz_game_project.data.local.entities.GameEntity;
import com.example.quiz_game_project.data.remote.questions.models.AllCategories;
import com.example.quiz_game_project.data.remote.questions.models.Category;
import com.example.quiz_game_project.data.remote.questions.models.Result;
import com.example.quiz_game_project.databinding.FragmentQuizAppStartBinding;
import com.example.quiz_game_project.ui.adapters.AdapterData;
import com.example.quiz_game_project.ui.adapters.GamesAdapter;
import com.example.quiz_game_project.ui.adapters.Header;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class QuizAppStartFragment extends Fragment {

    private FragmentQuizAppStartBinding binding;
    private QuizAppStartController controller;
//    private Category[] categories;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentQuizAppStartBinding.inflate(inflater, container, false);
//        ((QuizGameApplication) getActivity().getApplication()).getAppComponent().inject(this);
        return this.binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupToolbar(view);
        controller = new QuizAppStartController(callback);
        controller.setCallback(callback);
//        controller.showErrorLiveData.observe(getViewLifecycleOwner(),
//                s -> Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show());
        initAddGameListener();

        controller.onUiLoaded();
        controller.saveAllCategories();

    }

    private void setupToolbar(View view) {
        Toolbar toolbar = binding.toolbar2;
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

    }

    private void initAddGameListener() {// да се оправят имената
        binding.btnCreateGame.setOnClickListener(view1 -> Navigation.findNavController(view1).navigate(R.id.action_quizAppStart_to_createGame));
    }



    private void setRecViewData(List<AdapterData> data) {
        GamesAdapter adapter = new GamesAdapter(data);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setAdapter(adapter);
        adapter.setOnRowClickedListener(new GamesAdapter.OnRowClickedListener() {
            @Override
            public void onRowClicked(int id, String categoryOne, String categoryTwo, int difficulty) {
                int categoryOneId =
                        CategoryRepository.getInstance().findCategoryIdByName(categoryOne);
                int categoryTwoId =
                        CategoryRepository.getInstance().findCategoryIdByName(categoryTwo);
                controller.saveGameQuestions(categoryOneId, categoryOne, categoryTwo, difficulty);
                controller.saveGameQuestions(categoryTwoId, categoryOne, categoryTwo, difficulty);
                Log.i("Info log",
                        "-----@@@@@@@@------ " + categoryOneId + "   "  + categoryTwoId);

                //ново------------------------------------------------------------------------
                controller.saveRoundTwoQuestions(categoryOneId, categoryOne, categoryTwo,
                        difficulty);
                controller.saveRoundTwoQuestions(categoryTwoId, categoryOne, categoryTwo,
                        difficulty);

                onRecycleRowClicked(id);
            }

            @Override
            public void onDelClicked(int id) {
                controller.removeGame(id);
            }
        });
    }

    private void onRecycleRowClicked(int id) {//????????????
        Bundle data = new Bundle();
        data.putInt("game_id", id);
        Navigation.findNavController(getView()).navigate(R.id.action_quizAppStart_to_existingGames, data);
    }

    private QuizAppStartController.QuizAppStartControllerCallback callback =
            new QuizAppStartController.QuizAppStartControllerCallback() {
                @Override
                //нoво=======================================================================
                public void showGames(List<GameEntity> data) {
                    if (data != null) {
                        List <AdapterData> adapterData = new ArrayList<>();
                        adapterData.add(new Header("Games"));
                        for (int i = 0; i < data.size(); i++){
                            adapterData.add(data.get(i));
                        }
                        adapterData.add(new Header("History"));
                        List<OldGame> oldGames = OldGamesRepo.getInstance().getData();
                        for (int i = 0; i < oldGames.size(); i++){
                            adapterData.add(oldGames.get(i));
                        }
                        Log.i("Info log", "-----OLDGAME------ " + adapterData.size());

                //=========================================================================

                    setRecViewData(adapterData); }
                }

                @Override
                public void saveCategories(Category[] data) {
//                    categories = data;
//                    for (int i = 0; i < data.length; i++) {
//                        Log.i("Info log", "-------------------- " + data[i].name);
                        CategoryRepository.getInstance().saveCategories(data);
//                    }
                }

                @Override
                public void saveQuestions(Result[] questions, String categoryOne,
                                          String categoryTwo) {
//                    Log.i("Info log", "-------------------- " + questions[0].category);
                    if (questions[0].category.equals(categoryOne)) {
                        QuestionsCategoryOneRepo.getInstance().setCategotyOneQuestions(questions);
//                        Log.i("Info log",
//                                "-------------------- " + QuestionsCategoryOneRepo.getInstance().getQuestion(4));
                    }
                    else {
                        QuestionsCategoryTwoRepo.getInstance().setCategotyTwoQuestions(questions);
//                        Log.i("Info log",
//                                "-------------------- " + QuestionsCategoryTwoRepo.getInstance().getQuestion(4));
                    }
                }

                @Override
                public void saveRoundTwoQuestions(Result[] questions, String categoryOne,
                                          String categoryTwo) {
//                    Log.i("Info log",
//                            "-----@@@@@@@@------ " + questions.length);
                    if (questions[0].category.equals(categoryOne)) {
                        QuestionRoundTwoRepo.getInstance().setRoundTwoQuestions(questions);
                        Log.i("Info log",
                                "-----@@@@@@@@1------ " + QuestionRoundTwoRepo.getInstance().getQuestion(12));
                    } else {
                        QuestionRoundTwoRepo.getInstance().setRoundTwoQuestions2(questions);
                        Log.i("Info log",
                                "-----@@@@@@@@2------ " + QuestionRoundTwoRepo.getInstance().getQuestion2(0));
                    }
                }
            };
}