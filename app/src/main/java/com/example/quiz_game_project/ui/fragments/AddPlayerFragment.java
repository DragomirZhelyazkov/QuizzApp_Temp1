package com.example.quiz_game_project.ui.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.quiz_game_project.PlayData.FirstRoundCounter;
import com.example.quiz_game_project.PlayData.PlayerFour;
import com.example.quiz_game_project.PlayData.PlayerOne;
import com.example.quiz_game_project.PlayData.PlayerThree;
import com.example.quiz_game_project.PlayData.PlayerTwo;
import com.example.quiz_game_project.R;
import com.example.quiz_game_project.data.local.CurrentGame;
import com.example.quiz_game_project.databinding.FragmentAddPlayerBinding;


public class AddPlayerFragment extends Fragment {

    private static final int PICK_IMAGE = 1;
    private FragmentAddPlayerBinding binding;
    private NavController navController;
    private EditText name;
    private ImageView image;
    private Bitmap imageBitmap;
    private Bitmap galleryImageBitmap;
    private Dialog dialog;
    private Uri uri;
    private int numberOfPlayers;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddPlayerBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String source = getArguments().getString("source");

        setupToolbar(view);

        binding.addPlayer1.setOnClickListener(view1 -> onButtonАddPlayer1Clicked(1, view));
        binding.addPlayer2.setOnClickListener(view2 -> onButtonАddPlayer1Clicked(2, view));
        binding.addPlayer3.setOnClickListener(view3 -> onButtonАddPlayer1Clicked(3, view));
        binding.addPlayer4.setOnClickListener(view4 -> onButtonАddPlayer1Clicked(4, view));
        binding.btnStart.setOnClickListener(view1 -> onButtonStartClicked(view));
        binding.txtTitleTool3.setText(CurrentGame.name);
    }

    private void onButtonАddPlayer1Clicked(int index, View view) {
// Create custom dialog object
        dialog = new Dialog(getContext());
        // Include dialog.xml file
        dialog.setContentView(R.layout.addplayerdialog);
        // Set dialog title
        dialog.setTitle("AddPlayer");
        // set values for custom dialog components - text, image and button
        name = (EditText) dialog.findViewById(R.id.editName);
//        ImageView image = (ImageView) dialog.findViewById(R.id.imageView6);
        image = (ImageView) dialog.findViewById(R.id.imageView6);
//        image.setImageResource(R.drawable.image0);

        dialog.show();

        Button gallery = (Button) dialog.findViewById(R.id.button);
        Button camera = (Button) dialog.findViewById(R.id.button2);
        Button cancel = (Button) dialog.findViewById(R.id.button4);
        Button add = (Button) dialog.findViewById(R.id.button3);
        // if decline button is clicked, close the custom dialog

        cancel.setOnClickListener((View.OnClickListener) v -> { dialog.dismiss(); });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddPlayerFragment.this.addPlayer(index);
            }
        });
        camera.setOnClickListener((View.OnClickListener) v -> { getCameraImage(); });
        gallery.setOnClickListener((View.OnClickListener) v -> { getGalleryImage(); });
    }

    private void getGalleryImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
    }

    private void getCameraImage() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePictureIntent, 11);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 11 && resultCode == Activity.RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            image.setImageBitmap(imageBitmap);

        } else if(requestCode == 1 && resultCode == Activity.RESULT_OK) {
            uri =  data.getData();
            //-----------------------------------------------------------------------------
            String path = getPathFromURI(uri);
            // Set the image in ImageView
            image.setImageURI(uri);
            imageBitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
            }

    }

    public String getPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getActivity().getContentResolver().query(contentUri, proj, null, null,
                null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }

    private void addPlayer(int i) {
        switch(i) {
            case 1: binding.addPlayer1.setImageBitmap(imageBitmap);
                break;
            case 2: binding.addPlayer2.setImageBitmap(imageBitmap);
                break;
            case 3: binding.addPlayer3.setImageBitmap(imageBitmap);
                break;
            case 4: binding.addPlayer4.setImageBitmap(imageBitmap);
                break;
        }

        switch(i) {
            case 1: binding.name1.setText(name.getText().toString());
                break;
            case 2: binding.name2.setText(name.getText().toString());
                break;
            case 3: binding.name3.setText(name.getText().toString());
                break;
            case 4: binding.name4.setText(name.getText().toString());
                break;
        }
        CurrentGame.numberOfPlayers = CurrentGame.numberOfPlayers + 1;
        dialog.dismiss();
    }


    private void onButtonStartClicked(View view) {
        FirstRoundCounter.roundNumber = 0;
        Log.i("Info log",
                "-------------------- " + FirstRoundCounter.roundNumber);
        PlayerOne.name = binding.name1.getText().toString();
        PlayerOne.playerOneScore = 1;
        PlayerTwo.name = binding.name2.getText().toString();
        PlayerTwo.playerTwoScore = 1;
        PlayerThree.name = binding.name3.getText().toString();
        PlayerThree.playerThreeScore = 1;
        PlayerFour.name = binding.name4.getText().toString();
        PlayerFour.playerFourScore = 1;

        Log.i("Info log",
                "------NnumPlayers----- " + CurrentGame.numberOfPlayers);

        Bundle fragmentData = new Bundle();
        fragmentData.putString("name1", binding.name1.getText().toString());
        fragmentData.putString("name2", binding.name2.getText().toString());
        fragmentData.putString("name3", binding.name3.getText().toString());
        fragmentData.putString("name4", binding.name4.getText().toString());
        Navigation.findNavController(view).navigate(R.id.action_addPlayerFragment_to_firstRoundPutFingersFragment2, fragmentData);
    }


    private void setupToolbar(@NonNull View view) {//в този метод свързваме toolbar и navController
        //създаваме Нав-контролер, containerView - къде се намира nav_graph
        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_container);
        //долното - готова схема копирана от сайт
        AppBarConfiguration appBarConfiguration =
                new AppBarConfiguration.Builder(navController.getGraph()).build();
        //създаваме тоолбар
        Toolbar toolbar = binding.toolbar4;
        //свързваме toolbar с navController
        NavigationUI.setupWithNavController(
                toolbar, navController, appBarConfiguration);
    }

}