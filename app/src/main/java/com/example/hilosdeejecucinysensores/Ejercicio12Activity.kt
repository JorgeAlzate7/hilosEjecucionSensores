package com.example.hilosdeejecucinysensores

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Ejercicio12Activity : AppCompatActivity(), GestureDetector.OnGestureListener {

    // declaracion de detector de gestos y vistas
    private lateinit var detectorGestos: GestureDetector
    private lateinit var tvGestoDetectado: TextView
    private lateinit var layoutPrincipal: LinearLayout

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio12)

        tvGestoDetectado = findViewById(R.id.tvGestoDetectado12)
        layoutPrincipal = findViewById(R.id.layoutGesture12)

        // inicializacion del gesturedetector
        detectorGestos = GestureDetector(this, this)

        layoutPrincipal.setOnTouchListener { _, event ->
            detectorGestos.onTouchEvent(event)
        }
    }

    // callbacks para detectar gestos especificos
    override fun onSingleTapUp(e: MotionEvent): Boolean {
        tvGestoDetectado.text = "Gesto detectado: Toque simple (Tap)"
        return true
    }

    override fun onLongPress(e: MotionEvent) {
        tvGestoDetectado.text = "Gesto detectado: Toque prolongado (Long Press)"
    }

    override fun onFling(
        e1: MotionEvent?,
        e2: MotionEvent,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        tvGestoDetectado.text = "Gesto detectado: Deslizamiento rapido (Fling)"
        return true
    }

    override fun onDown(e: MotionEvent): Boolean = true
    override fun onShowPress(e: MotionEvent) {}
    override fun onScroll(
        e1: MotionEvent?,
        e2: MotionEvent,
        distanceX: Float,
        distanceY: Float
    ): Boolean = true
}