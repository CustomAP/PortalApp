package com.aceshub.portal.subjects.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aceshub.portal.subjects.adapter.DerpAdapter;
import com.aceshub.portal.subjects.model.DerpData;
import com.aceshub.portal.R;

public class ListActivity extends AppCompatActivity {
    private RecyclerView recview;
    private DerpAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recview = (RecyclerView)findViewById(R.id.rec_list);

        recview.setLayoutManager(new LinearLayoutManager(this));

        adapter = new DerpAdapter(DerpData.getListData(), this);
        recview.setAdapter(adapter);


    }
}
