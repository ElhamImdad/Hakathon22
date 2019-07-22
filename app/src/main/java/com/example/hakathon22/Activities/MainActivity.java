package com.example.hakathon22.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.hakathon22.Fragments.AppliedJobsFragment;
import com.example.hakathon22.Fragments.JobAvailableFragment;
import com.example.hakathon22.Fragments.ProfileFragment;
import com.example.hakathon22.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    setFragment(new JobAvailableFragment());
    BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem menuItem) {
            switch (menuItem.getItemId()) {

                case R.id.applied_jobs:
                    setFragment(new AppliedJobsFragment());
                    break;
                case R.id.available_jobs:
                    setFragment(new JobAvailableFragment());
                    break;
                case R.id.profile:
                    setFragment(new ProfileFragment());
                    break;
            }
            return true;
        }
    });

}
    public void setFragment(Fragment f){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.frame,f);
        ft.commit();
    }


}
