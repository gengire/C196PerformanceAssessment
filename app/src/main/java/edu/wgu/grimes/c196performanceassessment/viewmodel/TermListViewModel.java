package edu.wgu.grimes.c196performanceassessment.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.List;

import edu.wgu.grimes.c196performanceassessment.database.TermEntity;
import edu.wgu.grimes.c196performanceassessment.utilities.SampleData;

public class TermListViewModel extends AndroidViewModel {

    public List<TermEntity> tlTerms = SampleData.getTerms();

    public TermListViewModel(@NonNull Application application) {
        super(application);
    }
}
