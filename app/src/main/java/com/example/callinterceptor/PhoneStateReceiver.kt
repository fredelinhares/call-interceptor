package com.example.callinterceptor

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.telephony.TelephonyManager
import android.util.Log

internal class PhoneStateReceiver(private val mainActivity: MainActivity) : BroadcastReceiver() {

    private var filters: IntentFilter = IntentFilter()

    init {
        filters.addAction(TelephonyManager.ACTION_PHONE_STATE_CHANGED)
        filters.addAction(Intent.ACTION_NEW_OUTGOING_CALL)
    }

    fun register(context: Context) = context.registerReceiver(this, filters)

    fun unregister(context: Context) = context.unregisterReceiver(this)

    override fun onReceive(context: Context, intent: Intent) {
        var phoneNumber = ""
        val a = intent.action
        if (a == TelephonyManager.ACTION_PHONE_STATE_CHANGED) {
            phoneNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER) ?: ""
        }
        if (a == Intent.ACTION_NEW_OUTGOING_CALL) {
            phoneNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER) ?: ""
        }

        Log.d("123456789", phoneNumber)

        val fragment = when (phoneNumber) {
            "40030101" -> FragmentA()
            "456" -> FragmentB()
            else -> FragmentC()
        }

         val args = Bundle().apply {
            putString("phoneNumber", phoneNumber)
        }
        fragment.arguments = args

        mainActivity.replaceFragment(fragment)
    }
}