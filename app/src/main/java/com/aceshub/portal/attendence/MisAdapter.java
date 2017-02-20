package com.aceshub.portal.attendence;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
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

        View.OnClickListener showStudentInfo = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Student Info");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        };
        private TextView mis_tv;
        private TextView mis_name_tv;
        private CardView card;
        private boolean absent = true;
        private LinearLayout listitemLinearlayout;
        private TextView paText;
        private ImageButton studentInfo;

        DataHolder(View itemView) {
            super(itemView);

            mis_tv = (TextView) itemView.findViewById(R.id.mis_tv);
            Typeface typeface = Typeface.createFromAsset(itemView.getContext().getAssets(), "Fresca-Regular.ttf");
            mis_tv.setTypeface(typeface);

            mis_name_tv = (TextView) itemView.findViewById(R.id.mis_name_tv);
            mis_name_tv.setTypeface(typeface);

            card = (CardView) itemView.findViewById(R.id.mis_listitem_layout);
            card.setOnClickListener(this);

            paText = (TextView) itemView.findViewById(R.id.mis_p_a_text);

            listitemLinearlayout = (LinearLayout) itemView.findViewById(R.id.listitem_linearlayout);

            studentInfo = (ImageButton) itemView.findViewById(R.id.imageButton_studentinfo);

            studentInfo.setOnClickListener(showStudentInfo);
        }

        @Override
        public void onClick(View v) {
            if (!absent) {
                //listitemLinearlayout.setBackground(ResourcesCompat.getDrawable(v.getResources(), R.drawable.mis_text_present, null));
                card.setBackground(ResourcesCompat.getDrawable(v.getResources(), R.drawable.mis_text_present, null));
                paText.setText("P");
                paText.setTextColor(ResourcesCompat.getColor(v.getResources(), R.color.green500, null));
                mis_tv.setTextColor(ResourcesCompat.getColor(v.getResources(), R.color.green500, null));
                mis_name_tv.setTextColor(ResourcesCompat.getColor(v.getResources(), R.color.green300, null));
                MisData.addAbsent(-1);
                AttendanceActivity.calculate();

                absent = true;
            } else {
                //listitemLinearlayout.setBackground(ResourcesCompat.getDrawable(v.getResources(), R.drawable.mis_text_absent, null));
                card.setBackground(ResourcesCompat.getDrawable(v.getResources(), R.drawable.mis_text_absent, null));
                paText.setText("A");
                paText.setTextColor(ResourcesCompat.getColor(v.getResources(), R.color.red500, null));
                mis_tv.setTextColor(ResourcesCompat.getColor(v.getResources(), R.color.red500, null));
                mis_name_tv.setTextColor(ResourcesCompat.getColor(v.getResources(), R.color.red300, null));
                MisData.addAbsent(1);
                AttendanceActivity.calculate();

                absent = false;
            }
        }
    }
}
