package com.cursoandroid.uatis.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.cursoandroid.uatis.Helper.Permissoes;
import com.cursoandroid.uatis.Helper.Preferencias;
import com.cursoandroid.uatis.R;

import java.util.HashMap;
import java.util.Random;


public class LoginActivity extends AppCompatActivity {

    private EditText telefone, nome;
    private Button cadastrar;

    private String[] permissoesNecessarias= new String[]{Manifest.permission.SEND_SMS};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Permissoes.validaPermissoes(1,this,permissoesNecessarias);

        finAll();
        cadastrar();

        cadastrar.setOnClickListener(view -> cadastrar());
    }

    public void finAll() {
        nome = findViewById(R.id.et_nome);
        telefone = findViewById(R.id.et_telefone);
        cadastrar = findViewById(R.id.bt_cadastrar);
    }

    public void cadastrar(){
        String nomeUser = nome.getText().toString();
        String telUser = telefone.getText().toString();
        String telTeste = "5554";

        //token
        Random random = new Random();
        int nrandom = random.nextInt(9999-1000)+1000;
        String token = String.valueOf(nrandom);
        String mensagem = "O código de confirmação é: " + token;

        //salvar  dados p validacao
        Preferencias preferencias = new Preferencias(getApplicationContext());
        preferencias.salvarUsuarioPreferencias(nomeUser, telUser, token);

        //envio do sms
        boolean enviado = sms("+" + telefone,mensagem);

        /*HashMap<String, String> usuario = preferencias.getDadosUsuario();
        Log.i("NOME", "N:" + usuario.get("nome"));*/
    }

    private boolean sms(String telefone, String mensagem){
        try{
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(telefone, null, mensagem, null, null);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        for(int resultado : grantResults){
            if(resultado == PackageManager.PERMISSION_DENIED){
                alertaValidacaoPermissao();
            }
        }
    }

    private void alertaValidacaoPermissao() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
        builder.setTitle("Permissão Negada");
        builder.setMessage("Aceite as permissões");
        builder.setPositiveButton("Confirmar", (dialogInterface, i) -> {

        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }


}