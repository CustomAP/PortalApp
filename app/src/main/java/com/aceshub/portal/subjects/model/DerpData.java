package com.aceshub.portal.subjects.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shri on 03-11-2016.
 */

public class DerpData {
    private static final String array[] = {"physics", "chemistry", "maths"};

    public static List<ListItem> getListData() {
        List<ListItem> data = new ArrayList<>();
        for (int x = 0; x < 4; x++) {
            for (int i = 0; i < array.length; i++) {
                ListItem item = new ListItem();
                item.setTitle(array[i]);
                data.add(item);
            }
        }
        return data;
    }
}
