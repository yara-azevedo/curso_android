package com.cursoandroid.mark.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.cursoandroid.mark.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fob = findViewById(R.id.add_note_btn);


        fob.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, DetalheActivity.class));
        });


    }
}