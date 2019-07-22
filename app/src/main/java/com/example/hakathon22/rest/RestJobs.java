package com.example.hakathon22.rest;

import android.util.Base64;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestJobs {

        private static RestJobs mInstance;
        private static Retrofit retrofit ;
        public static final String BASE_URL = "https://linguistixtank.website/job/public/api/";
       // private static final String AUTH = "Basic " + Base64.encodeToString(("adsf1234@hotmail.com:asdf1234").getBytes(), Base64.NO_WRAP);
        private static final String AUT = "application/json";
    private static final String AUTH = "application/json";


        private  RestJobs() {
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(
                            new Interceptor() {
                                @Override
                                public Response intercept(Chain chain) throws IOException {
                                    Request original = chain.request();

                                    Request.Builder requestBuilder = original.newBuilder()
                                           .addHeader("Content-Type", AUT)

                                           // .addHeader("Authorization", AUTH)
                                            .addHeader("Accept", AUTH)
                                            .method(original.method(), original.body());

                                    Request request = requestBuilder.build();
                                    return chain.proceed(request);
                                }

                            }
                    ).build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();


        }

        public static synchronized RestJobs getInstance() {
            if (mInstance == null) {
                mInstance = new RestJobs();
            }
            return mInstance;
        }

        public static APIService getApi() {
            return retrofit.create(APIService.class);
        }
    }


