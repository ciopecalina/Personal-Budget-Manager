package com.example.proiectpdmandroid

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.provider.Settings
import android.util.Log
import android.widget.Toast

private const val ACTION_PLAY: String = "com.example.proiectpdmandroid"

class ServiciuMediaPlayer : Service(), MediaPlayer.OnPreparedListener {
    val TAG = "MyService"

    init{
        Log.d(TAG,"Service is running")
    }
    override fun onBind(p0: Intent?): IBinder? = null

    lateinit var mMediaPlayer: MediaPlayer

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.d(TAG, "aaaa")
        mMediaPlayer = MediaPlayer.create(this, R.raw.service_song)
        mMediaPlayer.start()

        return START_STICKY

    }

    /** Called when MediaPlayer is ready */
    override fun onPrepared(mediaPlayer: MediaPlayer) {
        //mediaPlayer.start()
    }

    override fun onDestroy() {
        mMediaPlayer.stop()
    }
}