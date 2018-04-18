package com.amohnacs.procorepulls.diff;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.amohnacs.common.Utils;
import com.amohnacs.common.mvp.BasePresenter;
import com.amohnacs.model.ProperDiffRow;

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

    private static final char POSITIVE = '+';
    private static final char NEGATIVE = '-';
    private static final char OTHER = ' ';

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
        if (hasError) {
            if (isViewAttached()) {
                getMvpView().displayError(t != null ? t.getMessage() : "Response not successful, see log");
            }
        } else {
            if (response != null) {
                if (isViewAttached()) {
                    getMvpView().updateList(properParseDiffResponse(response));
                }
            } else {
                if (isViewAttached()) {
                    getMvpView().displayError("Response is null");
                }
            }
        }
    }

    /**
     * Iterating through  the lines in the response and "pairing" our removals and additions based on the following conditions:
     * <ul>
     *     <li>If a removal is preceeding by something other than another removal, it is a new change.
     *     Otherwise there is a sequence of removals that could be followed (or not) by "paired" additions</li>
     *     <li>If an addition is preceeded by something other than a removal or additon. It is a new change.
     *     If it is preceeded by another addition, there is a sequence of additions that could be paired.  If
     *     it has been preceeded by any number of removals in the past then it is "paried"</li>
     *     <li>And then we just kind of mark anything else</li>
     * </ul>
     * @param responseBody
     * @return
     */
    private List<ProperDiffRow> properParseDiffResponse(ResponseBody responseBody) {
        ArrayList<ProperDiffRow> pages = new ArrayList<>();

        InputStreamReader inputReader = new InputStreamReader(responseBody.byteStream());
        BufferedReader bufferedReader = new BufferedReader(inputReader);

        String line;
        ProperDiffRow workingDiffRow = null;

        try {
            char previousChar = OTHER;
            ArrayList<String> accumulativeNegative = new ArrayList<>();
            ArrayList<String> accumulativePositive = new ArrayList<>();

            while ((line = bufferedReader.readLine()) != null) {

                String[] splitLine = line.split(" ", 2);

                if (splitLine.length >= 1) {

                    String actionFlag = splitLine[0];
                    String contentString = splitLine.length == 2 ? splitLine[1] : "";
                    String completeString = actionFlag + " " + contentString;

                    if (actionFlag.equals("diff")) {
                        //we are starting over so let's add our previous object to the array
                        if (workingDiffRow != null) {
                            pages.add(workingDiffRow);
                        }

                        workingDiffRow = new ProperDiffRow(completeString);
                        workingDiffRow.setDiffRows(new ArrayList<String[]>());

                        previousChar = OTHER;

                    } else if (actionFlag.contains("-") && workingDiffRow != null) {
                        //indicating a new row
                        if (previousChar == OTHER) {
                            accumulativeNegative.add(completeString);

                        } else if (previousChar == NEGATIVE) {
                            accumulativeNegative.add(completeString);

                        } //previous char should never be positive, because negative is always first
                        previousChar = NEGATIVE;

                    } else if (actionFlag.contains("+") && workingDiffRow != null) {
                        //indicating a new row
                        if (previousChar == OTHER) {
                            accumulativePositive.add(completeString);

                        } else if (previousChar == POSITIVE) {
                            accumulativePositive.add(completeString);

                        } else {
                            accumulativePositive.add(completeString);
                        }

                        previousChar = POSITIVE;

                    } else if (workingDiffRow != null) {
                        //this is where we want to analyze our accumulative lists and combine them into a series of rows
                        workingDiffRow.getDiffRows().addAll(addDiffsToPage(accumulativeNegative, accumulativePositive));
                        workingDiffRow.getDiffRows().add(new String[]{actionFlag + contentString});

                        previousChar = OTHER;
                    }
                }
            }
            //adding the last page we're working on
            if (workingDiffRow != null) {
                pages.add(workingDiffRow);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pages;
    }

    /**
     * Our two accumulated lists of removals and additions are "paired" together
     * @param accumulativeNegative
     * @param accumulativePositive
     * @return
     */
    private ArrayList<String[]> addDiffsToPage(ArrayList<String> accumulativeNegative, ArrayList<String> accumulativePositive) {
        ArrayList<String[]> diffRows = new ArrayList<>();

        if (Utils.isEmpty(accumulativeNegative) && Utils.isEmpty(accumulativePositive)) {
            return diffRows;
        } else if (Utils.isEmpty(accumulativeNegative)) {
            //right aligned
            for (String s : accumulativePositive) {
                diffRows.add(new String[]{"", s});
            }
            accumulativePositive.clear();
        } else if (Utils.isEmpty(accumulativePositive)) {
            //left aligned
            for (String s : accumulativeNegative) {
                diffRows.add(new String[]{s, ""});
            }
            accumulativeNegative.clear();
        } else {
            //both contain items, merge
            if (accumulativeNegative.size() > accumulativePositive.size()) {
                for (int i = 0; i < accumulativeNegative.size(); i++) {
                    //avoid index out of bounds exception
                    if (i < accumulativePositive.size()) {
                        diffRows.add(new String[]{
                                accumulativeNegative.get(i), accumulativePositive.get(i)
                        });
                    } else {
                        diffRows.add(new String[]{"", accumulativeNegative.get(i)});
                    }
                }
                accumulativeNegative.clear();
                accumulativePositive.clear();
            } else {
                for (int i = 0; i < accumulativePositive.size(); i++) {
                    if (i < accumulativeNegative.size()) {
                        diffRows.add(new String[]{
                                accumulativeNegative.get(i), accumulativePositive.get(i)
                        });
                    } else {
                        diffRows.add(new String[]{"", accumulativePositive.get(i)});
                    }
                }
                accumulativeNegative.clear();
                accumulativePositive.clear();
            }
        }

        return diffRows;
    }
}
