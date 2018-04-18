package com.amohnacs.procorepulls.diff;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amohnacs.model.ProperDiffRow;
import com.amohnacs.procorepulls.R;
import com.amohnacs.procorepulls.diff.ui.DiffDetailsFragment.OnListFragmentInteractionListener;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link com.amohnacs.model.ProperDiffRow} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 */
public class DiffAdapter extends RecyclerView.Adapter<DiffAdapter.DiffViewHolder> {

    private final List<ProperDiffRow> values;
    private final Context context;

    public DiffAdapter(Context context, ArrayList<ProperDiffRow> items) {
        this.context = context;
        values = items;
    }

    @Override
    public DiffViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_diff_row_item, parent, false);
        return new DiffViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DiffViewHolder holder, int position) {
        holder.mItem = values.get(position);
        holder.pageTitleTextView.setText(values.get(position).getPageTitle());

        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context));
        DiffDetailsRowAdapter adapter = new DiffDetailsRowAdapter(context, values.get(position).getDiffRows());
        holder.recyclerView.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    class DiffViewHolder extends RecyclerView.ViewHolder {

        TextView pageTitleTextView;
        RecyclerView recyclerView;
        ProperDiffRow mItem;

        DiffViewHolder(View view) {
            super(view);
            pageTitleTextView = view.findViewById(R.id.page_title_textView);
            recyclerView = view.findViewById(R.id.list);
        }
    }
}
