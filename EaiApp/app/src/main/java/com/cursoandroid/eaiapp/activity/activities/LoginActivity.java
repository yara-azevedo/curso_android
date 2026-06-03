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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import helper.Base64Custom;
import helper.Preferencias;

public class LoginActivity extends AppCompatActivity {

    private EditText email, senha;
    private Button logar;
    private TextView cadastrar;
    private Usuario usuario;
    private FirebaseAuth firebaseAuth;
    private ValueEventListener valueEventListener;
    private DatabaseReference databaseReference;
    String identificadorusuariologado;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
    });
        findd();
        listenerr();
        verificarUsuariologado();
    }




    private void findd() {
        email = findViewById(R.id.et_email);
        senha = findViewById(R.id.et_senha);
        logar = findViewById(R.id.btn_logar);
        cadastrar = findViewById(R.id.txt_logar);


    }

    void listenerr() {

        cadastrar.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
            startActivity(intent);
        });

        logar.setOnClickListener(view -> {
            usuario = new Usuario();
            usuario.setEmail(email.getText().toString().trim());
            usuario.setSenha(senha.getText().toString());

            validarLogin();
        });

    }

    private void verificarUsuariologado(){
        firebaseAuth = ConfiguracaoFirebase.getFirebaseAuth();
        if(firebaseAuth.getCurrentUser() !=null){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void validarLogin(){
        firebaseAuth = ConfiguracaoFirebase.getFirebaseAuth();
        firebaseAuth.signInWithEmailAndPassword(usuario.getEmail(), usuario.getSenha()).addOnCompleteListener(task -> {
            if(task.isSuccessful()){


                identificadorusuariologado = Base64Custom.codificarBase64(usuario.getEmail());

                databaseReference= ConfiguracaoFirebase.getFirebase().child("usuarios").child(identificadorusuariologado);
                valueEventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Usuario usuariorecuperado = snapshot.getValue(Usuario.class);

                        Preferencias preferencias = new Preferencias(LoginActivity.this);
                        preferencias.salvarDados(identificadorusuariologado,usuariorecuperado.getNome());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                };
                databaseReference.addListenerForSingleValueEvent(valueEventListener);



                Toast.makeText(LoginActivity.this, "Sucesso ", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }else{
                Toast.makeText(LoginActivity.this, "erro ", Toast.LENGTH_SHORT).show();
            }
        });
    }
    }
