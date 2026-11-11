package com.example.hilosdeejecucinysensores

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

// clase mitarea que ejecuta la operacion matematica en un hilo secundario
class MiTarea(private val limiteN: Int, private val alFinalizar: (Long) -> Unit) : Thread() {
    override fun run() {
        var suma: Long = 0
        for (i in 1..limiteN) {
            suma += i
            try {
                // simulamos un pequeño tiempo de procesamiento
                sleep(10)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
        // devolvemos el resultado mediante una funcion de callback
        alFinalizar(suma)
    }
}

class Ejercicio4Activity : AppCompatActivity() {

    // declaracion de variables para la interfaz
    private lateinit var etNumero: EditText
    private lateinit var btnCalcular: Button
    private lateinit var tvResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio4)

        // inicializacion de las vistas
        etNumero = findViewById(R.id.etNumero4)
        btnCalcular = findViewById(R.id.btnCalcular4)
        tvResultado = findViewById(R.id.tvResultado4)

        btnCalcular.setOnClickListener {
            val texto = etNumero.text.toString()
            if (texto.isNotEmpty()) {
                val n = texto.toInt()
                tvResultado.text = "Calculando..."

                // instanciamos la clase mitarea y la iniciamos
                val tarea = MiTarea(n) { resultado ->
                    runOnUiThread {
                        tvResultado.text = "La suma de los primeros $n numeros es: $resultado"
                    }
                }
                tarea.start()
            }
        }
    }
}