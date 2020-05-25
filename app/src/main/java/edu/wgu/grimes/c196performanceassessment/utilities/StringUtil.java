package edu.wgu.grimes.c196performanceassessment.utilities;

import java.util.Calendar;
import java.util.Date;

public class StringUtil {

    public static String getFormattedDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        String formattedDate =
                (cal.get(Calendar.MONTH) + 1) + "/" +
                        cal.get(Calendar.DATE) + "/" +
                        cal.get(Calendar.YEAR);
        return formattedDate;
    }
}
