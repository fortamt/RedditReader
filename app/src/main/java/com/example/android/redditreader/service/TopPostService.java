package com.example.android.redditreader.service;

import com.example.android.redditreader.model.RootApiResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TopPostService {

    @GET("top.json")
    Single<RootApiResponse> getTopPosts(
            @Query("after") String after,
            @Query("before") String before
    );
}