package com.nickagas.jobschedullerexample.Java;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;
import android.widget.Toast;

public class MJobSchedulerJava extends JobService{

        private MjobExecutorJava mJobExecutor;
    @Override
    public boolean onStartJob(final JobParameters jobParameters) {
        Log.d("nikos", "onStartJob java");
        mJobExecutor = new MjobExecutorJava(){
            @Override
            protected void onPostExecute(String s) {
                Log.d("nikos", "onPostExecute java");
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
                jobFinished(jobParameters,false);
            }
        };

        mJobExecutor.execute();

        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        Log.d("nikos", "onStopJob java");
        mJobExecutor.cancel(true);


        return false;
    }
}
