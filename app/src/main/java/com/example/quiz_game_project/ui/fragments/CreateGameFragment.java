package com.example.quiz_game_project.ui.fragments;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quiz_game_project.R;
import com.example.quiz_game_project.data.CategoryRepository;
import com.example.quiz_game_project.data.local.AsyncDatabase;
import com.example.quiz_game_project.data.local.CurrentGame;
import com.example.quiz_game_project.data.local.entities.GameEntity;
import com.example.quiz_game_project.data.remote.questions.models.Category;
import com.example.quiz_game_project.databinding.FragmentCreateGameBinding;


public class CreateGameFragment extends Fragment {

    private AsyncDatabase asyncDatabase;
    private FragmentCreateGameBinding binding;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCreateGameBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupToolbar(view);

        binding.btnCreateGame.setOnClickListener(view1 -> onCreateGameClicked(view));
        binding.imgShowCategories.setOnClickListener(view1 -> addCategory(view));
        binding.imgDelCategoryOne.setOnClickListener(view2 -> binding.txtCategoryOne.setText(
                "CategoryOne"));
        binding.imgDelCategoryTwo.setOnClickListener(view2 -> binding.txtCategoryTwo.setText(
                "CategoryTwo"));
        binding.txtIcon.setOnClickListener(view3 -> addGameIcon());
    }

    private void onCreateGameClicked(@NonNull View view) {
        addGameToDb();
        Navigation.findNavController(view).navigate(R.id.action_createGame_to_quizAppStart);
    }

    private void addCategory(View view) {
        String[] categories = CategoryRepository.getInstance().getData();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose category")
                .setItems(categories, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position of the selected item
                        if (binding.txtCategoryOne.getText().toString().equals("CategoryOne")){
                            binding.txtCategoryOne.setText(categories[which]);
                        } else if(binding.txtCategoryTwo.getText().toString().equals("CategoryTwo")){
                            binding.txtCategoryTwo.setText(categories[which]);
                        }
                    }
                });
        builder.create().show();
    }

    private void addGameIcon() {
        String[] icons = new String[3];
        icons[0] = new String(Character.toChars(0x1F601));
        icons[1] = new String(Character.toChars(0x1F61C));
        icons[2] = new String(Character.toChars(0x1F625));
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose Icon")
                .setItems(icons, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position of the selected item
                        binding.txtIcon.setText(icons[which]);
                    }
                });
        builder.create().show();
    }

    private void addGameToDb() {
        GameEntity gameEntity = new GameEntity();
        gameEntity.setIcon(binding.txtIcon.getText().toString());
        gameEntity.setName(binding.editTextTextGameName.getText().toString());
        gameEntity.setCategory1(binding.txtCategoryOne.getText().toString());
        gameEntity.setCategory2(binding.txtCategoryTwo.getText().toString());
        gameEntity.setQuestiondeley(binding.seekQuestiondeley.getProgress());
        gameEntity.setFirstRoundQuestionAnswer(binding.seekFirstRoundQuestionAnswer.getProgress());
        gameEntity.setSecondRoundQuestionAnswer(binding.seekSecondRoundQuestionAnswer.getProgress() + 1);
        gameEntity.setSecondroundduration(binding.seekSecondroundduration.getProgress());
        gameEntity.setDifficulty(binding.gameDificulty.getProgress());
        gameEntity.setLastplayed("   ");
        gameEntity.setTopplayer("   ");
        gameEntity.setSource("Remote");
        gameEntity.setFlag("active");
        AsyncDatabase.getInstance().insert(gameEntity);
    }

    private void setupToolbar(@NonNull View view) {
        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_container);
        AppBarConfiguration appBarConfiguration =
                new AppBarConfiguration.Builder(navController.getGraph()).build();
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        NavigationUI.setupWithNavController(
                toolbar, navController, appBarConfiguration);
    }

}