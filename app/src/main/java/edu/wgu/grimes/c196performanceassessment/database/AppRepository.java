package edu.wgu.grimes.c196performanceassessment.database;

import android.content.Context;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import edu.wgu.grimes.c196performanceassessment.utilities.SampleData;

public class AppRepository {

    private static AppRepository instance;

    public List<TermEntity> mTerms;
    private AppDatabase mDb;
    private Executor executor = Executors.newSingleThreadExecutor();

    public static AppRepository getInstance(Context context) {
        if (instance == null) {
            instance = new AppRepository(context);
        }
        return instance;
    }

    private AppRepository(Context context) {
        mTerms = SampleData.getTerms();
        mDb = AppDatabase.getInstance(context);
    }

    public void addSampleData() {
        executor.execute(() -> {
            mDb.termDao().saveAll(SampleData.getTerms());
        });
    }
}
