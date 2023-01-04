package com.cursoandroid.recyclerview.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cursoandroid.recyclerview.R;
import com.cursoandroid.recyclerview.model.Filme;

import java.util.List;

public class Adapter  extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private List<Filme> listafilmes;
    public Adapter(List<Filme> filmes) {
        this.listafilmes = filmes;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { //cria a visualização
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista, parent, false);
        return new MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) { //exibe os itens
        Filme filme = listafilmes.get(position);
        holder.titulo.setText(filme.getTitulo());
        holder.ano.setText(filme.getAno());
        holder.subtitulo.setText(filme.getSubtitulo());
    }

    @Override
    public int getItemCount() { //qtde de itens por vez
        return listafilmes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView titulo, subtitulo, ano;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo=itemView.findViewById(R.id.textView);
            subtitulo=itemView.findViewById(R.id.textView3);
            ano=itemView.findViewById(R.id.textView2);
        }
    }
}
