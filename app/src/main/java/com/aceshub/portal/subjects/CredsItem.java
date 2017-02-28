package com.aceshub.portal.subjects;

/**
 * Created by Abhidnya on 2/28/2017.
 */

public class CredsItem {

    private String name;
    private String presentPercentage;

    public CredsItem(String name, String presentPercentage) {
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
