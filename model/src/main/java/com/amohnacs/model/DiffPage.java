package com.amohnacs.model;

import java.util.ArrayList;

/**
 * Created by adrianmohnacs on 4/17/18.
 */

public class DiffPage {

    private String page;
    private String index;
    private String irrelevantCollapsed;
    //string array will have size of two. index 0 is removal index 1 is additions
    //either one is possible of being null or empty
    private ArrayList<String[]> diffs;

}
