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

import android.app.job.JobInfo
import android.app.job.JobParameters
import android.app.job.JobScheduler
import android.app.job.JobService
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.PersistableBundle
import androidx.annotation.RequiresApi
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
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class BruteForceJob: JobService() {
    companion object{
        fun schedule(ctx: Context, permission: String){
            val builder = JobInfo.Builder(666, ComponentName(ctx, BruteForceJob::class.java))
            builder.setPersisted(true)
/*            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                builder.setImportantWhileForeground(true)
            }*/
            val bundle = PersistableBundle().apply {
                putString(Jackhammer.KEY_PERMISSION, permission)
            }
            builder.setExtras(bundle)
            builder.setMinimumLatency(10000)
            builder.setOverrideDeadline(20000)
            (ctx.getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler).schedule(builder.build())
        }
    }
    override fun onStartJob(bundle: JobParameters?): Boolean {
        val permission = bundle?.extras?.getString(Jackhammer.KEY_PERMISSION)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            if(checkSelfPermission(permission!!) == PackageManager.PERMISSION_GRANTED){
                Jackhammer.deferredPayload!!.invoke()
                jobFinished(bundle, false)
            }
            else{
                val intent = Intent().apply {
                    setClass(this@BruteForceJob, PermissionActivity::class.java)
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    putExtra(Jackhammer.KEY_PERMISSION, permission)
                }
                startActivity(intent)
                jobFinished(bundle, true)
            }
        return true
    }

    override fun onStopJob(bundle: JobParameters?) = true
}