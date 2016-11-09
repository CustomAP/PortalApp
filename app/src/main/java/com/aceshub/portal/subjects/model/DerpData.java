package com.aceshub.portal.subjects.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shri on 03-11-2016.
 */

public class DerpData {
    private static final String array[] = {"physics", "chemistry", "maths"};

    public static List<com.example.shri.list.model.ListItem> getListData() {
        List<com.example.shri.list.model.ListItem> data = new ArrayList<>();
        for (int x = 0; x < 4; x++) {
            for (int i = 0; i < array.length; i++) {
                com.example.shri.list.model.ListItem item = new com.example.shri.list.model.ListItem();
                item.setTitle(array[i]);
                data.add(item);
            }
        }
        return data;
    }
}
