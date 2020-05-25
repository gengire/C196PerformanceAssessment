package edu.wgu.grimes.c196performanceassessment.utilities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import edu.wgu.grimes.c196performanceassessment.database.TermEntity;

public class SampleData {

    private static Date getDate(int diff) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.add(GregorianCalendar.MILLISECOND, diff);
        return cal.getTime();
    }

    private static Date getDate(int month, int day, int year) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.set(Calendar.MONTH, month-1);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.YEAR, year);
        return cal.getTime();
    }

    public static List<TermEntity> getTerms() {
        List<TermEntity> terms = new ArrayList<>();
        terms.add(new TermEntity("Fall 2018", getDate(10,1, 2018), getDate(3, 31, 2019), "Complete"));
        terms.add(new TermEntity("Spring 2019", getDate(4, 1, 2019), getDate(9, 30, 2019), "Complete"));
        terms.add(new TermEntity("Fall 2019", getDate(10, 1, 2019), getDate(3, 31, 2020), "Complete"));
        terms.add(new TermEntity("Spring 2020", getDate(4, 1, 2020), getDate(9, 30, 2020), "In Progress"));
        return terms;
    }

}
