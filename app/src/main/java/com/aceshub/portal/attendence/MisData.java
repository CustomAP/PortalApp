package com.aceshub.portal.attendence;

import java.util.ArrayList;
import java.util.List;

public class MisData {

    private static List<MisListItem> data = new ArrayList<>();
    private static List<MisListItem> currentStudentList = new ArrayList<>();
    private static int absent = 0;

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

    public static List<MisListItem> getCurrentStudentList() {
        return currentStudentList;
    }

    public static void setCurrentStudentList(List<MisListItem> currentStudentList) {
        MisData.currentStudentList = currentStudentList;
    }

    public static int getSize() {
        return data.size();
    }

    public static void clear() {
        data.clear();
    }

    public static int getAbsent() {
        return absent;
    }

    public static void setAbsent(int absent) {
        MisData.absent = absent;
    }

    public static void addAbsent(int count) {
        MisData.absent += count;
    }
}
