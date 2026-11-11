package com.example.hilosdeejecucinysensores

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

// vista personalizada para almacenar y redibujar trazos
class LienzoDibujo11 @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    // configuracion de pintura y trazos
    private val pincel = Paint().apply {
        color = Color.BLUE
        isAntiAlias = true
        strokeWidth = 10f
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
    }

    private var trazoActual = Path()
    private val listaTrazos = mutableListOf<Path>()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // dibuja todos los trazos almacenados previamente
        for (trazo in listaTrazos) {
            canvas.drawPath(trazo, pincel)
        }
        // dibuja el trazo actual en tiempo real
        canvas.drawPath(trazoActual, pincel)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                trazoActual = Path()
                trazoActual.moveTo(x, y)
                invalidate()
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                trazoActual.lineTo(x, y)
                invalidate()
                return true
            }
            MotionEvent.ACTION_UP -> {
                listaTrazos.add(trazoActual)
                invalidate()
                return true
            }
        }
        return super.onTouchEvent(event)
    }

    // funcion para limpiar la pantalla si es necesario
    fun limpiarLienzo() {
        listaTrazos.clear()
        trazoActual.reset()
        invalidate()
    }
}