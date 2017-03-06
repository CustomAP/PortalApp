package com.aceshub.portal.database.model;

/**
 * Created by guitarman on 22/2/17.
 */

public class FacultySubjectMappingView {

    private int Fsmid, divID, sync;
    private String subCode, subTitle, subType, branch, abbreviation, div;

    public FacultySubjectMappingView() {
    }

    public void setSync(int sync) {
        this.sync = sync;
    }

    public int getFsmid() {
        return Fsmid;
    }

    public void setFsmid(int fsmid) {
        this.Fsmid = fsmid;
    }

    public String getSubCode() {
        return subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public int getDivID() {
        return divID;
    }
    public String getDiv() {
        return div;
    }

    public void setDivID(int divID) {
        this.divID = divID;
    }

    public void setDiv(String div) {
        this.div = div;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public int isSync() {
        return sync;
    }
}
