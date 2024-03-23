package com.cursoandroid.app10_listatarefas.helper;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static int VERSION =1;
    public static String NOME_DB = "TAREFAS";

    public static String TB_NOME = "tarefas";

    public DBHelper(@Nullable Context context) {
        super(context, NOME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql = "CREATE TABLE IF NOT EXISTS " + TB_NOME + " (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT NOT NULL)";

        try{
            sqLiteDatabase.execSQL(sql);
            Log.i("INFO DB", "foooi" );
        }catch (Exception e){
            Log.i("INFO DB", "errooo" +e.getMessage());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
