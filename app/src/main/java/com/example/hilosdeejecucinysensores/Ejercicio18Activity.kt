package com.example.hilosdeejecucinysensores

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.atan2

class Ejercicio18Activity : AppCompatActivity() {

    private lateinit var ivImagen: ImageView
    private lateinit var contenedor: FrameLayout
    private lateinit var detectorEscala: ScaleGestureDetector

    private var dX = 0f
    private var dY = 0f
    private var factorEscala = 1.0f
    private var anguloBase = 0f

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio18)

        ivImagen = findViewById(R.id.ivImagen18)
        contenedor = findViewById(R.id.contenedor18)

        detectorEscala = ScaleGestureDetector(this, object : ScaleGestureDetector.SimpleOnScaleGestureListener() {
            override fun onScale(detector: ScaleGestureDetector): Boolean {
                factorEscala *= detector.scaleFactor
                factorEscala = factorEscala.coerceIn(0.5f, 4.0f)
                ivImagen.scaleX = factorEscala
                ivImagen.scaleY = factorEscala
                return true
            }
        })

        contenedor.setOnTouchListener { _, event ->
            detectorEscala.onTouchEvent(event)

            when (event.actionMasked) {
                MotionEvent.ACTION_DOWN -> {
                    dX = ivImagen.x - event.rawX
                    dY = ivImagen.y - event.rawY
                }
                MotionEvent.ACTION_POINTER_DOWN -> {
                    if (event.pointerCount == 2) {
                        val x1 = event.getX(0)
                        val y1 = event.getY(0)
                        val x2 = event.getX(1)
                        val y2 = event.getY(1)
                        val anguloRadianes = atan2((y2 - y1).toDouble(), (x2 - x1).toDouble())
                        anguloBase = ivImagen.rotation - Math.toDegrees(anguloRadianes).toFloat()
                    }
                }
                MotionEvent.ACTION_MOVE -> {
                    if (event.pointerCount == 1) {
                        ivImagen.animate().x(event.rawX + dX).y(event.rawY + dY).setDuration(0).start()
                    } else if (event.pointerCount == 2) {
                        val x1 = event.getX(0)
                        val y1 = event.getY(0)
                        val x2 = event.getX(1)
                        val y2 = event.getY(1)
                        val anguloRadianes = atan2((y2 - y1).toDouble(), (x2 - x1).toDouble())
                        val anguloGrados = Math.toDegrees(anguloRadianes).toFloat()
                        ivImagen.rotation = anguloGrados + anguloBase
                    }
                }
            }
            true
        }
    }
}