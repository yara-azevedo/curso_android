package com.cursoandroid.app8_aprendaingls.fragments;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.cursoandroid.app8_aprendaingls.R;

public class BichosFragment extends Fragment {

    private ImageButton buttonCao, buttonGato, buttonLeao, buttonMacaco, buttonOvelha, buttonVaca;
    private MediaPlayer mediaPlayer;

    public BichosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bichos, container, false);

        buttonCao = view.findViewById(R.id.cao);
        buttonGato = view.findViewById(R.id.gato);
        buttonLeao = view.findViewById(R.id.leao);
        buttonMacaco = view.findViewById(R.id.macaco);
        buttonOvelha = view.findViewById(R.id.ovelha);
        buttonVaca = view.findViewById(R.id.vaca);

        //Aplica eventos de click
       /* buttonCao.setOnClickListener(this);
        buttonGato.setOnClickListener(this);
        buttonLeao.setOnClickListener(this);
        buttonMacaco.setOnClickListener(this);
        buttonOvelha.setOnClickListener(this);
        buttonVaca.setOnClickListener(this);


        return view;
    }

   /* @Override
    public void onClick(View view) {

        switch ( view.getId() ) {
            case R.id.cao :
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.dog);
                tocarSom();
                break;
            case R.id.gato :
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.cat);
                tocarSom();
                break;
            case R.id.buttonLeao :
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.lion);
                tocarSom();
                break;
            case R.id.macaco :
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.monkey);
                tocarSom();
                break;
            case R.id.ovelha :
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.sheep);
                tocarSom();
                break;
            case R.id.vaca :
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.cow);
                tocarSom();
                break;

        }

    }

    /*public void tocarSom(){
        if ( mediaPlayer != null ){
            mediaPlayer.start();

            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.release();
                }
            });

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer !=null){
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }*/

    }

