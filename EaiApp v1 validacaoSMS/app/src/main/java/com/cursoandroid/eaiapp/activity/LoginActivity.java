package com.cursoandroid.eaiapp.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.cursoandroid.eaiapp.R;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.util.Arrays;
import java.util.Random;

import helper.Permissao;
import helper.Preferencias;

public class LoginActivity extends AppCompatActivity {

    private EditText nome, telefone;
    private Button cadastrar;
    private String[] permissoesNecessarias = new String[]{Manifest.permission.SEND_SMS};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Permissao.validaPermissoes(1,this, new String[]{Arrays.toString(permissoesNecessarias)});

        findd();
        listenerr();
    }

    void maskk(){
        SimpleMaskFormatter simpleMaskFormatter = new SimpleMaskFormatter("+NN (NN)NNNNN-NNNN");
        MaskTextWatcher maskTextWatcher = new MaskTextWatcher(telefone, simpleMaskFormatter);
        telefone.addTextChangedListener(maskTextWatcher);
    }


    void findd() {
        nome = findViewById(R.id.et_token);
        telefone = findViewById(R.id.et_telefone);
        cadastrar = findViewById(R.id.btn_validar);
        maskk();


    }

    void listenerr() {
        cadastrar.setOnClickListener(view -> {
            String nome1 = nome.getText().toString();
            String telefone1 = telefone.getText().toString();

            //token pra validacao do numero
            Random random = new Random();
            int randomm = random.nextInt(9999-100)+1000;
            String token = String.valueOf(randomm);
            String mensagemEnvio = "Eai código de confirmação: " + token;

            Preferencias preferencias = new Preferencias(LoginActivity.this);
            preferencias.salvarUsuarioPreferencias(nome1, telefone1,token);

            //envio do sms
            telefone1 = "4567";
            boolean enviadoSMS = enviaSMS(telefone1, mensagemEnvio);

            if(enviadoSMS){
                Intent intent = new Intent(this, ValidadorActivity.class);
                startActivity(intent);
                finish();
            }else{
                Toast.makeText(this, "problema ao enviar o sms, tente novamente", Toast.LENGTH_SHORT).show();
                //mensagem de erro")

            }

            //HashMap<String, String> usuario = preferencias.getDadosUsuario();

        });

    }

    private boolean enviaSMS(String telefone, String mensagem){
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(telefone,null, mensagem, null, null);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public void onRequestPermissionResult(int requestCode, String[] permissions, int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        for (int resultado : grantResults){
            if(resultado == PackageManager.PERMISSION_DENIED){
                alertaValidacaoPermissao();
            }
        }
    }

    private void alertaValidacaoPermissao() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Permissões Negadas");
        builder.setMessage("Para utilizar o app é necessário aceitar as permissões");
        builder.setPositiveButton("CONFIRMAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();


    }
}