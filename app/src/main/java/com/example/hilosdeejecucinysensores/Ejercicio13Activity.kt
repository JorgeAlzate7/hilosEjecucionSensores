package com.example.hilosdeejecucinysensores

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Ejercicio13Activity : AppCompatActivity() {

    // declaracion de vistas
    private lateinit var tvPosicionDedos: TextView
    private lateinit var layoutMultitouch: LinearLayout

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio13)

        tvPosicionDedos = findViewById(R.id.tvPosicionDedos13)
        layoutMultitouch = findViewById(R.id.layoutMultitouch13)

        // evento listener para rastrear punteros multitactiles
        layoutMultitouch.setOnTouchListener { _, event ->
            val cantidadDedos = event.pointerCount
            val builder = StringBuilder("Cantidad de dedos: $cantidadDedos\n\n")

            for (i in 0 until cantidadDedos) {
                val x = event.getX(i).toInt()
                val y = event.getY(i).toInt()
                builder.append("Dedo ${i + 1}: X = $x px, Y = $y px\n")
            }

            tvPosicionDedos.text = builder.toString()
            true
        }
    }
}