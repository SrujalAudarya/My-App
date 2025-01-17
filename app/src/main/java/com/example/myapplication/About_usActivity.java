package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class About_usActivity extends AppCompatActivity {

    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        setTitle("About Us");

        videoView = findViewById(R.id.vv_about_us);

        String videopath = "android.resource://"+getPackageName()+"/raw/video";
        videoView.setVideoPath(videopath);
        videoView.start();

        MediaController mediaController = new MediaController(About_usActivity.this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
    }
}