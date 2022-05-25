package com.example.asd.utils;

import java.time.LocalDate;

public class DateUtils {
    public static boolean isYYYYMMDD(String date) {
        String[] split = date.split("-");
        if (split.length != 3) return false;
        if (split[0].length() != 4 || split[1].length() != 2 || split[2].length() != 2) {
            return false;
        }
        try {
            for (String s : split) {
                Integer.parseInt(s);
            }
        } catch (Throwable e) {
            return false;
        }
        return true;
    }

    public static boolean isYYYYMM(String date) {
        String[] split = date.split("-");
        if (split.length != 2) return false;
        if (split[0].length() != 4 || split[1].length() != 2) {
            return false;
        }
        try {
            for (String s : split) {
                Integer.parseInt(s);
            }
        } catch (Throwable e) {
            return false;
        }
        return true;
    }

    public static boolean isYYYY(String date) {
        System.out.println(date);
        if (date.length() != 4) return false;

        try {
            Integer.parseInt(date);

        } catch (Throwable e) {
            return false;
        }
        return true;
    }

    public static String getToday() {
        LocalDate x = LocalDate.now();
        return String.format("%s-%s-%s", x.getYear(), x.getMonth().getValue() > 9 ? x.getMonth().getValue() : "0" + x.getMonth().getValue(),
                x.getDayOfMonth() > 9 ? x.getDayOfMonth() : "0" + x.getDayOfMonth());

    }

    public static String getTomorrow() {
        LocalDate x = LocalDate.now();
        LocalDate y = LocalDate.of(x.getYear(), x.getMonthValue(), x.getDayOfMonth() + 1);
        return String.format("%s-%s-%s", y.getYear(), y.getMonthValue() > 9 ? y.getMonthValue() : "0" + y.getMonthValue(),
                y.getDayOfMonth() > 9 ? y.getDayOfMonth() : "0" + y.getDayOfMonth());
    }

    public static String[] getThisMonth() {
        LocalDate x = LocalDate.now();
        return new String[]{String.valueOf(x.getYear()),(x.getMonthValue()> 9 ? String.valueOf(x.getMonthValue()) : "0" + x.getMonthValue())};
    }
}

