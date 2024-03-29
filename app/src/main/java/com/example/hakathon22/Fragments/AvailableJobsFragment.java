package com.example.hakathon22.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
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
import com.example.hakathon22.Adapters.AvailableJobsAdapter;
import com.example.hakathon22.Models.Jobs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class AvailableJobsFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    private ArrayList<Jobs> jobsList;
    private AvailableJobsAdapter mAdapter;

    public AvailableJobsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_available_jobs, container, false);
        jobsList = new ArrayList<>();
        layoutManager = (new LinearLayoutManager(getContext()));

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        registerRequester();

        mAdapter = new AvailableJobsAdapter(jobsList, getContext());
        recyclerView.setAdapter(mAdapter);

        return view;

    }
    private void registerRequester() {

        String url = "https://linguistixtank.website/job/public/api/job/index";

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new com.android.volley.Response.Listener<String>() {

                        @Override
                        public void onResponse(String response) {
                            try {
                                Log.e("resssss", response);
                                JSONObject ob = new JSONObject(response);
                                JSONArray jpbss = ob.getJSONArray("jobs");
                                Jobs jobsObj ;
                                for (int i =0 ; i< jpbss.length() ; i++){
                                    jobsObj = new Jobs();
                                    jobsObj.setId(jpbss.getJSONObject(i).getInt("id"));
                                   jobsObj.setTitle(jpbss.getJSONObject(i).getString("title"));
                                    jobsObj.setCompanyName(jpbss.getJSONObject(i).getString("company_name"));
                                    jobsObj.setDesc(jpbss.getJSONObject(i).getString("desc"));
                                    jobsObj.setStartDate(jpbss.getJSONObject(i).getString("start_date"));
                                    jobsObj.setEndDate(jpbss.getJSONObject(i).getString("end_date"));
                                    jobsObj.setType(jpbss.getJSONObject(i).getJSONObject("user").getString("type"));
                                    jobsList.add(jobsObj);

                                }
                                mAdapter.notifyDataSetChanged();

                            } catch (JSONException e) {
                                e.printStackTrace();
                                Log.e("nnnnew error>>", e.toString());
                            }

                        }
                    },
                    new com.android.volley.Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.e("errrror response", error.toString());
                        }

                    }) {

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> headers = new HashMap<String, String>();
                    headers.put("Accept", "application/json");
                    String token = SharedPrefManager.getInstance(getContext()).getSearchingJob().getToken();
                    Log.e("token for user", token);
                    headers.put("Authorization", "Bearer " + token);
                    return headers;
                }


            };
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                    5000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
        }

    }

