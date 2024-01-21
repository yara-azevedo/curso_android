package com.cursoandroid.bdsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            //criacao do banco
            SQLiteDatabase sqLiteDatabase = openOrCreateDatabase("app", MODE_PRIVATE, null);
            //tabela
            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS pessoa (" +
                                                                        "nome VARCHAR(20)," +
                                                                        "idade INT(2))");
            //inserir
            sqLiteDatabase.execSQL("INSERT INTO pessoa(nome, idade) VALUES ('Aaaa', 12)");
            sqLiteDatabase.execSQL("INSERT INTO pessoa(nome, idade) VALUES ('Bbbbb', 7)");

            //recuperar
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM pessoa", null);

            int indice = cursor.getColumnIndex("nome");
            int indiceI = cursor.getColumnIndex("idade");

            cursor.moveToFirst();

            while(cursor != null){
                Log.i("Resultado - nome: ", cursor.getString(indice));
                Log.i("Resultado - idade: ", cursor.getString(indiceI));
                cursor.moveToNext();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}