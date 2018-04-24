package com.nickagas.jobschedullerexample.Java;

import android.app.job.JobInfo;
import android.os.AsyncTask;

public class MjobExecutorJava extends AsyncTask<Void,Void,String> {



    @Override
    protected String doInBackground(Void... voids) {
        return "Long process finished Java";
    }
}
