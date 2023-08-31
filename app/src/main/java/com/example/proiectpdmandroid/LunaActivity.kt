package com.example.proiectpdmandroid

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton

class LunaActivity : AppCompatActivity() {
    //lateinit var buget : Buget
    val buget : Buget = Buget

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_luna)


        val spinner: Spinner = findViewById(R.id.spinnerLuna)
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.luni,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

    }

    fun btnCalculatorVenituri(view: View) {
        val spinner: Spinner = findViewById(R.id.spinnerLuna)
        buget.luna = spinner.selectedItem.toString()
        val intent = Intent(this, VenituriActivity::class.java)
        startActivity(intent)

    }


}