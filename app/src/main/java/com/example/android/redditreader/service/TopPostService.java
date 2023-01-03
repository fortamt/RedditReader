package com.example.android.redditreader.service;

import com.example.android.redditreader.model.RootApiResponse;
import com.google.common.util.concurrent.ListenableFuture;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TopPostService {

    @GET("top.json")
    ListenableFuture<RootApiResponse> getTopPosts(
            @Query("after") String after,
            @Query("before") String before
    );

}