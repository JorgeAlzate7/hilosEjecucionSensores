package com.example.hilosdeejecucinysensores

// modelo para representacion de cada ejercicio en el menu principal
data class EjercicioInfo(
    val numero: Int,
    val titulo: String,
    val descripcion: String,
    val actividadClase: Class<*>
)