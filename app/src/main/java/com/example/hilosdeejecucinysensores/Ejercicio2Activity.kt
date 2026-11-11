package com.example.hilosdeejecucinysensores

import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Ejercicio2Activity : AppCompatActivity() {

    // declaracion de variables para vistas
    private lateinit var tvProgreso: TextView
    private lateinit var pbCarga: ProgressBar
    private lateinit var btnIniciar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio2)

        // inicializacion de componentes mediante id
        tvProgreso = findViewById(R.id.tvProgreso2)
        pbCarga = findViewById(R.id.pbCarga2)
        btnIniciar = findViewById(R.id.btnIniciar2)

        // evento listener para el boton
        btnIniciar.setOnClickListener {
            iniciarCargaProgresiva()
        }
    }

    // funcion para simular la carga progresiva en segundo plano
    private fun iniciarCargaProgresiva() {
        // deshabilitamos el boton mientras se ejecuta la carga
        btnIniciar.isEnabled = false

        // hilo secundario para la simulacion
        Thread {
            for (i in 0..100) {
                try {
                    // pausa de cien milisegundos entre cada incremento
                    Thread.sleep(100)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

                // actualizacion del progreso en la interfaz de usuario
                runOnUiThread {
                    pbCarga.progress = i
                    tvProgreso.text = "Carga: $i%"
                }
            }

            // reactivamos el boton al finalizar
            runOnUiThread {
                btnIniciar.isEnabled = true
            }
        }.start()
    }
}