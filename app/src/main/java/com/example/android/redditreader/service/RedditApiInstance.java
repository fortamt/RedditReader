package com.example.android.redditreader.service;

import retrofit2.Retrofit;

import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RedditApiInstance {

    private static Retrofit retrofit = null;

    private static final String BASE_URL = "https://www.reddit.com/";

    public static TopPostService getService() {
        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build();
        }

        return retrofit.create(TopPostService.class);
    }
}