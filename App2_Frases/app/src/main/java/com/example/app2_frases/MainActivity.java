package com.example.app2_frases;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void nova(View view){
        result = findViewById(R.id.result);
        int n = new Random().nextInt(5);
        String[] frases = {
                            "Peace will win and fear will lose",
                            "I'm about to break out searching for a way out",
                            "I'm a believer",
                            "Live your dreams",
                            "Vai caraaai"};

        result.setText(""+ frases[n]);
    }
}