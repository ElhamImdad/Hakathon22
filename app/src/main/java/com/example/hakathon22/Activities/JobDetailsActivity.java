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


    TextView textView;
    List<JobsDetails> jobsDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_detailes);

        textView = (TextView)findViewById(R.id.job_details__id);

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
                //  Log.e("Response 1", "Response code: " + response.code());

                if (response.isSuccessful()) {
                    // progressDoalog.dismiss();
                    // Log.e("Response 2", "Response code: " + response.body().size());
                    JobsDetails ite;
                    jobsDetails = response.body();
                    for(int i = 0 ; i <jobsDetails.size();i++){
                        // Log.e("Response 2555", "Response code: " +item.get(i).getName() );
                        if(id==(jobsDetails.get(i).getId() )){

                            //Log.e("JobsDetails Activity", "Response: " +jobsDetails.get(i).getLink() );

                            ite = new JobsDetails();
                            ite.setTitle(jobsDetails.get(i).getTitle());
                            ite.setDesc(jobsDetails.get(i).getDesc());
                            ite.setStartDate(jobsDetails.get(i).getStartDate());
                            ite.setEndDate(jobsDetails.get(i).getEndDate());
                            ite.setCompanyName(jobsDetails.get(i).getCompanyName());
                            ite.setUserId(jobsDetails.get(i).getUserId());

                            textView.setText(jobsDetails.get(i).getTitle());



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
