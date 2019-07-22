package com.example.hakathon22.Fragments;


import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.hakathon22.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class AppliedJobsFragment extends Fragment {

    //ArrayList<JobOrder> requests = new ArrayList<>();
    public AppliedJobsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_applied_jobs, container, false);
    }

    public void getAllRequeste() {
        String url = "https://linguistixtank.website/job/public/api/oneUser\n";
        Log.d("Applied Fragment", "inside get all requests");
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("jobs");
                    int size = jsonArray.length();
                    Log.d("Array Size is ", " " + size);
                    // print the number of requests
                   /* if (requests.size() != 0) {
                        requests.clear();
                    }*/
                    for (int i = 0; i < size; i++) {
                         //JobOrder order = new JobOrder();
                        // get objects from the array
                        JSONObject object = jsonArray.getJSONObject(i);
                       String title= object.getString("title");
                       String company = object.getString("company_name");
                       String des = object.getString("desc");
                     // order.
                        //  umraRequests.add(umraRequest);
                        // adapterHD.notifyItemInserted(i);
                    }
                    // adapterHD.updateData(umraRequests);
                    // adapterHD.notifyDataSetChanged();

                    //Log.e("requests No ", "" + umraRequests.size());

                }


                      /*  if (dateFromApi != null){
                       //     gregorianString = convertDte(dateFromApi);
                        }
                        umraRequests.setDate(gregorianString);*/
                // add the umra object to the arrayList
                //  int initialSize = umraRequests.size();
                //  adapterHD.notifyItemRangeInserted(initialSize, umraRequests.size()-1);

                catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }

        );
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);
    }
}
