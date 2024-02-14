package com.cursoandroid.barbearia.view

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import com.cursoandroid.barbearia.R
import com.cursoandroid.barbearia.databinding.ActivityAgendamentoBinding
import com.google.android.material.snackbar.Snackbar
import java.util.Calendar

class AgendamentoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAgendamentoBinding
    private val calendar: Calendar = Calendar.getInstance()
    private var data: String=""
    private var hora:String=""
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgendamentoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        val nome = intent.extras?.getString("nome").toString()

        val datePicker = binding.datepicker
        datePicker.setOnDateChangedListener{_, year, monthOfYear, dayOfMonth->
            calendar.set(Calendar.YEAR,year)
            calendar.set(Calendar.MONTH,monthOfYear)
            calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)

            var dia = dayOfMonth.toString()
            val mes: String

            if(dayOfMonth<10){
                dia="0$dayOfMonth"
            }
            if(monthOfYear<10){
                mes = " " + (monthOfYear+1)
            }else{
                mes = (monthOfYear + 1).toString()
            }

            data ="$dia / $mes / %year"
        }

        binding.timerpicker.setOnTimeChangedListener{_, hourOfDay, minute ->
            val minuto: String

            if(minute<10){
                minuto="0$minute"
            }else{
                minuto = minute.toString()
            }

            hora = "$hourOfDay:$minuto"
        }

        binding.timerpicker.setIs24HourView(true)

        binding.btAgendar.setOnClickListener{
            val ba = binding.ba
            val bb = binding.bb
            val bc = binding.bc

            when{
                hora.isEmpty()->{
                    mensagem(it, "Escolha o horario", "#FF0000")
                }
                hora< "8:00" && hora>"19:00"->{
                    mensagem(it, "Fora do horário de atendimento", "#FF0000")
                }
                data.isEmpty()->{
                    mensagem(it, "Escolha uma data", "#FF0000")
                }
                ba.isChecked && data.isNotEmpty() && hora.isNotEmpty()->{
                    mensagem(it, "Agendamento realizado", "#FF03DAC5")
                }
                bb.isChecked && data.isNotEmpty() && hora.isNotEmpty()->{
                    mensagem(it, "Agendamento realizado", "#FF03DAC5")
                }
                bc.isChecked && data.isNotEmpty() && hora.isNotEmpty()->{
                    mensagem(it, "Agendamento realizado", "#FF03DAC5")
                }
                else ->{
                    mensagem(it, "Escolha um profissional", "#FF0000")
                }
            }
        }
    }

    private fun mensagem(view: View, mensagem: String, cor:String){
        val snackbar = Snackbar.make(view, mensagem, Snackbar.LENGTH_SHORT)
        snackbar.setBackgroundTint(Color.parseColor(cor))
        snackbar.setTextColor(Color.parseColor("#FFFFFF"))
        snackbar.show()

    }
}