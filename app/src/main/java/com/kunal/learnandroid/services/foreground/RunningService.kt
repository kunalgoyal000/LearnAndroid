package com.kunal.learnandroid.services.foreground

import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.kunal.learnandroid.R

class RunningService : Service() {

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            Actions.START.toString() -> start()
            Actions.STOP.toString() -> stopSelf()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    @SuppressLint("ForegroundServiceType")
    private fun start() {
        val notification = NotificationCompat.Builder(this, "running_channel")
            .setSmallIcon(R.drawable.logo)
            .setContentTitle("Run is active")
            .setContentText("Elapsed Time: 00:25")
            .build()

        startForeground(1, notification)
    }

    enum class Actions {
        START, STOP
    }
}