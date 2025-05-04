package com.cursoandroid.mark.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cursoandroid.mark.Content.Conteudo;
import com.cursoandroid.mark.R;

import java.util.List;

public class ConteudoAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private Context context;
    private List<Conteudo> conteudoList;

    public void setConteudoList(List<Conteudo> conteudoSearchList){
        this.conteudoList = conteudoSearchList;
        notifyDataSetChanged();
    }

    public MyAdapter(Context context, List<Conteudo> conteudoList){
        this.context = context;
        this.conteudoList = conteudoList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

class MyViewHolder extends RecyclerView.ViewHolder{
    String titulo, genero, tipo;
    int ano, temporada, id;
    Double nota;

    @SuppressLint("WrongViewCast")
    public  MyViewHolder(@NonNull View itemView){
        super(itemView);

        titulo = String.valueOf(itemView.findViewById(R.id.et_titulo));
        genero = String.valueOf(itemView.findViewById(R.id.et_genero));
        tipo = String.valueOf(itemView.findViewById(R.id.et_tipo));
        ano = Integer.parseInt(String.valueOf(itemView.findViewById(R.id.et_ano)));
        temporada = Integer.parseInt(String.valueOf(itemView.findViewById(R.id.et_temporada)));



    }
}
