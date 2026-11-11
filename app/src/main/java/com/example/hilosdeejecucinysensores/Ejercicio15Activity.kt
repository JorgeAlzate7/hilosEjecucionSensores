package com.example.hilosdeejecucinysensores

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.ScaleGestureDetector
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class Ejercicio15Activity : AppCompatActivity() {

    private lateinit var ivImagen: ImageView
    private lateinit var contenedor: FrameLayout
    private lateinit var detectorEscala: ScaleGestureDetector
    private var factorEscala = 1.0f

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio15)

        ivImagen = findViewById(R.id.ivImagen15)
        contenedor = findViewById(R.id.contenedor15)

        // inicializacion del detector de gesto de pellizco
        detectorEscala = ScaleGestureDetector(this, object : ScaleGestureDetector.SimpleOnScaleGestureListener() {
            override fun onScale(detector: ScaleGestureDetector): Boolean {
                factorEscala *= detector.scaleFactor
                // limitamos el nivel minimo y maximo de zoom
                factorEscala = factorEscala.coerceIn(0.5f, 4.0f)
                ivImagen.scaleX = factorEscala
                ivImagen.scaleY = factorEscala
                return true
            }
        })

        contenedor.setOnTouchListener { _, event ->
            detectorEscala.onTouchEvent(event)
            true
        }
    }
}