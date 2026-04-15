package com.cursoandroid.fragment;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private Boolean status = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        button = findViewById(R.id.button);
        button.setOnClickListener(view -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            if(status){
                LoginFragment loginFragment = new LoginFragment();

                fragmentTransaction.add(R.id.container, loginFragment);
                fragmentTransaction.commit();

                button.setText("cadastrar");
                status = false;
            }else{
                CadastroFragment cadastroFragment = new CadastroFragment();
                fragmentTransaction.add(R.id.container, cadastroFragment);
                fragmentTransaction.commit();

                button.setText("logar");
                status = true;

            }


        });
    }
}