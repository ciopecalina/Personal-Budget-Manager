package com.example.proiectpdmandroid

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView


class BancaAdapter  (var myContext: Context, var resources:Int, var items:List<Banca> ): ArrayAdapter<Banca>(myContext, resources, items)
{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(myContext)
        val view: View =layoutInflater.inflate(resources, null)
        val ivLogoBanca: ImageView =view.findViewById(R.id.ivLogoBanca)
        val tvDenumireBanca: TextView =view.findViewById(R.id.tvDenumireBanca)
        val tvAdresaBanca: TextView =view.findViewById(R.id.tvAdresaBanca)

        var mItem:Banca=items[position]
        ivLogoBanca.setImageDrawable(context.resources.getDrawable(mItem.img))
        tvAdresaBanca.text=mItem.adresa
        tvDenumireBanca.text=mItem.denumire

        return view
    }
}