package com.cursoandroid.executandovideos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class PlayerActivity extends AppCompatActivity {

    VideoView viewV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        viewV = findViewById(R.id.videoView);

        viewV.setMediaController(new MediaController(this)); //controles de execucao
        viewV.setVideoPath("android.resource://" +getPackageName() + "/" + R.raw.video); //local
        viewV.start();
    }
}