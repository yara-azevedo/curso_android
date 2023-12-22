package com.example.app6_caracoroa;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    ImageView img1, img2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        findA();
        action();

        Bundle data = getIntent().getExtras();
        assert data != null;
        int numero = data.getInt("numero");

        if (numero == 0) {
            img1.setImageResource(R.drawable.moeda_cara);
            Toast.makeText(getApplicationContext(), "cara", Toast.LENGTH_SHORT).show();
        } else {
            img1.setImageResource(R.drawable.moeda_coroa);
            Toast.makeText(getApplicationContext(), "coroa", Toast.LENGTH_SHORT).show();
        }
    }

    public void action(){
        img2.setOnClickListener(view -> {
            finish();
        });
    }

    public void findA() {
        img1 = findViewById(R.id.imageView1);
        img2 = findViewById(R.id.imageView2);
    }
}