package edu.wgu.grimes.c196performanceassessment.utilities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import edu.wgu.grimes.c196performanceassessment.database.entities.CourseEntity;
import edu.wgu.grimes.c196performanceassessment.database.entities.TermEntity;

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
        terms.add(new TermEntity(1, "Fall 2018", getDate(10,1, 2018), getDate(3, 31, 2019), "Complete"));
        terms.add(new TermEntity(2,"Spring 2019", getDate(4, 1, 2019), getDate(9, 30, 2019), "Complete"));
        terms.add(new TermEntity(3, "Fall 2019", getDate(10, 1, 2019), getDate(3, 31, 2020), "Complete"));
        terms.add(new TermEntity(4, "Spring 2020", getDate(4, 1, 2020), getDate(9, 30, 2020), "In Progress"));
        return terms;
    }

    public static List<CourseEntity> getCourses() {
        List<CourseEntity> courses = new ArrayList<>();
        courses.add(new CourseEntity(1, 1, 4, "C182", "Introduction to IT", getDate(10, 6, 2018), getDate(10, 8, 2018), "Complete"));
        courses.add(new CourseEntity(2, 1, 3, "C173", "Scripting and Programming - Foundations", getDate(10, 8, 2018), getDate(10, 10, 2018), "Complete"));
        courses.add(new CourseEntity(3, 1, 0, "ORA1", "Orientation", getDate(3, 30, 2019), getDate(4, 1, 2019), "Complete"));
        courses.add(new CourseEntity(4, 1, 3, "C779", "Web Development Foundations", getDate(11, 11, 2018), getDate(11, 13, 2018), "Complete"));
        courses.add(new CourseEntity(5, 1, 3, "C100", "Introduction to Humanities", getDate(10, 30, 2018), getDate(12, 30, 2018), "Complete"));
        courses.add(new CourseEntity(6, 1, 4, "C993", "Structured Query Language", getDate(1, 14, 2019), getDate(1, 30, 2019), "Complete"));
        courses.add(new CourseEntity(7, 1, 6, "C482", "Software I", getDate(2, 11, 2019), getDate(2, 13, 2019), "Complete"));
        courses.add(new CourseEntity(8, 1, 6, "C195", "Software II - Advanced Java Concepts", getDate(2, 25, 2019), getDate(2, 27, 2019), "Complete"));
        courses.add(new CourseEntity(9, 1, 3, "C175", "Data Management - Foundations", getDate(3, 11, 2019), getDate(3, 12, 2019), "Complete"));
        courses.add(new CourseEntity(10, 1, 4, "C170", "Data Management - Applications", getDate(3, 16, 2019), getDate(3, 18, 2019), "Complete"));
        return courses;
    }

}
