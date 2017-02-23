package com.aceshub.portal.subjects;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.aceshub.portal.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Subjects extends Fragment {

    ExpandableListAdapter expandableListAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

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

        expListView = (ExpandableListView) view.findViewById(R.id.subjects_expandable_layout);
        prepareListData();
        expandableListAdapter = new ExpandableListAdapter(expListView, getContext(), listDataHeader);
        expListView.setAdapter(expandableListAdapter);
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();

        // Adding child data
        listDataHeader.add("Subject1");
        listDataHeader.add("Subject2");
        listDataHeader.add("Subject3");

        // Header, Child data
        listDataChild.put(listDataHeader.get(0), null);
        listDataChild.put(listDataHeader.get(1), null);
        listDataChild.put(listDataHeader.get(2), null);
    }
}