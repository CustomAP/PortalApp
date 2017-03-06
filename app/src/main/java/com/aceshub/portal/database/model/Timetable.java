package com.aceshub.portal.database.model;

import java.sql.Time;

/**
 * Created by guitarman on 22/2/17.
 */

public class Timetable {
    //int Fid;
    private String subCode, subTitle, day;
    private Time start, end;

    public Timetable() {
    }

    //Setter

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

    public String getDay() {
        return day;
    }


    // getter

    public void setDay(String day) {
        this.day = day;
    }

    public Time getStart() {
        return start;
    }

    public void setStart(Time start) {
        this.start = start;
    }

    public Time getEnd() {
        return end;
    }

    public void setEnd(Time end) {
        this.end = end;
    }
}
