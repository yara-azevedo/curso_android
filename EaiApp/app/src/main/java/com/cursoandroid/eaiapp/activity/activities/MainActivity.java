package com.cursoandroid.eaiapp.activity.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;

import com.cursoandroid.eaiapp.R;
import com.cursoandroid.eaiapp.activity.adapter.TabAdapter;
import com.cursoandroid.eaiapp.activity.config.ConfiguracaoFirebase;
import com.cursoandroid.eaiapp.activity.model.Contato;
import com.cursoandroid.eaiapp.activity.model.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import helper.Base64Custom;
import helper.Preferencias;
import helper.SlidingTabLayout;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth1 ;
    private Toolbar toolbar;
    private SlidingTabLayout slidingTabLayout;
    private ViewPager viewPager;

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

        findd();
        listenerr();
        configAdapter();

    }

    private void configAdapter() {
        TabAdapter tabAdapter = new TabAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tabAdapter);

        slidingTabLayout.setViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //exibir menu
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_sair) {
            deslogarUsuario();
            return true;
        } else if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_adicionar) {
            abrirCadastroContato();
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

    private void abrirCadastroContato(){

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);

        //Configurações do Dialog
        alertDialog.setTitle("Novo contato");
        alertDialog.setMessage("E-mail do usuário");
        alertDialog.setCancelable(false);

        final EditText editText = new EditText(MainActivity.this);
        alertDialog.setView( editText );

        //Configura botões
        alertDialog.setPositiveButton("Cadastrar", (dialog, which) -> {

            String emailContato = editText.getText().toString().trim();

            //Valida se o e-mail foi digitado
            if( emailContato.isEmpty() ){
                Toast.makeText(MainActivity.this, "Preencha o e-mail", Toast.LENGTH_LONG).show();
            }else{

                //Verificar se o usuário já está cadastrado no nosso App
                String identificadorContatoLocal = Base64Custom.codificarBase64(emailContato);

                //Recuperar identificador usuario logado (base64)
                Preferencias preferencias = new Preferencias(MainActivity.this);
                String identificadorUsuarioLogado = preferencias.getIdentificador();

                if (identificadorUsuarioLogado == null) {
                    Toast.makeText(MainActivity.this, "Erro ao recuperar identificador do usuário logado", Toast.LENGTH_LONG).show();
                    return;
                }

                if (identificadorContatoLocal.equals(identificadorUsuarioLogado)) {
                    Toast.makeText(MainActivity.this, "Você não pode adicionar seu próprio e-mail", Toast.LENGTH_LONG).show();
                    return;
                }

                //Recuperar instância Firebase
                DatabaseReference firebase = ConfiguracaoFirebase.getFirebase();
                DatabaseReference usuariosRef = firebase.child("usuarios").child(identificadorContatoLocal);

                usuariosRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        if( dataSnapshot.getValue() != null ){

                            //Recuperar dados do contato a ser adicionado
                            Usuario usuarioContato = dataSnapshot.getValue( Usuario.class );

                            if (usuarioContato != null) {
                                DatabaseReference contatosRef = firebase.child("contatos")
                                        .child( identificadorUsuarioLogado )
                                        .child( identificadorContatoLocal );

                                Contato contato = new Contato();
                                contato.setIdentificadorUsuario( identificadorContatoLocal );
                                contato.setEmail( usuarioContato.getEmail() );
                                contato.setNome( usuarioContato.getNome() );

                                contatosRef.setValue( contato ).addOnCompleteListener(task -> {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(MainActivity.this, "Contato adicionado com sucesso!", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(MainActivity.this, "Erro ao salvar contato", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }

                        }else {
                            Toast.makeText(MainActivity.this, "Usuário não possui cadastro.", Toast.LENGTH_LONG)
                                    .show();
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(MainActivity.this, "Erro ao consultar usuário: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }

        });

        alertDialog.setNegativeButton("Cancelar", (dialog, which) -> {

        });

        alertDialog.create();
        alertDialog.show();

    }
    private void deslogarUsuario(){
        firebaseAuth1 = ConfiguracaoFirebase.getFirebaseAuth();
        firebaseAuth1.signOut();

        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void listenerr() {

    }

    private void findd() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("EaiApp");
        setSupportActionBar(toolbar);

        slidingTabLayout = findViewById(R.id.sliding_tabs);
        viewPager = findViewById(R.id.viewpager);

        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setSelectedIndicatorColors(ContextCompat.getColor(this, R.color.colorAccent));
    }
}
