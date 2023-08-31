package com.example.proiectpdmandroid

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.proiectpdmandroid.room.BugetViewModel
import com.example.proiectpdmandroid.service.NotifyService
import com.example.proiectpdmandroid.widget.BugetAppWidget
import java.util.*

class SetariActivity : AppCompatActivity() {
    lateinit var myIntent: Intent
    lateinit var alarmManager: AlarmManager
    lateinit var pendingIntent: PendingIntent
    lateinit var bugetViewModel : BugetViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setari)

        myIntent = Intent(this, NotifyService::class.java)
        alarmManager = ((this.getSystemService(Context.ALARM_SERVICE) as? AlarmManager)!!)
        pendingIntent = PendingIntent.getService(this, 0, myIntent, 0)
        bugetViewModel = ViewModelProvider(this).get(BugetViewModel::class.java)

        val btnStartService: Button
        btnStartService = findViewById(R.id.btnSetariStart)

        val btnStopService: Button
        btnStopService = findViewById(R.id.btnSetariStop)

        btnStartService.setOnClickListener {
            Intent(this, ServiciuMediaPlayer::class.java).also {
                startService(it)
            }

            Toast.makeText(this, "Melodie pornita", Toast.LENGTH_SHORT).show()
        }

        btnStopService.setOnClickListener {
            Intent(this, ServiciuMediaPlayer::class.java).also {
                stopService(it)
            }
            Toast.makeText(this, "Melodie oprita", Toast.LENGTH_SHORT).show()
        }
    }

    fun btnStartNotificare(view: View) {

        val calendar = Calendar.getInstance()

        //15 secunde
//         calendar.add(Calendar.SECOND, 5)
//        //calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)+1)
//         alarmManager?.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)

        //Lunar
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.HOUR, 0)
        calendar.set(Calendar.AM_PM, Calendar.AM)
        calendar.add(Calendar.DAY_OF_MONTH, 1)
        alarmManager?.setRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, 1000 * 60 * 60 * 24 * 30, pendingIntent)

        Intent(this, NotifyService::class.java).also {
            startService(it)
        }
        Toast.makeText(this, "Alarma pornita", Toast.LENGTH_SHORT).show()
    }

    fun btnStopNotificare(view: View) {
        alarmManager?.cancel(pendingIntent)

        Intent(this, NotifyService::class.java).also {
            stopService(it)
        }

        Toast.makeText(this, "Alarma oprita", Toast.LENGTH_SHORT).show()
    }

    fun btnStergeDatele(view: View) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Stergere Baza de date")
        builder.setMessage("Doriti sa stergeti toate datele din baza de date?")

        builder.setPositiveButton("Da") { dialog, which ->
            bugetViewModel.deleteDb()

            //actualizare widget cu semnal prin broadcast
            val intentUpdate = Intent(applicationContext, BugetAppWidget::class.java)
            intentUpdate.action = "update"
            applicationContext.sendBroadcast(intentUpdate)

            Toast.makeText(applicationContext,"Datele au fost sterse!", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("Nu") { dialog, which ->
            Toast.makeText(applicationContext,"Actiune anulata!", Toast.LENGTH_SHORT).show()
        }
        builder.show()
    }


}