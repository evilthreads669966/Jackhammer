package com.evilthreads.jackhammer

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.evilthreads.jackhammerlib.checkOverlayPermission
import com.evilthreads.jackhammerlib.requestpermission

class MainActivity : AppCompatActivity() {

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        bruteForce()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bruteForce()
    }

    fun bruteForce(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(checkOverlayPermission())
                requestpermission(Manifest.permission.READ_CALENDAR){
                    Log.d("JACKJAMMER", "WE DID IT")
                }
        }
    }
}