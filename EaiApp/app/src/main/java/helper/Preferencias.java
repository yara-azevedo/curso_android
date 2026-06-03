package helper;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class Preferencias {
    private Context context;
    private SharedPreferences sharedPreferences;
    private final String NOME_ARQUIVO = "eaiapp.preferencias";
    private final int MODE = 0;
    private SharedPreferences.Editor editor;
    private final String CHAVE_IDENTIFICADOR = "identificadorusuariologado";
    private final String CHAVE_NOME = "nomeusuariologado";




    public Preferencias(Context contextParametro){
        context = contextParametro;
        sharedPreferences = context.getSharedPreferences(NOME_ARQUIVO, MODE);
        editor = sharedPreferences.edit();

    }

    public void salvarDados(String identificadorusuariologado, String nomeUsuario){
        editor.putString(CHAVE_IDENTIFICADOR, identificadorusuariologado);
        editor.putString(CHAVE_NOME, nomeUsuario);
        editor.commit();

    }

    public String getIdentificador(){
        return sharedPreferences.getString(CHAVE_IDENTIFICADOR,null);
    }

    public String getNome(){
        return sharedPreferences.getString(CHAVE_NOME,null);
    }


}
