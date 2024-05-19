package com.cursoandroid.mark.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cursoandroid.mark.R;
import com.google.firebase.auth.FirebaseAuth;

public class CadastroActivity extends AppCompatActivity {

    private EditText et_email,et_senha,et_nome;
    private Button button;
    private ProgressBar progressBar;
    private TextView txt_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        finAll();
        nextScreen();


    }

    private void createAccount() {
        String email = et_email.getText().toString();
        String senha = et_senha.getText().toString();

        boolean isValidated = validateData(email, senha);
        if(!isValidated){
            return;
        }
        createAccountFirebase(email, senha);
    }

    private void createAccountFirebase(String email, String senha) {
        changeInProgress(true);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(CadastroActivity.this,
                task -> {
            changeInProgress(false);
            if (task.isSuccessful()){
                Toast.makeText(this, "Naissu xD", Toast.LENGTH_SHORT).show();
                firebaseAuth.getCurrentUser().sendEmailVerification();
                firebaseAuth.signOut();
                finish();
            }else{
                Toast.makeText(this, "Num deu :/", Toast.LENGTH_SHORT).show();
            }
                });
    }

    private void changeInProgress(boolean bool){
        if(bool){
            progressBar.setVisibility(View.VISIBLE);
            button.setVisibility(View.GONE);
        }else{
            progressBar.setVisibility(View.GONE);
            button.setVisibility(View.VISIBLE);
        }
    }

    private boolean validateData(String email, String senha){
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(this, "E-mail inválido :|", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (senha.length()<5){
            Toast.makeText(this, "Senha muito curta :|", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void nextScreen() {
        button.setOnClickListener(v -> createAccount());

        txt_login.setOnClickListener(v -> {startActivity(new Intent(CadastroActivity.this, LoginActivity.class));
                                        finish();
        });
    }

    private void finAll(){
        et_email = findViewById(R.id.et_nome);
        et_senha = findViewById(R.id.et_senha);
        et_nome = findViewById(R.id.et_nome);
        button = findViewById(R.id.btn);
        progressBar = findViewById(R.id.progress_bar);
        txt_login = findViewById(R.id.txt_login);
    }
}
