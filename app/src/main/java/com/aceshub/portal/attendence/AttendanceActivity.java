package com.aceshub.portal.attendence;

import android.content.Intent;
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
import com.aceshub.portal.database.helper.DatabaseHelper;

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
    DatabaseHelper databasehelper;
    private List<MisListItem> studentList = new ArrayList<>();
    static String subject_id, subject_name;
    public static void calculate() {
        update.run();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendance);
        subject_id = getIntent().getExtras().getString("subject_id");
        subject_name = getIntent().getExtras().getString("subject_name");
        setTitle("Attendance");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        databasehelper = new DatabaseHelper(getApplicationContext());
        dateTv = (TextView) findViewById(R.id.mis_date_tv);
        dateTv.setText(new SimpleDateFormat("EEEE, dd MMM yyyy").format(System.currentTimeMillis()));

        saveAttendance = (Button) findViewById(R.id.button_attendance_save);

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

                        absentNoTv.setText(String.valueOf(studentList.size() - getPresentNumber()));

                        presentNoTv.setText(String.valueOf(getPresentNumber()));
                    }
                });
            }
        });
        update.run();
    }

    private void createDummyData() {
        List<String> student_name_list = databasehelper.getAllStudentList(subject_id)[0];
        List<String> student_regcode_list = databasehelper.getAllStudentList(subject_id)[1];
        int i;
        for(i = 0; i < student_name_list.size();i++){
            studentList.add(new MisListItem(student_regcode_list.get(i), student_name_list.get(i),subject_name, true));
        }
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
        List<String> branch_names = databasehelper.getBranchAndDivision(subject_id)[0];
        List<String> branch_division = databasehelper.getBranchAndDivision(subject_id)[1];


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
