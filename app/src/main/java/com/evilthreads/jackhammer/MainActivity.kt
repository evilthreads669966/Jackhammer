package com.evilthreads.jackhammer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.candroid.bootlaces.bootService
import com.evilthreads.jackhammerlib.checkOverlayPermission

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bootService(this){
            service = MyService::class
        }
    }

    override fun onResume() {
        super.onResume()
        checkOverlayPermission()
    }
}