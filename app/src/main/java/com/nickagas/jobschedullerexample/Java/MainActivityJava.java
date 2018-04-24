package com.nickagas.jobschedullerexample.Java;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.nickagas.jobschedullerexample.R;

public class MainActivityJava extends AppCompatActivity {
    private final int JOB_ID = 101;
    private JobScheduler jobScheduler;
    private JobInfo jobInfo;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ComponentName contenrName = new ComponentName(this, MJobSchedulerJava.class);
        JobInfo.Builder builder = new JobInfo.Builder(JOB_ID, contenrName);
        builder.setPeriodic(15*60*1000);
       // builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
        builder.setPersisted(true);

        jobInfo = builder.build();
        jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
    }

    public void scheduleJob(View v){

        ComponentName componentName= new ComponentName(this,MJobSchedulerJava.class);
        JobInfo info = new JobInfo.Builder(JOB_ID+1,componentName)
                .setRequiresCharging(true)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setPersisted(true)
                .setPeriodic(15*60*1000)
                .build();
        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int resultcode = scheduler.schedule(info);
        if(resultcode == JobScheduler.RESULT_SUCCESS){
            Log.d("nikos","job scheduler");
        }else{
            Log.d("nikos","jobSchedulling failed");
        }

        //jobScheduler.schedule(jobInfo);
        Toast.makeText(this,"job Scheduled", Toast.LENGTH_SHORT).show();

    }

    public void clearJob(View v){
        Toast.makeText(this,"clearJob ", Toast.LENGTH_SHORT).show();

        jobScheduler.cancelAll();
    }
}
