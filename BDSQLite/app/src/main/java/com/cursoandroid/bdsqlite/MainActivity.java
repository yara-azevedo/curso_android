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
                                                                       "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                                                                        "nome VARCHAR(20)," +
                                                                        "idade INT(2))");
           // sqLiteDatabase.execSQL("DROP TABLE pessoa");
            //inserir
            sqLiteDatabase.execSQL("INSERT INTO pessoa(nome, idade) VALUES ('Aaaa', 12)");
            sqLiteDatabase.execSQL("INSERT INTO pessoa(nome, idade) VALUES ('Bbbbb', 7)");
            sqLiteDatabase.execSQL("INSERT INTO pessoa(nome, idade) VALUES ('ccc', 40)");
            sqLiteDatabase.execSQL("INSERT INTO pessoa(nome, idade) VALUES ('ddd', 87)");

            sqLiteDatabase.execSQL("UPDATE pessoa set idade = 18 where nome ='ddd'");
            sqLiteDatabase.execSQL("DELETE FROM pessoa WHERE nome ='ddd'");

            //recuperar
            String consulta= "SELECT * FROM pessoa WHERE nome ='Aaaa'";

            Cursor cursor = sqLiteDatabase.rawQuery(consulta,  null);
            int indiceID = cursor.getColumnIndex("id");
            int indice = cursor.getColumnIndex("nome");
            int indiceI = cursor.getColumnIndex("idade");

            cursor.moveToFirst();

            while(cursor != null){
                String nome = cursor.getString(indice);
                String idade = cursor.getString(indiceI);
                Log.i("Resultado - nome: ",nome+" "+ " idade" +idade );
                cursor.moveToNext();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}