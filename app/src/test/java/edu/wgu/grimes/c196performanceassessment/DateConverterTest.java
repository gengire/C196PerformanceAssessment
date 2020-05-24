package edu.wgu.grimes.c196performanceassessment;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Date;

import edu.wgu.grimes.c196performanceassessment.database.DateConverter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(JUnit4.class)
public class DateConverterTest {


    @Test
    public void testToTimestamp() {
        Date date = new Date();
        Long timeStamp = DateConverter.toTimestamp(date);
        assertThat(timeStamp, is(date.getTime()));
    }

    @Test
    public void testToDate() {
        Long timestamp = 1590350372057L;
        Date date = DateConverter.toDate(timestamp);
        assertThat(date.getTime(), is(timestamp));
    }

    @Test
    public void testBoth() {
        Date date = new Date();
        assertThat(DateConverter.toDate(DateConverter.toTimestamp(date)), is (date));

        Long timestamp = 1590350372057L;
        assertThat(DateConverter.toTimestamp(DateConverter.toDate(timestamp)), is(timestamp));
    }
}
