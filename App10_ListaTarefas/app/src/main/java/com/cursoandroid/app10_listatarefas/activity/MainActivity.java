package com.cursoandroid.app10_listatarefas.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.cursoandroid.app10_listatarefas.R;
import com.cursoandroid.app10_listatarefas.helper.DAO;
import com.cursoandroid.app10_listatarefas.helper.DBHelper;
import com.cursoandroid.app10_listatarefas.helper.RecyclerItemClickListener;
import com.cursoandroid.app10_listatarefas.adapter.Adapter;
import com.cursoandroid.app10_listatarefas.model.Tarefa;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fob;
    RecyclerView recyclerView;

    Adapter adapter;
    List<Tarefa> tarefaList = new ArrayList<>();

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

    @Override
    protected void onStart() {
        lista();
        super.onStart();
    }

    private void findA() {
        fob = findViewById(R.id.floatingActionButton);
        recyclerView = findViewById(R.id.recyclerView);



        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void onLongItemClick(View view, int position) {

            }

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        }));
    }

    public void lista(){

        DAO dao= new DAO(getApplicationContext());
        tarefaList = dao.listar();

        adapter = new Adapter(tarefaList);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerView.setAdapter(adapter);
    }
}