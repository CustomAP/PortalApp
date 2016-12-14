package com.aceshub.portal.attendence;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.aceshub.portal.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AttendanceActivity extends AppCompatActivity {

    private static Thread update;
    TextView dateTv;
    private TextView presentNoTv, absentNoTv;
    private RecyclerView recyclerView;
    private MisAdapter adapter;
    private Button saveAttendance;

    public static void calculate() {
        update.run();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendance);

        setTitle("Attendance");

        dateTv = (TextView) findViewById(R.id.mis_date_tv);
        dateTv.setText(new SimpleDateFormat("EEEE, dd MMM yyyy").format(System.currentTimeMillis()));

        saveAttendance = (Button) findViewById(R.id.button_attendance_save);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "Fresca-Regular.ttf");
        saveAttendance.setTypeface(typeface);

        recyclerView = (RecyclerView) findViewById(R.id.mislist_recyview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MisAdapter(MisData.getData(), this);
        recyclerView.setAdapter(adapter);

        presentNoTv = (TextView) findViewById(R.id.present_num_tv);
        absentNoTv = (TextView) findViewById(R.id.absent_num_tv);

        MisData.clear();
        List<MisListItem> items = new ArrayList<>();
        items.add(new MisListItem("111407001"));
        items.add(new MisListItem("111407002"));
        items.add(new MisListItem("111407003"));
        items.add(new MisListItem("111407004"));
        items.add(new MisListItem("111407005"));
        items.add(new MisListItem("111407006"));
        items.add(new MisListItem("111407007"));
        items.add(new MisListItem("111407008"));
        items.add(new MisListItem("111407009"));
        items.add(new MisListItem("111407010"));
        items.add(new MisListItem("111407011"));
        items.add(new MisListItem("111407012"));
        items.add(new MisListItem("111407013"));
        addItems(items);

        MisData.addAbsent(-MisData.getAbsent());
        update = new Thread(new Runnable() {
            public void run() {
                absentNoTv.post(new Runnable() {
                    public void run() {
                        absentNoTv.setText("A : " + MisData.getAbsent());
                        presentNoTv.setText("P : " + (MisData.getSize() - MisData.getAbsent()));
                    }
                });
            }
        });
        update.run();
    }

    public void addItems(List<MisListItem> items) {
        for (MisListItem item : items) {
            MisData.addData(item);
            adapter.notifyItemInserted(MisData.getSize() - 1);
        }
    }

    public void saveAttendance(View view) {
        finish();
    }

}
