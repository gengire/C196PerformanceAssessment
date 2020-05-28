package edu.wgu.grimes.c196performanceassessment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.wgu.grimes.c196performanceassessment.database.entities.CourseEntity;
import edu.wgu.grimes.c196performanceassessment.ui.CoursesAdapter;
import edu.wgu.grimes.c196performanceassessment.viewmodel.TermEditorViewModel;

import static edu.wgu.grimes.c196performanceassessment.utilities.Constants.EDITING_KEY;
import static edu.wgu.grimes.c196performanceassessment.utilities.Constants.NOTE_ID_KEY;
import static edu.wgu.grimes.c196performanceassessment.utilities.StringUtil.getFormattedDate;

public class TermEditorActivity extends AppCompatActivity {

    private TermEditorViewModel mViewModel;

    @BindView(R.id.text_edit_term_editor_title)
    TextView mTitle;

    @BindView(R.id.text_view_term_editor_start_date_value)
    TextView mStartDate;

    @BindView(R.id.text_view_term_editor_end_date_value)
    TextView mEndDate;

    @BindView(R.id.recycler_view_course_list)
    RecyclerView mRecyclerViewCourseList;

//    @OnClick(R.id.fab_add_course)
//    void addCourseClickHandler() {
//        Intent intent = new Intent(this, CourseEditorActivity.class);
//        startActivity(intent);
//    }

    private List<CourseEntity> coursesData = new ArrayList<>();
    private CoursesAdapter mAdapter;

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

        ButterKnife.bind(this);

        if (savedInstanceState != null) {
            mEditing = savedInstanceState.getBoolean(EDITING_KEY);
        }

        initRecyclerViewCourseList();
        initViewModel();
    }

    private void initViewModel() {

        final Observer<List<CourseEntity>> coursesObserver = courseEntities -> {
            coursesData.clear();
            coursesData.addAll(courseEntities);

            if (mAdapter == null) {
                mAdapter = new CoursesAdapter(coursesData, TermEditorActivity.this);
                mRecyclerViewCourseList.setAdapter(mAdapter);
            } else {
                mAdapter.notifyDataSetChanged();
            }
        };

        ViewModelProvider.Factory factory = new ViewModelProvider.AndroidViewModelFactory(getApplication());
        mViewModel = new ViewModelProvider(this, factory).get(TermEditorViewModel.class);

        mViewModel.mCourses.observe(this, coursesObserver);

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

    private void initRecyclerViewCourseList() {
        mRecyclerViewCourseList.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerViewCourseList.setLayoutManager(layoutManager);

        DividerItemDecoration divider = new DividerItemDecoration(mRecyclerViewCourseList.getContext(),
                layoutManager.getOrientation());
        mRecyclerViewCourseList.addItemDecoration(divider);
    }

    @OnClick(R.id.text_view_term_editor_start_date_value)
    void startDateClickHandler() {
        DatePickerDialog.OnDateSetListener mStartDateListener = (view, year, month, day) -> {
            month = month + 1;
            String date = month + "/" + day + "/" + year;
            mStartDate.setText(date);
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.DAY_OF_MONTH, day);
            cal.set(Calendar.MONTH, month - 1);
            cal.set(Calendar.YEAR, year);
            startDate = cal.getTime();
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
        DatePickerDialog.OnDateSetListener mEndDateListener = (view, year, month, day) -> {
            month = month + 1;
            String date = month + "/" + day + "/" + year;
            mEndDate.setText(date);
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.DAY_OF_MONTH, day);
            cal.set(Calendar.MONTH, month - 1);
            cal.set(Calendar.YEAR, year);
            endDate = cal.getTime();

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_term_editor, menu);
        menu.findItem(R.id.action_delete_term).setVisible(!mNewTerm);
        menu.findItem(R.id.action_add_course).setVisible(!mNewTerm);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                saveAndReturn();
                return true;
            case R.id.action_delete_term:
                mViewModel.deleteTerm();
                finish();
                return true;
            case R.id.action_add_course:
                addCourse();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void addCourse() {
        Intent intent = new Intent(this, CourseEditorActivity.class);
        startActivity(intent);
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
