package com.aceshub.portal.today;

public class TodayListItem {

    private String time;
    private String subjectName;

    public TodayListItem(String date, String subjectName) {
        this.time = date;
        this.subjectName = subjectName;
    }

    public String getTime() {
        return time;
    }

    public String getSubjectName() {
        return subjectName;
    }
}
