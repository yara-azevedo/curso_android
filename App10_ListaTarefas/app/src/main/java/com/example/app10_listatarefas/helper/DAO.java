package com.example.app10_listatarefas.helper;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.app10_listatarefas.model.Tarefa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DAO implements iDAO{
    private SQLiteDatabase escreve;
    private SQLiteDatabase le;

    public DAO(Context context) {
        DBHelper db = new DBHelper(context);
        escreve = db.getWritableDatabase();
        le = db.getReadableDatabase();

    }

    @Override
    public boolean salvar(Tarefa tarefa) {
        ContentValues cv = new ContentValues();
        cv.put("nome",tarefa.getNome());

        try{
            escreve.insert(DBHelper.TABELA,null, cv);
            Log.i("INFO", "fooi" );
        }catch (Exception e){
            Log.e("INFO", "erro salvar a tarefa" + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean atualizar(Tarefa tarefa) {
        ContentValues cv = new ContentValues();
        cv.put("nome",tarefa.getNome());
        try{
            String[] args ={tarefa.getId().toString()};
            escreve.update(DBHelper.TABELA,cv, "id=?", args);
            Log.i("INFO", "atualizada" );
        }catch (Exception e){
            Log.e("INFO", "erro atualizar a tarefa" + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean deletar(Tarefa tarefa) {

        try{
            String[] args ={tarefa.getId().toString()};
            escreve.delete(DBHelper.TABELA,"id=?", args);
            Log.i("INFO", "removida" );
        }catch (Exception e){
            Log.e("INFO", "erro remover a tarefa" + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public List<Tarefa> listar() {

        List<Tarefa> tarefas = new ArrayList<>();

        String sql = "SELECT * FROM " + DBHelper.TABELA + " ;";
        Cursor c = le.rawQuery(sql, null);

        int indexId = c.getColumnIndex("id");
        int indexNome = c.getColumnIndex("nome");

        while (c.moveToNext()) {
            if (indexId != -1 && indexNome != -1) {
                Long id = c.getLong(indexId);
                String nomeTarefa = c.getString(indexNome);

                Tarefa tarefa = new Tarefa();
                tarefa.setId(id);
                tarefa.setNome(nomeTarefa);
                tarefas.add(tarefa);

                Log.i("tarefaDao", tarefa.getNome());
            }
        }


        return tarefas;
    }
}
