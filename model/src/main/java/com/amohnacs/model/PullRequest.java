package com.amohnacs.model;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * Created by adrianmohnacs on 4/17/18.
 */

public class PullRequest {

    @SerializedName("url")
    private String url;
    @SerializedName("id")
    private long id;
    @SerializedName("diff_url")
    private String diffUrl;
    @SerializedName("state")
    private State state;
    @SerializedName("title")
    private String title;
    @SerializedName("created_at")
    private Date createdDate;
    @SerializedName("assignee")
    private Assignee assignee;
    @SerializedName("labels")
    private List<Label> labels;

    public PullRequest(String url, long id, @NonNull String diffUrl) {
        this.url = url;
        this.id = id;
        this.diffUrl = diffUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDiffUrl() {
        return diffUrl;
    }

    public void setDiffUrl(String diffUrl) {
        this.diffUrl = diffUrl;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Assignee getAssignee() {
        return assignee;
    }

    public void setAssignee(Assignee assignee) {
        this.assignee = assignee;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }
}
