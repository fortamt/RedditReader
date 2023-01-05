package com.example.android.redditreader.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.android.redditreader.R;
import com.example.android.redditreader.databinding.ActivityFullScreenImageBinding;
import com.squareup.picasso.Picasso;

public class FullScreenImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_full_screen_image);

        ActivityFullScreenImageBinding activityFullScreenImageBinding = DataBindingUtil.setContentView(
                this, R.layout.activity_full_screen_image);

        ImageView fullScreenImageView = activityFullScreenImageBinding.fullScreenImageView;

        Intent callingActivityIntent = getIntent();
        if (callingActivityIntent != null) {
            Uri imageUri = callingActivityIntent.getData();
            if(imageUri != null) {
                Picasso.get().load(imageUri)
                        .placeholder(R.drawable.loading)
                        .fit()
                        .centerInside()
                        .into(fullScreenImageView);
            }
        }
    }
}