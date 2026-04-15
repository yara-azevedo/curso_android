package com.cursoandroid.eaiapp.activity.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;

import com.cursoandroid.eaiapp.R;
import com.cursoandroid.eaiapp.activity.adapter.TabAdapter;
import com.cursoandroid.eaiapp.activity.config.ConfiguracaoFirebase;
import com.google.firebase.auth.FirebaseAuth;

import helper.SlidingTabLayout;

public class MainActivity extends AppCompatActivity {

    private Button sair;
    private FirebaseAuth firebaseAuth, firebaseAuth1 ;
    private Toolbar toolbar;
    private SlidingTabLayout slidingTabLayout;
    private ViewPager viewPager;

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

        findd();
        listenerr();
        configAdapter();

    }

    private void configAdapter() {
        TabAdapter tabAdapter = new TabAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tabAdapter);

        slidingTabLayout.setViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //exibir menu
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_sair) {
            deslogarUsuario();
            return true;
        } else if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_adicionar) {
            abrirCadastroContato();
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

    private void abrirCadastroContato() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle("Novo Contato");
        alertDialog.setMessage("E-mail do usuário");
        alertDialog.setCancelable(false);

        final EditText editText = new EditText(MainActivity.this);
        alertDialog.setView(editText);

        alertDialog.setPositiveButton("Cadastrar", (dialog, which) -> {
            String emailContato = editText.getText().toString();
            // TODO: Adicionar lógica de cadastro
        });

        alertDialog.setNegativeButton("Cancelar", (dialog, which) -> {
            // Cancelado
        });

        alertDialog.show();
    }

    private void deslogarUsuario(){
        firebaseAuth1 = ConfiguracaoFirebase.getFirebaseAuth();
        firebaseAuth1.signOut();

        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void listenerr() {

    }

    private void findd() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("EaiApp");
        setSupportActionBar(toolbar);

        slidingTabLayout = findViewById(R.id.sliding_tabs);
        viewPager = findViewById(R.id.viewpager);

        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setSelectedIndicatorColors(ContextCompat.getColor(this, R.color.colorAccent));
    }
}