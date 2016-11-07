package com.aceshub.portal.today;

import java.util.ArrayList;
import java.util.List;

public class TodayData {

    private static List<TodayListItem> data = new ArrayList<>();

    public TodayData() {
    }

    public static List<TodayListItem> getData() {
        return data;
    }

    public static void setData(List<TodayListItem> data) {
        TodayData.data = data;
    }

    public static void remove(int position) {
        data.remove(position);
    }

    public static void addData(TodayListItem todayListItem) {
        data.add(todayListItem);
    }

    public static int getSize() {
        return data.size();
    }

    public static void clear() {
        data.clear();
    }

}
