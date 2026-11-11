package com.example.hilosdeejecucinysensores

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class Ejercicio6Activity : AppCompatActivity() {

    // declaracion de vistas y trabajo de corrutina
    private lateinit var tvValoresSensor: TextView
    private lateinit var btnIniciar: Button
    private lateinit var btnDetener: Button

    private var trabajoSensor: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio6)

        // inicializacion de las vistas
        tvValoresSensor = findViewById(R.id.tvValoresSensor6)
        btnIniciar = findViewById(R.id.btnIniciar6)
        btnDetener = findViewById(R.id.btnDetener6)

        btnIniciar.setOnClickListener {
            iniciarSimulacionSensor()
        }

        btnDetener.setOnClickListener {
            detenerSimulacionSensor()
        }
    }

    // funcion para iniciar la lectura simulada del sensor con una corrutina
    private fun iniciarSimulacionSensor() {
        btnIniciar.isEnabled = false
        btnDetener.isEnabled = true

        trabajoSensor = lifecycleScope.launch {
            while (true) {
                // generacion de dato aleatorio entre 0 y 100
                val valorAleatorio = Random.nextInt(0, 100)
                tvValoresSensor.text = "Valor del Sensor: $valorAleatorio"

                // pausa de quinientos milisegundos
                delay(500)
            }
        }
    }

    // funcion para cancelar la corrutina en ejecucion
    private fun detenerSimulacionSensor() {
        trabajoSensor?.cancel()
        tvValoresSensor.text = "Sensor detenido"
        btnIniciar.isEnabled = true
        btnDetener.isEnabled = false
    }

    override fun onDestroy() {
        super.onDestroy()
        // cancelamos la corrutina si la actividad se destruye
        trabajoSensor?.cancel()
    }
}