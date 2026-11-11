package com.example.hilosdeejecucinysensores

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Ejercicio5Activity : AppCompatActivity() {

    // declaracion de vistas
    private lateinit var tvMensaje: TextView
    private lateinit var btnCorrutina: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio5)

        // vinculacion de vistas
        tvMensaje = findViewById(R.id.tvMensaje5)
        btnCorrutina = findViewById(R.id.btnCorrutina5)

        // evento listener para el boton de corrutina
        btnCorrutina.setOnClickListener {
            lanzarCorrutina5Segundos()
        }
    }

    // funcion que inicia una corrutina utilizando el lifecyclescope de la actividad
    private fun lanzarCorrutina5Segundos() {
        btnCorrutina.isEnabled = false

        lifecycleScope.launch {
            for (segundo in 1..5) {
                tvMensaje.text = "Mensaje $segundo de 5 (esperando 1 seg)"
                // retardo suspendible propio de las corrutinas sin bloquear la pantalla
                delay(1000)
            }
            tvMensaje.text = "Corrutina finalizada exitosamente"
            btnCorrutina.isEnabled = true
        }
    }
}