package com.parse.starter.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.starter.R;

import java.util.ArrayList;
import java.util.List;

import adapter.HomeAdapter;

public class UserFActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView listView;
    private String username;
    private ArrayAdapter<ParseObject> adapter;
    private ArrayList<ParseObject> postagens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_factivity);

        // Recupera o nome do usuário passado pela intent
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        postagens = new ArrayList<>();

        // Configura a toolbar
        toolbar = findViewById(R.id.toolbar_user_f);
        if (toolbar != null) {
            toolbar.setTitle(username);
            toolbar.setTitleTextColor(Color.WHITE);
            setSupportActionBar(toolbar);
            
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                // Define o fundo como preto explicitamente no Action Bar
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.BLACK));
            }
        }

        // Configura a cor da barra de status para preto
        getWindow().setStatusBarColor(Color.BLACK);

        // Configura o listview e o adapter
        listView = findViewById(R.id.listView);
        adapter = new HomeAdapter(UserFActivity.this, postagens);
        listView.setAdapter(adapter);

        // Busca as postagens do usuário
        getPostagens();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish(); // Fecha a activity ao clicar no botão voltar
        return true;
    }

    private void getPostagens() {
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("imagem");
        query.whereEqualTo("username", username);
        query.orderByDescending("createdAt");

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e == null){
                    postagens.clear();
                    if(objects.size() > 0){
                        postagens.addAll(objects);
                    }
                    adapter.notifyDataSetChanged();
                }else {
                    e.printStackTrace();
                }
            }
        });
    }
}
