package com.aceshub.portal.attendence;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.aceshub.portal.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AttendanceActivity extends AppCompatActivity {

    private static Thread update;

    private TextView presentNoTv, absentNoTv, dateTv;
    private RecyclerView recyclerView;
    private MisAdapter adapter;
    private Button saveAttendance;

    private SubMenu branches;
    private MenuItem currentBranchItem;

    private List<MisListItem> studentList = new ArrayList<>();

    public static void calculate() {
        update.run();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendance);

        setTitle("Attendance");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dateTv = (TextView) findViewById(R.id.mis_date_tv);
        dateTv.setText(new SimpleDateFormat("EEEE, dd MMM yyyy").format(System.currentTimeMillis()));

        saveAttendance = (Button) findViewById(R.id.button_attendance_save);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "Fresca-Regular.ttf");
        saveAttendance.setTypeface(typeface);

        recyclerView = (RecyclerView) findViewById(R.id.mislist_recyview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MisAdapter(studentList);
        recyclerView.setAdapter(adapter);

        presentNoTv = (TextView) findViewById(R.id.present_num_tv);
        absentNoTv = (TextView) findViewById(R.id.absent_num_tv);

        MisData.clear();
        createDummyData();
        addItems(studentList);
        MisData.setCurrentStudentList(studentList);

        MisData.addAbsent(-MisData.getAbsent());
        update = new Thread(new Runnable() {
            public void run() {
                absentNoTv.post(new Runnable() {
                    public void run() {
                        absentNoTv.setText("A : ");
                        absentNoTv.append(String.valueOf(studentList.size() - getPresentNumber()));
                        presentNoTv.setText("P : ");
                        presentNoTv.append(String.valueOf(getPresentNumber()));
                    }
                });
            }
        });
        update.run();
    }

    private void createDummyData() {
        studentList.add(new MisListItem("111407001", "Hello Electronics", "EnTC", true));
        studentList.add(new MisListItem("111407002", "Hi Electronics", "EnTC", true));
        studentList.add(new MisListItem("111407003", "Bye Electronics", "EnTC", true));
        studentList.add(new MisListItem("111407004", "Goodbye Electronics", "EnTC", true));
        studentList.add(new MisListItem("111407005", "Good Afternoon Electronics", "EnTC", true));
        studentList.add(new MisListItem("111403001", "Hello Computer", "COMP", true));
        studentList.add(new MisListItem("111403002", "Hi Computer", "COMP", true));
        studentList.add(new MisListItem("111403003", "Bye Computer", "COMP", true));
        studentList.add(new MisListItem("111403004", "Goodbye Computer", "COMP", true));
        studentList.add(new MisListItem("111403005", "Good Afternoon Computer", "COMP", true));
        studentList.add(new MisListItem("111405001", "Hello Electrical", "ELEC", true));
        studentList.add(new MisListItem("111405002", "Hi Electrical", "ELEC", true));
        studentList.add(new MisListItem("111405003", "Bye Electrical", "ELEC", true));
        studentList.add(new MisListItem("111405004", "Goodbye Electrical", "ELEC", true));
        studentList.add(new MisListItem("111405005", "Good Afternoon Electrical", "ELEC", true));
        adapter.notifyDataSetChanged();
    }

    public void addItems(List<MisListItem> items) {
        for (MisListItem item : items) {
            MisData.addData(item);
        }
    }

    private int getPresentNumber() {
        int totalPresent = 0;
        for (MisListItem item : studentList) {
            if (item.isPresent())
                totalPresent++;
        }
        MisData.setAbsent(studentList.size() - totalPresent);
        return totalPresent;
    }

    public void saveAttendance(View view) {
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.attendance_menu, menu);

        branches = menu.addSubMenu(12, 100, 0, "Branch");
        branches.setHeaderTitle("Select Branch");
        branches.setGroupCheckable(1, true, true);
        currentBranchItem = branches.add(1, 0, 0, "All").setCheckable(true).setChecked(true);
        branches.add(1, 1, 1, "EnTC").setCheckable(true);
        branches.add(1, 2, 2, "Comp Sci").setCheckable(true);
        branches.add(1, 3, 3, "Electrical").setCheckable(true);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menuitem_mark_all_present:
                markAll(true);
                break;
            case R.id.menuitem_mark_all_absent:
                markAll(false);
                break;
            case 0:
                setBranchwiseData(0);
                currentBranchItem.setChecked(false);
                currentBranchItem = item;
                item.setChecked(true);
                break;
            case 1:
                setBranchwiseData(1);
                currentBranchItem.setChecked(false);
                currentBranchItem = item;
                item.setChecked(true);
                break;
            case 2:
                setBranchwiseData(2);
                currentBranchItem.setChecked(false);
                currentBranchItem = item;
                item.setChecked(true);
                break;
            case 3:
                setBranchwiseData(3);
                currentBranchItem.setChecked(false);
                currentBranchItem = item;
                item.setChecked(true);
                break;
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return true;
    }

    private void markAll(boolean isPresent) {
        for (MisListItem item : studentList) {
            item.setPresent(isPresent);
        }
        adapter.notifyDataSetChanged();
        update.run();
    }

    public void setBranchwiseData(int branchCode) {
        String branchName;

        switch (branchCode) {
            case 0:
                branchName = "ALL";
                break;
            case 1:
                branchName = "EnTC";
                break;
            case 2:
                branchName = "COMP";
                break;
            case 3:
                branchName = "ELEC";
                break;
            default:
                branchName = "INVALID";
                break;
        }

        if (!branchName.equals("INVALID") && !branchName.equals("ALL")) {
            studentList.clear();
            Toast.makeText(this, branchName, Toast.LENGTH_SHORT).show();
            for (MisListItem item : MisData.getData()) {
                if (item.getBranch().equals(branchName))
                    studentList.add(item);
            }
            adapter.notifyDataSetChanged();
        } else {
            studentList.clear();
            for (MisListItem item : MisData.getData())
                studentList.add(item);
            adapter.notifyDataSetChanged();
        }
        MisData.setCurrentStudentList(studentList);
        update.run();
    }
}
