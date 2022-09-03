package com.example.app3_jokenpo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void pedra(View view){
        this.opcaoSelecionada("pedra");
    }

    public void papel(View view){
        this.opcaoSelecionada("papel");
    }

    public void tesoura(View view){
        this.opcaoSelecionada("tesoura");
    }

    public void opcaoSelecionada(String opcaoSelecionada){ //pelo user
        ImageView imageView = findViewById(R.id.imageView);
        TextView textView = findViewById(R.id.resultado);
        int random = new Random().nextInt(3);
        String[] opcoes = {"pedra", "papel", "tesoura"};
        String opcaoApp = opcoes [random]; //associa os numeros aleatorios aos elementos do array

        switch(opcaoApp){
            case  "pedra":
                imageView.setImageResource(R.drawable.pedra);
                break;
            case  "papel":
                imageView.setImageResource(R.drawable.papel);
                break;
            case  "tesoura":
                imageView.setImageResource(R.drawable.tesoura);
                break;
        }

        if((opcaoApp=="tesoura" && opcaoSelecionada=="papel") ||
           (opcaoApp=="papel" && opcaoSelecionada=="pedra") ||
           (opcaoApp=="pedra" && opcaoSelecionada=="tesoura")){ //app ganha
                textView.setText("Você perdeu :(");
        }else if(
                (opcaoSelecionada=="tesoura" && opcaoApp=="papel") ||
                (opcaoSelecionada=="papel" && opcaoApp=="pedra") ||
                (opcaoSelecionada=="pedra" && opcaoApp=="tesoura")
        ){ //user ganha
            textView.setText("Você ganhou :D");
        }else{ //empate
            textView.setText("Empate :o");
        }
    }
}