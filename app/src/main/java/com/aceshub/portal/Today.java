package com.aceshub.portal;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.aceshub.portal.today.AddLectureDialog;
import com.aceshub.portal.today.TodayAdapter;
import com.aceshub.portal.today.TodayData;
import com.aceshub.portal.today.TodayListItem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Today extends Fragment {

    public static TodayAdapter adapter;
    TextView dateTv;
    FloatingActionButton addLectureFab;

    private RecyclerView recyclerView;

    public Today() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_today, container, false);
    }
/*
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.today_recview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new TodayAdapter(TodayData.getData(), getActivity());
        recyclerView.setAdapter(adapter);

        dateTv = (TextView) view.findViewById(R.id.today_date_tv);
        dateTv.setText("");
        dateTv.setText(new SimpleDateFormat("EEEE, dd MMM yyyy").format(System.currentTimeMillis()));

        TodayData.clear();
        List<TodayListItem> items = new ArrayList<>();
        items.add(new TodayListItem("10:00 - 11:00", "Maths"));
        items.add(new TodayListItem("12:00 - 01:00", "Physics"));
        items.add(new TodayListItem("03:00 - 04:00", "Chemistry"));
        items.add(new TodayListItem("04:00 - 05:00", "Biology"));
        items.add(new TodayListItem("05:00 - 06:00", "CS:GO"));
        addItems(items);

        addLectureFab = (FloatingActionButton) view.findViewById(R.id.add_lecture_fab);
        addLectureFab.setOnClickListener(addLecture);
    }

    View.OnClickListener addLecture = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AddLectureDialog dialog = new AddLectureDialog(getActivity());
            dialog.show();
        }
    };

    public void addItems(List<TodayListItem> items) {
        for (TodayListItem item : items) {
            TodayData.addData(item);
            adapter.notifyItemInserted(TodayData.getSize() - 1);
        }
    }*/

}
