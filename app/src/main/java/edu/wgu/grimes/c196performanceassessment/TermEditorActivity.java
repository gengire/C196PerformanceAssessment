package edu.wgu.grimes.c196performanceassessment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;
import java.util.GregorianCalendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TermEditorActivity extends AppCompatActivity {

    private static final String TAG = "TermDetailsActivity";

    private DatePickerDialog.OnDateSetListener mStartDateListener;
    private DatePickerDialog.OnDateSetListener mEndDateListener;

    @BindView(R.id.text_view_term_details_start_date_value)
    TextView mStartDate;

    @BindView(R.id.text_view_term_details_end_date_value)
    TextView mEndDate;

    @OnClick(R.id.text_view_term_details_start_date_value)
    void startDateClickHandler() {
        mStartDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                String date = month + "/" + day + "/" + year;
                mStartDate.setText(date);
            }
        };

        Calendar cal = GregorianCalendar.getInstance();
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

    @OnClick(R.id.text_view_term_details_end_date_value)
    void endDateClickHandler() {
        mEndDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                String date = month + "/" + day + "/" + year;
                mEndDate.setText(date);
            }
        };

        Calendar cal = GregorianCalendar.getInstance();
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_editor);

        ButterKnife.bind(this);
    }
}
