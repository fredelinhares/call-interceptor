package com.example.callinterceptor

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.telephony.TelephonyManager
import android.util.Log


internal class PhoneStateReceiver : BroadcastReceiver() {

    private var filters: IntentFilter = IntentFilter()

    init {
        filters.addAction(TelephonyManager.ACTION_PHONE_STATE_CHANGED)
        filters.addAction(Intent.ACTION_NEW_OUTGOING_CALL)
    }

    fun register(context: Context) = context.registerReceiver(this, filters)

    fun unregister(context: Context) = context.unregisterReceiver(this)

    override fun onReceive(context: Context, intent: Intent) {
        var phoneNumer = ""
        val a = intent.action
        if (a == TelephonyManager.ACTION_PHONE_STATE_CHANGED) {
            phoneNumer = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER) ?: ""
        }
        if (a == Intent.ACTION_NEW_OUTGOING_CALL) {
            phoneNumer = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER) ?: ""
        }
        Log.d("123456789", phoneNumer)
    }
}
