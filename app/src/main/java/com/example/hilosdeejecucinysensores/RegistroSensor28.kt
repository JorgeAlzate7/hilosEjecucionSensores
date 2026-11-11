package com.example.hilosdeejecucinysensores

// modelo de datos para registrar lecturas de los sensores en el recyclerview
data class RegistroSensor28(
    val hora: String,
    val pasos: Int,
    val nivelMovimiento: String,
    val orientacion: String
)