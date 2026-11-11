package com.example.hilosdeejecucinysensores

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Ejercicio21Activity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var acelerometro: Sensor? = null
    private var magnetometro: Sensor? = null
    private lateinit var tvOrientacion: TextView

    private val datosAcelerometro = FloatArray(3)
    private val datosMagnetometro = FloatArray(3)
    private var tieneAcelerometro = false
    private var tieneMagnetometro = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio21)

        tvOrientacion = findViewById(R.id.tvOrientacion21)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        acelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        magnetometro = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
    }

    override fun onResume() {
        super.onResume()
        acelerometro?.let { sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_UI) }
        magnetometro?.let { sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_UI) }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            System.arraycopy(event.values, 0, datosAcelerometro, 0, event.values.size)
            tieneAcelerometro = true
        } else if (event.sensor.type == Sensor.TYPE_MAGNETIC_FIELD) {
            System.arraycopy(event.values, 0, datosMagnetometro, 0, event.values.size)
            tieneMagnetometro = true
        }

        if (tieneAcelerometro && tieneMagnetometro) {
            val matrizRotacion = FloatArray(9)
            val matrizInclinacion = FloatArray(9)
            val exito = SensorManager.getRotationMatrix(matrizRotacion, matrizInclinacion, datosAcelerometro, datosMagnetometro)

            if (exito) {
                val valoresOrientacion = FloatArray(3)
                SensorManager.getOrientation(matrizRotacion, valoresOrientacion)

                // conversion de radianes a grados
                val azimut = Math.toDegrees(valoresOrientacion[0].toDouble()).toInt()
                val pitch = Math.toDegrees(valoresOrientacion[1].toDouble()).toInt()
                val roll = Math.toDegrees(valoresOrientacion[2].toDouble()).toInt()

                tvOrientacion.text = "Orientacion:\nAzimut: $azimut°\nPitch: $pitch°\nRoll: $roll°"
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
}