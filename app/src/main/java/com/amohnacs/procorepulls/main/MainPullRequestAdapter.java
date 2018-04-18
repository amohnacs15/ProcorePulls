package com.amohnacs.procorepulls.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amohnacs.model.PullRequest;
import com.amohnacs.procorepulls.R;
import com.amohnacs.procorepulls.main.ui.MainFragment.OnListFragmentInteractionListener;
import com.amohnacs.procorepulls.main.ui.PRViewHolder;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PullRequest} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MainPullRequestAdapter extends RecyclerView.Adapter<PRViewHolder> {

    private final List<PullRequest> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MainPullRequestAdapter(List<PullRequest> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public PRViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_row_pull_request, parent, false);
        return new PRViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PRViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        //holder.mIdView.setText(mValues.get(position).id);
        //holder.mContentView.setText(mValues.get(position).content);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }


}
