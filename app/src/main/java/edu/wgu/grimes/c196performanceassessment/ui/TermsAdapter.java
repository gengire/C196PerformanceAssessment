package edu.wgu.grimes.c196performanceassessment.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.wgu.grimes.c196performanceassessment.R;
import edu.wgu.grimes.c196performanceassessment.database.TermEntity;

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
        holder.mTextView.setText(term.getTitle());
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
        @BindView(R.id.term_text)
        TextView mTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
