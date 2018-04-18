package com.amohnacs.procorepulls.diff.ui;

import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diff_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getIntent().getExtras() != null) {
            diffUrl = getIntent().getExtras().getString(DiffDetailsFragment.DIFF_URL);
        }
        DiffDetailsFragment fragment = DiffDetailsFragment.newInstance(diffUrl);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, fragment).commit();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void onListFragmentInteraction(PullRequest item) {
        //no op
    }
}
