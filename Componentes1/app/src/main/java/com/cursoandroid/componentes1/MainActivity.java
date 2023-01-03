package com.cursoandroid.componentes1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        textView3 = findViewById(R.id.textView3);

        radiolistener();

    }

    public void edit(View view){
        EditText e1, e2;
        TextView textView;

        e1 = findViewById(R.id.editTextTextPersonName);
        e2 = findViewById(R.id.editTextTextPersonName2);
        textView = findViewById(R.id.textView);

        String a, b;

        a = e1.getText().toString();
        b = e2.getText().toString();

        textView.setText(a + " " + b);
        e1.setText(" ");
        e2.setText(" ");
    }

    public void check(View view){
        CheckBox c1, c2, c3;
        TextView textView = findViewById(R.id.textView2);
        String texto = " ";

        c1 = findViewById(R.id.checkBox);
        c2 = findViewById(R.id.checkBox2);
        c3 = findViewById(R.id.checkBox3);

        if(c1.isChecked()){
            String t = c1.getText().toString(); //se precisar recuperar o valor do checkbox
            texto = texto +  t + " is checked - ";
        }
        if (c2.isChecked()){
            texto = texto + "B is checked - ";
        }
        if (c3.isChecked()){
            texto = texto + "C is checked - ";
        }
        textView.setText(texto);
    }

    public void radio (View view){
        RadioButton r1, r2, r3;
        String texto = " ";
        TextView textView;
        RadioGroup radioGroup = findViewById(R.id.radioGroup);

        r1 = findViewById(R.id.radioButton1);
        r2 = findViewById(R.id.radioButton2);
        r3 = findViewById(R.id.radioButton3);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if(i == R.id.radioButton2){
                    textView3.setText("B is checked");
                }else if (i == R.id.radioButton3){
                    textView3.setText("C is checked");
                }

            }
        });

        if(r1.isChecked()){
            String r;
            r = r1.getText().toString();
            texto = r + " is checked - ";
        }
        if(r2.isChecked()){
            texto = texto +"B is checked - ";
        } else if(r3.isChecked()){
            texto = texto +"C is checked - ";
        }

        textView3.setText(texto);
    }

    public void radiolistener(){
        RadioButton r2, r3;
        RadioGroup radioGroup = findViewById(R.id.radioGroup);

        r2 = findViewById(R.id.radioButton2);
        r3 = findViewById(R.id.radioButton3);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if(i == R.id.radioButton2){
                    textView3.setText("B is checked");
                }else if (i == R.id.radioButton3){
                    textView3.setText("C is checked");
                }

            }
        });

    }

}