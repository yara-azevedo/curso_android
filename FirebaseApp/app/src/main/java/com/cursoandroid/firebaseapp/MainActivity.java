package com.cursoandroid.firebaseapp;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

       // DatabaseReference databasepesquisa = databaseReference.child("-Ooqu4R7cgRvQ1bl_ew4");//recuperar registro especifico
        //Query databasepesquisa = databaseReference.child("usuarios").orderByChild("nome").equalTo("luke"); //querys - precisa ordernar (orderbyuchild)pra filtrar depois
        //Query databasepesquisa = databaseReference.orderByKey().limitToFirst(2); //limtia a pesquisa pelos dois primeiros/ultimos (limittolast)
        //Query databasepesquisa = databaseReference.orderByKey().limitToLast(2);

        //Query databasepesquisa = databaseReference.orderByChild("idade").startAt(12); //>= usuarios que tem idade maior ou igual a 12
        //Query databasepesquisa = databaseReference.orderByChild("idade").endAt(12); //<= usuarios que tem idade menor ou igual a 12
        //Query databasepesquisa = databaseReference.orderByChild("idade").startAt(1).endAt(12); //entre
        Query databasepesquisa = databaseReference.orderByChild("nome").startAt("J").endAt("J"+"\uf8ff"); //começa com J e termina com J



        databasepesquisa.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Usuario dadosusuario = snapshot.getValue(Usuario.class);
                Log.i("dados usuario: ", snapshot.getValue().toString());
                //Log.i("dados usuario: ", " nome: " + dadosusuario.getNome() + " sobrenome: " + dadosusuario.getSobrenome());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        /*gerar ID automatico: push
        DatabaseReference usuarios = databaseReference.child("usuarios");
        Usuario usuario = new Usuario();
        usuario.setNome("556756");
        usuario.setSobrenome("azevedo");
        usuario.setIdade(5);
        usuarios.push().setValue(usuario);*/ //gera id unico

        //logar usuario
        firebaseAuth.signInWithEmailAndPassword("yara.zs@hotmail.com", "123456").addOnCompleteListener(MainActivity.this, task -> {
            if(task.isSuccessful()){
                Log.i("FIREBASE", "logado com sucesso");
            }else{
                Log.i("FIREBASE", "login nao realizado");
            }
        });

        //deslogar usuario
        firebaseAuth.signOut();

        //verifica se o usuario ta logado
        if(firebaseAuth.getCurrentUser() != null){
            Log.i("FIREBASE", "logado");
        }else{
            Log.i("FIREBASE", "cadastro nao realizado");
        }

        //cadastro usuario
        firebaseAuth.createUserWithEmailAndPassword("yara.zs@hotmail.com", "123456").addOnCompleteListener(MainActivity.this, task -> {
            if(task.isSuccessful()){
                Log.i("FIREBASE", "cadastro realizado com sucesso");
            }else{
                Log.i("FIREBASE", "cadastro nao realizado");
            }
        });

                //criar atualizar registros
        //opcao 1
        databaseReference.child("teste").setValue("goo");

        //opcao 2
        databaseReference.child("usuarios02").child("001").child("nome").setValue("luke tiffany");
        //opcao 3

        //DatabaseReference usuarios = databaseReference.child("usuarios");
        usuarios.child("001").child("nome").setValue("luke");
        usuarios.child("002").child("nome").setValue("tiffany");

        /*opcao 4: usando classe
        Usuario usuario = new Usuario();
        usuario.setNome("luke");
        usuario.setSobrenome("azevedo");
        usuario.setIdade(9);
        usuarios.child("001").setValue(usuario);*/

        usuario.setNome("tiffany");
        usuario.setSobrenome("azevedo");
        usuario.setIdade(12);
        usuarios.child("002").setValue(usuario);


        //recuperar registros
        //DatabaseReference usuarios = databaseReference.child("usuarios"); // listener p todos os registros
        DatabaseReference usuarioss = databaseReference.child("usuarios").child("001"); //listener so os registros descritos

        usuarios.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //recuperar os dados
                Log.i("FIREBASE", snapshot.getValue().toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //requisicao cancelada
            }
        });

    }
}