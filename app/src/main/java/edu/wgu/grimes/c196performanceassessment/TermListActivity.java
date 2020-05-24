package edu.wgu.grimes.c196performanceassessment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.wgu.grimes.c196performanceassessment.database.TermEntity;
import edu.wgu.grimes.c196performanceassessment.ui.TermsAdapter;
import edu.wgu.grimes.c196performanceassessment.utilities.SampleData;

public class TermListActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view_term_list)
    RecyclerView mRecyclerViewTermList;

    private List<TermEntity> termsData = new ArrayList<>();
    private TermsAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_list);

        ButterKnife.bind(this);
        initRecyclerViewTermList();

        termsData.addAll(SampleData.getTerms());

        for (TermEntity term : termsData) {
            Log.i("Terms", term.toString());
        }
    }

    private void initRecyclerViewTermList() {
        mRecyclerViewTermList.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerViewTermList.setLayoutManager(layoutManager);

        mAdapter = new TermsAdapter(termsData, this);
        mRecyclerViewTermList.setAdapter(mAdapter);
    }
}
