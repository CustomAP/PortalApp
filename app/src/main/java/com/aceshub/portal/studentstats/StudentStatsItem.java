package com.aceshub.portal.studentstats;

/**
 * Created by Abhidnya on 2/28/2017.
 */

public class StudentStatsItem {

    private String name;
    private String presentPercentage;

    public StudentStatsItem(String name, String presentPercentage) {
        this.name = name;
        this.presentPercentage = presentPercentage;
    }

    public String getName() {
        return name;
    }

    public String getPresentPercentage() {
        return presentPercentage;
    }
}
