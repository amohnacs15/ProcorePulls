package com.amohnacs.procorepulls.diff.ui;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.TextView;

import com.amohnacs.procorepulls.R;

/**
 * Created by adrianmohnacs on 4/18/18.
 */

public class SplitViewHolder extends ViewHolder {

    private final Context context;
    private TextView removalTextView, additionTextView;

    public SplitViewHolder(Context context, View itemView) {
        super(itemView);
        this.context = context;
        removalTextView = itemView.findViewById(R.id.removal_textView);
        additionTextView = itemView.findViewById(R.id.addition_textView);
    }

    //already determined to be of length two
    public void setDiffRow(String[] array) {
        if(!array[0].equals("") && !array[1].equals("")) {

            removalTextView.setText(array[0]);
            additionTextView.setText(array[1]);

            removalTextView.setBackgroundColor(ContextCompat.getColor(context, R.color.gitRed));
            additionTextView.setBackgroundColor(ContextCompat.getColor(context, R.color.gitGreen));

        } else if (array[0].equals("")) {

            additionTextView.setText(array[1]);

            removalTextView.setBackgroundColor(ContextCompat.getColor(context, R.color.gitBackground));
            additionTextView.setBackgroundColor(ContextCompat.getColor(context, R.color.gitGreen));

        } else {

            removalTextView.setText(array[0]);

            removalTextView.setBackgroundColor(ContextCompat.getColor(context, R.color.gitRed));
            additionTextView.setBackgroundColor(ContextCompat.getColor(context, R.color.gitBackground));
        }
    }

}
