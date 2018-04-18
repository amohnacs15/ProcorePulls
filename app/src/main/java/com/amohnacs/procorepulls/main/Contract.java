package com.amohnacs.procorepulls.main;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.amohnacs.model.PullRequest;

import java.util.List;

/**
 * Created by adrianmohnacs on 4/17/18.
 */

public interface Contract {

    interface Interactor {

        void getPullRequests(@NonNull Callback callback, String owner, String repository);

        interface Callback {
            void onResponse(@Nullable List<PullRequest> response, @NonNull boolean hasError, @Nullable Throwable t);
        }
    }

    interface Presenter {
        void getPullRequests();

    }

    interface View {

        void updateList(List<PullRequest> items);
        void displayError(String message);
    }
}
