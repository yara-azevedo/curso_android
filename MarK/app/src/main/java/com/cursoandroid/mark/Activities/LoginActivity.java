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

public class LoginActivity extends AppCompatActivity {
    private EditText et_email, et_senha;
    private Button button;
    private ProgressBar progressBar;
    private TextView txt_cadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        finAll();
        nextScreen();

    }


    private void login() {
        String email  = et_email.getText().toString();
        String senha  = et_senha.getText().toString();


        boolean isValidated = validateData(email,senha);
        if(!isValidated){
            return;
        }

        loginAccountFirebase(email,senha);
    }

    private void loginAccountFirebase(String email, String senha) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        changeInProgress(true);
        firebaseAuth.signInWithEmailAndPassword(email,senha).addOnCompleteListener(task -> {
            changeInProgress(false);
            if(task.isSuccessful()){
                //login is success
                if(firebaseAuth.getCurrentUser().isEmailVerified()){
                    //go to mainactivity
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this, "email n verificado", Toast.LENGTH_SHORT).show();
                }

            }else{
                //login failed
                Toast.makeText(LoginActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    void changeInProgress(boolean inProgress){
        if(inProgress){
            progressBar.setVisibility(View.VISIBLE);
            button.setVisibility(View.GONE);
        }else{
            progressBar.setVisibility(View.GONE);
            button.setVisibility(View.VISIBLE);
        }
    }

    boolean validateData(String email,String password){
        //validate the data that are input by user.

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            et_email.setError("E-mail inválido :(");
            return false;
        }
        if(password.length()<5){
            et_senha.setError("Senha curta demais :/");
            return false;
        }
        return true;
    }

    private void nextScreen() {
        button.setOnClickListener(v -> login());

        /*txt_cadastro.setOnClickListener(v -> {startActivity(new Intent(LoginActivity.this, CadastroActivity.class));
            finish();
        });*/
    }


    private void finAll(){
        et_email = findViewById(R.id.et_nome);
        et_senha = findViewById(R.id.et_senha);
        button = findViewById(R.id.login_btn);
        progressBar = findViewById(R.id.progress_bar);
        txt_cadastro = findViewById(R.id.txt_login);
    }
}
