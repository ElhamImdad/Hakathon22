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

import com.example.hakathon22.R;
import com.example.hakathon22.adapters.AvailableJobsAdapter;
import com.example.hakathon22.models.Jobs;
import com.example.hakathon22.rest.RestJobs;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class AvailableJobsFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    private List<Jobs> jobs;
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

        response();

        // RoutinesAdapter mAdapter =new RoutinesAdapter(routines,getContext());
        // recyclerView.setAdapter(mAdapter);
        return view;

    }


        public void response() {

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
                        //  Log.e("Response 1", "Response code: " + response.code());

                        if (response.isSuccessful()) {
                            progressDoalog.dismiss();
                            Log.e("Response 2", "Response code: " + response.code());
                            jobs = response.body();
                            //Log.e("All things fragment", "Response: " + t.get(1).getChannels().get(0).getLinkedItems().get(0));


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
        }

        private void showIt(List<Jobs> response) {


            mAdapter = new AvailableJobsAdapter(response,getContext());
            recyclerView.setAdapter(mAdapter);
        }

    }

