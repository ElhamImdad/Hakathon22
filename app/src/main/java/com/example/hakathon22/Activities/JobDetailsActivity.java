package com.example.hakathon22.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hakathon22.R;
import com.example.hakathon22.Models.JobsDetails;
import com.example.hakathon22.rest.RestJobs;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JobDetailsActivity extends AppCompatActivity {


    TextView job_details__title , job_details__company_name , job_details__desc , job_details__type , job_details__startdate , job_details__enddate;

    List<JobsDetails> jobsDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_detailes);

        job_details__title = (TextView)findViewById(R.id.job_details__title);
        job_details__company_name = (TextView)findViewById(R.id.job_details__company_name);
        job_details__desc = (TextView)findViewById(R.id.job_details__desc);
        job_details__type = (TextView)findViewById(R.id.job_details__type);
        job_details__startdate = (TextView)findViewById(R.id.job_details__startdate);
        job_details__enddate = (TextView)findViewById(R.id.job_details__enddate);

        Intent intent = getIntent();
        Bundle bundle;
        bundle =  getIntent().getExtras();
        Integer option = bundle.getInt("option");

        job_details__title.setText(bundle.getString("title"));
        job_details__company_name.setText(bundle.getString("company_name"));
        job_details__desc.setText(bundle.getString("desc"));
        job_details__type.setText(bundle.getString("type"));
        job_details__startdate.setText(bundle.getString("start_date"));
        job_details__enddate.setText(bundle.getString("end_date"));

    }
}
