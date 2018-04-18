package com.amohnacs.procorepulls.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.amohnacs.model.PullRequest;
import com.amohnacs.procorepulls.domain.PullRequestClient;
import com.amohnacs.procorepulls.domain.RetrofitClientGenerator;

import java.lang.ref.WeakReference;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by adrianmohnacs on 4/17/18.
 */

public class MainInteractor implements Contract.Interactor {
    private static final String TAG = MainInteractor.class.getSimpleName();

    private static volatile MainInteractor instance;

    private Context context;
    private PullRequestClient pullRequestClient;
    private WeakReference<Callback> weakCallback;

    private MainInteractor(Context context) {
        this.context = context;
        pullRequestClient = RetrofitClientGenerator.generateClient(PullRequestClient.class);
    }

    public static MainInteractor getInstance(Context context) {
        if (instance == null) {
            synchronized (MainInteractor.class) {
                if (instance == null) {
                    instance = new MainInteractor(context);
                }
            }
        }
        return instance;
    }


    @Override
    public void getPullRequests(@NonNull Callback callback, String owner, String repository) {

        weakCallback = new WeakReference<>(callback);

        Call<List<PullRequest>> pullRequestsCall = pullRequestClient
                .getPullRequests(owner, repository);

        pullRequestsCall.enqueue(new retrofit2.Callback<List<PullRequest>>() {
            @Override
            public void onResponse(Call<List<PullRequest>> call, Response<List<PullRequest>> response) {
                if (response.isSuccessful()) {
                    if (weakCallback != null) {
                        weakCallback.get().onResponse(response.body(), false, null);
                    }
                } else {
                    Log.e(TAG, String.valueOf(response.errorBody()));
                    if(weakCallback != null) {
                        weakCallback.get().onResponse(null, true, null);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<PullRequest>> call, Throwable t) {
                Log.e(TAG, String.valueOf(t.getMessage()));
                if(weakCallback != null) {
                    weakCallback.get().onResponse(null, true, t);
                }
            }
        });
    }
}
