package com.aceshub.portal.subject_details;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aceshub.portal.R;

import java.util.ArrayList;

/**
 * Created by Ashish Pawar on 3/7/2017.
 */

public class SubjectDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<String> dates,days;
    private ArrayList<Integer>present,absent;

    @Override
    public SubjectDetailsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.subject_details_single_item,parent,false);
        return new SubjectDetailsHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SubjectDetailsHolder subjectDetailsHolder=(SubjectDetailsHolder)holder;
        subjectDetailsHolder.date.setText(dates.get(position));
        subjectDetailsHolder.day.setText(days.get(position));
        subjectDetailsHolder.present.setText(present.get(position)+"");
        subjectDetailsHolder.absent.setText(absent.get(position)+"");
    }

    @Override
    public int getItemCount() {
        return dates.size();
    }


    public SubjectDetailsAdapter (ArrayList<String> dates,ArrayList<String> days,ArrayList<Integer> present,ArrayList<Integer> absent){
        this.absent=absent;
        this.present=present;
        this.dates=dates;
        this.days=days;
    }


    private class SubjectDetailsHolder extends RecyclerView.ViewHolder{
        TextView date,day,present,absent;

        private SubjectDetailsHolder(View itemView) {
            super(itemView);
            date=(TextView)itemView.findViewById(R.id.date_subject_details);
            day=(TextView)itemView.findViewById(R.id.day_subject_details);
            present=(TextView)itemView.findViewById(R.id.present_num_subject_details);
            absent=(TextView)itemView.findViewById(R.id.absent_num_subject_details);
        }
    }
}
