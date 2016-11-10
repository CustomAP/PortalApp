package com.aceshub.portal.attendence;

import java.util.ArrayList;
import java.util.List;

public class MisData {

    private static List<MisListItem> data = new ArrayList<>();

    public MisData() {
    }

    public static List<MisListItem> getData() {
        return data;
    }

    public static void setData(List<MisListItem> data) {
        MisData.data = data;
    }

    public static void addData(MisListItem misListItem) {
        data.add(misListItem);
    }

    public static int getSize() {
        return data.size();
    }

    public static void clear() {
        data.clear();
    }
}
