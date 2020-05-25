package edu.wgu.grimes.c196performanceassessment.database;

import java.util.List;

import edu.wgu.grimes.c196performanceassessment.utilities.SampleData;

public class AppRepository {

    private static final AppRepository instance = new AppRepository();

    public List<TermEntity> mTerms;

    public static AppRepository getInstance() { return instance; }

    private AppRepository() {
        mTerms = SampleData.getTerms();
    }
}
