package com.example.hilosdeejecucinysensores

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Ejercicio1Activity : AppCompatActivity() {

    // declaracion de variables para componentes visuales
    private lateinit var tvResultado: TextView
    private lateinit var btnIniciar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio1)

        // enlace de elementos con los ids del archivo xml
        tvResultado = findViewById(R.id.tvResultado1)
        btnIniciar = findViewById(R.id.btnIniciar1)

        // evento click del boton
        btnIniciar.setOnClickListener {
            iniciarContador()
        }
    }

    // funcion que ejecuta la secuencia de conteo en segundo plano
    private fun iniciarContador() {
        // creacion del hilo secundario
        Thread {
            for (i in 1..10) {
                try {
                    // pausa de mil milisegundos equivalente a un segundo
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

                // envio de resultados al hilo de la interfaz de usuario
                runOnUiThread {
                    tvResultado.text = "Numero: $i"
                }
            }
        }.start()
    }
}