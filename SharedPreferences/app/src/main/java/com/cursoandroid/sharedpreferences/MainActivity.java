package com.cursoandroid.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button button;
    EditText editText;

    private static final String ARQUIVO_PREFERENCIA="ArquivoPreferencia"; //static o valor é o mesmo em todas as instancias e final é constante

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findA();

        button.setOnClickListener(view -> {
            SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0); //0=privado- so esse app tem acesso ao arquivo
            SharedPreferences.Editor editor = preferences.edit();

            if(editText.getText().toString().equals("")){
                Toast.makeText(getApplicationContext(), "Preenche o nome", Toast.LENGTH_SHORT).show();
            }else{
                String nome = editText.getText().toString();
                editor.putString("nome", nome);
                editor.commit(); //salva

                textView.setText(" "+ nome);
            }
        });

        SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);
        if(preferences.contains("nome")){
            String nome = preferences.getString("nome", "nao foi");
            textView.setText(" "+ nome);
        }else{
            textView.setText("usuario nao definido");
        }
    }

    private void findA(){
        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editTextText);
        button = findViewById(R.id.button);
    }
}