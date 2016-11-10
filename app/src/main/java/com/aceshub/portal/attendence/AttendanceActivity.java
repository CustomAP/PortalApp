package com.aceshub.portal.attendence;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.aceshub.portal.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AttendanceActivity extends AppCompatActivity {

    public int presentNo, absentNo;
    TextView presentNoTv, absentNoTv;
    TextView dateTv;
    private RecyclerView recyclerView;
    private MisAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendance);

        dateTv = (TextView) findViewById(R.id.mis_date_tv);
        dateTv.setText(new SimpleDateFormat("EEEE, dd MMM yyyy").format(System.currentTimeMillis()));

        recyclerView = (RecyclerView) findViewById(R.id.mislist_recyview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MisAdapter(MisData.getData(), this);
        recyclerView.setAdapter(adapter);

        presentNoTv = (TextView) findViewById(R.id.present_num_tv);
        absentNoTv = (TextView) findViewById(R.id.absent_num_tv);

        MisData.clear();
        List<MisListItem> items = new ArrayList<>();
        items.add(new MisListItem("111407001"));
        items.add(new MisListItem("111407011"));
        items.add(new MisListItem("111407007"));
        items.add(new MisListItem("111407042"));
        items.add(new MisListItem("111407042"));
        items.add(new MisListItem("111407042"));
        items.add(new MisListItem("111407042"));
        items.add(new MisListItem("111407042"));
        items.add(new MisListItem("111407042"));
        items.add(new MisListItem("111407042"));
        items.add(new MisListItem("111407042"));
        items.add(new MisListItem("111407042"));
        items.add(new MisListItem("111407042"));
        addItems(items);

        absentNo = items.size();
        presentNo = 0;

        absentNoTv.setText("A : " + absentNo);
        presentNoTv.setText("P : " + presentNo);
    }

    public void addItems(List<MisListItem> items) {
        for (MisListItem item : items) {
            MisData.addData(item);
            adapter.notifyItemInserted(MisData.getSize() - 1);
        }
    }

    public void submit(View view) {
        finish();
    }
}
