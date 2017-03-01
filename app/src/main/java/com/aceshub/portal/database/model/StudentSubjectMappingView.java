package com.aceshub.portal.database.model;

/**
 * Created by guitarman on 22/2/17.
 */

public class StudentSubjectMappingView {
    int FacultySubjectMappingID;
    int DivisionID;
    int Sync;
    String stregcode;
    String abbreviation;
    String subCode;
    String subTitle;
    String subType;
    String branchname;
    String nameofstudent;
    int SID;

    public void setNameofstudent(String nameofstudent) {
        this.nameofstudent = nameofstudent;
    }

    public void setSID(int SID) {
        this.SID = SID;
    }

    public void setFacultySubjectMappingID(int facultySubjectMappingID) {
        FacultySubjectMappingID = facultySubjectMappingID;
    }

    public void setDivisionID(int divisionID) {
        DivisionID = divisionID;
    }

    public void setSync(int sync) {
        Sync = sync;
    }

    public void setStregcode(String stregcode) {
        this.stregcode = stregcode;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
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

    public void setBranchname(String branchname) {
        this.branchname = branchname;
    }


    public int getSID() {
        return SID;
    }

    public int getFacultySubjectMappingID() {
        return FacultySubjectMappingID;
    }

    public int getDivisionID() {
        return DivisionID;
    }

    public int getSync() {
        return Sync;
    }

    public String getStregcode() {
        return stregcode;
    }

    public String getAbbreviation() {
        return abbreviation;
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

    public String getBranchname() {
        return branchname;
    }

    public String getNameofstudent() {
        return nameofstudent;
    }
}
