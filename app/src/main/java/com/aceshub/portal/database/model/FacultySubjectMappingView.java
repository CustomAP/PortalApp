package com.aceshub.portal.database.model;

/**
 * Created by guitarman on 22/2/17.
 */

public class FacultySubjectMappingView {
    int Fid;
    String subCode,subTitle,subType,divID,facultySubMapID,branch;
    boolean sync;

    public FacultySubjectMappingView(int Fid, String subCode,String subTitle,String subType, String divID,String facultySubMapID,String branch,boolean sync) {
        this.Fid = Fid;
        this.subCode=subCode;
        this.subTitle=subTitle;
        this.subType=subType;
        this.divID=divID;
        this.facultySubMapID=facultySubMapID;
        this.branch=branch;
    }

    //Setter
    public void setFid(int fid) {
        Fid = fid;
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

    public void setFacultySubMapID(String facultySubMapID) {
        this.facultySubMapID = facultySubMapID;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public void setSync(boolean sync) {
        this.sync = sync;
    }


    // getter


    public int getFid() {
        return Fid;
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

    public String getFacultySubMapID() {
        return facultySubMapID;
    }

    public String getBranch() {
        return branch;
    }

    public boolean isSync() {
        return sync;
    }


}
