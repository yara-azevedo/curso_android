package com.example.app6_caracoroa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView img1, img2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findA();
        action();
    }

    public void action() {
        img2.setOnClickListener(view ->{
            Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
            int numero = new Random().nextInt(3);
            intent.putExtra("numero", numero); //passa as informações pra proximam tela
            startActivity(intent);
                });
    }

    public void findA() {
        img1 = findViewById(R.id.imageView);
        img2 = findViewById(R.id.imageView2);
    }
}