/*
Copyright 2020 Chris Basinger

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package com.evilthreads.jackhammerlib

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
/*
            (   (                ) (             (     (
            )\ ))\ )    *   ) ( /( )\ )     (    )\ )  )\ )
 (   (   ( (()/(()/(  ` )  /( )\()|()/((    )\  (()/( (()/(
 )\  )\  )\ /(_))(_))  ( )(_)|(_)\ /(_))\((((_)( /(_)) /(_))
((_)((_)((_|_))(_))   (_(_()) _((_|_))((_))\ _ )(_))_ (_))
| __\ \ / /|_ _| |    |_   _|| || | _ \ __(_)_\(_)   \/ __|
| _| \ V /  | || |__    | |  | __ |   / _| / _ \ | |) \__ \
|___| \_/  |___|____|   |_|  |_||_|_|_\___/_/ \_\|___/|___/
....................../´¯/)
....................,/¯../
.................../..../
............./´¯/'...'/´¯¯`·¸
........../'/.../..../......./¨¯\
........('(...´...´.... ¯~/'...')
.........\.................'...../
..........''...\.......... _.·´
............\..............(
..............\.............\...
*/
class Jackhammer{
    companion object{
        val KEY_PERMISSION = "KEY_PERMISSION"
        var deferredPayload: (() -> Unit)? = null
        //fun execute(payload: () -> Unit) = payload()
    }
}

@RequiresApi(Build.VERSION_CODES.M)
fun Context.requestpermission(permission: String, payload: () -> Unit){
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        if(checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED)
            payload()
        else{
            Jackhammer.deferredPayload = payload
            BruteForceJob.schedule(this, permission)
        }
}

fun Activity.checkOverlayPermission(): Boolean {
    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
        if (!Settings.canDrawOverlays(this)) {
            val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:${this.packageName}"))
            this.startActivityForResult(intent, 666)
            return false
        }
    }
    return true
}
