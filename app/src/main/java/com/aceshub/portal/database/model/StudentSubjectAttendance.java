package com.aceshub.portal.database.model;

/**
 * Created by guitarman on 22/2/17.
 */

public class StudentSubjectAttendance {
    int Sid, Siid, DivisionID, sync;
    boolean status;


    public int getSid() {
        return Sid;
    }

    //Setter
    public void setSid(int sid) {
        Sid = sid;
    }

    public int getSiid() {
        return Siid;
    }

    public void setSiid(int Siid) {
        this.Siid = Siid;
    }

    public int isSync() {
        return sync;
    }

    public void setSync(int sync) {
        this.sync = sync;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
