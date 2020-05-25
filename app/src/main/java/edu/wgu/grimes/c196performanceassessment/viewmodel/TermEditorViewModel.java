package edu.wgu.grimes.c196performanceassessment.viewmodel;

import android.app.Application;
import android.content.res.Resources;
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

        } else {
            term.setTitle(title);

            Date startDate = null;
            Date endDate = null;
//            Resources res = getApplication().getResources().getSystem();
//            String format = res.getString(R.string.long_date_format);
//            String format = "E MMM dd HH:mm:ss Z yyyy";
            String format = "MM/dd/yyyy";
            try {
                startDate = new SimpleDateFormat(format).parse(sDate);
                endDate = new SimpleDateFormat(format).parse(eDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            term.setStartDate(startDate);
            term.setEndDate(endDate);
        }
        mRepository.insertTerm(term);
    }
}
