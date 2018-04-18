package com.amohnacs.procorepulls.diff;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.amohnacs.procorepulls.domain.PullRequestClient;
import com.amohnacs.procorepulls.domain.RetrofitClientGenerator;

import java.lang.ref.WeakReference;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by adrianmohnacs on 4/17/18.
 */

public class DiffDetailInteractor implements Contract.Interactor {
    private static final String TAG = DiffDetailInteractor.class.getSimpleName();

    private static volatile DiffDetailInteractor instance;

    private Context context;
    private PullRequestClient pullRequestClient;
    private WeakReference<Callback> weakCallback;

    private DiffDetailInteractor(Context context) {
        this.context = context;
        pullRequestClient = RetrofitClientGenerator.generateClient(PullRequestClient.class);
    }

    public static DiffDetailInteractor getInstance(Context context) {
        if (instance == null) {
            synchronized (DiffDetailInteractor.class) {
                if (instance == null) {
                    instance = new DiffDetailInteractor(context);
                }
            }
        }
        return instance;
    }

    @Override
    public void getRawDiff(@NonNull Callback callback, String diffUrl) {
        weakCallback = new WeakReference<>(callback);

        Call<ResponseBody> pullRequestCall = pullRequestClient.getDiff(diffUrl);

        pullRequestCall.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    //successful response
                    Log.d(TAG, "Raw Success!");
                    if(weakCallback != null) {
                        weakCallback.get().onResponse(response.body(), false, null);
                    }
                } else {
                    Log.e(TAG, String.valueOf(response.errorBody()));
                    if (weakCallback != null) {
                        weakCallback.get().onResponse(null, true, null);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, String.valueOf(t.getMessage()));
                if (weakCallback != null) {
                    weakCallback.get().onResponse(null, true, t);
                }
            }
        });
    }
}
