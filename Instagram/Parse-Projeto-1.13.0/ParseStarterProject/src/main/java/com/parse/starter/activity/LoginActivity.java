package com.parse.starter.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseUser;
import com.parse.starter.R;

public class LoginActivity extends AppCompatActivity {
    private EditText et_usuario, et_senha;
    private Button btn_logar;
    private TextView txt_cadastrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        findd();
        clickk();
        //ParseUser.logOut();;
        verificarUsuarioLogado();

    }

    private void verificarUsuarioLogado(){
        if(ParseUser.getCurrentUser() != null){
            abrirTelaPrincipal();
            finish();
        }else{

        }
    }

    private void abrirTelaPrincipal() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }



    private void clickk(){
        txt_cadastrar.setOnClickListener(v -> {
            Intent intent = new Intent(this, CadastroActivity.class);
            startActivity(intent);
        });
        btn_logar.setOnClickListener(v -> {
            String usuario = et_usuario.getText().toString();
            String senha = et_senha.getText().toString();
            verificaLogin(usuario, senha);
        });
    }

        private void verificaLogin(String usuario, String senha) {
            ParseUser.logInInBackground( usuario,  senha, new LogInCallback() {
                @Override
                public void done(ParseUser user, com.parse.ParseException e) {
                    if(user != null){
                        Toast.makeText(LoginActivity.this, "Logado com sucesso", Toast.LENGTH_SHORT).show();
                        abrirTelaPrincipal();
                        finish();
                    }else{
                        Toast.makeText(LoginActivity.this, "Erro ao logar", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }





    private void findd(){
        et_usuario = findViewById(R.id.et_usuario);
        et_senha = findViewById(R.id.et_senha);
        btn_logar = findViewById(R.id.btn_logar);
        txt_cadastrar = findViewById(R.id.txt_cadastrar);


    }
}