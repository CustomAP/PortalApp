package com.aceshub.portal.database.model;

/**
 * Created by guitarman on 22/2/17.
 */

public class FacultySubjectMappingView {
    int Fid, divID, sync, facultySubMapID;
    String subCode,subTitle,subType,branch, abbreviation;

    public FacultySubjectMappingView() {
    }

    //Setter
    public void setFid(int fid) {
        this.Fid = fid;
    }

    public void setSubCode(String subCode) {
        this.subCode=subCode;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle=subTitle;
    }

    public void setSubType(String subType) {
        this.subType=subType;
    }

    public void setDivID(int divID) {
        this.divID=divID;
    }

    public void setFacultySubMapID(int facultySubMapID) {
        this.facultySubMapID = facultySubMapID;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public void setSync(int sync) {
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

    public int getDivID() {
        return divID;
    }

    public int getFacultySubMapID() {
        return facultySubMapID;
    }

    public String getBranch() {
        return branch;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public int isSync() {
        return sync;
    }


}
