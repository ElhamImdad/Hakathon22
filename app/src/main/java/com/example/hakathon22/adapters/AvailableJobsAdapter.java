package com.example.hakathon22.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hakathon22.Activities.JobDetailsActivity;
import com.example.hakathon22.R;
import com.example.hakathon22.models.Jobs;

import java.util.List;

public class AvailableJobsAdapter extends RecyclerView.Adapter<AvailableJobsAdapter.ViewHolder>{

    private List<Jobs> jobsList;
    Context context;
    TextView title , company;

    public AvailableJobsAdapter(List<Jobs> jobsList, Context context) {
        this.jobsList = jobsList;
        this.context = context;
    }


    @NonNull
    @Override
    public AvailableJobsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.jobs_layout,parent, false);


        return new AvailableJobsAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        final Jobs jobs_position = jobsList.get(position);

        title = (TextView) title.findViewById(R.id.title_id);

        company = (TextView) company.findViewById(R.id.company_id);

        holder.title.setText(jobs_position.getTitle());

        holder.company.setText(jobs_position.getCompanyName());



        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, JobDetailsActivity.class);
                intent.putExtra("option", jobsList.get(position).getId());

                Log.e("All Jobs adapter", "Response: " + jobsList.get(position).getId());

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return jobsList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title , company;
        //ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.title_id);

            company = (TextView) itemView.findViewById(R.id.company_id);

           // imageView = (ImageView)itemView.findViewById(R.id.img_dash);
        }
    }
}






