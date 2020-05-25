package edu.wgu.grimes.c196performanceassessment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.wgu.grimes.c196performanceassessment.database.TermEntity;
import edu.wgu.grimes.c196performanceassessment.ui.TermsAdapter;
import edu.wgu.grimes.c196performanceassessment.utilities.SampleData;
import edu.wgu.grimes.c196performanceassessment.viewmodel.TermListViewModel;

public class TermListActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view_term_list)
    RecyclerView mRecyclerViewTermList;

    @OnClick(R.id.fab_add_term)
    void fabAddTermClickHandler() {
        Intent intent = new Intent(this, TermEditorActivity.class);
        startActivity(intent);
    }

    private List<TermEntity> termsData = new ArrayList<>();
    private TermsAdapter mAdapter;
    private TermListViewModel tlViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_list);

        ButterKnife.bind(this);
        initViewModel();
        initRecyclerViewTermList();

        termsData.addAll(tlViewModel.tlTerms);

        for (TermEntity term : termsData) {
            Log.i("Terms", term.toString());
        }
    }

    private void initViewModel() {
        ViewModelProvider.Factory factory = new ViewModelProvider.AndroidViewModelFactory(getApplication());
        tlViewModel = new ViewModelProvider(this, factory).get(TermListViewModel.class);
    }

    private void initRecyclerViewTermList() {
        mRecyclerViewTermList.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerViewTermList.setLayoutManager(layoutManager);

        mAdapter = new TermsAdapter(termsData, this);
        mRecyclerViewTermList.setAdapter(mAdapter);
    }
}
