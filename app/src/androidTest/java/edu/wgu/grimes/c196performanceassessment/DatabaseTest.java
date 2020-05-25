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
import edu.wgu.grimes.c196performanceassessment.database.TermDao;
import edu.wgu.grimes.c196performanceassessment.database.TermEntity;
import edu.wgu.grimes.c196performanceassessment.utilities.SampleData;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(AndroidJUnit4.class)
public class DatabaseTest {

    public static final String TAG = "Junit";
    private AppDatabase mDb;
    private TermDao mDao;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        mDb = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        mDao = mDb.termDao();
        Log.i(TAG, "createDb");
    }

    @After
    public void closeDb() {
        mDb.close();
        Log.i(TAG, "closeDb");
    }

    @Test
    public void createAndCountTerms() {
        mDao.saveAll(SampleData.getTerms());
        int count = mDao.selectCount();
        assertThat(count, is(4));
    }

    @Test
    public void compareStrings() {
        mDao.saveAll(SampleData.getTerms());
        TermEntity original = SampleData.getTerms().get(0);
        TermEntity fromDb = mDao.selectTermById(1);
        assertThat(fromDb, notNullValue());
        assertThat(fromDb.getTitle(), is (original.getTitle()));
        assertThat(fromDb.getTermId(), is(1));
    }

    @Test
    public void testSelectAll() {
        List<TermEntity> expectedTerms = SampleData.getTerms();
        mDao.saveAll(expectedTerms);
        TermEntity fromDb = mDao.selectTermById(1);
        assertThat(fromDb, notNullValue());
        TermEntity originalTerm = expectedTerms.get(0);
        assertThat(fromDb.getTitle(), is (originalTerm.getTitle()));
        assertThat(fromDb.getTermId(), is(originalTerm.getTermId()));
        assertThat(fromDb.getStartDate(), is(originalTerm.getStartDate()));
        assertThat(fromDb.getEndDate(), is(originalTerm.getEndDate()));
        assertThat(fromDb.getStatus(), is("Complete"));
    }

}
