package com.cursoandroid.cardview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Model> postagens = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        getSupportActionBar().hide();

        //define layout
        //os itens ficam um ao lado do outro, pra visualziar a lista precisa arrastar pro lado
        //LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //layoutManager.setOrientation(RecyclerView.HORIZONTAL); //p visualizar os
        //recyclerView.setLayoutManager(layoutManager);
        //aqui os itens ficam em grade
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        this.prepararPostagens();

        Adapter adapter = new Adapter(postagens);
        recyclerView.setAdapter(adapter);

    }

    public void prepararPostagens(){
        Model p = new Model("AAA", "top", R.drawable.img);
        this.postagens.add(p);
        p = new Model("asasd", "ssdfsdf", R.drawable.img2);
        this.postagens.add(p);
        p = new Model("fsdf", "fssdfs", R.drawable.img3);
        this.postagens.add(p);
        p = new Model("AAA", "top", R.drawable.img);
        this.postagens.add(p);
        p = new Model("asasd", "ssdfsdf", R.drawable.img2);
        this.postagens.add(p);
        p = new Model("fsdf", "fssdfs", R.drawable.img3);
        this.postagens.add(p);
        p = new Model("AAA", "top", R.drawable.img);
        this.postagens.add(p);
        p = new Model("asasd", "ssdfsdf", R.drawable.img2);
        this.postagens.add(p);
        p = new Model("fsdf", "fssdfs", R.drawable.img3);
        this.postagens.add(p);


    }
}