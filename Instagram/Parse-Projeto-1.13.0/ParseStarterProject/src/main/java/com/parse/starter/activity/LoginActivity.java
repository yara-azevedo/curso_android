package com.parse.starter.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.starter.R;

public class LoginActivity extends AppCompatActivity {
    private EditText et_usuario, et_senha;
    private Button btn_logar;
    private TextView txt_login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        findd();
        clickk();

    }

    private void clickk(){
        txt_login.setOnClickListener(v -> {
            Intent intent = new Intent(this, CadastroActivity.class);
            startActivity(intent);
        });
        btn_logar.setOnClickListener(v -> {

        });
    }
    private void findd(){
        et_usuario = findViewById(R.id.et_usuario);
        et_senha = findViewById(R.id.et_senha);
        btn_logar = findViewById(R.id.btn_logar);
        txt_login = findViewById(R.id.txt_cadastrar);

    }
}