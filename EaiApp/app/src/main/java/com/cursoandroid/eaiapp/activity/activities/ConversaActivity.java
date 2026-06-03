package com.cursoandroid.eaiapp.activity.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.cursoandroid.eaiapp.R;
import com.cursoandroid.eaiapp.activity.adapter.MensagemAdapter;
import com.cursoandroid.eaiapp.activity.config.ConfiguracaoFirebase;
import com.cursoandroid.eaiapp.activity.model.Conversa;
import com.cursoandroid.eaiapp.activity.model.Mensagem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import helper.Base64Custom;
import helper.Preferencias;

public class ConversaActivity extends AppCompatActivity {

    private Toolbar toolbar;
    //dados destinatario
    private String nomeUsuarioDestinatario;
    private Button btnEnviar;
    private EditText etMensagem;
    private String idUsuarioDestinatario;

    //remetente
    private String idUsuarioRemetente;
    private String nomeUsuarioRemetente;

    DatabaseReference databaseReference;
    private ListView listView;
    private ArrayList<Mensagem> mensagens;
    private ArrayAdapter<Mensagem> adapter;
    private ValueEventListener valueEventListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_conversa);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        findd();
        listenner();

        Preferencias preferencias = new Preferencias(ConversaActivity.this);
        idUsuarioRemetente = preferencias.getIdentificador();
        nomeUsuarioRemetente = preferencias.getNome();

        // Recuperar dados do destinatário
        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            nomeUsuarioDestinatario = extra.getString("nome");
            String emailDestinatario = extra.getString("email");
            idUsuarioDestinatario = Base64Custom.codificarBase64(emailDestinatario);
        }

        // Configurar toolbar
        toolbar.setTitle(nomeUsuarioDestinatario);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mensagens = new ArrayList<>();
        adapter = new MensagemAdapter(ConversaActivity.this, mensagens);
        listView.setAdapter(adapter);

        //recuperando do firebase
        databaseReference = ConfiguracaoFirebase.getFirebase().child("mensagens").child(idUsuarioRemetente).child(idUsuarioDestinatario);
        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mensagens.clear();
                for (DataSnapshot dados : snapshot.getChildren()) {
                    Mensagem mensagem = dados.getValue(Mensagem.class);
                    mensagens.add(mensagem);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        databaseReference.addValueEventListener(valueEventListener);


    }

    private boolean salvarMensagem(String idRemetente, String idDestinatario, Mensagem mensagem) {
        try {
            databaseReference = ConfiguracaoFirebase.getFirebase().child("mensagens");

            databaseReference.child(idRemetente)
                    .child(idDestinatario)
                    .push()
                    .setValue(mensagem);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean salvarConversa(String idRemetente, String idDestinatario, Conversa conversa){
        try {
            databaseReference = ConfiguracaoFirebase.getFirebase().child("conversas");
            databaseReference.child(idRemetente).child(idDestinatario).setValue(conversa);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (databaseReference != null && valueEventListener != null) {
            databaseReference.removeEventListener(valueEventListener);
        }
    }

    void listenner(){
        btnEnviar.setOnClickListener(view -> {
            String textoMensagem = etMensagem.getText().toString();
            if (textoMensagem.isEmpty()) {
                Toast.makeText(ConversaActivity.this, "Digite uma mensagem", Toast.LENGTH_SHORT).show();
            } else {
                Mensagem mensagem = new Mensagem();
                mensagem.setIdUsuario(idUsuarioRemetente);
                mensagem.setMensagem(textoMensagem);
                //salvando pro remetente e destinatario

                Boolean retornoMensagemRemetente = salvarMensagem(idUsuarioRemetente, idUsuarioDestinatario, mensagem);
                if (!retornoMensagemRemetente){
                    Toast.makeText(ConversaActivity.this, "Erro ao salvar mensagem", Toast.LENGTH_SHORT).show();
                }else{
                    Boolean retornoMensagemDestinatario = salvarMensagem(idUsuarioDestinatario, idUsuarioRemetente, mensagem);
                    if (!retornoMensagemDestinatario){
                        Toast.makeText(
                                ConversaActivity.this,
                                "Problema ao enviar mensagem para o destinatário, tente novamente!",
                                Toast.LENGTH_LONG
                        ).show();
                    }

                }

                // salvamos Conversa para o remetente
                Conversa conversa = new Conversa();
                conversa.setIdUsuario( idUsuarioDestinatario );
                conversa.setNome( nomeUsuarioDestinatario );
                conversa.setMensagem( textoMensagem );
                Boolean retornoConversaRemetente = salvarConversa(idUsuarioRemetente, idUsuarioDestinatario, conversa);
                if( !retornoConversaRemetente ){
                    Toast.makeText(
                            ConversaActivity.this,
                            "Problema ao salvar conversa, tente novamente!",
                            Toast.LENGTH_LONG
                    ).show();
                }else {
                    // salvamos Conversa para o Destinatario

                    conversa = new Conversa();
                    conversa.setIdUsuario( idUsuarioRemetente );
                    conversa.setNome( nomeUsuarioRemetente );
                    conversa.setMensagem(textoMensagem);

                    Boolean retornoConversaDestinatario = salvarConversa(idUsuarioDestinatario, idUsuarioRemetente, conversa );
                    if( !retornoConversaDestinatario ){
                        Toast.makeText(
                                ConversaActivity.this,
                                "Problema ao salvar conversa para o destinatário, tente novamente!",
                                Toast.LENGTH_LONG
                        ).show();
                    }

                }
                etMensagem.setText("");
            }
        });
    }

    void findd(){
        btnEnviar = findViewById(R.id.btn_send);
        etMensagem = findViewById(R.id.et_mensagem);
        listView = findViewById(R.id.lv_conversas);
        toolbar = findViewById(R.id.tb_conversa);


    }
}
