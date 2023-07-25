package com.cursoandroid.passandodadosactivitiesd;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    private TextView um, dois;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        um = findViewById(R.id.textView);
        dois = findViewById(R.id.textView2);

        Bundle bundle = getIntent().getExtras();

        //recuperar  objeto
        Usuario usuario = (Usuario) bundle.getSerializable("objeto");//precisa fazer o cast
        um.setText(usuario.getNome());
        dois.setText(String.valueOf(usuario.getIdade()));
        //recuperar dados
        String nome =bundle.getString("nome");
        int b =bundle.getInt("b");

        //um.setText(nome);
        //dois.setText(String.valueOf(b));


    }
}