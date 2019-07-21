package com.example.hakathon22.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.hakathon22.R;

public class SelectTypeUserFragment extends Fragment {
    Button searchJobBtn, provideJobBTn;
    public SelectTypeUserFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_select_type_user, container, false);
        searchJobBtn = view.findViewById(R.id.ReqBTN);
        provideJobBTn = view.findViewById(R.id.OfferBTN);
        searchJobBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchJobBtn.setBackgroundColor(getResources().getColor(R.color.red));
                searchJobBtn.setTextColor(getResources().getColor(R.color.white));

                provideJobBTn.setBackgroundColor(getResources().getColor(R.color.white));
                provideJobBTn.setTextColor(getResources().getColor(R.color.red));
                        RequesterHomeFragment f = new RequesterHomeFragment();
                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        ft.replace(R.id.container,f);
                        ft.commit();
            }
        });
        provideJobBTn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                provideJobBTn.setBackgroundColor(getResources().getColor(R.color.red));
                provideJobBTn.setTextColor(getResources().getColor(R.color.white));

                searchJobBtn.setBackgroundColor(getResources().getColor(R.color.white));
                searchJobBtn.setTextColor(getResources().getColor(R.color.red));
                RequesterHomeFragment f = new RequesterHomeFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.container,f);
                ft.commit();
            }
        });

        return view;
    }
}
