package com.example.proiectpdmandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView

class ListaBanciActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_banci)

        var lvListaBanci=findViewById<ListView>(R.id.lvListaBanci)

        var lista= mutableListOf<Banca>()
        lista.add(Banca(getString(R.string.alpha_bank),"Sediu central:\n" +getString(R.string.sediu_alpha_bank),R.drawable.alphabank))
        lista.add(Banca(getString(R.string.bcr),"Sediu central:\n"+getString(R.string.sediu_bcr),R.drawable.bcr))
        lista.add(Banca(getString(R.string.brd),"Sediu central:\n"+getString(R.string.sediu_brd),R.drawable.brd))
        lista.add(Banca(getString(R.string.raiffeisen),"Sediu central:\n"+getString(R.string.sediu_raiffeisen),R.drawable.raiffeisen))

        lvListaBanci.adapter=BancaAdapter(this, R.layout.lv_lista_banci_item, lista)

        lvListaBanci.setOnItemClickListener{ parent: AdapterView<*>, view: View, position:Int, id:Long ->
                val intent = Intent(this, MapsActivity::class.java)
                intent.putExtra("denumire", lista[position].denumire)
                startActivity(intent)
        }

    }
}