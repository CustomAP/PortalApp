package com.aceshub.portal.database.model;
import java.sql.Time;
import java.util.Date;
/**
 * Created by guitarman on 22/2/17.
 */

public class SubjectAttendenceInfo {
    int Fsmid,flag_ID;
     String sDate,devDate;
     String sTime,devTime;
    //String subCode,subTitle,subType,divID,facultySubMapID,branch;
    int sync;

    public SubjectAttendenceInfo( ) {
    }

    //Setter

    public void setFsmid(int fsmid) {
        Fsmid = fsmid;
    }


    public void setFlag_ID(int flag_ID) {
        this.flag_ID = flag_ID;
    }

    public void setsDate(String sDate) {
        this.sDate = sDate;
    }

    public void setDevDate(String devDate) {
        this.devDate = devDate;
    }

    public void setsTime(String sTime) {
        this.sTime = sTime;
    }

    public void setDevTime(String devTime) {
        this.devTime = devTime;
    }

    public void setSync(int sync) {
        this.sync = sync;
    }


    // getter


    public int getFsmid() {
        return Fsmid;
    }


    public int getFlag_ID() {
        return flag_ID;
    }

    public String getsDate() {
        return sDate;
    }

    public String getDevDate() {
        return devDate;
    }

    public String getsTime() {
        return sTime;
    }

    public String getDevTime() {
        return devTime;
    }

    public int isSync() {
        return sync;
    }
}
