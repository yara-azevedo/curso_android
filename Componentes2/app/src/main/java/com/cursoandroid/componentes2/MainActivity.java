 package com.cursoandroid.componentes2;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

 public class MainActivity extends AppCompatActivity {

    private Switch aSwitch;
    private ToggleButton toggleButton;
    TextView textView;
    private Button button;
     int progresso =0; //se colocar dentro do metodo não vai funcionar corretamente


     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        aSwitch = findViewById(R.id.switch1);
        toggleButton = findViewById(R.id.toggleButton);
        textView = findViewById(R.id.resultado);

        listenerToggleSwitch();
         seekbar();
    }

    public void ToggleSwitch(View view){


        if(toggleButton.isChecked()){
            textView.setText("Toggle ligado");
        }else{
            textView.setText("Toggle desligado");
        }

        if(aSwitch.isChecked()){
            textView.setText("Switch ligado");
        }else{
            textView.setText("Switch desligado");
        }
    }

    public void listenerToggleSwitch(){
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    textView.setText("aSwitch ligado");
                }else{
                    textView.setText("aSwitch desligado");
                }
            }
        });
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    textView.setText("Toggle ligado");
                }else{
                    textView.setText("Toggle desligado");
                }
            }
        });

    }

    public void Toast(View view){
        Button button = findViewById(R.id.button);

       // Toast.makeText(this, "TOAST", Toast.LENGTH_SHORT).show();

        //personalizado
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(android.R.drawable.star_big_on);

        TextView textView = new TextView(this);
        textView.setText("personalizado");
        textView.setBackgroundResource(R.color.purple_200);

        Toast toast = new Toast(this);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(textView);
        //toast.setView(imageView);
        toast.show();
    }

    public void AlertDialog(View view){
        //instanciar
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        //mensagem e titulo e imagem
        dialog.setTitle("AAAAAAA");
        dialog.setMessage("BBBBB");
        dialog.setIcon(android.R.drawable.arrow_down_float);

        //cancelamento
        dialog.setCancelable(false); //obriga a escolher uma das opções se for true(default)

        //ações
        dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "Sim", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "Não", Toast.LENGTH_SHORT).show();
            }
        });

        //criar e exibir
        dialog.create();
        dialog.show();
    }

    public void progressBar( View view){
        ProgressBar horizontal = findViewById(R.id.progressBar);
        ProgressBar circular = findViewById(R.id.progressBar2);
        Button button = findViewById(R.id.button4);
        //horizontal
        this.progresso = this.progresso + 1;
        horizontal.setProgress(this.progresso);
        //circular
        circular.setVisibility(View.VISIBLE);
        if(this.progresso==10){
            circular.setVisibility(View.GONE);
        }
    }

    public void seekbar(){
        SeekBar seekBar = findViewById(R.id.seekBar);
        TextView res = findViewById(R.id.textView4);
        Button button = findViewById(R.id.button5);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                res.setText("onProgressChanged "+ i + " / " + seekBar.getMax());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                res.setText("onStartTrackingTouch");

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                res.setText("onStopTrackingTouch");

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                res.setText("Progresso: "+ seekBar.getProgress());

            }
        });
    }
}