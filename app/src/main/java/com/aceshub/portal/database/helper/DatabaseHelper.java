package com.aceshub.portal.database.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.aceshub.portal.database.model.FacultySubjectMappingView;

import java.util.ArrayList;

/**
 * Created by komal on 20/2/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DBName = "attendance.db";
    private static final String facultySubMapViewTable = "FacultySubjectMappingView";
    private static final String studentSubMapTable = "StudentSubjectMappingView";
    private static final String subAttendanceInfoTable = "SubjectAttendanceInfo";
    private static final String studentSubAttendanceTable = "StudentSubjectAttendance";
    private static final String subAttendanceInfoFlagTable = "SubjectAttendanceInfoFlag";
    private static final String timetableTable = "Timetable";
    private static final String column_FID = "FID";
    private static final String column_subCode = "SubjectCode";
    private static final String column_subTitle = "SubjectTitle";
    private static final String column_divID = "DivisionID";
    private static final String column_subType = "SubjectType";
    private static final String column_facultySubMapID = "FacultySubjectMappingID";
    private static final String column_branchName = "BranchName";
    private static final String column_SID = "SID";
    private static final String column_stRegCode = "StudentRegCode";
    private static final String column_stName = "StudentName";
    private static final String column_SIID = "SIID";
    private static final String column_flagID = "FlagID";
    private static final String column_sDate = "SDate";
    private static final String column_sTime = "STime";
    private static final String column_devDate = "DevDate";
    private static final String column_devTime = "DevTime";
    private static final String column_sync = "Sync";
    private static final String column_status = "Status";
    private static final String column_day = "Day";
    private static final String column_start = "StartTime";
    private static final String column_end = "EndTime";
    private static final String column_flag = "Flag";
    private static final String column_abbreviation = "Abbreviation";

    private static final int DBVersion = 1;

    public DatabaseHelper(Context context) {
        super(context, DBName, null, DBVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createFacultySubMapView = "CREATE TABLE " + facultySubMapViewTable +
                " (" + column_FID + " INT, "
                + column_subCode + " VARCHAR(16), "
                + column_subTitle + " VARCHAR(32), "
                + column_subType + " VARCHAR(16), "
                + column_divID + " INT, "
                + column_facultySubMapID + " VARCHAR(16), "
                + column_branchName + " VARCHAR(16), "
                + column_abbreviation + " VARCHAR(10), "
                + column_sync + " BOOLEAN, "
                + "PRIMARY KEY (" + column_FID + "));";


        String createStudentSubMap = "CREATE TABLE " + studentSubMapTable +
                " (" + column_SID + " INT, "
                + column_facultySubMapID + " VARCHAR(16), "
                + column_stRegCode + " VARCHAR(16), "
                + column_stName + " VARCHAR(50), "
                + column_subCode + " VARCHAR(16), "
                + column_subTitle + " VARCHAR(32), "
                + column_subType + " VARCHAR(16), "
                + column_divID + " INT, "
                + column_branchName + " VARCHAR(32), "
                + column_abbreviation + " VARCHAR(10), "
                + column_sync + " BOOLEAN, "
                + "PRIMARY KEY (" + column_SID + "))";

        String createSubAttendanceInfo = "CREATE TABLE " + subAttendanceInfoTable +
                " (" + column_SIID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + column_FID + " INT, "
                + column_flagID + " INT, "
                + column_sDate + " DATE, "
                + column_sTime + " TIME, "
                + column_devDate + " DATE, "
                + column_devTime + " TIME, "
                + column_sync + " BOOLEAN, "
                + "FOREIGN KEY (" + column_FID + ") REFERENCES "
                + facultySubMapViewTable + "(" + column_FID + ") "
                + "ON DELETE SET NULL);";

        String createStudentSubAttendance = "CREATE TABLE " + studentSubAttendanceTable +
                " (" + column_SIID + " INT, "
                + column_SID + " INT, "
                + column_status + " BOOLEAN, "
                + column_sync + " BOOLEAN, "
                + "PRIMARY KEY(" + column_SIID + ", " + column_SID + ")"
                + "FOREIGN KEY (" + column_SID + ") REFERENCES "
                + studentSubMapTable + "(" + column_SID + ") "
                + "ON DELETE CASCADE, "
                + "FOREIGN KEY (" + column_SIID + ") REFERENCES "
                + subAttendanceInfoTable + "(" + column_SIID + ") "
                + "ON DELETE CASCADE);";

        String createSubjectAttendanceInfoFlag = "CREATE TABLE " + subAttendanceInfoFlagTable +
                " (" + column_flagID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + column_flag + " VARCHAR(10));";

        String createTimetable = "CREATE TABLE " + timetableTable +
                " (" + column_subCode + " VARCHAR(16), "
                + column_subTitle + " VARCHAR(32), "
                + column_day + " VARCHAR(8), "
                + column_start + " TIME, "
                + column_end + " TIME, "
                + "PRIMARY KEY (" + column_subCode + "));";
        try {
            db.execSQL(createFacultySubMapView);
            db.execSQL(createStudentSubMap);
            db.execSQL(createSubAttendanceInfo);
            db.execSQL(createStudentSubAttendance);
            db.execSQL(createTimetable);
            db.execSQL(createSubjectAttendanceInfoFlag);
            Log.d("Database", "Tables Created");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql0 = "DROP TABLE IF EXISTS '" + facultySubMapViewTable + "'";
        String sql1 = "DROP TABLE IF EXISTS '" + studentSubMapTable + "'";
        String sql2 = "DROP TABLE IF EXISTS '" + subAttendanceInfoTable + "'";
        String sql3 = "DROP TABLE IF EXISTS '" + studentSubAttendanceTable + "'";
        String sql4 = "DROP TABLE IF EXISTS '" + timetableTable + "'";
        String sql5 = "DROP TABLE IF EXISTS '" + subAttendanceInfoFlagTable + "'";
        db.execSQL(sql0);
        db.execSQL(sql1);
        db.execSQL(sql2);
        db.execSQL(sql3);
        db.execSQL(sql4);
        db.execSQL(sql5);
        onCreate(db);
    }

    public void InsertFacultySubjects(FacultySubjectMappingView facultySubjectMappingView) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(column_FID, facultySubjectMappingView.getFid());
        values.put(column_subCode, facultySubjectMappingView.getSubCode());
        values.put(column_subTitle, facultySubjectMappingView.getSubTitle());
        values.put(column_subType, facultySubjectMappingView.getSubType());
        values.put(column_divID, facultySubjectMappingView.getDivID());
        values.put(column_facultySubMapID, facultySubjectMappingView.getFacultySubMapID());
        values.put(column_branchName, facultySubjectMappingView.getBranch());
        values.put(column_abbreviation, facultySubjectMappingView.getAbbreviation());
        values.put(column_sync, facultySubjectMappingView.isSync());

        sqLiteDatabase.insert(facultySubMapViewTable, null, values);
    }

   /* public ArrayList<FacultySubjectMappingView> getFacultySubjects() {
        List<FacultySubjectMappingView> todos = new ArrayList<FacultySubjectMappingView>();
        String selectQuery = "SELECT  FROM " + facultySubMapViewTable;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Todo td = new Todo();
                td.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                td.setNote((c.getString(c.getColumnIndex(KEY_TODO))));
                td.setCreatedAt(c.getString(c.getColumnIndex(KEY_CREATED_AT)));

                // adding to todo list
                todos.add(td);
            } while (c.moveToNext());
        }

        return todos;
    }*/
}

