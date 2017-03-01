package com.aceshub.portal.database.model;

/**
 * Created by guitarman on 22/2/17.
 */

public class StudentSubjectAttendance {
    int Sid,SSid;
    //String subCode,subTitle,subType,divID,facultySubMapID,branch;
    boolean sync,status;

    public StudentSubjectAttendance(int Sid,int SSid, boolean sync,boolean status) {
        this.Sid = Sid;
        this.SSid=SSid;
        this.sync=sync;
        this.status=status;
    }

    //Setter


    public void setSid(int sid) {
        Sid = sid;
    }

    public void setSSid(int SSid) {
        this.SSid = SSid;
    }

    public void setSync(boolean sync) {
        this.sync = sync;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


    //Getter


    public int getSid() {
        return Sid;
    }

    public int getSSid() {
        return SSid;
    }

    public boolean isSync() {
        return sync;
    }

    public boolean isStatus() {
        return status;
    }
}
