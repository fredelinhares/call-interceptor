package com.example.callinterceptor

import android.app.Service
import android.content.Intent
import android.os.IBinder

class CallInterceptorService : Service() {

    private val phoneStateReceiver = PhoneStateReceiver()

    override fun onCreate() {
        super.onCreate()
        phoneStateReceiver.register(this)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        phoneStateReceiver.unregister(this)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}