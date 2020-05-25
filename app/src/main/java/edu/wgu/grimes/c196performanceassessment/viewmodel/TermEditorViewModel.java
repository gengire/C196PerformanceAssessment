package edu.wgu.grimes.c196performanceassessment.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import edu.wgu.grimes.c196performanceassessment.database.AppRepository;
import edu.wgu.grimes.c196performanceassessment.database.TermEntity;

public class TermEditorViewModel extends AndroidViewModel {

    public MutableLiveData<TermEntity> mLiveTerm = new MutableLiveData<>();

    private AppRepository mRepository;

    private Executor executor = Executors.newSingleThreadExecutor();

    public TermEditorViewModel(@NonNull Application application) {
        super(application);
        mRepository = AppRepository.getInstance(getApplication());
    }


    public void loadData(int termId) {
        executor.execute(() -> {
            TermEntity term = mRepository.getTermById(termId);
            mLiveTerm.postValue(term);
        });
    }
}
