package com.cursoandroid.app4_alcoolgasolina;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText alcool1, gasolina1;
    private TextView textView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        alcool1 = findViewById(R.id.ed_alcool);
        gasolina1 = findViewById(R.id.ed_gasolina);
        textView = findViewById(R.id.textView2);

        }

    public void calcular(View view){
        String alcool = alcool1.getText().toString();
        String gasolina = gasolina1.getText().toString();

        Boolean resultado =validar(alcool, gasolina);
        if(resultado){
            Double precoA = Double.parseDouble(alcool);
            Double precoG = Double.parseDouble(gasolina);

            Double r = precoA / precoG;
            if(r>=0.7){
                textView.setText("Melhor ir de GASOLINA");
            }else{
                textView.setText("Melhor ir de ALCOOL");
            }
        }else{
            textView.setText("Preencha todos os campos");
        }


    }
    public Boolean validar(String pA, String pG){
        Boolean resultado = true;

        if(pA == null || pA.equals("")){
            resultado = false;
        }else if(pG == null || pG.equals("")){
            resultado = false;
        }
        return resultado;

    }
}
