package com.example.proiectpdmandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class VenituriActivity : AppCompatActivity() {
    lateinit var etSalariu : EditText
    lateinit var etInvestitii : EditText
    lateinit var etAltele : EditText
    val buget : Buget = Buget

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_venituri)

        etSalariu = findViewById(R.id.etVSalariu)
        etInvestitii = findViewById(R.id.etVInvestitii)
        etAltele = findViewById(R.id.etVAltele)

    }

    fun checkEditTextForEmpty(){
        etSalariu.text.toString().trim().isEmpty().apply {
            if (this)
            etSalariu.setText("0")
        }

        etInvestitii.text.toString().trim().isEmpty().apply {
            if (this)
            etInvestitii.setText("0")
        }

        etAltele.text.toString().trim().isEmpty().apply {
            if (this)
            etAltele.setText("0")
        }
    }

    fun extractDataAndAssignToObject(){
        buget.VSalariu = etSalariu.text.toString().toDouble()
        buget.VInvestitii = etInvestitii.text.toString().toDouble()
        buget.VAltele = etAltele.text.toString().toDouble()

        buget.TotalVenituri = buget.VSalariu + buget.VInvestitii + buget.VAltele
    }

    fun btnCheltuieli(view: View) {
        checkEditTextForEmpty()

        extractDataAndAssignToObject()

        val intent = Intent(this, CheltuieliActivity::class.java)
        startActivity(intent)
    }

    fun resetare(view: View) {
        etSalariu.setText("")
        etInvestitii.setText("")
        etAltele.setText("")

    }
}