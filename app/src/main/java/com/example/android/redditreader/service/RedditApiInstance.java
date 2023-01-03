package com.example.android.redditreader.service;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.guava.GuavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RedditApiInstance {

    private static Retrofit retrofit = null;

    private static final String BASE_URL = "https://www.reddit.com/";

    public static TopPostService getService() {
        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(GuavaCallAdapterFactory.create())
                    .build();
        }

        return retrofit.create(TopPostService.class);
    }

}