package com.amohnacs.procorepulls.main.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.amohnacs.model.PullRequest;
import com.amohnacs.procorepulls.R;

public class MainActivity extends AppCompatActivity implements MainFragment.OnListFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onListFragmentInteraction(PullRequest item) {
        //no op
    }
}
