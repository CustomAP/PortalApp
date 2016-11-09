package com.aceshub.portal.subjects.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.shri.list.R;

public class ListActivity extends AppCompatActivity {
    private RecyclerView recview;
    private com.example.shri.list.adapter.DerpAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recview = (RecyclerView)findViewById(R.id.rec_list);

        recview.setLayoutManager(new LinearLayoutManager(this));

        adapter = new com.example.shri.list.adapter.DerpAdapter(com.example.shri.list.model.DerpData.getListData(), this);
        recview.setAdapter(adapter);


    }
}
