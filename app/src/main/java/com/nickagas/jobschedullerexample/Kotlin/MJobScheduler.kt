package com.nickagas.jobschedullerexample.Kotlin

import android.app.job.JobParameters
import android.app.job.JobService
import android.util.Log
import android.widget.Toast
import com.nickagas.jobschedullerexample.Java.MjobExecutorJava

class MJobSchedule : JobService() {

    private var mJobExecutor: MjobExecutor? = null

    override fun onStartJob(jobParameters: JobParameters): Boolean {
        Log.d("nikos", "onStartJob kotlin")

        mJobExecutor = object : MjobExecutor() {
            override fun onPostExecute(s: String) {
                Log.d("nikos", "onPostExecute kotlin")
                Toast.makeText(applicationContext, s, Toast.LENGTH_SHORT).show()
                jobFinished(jobParameters, false)
            }
        }

        mJobExecutor!!.execute()

        return true
    }

    override fun onStopJob(jobParameters: JobParameters): Boolean {
        mJobExecutor!!.cancel(true)


        return false
    }
}