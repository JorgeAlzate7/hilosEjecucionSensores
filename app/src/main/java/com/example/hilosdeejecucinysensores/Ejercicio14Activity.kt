package com.example.hilosdeejecucinysensores

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Ejercicio14Activity : AppCompatActivity() {

    private lateinit var lienzo: LienzoMultitactil14
    private lateinit var btnLimpiar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio14)

        lienzo = findViewById(R.id.lienzo14)
        btnLimpiar = findViewById(R.id.btnLimpiar14)

        btnLimpiar.setOnClickListener {
            lienzo.limpiar()
        }
    }
}