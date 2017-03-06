package com.aceshub.portal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aceshub.portal.database.helper.DatabaseHelper;
import com.aceshub.portal.today.AddLectureDialog;
import com.aceshub.portal.today.TodayAdapter;
import com.aceshub.portal.today.TodayData;
import com.aceshub.portal.today.TodayListItem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Today extends Fragment {

    public static TodayAdapter adapter;
    TextView dateTv;
    FloatingActionButton addLectureFab;
    DatabaseHelper databaseHelper;

    View.OnClickListener addLecture = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AddLectureDialog dialog = new AddLectureDialog(getActivity());
            dialog.show();
        }
    };

    private RecyclerView recyclerView;

    public Today() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_today, container, false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        databaseHelper.close();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.today_recview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new TodayAdapter(TodayData.getData(), getContext());
        recyclerView.setAdapter(adapter);

        databaseHelper = new DatabaseHelper(getActivity());
        getBranchList();

        dateTv = (TextView) view.findViewById(R.id.today_date_tv);
        dateTv.setText("");
        dateTv.setText(new SimpleDateFormat("EEEE, dd MMM yyyy").format(System.currentTimeMillis()));

        TodayData.clear();
        List<TodayListItem> items = new ArrayList<>();
        List<String> subjects = databaseHelper.subjectsList()[0];
        for(String a:subjects){
            items.add(new TodayListItem("10:00 - 11:00", a));
        }
        addItems(items);

        addLectureFab = (FloatingActionButton) view.findViewById(R.id.add_lecture_fab);
        addLectureFab.setOnClickListener(addLecture);
    }



    public void addItems(List<TodayListItem> items) {
        for (TodayListItem item : items) {
            TodayData.addData(item);
            adapter.notifyItemInserted(TodayData.getSize() - 1);
        }
    }

    private void getAllStudentsName(){
        List<String> list= databaseHelper.getAllStudentList("ETC-15001")[0];
        Log.d("Name", ""+list.size());
    }

    private void getAllStudentsRegCode(){
        List<String> list= databaseHelper.getAllStudentList("ETC-15001")[1];
        Log.d("RegCode", ""+list.size());
    }

    private void getStudentsNameBranchandDivisionWise() {
        List<String> list= databaseHelper.getStudentListBranchAndDivisionWise("ETC-15001", "Civil Engineering", "Div-1")[0];
        for(int i = 0; i < list.size(); i++) {
            Log.d("NameBandDWise", "" + list.get(i));
        }
    }

    private void getStudentsRegCodeBranchandDivisionWise(){
        List<String> list= databaseHelper.getStudentListBranchAndDivisionWise("ETC-15001", "Civil Engineering", "Div-1")[1];
        for(int i = 0; i < list.size(); i++) {
            Log.d("RegCodeBandDWise", "" + list.get(i));
        }
    }

    private void getBranchList() {
        List<String> list = databaseHelper.getBranchAndDivision("ETC-15001")[0];
        for(int i = 0; i < list.size(); i++) {
            Log.d("Branch", "" + list.get(i));
        }
    }

    private void getDivisionList(){
        List<String> list= databaseHelper.getBranchAndDivision("ETC-15001")[1];
        for(int i = 0; i < list.size(); i++) {
            Log.d("Division", "" + list.get(i));
        }
    }
}
