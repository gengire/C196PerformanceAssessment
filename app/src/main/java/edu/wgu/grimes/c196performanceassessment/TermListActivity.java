package edu.wgu.grimes.c196performanceassessment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.wgu.grimes.c196performanceassessment.database.entities.TermEntity;
import edu.wgu.grimes.c196performanceassessment.ui.TermsAdapter;
import edu.wgu.grimes.c196performanceassessment.viewmodel.TermListViewModel;

public class TermListActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view_term_list)
    RecyclerView mRecyclerViewTermList;

//    @OnClick(R.id.fab_add_term)
//    void fabAddTermClickHandler() {
//        Intent intent = new Intent(this, TermEditorActivity.class);
//        startActivity(intent);
//    }

    private List<TermEntity> termsData = new ArrayList<>();
    private TermsAdapter mAdapter;
    private TermListViewModel tlViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_list);
        Toolbar toolbar = findViewById(R.id.toolbar_term_list);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_check);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);

        initRecyclerViewTermList();
        initViewModel();
    }

    private void initViewModel() {

        final Observer<List<TermEntity>> termsObserver = new Observer<List<TermEntity>>() {
            @Override
            public void onChanged(List<TermEntity> termEntities) {
                termsData.clear();
                termsData.addAll(termEntities);

                if (mAdapter == null) {
                    mAdapter = new TermsAdapter(termsData, TermListActivity.this);
                    mRecyclerViewTermList.setAdapter(mAdapter);
                } else {
                    mAdapter.notifyDataSetChanged();
                }
            }
        };
        ViewModelProvider.Factory factory = new ViewModelProvider.AndroidViewModelFactory(getApplication());
        tlViewModel = new ViewModelProvider(this, factory).get(TermListViewModel.class);

        tlViewModel.mTerms.observe(this, termsObserver);
    }

    private void initRecyclerViewTermList() {
        mRecyclerViewTermList.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerViewTermList.setLayoutManager(layoutManager);

        DividerItemDecoration divider = new DividerItemDecoration(mRecyclerViewTermList.getContext(),
                layoutManager.getOrientation());
        mRecyclerViewTermList.addItemDecoration(divider);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_term_list, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                back();
                return true;
            case R.id.action_add_term:
                addTerm();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void addTerm() {
        Intent intent = new Intent(this, TermEditorActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        back();
    }

    private void back() {
        finish();
    }
}
