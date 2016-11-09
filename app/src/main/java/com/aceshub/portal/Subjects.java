package com.aceshub.portal;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aceshub.portal.subjects.adapter.DerpAdapter;
import com.aceshub.portal.subjects.model.DerpData;

import java.util.zip.Inflater;

/**
 * A simple {@link Fragment} subclass.
 */
public class Subjects extends Fragment {
    private RecyclerView recview;
    private DerpAdapter adapter;

    public Subjects() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.activity_list, container, false);
        recview = (RecyclerView)view.findViewById(R.id.rec_list);

        recview.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new DerpAdapter(DerpData.getListData(), getActivity());
        recview.setAdapter(adapter);

        return view;
    }


}
