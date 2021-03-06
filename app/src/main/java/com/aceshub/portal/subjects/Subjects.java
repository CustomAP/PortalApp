package com.aceshub.portal.subjects;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.aceshub.portal.R;
import com.aceshub.portal.database.helper.DatabaseHelper;

import java.util.List;

public class Subjects extends Fragment {

    DatabaseHelper databaseHelper;
    private ExpandableListAdapter expandableListAdapter;
    private ExpandableListView expListView;

    public Subjects() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_subjects, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        databaseHelper = new DatabaseHelper(getContext());

        expListView = (ExpandableListView) view.findViewById(R.id.subjects_expandable_layout);
        expandableListAdapter = new ExpandableListAdapter(expListView, getContext(), getSubjectsList(), getSubjectCodeList());
        expListView.setAdapter(expandableListAdapter);
    }

    private List<String> getSubjectsList() {
        return databaseHelper.subjectsList()[0];
    }

    private List<String> getSubjectCodeList() {
        return databaseHelper.subjectsList()[1];
    }

}