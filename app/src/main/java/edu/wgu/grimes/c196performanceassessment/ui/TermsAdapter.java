package edu.wgu.grimes.c196performanceassessment.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.wgu.grimes.c196performanceassessment.R;
import edu.wgu.grimes.c196performanceassessment.TermEditorActivity;
import edu.wgu.grimes.c196performanceassessment.database.TermEntity;
import edu.wgu.grimes.c196performanceassessment.utilities.StringUtil;

import static edu.wgu.grimes.c196performanceassessment.utilities.Constants.NOTE_ID_KEY;

public class TermsAdapter extends RecyclerView.Adapter<TermsAdapter.ViewHolder> {

    private final List<TermEntity> mTerms;
    private final Context mContext;

    public TermsAdapter(List<TermEntity> mTerms, Context mContext) {
        this.mTerms = mTerms;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    /*
     * called each time a new view holder needs to be created
     */
    public TermsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.term_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    /*
     * called each time we want to update the display of a list item
     */
    public void onBindViewHolder(@NonNull TermsAdapter.ViewHolder holder, int position) {
        final TermEntity term = mTerms.get(position);
        holder.mTitle.setText(term.getTitle());

        StringBuilder sb = new StringBuilder();
        String startDate = StringUtil.getFormattedDate(term.getStartDate());
        String endDate = StringUtil.getFormattedDate(term.getEndDate());
        startDate = startDate == null ? "????" : startDate;
        endDate = endDate == null ? "????" : endDate;
        String dateRange =  startDate + " - " + endDate;
        holder.mDateRange.setText(dateRange);

        holder.fabTerm.setOnClickListener(view -> {
            Intent intent = new Intent(mContext, TermEditorActivity.class);
            intent.putExtra(NOTE_ID_KEY, term.getTermId());
            mContext.startActivity(intent);
        });
    }

    @Override
    /*
     * returns the total number of items in the data
     */
    public int getItemCount() {
        return mTerms.size();
    }

    /*
     * onCreateViewHolder generates an instance of this class
     * the purpose of the view holder is to manage the view itself
     * and it has to contain view references for any component that
     * you want to use to display data
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_view_term_text)
        TextView mTitle;

        @BindView(R.id.text_view_term_date_range)
        TextView mDateRange;

        @BindView(R.id.fab_term)
        FloatingActionButton fabTerm;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
