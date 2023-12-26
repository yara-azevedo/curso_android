package com.cursoandroid.snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    Button snack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        snack = findViewById(R.id.button);

        snack.setOnClickListener(view -> {
            Snackbar.make(view, "aaaaa", Snackbar.LENGTH_INDEFINITE).setAction("Confirmar", view1 -> {
                snack.setText("alterou");
            }).show(); //.lenght_short ou lenght_long(precisa adicionar uma acao)
        });

    }

    public void abrirSnack(View view){ //pra separar o metodo, é só usar essa view

    }
}