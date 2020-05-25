package edu.wgu.grimes.c196performanceassessment.viewmodel;

import android.app.Application;
import android.content.res.Resources;
import android.text.TextUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import edu.wgu.grimes.c196performanceassessment.R;
import edu.wgu.grimes.c196performanceassessment.database.AppRepository;
import edu.wgu.grimes.c196performanceassessment.database.TermEntity;

import static edu.wgu.grimes.c196performanceassessment.utilities.StringUtil.getDate;

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

    public void saveTerm(String title, String sDate, String eDate) {
        TermEntity term = mLiveTerm.getValue();
        if (term == null) {
            if (TextUtils.isEmpty(title)) {
                return;
            } else {
                term = new TermEntity(title, getDate(sDate), getDate(eDate), "Not Started" );
            }
        } else {
            term.setTitle(title);
            term.setStartDate(getDate(sDate));
            term.setEndDate(getDate(eDate));
        }
        mRepository.insertTerm(term);
    }
}
