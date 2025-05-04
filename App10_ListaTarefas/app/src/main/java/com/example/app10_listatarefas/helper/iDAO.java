package com.example.app10_listatarefas.helper;

import com.example.app10_listatarefas.model.Tarefa;

import java.util.List;

public interface iDAO {

    public boolean salvar(Tarefa tarefa);
    public boolean atualizar(Tarefa tarefa);
    public boolean deletar(Tarefa tarefa);
    public List<Tarefa> listar();

}
