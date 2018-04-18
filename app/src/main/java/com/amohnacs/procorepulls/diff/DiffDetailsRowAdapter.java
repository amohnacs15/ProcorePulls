package com.amohnacs.procorepulls.diff;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amohnacs.procorepulls.R;
import com.amohnacs.procorepulls.diff.ui.IrrelevantViewHolder;
import com.amohnacs.procorepulls.diff.ui.SplitViewHolder;

import java.util.ArrayList;

/**
 * Created by adrianmohnacs on 4/18/18.
 */

class DiffDetailsRowAdapter extends RecyclerView.Adapter {
    private static final String TAG = DiffDetailsRowAdapter.class.getSimpleName();

    private static final int SPLIT = 0;
    private static final int IRRELEVANT = 1;

    private Context context;
    private ArrayList<String[]> diffItems;

    DiffDetailsRowAdapter(Context context, ArrayList<String[]> diffItems) {
        this.context = context;
        this.diffItems = diffItems;
    }

    @Override
    public int getItemViewType(int position) {
        if (diffItems.get(position).length == 2) {
            return SPLIT;
        } else {
            return IRRELEVANT;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        //usually we would use a switch statement
        if (viewType == SPLIT) {
            View v1 = inflater.inflate(R.layout.fragment_diff_row_split, viewGroup, false);
            viewHolder = new SplitViewHolder(context, v1);
        } else {
            View v2 = inflater.inflate(R.layout.fragment_diff_row_irrelevant, viewGroup, false);
            viewHolder = new IrrelevantViewHolder(v2);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //usually we would use a switch statement
        if (holder.getItemViewType() == SPLIT) {
            SplitViewHolder v1 = (SplitViewHolder) holder;
            configure(v1, position);
        } else {
            IrrelevantViewHolder v2 = (IrrelevantViewHolder) holder;
            v2.setText(diffItems.get(position)[0]);
        }
    }

    private void configure(SplitViewHolder v1, int position) {
        v1.setDiffRow(diffItems.get(position));
    }

    @Override
    public int getItemCount() {
        return diffItems.size();
    }
}
