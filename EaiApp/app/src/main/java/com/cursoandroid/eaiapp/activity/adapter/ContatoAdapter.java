package com.cursoandroid.eaiapp.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.cursoandroid.eaiapp.R;
import com.cursoandroid.eaiapp.activity.model.Contato;

import java.util.ArrayList;
import java.util.List;

public class ContatoAdapter extends ArrayAdapter<Contato> {
    private ArrayList<Contato> contatos;
    private Context context;


    public ContatoAdapter(@NonNull Context c, @NonNull ArrayList<Contato> objects) {
        super(c, 0, objects);
        this.contatos = objects;
        this.context = c;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if (contatos != null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.lista_contato, parent, false);
            TextView tvNome = view.findViewById(R.id.tv_nome);
            TextView tvEmail = view.findViewById(R.id.tv_email);
            Contato contato = contatos.get(position);
            tvNome.setText(contato.getNome());
            tvEmail.setText(contato.getEmail());
        }
        return view;
    }
}
