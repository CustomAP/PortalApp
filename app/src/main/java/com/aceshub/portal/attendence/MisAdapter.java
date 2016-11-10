package com.aceshub.portal.attendence;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aceshub.portal.R;

import java.util.List;

public class MisAdapter extends RecyclerView.Adapter<MisAdapter.DataHolder> {

    private List<MisListItem> data;

    public MisAdapter(List<MisListItem> data, Context context) {
        this.data = data;
    }

    @Override
    public DataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mis_listitem, parent, false);
        return new DataHolder(view);
    }

    @Override
    public void onBindViewHolder(DataHolder holder, int position) {
        MisListItem misListItem = data.get(position);
        holder.mis_tv.setText(misListItem.getMis());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class DataHolder extends RecyclerView.ViewHolder implements RecyclerView.OnClickListener {

        final int redColor;
        private TextView mis_tv;
        private CardView card;
        private boolean absent = true;

        DataHolder(View itemView) {
            super(itemView);

            mis_tv = (TextView) itemView.findViewById(R.id.mis_tv);
            Typeface typeface = Typeface.createFromAsset(itemView.getContext().getAssets(), "Fresca-Regular.ttf");
            mis_tv.setTypeface(typeface);

            card = (CardView) itemView.findViewById(R.id.mis_listitem_layout);
            card.setOnClickListener(this);

            redColor = R.color.red500;
        }

        @Override
        public void onClick(View v) {
            if (absent) {
                mis_tv.setBackground(v.getResources().getDrawable(R.drawable.mis_text_present));
                absent = false;
            } else {
                mis_tv.setBackground(v.getResources().getDrawable(R.drawable.mis_text_absent));
                absent = true;
            }
        }
    }
}
