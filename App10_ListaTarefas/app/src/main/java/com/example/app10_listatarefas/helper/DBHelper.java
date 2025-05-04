package com.example.app10_listatarefas.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static int VERSION = 3;
    public static String NOME="DB_TAREFAS";
    public static String TABELA="tarefas";


    public DBHelper(Context context) {
        super(context, NOME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABELA +" (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT NOT NULL DEFAULT 'Sem nome'\n);";
        db.execSQL(sql);

        try{
            Log.i("INFO DB", "sucessooo");
        }catch (Exception e){
            Log.i("INFO DB", "erro criacao de tabela" + e.getMessage());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tarefas");
        onCreate(db);

    }
}
