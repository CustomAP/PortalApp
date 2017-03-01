package com.aceshub.portal.database.model;

/**
 * Created by guitarman on 22/2/17.
 */

public class StudentSubjectMappingView {
    int Sid;
    String facultySubMapID,stRegcode,stName,subCode,subTitle,subType,divID,branch_name;
    boolean sync;

    public StudentSubjectMappingView(int Sid, String subCode, String subTitle,String stRegcode,String stName, String subType, String divID, String facultySubMapID, String branch_name, boolean sync) {
        this.Sid = Sid;
        this.subCode=subCode;
        this.subTitle=subTitle;
        this.subType=subType;
        this.divID=divID;
        this.facultySubMapID=facultySubMapID;
        this.branch_name=branch_name;
        this.sync=sync;
        this.stRegcode=stRegcode;
        this.stName=stName;
    }

    //Setter

    public void setSid(int sid) {
        Sid = sid;
    }

    public void setFacultySubMapID(String facultySubMapID) {
        this.facultySubMapID = facultySubMapID;
    }

    public void setStRegcode(String stRegcode) {
        this.stRegcode = stRegcode;
    }

    public void setStName(String stName) {
        this.stName = stName;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public void setDivID(String divID) {
        this.divID = divID;
    }

    public void setBranch_name(String branch_name) {
        this.branch_name = branch_name;
    }

    public void setSync(boolean sync) {
        this.sync = sync;
    }


    // getter


    public int getSid() {
        return Sid;
    }

    public String getFacultySubMapID() {
        return facultySubMapID;
    }

    public String getStRegcode() {
        return stRegcode;
    }

    public String getStName() {
        return stName;
    }

    public String getSubCode() {
        return subCode;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public String getSubType() {
        return subType;
    }

    public String getDivID() {
        return divID;
    }

    public String getBranch_name() {
        return branch_name;
    }

    public boolean isSync() {
        return sync;
    }
}
