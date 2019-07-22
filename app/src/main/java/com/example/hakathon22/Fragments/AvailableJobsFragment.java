package com.example.hakathon22.Fragments;


import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.hakathon22.Helpers.SharedPrefManager;
import com.example.hakathon22.Helpers.VolleySingleton;
import com.example.hakathon22.R;
import com.example.hakathon22.adapters.AvailableJobsAdapter;
import com.example.hakathon22.models.Jobs;
import com.example.hakathon22.rest.RestJobs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class AvailableJobsFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    private List<Jobs> jobsList;
    private AvailableJobsAdapter mAdapter;



    public AvailableJobsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_available_jobs, container, false);

        layoutManager = (new LinearLayoutManager(getContext()));

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        registerRequester();

        mAdapter = new AvailableJobsAdapter(jobsList, getContext());
        recyclerView.setAdapter(mAdapter);

        return view;

    }


   /*     public void response() {

            try {
                Call<List<Jobs>> call = RestJobs.getInstance().getApi().getJobs();

                // Set up progress before call
                final ProgressDialog progressDoalog;
                progressDoalog = new ProgressDialog(getContext());
                progressDoalog.setMax(100);
                progressDoalog.setMessage("Loading...");
                // progressDoalog.setTitle("ProgressDialog bar example");
                progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                // show it
                progressDoalog.show();

                call.enqueue(new Callback<List<Jobs>>() {


                    @Override
                    public void onResponse(Call<List<Jobs>> call, Response<List<Jobs>> response) {

                         Log.e("Response 1", "Response code: " + response.code());

                        if (response.isSuccessful()) {
                            progressDoalog.dismiss();
                            Log.e("Response 2", "Response code: " + response.code());

                            jobs = response.body();

                            showIt(jobs);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Jobs>> call, Throwable t) {
                        progressDoalog.dismiss();

                        Toast.makeText(getContext(), " Error connecting to the server.. Trying Again...", Toast.LENGTH_SHORT).show();

                        response();

                    }
                });

            } catch (Exception e) {
                Log.e("Error", "" + e);

            }
        }*/

    private void registerRequester() {

        String url = "https://linguistixtank.website/job/public/api/job/index";

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new com.android.volley.Response.Listener<String>() {

                        @Override
                        public void onResponse(String response) {
                            try {

                                JSONObject ob = new JSONObject(response);
                                JSONArray jpbss = ob.getJSONArray("jobs");
                                Jobs jobsObj ;
                                for (int i =0 ; i< jpbss.length() ; i++){
                                    jobsObj = new Jobs();
                                    jobsObj.setId(jpbss.getJSONObject(i).getInt("id"));
                                   jobsObj.setTitle(jpbss.getJSONObject(i).getString("title"));
                                    jobsObj.setCompanyName(jpbss.getJSONObject(i).getString("company_name"));
                                    jobsList.add(jobsObj);

                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                                Log.e("new error>>", e.toString());
                            }

                        }
                    },
                    new com.android.volley.Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.e("error response", error.toString());
                        }

                    }) {

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> headers = new HashMap<String, String>();
                    headers.put("Accept", "application/json");
                 //   String token = SharedPrefManager.getInstance(getContext()).getSearchingJob().getToken();
                   // Log.e("token for user", token);
                    headers.put("Authorization", "Bearer " + "\n" +
                            "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjUyZDEzYjkxMjRhNzc0NzljMDA4ZTA5NDZhYzAwMjRhNmVhOWQ1NmRjZWMxNmI5YmMxYTQ3NmYxYTFlZjZlYWU0OWZiYzVlMmY0ZDgxZTc3In0.eyJhdWQiOiIxIiwianRpIjoiNTJkMTNiOTEyNGE3NzQ3OWMwMDhlMDk0NmFjMDAyNGE2ZWE5ZDU2ZGNlYzE2YjliYzFhNDc2ZjFhMWVmNmVhZTQ5ZmJjNWUyZjRkODFlNzciLCJpYXQiOjE1NjM3ODM5NzQsIm5iZiI6MTU2Mzc4Mzk3NCwiZXhwIjoxNTk1NDA2Mzc0LCJzdWIiOiIyMCIsInNjb3BlcyI6W119.w85Y67yh5WCgalv3KpEAC9pM5qBgXoiu_Ezp6eEQ2zwUrczqZUHlGqW0LLxA2pojlAg9zCiVzlm9-EQ2VOdK27lnCiX-xwwnWg_lDabWzPFFw5st7DKEIquIiz4-CJp9wYfwo1KESmDh8joFtTVzi9OcJL3cFX0uL3wt3-ldgl8RjcYtyI3T5BuoM63HCu56ChRsMeUK6-QObtu2Ymc8LwKfqUNUiu21ozlXA9P9uauTX2BKqQE1otjGTWqPZPMLshbUmIGxkAjk56p0KQyWSsFcDLXme14pn9WhFyct9bhBCEAg-ONVDq8tDLc4_-HNXHIA8hpb4_AJsjJpMOenCkVnOchODrxPg4bn6Q0HG5Fm-HIw7a_Thqm_7Pu-7VeRs7uzPrdbJb7ZCK13ijCrTFCuaVzOnaDg9nF6WlmuGMlEWBJsz5qlfNMTcX2CTj8LFLqn-gdlwADO_pjeXKRMiFQQVRaVHZmrxtAkVyj9_URuOFcxO0eSWip4uTmR6CiY1ff4s1ok_HSr_TQNAxkZ18tq1Ll8zX2Ad48NBAGC_SbOFiC79AhkXbx5Y4twL0xF1ScbHyTPqOKF85LvlcyiJ7RVj_P1qX5cglK2GpVatnbe-z5mtUMhxZQGSnkz6igH8NlQ9inU6YlkoSvvRz4zE1N2VhD3BQuCm8XH6F-C5PA");
                    return headers;
                }


            };
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                    5000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
        }

        private void showIt(List<Jobs> response) {


            mAdapter = new AvailableJobsAdapter(response,getContext());
            recyclerView.setAdapter(mAdapter);
        }

    }

