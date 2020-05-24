package edu.wgu.grimes.c196performanceassessment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView coursesInProgress = findViewById(R.id.text_view_courses_in_progress_value);
        coursesInProgress.setText("20");

        TextView coursesCompleted = findViewById(R.id.text_view_courses_completed_value);
        coursesCompleted.setText("21");

        TextView coursesDropped = findViewById(R.id.text_view_courses_dropped_value);
        coursesDropped.setText("22");

        TextView coursesFailed = findViewById(R.id.text_view_courses_failed_value);
        coursesFailed.setText("23");

        TextView assessmentsPending = findViewById(R.id.text_view_assessments_pending_value);
        assessmentsPending.setText("24");

        TextView assessmentsPassed = findViewById(R.id.text_view_assessments_passed_value);
        assessmentsPassed.setText("25");

        TextView assessmentsFailed = findViewById(R.id.text_view_assessments_failed_value);
        assessmentsFailed.setText("26");
    }

    public void gotoTermList(View v) {
        Intent i = new Intent(this, TermListActivity.class);
        startActivity(i);
    }
}
