package com.example.proiectpdmandroid.service


import android.app.*
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.proiectpdmandroid.MainActivity
import com.example.proiectpdmandroid.R
import com.example.proiectpdmandroid.RaportActivity

class NotifyService : Service() {
    override fun onBind(p0: Intent?): IBinder? = null

    init {
        Log.e("service", "running")
    }


    //private val channelId = "Notification from Service"
    private val channelId = "Service Notification"
    private var isShowingServiceNotification : Boolean = true

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()

        if (Build.VERSION.SDK_INT >= 26) {
            val channel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel(channelId, "Alarms", NotificationManager.IMPORTANCE_DEFAULT)
            } else {
                TODO("VERSION.SDK_INT < O")
            }
            (getSystemService(NOTIFICATION_SERVICE) as NotificationManager).createNotificationChannel(channel)
        }

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        if (isShowingServiceNotification){
            showServiceNotification()
            isShowingServiceNotification = false
        }else{
            showAlarmNotification()
        }

        return START_NOT_STICKY
    }

    fun showServiceNotification(){
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        val notification: Notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle("Serviciu Reminder")
            .setSmallIcon(R.drawable.calendar_icon)
            .setContentIntent(pendingIntent)
            .build()
        startForeground(2, notification)
    }


    fun showAlarmNotification(){
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        val builder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.calendar_icon)
            .setContentTitle("Manager Buget Personal")
            .setContentText("Nu uita sa iti calculezi economiile!")
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)


        with(NotificationManagerCompat.from(this)){
            notify(1, builder.build())
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("service", "Stopped")
    }

}
