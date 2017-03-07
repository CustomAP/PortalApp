package com.aceshub.portal.studentstats;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aceshub.portal.R;

import java.util.ArrayList;
import java.util.List;

public class StudentStatsFragment extends Fragment {

    private RecyclerView recyclerView;
    private StudentStatsAdapter adapter;
    private List<StudentStatsItem> studentList ;

    public StudentStatsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_studentstats, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.student_rec_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        studentList = new ArrayList<>();
        generateData();

        adapter = new StudentStatsAdapter(studentList);
        recyclerView.setAdapter(adapter);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        recyclerView = null;
        studentList  = null;
        adapter = null;
    }

    private void generateData() {
        studentList.add(new StudentStatsItem("Hello World", "90%"));
        studentList.add(new StudentStatsItem("Hello World", "90%"));
        studentList.add(new StudentStatsItem("Hello World", "90%"));
        studentList.add(new StudentStatsItem("Hello World", "90%"));
        studentList.add(new StudentStatsItem("Hello World", "90%"));
        studentList.add(new StudentStatsItem("Hello World", "90%"));
        studentList.add(new StudentStatsItem("Hello World", "90%"));
        studentList.add(new StudentStatsItem("Hello World", "90%"));
        studentList.add(new StudentStatsItem("Hello World", "90%"));
        studentList.add(new StudentStatsItem("Hello World", "90%"));
        studentList.add(new StudentStatsItem("Hello World", "90%"));
        studentList.add(new StudentStatsItem("Hello World", "90%"));
        studentList.add(new StudentStatsItem("Hello World", "90%"));
        studentList.add(new StudentStatsItem("Hello World", "90%"));
        studentList.add(new StudentStatsItem("Hello World", "90%"));
        studentList.add(new StudentStatsItem("Hello World", "90%"));
        studentList.add(new StudentStatsItem("Hello World", "90%"));
    }
}
