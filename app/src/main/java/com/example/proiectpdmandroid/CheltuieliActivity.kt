package com.example.proiectpdmandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class CheltuieliActivity : AppCompatActivity() {
    lateinit var etUtilitati : EditText
    lateinit var etAlimente : EditText
    lateinit var etTransport : EditText
    lateinit var etCumparaturi : EditText
    lateinit var etMedical : EditText
    lateinit var etAltele : EditText
    val buget : Buget = Buget

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheltuieli)

        etUtilitati = findViewById(R.id.etCUtilitati)
        etAlimente = findViewById(R.id.etCAlimente)
        etTransport = findViewById(R.id.etCTransport)
        etCumparaturi = findViewById(R.id.etCCumparaturi)
        etMedical = findViewById(R.id.etCMedical)
        etAltele = findViewById(R.id.etCAltele)

    }

    fun checkEditTextForEmpty(){
        etUtilitati.text.toString().trim().isEmpty().apply {
            if (this)
            etUtilitati.setText("0")
        }

        etAlimente.text.toString().trim().isEmpty().apply {
            if (this)
            etAlimente.setText("0")
        }

        etTransport.text.toString().trim().isEmpty().apply {
            if (this)
            etTransport.setText("0")
        }

        etCumparaturi.text.toString().trim().isEmpty().apply {
            if (this)
            etCumparaturi.setText("0")
        }

        etMedical.text.toString().trim().isEmpty().apply {
            if (this)
            etMedical.setText("0")
        }

        etAltele.text.toString().trim().isEmpty().apply {
            if (this)
            etAltele.setText("0")
        }

    }

    fun extractDataAndAssignToObject(){
        buget.CUtilitati = etUtilitati.text.toString().toDouble()
        buget.CAlimente = etAlimente.text.toString().toDouble()
        buget.CTransport = etTransport.text.toString().toDouble()
        buget.CCumparaturi = etCumparaturi.text.toString().toDouble()
        buget.CMedical = etMedical.text.toString().toDouble()
        buget.CAltele = etAltele.text.toString().toDouble()

        buget.TotalCheltuieli = buget.CAlimente + buget.CTransport + buget.CCumparaturi + buget.CMedical + buget.CUtilitati + buget.CAltele
    }

    fun btnCalculator(view: View) {
        checkEditTextForEmpty()

        extractDataAndAssignToObject()

        val intent = Intent(this, CalculatorActivity::class.java)
        startActivity(intent)
    }



    fun resetare(view: View) {
        etUtilitati.setText("")
        etAlimente.setText("")
        etTransport.setText("")
        etCumparaturi.setText("")
        etMedical.setText("")
        etAltele.setText("")
    }
}