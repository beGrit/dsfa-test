package com.dsfa.platform.service.core.kit.factory;

import java.util.Calendar;
import java.util.Date;

public class BirthFactory {
    public static Date getOldest() {
        Calendar c1 = Calendar.getInstance();
        int curYear = c1.get(Calendar.YEAR);
        c1.set(Calendar.YEAR, curYear - 60);
        return c1.getTime();
    }

    public static Date getYoungest() {
        Calendar c1 = Calendar.getInstance();
        int curYear = c1.get(Calendar.YEAR);
        c1.set(Calendar.YEAR, curYear - 18);
        return c1.getTime();
    }
}
