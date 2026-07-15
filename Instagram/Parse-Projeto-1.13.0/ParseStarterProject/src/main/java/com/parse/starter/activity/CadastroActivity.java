package com.parse.starter.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseUser;
import com.parse.starter.R;
import com.parse.starter.util.ParseErros;

public class CadastroActivity extends AppCompatActivity {
    private EditText et_usuario, et_senha, et_email;
    private Button btn_cadastrar;
    private TextView txt_logar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cadastro);
        findd();
        clickk();
    }

    private void cadastrarUsuario(){
        String nomeUsuario = et_usuario.getText().toString();
        String email = et_email.getText().toString();
        String senha = et_senha.getText().toString();

        // Faz logOut para limpar qualquer sessão inválida anterior (corrige o erro 209: invalid session code)
        ParseUser.logOut();

        ParseUser usuario = new ParseUser();
        usuario.setUsername(nomeUsuario);
        usuario.setEmail(email);
        usuario.setPassword(senha);

        usuario.signUpInBackground(e -> {
            if(e == null){
                Toast.makeText(CadastroActivity.this, "Usuário cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                abrirLoginUsuario();
                finish();
            } else {
                ParseErros erros = new ParseErros();
                String erro = erros.getErro(e.getCode());
                Toast.makeText(CadastroActivity.this, erro, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void abrirLoginUsuario(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void clickk(){
        txt_logar.setOnClickListener(v -> {
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
        txt_logar = findViewById(R.id.txt_cadastrar);
    }
}
