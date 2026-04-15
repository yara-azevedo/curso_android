package com.cursoandroid.eaiapp.activity.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.cursoandroid.eaiapp.R;
import com.cursoandroid.eaiapp.activity.config.ConfiguracaoFirebase;
import com.cursoandroid.eaiapp.activity.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

import helper.Base64Custom;

public class CadastroActivity extends AppCompatActivity {
    private EditText nome, email, senha;
    private Button cadastrar;
    private TextView logar;
    private Usuario usuario;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cadastro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        findd();
        listenerr();
    }

    private void findd() {
        nome = findViewById(R.id.et_nome);
        email = findViewById(R.id.et_email);
        senha = findViewById(R.id.et_senha);
        cadastrar = findViewById(R.id.btn_cadastrar);
        logar = findViewById(R.id.btn_logar);


    }

    void listenerr() {
        cadastrar.setOnClickListener(view -> {
            usuario = new Usuario();
            usuario.setNome(nome.getText().toString());
            usuario.setEmail(email.getText().toString());
            usuario.setSenha(senha.getText().toString());
            cadastrarUsuario();
        });

    }

    private void cadastrarUsuario() {
        firebaseAuth = ConfiguracaoFirebase.getFirebaseAuth();
        firebaseAuth.createUserWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()
        ).addOnCompleteListener(CadastroActivity.this, (OnCompleteListener<AuthResult>) task -> {
            if (task.isSuccessful()) {
                Toast.makeText(CadastroActivity.this, "Sucesso ao cadastrar usuário", Toast.LENGTH_SHORT).show();
                String idUsuario = Base64Custom.codificarBase64(usuario.getEmail());
                usuario.setId(idUsuario);
                usuario.salvar();

                abrirLoginUsuario();


            } else {

                String erroExcecao = "";
                try {
                    throw task.getException();
                } catch (FirebaseAuthWeakPasswordException e) {
                    erroExcecao = "Digite uma senha mais forte";
                } catch (FirebaseAuthInvalidCredentialsException e) {
                    erroExcecao = "Email inválido";
                } catch (FirebaseAuthUserCollisionException e) {
                    erroExcecao = "Já existe uma conta";
                } catch (Exception e) {
                    erroExcecao = "Erro ao cadastrar usuário " + e.getMessage();
                }
                Toast.makeText(CadastroActivity.this, "Erro: " + erroExcecao, Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void abrirLoginUsuario() {
        Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();

    }
}