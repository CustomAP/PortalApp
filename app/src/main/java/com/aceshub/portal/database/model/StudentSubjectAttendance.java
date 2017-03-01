package com.aceshub.portal.database.model;

/**
 * Created by guitarman on 22/2/17.
 */

public class StudentSubjectAttendance {
    int Sid, SSid, DivisionID, sync;
    int status;


    public int getSid() {
        return Sid;
    }

    //Setter
    public void setSid(int sid) {
        Sid = sid;
    }

    public int getSSid() {
        return SSid;
    }

    public void setSSid(int SSid) {
        this.SSid = SSid;
    }

    public int isSync() {
        return sync;
    }

    public void setSync(int sync) {
        this.sync = sync;
    }

    public int isStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
