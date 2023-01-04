package com.cursoandroid.recyclerview.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cursoandroid.recyclerview.R;
import com.cursoandroid.recyclerview.RecyclerItemClickListener;
import com.cursoandroid.recyclerview.adapter.Adapter;
import com.cursoandroid.recyclerview.model.Filme;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Filme> filmes= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        recyclerView = findViewById(R.id.recyclerView);

        this.criarFilmes();

        //adapter
        Adapter adapter = new Adapter(filmes);

        //configurar
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL)); //mostra uma linha de divisao entre os itens
        recyclerView.setAdapter(adapter);

        //eventos de click
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Filme filme = filmes.get(position);
                Toast.makeText(MainActivity.this, filme.getTitulo() + " pressionado", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, "looongo", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        }));
    }

    public void criarFilmes(){
        Filme filme = new Filme("A", "b", "2022");
        this.filmes.add(filme);
        filme = new Filme("A", "b", "2022");
        this.filmes.add(filme);
        filme = new Filme("A", "c", "2022");
        this.filmes.add(filme);
        filme = new Filme("A", "d", "2022");
        this.filmes.add(filme);
        filme = new Filme("A", "b", "2021");
        this.filmes.add(filme);
        filme = new Filme("A", "b", "2020");
        this.filmes.add(filme);
        filme = new Filme("A", "b", "2022");
        this.filmes.add(filme);
        filme = new Filme("A", "c", "2022");
        this.filmes.add(filme);
        filme = new Filme("A", "d", "2022");
        this.filmes.add(filme);
        filme = new Filme("A", "b", "2021");
        this.filmes.add(filme);
        filme = new Filme("A", "b", "2020");
        this.filmes.add(filme);
        filme = new Filme("A", "b", "2022");
        this.filmes.add(filme);
        filme = new Filme("A", "c", "2022");
        this.filmes.add(filme);
        filme = new Filme("A", "d", "2022");
        this.filmes.add(filme);
        filme = new Filme("A", "b", "2021");
        this.filmes.add(filme);
        filme = new Filme("A", "b", "2020");
        this.filmes.add(filme);


    }
}