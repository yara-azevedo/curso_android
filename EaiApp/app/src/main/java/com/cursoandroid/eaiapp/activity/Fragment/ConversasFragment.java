package com.cursoandroid.eaiapp.activity.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cursoandroid.eaiapp.R;
import com.cursoandroid.eaiapp.activity.activities.ConversaActivity;
import com.cursoandroid.eaiapp.activity.adapter.ContatoAdapter;
import com.cursoandroid.eaiapp.activity.adapter.ConversaAdapter;
import com.cursoandroid.eaiapp.activity.config.ConfiguracaoFirebase;
import com.cursoandroid.eaiapp.activity.model.Contato;
import com.cursoandroid.eaiapp.activity.model.Conversa;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import helper.Base64Custom;
import helper.Preferencias;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConversasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConversasFragment extends Fragment {
    private ListView listView;
    private ArrayAdapter adapter;
    private ArrayList<Conversa> conversas;
    private DatabaseReference databaseReference;
    private ValueEventListener valueEventListenerConversa;

    public ConversasFragment() {
    }

    @Override
    public void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(valueEventListenerConversa);
    }

    @Override
    public void onStop() {
        super.onStop();
        databaseReference.removeEventListener(valueEventListenerConversa);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        conversas = new ArrayList<>();


        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_conversas, container, false);

        listView = view.findViewById(R.id.list_conversas);
        //adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1,contatos);
        adapter = new ConversaAdapter(getActivity(), conversas);
        listView.setAdapter(adapter);

        Preferencias preferencias= new Preferencias(getActivity());
        String identificadorUsuarioLogado = preferencias.getIdentificador();
        databaseReference = ConfiguracaoFirebase.getFirebase().child("conversas").child(identificadorUsuarioLogado);

        valueEventListenerConversa = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //limpar lista
                conversas.clear();
                //listar contatos
                for(DataSnapshot dados: snapshot.getChildren()){
                    Conversa conversa = dados.getValue(Conversa.class);
                    conversas.add(conversa);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        listView.setOnItemClickListener((adapterView, view1, i, l) -> {
            Intent intent = new Intent(getActivity(), ConversaActivity.class);
            //recuperando os dados
            Conversa conversa = conversas.get(i);

            //enviando dados entre activities
            intent.putExtra("nome", conversa.getNome());
            String email = Base64Custom.decodificarBase64(conversa.getIdUsuario());
            intent.putExtra("email", email);

            startActivity(intent);

        });
        return view;
    }
}