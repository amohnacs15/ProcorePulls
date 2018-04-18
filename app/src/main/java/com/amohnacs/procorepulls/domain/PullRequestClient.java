package com.amohnacs.procorepulls.domain;


import com.amohnacs.model.PullRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by adrianmohnacs on 4/17/18.
 */

public interface PullRequestClient {

    @GET("{owner}/{repo}/pulls")
    Call<List<PullRequest>> getPullRequests(
            @Path("owner") String repositoryOwner,
            @Path("repo") String repository
    );
}
