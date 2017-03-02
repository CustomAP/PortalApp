package com.aceshub.portal.server_connection;

import android.content.Context;
import android.util.Log;
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

public class FacultySubjectMapping {

    private final String falsubs = "http://10.10.1.6:9999/atten/index.php/welcome/first";
    private AQuery aQuery;
    private String id;
    private int ret = 0;
    private com.aceshub.portal.database.model.FacultySubjectMappingView facultySubjectMappingView;
    private int FID;
    private String SubjectCode, SubjectType, SubjectTitle, PicklistValueName, BranchName, Abbreviation;
    private int DivisionID, FacultySubjectMappingID;
    private DatabaseHelper databaseHelper;

    public FacultySubjectMapping(String id, Context context) {
        facultySubjectMappingView = new com.aceshub.portal.database.model.FacultySubjectMappingView();
        databaseHelper = new DatabaseHelper(context);
        aQuery = new AQuery(context);
        this.id = id;
    }

    public void  run() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        aQuery.ajax(falsubs, params, JSONObject.class,new AjaxCallback<JSONObject>() {
            @Override
            public void callback(String url, JSONObject json, AjaxStatus status) {
                if (json != null) {
                    try {
                        int length = json.getInt("length");

                        String in = json.getString("ResultSet");

                        JSONObject reader = new JSONObject(in);
                        for (int i = 0; i < length; i++) {
                            JSONObject obj = reader.getJSONObject(String.valueOf(i));
                            FID = obj.getInt("FID");
                            SubjectCode = obj.getString("SubjectCode");
                            SubjectTitle = obj.getString("SubjectTitle");
                            DivisionID = obj.getInt("DivisionID");
                            PicklistValueName = obj.getString("PicklistValueName");
                            BranchName = obj.getString("BranchName");
                            Abbreviation = obj.getString("Abbreviation");
                            FacultySubjectMappingID = obj.getInt("FacultySubjectMappingID");

                            //Setter methods of FacultySubjectsMappingView
                            facultySubjectMappingView.setBranch(BranchName);
                            facultySubjectMappingView.setAbbreviation(Abbreviation);
                            facultySubjectMappingView.setDivID(DivisionID);
                            facultySubjectMappingView.setFacultySubMapID(FacultySubjectMappingID);
                            facultySubjectMappingView.setFid(FID);
                            facultySubjectMappingView.setSubCode(SubjectCode);
                            facultySubjectMappingView.setSubTitle(SubjectTitle);
                            facultySubjectMappingView.setSubType(PicklistValueName);
                            facultySubjectMappingView.setSync(0);


                            //Inserting row in database
                            databaseHelper.InsertFacultySubjectMappingView(facultySubjectMappingView);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();

                    }
                }

            }
        });

    }
}
