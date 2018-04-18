package com.amohnacs.procorepulls.diff;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.amohnacs.model.DiffPage;

import java.util.List;

import okhttp3.ResponseBody;


/**
 * Created by adrianmohnacs on 4/18/18.
 */

public interface Contract {

    interface Interactor {

        void getRawDiff(@NonNull Callback callback, String diffUrl);

        interface Callback {

            void onResponse(@Nullable ResponseBody response, @NonNull boolean hasError, @Nullable Throwable t);
        }
    }

    interface Presenter {
        void getDiffs(String diffUrl);
    }

    interface View {
        void updateList(List<DiffPage> items);
        void displayError(String message);
    }
}
