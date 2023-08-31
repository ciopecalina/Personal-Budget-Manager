package com.example.proiectpdmandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContentProviderCompat.requireContext

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.proiectpdmandroid.room.BugetDataClass
import com.example.proiectpdmandroid.room.BugetDatabase
import com.example.proiectpdmandroid.room.BugetViewModel
import com.example.proiectpdmandroid.widget.BugetAppWidget
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CalculatorActivity : AppCompatActivity() {
    lateinit var tvTotalCheltuieli : TextView
    lateinit var tvTotalVenituri : TextView
    lateinit var tvEconomii : TextView
    val buget : Buget = Buget
    private lateinit var bugetViewModel : BugetViewModel
    var countLuna = 0
    private lateinit var database : BugetDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        tvTotalCheltuieli = findViewById(R.id.tvValoareTotalCheluieli)
        tvTotalVenituri = findViewById(R.id.tvValoareTotalaVenituri)
        tvEconomii = findViewById(R.id.tvValoareEconomii)

        buget.Economii = buget.TotalVenituri - buget.TotalCheltuieli

        tvTotalVenituri.setText(buget.TotalVenituri.toString() + " lei")
        tvTotalCheltuieli.setText(buget.TotalCheltuieli.toString() + " lei")

        tvEconomii.setText(buget.Economii.toString() + " lei")

        bugetViewModel = ViewModelProvider(this).get(BugetViewModel::class.java)

        database = BugetDatabase.getDatabase(this)

        getCountLuna(buget.luna)

        findViewById<TextView>(R.id.tvTextTotalEconomii).setText("Luna: " + buget.luna)
    }

    fun btnMeniuPrincipal(view: View) {
        val intent = Intent(this, MainActivity::class.java)

        val intentUpdate = Intent(applicationContext, BugetAppWidget::class.java)
        intentUpdate.action = "update"
        applicationContext.sendBroadcast(intentUpdate)

        startActivity(intent)
    }

    fun btnSalvareDb(view: View) {
    insertDataToDatabase()

    }

    private fun getCountLuna(luna : String){
        CoroutineScope(Dispatchers.IO).launch{
            countLuna = database.bugetDao().getCountLuna(luna)
        }
    }

    private fun insertDataToDatabase(){
        var bugetData : BugetDataClass = BugetDataClass(buget.luna, buget.VSalariu,  buget.VInvestitii, buget.VAltele, buget.CUtilitati, buget.CAlimente, buget.CTransport, buget.CCumparaturi, buget.CMedical, buget.CAltele, buget.TotalVenituri, buget.TotalCheltuieli, buget.Economii)

        if (countLuna == 0){
            bugetViewModel.addBuget(bugetData)
            Toast.makeText(applicationContext,"Inregistrare adaugata!", Toast.LENGTH_SHORT).show()

            //actualizare widget cu semnal prin broadcast
            val intentUpdate = Intent(applicationContext, BugetAppWidget::class.java)
            intentUpdate.action = "update"
            applicationContext.sendBroadcast(intentUpdate)
        }else{
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Luna Existenta")
            builder.setMessage("Luna " + buget.luna + " exista deja in baza de date. Doriti sa o suprascrieti?")

            builder.setPositiveButton("Da") { dialog, which ->
                bugetViewModel.updateLuna(bugetData)
                Toast.makeText(applicationContext,"Luna actualizata!", Toast.LENGTH_SHORT).show()
                //actualizare widget cu semnal prin broadcast
                val intentUpdate = Intent(applicationContext, BugetAppWidget::class.java)
                intentUpdate.action = "update"
                applicationContext.sendBroadcast(intentUpdate)
            }
            builder.setNegativeButton("Nu") { dialog, which ->
                Toast.makeText(applicationContext,"Actualizare anulata!", Toast.LENGTH_SHORT).show()
            }
            builder.show()
        }
    }
}