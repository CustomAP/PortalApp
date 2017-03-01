package com.aceshub.portal.server_connection;

import android.content.Context;
import android.util.Log;

import com.aceshub.portal.database.helper.DatabaseHelper;
import com.aceshub.portal.database.model.FacultySubjectMappingView;
import com.aceshub.portal.database.model.StudentSubjectMappingView;
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

public class SubjectsEnrolledStudents {

    String subsstudents = "http://10.10.1.6:9999/atten/index.php/welcome/third";
    AQuery aQuery;
    String id;
    int ret = 0;
    String SubjectCode;
    String SubjectTitle;
    int DivisionID;
    String PicklistValueName;
    String BranchName;
    String Abbreviation;
    int FacultySubjectMappingID;
    String SubjectType;
    DatabaseHelper databaseHelper;
    int SID;
    String StudentRegCode, NameofStudent;
    StudentSubjectMappingView studentSubjectMappingView;

    public SubjectsEnrolledStudents(Context context) {
        databaseHelper = new DatabaseHelper(context);
        studentSubjectMappingView = new StudentSubjectMappingView();
        aQuery = new AQuery(context);
    }


    public void getSubjectsEnrolledStudents() {
        ArrayList<FacultySubjectMappingView> arrayList = databaseHelper.getFacultySubjects();
        for (FacultySubjectMappingView facultySubjectMappingView : arrayList) {
            final String subcode = facultySubjectMappingView.getSubCode();
            final int divid = facultySubjectMappingView.getDivID();
            final int fsmid = facultySubjectMappingView.getFacultySubMapID();

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("subcode", subcode);
            params.put("divid", divid);
            params.put("fsmid", fsmid);
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

                                Log.d("SID", ""+SID);
                                Log.d("StudentRegCode", ""+StudentRegCode);
                                Log.d("NameofStudent", ""+NameofStudent);

                                studentSubjectMappingView.setSubCode(subcode);
                                studentSubjectMappingView.setDivisionID(divid);
                                studentSubjectMappingView.setFacultySubjectMappingID(fsmid);
                                studentSubjectMappingView.setSID(SID);
                                studentSubjectMappingView.setStregcode(StudentRegCode);
                                studentSubjectMappingView.setNameofstudent(NameofStudent);
                                studentSubjectMappingView.setSync(0);
                                studentSubjectMappingView.setSubType("");
                                studentSubjectMappingView.setSubTitle("");
                                studentSubjectMappingView.setAbbreviation("");
                                studentSubjectMappingView.setBranchname("");

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }else{}
                }
            });
        }
    }
}
