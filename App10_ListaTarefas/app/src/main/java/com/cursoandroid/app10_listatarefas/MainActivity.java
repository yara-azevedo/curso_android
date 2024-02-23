package com.cursoandroid.app10_listatarefas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findA();

        fob.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AdicionarActivity.class);
            startActivity(intent);
        });
    }

    private void findA() {
        fob = findViewById(R.id.floatingActionButton);
    }
}