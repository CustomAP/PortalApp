package com.aceshub.portal.subject_details;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.aceshub.portal.R;

import java.util.ArrayList;

/**
 * Created by Ashish Pawar on 3/7/2017.
 */

public class SubjectDetailsActivity extends AppCompatActivity {
    private RecyclerView rvSubjectDetails;
    private ArrayList<String> dates,days;
    private ArrayList<Integer> present,absent;
    private RecyclerView.LayoutManager manager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_subject_details);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(getIntent().getStringExtra("subject_name"));

        setData();

        rvSubjectDetails=(RecyclerView)findViewById(R.id.subject_details_rec_view);
        manager=new LinearLayoutManager(getApplicationContext());
        rvSubjectDetails.setItemAnimator(new DefaultItemAnimator());
        rvSubjectDetails.setLayoutManager(manager);

        SubjectDetailsAdapter adapter=new SubjectDetailsAdapter(dates,days,present,absent);
        rvSubjectDetails.setAdapter(adapter);

    }

    private void setData() {
        dates=new ArrayList<>();
        days=new ArrayList<>();
        present=new ArrayList<>();
        absent=new ArrayList<>();

        //test
        for(int i=0;i<25;i++) {
            dates.add("07/03/2017");
            days.add("Tuesday");
            present.add(15);
            absent.add(8);
        }
    }
}
