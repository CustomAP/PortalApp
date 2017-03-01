package com.aceshub.portal.database.model;

/**
 * Created by guitarman on 22/2/17.
 */

public class SubjectAttendanceInfoFlag {
    int flag_ID;
    String flag;
    //String subCode,subTitle,subType,divID,facultySubMapID,branch;
    //boolean sync;

    public SubjectAttendanceInfoFlag(int flag_ID, String flag) {
        this.flag=flag;
        this.flag_ID=flag_ID;
    }

    //Setter

    public void setFlag_ID(int flag_ID) {
        this.flag_ID = flag_ID;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }


    // getter


    public int getFlag_ID() {
        return flag_ID;
    }

    public String getFlag() {
        return flag;
    }
}
