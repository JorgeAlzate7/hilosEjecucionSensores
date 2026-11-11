package com.example.hilosdeejecucinysensores

import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Ejercicio25Activity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var sensorProximidad: Sensor? = null
    private var sensorLuz: Sensor? = null

    private lateinit var layoutPrincipal: LinearLayout
    private lateinit var tvEstado: TextView

    private var estaCerca = false
    private var esOscuro = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio25)

        layoutPrincipal = findViewById(R.id.layoutPrincipal25)
        tvEstado = findViewById(R.id.tvEstado25)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        sensorProximidad = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
        sensorLuz = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
    }

    override fun onResume() {
        super.onResume()
        sensorProximidad?.let { sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL) }
        sensorLuz?.let { sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL) }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type == Sensor.TYPE_PROXIMITY) {
            val distancia = event.values[0]
            val maxRango = sensorProximidad?.maximumRange ?: 5f
            estaCerca = distancia < maxRango
        } else if (event.sensor.type == Sensor.TYPE_LIGHT) {
            val lux = event.values[0]
            esOscuro = lux < 20
        }

        // si esta oscuro y cerca -> fondo negro
        // si esta claro y lejos -> fondo blanco
        if (esOscuro && estaCerca) {
            layoutPrincipal.setBackgroundColor(Color.BLACK)
            tvEstado.text = "Oscuro y Cerca\nFondo Negro"
            tvEstado.setTextColor(Color.WHITE)
        } else if (!esOscuro && !estaCerca) {
            layoutPrincipal.setBackgroundColor(Color.WHITE)
            tvEstado.text = "Claro y Lejos\nFondo Blanco"
            tvEstado.setTextColor(Color.BLACK)
        } else {
            layoutPrincipal.setBackgroundColor(Color.GRAY)
            tvEstado.text = "Estado Intermedio"
            tvEstado.setTextColor(Color.WHITE)
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
}