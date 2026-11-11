package com.example.hilosdeejecucinysensores

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.sqrt

class Ejercicio28Activity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var acelerometro: Sensor? = null
    private var giroscopio: Sensor? = null
    private var contadorPasosSensor: Sensor? = null

    private lateinit var rvSensores: RecyclerView
    private lateinit var btnRegistrar: Button

    private val listaRegistros = mutableListOf<RegistroSensor28>()
    private lateinit var adaptador: SensorAdapter28

    private var pasosTotal = 0
    private var nivelMovimientoActual = "Bajo"
    private var orientacionActual = "Estable"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio28)

        rvSensores = findViewById(R.id.rvSensores28)
        btnRegistrar = findViewById(R.id.btnRegistrar28)

        // configuracion de recyclerview con adaptador
        adaptador = SensorAdapter28(listaRegistros)
        rvSensores.layoutManager = LinearLayoutManager(this)
        rvSensores.adapter = adaptador

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        acelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        giroscopio = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
        contadorPasosSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR)

        btnRegistrar.setOnClickListener {
            guardarLecturaActual()
        }
    }

    override fun onResume() {
        super.onResume()
        acelerometro?.let { sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_UI) }
        giroscopio?.let { sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_UI) }
        contadorPasosSensor?.let { sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_UI) }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent) {
        when (event.sensor.type) {
            Sensor.TYPE_ACCELEROMETER -> {
                val x = event.values[0]
                val y = event.values[1]
                val z = event.values[2]
                val magnitud = sqrt((x * x + y * y + z * z).toDouble())
                nivelMovimientoActual = if (magnitud > 13) "Alto" else if (magnitud > 10.5) "Medio" else "Bajo"
            }
            Sensor.TYPE_GYROSCOPE -> {
                val gx = event.values[0]
                val gy = event.values[1]
                val gz = event.values[2]
                val velocidadGiro = sqrt((gx * gx + gy * gy + gz * gz).toDouble())
                orientacionActual = if (velocidadGiro > 1.5) "Girando rapido" else "Orientacion estable"
            }
            Sensor.TYPE_STEP_DETECTOR -> {
                if (event.values[0] == 1.0f) {
                    pasosTotal++
                }
            }
        }
    }

    private fun guardarLecturaActual() {
        val formatoHora = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        val horaActual = formatoHora.format(Date())

        val nuevoRegistro = RegistroSensor28(
            hora = horaActual,
            pasos = pasosTotal,
            nivelMovimiento = nivelMovimientoActual,
            orientacion = orientacionActual
        )

        listaRegistros.add(0, nuevoRegistro)
        adaptador.notifyItemInserted(0)
        rvSensores.scrollToPosition(0)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
}