package com.aceshub.portal.studentstats;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aceshub.portal.R;

import java.util.List;

/**
 * Created by Abhidnya on 2/28/2017.
 */

public class StudentStatsAdapter extends RecyclerView.Adapter<StudentStatsAdapter.DataHolder> {

    private List<StudentStatsItem> studentsList;
    private StudentStatsItem item;

    public StudentStatsAdapter(List<StudentStatsItem> studentsList) {
        this.studentsList = studentsList;
    }

    @Override
    public DataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_list, parent, false);
        return new DataHolder(view);
    }

    @Override
    public void onBindViewHolder(DataHolder holder, int position) {
        item = studentsList.get(position);
        holder.studentName.setText(item.getName());
        holder.studentPercentage.setText(item.getPresentPercentage());

    }

    @Override
    public int getItemCount() {
        return studentsList.size();
    }

    class DataHolder extends RecyclerView.ViewHolder {

        private TextView studentName;
        private TextView studentPercentage;

        public DataHolder(View itemView) {
            super(itemView);

            studentName = (TextView) itemView.findViewById(R.id.creds_student_name);
            studentPercentage = (TextView) itemView.findViewById(R.id.creds_student_percentage);
        }
    }
}
