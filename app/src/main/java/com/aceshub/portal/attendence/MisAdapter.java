package com.aceshub.portal.attendence;

import android.graphics.Typeface;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aceshub.portal.R;

import java.util.List;

public class MisAdapter extends RecyclerView.Adapter<MisAdapter.DataHolder> {

    MisListItem misListItem;
    private List<MisListItem> data;

    public MisAdapter(List<MisListItem> data) {
        this.data = data;
    }

    @Override
    public DataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mis_listitem, parent, false);
        return new DataHolder(view);
    }

    @Override
    public void onBindViewHolder(DataHolder holder, int position) {
        misListItem = data.get(position);
        boolean isPresent = misListItem.isPresent();
        if (isPresent) {
            holder.setPresent(holder.itemView);
        } else {
            holder.setAbsent(holder.itemView);
        }
        holder.isPresent = isPresent;
        holder.misTv.setText(misListItem.getMis());
        holder.nameTv.setText(misListItem.getName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class DataHolder extends RecyclerView.ViewHolder implements RecyclerView.OnClickListener {

        private TextView misTv;
        private TextView nameTv;
        private CardView card;
        private boolean isPresent;
        private TextView paText;

        DataHolder(View itemView) {
            super(itemView);

            misTv = (TextView) itemView.findViewById(R.id.attendance_mis_tv);
            Typeface typeface = Typeface.createFromAsset(itemView.getContext().getAssets(), "Fresca-Regular.ttf");
            misTv.setTypeface(typeface);

            nameTv = (TextView) itemView.findViewById(R.id.attendance_name_tv);

            card = (CardView) itemView.findViewById(R.id.mis_listitem_layout);
            card.setOnClickListener(this);

            paText = (TextView) itemView.findViewById(R.id.mis_p_a_text);
        }

        @Override
        public void onClick(View v) {
            if (isPresent) {
                setAbsent(v);
                MisData.addAbsent(1);
                AttendanceActivity.calculate();
            } else {
                setPresent(v);
                MisData.addAbsent(-1);
                AttendanceActivity.calculate();
            }
        }

        public void setPresent(View v) {
            card.setBackground(ResourcesCompat.getDrawable(v.getResources(), R.drawable.mis_text_present, null));
            paText.setText("P");
            paText.setTextColor(ResourcesCompat.getColor(v.getResources(), R.color.green500, null));
            misTv.setTextColor(ResourcesCompat.getColor(v.getResources(), R.color.green500, null));
            nameTv.setTextColor(ResourcesCompat.getColor(v.getResources(), R.color.green300, null));
            MisData.getData().get(getAdapterPosition()).setPresent(true);
            isPresent = true;
        }

        public void setAbsent(View v) {
            card.setBackground(ResourcesCompat.getDrawable(v.getResources(), R.drawable.mis_text_absent, null));
            paText.setText("A");
            paText.setTextColor(ResourcesCompat.getColor(v.getResources(), R.color.red500, null));
            misTv.setTextColor(ResourcesCompat.getColor(v.getResources(), R.color.red500, null));
            nameTv.setTextColor(ResourcesCompat.getColor(v.getResources(), R.color.red300, null));
            MisData.getData().get(getAdapterPosition()).setPresent(false);
            isPresent = false;
        }
    }
}
