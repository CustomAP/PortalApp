package com.aceshub.portal.today;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.aceshub.portal.R;
import com.aceshub.portal.Today;
import com.aceshub.portal.attendence.AttendanceActivity;

import java.util.List;

public class TodayAdapter extends RecyclerView.Adapter<TodayAdapter.DataHolder> {

    Context context;
    private List<TodayListItem> data;
    private int[] colors;

    public TodayAdapter(List<TodayListItem> data, Context context) {
        this.data = data;
        colors = context.getResources().getIntArray(R.array.colors);
        this.context = context;
    }

    @Override
    public DataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.today_listitem, parent, false);
        return new DataHolder(view);
    }

    @Override
    public void onBindViewHolder(final DataHolder holder, final int position) {
        TodayListItem todayListItem = data.get(position);
        holder.today_time.setText(todayListItem.getTime());
        holder.today_subject.setText(todayListItem.getSubjectName());
        holder.card.setCardBackgroundColor(holder.getColor(todayListItem.getSubjectName() +
                todayListItem.getTime() + "secret"));

        holder.menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(v, holder.menu, position);
            }
        });
    }

    private void showPopup(View view, ImageButton menuButton, final int position) {
        PopupMenu popup = new PopupMenu(view.getContext(), menuButton);
        MenuInflater inflate = popup.getMenuInflater();
        inflate.inflate(R.menu.today_card_menu, popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.edit:

                        break;
                    case R.id.delete:
                        TodayData.remove(position);
                        Today.adapter.notifyDataSetChanged();
                        break;
                    default:
                        return false;
                }
                return false;
            }
        });
        popup.setGravity(Gravity.END);
        popup.show();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class DataHolder extends RecyclerView.ViewHolder {

        private TextView today_time;
        private TextView today_subject;
        private CardView card;
        private ImageButton menu;
        private Context context;

        DataHolder(final View itemView) {
            super(itemView);

            Typeface typeface = Typeface.createFromAsset(itemView.getContext().getAssets(), "Fresca-Regular.ttf");

            today_time = (TextView) itemView.findViewById(R.id.today_time);
            today_subject = (TextView) itemView.findViewById(R.id.today_subjectname);

            today_time.setTypeface(typeface);
            today_subject.setTypeface(typeface);

            card = (CardView) itemView.findViewById(R.id.today_listitem_card);
            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), AttendanceActivity.class);
                    context.startActivity(intent);
                }
            });

            menu = (ImageButton) itemView.findViewById((R.id.menu_imgbtn));

            context = itemView.getContext();
        }

        private int getColor(String string) {
            int hash = 7;
            for (int i = 0, len = string.length(); i < len; i++) {
                hash = string.codePointAt(i) + (hash << 5) - hash;
            }
            int index = Math.abs(hash % colors.length);
            return colors[index];
        }
    }
}
