package com.aceshub.portal.subjects;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aceshub.portal.R;

import java.util.ArrayList;
import java.util.List;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    ExpandableListView expandableListView;

    //Parent
    TextView groupTitleTextView;
    private Context _context;
    private List<String> _listDataHeader;
    //Child
    private Button[] dayButtons = new Button[6];
    private TextView slot1TV;
    private TextView slot2TV;
    private ImageButton addButton;

    private int currentSelectedDay = 0;
    private int notSelectedChildTextColor;

    //Dummy data
    private ArrayList<String[]> data = new ArrayList<>();
    private String[] mondayTimes = {"10:00\nto\n11:00"};
    private String[] tuesdayTimes = {};
    private String[] wednesdayTimes = {"10:00\nto\n11:00", "11:00\nto\n12:00"};
    private String[] thursdayTimes = {"10:00\nto\n11:00"};
    private String[] fridayTimes = {};
    private String[] saturdayTimes = {"10:00\nto\n11:00", "11:00\nto\n12:00"};

    //OnClickListeners of Day Buttons
    private View.OnClickListener onClickListenerMondayButton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setData(0);
            dayButtons[currentSelectedDay].setBackground(ContextCompat.getDrawable(v.getContext(), R.drawable.subjects_days_normal));
            dayButtons[currentSelectedDay].setTextColor(notSelectedChildTextColor);
            dayButtons[0].setBackground(ContextCompat.getDrawable(v.getContext(), R.drawable.subjects_days_selected));
            dayButtons[0].setTextColor(Color.WHITE);
            currentSelectedDay = 0;
        }
    };
    private View.OnClickListener onClickListenerTuesdayButton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setData(1);
            dayButtons[currentSelectedDay].setBackground(ContextCompat.getDrawable(v.getContext(), R.drawable.subjects_days_normal));
            dayButtons[currentSelectedDay].setTextColor(notSelectedChildTextColor);
            dayButtons[1].setBackground(ContextCompat.getDrawable(v.getContext(), R.drawable.subjects_days_selected));
            dayButtons[1].setTextColor(Color.WHITE);
            currentSelectedDay = 1;
        }
    };
    private View.OnClickListener onClickListenerWednesdayButton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setData(2);
            dayButtons[currentSelectedDay].setBackground(ContextCompat.getDrawable(v.getContext(), R.drawable.subjects_days_normal));
            dayButtons[currentSelectedDay].setTextColor(notSelectedChildTextColor);
            dayButtons[2].setBackground(ContextCompat.getDrawable(v.getContext(), R.drawable.subjects_days_selected));
            dayButtons[2].setTextColor(Color.WHITE);
            currentSelectedDay = 2;
        }
    };
    private View.OnClickListener onClickListenerThursdayButton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setData(3);
            dayButtons[currentSelectedDay].setBackground(ContextCompat.getDrawable(v.getContext(), R.drawable.subjects_days_normal));
            dayButtons[currentSelectedDay].setTextColor(notSelectedChildTextColor);
            dayButtons[3].setBackground(ContextCompat.getDrawable(v.getContext(), R.drawable.subjects_days_selected));
            dayButtons[3].setTextColor(Color.WHITE);
            currentSelectedDay = 3;
        }
    };
    private View.OnClickListener onClickListenerFridayButton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setData(4);
            dayButtons[currentSelectedDay].setBackground(ContextCompat.getDrawable(v.getContext(), R.drawable.subjects_days_normal));
            dayButtons[currentSelectedDay].setTextColor(notSelectedChildTextColor);
            dayButtons[4].setBackground(ContextCompat.getDrawable(v.getContext(), R.drawable.subjects_days_selected));
            dayButtons[4].setTextColor(Color.WHITE);
            currentSelectedDay = 4;
        }
    };
    private View.OnClickListener onClickListenerSaturdayButton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setData(5);
            dayButtons[currentSelectedDay].setBackground(ContextCompat.getDrawable(v.getContext(), R.drawable.subjects_days_normal));
            dayButtons[currentSelectedDay].setTextColor(notSelectedChildTextColor);
            dayButtons[5].setBackground(ContextCompat.getDrawable(v.getContext(), R.drawable.subjects_days_selected));
            dayButtons[5].setTextColor(Color.WHITE);
            currentSelectedDay = 5;
        }
    };

    //Constructor
    public ExpandableListAdapter(ExpandableListView expandableListView, Context context, List<String> listDataHeader) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this.expandableListView = expandableListView;
        notSelectedChildTextColor = ContextCompat.getColor(context, R.color.subject_list_day_default_text_color);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        if (convertView == null) {

            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.subject_timetable_item_child_layout, null);
        }

        dayButtons[0] = (Button) convertView.findViewById(R.id.button_subjects_days_monday);
        dayButtons[1] = (Button) convertView.findViewById(R.id.button_subjects_days_tuesday);
        dayButtons[2] = (Button) convertView.findViewById(R.id.button_subjects_days_wednesday);
        dayButtons[3] = (Button) convertView.findViewById(R.id.button_subjects_days_thursday);
        dayButtons[4] = (Button) convertView.findViewById(R.id.button_subjects_days_friday);
        dayButtons[5] = (Button) convertView.findViewById(R.id.button_subjects_days_saturday);

        dayButtons[0].setOnClickListener(onClickListenerMondayButton);
        dayButtons[1].setOnClickListener(onClickListenerTuesdayButton);
        dayButtons[2].setOnClickListener(onClickListenerWednesdayButton);
        dayButtons[3].setOnClickListener(onClickListenerThursdayButton);
        dayButtons[4].setOnClickListener(onClickListenerFridayButton);
        dayButtons[5].setOnClickListener(onClickListenerSaturdayButton);

        slot1TV = (TextView) convertView.findViewById(R.id.tv_subject_timing_slot1);
        slot2TV = (TextView) convertView.findViewById(R.id.tv_subject_timing_slot2);
        addButton = (ImageButton) convertView.findViewById(R.id.imgbut_subject_timing_add);

        //dummy data
        data.add(mondayTimes);
        data.add(tuesdayTimes);
        data.add(wednesdayTimes);
        data.add(thursdayTimes);
        data.add(fridayTimes);
        data.add(saturdayTimes);

        setData(0);

        return convertView;
    }

    private void setData(int dayNumber) {
        switch (data.get(dayNumber).length) {
            case 0:
                addButton.setVisibility(View.VISIBLE);
                slot1TV.setVisibility(View.GONE);
                slot2TV.setVisibility(View.GONE);
                break;
            case 1:
                addButton.setVisibility(View.VISIBLE);
                slot1TV.setVisibility(View.VISIBLE);
                slot2TV.setVisibility(View.GONE);
                slot1TV.setText(data.get(dayNumber)[0]);
                break;
            case 2:
                slot1TV.setVisibility(View.VISIBLE);
                slot2TV.setVisibility(View.VISIBLE);
                addButton.setVisibility(View.GONE);
                slot1TV.setText(data.get(dayNumber)[0]);
                slot2TV.setText(data.get(dayNumber)[1]);
                break;
        }
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        final String headerTitle = (String) getGroup(groupPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.subject_timetable_item_layout, parent, false);
        }

        groupTitleTextView = (TextView) convertView.findViewById(R.id.tv_group_expandablelistview_subjectname);
        groupTitleTextView.setText(headerTitle);

        ImageView infoButton = (ImageView) convertView.findViewById(R.id.image_button_subject_info);

        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(_context, headerTitle + " info", Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);

        for (int i = 0; i < _listDataHeader.size(); i++) {
            if (i == groupPosition)
                continue;
            expandableListView.collapseGroup(i);
        }
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}