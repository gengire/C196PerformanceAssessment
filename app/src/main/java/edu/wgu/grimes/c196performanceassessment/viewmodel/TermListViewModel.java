package edu.wgu.grimes.c196performanceassessment.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import edu.wgu.grimes.c196performanceassessment.database.AppRepository;
import edu.wgu.grimes.c196performanceassessment.database.entities.TermEntity;

public class TermListViewModel extends AndroidViewModel {

    public LiveData<List<TermEntity>> mTerms;
    private AppRepository mRepository;

    public TermListViewModel(@NonNull Application application) {
        super(application);

        mRepository = AppRepository.getInstance(application.getApplicationContext());
        mTerms = mRepository.mTerms;
    }
}
