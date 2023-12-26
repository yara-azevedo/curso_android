package com.cursoandroid.fragments.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Button;

import com.cursoandroid.fragments.R;
import com.cursoandroid.fragments.fragment.AFragment;
import com.cursoandroid.fragments.fragment.BFragment;

public class MainActivity extends AppCompatActivity {
    Button buttonA, buttonB;
    private AFragment aFragment;
    private BFragment bFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        buttonA = findViewById(R.id.buttonA);
        buttonA.setOnClickListener(view -> {
            aFragment = new AFragment();
            FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.framelayout, aFragment);
            transaction.commit();
        });

        buttonB = findViewById(R.id.buttonB);
        buttonB.setOnClickListener(view -> {
            bFragment = new BFragment();
            FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.framelayout, bFragment); //o replace consegue mudar os fragmentos
           // transaction.add(R.id.framelayout, aFragment); //o add empilha os fragmentos um em cima do outro (dá pra usar quando só tiver um)
            transaction.commit();
        });
    }
}