package edu.wgu.grimes.c196performanceassessment.database;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import edu.wgu.grimes.c196performanceassessment.utilities.SampleData;

public class AppRepository {

    private static AppRepository instance;

    public LiveData<List<TermEntity>> mTerms;
    private AppDatabase mDb;
    private Executor executor = Executors.newSingleThreadExecutor();

    public static AppRepository getInstance(Context context) {
        if (instance == null) {
            instance = new AppRepository(context);
        }
        return instance;
    }

    private AppRepository(Context context) {
        mDb = AppDatabase.getInstance(context);
        mTerms = getAllTerms();
    }

    public void addSampleData() {
        executor.execute(() -> {
            mDb.termDao().saveAll(SampleData.getTerms());
        });
    }

    private LiveData<List<TermEntity>> getAllTerms() {
        return mDb.termDao().selectAll();
    }

    public void deleteAllTerms() {
        executor.execute(() -> {
            mDb.termDao().deleteAll();
        });
    }

    public TermEntity getTermById(int termId) {
        return mDb.termDao().selectTermById(termId);
    }

    public void insertTerm(TermEntity term) {
        executor.execute(() -> {
            mDb.termDao().saveTerm(term);
        });
    }
}
