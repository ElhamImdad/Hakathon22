package com.example.hakathon22.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.hakathon22.Models.JobOrder;
import com.example.hakathon22.R;
import java.util.ArrayList;

public class JobAdapter extends BaseAdapter {
    private ArrayList<JobOrder> order;


    public JobAdapter(ArrayList<JobOrder> order, Context context) {
        this.order = order;

    }

    @Override
    public int getCount() {
        return order.size();
    }

    @Override
    public Object getItem(int i) {
        return order.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view==null){

            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.raw, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }

        JobOrder item = (JobOrder) getItem(i);
        viewHolder.jobName.setText(item.getJobName());
        viewHolder. companyName.setText(item.getCompanyName());
        viewHolder. des.setText(item.getDes());

        return view;
    }
    private class ViewHolder {
        TextView jobName;
        TextView companyName;
        TextView des;

        public ViewHolder(View view) {
            jobName = view.findViewById(R.id.jobName);
            companyName = view.findViewById(R.id.companyName);
            des = view.findViewById(R.id.areaName);

        }
    }
}