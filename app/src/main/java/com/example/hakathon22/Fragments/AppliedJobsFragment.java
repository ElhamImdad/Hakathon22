package com.example.hakathon22.Fragments;


import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.hakathon22.Adapters.JobAdapter;
import com.example.hakathon22.Helpers.SharedPrefManager;
import com.example.hakathon22.Models.JobOrder;
import com.example.hakathon22.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class AppliedJobsFragment extends Fragment {

    ArrayList<JobOrder> requests = new ArrayList<>();
    JobAdapter adapter ;
    ListView listView ;
    public AppliedJobsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_applied_jobs, container, false);
        listView = v.findViewById(R.id.listView);
        getAllRequeste();
        adapter = new JobAdapter(requests,getContext());
        listView.setAdapter(adapter);
        return  v ;
    }

    public void getAllRequeste() {
        String url = "https://linguistixtank.website/job/public/api/oneUser\n";
        Log.d("Applied Fragment", "inside get all requests");
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("Respooonse", response.toString());
                try {
                    JSONArray jsonArray = response.getJSONArray("jobs");
                    int size = jsonArray.length();
                    Log.d("Array Size is ", " " + size);
                    // print the number of requests
                    if (requests.size() != 0) {
                        requests.clear();
                    }
                    for (int i = 0; i < size; i++) {

                        // get objects from the array
                       JSONObject object = jsonArray.getJSONObject(i);
                       String title= object.getString("title");
                       String company = object.getString("company_name");
                       String des = object.getString("desc");
                       JobOrder order = new JobOrder(title,company,des);
                       requests.add(order);

                    }
                    // adapterHD.updateData(umraRequests);
                     adapter.notifyDataSetChanged();

                    Log.e("requests No ", "" + requests.size());
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.e("R Errors ",error.toString());
            }
         }) {
        @Override
        public Map<String, String> getHeaders() throws AuthFailureError {
            Map<String, String> Headers = new HashMap<>();
              String token = SharedPrefManager.getInstance(getContext()).getSearchingJob().getToken();
            Headers.put("Authorization", "Bearer " + token);
            return Headers;
        }


    };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);}
}
