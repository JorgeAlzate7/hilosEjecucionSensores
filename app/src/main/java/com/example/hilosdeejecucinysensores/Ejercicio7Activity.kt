package com.example.hilosdeejecucinysensores

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Ejercicio7Activity : AppCompatActivity() {

    // declaracion de variables para vistas
    private lateinit var tvEventoTactil: TextView
    private lateinit var layoutPrincipal: LinearLayout

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio7)

        // inicializacion de componentes
        tvEventoTactil = findViewById(R.id.tvEventoTactil7)
        layoutPrincipal = findViewById(R.id.layoutPrincipal7)

        // escuchador de eventos de toque para la pantalla
        layoutPrincipal.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    tvEventoTactil.text = "Pulsacion iniciada"
                    true
                }
                MotionEvent.ACTION_MOVE -> {
                    tvEventoTactil.text = "Desplazando el dedo"
                    true
                }
                MotionEvent.ACTION_UP -> {
                    tvEventoTactil.text = "Pulsacion finalizada"
                    true
                }
                else -> false
            }
        }
    }
}