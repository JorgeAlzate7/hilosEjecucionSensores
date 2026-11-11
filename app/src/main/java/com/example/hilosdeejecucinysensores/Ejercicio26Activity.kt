package com.example.hilosdeejecucinysensores

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.sqrt

class Ejercicio26Activity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var acelerometro: Sensor? = null
    private lateinit var tvAgitado: TextView

    private var ultimaAceleracion = SensorManager.GRAVITY_EARTH
    private var aceleracionActual = SensorManager.GRAVITY_EARTH
    private var magnitudAgitado = 0.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio26)

        tvAgitado = findViewById(R.id.tvAgitado26)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        acelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    }

    override fun onResume() {
        super.onResume()
        acelerometro?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            val x = event.values[0]
            val y = event.values[1]
            val z = event.values[2]

            ultimaAceleracion = aceleracionActual
            // magnitud del vector de aceleracion
            aceleracionActual = sqrt((x * x + y * y + z * z).toDouble()).toFloat()

            val delta = aceleracionActual - ultimaAceleracion
            magnitudAgitado = magnitudAgitado * 0.9f + delta

            // umbral de sacudida
            if (magnitudAgitado > 12) {
                tvAgitado.text = "¡Agitado!"
            } else if (magnitudAgitado < 2) {
                tvAgitado.text = "Dispositivo estable"
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
}