package edu.wgu.grimes.c196performanceassessment;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import edu.wgu.grimes.c196performanceassessment.database.AppDatabase;
import edu.wgu.grimes.c196performanceassessment.database.daos.CourseDao;
import edu.wgu.grimes.c196performanceassessment.database.daos.TermDao;
import edu.wgu.grimes.c196performanceassessment.database.entities.CourseEntity;
import edu.wgu.grimes.c196performanceassessment.database.entities.TermEntity;
import edu.wgu.grimes.c196performanceassessment.utilities.SampleData;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(AndroidJUnit4.class)
public class DatabaseTest {

    public static final String TAG = "Junit";
    private AppDatabase mDb;
    private TermDao mTermDao;
    private CourseDao mCourseDao;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        mDb = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        mTermDao = mDb.termDao();
        mCourseDao = mDb.courseDao();
        Log.i(TAG, "createDb");
    }

    @After
    public void closeDb() {
        mDb.close();
        Log.i(TAG, "closeDb");
    }

    @Test
    public void createAndCountTerms() {
        mTermDao.saveAll(SampleData.getTerms());
        int count = mTermDao.selectCount();
        assertThat(count, is(4));
    }

    @Test
    public void createAndCountCourses() {
        mTermDao.saveAll(SampleData.getTerms());
        mCourseDao.saveAll(SampleData.getCourses());
        int count = mCourseDao.selectCount();
        assertThat(count, is (10));
    }

    @Test
    public void compareTerms() {
        List<TermEntity> terms = SampleData.getTerms();
        mTermDao.saveAll(terms);
        TermEntity original = terms.get(0);
        TermEntity fromDb = mTermDao.selectTermById(1);
        assertThat(fromDb, notNullValue());
        assertThat(fromDb.getTitle(), is (original.getTitle()));
        assertThat(fromDb.getTermId(), is(original.getTermId()));
        assertThat(fromDb.getStartDate(), is(original.getStartDate()));
        assertThat(fromDb.getEndDate(), is(original.getEndDate()));
        assertThat(fromDb.getStatus(), is(original.getStatus()));
    }

    @Test
    public void compareCourses() {
        mTermDao.saveAll(SampleData.getTerms());
        mCourseDao.saveAll(SampleData.getCourses());
        CourseEntity original = SampleData.getCourses().get(0);
        CourseEntity fromDb = mCourseDao.selectCourseById(1);
        assertThat(fromDb, notNullValue());
        assertThat(fromDb.getCourseId(), is(original.getCourseId()));
        assertThat(fromDb.getTermId(), is(original.getTermId()));
        assertThat(fromDb.getCode(), is(original.getCode()));
        assertThat(fromDb.getTitle(), is(original.getTitle()));
    }
}
