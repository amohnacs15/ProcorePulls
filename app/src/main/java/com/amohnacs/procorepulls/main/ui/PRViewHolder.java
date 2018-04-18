package com.amohnacs.procorepulls.main.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.amohnacs.model.PullRequest;
import com.amohnacs.procorepulls.R;

/**
 * Created by adrianmohnacs on 4/17/18.
 */

public class PRViewHolder extends RecyclerView.ViewHolder {
    public final View mView;
    public final TextView mIdView;
    public final TextView mContentView;
    public PullRequest mItem;

    public PRViewHolder(View view) {
        super(view);
        mView = view;
        mIdView = view.findViewById(R.id.id);
        mContentView = view.findViewById(R.id.content);
    }

    @Override
    public String toString() {
        return super.toString() + " '" + mContentView.getText() + "'";
    }
}
