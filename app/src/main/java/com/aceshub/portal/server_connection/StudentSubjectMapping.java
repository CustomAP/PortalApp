package com.aceshub.portal.server_connection;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.aceshub.portal.database.helper.DatabaseHelper;
import com.aceshub.portal.database.model.FacultySubjectMappingView;
import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by amarpreetsingha on 1/3/17.
 */

public class StudentSubjectMapping  {

    String subsstudents = "http://10.10.1.6:9999/atten/index.php/welcome/third";
    AQuery aQuery;
    String SubjectCode;
    String SubjectTitle;
    int DivisionID;
    String BranchName;
    String Abbreviation;
    String Division;
    int FacultySubjectMappingID;
    String SubjectType;
    DatabaseHelper databaseHelper;
    int SID;
    String StudentRegCode, NameofStudent;
    com.aceshub.portal.database.model.StudentSubjectMappingView studentSubjectMappingView;

    public StudentSubjectMapping(Context context) {
        databaseHelper = new DatabaseHelper(context);
        studentSubjectMappingView = new com.aceshub.portal.database.model.StudentSubjectMappingView();
        aQuery = new AQuery(context);
    }


    public void run() {
        ArrayList<FacultySubjectMappingView> arrayList = databaseHelper.getFacultySubjectMappingView();
        for (FacultySubjectMappingView facultySubjectMappingView : arrayList) {
            String  SubCode = facultySubjectMappingView.getSubCode();
            int DivID = facultySubjectMappingView.getDivID();
            int FSMID = facultySubjectMappingView.getFsmid();

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("SubjectCode", SubCode);
            params.put("DivisionID", DivID);
            params.put("FacultySubjectMappingID", FSMID);
            aQuery.ajax(subsstudents, params, JSONObject.class,new AjaxCallback<JSONObject>() {
                @Override
                public void callback(String url, JSONObject json, AjaxStatus status) {
                    if (json != null) {
                        try {
                            int length = json.getInt("length");

                            String in = json.getString("ResultSet");

                            JSONObject reader = new JSONObject(in);
                            for (int i = 0; i < length; i++) {

                                JSONObject obj = reader.getJSONObject(String.valueOf(i));
                                SID = obj.getInt("SID");
                                StudentRegCode = obj.getString("StudentRegCode");
                                NameofStudent = obj.getString("NameofStudent");
                                SubjectType = obj.getString("SubjectType");
                                SubjectTitle = obj.getString("SubjectTitle");
                                Abbreviation = obj.getString("Abbreviation");
                                BranchName = obj.getString("BranchName");
                                SubjectCode = obj.getString("SubjectCode");
                                DivisionID = obj.getInt("DivisionID");
                                Division = obj.getString("Division");
                                FacultySubjectMappingID = obj.getInt("FacultySubjectMappingID");


                                studentSubjectMappingView.setSubCode(SubjectCode);
                                studentSubjectMappingView.setDivisionID(DivisionID);
                                studentSubjectMappingView.setDivision(Division);
                                studentSubjectMappingView.setFacultySubjectMappingID(FacultySubjectMappingID);
                                studentSubjectMappingView.setSID(SID);
                                studentSubjectMappingView.setStregcode(StudentRegCode);
                                studentSubjectMappingView.setNameofstudent(NameofStudent);
                                studentSubjectMappingView.setSync(0);
                                studentSubjectMappingView.setSubType(SubjectType);
                                studentSubjectMappingView.setSubTitle(SubjectTitle);
                                studentSubjectMappingView.setAbbreviation(Abbreviation);
                                studentSubjectMappingView.setBranchname(BranchName);

                                //Inserting row in database
                                databaseHelper.InsertStudentSubjectMappingView(studentSubjectMappingView);


                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }else{
                        Log.d("JSON", "NULL");
                    }
                }
            });

        }
    }
}
