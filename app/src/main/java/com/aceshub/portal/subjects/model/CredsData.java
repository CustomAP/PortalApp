package com.aceshub.portal.subjects.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shri on 03-11-2016.
 */

public class CredsData {
    private static final String names[] = {"paji", "hehe", "joker"};
    private static final String per[] = {"10%", "20%", "30%"};


    public static List<CredsItem> getListData(int bool) {
        List<CredsItem> data = new ArrayList<>();
        for (int x = 0; x < bool+2; x++) {
            for (int i = 0; i < names.length; i++) {
                CredsItem item = new CredsItem();
                item.setName(names[i]);
                item.setPercentage(per[i]);
                data.add(item);
            }
        }
        return data;
    }
}
