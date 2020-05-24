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
        terms.add(new TermEntity(1, "Fall 2018", getDate(-1000), getDate(-900)));
        terms.add(new TermEntity(2, "Sprint 2019", getDate(-800), getDate(-700)));
        terms.add(new TermEntity(3, "Fall 2019", getDate(-600), getDate(-500)));
        terms.add(new TermEntity(4, "Spring 2020", getDate(-400), getDate(40)));
        return terms;
    }

}