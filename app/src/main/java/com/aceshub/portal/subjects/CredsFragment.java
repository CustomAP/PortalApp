package com.aceshub.portal.subjects;


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

public class CredsFragment extends Fragment {

    private RecyclerView recyclerView;
    private CredsAdapter adapter;
    private List<CredsItem> studentList = new ArrayList<>();

    public CredsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_creds, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        generateData();

        recyclerView = (RecyclerView) view.findViewById(R.id.student_rec_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new CredsAdapter(studentList);
        recyclerView.setAdapter(adapter);
    }

    private void generateData() {
        studentList.add(new CredsItem("Hello World", "90%"));
        studentList.add(new CredsItem("Hello World", "90%"));
        studentList.add(new CredsItem("Hello World", "90%"));
        studentList.add(new CredsItem("Hello World", "90%"));
        studentList.add(new CredsItem("Hello World", "90%"));
        studentList.add(new CredsItem("Hello World", "90%"));
        studentList.add(new CredsItem("Hello World", "90%"));
        studentList.add(new CredsItem("Hello World", "90%"));
        studentList.add(new CredsItem("Hello World", "90%"));
        studentList.add(new CredsItem("Hello World", "90%"));
        studentList.add(new CredsItem("Hello World", "90%"));
        studentList.add(new CredsItem("Hello World", "90%"));
        studentList.add(new CredsItem("Hello World", "90%"));
        studentList.add(new CredsItem("Hello World", "90%"));
        studentList.add(new CredsItem("Hello World", "90%"));
        studentList.add(new CredsItem("Hello World", "90%"));
        studentList.add(new CredsItem("Hello World", "90%"));
    }
}
