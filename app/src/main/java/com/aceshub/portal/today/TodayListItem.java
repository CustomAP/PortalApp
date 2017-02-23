package com.aceshub.portal.today;

public class TodayListItem {

    private String time;
    private String subjectName;
    private boolean isCanceled = false;

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

    public boolean isCanceled() {
        return isCanceled;
    }

    public void setCanceled(boolean canceled) {
        isCanceled = canceled;
    }
}
