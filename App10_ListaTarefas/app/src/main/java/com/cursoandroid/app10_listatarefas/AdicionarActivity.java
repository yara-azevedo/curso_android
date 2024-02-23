package com.cursoandroid.app10_listatarefas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class AdicionarActivity extends AppCompatActivity {

    Button salvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar); findA();

        salvar.setOnClickListener(view -> {
            Intent intent = new Intent(AdicionarActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void findA() {
        salvar = findViewById(R.id.salvar);
    }
}