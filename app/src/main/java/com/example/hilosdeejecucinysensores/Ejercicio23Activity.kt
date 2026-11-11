package com.example.hilosdeejecucinysensores

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class Ejercicio23Activity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var acelerometro: Sensor? = null
    private lateinit var ivCirculo: ImageView
    private lateinit var contenedor: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio23)

        ivCirculo = findViewById(R.id.ivCirculo23)
        contenedor = findViewById(R.id.contenedor23)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        acelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    }

    override fun onResume() {
        super.onResume()
        acelerometro?.let { sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_GAME) }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            val ax = event.values[0]
            val ay = event.values[1]

            // desplazamos el circulo invirtiendo el eje X para mantener naturalidad
            val nuevaX = ivCirculo.x - (ax * 3f)
            val nuevaY = ivCirculo.y + (ay * 3f)

            val limiteAncho = (contenedor.width - ivCirculo.width).toFloat()
            val limiteAlto = (contenedor.height - ivCirculo.height).toFloat()

            // delimitamos los bordes de la pantalla
            if (limiteAncho > 0 && limiteAlto > 0) {
                ivCirculo.x = nuevaX.coerceIn(0f, limiteAncho)
                ivCirculo.y = nuevaY.coerceIn(0f, limiteAlto)
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
}