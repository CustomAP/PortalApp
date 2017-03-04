package com.aceshub.portal.database.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.aceshub.portal.database.model.FacultySubjectMappingView;
import com.aceshub.portal.database.model.StudentSubjectMappingView;
import com.aceshub.portal.database.model.Timetable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DBName = "attendance.db";
    private static final String facultySubMapViewTable = "FacultySubjectMapping";
    private static final String studentSubMapTable = "StudentSubjectMapping";
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
    private static final String column_div = "Division";

    private static final int DBVersion = 1;

    public DatabaseHelper(Context context) {
        super(context, DBName, null, DBVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createFacultySubMapView = "CREATE TABLE " + facultySubMapViewTable +
                " (" + column_FID + " INT, "
                + column_subCode + " VARCHAR(16), "
                + column_subTitle + " VARCHAR(50), "
                + column_subType + " VARCHAR(16), "
                + column_divID + " INT, "
                + column_div + " VARCHAR(20), "
                + column_facultySubMapID + " INT, "
                + column_branchName + " VARCHAR(50), "
                + column_abbreviation + " VARCHAR(10), "
                + column_sync + " BOOLEAN, "
                + "PRIMARY KEY (" + column_FID + "));";


        String createStudentSubMap = "CREATE TABLE " + studentSubMapTable +
                " (" + column_SID + " INT, "
                + column_facultySubMapID + " INT, "
                + column_stRegCode + " VARCHAR(16), "
                + column_stName + " VARCHAR(50), "
                + column_subCode + " VARCHAR(16), "
                + column_subTitle + " VARCHAR(50), "
                + column_subType + " VARCHAR(16), "
                + column_divID + " INT, "
                + column_div + " VARCHAR(20), "
                + column_branchName + " VARCHAR(50), "
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
                + "PRIMARY KEY (" + column_subCode + ", "+ column_start+", "+ column_end+", "+ column_day+"));";
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

    public void InsertFacultySubjectMappingView(FacultySubjectMappingView facultySubjectMappingView) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(column_FID, facultySubjectMappingView.getFid());
        values.put(column_subCode, facultySubjectMappingView.getSubCode());
        values.put(column_subTitle, facultySubjectMappingView.getSubTitle());
        values.put(column_subType, facultySubjectMappingView.getSubType());
        values.put(column_divID, facultySubjectMappingView.getDivID());
        values.put(column_div, facultySubjectMappingView.getDiv());
        values.put(column_facultySubMapID, facultySubjectMappingView.getFacultySubMapID());
        values.put(column_branchName, facultySubjectMappingView.getBranch());
        values.put(column_abbreviation, facultySubjectMappingView.getAbbreviation());
        values.put(column_sync, facultySubjectMappingView.isSync());

        sqLiteDatabase.insert(facultySubMapViewTable, null, values);
    }

    public ArrayList<FacultySubjectMappingView> getFacultySubjectMappingView() {
        ArrayList<FacultySubjectMappingView> facultySubjectMappingViewList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + facultySubMapViewTable;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to arraylist
        if (c.moveToFirst()) {
            do {
                FacultySubjectMappingView facultySubjectMappingView = new FacultySubjectMappingView();
                facultySubjectMappingView.setFid(c.getInt(c.getColumnIndex(column_FID)));
                facultySubjectMappingView.setSubCode(c.getString((c.getColumnIndex(column_subCode))));
                facultySubjectMappingView.setSubTitle(c.getString(c.getColumnIndex(column_subTitle)));
                facultySubjectMappingView.setSubType(c.getString(c.getColumnIndex(column_subType)));
                facultySubjectMappingView.setDivID(c.getInt((c.getColumnIndex(column_divID))));
                facultySubjectMappingView.setDiv(c.getString((c.getColumnIndex(column_div))));
                facultySubjectMappingView.setFacultySubMapID((c.getInt(c.getColumnIndex(column_facultySubMapID))));
                facultySubjectMappingView.setBranch(c.getString(c.getColumnIndex(column_branchName)));
                facultySubjectMappingView.setAbbreviation(c.getString(c.getColumnIndex(column_abbreviation)));
                facultySubjectMappingView.setSync(c.getInt(c.getColumnIndex(column_sync)));
                // adding to facultysubjectmappingview
                facultySubjectMappingViewList.add(facultySubjectMappingView);
            } while (c.moveToNext());
        }

        c.close();

        return facultySubjectMappingViewList;
    }

    public List<String>[] subjectsList() {
        List<String> subjectList = new ArrayList<>();
        List<String> subjectCodeList = new ArrayList<>();
        String selectQuery = "SELECT DISTINCT " + column_subTitle + ", " + column_subCode + " FROM " + facultySubMapViewTable;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                subjectList.add(c.getString(c.getColumnIndex(column_subTitle)));
                subjectCodeList.add(c.getString(c.getColumnIndex(column_subCode)));
            } while (c.moveToNext());
        }

        c.close();

        List<String>[] result = new List[2];
        result[0] = subjectList;
        result[1] = subjectCodeList;

        return result;
    }

    public void InsertStudentSubjectMappingView(StudentSubjectMappingView studentSubjectMappingView) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(column_SID, studentSubjectMappingView.getSID());
        values.put(column_stRegCode, studentSubjectMappingView.getStregcode());
        values.put(column_stName, studentSubjectMappingView.getNameofstudent());
        values.put(column_subCode, studentSubjectMappingView.getSubCode());
        values.put(column_subTitle, studentSubjectMappingView.getSubTitle());
        values.put(column_subType, studentSubjectMappingView.getSubType());
        values.put(column_divID, studentSubjectMappingView.getDivisionID());
        values.put(column_div, studentSubjectMappingView.getDivision());
        values.put(column_facultySubMapID, studentSubjectMappingView.getFacultySubjectMappingID());
        values.put(column_branchName, studentSubjectMappingView.getBranchname());
        values.put(column_abbreviation, studentSubjectMappingView.getAbbreviation());
        values.put(column_sync, studentSubjectMappingView.getSync());

        sqLiteDatabase.insert(studentSubMapTable, null, values);
    }

    public void InsertTimeTable(Timetable timetable) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(column_subCode, timetable.getSubCode());
        values.put(column_subTitle, timetable.getSubTitle());
        values.put(column_day, timetable.getDay());
        values.put(column_start, String.valueOf(timetable.getStart()));
        values.put(column_end, String.valueOf(timetable.getEnd()));

        sqLiteDatabase.insert(timetableTable, null, values);
    }


    public List<String>[] getAllStudentList(String SubjectCode) {
        List<String> StudentName = new ArrayList<>();
        List<String> StudentRegCode = new ArrayList<>();
        try {
            String selectQuery = "SELECT " + column_stName + ", " + column_stRegCode
                    + " FROM " + studentSubMapTable + " WHERE " + column_subCode + " = '" + SubjectCode + "'";

            SQLiteDatabase db = this.getReadableDatabase();
            Cursor c = db.rawQuery(selectQuery, null);

            if (c.moveToFirst()) {
                do {
                    StudentName.add(c.getString(c.getColumnIndex(column_stName)));
                    StudentRegCode.add(c.getString(c.getColumnIndex(column_stRegCode)));
                } while (c.moveToNext());
            }

            c.close();
        }catch(Exception e){
            e.printStackTrace();
        }


        List<String>[] result = new List[2];
        result[0] = StudentName;
        result[1] = StudentRegCode;




        return result;
    }

    public List<String>[] getBranchandDivision(String SubjectCode) {
        List<String> Branch = new ArrayList<>();
        List<String> Division = new ArrayList<>();
        try {
            String selectQuery = "SELECT " + column_branchName + ", " + column_div + " FROM " + facultySubMapViewTable + " WHERE " + column_subCode + " = '" + SubjectCode + "'";

            SQLiteDatabase db = this.getReadableDatabase();
            Cursor c = db.rawQuery(selectQuery, null);

            if (c.moveToFirst()) {
                do {
                    Branch.add(c.getString(c.getColumnIndex(column_branchName)));
                    Division.add(c.getString(c.getColumnIndex(column_div)));
                } while (c.moveToNext());
            }

            c.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        List<String>[] result = new List[2];
        result[0] = Branch;
        result[1] = Division;
        return result;
    }

    public List<String>[] getStudentListBranchAndDivisionWise(String SubjectCode, String Branch, String Division) {
        List<String> StudentName = new ArrayList<>();
        List<String> StudentRegCode = new ArrayList<>();
        try {
            String selectQuery = "SELECT " + column_stName + ", " + column_stRegCode
                    + " FROM " + studentSubMapTable + " WHERE " + column_subCode + " = '" + SubjectCode + "' and " + column_branchName + " = '" + Branch + "' and " + column_div + " = '" + Division + "'";

            SQLiteDatabase db = this.getReadableDatabase();
            Cursor c = db.rawQuery(selectQuery, null);

            if (c.moveToFirst()) {
                do {
                    StudentName.add(c.getString(c.getColumnIndex(column_stName)));
                    StudentRegCode.add(c.getString(c.getColumnIndex(column_stRegCode)));
                } while (c.moveToNext());
            }

            c.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
        List<String>[] result = new List[2];
        result[0] = StudentName;
        result[1] = StudentRegCode;

        return result;
    }




}

