package com.example.proiectpdmandroid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proiectpdmandroid.room.BugetDataClass

class IstoricAdapter : RecyclerView.Adapter<IstoricAdapter.MyViewHolder>(){

    private var bugetList = emptyList<BugetDataClass>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.history_item,
        parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
    val currentItem = bugetList[position]

        holder.tvLuna.setText(currentItem.luna)
        holder.tvSalariu.setText(currentItem.VSalariu.toString() + " lei")
        holder.tvInvestitii.setText(currentItem.VInvestitii.toString() + " lei")
        holder.tvVAltele.setText(currentItem.VAltele.toString() + " lei")
        holder.tvUtilitati.setText(currentItem.CUtilitati.toString() + " lei")
        holder.tvAlimente.setText(currentItem.CAlimente.toString() + " lei")
        holder.tvTransport.setText(currentItem.CTransport.toString() + " lei")
        holder.tvCumparaturi.setText(currentItem.CCumparaturi.toString() + " lei")
        holder.tvMedical.setText(currentItem.CMedical.toString() + " lei")
        holder.tvCAltele.setText(currentItem.CAltele.toString() + " lei")
        holder.tvTotalVenituri.setText("Total: " + currentItem.TotalVenituri.toString() + " lei")
        holder.tvTotalCheltuieli.setText("Total: " + currentItem.TotalCheltuieli.toString() + " lei")
        holder.tvEconomii.setText("Total: " + currentItem.Economii.toString() + " lei")

    }

    override fun getItemCount() = bugetList.size

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvLuna : TextView = itemView.findViewById(R.id.tvLunaInregistrare)
        val tvSalariu : TextView = itemView.findViewById(R.id.tvSalariu)
        val tvInvestitii : TextView = itemView.findViewById(R.id.tvInvestitii)
        val tvVAltele : TextView = itemView.findViewById(R.id.tvAlteleVenit)
        val tvUtilitati : TextView = itemView.findViewById(R.id.tvUtilitati)
        val tvAlimente : TextView = itemView.findViewById(R.id.tvAlimente)
        val tvTransport : TextView = itemView.findViewById(R.id.tvTransport)
        val tvCumparaturi : TextView = itemView.findViewById(R.id.tvCumparaturi)
        val tvMedical : TextView = itemView.findViewById(R.id.tvMedical)
        val tvCAltele : TextView = itemView.findViewById(R.id.tvAlteleCheltuieli)
        val tvTotalVenituri : TextView = itemView.findViewById(R.id.tvTotalVenit)
        val tvTotalCheltuieli : TextView = itemView.findViewById(R.id.tvTotalCheltuieli)
        val tvEconomii : TextView = itemView.findViewById(R.id.tvTotalEconomii)
    }

    fun setData(buget: List<BugetDataClass>){
        this.bugetList = buget
        notifyDataSetChanged()
    }
}