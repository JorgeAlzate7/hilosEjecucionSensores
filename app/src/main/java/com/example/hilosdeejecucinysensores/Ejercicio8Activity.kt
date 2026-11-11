package com.example.hilosdeejecucinysensores

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Ejercicio8Activity : AppCompatActivity() {

    // declaracion de vistas
    private lateinit var tvCoordenadas: TextView
    private lateinit var layoutPrincipal: LinearLayout

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio8)

        // inicializacion de componentes visuales
        tvCoordenadas = findViewById(R.id.tvCoordenadas8)
        layoutPrincipal = findViewById(R.id.layoutPrincipal8)

        // evento touch para capturar las coordenadas x e y
        layoutPrincipal.setOnTouchListener { _, event ->
            val x = event.x
            val y = event.y
            tvCoordenadas.text = "Coordenadas:\nX = ${x.toInt()} px\nY = ${y.toInt()} px"
            true
        }
    }
}