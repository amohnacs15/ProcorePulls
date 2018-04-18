package com.amohnacs.procorepulls.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amohnacs.common.Utils;
import com.amohnacs.model.Label;
import com.amohnacs.model.PullRequest;
import com.amohnacs.procorepulls.R;
import com.amohnacs.procorepulls.main.ui.MainFragment.OnListFragmentInteractionListener;
import com.amohnacs.procorepulls.main.ui.PRViewHolder;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PullRequest} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 */
public class MainPullRequestAdapter extends RecyclerView.Adapter<PRViewHolder> {

    private Context context;
    private final List<PullRequest> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MainPullRequestAdapter(Context context, List<PullRequest> items,
                                  OnListFragmentInteractionListener listener) {
        this.context = context;
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

        PullRequest currentItem = mValues.get(position);
        holder.setItem(currentItem);

        if (currentItem != null) {
            holder.setTitle(currentItem.getTitle());

            if (!Utils.isEmpty(currentItem.getLabels())) {
                Label label = currentItem.getLabels().get(0);
                holder.setLabelTextView(label.getName(), label.getColor());
            }

            holder.setCreatedString(context, currentItem.getId(), currentItem.getCreatedDate(),
                    currentItem.getAssignee() != null ? currentItem.getAssignee().getLogin() : "unknown");
        }

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.item);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }


}
