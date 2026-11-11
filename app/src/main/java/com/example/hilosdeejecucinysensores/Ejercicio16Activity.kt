package com.example.hilosdeejecucinysensores

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.widget.FrameLayout
import android.widget.TextView
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.atan2

class Ejercicio16Activity : AppCompatActivity() {

    private lateinit var vistaRotable: View
    private lateinit var tvAngulo: TextView
    private lateinit var contenedor: FrameLayout

    private var anguloBase = 0f

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio16)

        vistaRotable = findViewById(R.id.vistaRotable16)
        tvAngulo = findViewById(R.id.tvAngulo16)
        contenedor = findViewById(R.id.contenedor16)

        contenedor.setOnTouchListener { _, event ->
            if (event.pointerCount == 2) {
                val x1 = event.getX(0)
                val y1 = event.getY(0)
                val x2 = event.getX(1)
                val y2 = event.getY(1)

                // calculo del angulo de rotacion con arco tangente 2
                val anguloRadianes = atan2((y2 - y1).toDouble(), (x2 - x1).toDouble())
                val anguloGrados = Math.toDegrees(anguloRadianes).toFloat()

                when (event.actionMasked) {
                    MotionEvent.ACTION_POINTER_DOWN -> {
                        anguloBase = vistaRotable.rotation - anguloGrados
                    }
                    MotionEvent.ACTION_MOVE -> {
                        val anguloFinal = anguloGrados + anguloBase
                        vistaRotable.rotation = anguloFinal
                        tvAngulo.text = "Angulo: ${anguloFinal.toInt()}°"
                    }
                }
            }
            true
        }
    }
}