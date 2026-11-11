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

class Ejercicio19Activity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var sensorProximidad: Sensor? = null
    private lateinit var layoutPrincipal: LinearLayout
    private lateinit var tvEstado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio19)

        layoutPrincipal = findViewById(R.id.layoutPrincipal19)
        tvEstado = findViewById(R.id.tvEstadoProximidad19)

        // obtenemos el servicio del administrador de sensores
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        sensorProximidad = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)

        if (sensorProximidad == null) {
            tvEstado.text = "El dispositivo no cuenta con sensor de proximidad"
        }
    }

    override fun onResume() {
        super.onResume()
        sensorProximidad?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent) {
        val distancia = event.values[0]
        val rangoMaximo = sensorProximidad?.maximumRange ?: 5f

        if (distancia < rangoMaximo) {
            // objeto cerca -> fondo rojo
            layoutPrincipal.setBackgroundColor(Color.RED)
            tvEstado.text = "Objeto cerca!\nDistancia: $distancia cm"
            tvEstado.setTextColor(Color.WHITE)
        } else {
            // objeto lejos -> fondo verde
            layoutPrincipal.setBackgroundColor(Color.GREEN)
            tvEstado.text = "Objeto lejos!\nDistancia: $distancia cm"
            tvEstado.setTextColor(Color.BLACK)
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
}