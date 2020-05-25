package edu.wgu.grimes.c196performanceassessment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import edu.wgu.grimes.c196performanceassessment.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private MainViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViewModel();
        populateStatistics();
    }

    private void populateStatistics() {
        TextView coursesInProgress = findViewById(R.id.text_view_courses_in_progress_value);
        coursesInProgress.setText(mViewModel.getCoursesInProgress());

        TextView coursesCompleted = findViewById(R.id.text_view_courses_completed_value);
        coursesCompleted.setText(mViewModel.getCoursesCompleted());

        TextView coursesDropped = findViewById(R.id.text_view_courses_dropped_value);
        coursesDropped.setText(mViewModel.getCoursesDropped());

        TextView coursesFailed = findViewById(R.id.text_view_courses_failed_value);
        coursesFailed.setText(mViewModel.getCoursesFailed());

        TextView assessmentsPending = findViewById(R.id.text_view_assessments_pending_value);
        assessmentsPending.setText(mViewModel.getAssessmentsPending());

        TextView assessmentsPassed = findViewById(R.id.text_view_assessments_passed_value);
        assessmentsPassed.setText(mViewModel.getAssessmentsPassed());

        TextView assessmentsFailed = findViewById(R.id.text_view_assessments_failed_value);
        assessmentsFailed.setText(mViewModel.getAssessmentsFailed());
    }

    private void initViewModel() {
        ViewModelProvider.Factory factory = new ViewModelProvider.AndroidViewModelFactory(getApplication());
        mViewModel = new ViewModelProvider(this, factory).get(MainViewModel.class);
    }

    public void gotoTermList(View v) {
        Intent i = new Intent(this, TermListActivity.class);
        startActivity(i);
    }
}
