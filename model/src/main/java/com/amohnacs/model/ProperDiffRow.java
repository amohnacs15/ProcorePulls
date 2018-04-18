package com.amohnacs.model;

import java.util.ArrayList;

/**
 * Created by adrianmohnacs on 4/18/18.
 */

public class ProperDiffRow {

    private String pageTitle;
    //index 0 is negative, index 1 is positive
    private ArrayList<String[]> diffRows;

    public ProperDiffRow(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public ArrayList<String[]> getDiffRows() {
        return diffRows;
    }

    public void setDiffRows(ArrayList<String[]> diffRows) {
        this.diffRows = diffRows;
    }
}
