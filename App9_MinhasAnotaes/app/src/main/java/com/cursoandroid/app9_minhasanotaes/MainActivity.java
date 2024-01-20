package com.cursoandroid.app9_minhasanotaes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    FloatingActionButton fob;

    private SharedPreferencesss preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = new SharedPreferencess(getApplicationContext());
        findA();

        fob.setOnClickListener(view -> {

        });

    }

    private void findA() {
        editText = findViewById(R.id.editTextTextMultiLine);
        fob = findViewById(R.id.floatingActionButton);
    }
}