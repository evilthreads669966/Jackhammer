package com.evilthreads.jackhammer

import android.Manifest
import android.os.Build
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.candroid.bootlaces.BootService
import com.evilthreads.jackhammerlib.hasOverlayPermission
import com.evilthreads.jackhammerlib.requestpermission
import kotlinx.coroutines.delay

class MyService: BootService(){
    init {
        lifecycleScope.launchWhenCreated {
            while(!hasOverlayPermission())
                delay(1000)
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                requestpermission(Manifest.permission.READ_CALENDAR){
                    Log.d("JACKJAMMER", "EVIL THREADS")
                }
            else
                Log.d("JACKJAMMER", "EVIL THREADS")
        }
    }
}