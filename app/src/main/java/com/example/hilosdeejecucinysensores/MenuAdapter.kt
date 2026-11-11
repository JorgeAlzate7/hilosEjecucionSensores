package com.example.hilosdeejecucinysensores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// adaptador para la lista del menu principal
class MenuAdapter(
    private val listaEjercicios: List<EjercicioInfo>,
    private val onItemClick: (EjercicioInfo) -> Unit
) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitulo: TextView = itemView.findViewById(R.id.tvTituloEjercicio)
        val tvDescripcion: TextView = itemView.findViewById(R.id.tvDescripcionEjercicio)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_menu_ejercicio, parent, false)
        return MenuViewHolder(vista)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val ejercicio = listaEjercicios[position]
        holder.tvTitulo.text = "Ejercicio ${ejercicio.numero}: ${ejercicio.titulo}"
        holder.tvDescripcion.text = ejercicio.descripcion

        holder.itemView.setOnClickListener {
            onItemClick(ejercicio)
        }
    }

    override fun getItemCount(): Int = listaEjercicios.size
}