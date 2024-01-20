package com.cursoandroid.app9_minhasanotaes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    FloatingActionButton fob;

    Preferencias preferencias;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferencias = new Preferencias(getApplicationContext());
        findA();

        fob.setOnClickListener(view -> {
            String recuperado = editText.getText().toString();
            if(recuperado.equals("")){
                Snackbar.make(view, "nota vazia", Snackbar.LENGTH_LONG).show();
            }else{
                preferencias.salvar(recuperado);
                Snackbar.make(view, "nota salva", Snackbar.LENGTH_LONG).show();
            }
        });

        String anotacao = preferencias.recuperar();
        if(!anotacao.equals("")){
            editText.setText(anotacao);
        }

    }

    private void findA() {
        editText = findViewById(R.id.editTextTextMultiLine);
        fob = findViewById(R.id.floatingActionButton);
    }
}