package com.cursoandroid.app5_gorjetas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private SeekBar seekBar;
    private TextView procentagem;
    private TextView txt_gorjeta;
    private TextView total;

    private double porc=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        editText = findViewById(R.id.editText);
        seekBar = findViewById(R.id.seekBar);
        procentagem = findViewById(R.id.txt_porcentagem);
        txt_gorjeta = findViewById(R.id.gorjeta);
        total = findViewById(R.id.total);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                porc = i;
                procentagem.setText( Math.round(porc) +"%");
                calcular();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        }

    public void calcular(){
        String recuperado = editText.getText().toString();
        if (recuperado == null || recuperado.equals("")) {
            Toast.makeText(this, "Digite um valor", Toast.LENGTH_SHORT).show();
        }else{
            double digitado = Double.parseDouble(recuperado);
            double gorjeta = digitado*(porc/100);
            txt_gorjeta.setText("R$ " + gorjeta);

            double t = gorjeta+digitado;
            total.setText("R$" + t);
        }
    }
}