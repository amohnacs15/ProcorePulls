package com.amohnacs.procorepulls.main.ui;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.amohnacs.model.PullRequest;
import com.amohnacs.procorepulls.R;

import java.util.Date;

/**
 * Created by adrianmohnacs on 4/17/18.
 */

public class PRViewHolder extends RecyclerView.ViewHolder {

    public final View view;
    public PullRequest item;

    private final TextView titleTextView;
    private final TextView labelTextView;
    private final TextView createdTextView;

    public PRViewHolder(View view) {
        super(view);

        this.view = view;

        titleTextView = view.findViewById(R.id.title_textView);
        labelTextView = view.findViewById(R.id.label_textView);
        createdTextView = view.findViewById(R.id.created_textView);
    }

    public void setTitle(String title) {
        titleTextView.setText(title);
    }

    public void setLabelTextView(String text, String colorHex) {
        labelTextView.setText(text);
        labelTextView.setBackgroundColor(Color.parseColor('#' + colorHex));
    }

    public void setCreatedString(Context context, long id, Date date, String user) {
        createdTextView.setText(String.format(
                context.getResources().getString(R.string.created_string),
                id, date, user)
        );
    }

    public void setItem(PullRequest item) {
        this.item = item;
    }
}
