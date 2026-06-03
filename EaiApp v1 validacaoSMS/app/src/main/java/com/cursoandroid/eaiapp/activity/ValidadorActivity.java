package com.cursoandroid.eaiapp.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.cursoandroid.eaiapp.R;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.util.HashMap;

import helper.Preferencias;

public class ValidadorActivity extends AppCompatActivity {

    private EditText et_token;
    private Button btn_validar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_validador);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        findd();
        listenerr();

    }

    private void findd(){
        et_token = findViewById(R.id.et_token);
        btn_validar = findViewById(R.id.btn_validar);
        maskk();
    }

    private void maskk(){
        SimpleMaskFormatter simpleMaskFormatter = new SimpleMaskFormatter("NNNN");
        MaskTextWatcher maskTextWatcher = new MaskTextWatcher(et_token, simpleMaskFormatter);
        et_token.addTextChangedListener(maskTextWatcher);
    }

    private void listenerr() {
        btn_validar.setOnClickListener(view -> {
            Preferencias preferencias = new Preferencias(ValidadorActivity.this);
            HashMap<String, String> usuario = preferencias.getDadosUsuario();

            String tokenGerado = usuario.get("token");
            String tokenDigitado = et_token.getText().toString();

            if(tokenDigitado.equals(tokenGerado)){
                Toast.makeText(this, "token validado", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "token não validado", Toast.LENGTH_SHORT).show();
            }


        });
    }
}