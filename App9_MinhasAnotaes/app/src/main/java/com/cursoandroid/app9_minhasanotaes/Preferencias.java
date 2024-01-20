package com.cursoandroid.app9_minhasanotaes;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferencias {

    private Context context;
    private SharedPreferences preferences;

    private SharedPreferences.Editor editor;

    private final String NOME_ARQUIVO ="anotacao.preferencias";
    private final String CHAVE_NOME = "";

    public Preferencias(Context c) {
        this.context = c;
        preferences= context.getSharedPreferences(NOME_ARQUIVO, 0);
        editor = preferences.edit();
    }

    public void salvar(String anotacao){
        editor.putString(CHAVE_NOME,anotacao);
        editor.commit();
    }

    public String recuperar(){
        return preferences.getString(CHAVE_NOME, " ");
    }
}
