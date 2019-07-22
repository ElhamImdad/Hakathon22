package com.example.hakathon22.rest;

import com.example.hakathon22.models.Jobs;
import com.example.hakathon22.models.JobsDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIService {


   /* @FormUrlEncoded
    @POST("/login")  //End Url
    Call<LoginResponse> userLogin(
            @Field("username") String username,
            @Field("password") String password
    );
*/


    @GET("/job/index")
    Call<List<Jobs>> getJobs();


    @GET("/job/show")
    Call<List<JobsDetails>> getJobsDetails();


}
