package com.amohnacs.procorepulls.diff.ui;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.amohnacs.model.PullRequest;
import com.amohnacs.procorepulls.R;

public class DiffDetailsActivity extends AppCompatActivity implements DiffDetailsFragment.OnListFragmentInteractionListener {
    private static final String TAG = DiffDetailsActivity.class.getSimpleName();
    private String diffUrl;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diff_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.toolbar_layout);

        if(getIntent().getExtras() != null) {
            title = getIntent().getExtras().getString(DiffDetailsFragment.PR_TITLE);
            diffUrl = getIntent().getExtras().getString(DiffDetailsFragment.DIFF_URL);
        }

        collapsingToolbarLayout.setTitle(title);

        DiffDetailsFragment fragment = DiffDetailsFragment.newInstance(title, diffUrl);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, fragment).commit();
    }

    @Override
    public void onListFragmentInteraction(PullRequest item) {
        //no op
    }
}
