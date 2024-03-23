package com.cursoandroid.app10_listatarefas.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.cursoandroid.app10_listatarefas.R;
import com.cursoandroid.app10_listatarefas.helper.DAO;
import com.cursoandroid.app10_listatarefas.model.Tarefa;

public class AdicionarActivity extends AppCompatActivity {

    Button salvar;
    EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar); findA();

        salvar.setOnClickListener(view -> {
            DAO dao = new DAO(getApplicationContext());
            String nometa = text.getText().toString();
            Tarefa tarefa = new Tarefa();
            tarefa.setNome(nometa);
            dao.salvar(tarefa);
        });
    }

    private void findA() {
        salvar = findViewById(R.id.salvar);
        text = findViewById(R.id.textInputLayout);
    }
}