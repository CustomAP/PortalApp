package com.aceshub.portal.attendence;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
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
import java.util.Arrays;
import java.util.List;

public class AttendanceActivity extends AppCompatActivity {


    private static String SUBJECT_ID, SUBJECT_NAME;
    private static Thread update;
    //Data
    private List<MisListItem> studentList = new ArrayList<>();
    private List<String> branchNames = new ArrayList<>();
    private List<String> divisionNames = new ArrayList<>();
    private DatabaseHelper databasehelper;
    private int BRANCH_CODE;
    //Views
    private TextView presentNoTv, absentNoTv, dateTv;
    private MisAdapter adapter;
    private Button saveAttendance;
    private MenuItem currentBranchItem;

    public static void calculate() {
        update.run();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendance);

        SUBJECT_ID = getIntent().getStringExtra("subject_id");
        SUBJECT_NAME = getIntent().getExtras().getString("subject_name");
        setTitle("Attendance");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Views
        databasehelper = new DatabaseHelper(getApplicationContext());
        dateTv = (TextView) findViewById(R.id.mis_date_tv);
        dateTv.setText(new SimpleDateFormat("EEEE, dd MMM yyyy").format(System.currentTimeMillis()));
        presentNoTv = (TextView) findViewById(R.id.present_num_tv);
        absentNoTv = (TextView) findViewById(R.id.absent_num_tv);
        saveAttendance = (Button) findViewById(R.id.button_attendance_save);

        //RecyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.mislist_recyview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MisAdapter(studentList);
        recyclerView.setAdapter(adapter);

        //Data
        MisData.clear();
        initializeData();
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

    private void initializeData() {
        branchNames = databasehelper.getBranchAndDivision(SUBJECT_ID)[0];
        divisionNames = databasehelper.getBranchAndDivision(SUBJECT_ID)[1];

        if (branchNames == null || divisionNames == null) {
            Toast.makeText(this, "NULL", Toast.LENGTH_SHORT).show();
            return;
        }

        Log.d("Branch", branchNames.get(0) + divisionNames.get(0));
        List<MisListItem> list = getMISList(0);
        for (MisListItem item : list)
            studentList.add(item);

        adapter.notifyDataSetChanged();

        BRANCH_CODE = 0;

        MisData.setData(studentList);
    }

    private List<MisListItem> getMISList(int branchCode) {
        List<MisListItem> list = new ArrayList<>();

        List<String> studentsNameList = databasehelper.getStudentListBranchAndDivisionWise(
                SUBJECT_ID, branchNames.get(branchCode), divisionNames.get(branchCode))[0];
        List<String> studentsMISList = databasehelper.getStudentListBranchAndDivisionWise(
                SUBJECT_ID, branchNames.get(branchCode), divisionNames.get(branchCode))[1];

        for (int i = 0; i < studentsNameList.size(); i++) {
            list.add(new MisListItem(studentsMISList.get(i), studentsNameList.get(i), SUBJECT_NAME, true));
        }

        return list;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.attendance_menu, menu);

        SubMenu branches = menu.addSubMenu(12, 100, 0, "Branch");
        branches.setHeaderTitle(Html.fromHtml("<font color = '#03A9F4'>Select Branch</font>"));
        branches.setGroupCheckable(1, true, true);

        String division = "<font color = '#757575'>" + divisionNames.get(0) + "</font>";
        String branch = " " + branchNames.get(0);

        currentBranchItem = branches.add(1, 0, 0, Html.fromHtml(division + branch)).setCheckable(true).setChecked(true);
        for (int i = 1; i < branchNames.size(); i++) {
            division = "<font color = '#757575'>" + divisionNames.get(i) + "</font>";
            branch = " " + branchNames.get(i);
            branches.add(1, i, i, Html.fromHtml(division + branch)).setCheckable(true).setChecked(false);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menuitem_mark_all_present:
                markAll(true);
                return true;

            case R.id.menuitem_mark_all_absent:
                markAll(false);
                return true;

            default:
                if (currentBranchItem == item)
                    return true;
                if (item.getItemId() > 54)
                    return false;
                setBranchWiseData(item.getItemId());
                currentBranchItem.setChecked(false);
                currentBranchItem = item;
                item.setChecked(true);
                return true;
        }
    }

    private void markAll(boolean isPresent) {
        for (MisListItem item : studentList) {
            item.setPresent(isPresent);
        }
        adapter.notifyDataSetChanged();
        update.run();
    }

    public void setBranchWiseData(int branchCode) {
        Toast.makeText(this, this.branchNames.get(branchCode), Toast.LENGTH_SHORT).show();

        studentList.clear();
        List<MisListItem> list = getMISList(branchCode);
        for (MisListItem item : list)
            studentList.add(item);
        adapter.notifyDataSetChanged();

        BRANCH_CODE = branchCode;

        MisData.setCurrentStudentList(studentList);
        update.run();
    }

    public void saveAttendance(View view) {
        //String FSMID = databasehelper.getFacultySubjectMappingFSMID();
        //String SIID = databasehelper.getFacultySubjectMappingSIID();
        List<String> SIDList = databasehelper.getStudentListBranchAndDivisionWise(
                SUBJECT_ID, branchNames.get(BRANCH_CODE), divisionNames.get(BRANCH_CODE))[2];
        List<Boolean> ATTENDANCE = new ArrayList<>();

        for (MisListItem item : studentList) {
            ATTENDANCE.add(item.isPresent());
        }

        //Original
        //Log.v("SAVE", "FSMID = " + FSMID + "  SIID = " + SIID + "\n" +
        //      "SIDs : " + Arrays.toString(SIDList.toArray()) + "\n" +
        //      "Attendence : " + Arrays.toString(ATTENDANCE.toArray()));

        //Temp
        Log.v("SAVE", "SIDs : " + Arrays.toString(SIDList.toArray()) + "\n" +
                "Attendence : " + Arrays.toString(ATTENDANCE.toArray()));
        finish();
    }
}
