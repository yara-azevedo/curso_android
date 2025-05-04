package com.example.app10_listatarefas.activity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app10_listatarefas.R;
import com.example.app10_listatarefas.adapter.TarefaAdapter;
import com.example.app10_listatarefas.helper.DAO;
import com.example.app10_listatarefas.helper.DBHelper;
import com.example.app10_listatarefas.helper.RecyclerItemClickListener;
import com.example.app10_listatarefas.model.Tarefa;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fob;
    private RecyclerView recyclerView;
    private TarefaAdapter tarefaAdapter;
    private List<Tarefa> listaTarefas = new ArrayList<>();

    private Tarefa tarefaSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        findAll();
        fob();

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //recuperando a tarefa
                Tarefa tarefaSelecionada = listaTarefas.get(position);
                Intent intent = new Intent(MainActivity.this, TarefaActivity.class);
                intent.putExtra("tarefaSelecionada", tarefaSelecionada);
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {
                tarefaSelecionada = listaTarefas.get(position);

                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("confirmar");
                dialog.setMessage("deseja excluir: " +tarefaSelecionada.getNome() + " ?" );

                dialog.setPositiveButton("sim", (dialog1, which) -> {
                    DAO dao = new DAO(getApplicationContext());
                    if(dao.deletar(tarefaSelecionada)){
                        listaTarefas();
                        Toast.makeText(getApplicationContext(), "Sucesso ao excluir tarefa!",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "Erro ao excluir tarefa!",Toast.LENGTH_SHORT).show();
                    }
                });

                dialog.setNegativeButton("não", null);

                dialog.create();
                dialog.show();
            }

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        }));



    }

    public void listaTarefas(){
        //listar
       DAO dao = new DAO(getApplicationContext());
       listaTarefas = dao.listar();

        tarefaAdapter= new TarefaAdapter(listaTarefas);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerView.setAdapter(tarefaAdapter);
    }

    @Override
    protected void onStart() {
        listaTarefas();
        super.onStart();
    }

    public void fob(){
        fob.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), TarefaActivity.class);
            startActivity(intent);
        });
    }
    public void findAll(){
        fob = findViewById(R.id.fob);
        recyclerView = findViewById(R.id.recyclerView);
    }


}