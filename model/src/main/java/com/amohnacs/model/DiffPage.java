package com.amohnacs.model;

import java.util.ArrayList;

/**
 * Created by adrianmohnacs on 4/17/18.
 */

public class DiffPage {

    private String page;
    private String index;
    private String irrelevantCollapsed;

    private ArrayList<String> negativeDiffs;
    private ArrayList<String> positiveDiffs;

    public DiffPage(String page) {
        this.page = page;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getIrrelevantCollapsed() {
        return irrelevantCollapsed;
    }

    public void setIrrelevantCollapsed(String irrelevantCollapsed) {
        this.irrelevantCollapsed = irrelevantCollapsed;
    }

    public ArrayList<String> getNegativeDiffs() {
        return negativeDiffs;
    }

    public void setNegativeDiffs(ArrayList<String> negativeDiffs) {
        this.negativeDiffs = negativeDiffs;
    }

    public ArrayList<String> getPositiveDiffs() {
        return positiveDiffs;
    }

    public void setPositiveDiffs(ArrayList<String> positiveDiffs) {
        this.positiveDiffs = positiveDiffs;
    }
}
