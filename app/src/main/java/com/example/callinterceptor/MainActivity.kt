package com.example.callinterceptor

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkAndRequestPermissions()

        startService(Intent(this, CallInterceptorService::class.java))

        val phoneNumber = intent.getStringExtra("phoneNumber")
        replaceFragmentBasedOnPhoneNumber(phoneNumber)
    }

    private fun replaceFragmentBasedOnPhoneNumber(phoneNumber: String?) {
        val fragment = when (phoneNumber) {
            "40030101" -> FragmentA()
            "031993927576" -> FragmentB()
            else -> FragmentC()
        }

        val args = Bundle().apply { putString("phoneNumber", phoneNumber) }
        fragment.arguments = args

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    private fun checkAndRequestPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val permissions = arrayOf(
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.PROCESS_OUTGOING_CALLS
            )

            val missingPermissions = ArrayList<String>()
            for (permission in permissions) {
                if (ContextCompat.checkSelfPermission(
                        this,
                        permission
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    missingPermissions.add(permission)
                }
            }

            if (missingPermissions.isNotEmpty()) {
                ActivityCompat.requestPermissions(
                    this,
                    missingPermissions.toTypedArray(),
                    PERMISSIONS_REQUEST_CODE
                )
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        stopService(Intent(this, CallInterceptorService::class.java))
    }

    companion object {
        private const val PERMISSIONS_REQUEST_CODE = 123
    }
}






