package edu.wgu.grimes.c196performanceassessment.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import edu.wgu.grimes.c196performanceassessment.database.AppRepository;
import edu.wgu.grimes.c196performanceassessment.database.entities.TermEntity;

public class MainViewModel extends AndroidViewModel {

    public LiveData<List<TermEntity>> mTerms;
    private AppRepository mRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);

        mRepository = AppRepository.getInstance(application.getApplicationContext());
        mTerms = mRepository.mTerms;
    }

    public String getCoursesInProgress() {
        return String.valueOf(getCourseStatusCount("In Progress"));
    }

    public String getCoursesCompleted() {
        return String.valueOf(getCourseStatusCount("Complete"));
    }

    public String getCoursesDropped() {
        return String.valueOf(getCourseStatusCount("Dropped"));
    }

    public String getCoursesFailed() {
        return String.valueOf(getCourseStatusCount("Failed"));
    }

    public String getAssessmentsPending() {
        return String.valueOf(getAssessmentStatusCount("Pending"));
    }

    public String getAssessmentsPassed() {
        return String.valueOf(getAssessmentStatusCount("Passed"));
    }

    public String getAssessmentsFailed() {
        return String.valueOf(getAssessmentStatusCount("Failed"));
    }

    private int getCourseStatusCount(String status) {
        //todo: need to tie this to the courses after they are added.
        return 0;
    }

    private int getAssessmentStatusCount(String status) {
        //todo: need to tie this to the assessments after they are added.
        return 0;
    }

    public void addSampleData() {
        mRepository.addSampleData();
    }

    public void deleteAllTerms() {
        mRepository.deleteAllTerms();
    }
}
