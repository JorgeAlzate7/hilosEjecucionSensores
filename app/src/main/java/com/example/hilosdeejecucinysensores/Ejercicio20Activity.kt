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

class Ejercicio20Activity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var sensorLuz: Sensor? = null
    private lateinit var layoutPrincipal: LinearLayout
    private lateinit var tvLux: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio20)

        layoutPrincipal = findViewById(R.id.layoutPrincipal20)
        tvLux = findViewById(R.id.tvLux20)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        sensorLuz = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)

        if (sensorLuz == null) {
            tvLux.text = "Sensor de luz no disponible"
        }
    }

    override fun onResume() {
        super.onResume()
        sensorLuz?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent) {
        val lux = event.values[0]
        tvLux.text = "Luminosidad: $lux Lux"

        // evaluacion de luminosidad para cambiar el color de fondo
        if (lux < 10) {
            layoutPrincipal.setBackgroundColor(Color.BLACK)
            tvLux.setTextColor(Color.WHITE)
        } else if (lux < 200) {
            layoutPrincipal.setBackgroundColor(Color.GRAY)
            tvLux.setTextColor(Color.WHITE)
        } else {
            layoutPrincipal.setBackgroundColor(Color.YELLOW)
            tvLux.setTextColor(Color.BLACK)
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
}