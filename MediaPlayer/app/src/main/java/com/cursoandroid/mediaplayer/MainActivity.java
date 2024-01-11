package com.cursoandroid.mediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    ImageButton play, pause, stop;
    SeekBar seekBar;

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findAll();

        mediaPlayer= MediaPlayer.create(getApplicationContext(),R.raw.aa_darkvoid);

        play.setOnClickListener(view -> {
            if(mediaPlayer!=null){
                mediaPlayer.start();
            }
        });

        pause.setOnClickListener(view -> {
            if(mediaPlayer.isPlaying()){
                mediaPlayer.pause();
            }
        });

        stop.setOnClickListener(view -> {
            if(mediaPlayer.isPlaying()){
                mediaPlayer.stop();
                mediaPlayer= MediaPlayer.create(getApplicationContext(),R.raw.aa_darkvoid);
            }
        });
        seekVolume();

    }

    private void seekVolume() {
        //audio manager
        AudioManager audioManager;
        audioManager = (AudioManager) getSystemService(this.AUDIO_SERVICE);
        //volume
        int max = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int atual = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        //max da seek bar
        seekBar.setMax(max);
        //atual
        seekBar.setProgress(atual);
        //
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,i, AudioManager.FLAG_SHOW_UI);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    public void findAll(){
        play = findViewById(R.id.play);
        pause = findViewById(R.id.pause);
        stop = findViewById(R.id.stop);
        seekBar = findViewById(R.id.seekBar);
    }

    protected void onStop(){ //para a musica quando o app sai do app
        super.onStop();
        if(mediaPlayer.isPlaying()){
            mediaPlayer.pause();
   }
    }

    protected void onDestroy() { //para a musica quando o app sai do app
        super.onDestroy();
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();//libera da memoria a musica
            mediaPlayer = null;

        }

    }}