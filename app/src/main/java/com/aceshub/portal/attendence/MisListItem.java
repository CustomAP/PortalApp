package com.aceshub.portal.attendence;

public class MisListItem {

    private String mis;
    private boolean present = true;

    public MisListItem(String mis) {
        this.mis = mis;
    }

    public String getMis() {
        return mis;
    }

    public void setMis(String mis) {
        this.mis = mis;
    }

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }

}
