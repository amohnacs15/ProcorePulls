package com.amohnacs.procorepulls.diff;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.amohnacs.common.Utils;
import com.amohnacs.common.mvp.BasePresenter;
import com.amohnacs.model.DiffPage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;

/**
 * Created by adrianmohnacs on 4/17/18.
 */

public class DiffDetailPresenter extends BasePresenter<Contract.View> implements Contract.Presenter, Contract.Interactor.Callback {
    private static final String TAG = DiffDetailPresenter.class.getSimpleName();

    private static volatile DiffDetailPresenter instance;

    private Context context;
    private DiffDetailInteractor interactor;

    private DiffDetailPresenter(Context context) {
        this.context = context;
        interactor = DiffDetailInteractor.getInstance(context);
    }

    public static DiffDetailPresenter getInstance(Context context) {
        if (instance == null) {
            synchronized (DiffDetailPresenter.class) {
                if (instance == null) {
                    instance = new DiffDetailPresenter(context);
                }
            }
        }
        return instance;
    }

    @Override
    public void getDiffs(String diffUrl) {
        interactor.getRawDiff(this, diffUrl);
    }

    @Override
    public void onResponse(@Nullable ResponseBody response, @NonNull boolean hasError, @Nullable Throwable t) {
        if(hasError) {
            if (isViewAttached()) {
                getMvpView().displayError(t != null ? t.getMessage() : "Response not successful, see log");
            }
        } else {
            if (response != null) {
                if(isViewAttached()) {
                    getMvpView().updateList(parseResponse(response));
                }
            } else {
                if (isViewAttached()) {
                    getMvpView().displayError("Response is null");
                }
            }
        }
    }

    /**
     * Convert our raw response to a byte stream which is then read one line at a time.  We look at the
     * first word of the line to determine it's purpose within the diff (file address, addition, or deletion).
     * These are then added to our {@link DiffPage} object
     * @param response
     * @return
     */
    private List<DiffPage> parseResponse(ResponseBody response) {
        ArrayList<DiffPage> pages = new ArrayList<>();

        InputStreamReader inputReader = new InputStreamReader(response.byteStream());
        BufferedReader bufferedReader = new BufferedReader(inputReader);

        String line;
        DiffPage workingDiffPage = null;

        try {
            while ((line = bufferedReader.readLine()) != null) {

                String[] splitLine = line.split(" ", 2);
                // TODO: 4/18/18 one of your array object may be index out of bounds
                //process if it is not empty
                if (splitLine.length >= 1) {
                    String actionFlag = splitLine[0];
                    String contentString = splitLine.length == 2 ? splitLine[1] : "";

                    if (!Utils.isEmpty(actionFlag)) {
                        if (actionFlag.equals("diff")) {
                            //we are starting over so let's add our previous object to the array
                            if(workingDiffPage != null) {
                                pages.add(workingDiffPage);
                            }

                            String[] diffArr = new String[]{actionFlag, contentString};

                            workingDiffPage = new DiffPage(diffArr);
                            workingDiffPage.setNegativeDiffs(new ArrayList<String[]>());
                            workingDiffPage.setPositiveDiffs(new ArrayList<String[]>());

                        } else if (actionFlag.contains("-")) {

                            if (workingDiffPage != null) {
                                String[] negativeArr = new String[]{actionFlag, contentString};
                                workingDiffPage.getNegativeDiffs().add(negativeArr);
                            }
                        } else if (actionFlag.contains("+")) {

                            if(workingDiffPage != null) {
                                String[] positiveArr = new String[]{actionFlag, contentString};

                                workingDiffPage.getPositiveDiffs().add(positiveArr);
                            }
                        } else if (actionFlag.equals("index")) {
                            if(workingDiffPage != null) {
                                String[] indexArr = new String[]{actionFlag, contentString};
                                workingDiffPage.setIndex(indexArr);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pages;
    }
}
