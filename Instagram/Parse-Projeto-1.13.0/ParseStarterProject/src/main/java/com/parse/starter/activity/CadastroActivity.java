package com.parse.starter.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.parse.starter.R;

public class CadastroActivity extends AppCompatActivity {
    private EditText et_usuario, et_senha, et_email;
    private Button btn_cadastrar;
    private TextView txt_cadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cadastro);
        findd();
        clickk();
    }

    private void cadastrarUsuario(){
        ParseUser usuario = new ParseUser();
        //objeto usuario create
        usuario.setUsername(et_usuario.getText().toString());
        usuario.setEmail(et_email.getText().toString());
        usuario.setPassword(et_senha.getText().toString());

        //salvar dados

    }

    private void clickk(){
        txt_cadastrar.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });
        btn_cadastrar.setOnClickListener(v -> {
            cadastrarUsuario();
        });
    }
    private void findd(){
        et_usuario = findViewById(R.id.et_usuario);
        et_email = findViewById(R.id.et_email);
        et_senha = findViewById(R.id.et_senha);
        btn_cadastrar = findViewById(R.id.btn_logar);
        txt_cadastrar = findViewById(R.id.txt_cadastrar);

    }
}