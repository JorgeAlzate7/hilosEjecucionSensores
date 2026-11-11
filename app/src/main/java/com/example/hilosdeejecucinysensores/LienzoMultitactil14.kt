package com.example.hilosdeejecucinysensores

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

// vista personalizada para dibujar con varios dedos y colores distintos por puntero
class LienzoMultitactil14 @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    // paleta de colores para asignar a cada dedo
    private val colores = intArrayOf(
        Color.RED, Color.BLUE, Color.GREEN, Color.MAGENTA, Color.CYAN, Color.YELLOW
    )

    // mapa de punteros activos a sus respectivos trazos y pinceles
    private val trazosActivos = HashMap<Int, Path>()
    private val pincelesActivos = HashMap<Int, Paint>()

    // lista de trazos finalizados para redibujar
    private val trazosFinalizados = mutableListOf<Pair<Path, Paint>>()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // dibuja trazos anteriores
        for (par in trazosFinalizados) {
            canvas.drawPath(par.first, par.second)
        }

        // dibuja trazos activos en tiempo real
        for ((id, path) in trazosActivos) {
            pincelesActivos[id]?.let { canvas.drawPath(path, it) }
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val index = event.actionIndex
        val id = event.getPointerId(index)
        val x = event.getX(index)
        val y = event.getY(index)

        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN -> {
                val nuevoPath = Path().apply { moveTo(x, y) }
                val nuevoPaint = Paint().apply {
                    color = colores[id % colores.size]
                    strokeWidth = 14f
                    style = Paint.Style.STROKE
                    isAntiAlias = true
                    strokeJoin = Paint.Join.ROUND
                    strokeCap = Paint.Cap.ROUND
                }
                trazosActivos[id] = nuevoPath
                pincelesActivos[id] = nuevoPaint
                invalidate()
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                for (i in 0 until event.pointerCount) {
                    val pId = event.getPointerId(i)
                    val px = event.getX(i)
                    val py = event.getY(i)
                    trazosActivos[pId]?.lineTo(px, py)
                }
                invalidate()
                return true
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_POINTER_UP -> {
                trazosActivos[id]?.let { path ->
                    pincelesActivos[id]?.let { paint ->
                        trazosFinalizados.add(Pair(path, paint))
                    }
                }
                trazosActivos.remove(id)
                pincelesActivos.remove(id)
                invalidate()
                return true
            }
        }
        return super.onTouchEvent(event)
    }

    override fun performClick(): Boolean {
        return super.performClick()
    }

    fun limpiar() {
        trazosFinalizados.clear()
        trazosActivos.clear()
        pincelesActivos.clear()
        invalidate()
    }
}