package com.nickagas.jobschedullerexample.Kotlin

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.nickagas.jobschedullerexample.Java.MJobSchedulerJava
import com.nickagas.jobschedullerexample.R

class MainActivity : AppCompatActivity() {

    private val JOB_ID = 101
    private var jobScheduler: JobScheduler? = null
    private var jobInfo: JobInfo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val contenrName = ComponentName(this, MJobSchedulerJava::class.java)
        val builder: JobInfo.Builder = JobInfo.Builder(JOB_ID,contenrName)

        builder.setPeriodic(5000)
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
        builder.setPersisted(true)
        jobInfo = builder.build()
        jobScheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler


    }

    fun scheduleJob(view: View){

        val componentName = ComponentName(this, MJobSchedule::class.java)
        val info = JobInfo.Builder(JOB_ID + 1, componentName)
                .setRequiresCharging(true)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setPersisted(true)
                .setPeriodic((15 * 60 * 1000).toLong())
                .build()
        val scheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        val resultcode = scheduler.schedule(info)
        if (resultcode == JobScheduler.RESULT_SUCCESS) {
            Log.d("nikos", "job scheduler")
        } else {
            Log.d("nikos", "jobSchedulling failed")
        }

        //jobScheduler.schedule(jobInfo);
        Toast.makeText(this, "job Scheduled", Toast.LENGTH_SHORT).show()

        Log.d("Nikos Says", "scheduled Job")
        jobScheduler!!.schedule(jobInfo)

    }


    fun clearJob(view: View){
        Log.d("Nikos Says", "clear Job")
        jobScheduler!!.cancel(JOB_ID)
    }
}
