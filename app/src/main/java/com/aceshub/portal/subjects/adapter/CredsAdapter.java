package com.aceshub.portal.subjects.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.shri.list.R;

import java.util.List;

/**
 * Created by Shri on 03-11-2016.
 */

public class CredsAdapter extends RecyclerView.Adapter<CredsAdapter.CredsHolder>{

    private List<com.example.shri.list.model.CredsItem> listData;
    private LayoutInflater inflater;
    int val;
    public CredsAdapter(List<com.example.shri.list.model.CredsItem> listData, Context c){
        this.inflater = LayoutInflater.from(c);
        this.listData = listData;

    }

    @Override
    public CredsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = inflater.inflate(R.layout.student_list, parent, false);
        return new CredsHolder(view);
    }

    @Override
    public void onBindViewHolder(final CredsHolder holder, int position) {
        final com.example.shri.list.model.CredsItem item = listData.get(position);
        holder.title.setText(item.getName());
        holder.b1.setText(item.getPercentage());

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    class CredsHolder extends RecyclerView.ViewHolder {

        private TextView title;
        Button b1;
        private View container;

        public CredsHolder(View itemView) {

            super(itemView);

            title = (TextView) itemView.findViewById(R.id.student_t1);
            b1 = (Button) itemView.findViewById(R.id.student_b1);

        }
    }
}
