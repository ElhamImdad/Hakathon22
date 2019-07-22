package com.example.hakathon22.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hakathon22.R;
import com.example.hakathon22.models.JobsDetails;
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

        response(option);
    }


    public void response(final Integer id) {


        Call<List<JobsDetails>> call = RestJobs.getInstance().getApi().getJobsDetails();



        call.enqueue(new Callback<List<JobsDetails>>() {


            @Override
            public void onResponse(Call<List<JobsDetails>> call, Response<List<JobsDetails>> response) {


                if (response.isSuccessful()) {

                    Log.e("JobsDetails", "Response code: " + response.body());

                    JobsDetails ite;

                    jobsDetails = response.body();
                    for(int i = 0 ; i <jobsDetails.size();i++){
                        Log.e("JobsDetails", "Response code: " +jobsDetails.get(i).getId() );

                        if(id==(jobsDetails.get(i).getId() )){

                            //Log.e("JobsDetails Activity", "Response: " +jobsDetails.get(i).getLink() );

                            ite = new JobsDetails();
                            ite.setTitle(jobsDetails.get(i).getTitle());
                            ite.setDesc(jobsDetails.get(i).getDesc());
                            ite.setStartDate(jobsDetails.get(i).getStartDate());
                            ite.setEndDate(jobsDetails.get(i).getEndDate());
                            ite.setCompanyName(jobsDetails.get(i).getCompanyName());
                            ite.setUserId(jobsDetails.get(i).getUserId());

                            job_details__title.setText(jobsDetails.get(i).getTitle());
                            job_details__company_name.setText(jobsDetails.get(i).getCompanyName());
                            job_details__desc.setText(jobsDetails.get(i).getDesc());
                            job_details__startdate.setText(jobsDetails.get(i).getStartDate());
                            job_details__enddate.setText(jobsDetails.get(i).getEndDate());



                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<JobsDetails>> call, Throwable t) {

                Toast.makeText(getApplicationContext(), " Error connecting to the server.. Trying Again...", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
