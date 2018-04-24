package com.nickagas.jobschedullerexample.Kotlin

import android.app.job.JobInfo
import android.os.AsyncTask
import com.nickagas.jobschedullerexample.Java.MJobSchedulerJava


open class MjobExecutor : AsyncTask<Void, Void, String>() {


    override fun doInBackground(vararg voids: Void): String {
        return "Long process finished Java"
    }
}
