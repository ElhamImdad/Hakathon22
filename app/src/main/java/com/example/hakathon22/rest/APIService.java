package com.example.hakathon22.rest;

import com.example.hakathon22.Models.Jobs;
import com.example.hakathon22.Models.JobsDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {



    @GET("/job/index")
    Call<List<Jobs>> getJobs();


    @GET("/job/show")
    Call<List<JobsDetails>> getJobsDetails();


}
