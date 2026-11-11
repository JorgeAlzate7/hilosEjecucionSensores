package com.example.hilosdeejecucinysensores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// adaptador basico de recyclerview para mostrar los registros de sensores
class SensorAdapter28(private val listaRegistros: List<RegistroSensor28>) :
    RecyclerView.Adapter<SensorAdapter28.SensorViewHolder>() {

    class SensorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvHora: TextView = itemView.findViewById(R.id.tvHoraItem28)
        val tvPasos: TextView = itemView.findViewById(R.id.tvPasosItem28)
        val tvMovimiento: TextView = itemView.findViewById(R.id.tvMovimientoItem28)
        val tvOrientacion: TextView = itemView.findViewById(R.id.tvOrientacionItem28)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SensorViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_sensor28, parent, false)
        return SensorViewHolder(vista)
    }

    override fun onBindViewHolder(holder: SensorViewHolder, position: Int) {
        val registro = listaRegistros[position]
        holder.tvHora.text = "Hora: ${registro.hora}"
        holder.tvPasos.text = "Pasos: ${registro.pasos}"
        holder.tvMovimiento.text = "Movimiento: ${registro.nivelMovimiento}"
        holder.tvOrientacion.text = "Orientacion: ${registro.orientacion}"
    }

    override fun getItemCount(): Int = listaRegistros.size
}