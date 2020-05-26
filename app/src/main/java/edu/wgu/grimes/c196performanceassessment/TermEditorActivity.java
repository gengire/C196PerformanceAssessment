package edu.wgu.grimes.c196performanceassessment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.wgu.grimes.c196performanceassessment.database.TermEntity;
import edu.wgu.grimes.c196performanceassessment.utilities.StringUtil;
import edu.wgu.grimes.c196performanceassessment.viewmodel.TermEditorViewModel;

import static edu.wgu.grimes.c196performanceassessment.utilities.Constants.EDITING_KEY;
import static edu.wgu.grimes.c196performanceassessment.utilities.Constants.NOTE_ID_KEY;
import static edu.wgu.grimes.c196performanceassessment.utilities.StringUtil.*;
import static edu.wgu.grimes.c196performanceassessment.utilities.StringUtil.getFormattedDate;

public class TermEditorActivity extends AppCompatActivity {

    private static final String TAG = "TermEditorActivity";

    private TermEditorViewModel mViewModel;

    private DatePickerDialog.OnDateSetListener mStartDateListener;
    private DatePickerDialog.OnDateSetListener mEndDateListener;

    @BindView(R.id.text_edit_term_editor_title)
    TextView mTitle;

    @BindView(R.id.text_view_term_editor_start_date_value)
    TextView mStartDate;

    @BindView(R.id.text_view_term_editor_end_date_value)
    TextView mEndDate;
    private boolean mNewTerm;
    private boolean mEditing;

    private Date startDate;
    private Date endDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_editor);
        Toolbar toolbar = findViewById(R.id.toolbar_term_editor);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_check);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimaryDark)));
        getSupportActionBar().setDisplayShowTitleEnabled(false);  // required to force redraw, without, gray color
        getSupportActionBar().setDisplayShowTitleEnabled(true);


        ButterKnife.bind(this);

        if (savedInstanceState != null) {
            mEditing = savedInstanceState.getBoolean(EDITING_KEY);
        }

        initViewModel();
    }

    @OnClick(R.id.text_view_term_editor_start_date_value)
    void startDateClickHandler() {
        mStartDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                String date = month + "/" + day + "/" + year;
                mStartDate.setText(date);
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.DAY_OF_MONTH, day);
                cal.set(Calendar.MONTH, month-1);
                cal.set(Calendar.YEAR, year);
                startDate = cal.getTime();
            }
        };

        Calendar cal = GregorianCalendar.getInstance();
        if (startDate != null) {
            cal.setTime(startDate);
        }
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(
                TermEditorActivity.this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                mStartDateListener,
                year, month, day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
        dialog.show();
    }

    @OnClick(R.id.text_view_term_editor_end_date_value)
    void endDateClickHandler() {
        mEndDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                String date = month + "/" + day + "/" + year;
                mEndDate.setText(date);
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.DAY_OF_MONTH, day);
                cal.set(Calendar.MONTH, month-1);
                cal.set(Calendar.YEAR, year);
                endDate = cal.getTime();

            }
        };

        Calendar cal = GregorianCalendar.getInstance();
        if (endDate != null) {
            cal.setTime(endDate);
        }
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(
                TermEditorActivity.this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                mEndDateListener,
                year, month, day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
        dialog.show();
    }



    private void initViewModel() {
        ViewModelProvider.Factory factory = new ViewModelProvider.AndroidViewModelFactory(getApplication());
        mViewModel = new ViewModelProvider(this, factory).get(TermEditorViewModel.class);

        // update the view when the model is changed
        mViewModel.mLiveTerm.observe(this, (termEntity) -> {
            if (termEntity != null) {
                if (!mEditing) {
                    mTitle.setText(termEntity.getTitle());
                }
                startDate = termEntity.getStartDate();
                endDate = termEntity.getEndDate();
                if (startDate != null) {
                    mStartDate.setText(getFormattedDate(startDate));
                }
                if (endDate != null) {
                    mEndDate.setText(getFormattedDate(endDate));
                }
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            setTitle(getString(R.string.new_term));
            mNewTerm = true;
        } else {
            setTitle(getString(R.string.edit_term));
            int termId = extras.getInt(NOTE_ID_KEY);
            mViewModel.loadData(termId);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNewTerm) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_term_editor, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            saveAndReturn();
            return true;
        } else if (item.getItemId() == R.id.action_delete) {
            mViewModel.deleteTerm();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        saveAndReturn();
    }

    private void saveAndReturn() {
        mViewModel.saveTerm(mTitle.getText().toString(), mStartDate.getText().toString(), mEndDate.getText().toString());
        finish();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        //todo: BUG - dates are lost on orientation shift
        outState.putBoolean(EDITING_KEY, true);
        super.onSaveInstanceState(outState);
    }
}
