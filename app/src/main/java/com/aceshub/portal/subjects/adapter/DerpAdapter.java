package com.aceshub.portal.subjects.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.aceshub.portal.R;
import com.aceshub.portal.subjects.model.ListItem;
import com.aceshub.portal.subjects.ui.CredsActivity;

import java.util.List;

public class DerpAdapter extends RecyclerView.Adapter<DerpAdapter.DerpHolder>{

    private List<ListItem> listData;
    private LayoutInflater inflater;

    public DerpAdapter(List<ListItem> listData, Context c){
        this.inflater = LayoutInflater.from(c);
        this.listData = listData;
    }

    @Override
    public DerpHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = inflater.inflate(R.layout.list_item, parent, false);
        return new DerpHolder(view);
    }

    @Override
    public void onBindViewHolder(final DerpHolder holder, int position) {
        final ListItem item = listData.get(position);
        holder.title.setText(item.getTitle());
        holder.b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent i = new Intent(v.getContext(), CredsActivity.class);
                i.putExtra("Subject", holder.title.getText().toString());Toast.makeText(v.getContext(),holder.title.getText().toString(), Toast.LENGTH_SHORT).show();
                v.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    class DerpHolder extends RecyclerView.ViewHolder {

        Button b1, b2;
        private TextView title;
        private View container;

        public DerpHolder(View itemView) {

            super(itemView);

            title = (TextView) itemView.findViewById(R.id.t1);
            b1 = (Button) itemView.findViewById(R.id.b1);
            b2 = (Button) itemView.findViewById(R.id.b2);

        }
    }
}
