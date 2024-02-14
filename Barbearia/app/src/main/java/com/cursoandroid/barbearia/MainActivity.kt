package com.cursoandroid.barbearia

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.cursoandroid.barbearia.databinding.ActivityMainBinding
import com.cursoandroid.barbearia.view.HomeActivity
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.button.setOnClickListener{
            val nome = binding.etNome.text.toString()
            val senha = binding.etSenha.text.toString()

            when{
                nome.isEmpty()->{
                    mensagem(it, "Digite seu nome")
                }senha.isEmpty()->{
                    mensagem(it, "Digite a senha")
                }senha.length<=5 ->{
                    mensagem(it, "A senha precisa ter pelo menos 6 caracteres")
                }else ->{
                    navegarParaHome(nome)
                }
            }
        }
    }

    private fun mensagem(view: View, mensagem: String){
        val snackbar= Snackbar.make(view, mensagem, Snackbar.LENGTH_SHORT)
        snackbar.setBackgroundTint(Color.parseColor("#FF0000"))
        snackbar.setTextColor(Color.parseColor("#FFFFFF"))
        snackbar.show()
    }

    private fun navegarParaHome (nome: String){
        val intent = Intent(this, HomeActivity::class.java)
        intent.putExtra("nome", nome)
        startActivity(intent)
    }
}