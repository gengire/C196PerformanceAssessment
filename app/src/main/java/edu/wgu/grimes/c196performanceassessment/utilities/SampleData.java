package edu.wgu.grimes.c196performanceassessment.utilities;

import java.util.ArrayList;
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

    public static List<TermEntity> getTerms() {
        List<TermEntity> terms = new ArrayList<>();
        terms.add(new TermEntity("Fall 2018", getDate(-1000), getDate(-900), "Complete"));
        terms.add(new TermEntity("Spring 2019", getDate(-800), getDate(-700), "Complete"));
        terms.add(new TermEntity("Fall 2019", getDate(-600), getDate(-500), "Complete"));
        terms.add(new TermEntity("Spring 2020", getDate(-400), getDate(40), "In Progress"));
        return terms;
    }

}
