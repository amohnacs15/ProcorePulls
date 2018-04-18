package com.amohnacs.procorepulls.main.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.amohnacs.model.PullRequest;
import com.amohnacs.procorepulls.R;
import com.amohnacs.procorepulls.diff.ui.DiffDetailsActivity;
import com.amohnacs.procorepulls.diff.ui.DiffDetailsFragment;

public class MainActivity extends AppCompatActivity implements MainFragment.OnListFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onListFragmentInteraction(PullRequest item) {
        Intent intent = new Intent(this, DiffDetailsActivity.class);
        intent.putExtra(DiffDetailsFragment.PR_TITLE, item.getTitle());
        intent.putExtra(DiffDetailsFragment.DIFF_URL, item.getDiffUrl());
        startActivity(intent);
    }
}
