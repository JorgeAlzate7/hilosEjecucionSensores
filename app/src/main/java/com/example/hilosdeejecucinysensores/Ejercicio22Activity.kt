package com.example.hilosdeejecucinysensores

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Ejercicio22Activity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var acelerometro: Sensor? = null
    private var magnetometro: Sensor? = null
    private lateinit var ivAguja: ImageView
    private lateinit var tvBrujula: TextView

    private val datosAcelerometro = FloatArray(3)
    private val datosMagnetometro = FloatArray(3)
    private var tieneAcelerometro = false
    private var tieneMagnetometro = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio22)

        ivAguja = findViewById(R.id.ivAgujaBrujula22)
        tvBrujula = findViewById(R.id.tvBrujula22)

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
                val azimutRadianes = valoresOrientacion[0]
                val azimutGrados = Math.toDegrees(azimutRadianes.toDouble()).toFloat()

                // rotacion negativa para apuntar al norte magnetico
                ivAguja.rotation = -azimutGrados
                tvBrujula.text = "Azimut: ${azimutGrados.toInt()}° Norte"
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
}