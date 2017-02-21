package com.aceshub.portal.attendence;

public class MisListItem {

    private String mis;
    private String name;
    private String branch;
    private boolean isPresent = true;

    public MisListItem(String mis, String name, String branch, boolean isPresent) {
        this.mis = mis;
        this.name = name;
        this.branch = branch;
        this.isPresent = isPresent;
    }

    public String getMis() {
        return mis;
    }

    public void setMis(String mis) {
        this.mis = mis;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public boolean isPresent() {
        return isPresent;
    }

    public void setPresent(boolean present) {
        this.isPresent = present;
    }

}
