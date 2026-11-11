package com.example.hilosdeejecucinysensores

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Ejercicio3Activity : AppCompatActivity() {

    // declaracion de variables visuales
    private lateinit var btnIniciar: Button
    private lateinit var tvSalida: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio3)

        // vinculacion de componentes
        btnIniciar = findViewById(R.id.btnIniciar3)
        tvSalida = findViewById(R.id.tvSalida3)

        btnIniciar.setOnClickListener {
            tvSalida.text = "Iniciando hilos simultaneos...\n\n"
            iniciarTresHilos()
        }
    }

    // funcion para ejecutar tres hilos al mismo tiempo
    private fun iniciarTresHilos() {
        // hilo numero uno para imprimir numeros pares
        val hiloPares = Thread {
            for (i in 2..10 step 2) {
                Thread.sleep(150)
                agregarTexto("Par: $i")
            }
        }

        // hilo numero dos para imprimir numeros impares
        val hiloImpares = Thread {
            for (i in 1..9 step 2) {
                Thread.sleep(150)
                agregarTexto("Impar: $i")
            }
        }

        // hilo numero tres para imprimir letras del alfabeto
        val hiloLetras = Thread {
            val letras = charArrayOf('A', 'B', 'C', 'D', 'E')
            for (letra in letras) {
                Thread.sleep(150)
                agregarTexto("Letra: $letra")
            }
        }

        // arranque de los tres hilos en paralelo
        hiloPares.start()
        hiloImpares.start()
        hiloLetras.start()
    }

    // funcion auxiliar para anexar texto a la pantalla desde cualquier hilo
    private fun agregarTexto(mensaje: String) {
        runOnUiThread {
            tvSalida.append("$mensaje\n")
        }
    }
}