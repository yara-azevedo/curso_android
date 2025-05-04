package com.example.app10_listatarefas.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.app10_listatarefas.R;
import com.example.app10_listatarefas.helper.DAO;
import com.example.app10_listatarefas.model.Tarefa;

public class TarefaActivity extends AppCompatActivity {
    private Button salvar;
    private EditText editText;

    private Tarefa tarefaAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tarefa);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        findAll();
        salvarTarefa();

        tarefaAtual = (Tarefa) getIntent().getSerializableExtra("tarefaSelecionada");
        if(tarefaAtual!=null){
            editText.setText(tarefaAtual.getNome());
        }
    }

    private void salvarTarefa() {
        salvar.setOnClickListener(v -> {
            DAO tarefaDAO = new DAO( getApplicationContext() );

            if ( tarefaAtual != null ){//edicao

                String nomeTarefa = editText.getText().toString();
                if ( !nomeTarefa.isEmpty() ){

                    Tarefa tarefa = new Tarefa();
                    tarefa.setNome( nomeTarefa);
                    tarefa.setId( tarefaAtual.getId() );

                    //atualizar no banco de dados
                    if ( tarefaDAO.atualizar( tarefa ) ){
                        finish();
                        Toast.makeText(getApplicationContext(),
                                "Sucesso ao atualizar tarefa!",
                                Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getApplicationContext(),
                                "Erro ao atualizar tarefa!",
                                Toast.LENGTH_SHORT).show();
                    }

                }

            }else {//salvar

                String nomeTarefa = editText.getText().toString();
                if ( !nomeTarefa.isEmpty() ){
                    Tarefa tarefa = new Tarefa();
                    tarefa.setNome( nomeTarefa );

                    if ( tarefaDAO.salvar( tarefa ) ){
                        finish();
                        Toast.makeText(getApplicationContext(),
                                "Sucesso ao salvar tarefa!",
                                Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getApplicationContext(),
                                "Erro ao salvar tarefa!",
                                Toast.LENGTH_SHORT).show();
                    }


                }

            }
        });

    }

    public void findAll(){
        salvar = findViewById(R.id.salvar);
        editText = findViewById(R.id.textEdit);
    }
}