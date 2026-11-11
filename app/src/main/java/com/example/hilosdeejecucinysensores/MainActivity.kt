package com.example.hilosdeejecucinysensores

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvEjercicios: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvEjercicios = findViewById(R.id.rvEjerciciosMenu)
        rvEjercicios.layoutManager = LinearLayoutManager(this)

        val lista = obtenerListaEjercicios()
        rvEjercicios.adapter = MenuAdapter(lista) { ejercicio ->
            val intent = Intent(this, ejercicio.actividadClase)
            startActivity(intent)
        }
    }

    private fun obtenerListaEjercicios(): List<EjercicioInfo> {
        return listOf(
            EjercicioInfo(1, "Hilo Contador 1 al 10", "Conteo con Thread.sleep y runOnUiThread", Ejercicio1Activity::class.java),
            EjercicioInfo(2, "Carga Progresiva", "ProgressBar del 0 al 100 actualizando cada 100 ms", Ejercicio2Activity::class.java),
            EjercicioInfo(3, "3 Hilos Simultaneos", "Impresion de pares impares y letras al mismo tiempo", Ejercicio3Activity::class.java),
            EjercicioInfo(4, "Clase MiTarea", "Suma de los primeros N numeros en hilo secundario", Ejercicio4Activity::class.java),
            EjercicioInfo(5, "Corrutina de 5 Segundos", "Boton que lanza corrutina con delay cada segundo", Ejercicio5Activity::class.java),
            EjercicioInfo(6, "Simulacion de Sensor", "Generador de datos aleatorios con corrutina e inicio fin", Ejercicio6Activity::class.java),
            EjercicioInfo(7, "Eventos Tactiles", "Deteccion de ACTION_DOWN ACTION_MOVE y ACTION_UP", Ejercicio7Activity::class.java),
            EjercicioInfo(8, "Coordenadas X e Y", "Visualizacion de posicion del toque en tiempo real", Ejercicio8Activity::class.java),
            EjercicioInfo(9, "Vista de Color Personalizada", "Cambio de color de fondo al tocar la vista", Ejercicio9Activity::class.java),
            EjercicioInfo(10, "Arrastre de Pelota", "Imagen que se arrastra libremente con el dedo", Ejercicio10Activity::class.java),
            EjercicioInfo(11, "Lienzo de Dibujo", "Trazo libre guardado y redibujado", Ejercicio11Activity::class.java),
            EjercicioInfo(12, "GestureDetector", "Reconocimiento de Tap LongPress y Fling", Ejercicio12Activity::class.java),
            EjercicioInfo(13, "Posicion Multitactil", "Conteo de dedos y coordenadas de cada puntero", Ejercicio13Activity::class.java),
            EjercicioInfo(14, "Dibujo Multitactil Multicolor", "Cada dedo dibuja con un color distinto", Ejercicio14Activity::class.java),
            EjercicioInfo(15, "Pellizco y Zoom (Pinch)", "Escalado de imagen con ScaleGestureDetector", Ejercicio15Activity::class.java),
            EjercicioInfo(16, "Rotacion de Vista", "Giro de vista segun el angulo entre 2 dedos", Ejercicio16Activity::class.java),
            EjercicioInfo(17, "Lienzo de Puntos Dinamicos", "Puntos de colores por dedo que desaparecen al soltar", Ejercicio17Activity::class.java),
            EjercicioInfo(18, "Control Total de Imagen", "Arrastre escala y rotacion simultanea", Ejercicio18Activity::class.java),
            EjercicioInfo(19, "Sensor de Proximidad", "Fondo rojo si esta cerca y verde si esta lejos", Ejercicio19Activity::class.java),
            EjercicioInfo(20, "Sensor de Luz (Lux)", "Medicion de luz ambiental y cambio de color", Ejercicio20Activity::class.java),
            EjercicioInfo(21, "Orientacion del Dispositivo", "Azimut pitch y roll con acelerometro y magnetometro", Ejercicio21Activity::class.java),
            EjercicioInfo(22, "Brujula Magnetica", "Aguja apuntando al Norte magnetico", Ejercicio22Activity::class.java),
            EjercicioInfo(23, "Mover Pelota por Inclinacion", "Acelerometro guiando la posicion de un circulo", Ejercicio23Activity::class.java),
            EjercicioInfo(24, "Contador de Pasos", "Sensor TYPE_STEP_COUNTER o DETECTOR", Ejercicio24Activity::class.java),
            EjercicioInfo(25, "Proximidad + Luz", "Combinacion: oscuro y cerca (negro) o claro y lejos (blanco)", Ejercicio25Activity::class.java),
            EjercicioInfo(26, "Detector de Agitado (Shake)", "Calculo del vector de aceleracion y umbral", Ejercicio26Activity::class.java),
            EjercicioInfo(27, "Posicion del Telefono", "Deteccion: boca arriba boca abajo o vertical", Ejercicio27Activity::class.java),
            EjercicioInfo(28, "Registro de Sensores en RecyclerView", "Pasos movimiento y orientacion guardados en lista", Ejercicio28Activity::class.java)
        )
    }
}