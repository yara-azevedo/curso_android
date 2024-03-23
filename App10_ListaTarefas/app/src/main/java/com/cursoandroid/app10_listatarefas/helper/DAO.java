package com.cursoandroid.app10_listatarefas.helper;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.cursoandroid.app10_listatarefas.model.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class DAO implements IDAO{

    private SQLiteDatabase escreve;
    private SQLiteDatabase le;

    public DAO(Context context) {
        DBHelper helper = new DBHelper(context);
        escreve = helper.getWritableDatabase();
        le = helper.getReadableDatabase();
    }

    @Override
    public boolean salvar(Tarefa tarefa) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", tarefa.getNome());

        try{
            escreve.insert(DBHelper.TB_NOME, null, contentValues);
            Log.i("INFO DB", "fooooi");
        }catch (Exception e){
            Log.i("INFO DB", "errooo" +e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean atualizar(Tarefa tarefa) {
        return false;
    }

    @Override
    public boolean deletar(Tarefa tarefa) {
        return false;
    }

    @Override
    public List<Tarefa> listar() {
        List<Tarefa> tarefas = new ArrayList<>();
        String sql = "SELECT * FROM " + DBHelper.TB_NOME + " ;";
        Cursor cursor  = le.rawQuery(sql, null);

        Long id = cursor.getLong(cursor.getColumnIndex("id"));
        String nomet = cursor.getString(cursor.getColumnIndex("nome"));

        while(cursor.moveToNext()){
            Tarefa tarefa = new Tarefa();

            tarefa.setId(id);
            tarefa.setNome(nomet);

            tarefas.add(tarefa);
        }
        return tarefas;
    }
}
