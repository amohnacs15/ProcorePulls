package com.amohnacs.model;

import java.util.List;

/**
 * Created by adrianmohnacs on 4/17/18.
 */

public class PullRequestResponse {

    private List<PullRequest> pullRequests;

    public PullRequestResponse(List<PullRequest> pullRequests) {
        this.pullRequests = pullRequests;
    }

    public List<PullRequest> getPullRequests() {
        return pullRequests;
    }

    public void setPullRequests(List<PullRequest> pullRequests) {
        this.pullRequests = pullRequests;
    }
}
