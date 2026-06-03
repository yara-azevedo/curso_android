package com.cursoandroid.eaiapp.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cursoandroid.eaiapp.R;
import com.cursoandroid.eaiapp.activity.model.Mensagem;

import java.util.ArrayList;
import java.util.List;

import helper.Preferencias;

public class MensagemAdapter extends ArrayAdapter<Mensagem> {

    private Context context;
    private ArrayList<Mensagem> mensagens;
    public MensagemAdapter(@NonNull Context c,  @NonNull ArrayList<Mensagem> objects) {
        super(c, 0, objects);
        this.context = c;
        this.mensagens = objects;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = null;

        if(mensagens != null){
            Preferencias preferencias= new Preferencias(context);
            String idUsuarioRemetente = preferencias.getIdentificador();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            Mensagem mensagem = mensagens.get(position);
            if(idUsuarioRemetente.equals(mensagem.getIdUsuario())){
                view = inflater.inflate(R.layout.mensagem_remetente, parent, false);
            }else{
                view = inflater.inflate(R.layout.mensagem_destinatario, parent, false);
            }

            TextView textoMensagem = view.findViewById(R.id.tv_mensagem);
            textoMensagem.setText(mensagem.getMensagem());
        }
        return view;
    }
}
