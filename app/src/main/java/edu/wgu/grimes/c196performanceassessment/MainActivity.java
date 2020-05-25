package edu.wgu.grimes.c196performanceassessment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import edu.wgu.grimes.c196performanceassessment.database.TermEntity;
import edu.wgu.grimes.c196performanceassessment.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private MainViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initViewModel();
        populateStatistics();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
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
        final Observer<List<TermEntity>> termsObserver = new Observer<List<TermEntity>>() {
            @Override
            public void onChanged(List<TermEntity> termEntities) {
                // called automatically when ever the data is updated
                populateStatistics();
            }
        };

        ViewModelProvider.Factory factory = new ViewModelProvider.AndroidViewModelFactory(getApplication());
        mViewModel = new ViewModelProvider(this, factory).get(MainViewModel.class);

        mViewModel.mTerms.observe(this, termsObserver);
    }

    public void gotoTermList(View v) {
        Intent i = new Intent(this, TermListActivity.class);
        startActivity(i);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(id) {
            case R.id.action_add_sample_data:
                addSampleData();
                return true;
            case R.id.action_delete_all:
                deleteAllTerms();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void deleteAllTerms() {
        mViewModel.deleteAllTerms();
    }

    private void addSampleData() {
        mViewModel.addSampleData();
    }
}
