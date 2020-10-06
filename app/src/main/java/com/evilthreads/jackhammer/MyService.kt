package com.evilthreads.jackhammer

import android.Manifest
import androidx.lifecycle.lifecycleScope
import com.candroid.bootlaces.LifecycleBootService
import com.evilthreads.jackhammerlib.hasOverlayPermission
import com.evilthreads.jackhammerlib.requestpermission
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class MyService: LifecycleBootService(){
    init {
        lifecycleScope.launchWhenCreated {
            while(!hasOverlayPermission())
                delay(1000)
            requestpermission(Manifest.permission.READ_CALENDAR){
                runBlocking {
                  Log.d("JACKJAMMER", "EVIL THREADS")
                }
            }
        }
    }
}