package com.amohnacs.procorepulls.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.amohnacs.common.mvp.BasePresenter;
import com.amohnacs.model.PullRequest;
import com.amohnacs.model.State;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adrianmohnacs on 4/17/18.
 */

public class MainPresenter extends BasePresenter<Contract.View> implements Contract.Presenter, Contract.Interactor.Callback{
    private static final String TAG = MainPresenter.class.getSimpleName();
    private static final String HABATICA_OWNER = "HabitRPG";
    private static final String HABATICA_REPO = "habitica";

    private static volatile MainPresenter instance;

    private Context context;
    private MainInteractor interactor;

    private MainPresenter(Context context) {
        this.context = context;
        interactor = MainInteractor.getInstance(context);
    }

    public static MainPresenter getInstance(Context context) {
        if (instance == null) {
            synchronized (MainPresenter.class) {
                if (instance == null) {
                    instance = new MainPresenter(context);
                }
            }
        }
        return instance;
    }

    @Override
    public void getPullRequests() {
        interactor.getPullRequests(this, HABATICA_OWNER, HABATICA_REPO);
    }

    @Override
    public void onResponse(@Nullable List<PullRequest> response, @NonNull boolean hasError, @Nullable Throwable t) {
        if (hasError) {
            if (isViewAttached()) {
                getMvpView().displayError(t != null ? t.getMessage() : "Response not successful, see log");
            }
        } else {
            //successful response
            ArrayList<PullRequest> openList = new ArrayList<>();
            for (PullRequest pr : response) {
                if (pr.getState() == State.OPEN) {
                    openList.add(pr);
                }
            }
            if(isViewAttached()) {
                getMvpView().updateList(openList);
            }
        }
    }


}
