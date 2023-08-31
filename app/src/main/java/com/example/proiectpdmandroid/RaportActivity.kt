package com.example.proiectpdmandroid

import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.getSystemService
import com.example.proiectpdmandroid.service.NotifyService
import java.util.*

class RaportActivity : AppCompatActivity() {
    lateinit var myIntent: Intent
    lateinit var alarmManager: AlarmManager
    lateinit var pendingIntent: PendingIntent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_raport)


        myIntent = Intent(this, NotifyService::class.java)
        alarmManager = ((this.getSystemService(Context.ALARM_SERVICE) as? AlarmManager)!!)
        pendingIntent = PendingIntent.getService(this, 0, myIntent, 0)
    }


    fun btnStartService(view: View) {



//        if (pendingIntent != null && alarmManager != null) {
//            alarmManager.cancel(pendingIntent)
//        }

        val calendar = Calendar.getInstance()
       // calendar.add(Calendar.SECOND, 15)
       //calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)+1)
       // alarmManager?.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)

        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.HOUR, 0)
        calendar.set(Calendar.AM_PM, Calendar.AM)
        calendar.add(Calendar.DAY_OF_MONTH, 1)
        alarmManager?.setRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis,1000*60*60*24*30, pendingIntent)

        Intent(this, NotifyService::class.java).also {
            startService(it)
        }
    }

    fun btnStopService(view: View) {
            alarmManager?.cancel(pendingIntent)

        Intent(this, NotifyService::class.java).also {
            stopService(it)
        }
    }


}