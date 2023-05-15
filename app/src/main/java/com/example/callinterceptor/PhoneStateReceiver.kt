package com.example.callinterceptor

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.telephony.TelephonyManager
import android.util.Log

internal class PhoneStateReceiver() : BroadcastReceiver() {

    private var filters: IntentFilter = IntentFilter()

    init {
        filters.addAction(TelephonyManager.ACTION_PHONE_STATE_CHANGED)
        filters.addAction(Intent.ACTION_NEW_OUTGOING_CALL)
    }

    fun register(context: Context) = context.registerReceiver(this, filters)

    fun unregister(context: Context) = context.unregisterReceiver(this)

    override fun onReceive(context: Context, intent: Intent) {
        var phoneNumber = ""
        val action = intent.action
        if (action == TelephonyManager.ACTION_PHONE_STATE_CHANGED) {
            phoneNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER) ?: ""
        }
        if (action == Intent.ACTION_NEW_OUTGOING_CALL) {
            phoneNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER) ?: ""
        }

        Log.d("123456789", phoneNumber)
        redirectToApp(context, phoneNumber)
    }

    private fun redirectToApp(context: Context, phoneNumber: String) {
        val mainActivityIntent = Intent(context, MainActivity::class.java)
        mainActivityIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        mainActivityIntent.putExtra("phoneNumber", phoneNumber)
        context.startActivity(mainActivityIntent)
    }
}