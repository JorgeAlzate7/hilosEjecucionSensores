package com.example.hilosdeejecucinysensores

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class Ejercicio10Activity : AppCompatActivity() {

    // declaracion de variables para arrastrar la imagen
    private lateinit var ivPelota: ImageView
    private var dX = 0f
    private var dY = 0f

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio10)

        ivPelota = findViewById(R.id.ivPelota10)

        // evento touch listener para arrastrar la pelota libremente
        ivPelota.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // guardamos la diferencia entre la posicion de la vista y el toque
                    dX = view.x - event.rawX
                    dY = view.y - event.rawY
                    true
                }
                MotionEvent.ACTION_MOVE -> {
                    // actualizamos la posicion X e Y de la imagen segun el movimiento
                    view.animate()
                        .x(event.rawX + dX)
                        .y(event.rawY + dY)
                        .setDuration(0)
                        .start()
                    true
                }
                else -> false
            }
        }
    }
}