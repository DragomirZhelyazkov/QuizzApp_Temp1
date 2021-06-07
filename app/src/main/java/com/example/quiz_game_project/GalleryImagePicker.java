package com.example.quiz_game_project;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.ImageView;

public class GalleryImagePicker extends Activity {

    private Bitmap picture;
    Uri imageUri;
    private ImageView avatar;

    public Uri getImageUri() {
        return imageUri;
    }

    public Bitmap getPicture() {
        return picture;
    }

    public void pickImageFromGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 1){
            imageUri = data.getData();
        }
    }
}



