package com.amohnacs.procorepulls.diff.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.amohnacs.procorepulls.R;

/**
 * Created by adrianmohnacs on 4/18/18.
 */

public class IrrelevantViewHolder extends RecyclerView.ViewHolder {

    private TextView textView;

    public IrrelevantViewHolder(View itemView) {
        super(itemView);

        textView = itemView.findViewById(R.id.irrelevant_textView);
    }

    public void setText(String text) {
        textView.setText(text);
    }
}
