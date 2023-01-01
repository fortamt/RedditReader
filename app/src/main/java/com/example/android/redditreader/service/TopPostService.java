package com.example.android.redditreader.service;

import com.example.android.redditreader.model.RootApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TopPostService {

    @GET("top.json")
    Call<RootApiResponse> getTopPosts();

}